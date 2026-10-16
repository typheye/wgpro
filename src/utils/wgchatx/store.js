/*
 * wgchatx 本地存储层。
 * 只依赖已验证的 utils/file.js 与 utils/storage.js，不直接使用 @system.*。
 * 与项目其它模块保持一致：默认导出（export default），避免命名导出的 interop 差异。
 *
 * 目录：internal://files/wgchatx/
 *   <roomId>.meta.json        房间元信息 + 分片索引 + outbox
 *   <roomId>.<startId>.ndjson 每行一条消息（3–5KB 一片）
 */
import file from "../file.js";
import storage from "../storage.js";

const DIR = "internal://files/wgchatx/";
const ROOM_KEY = "wgsettings_wgchatx_roomlist";
const ENGINE_KEY = "wgsettings_iswgchatx";
const SEG_ROLL = 5 * 1024; // 超过 ~5KB 滚动新分片
const ROOM_BUDGET = 512 * 1024; // 每房间本地缓存上限
const PAGE_SIZE = 20; // 官方建议每页 <= 20

function metaUri(roomId) {
  return DIR + roomId + ".meta.json";
}
function segUri(roomId, startId) {
  return DIR + roomId + "." + startId + ".ndjson";
}
function byteLen(str) {
  let n = 0;
  for (let i = 0; i < str.length; i++) {
    const c = str.charCodeAt(i);
    n += c < 0x80 ? 1 : c < 0x800 ? 2 : 3;
  }
  return n;
}
function sortShards(shards) {
  shards.sort((a, b) => a.start - b.start);
  return shards;
}
function emptyMeta(roomId, room) {
  return {
    room: room || { id: roomId, title: "" },
    latest_id: 0,
    oldest_id: 0,
    last_read_id: 0,
    shards: [],
    outbox: [],
    updated_at: 0,
  };
}

function readMeta(roomId, cb) {
  file.access({
    uri: metaUri(roomId),
    success: () => {
      file.readText({
        uri: metaUri(roomId),
        success: (res) => {
          let meta = null;
          try {
            meta = JSON.parse(res.text);
          } catch (e) {}
          cb(null, meta || emptyMeta(roomId));
        },
        fail: () => cb(null, emptyMeta(roomId)),
      });
    },
    fail: () => cb(null, emptyMeta(roomId)),
  });
}

function saveMeta(roomId, meta, cb) {
  meta.updated_at = Date.now();
  meta.room = meta.room || { id: roomId, title: "" };
  file.writeText({
    uri: metaUri(roomId),
    text: JSON.stringify(meta),
    success: () => cb && cb(null, meta),
    fail: (data, code) => cb && cb("meta write fail " + code),
  });
}

function readShard(roomId, shard, cb) {
  file.readText({
    uri: segUri(roomId, shard.start),
    success: (res) => {
      const out = [];
      const lines = (res.text || "").split("\n");
      for (let i = 0; i < lines.length; i++) {
        const line = lines[i].trim();
        if (!line) continue;
        try {
          out.push(JSON.parse(line));
        } catch (e) {}
      }
      cb(null, out);
    },
    fail: () => cb(null, []),
  });
}

/** 追加“更新”的消息（id 大于 latest_id），必要时滚分片。 */
function appendNew(roomId, meta, msgs, cb) {
  if (!msgs || !msgs.length) {
    cb && cb(null, meta);
    return;
  }
  const lastId = meta.latest_id || 0;
  const fresh = msgs
    .filter((m) => (m.id || 0) > lastId)
    .sort((a, b) => a.id - b.id);
  if (!fresh.length) {
    cb && cb(null, meta);
    return;
  }
  const lines = fresh.map((m) => JSON.stringify(m)).join("\n") + "\n";
  const bytes = byteLen(lines);
  const shards = sortShards(meta.shards || []);
  const last = shards.length ? shards[shards.length - 1] : null;

  const finish = () => {
    meta.latest_id = fresh[fresh.length - 1].id;
    if (!meta.oldest_id) meta.oldest_id = fresh[0].id;
    trim(roomId, meta);
    saveMeta(roomId, meta, (e) => cb && cb(e, meta));
  };

  if (!last) {
    const startId = fresh[0].id;
    file.writeText({
      uri: segUri(roomId, startId),
      text: lines,
      success: () => {
        meta.shards = [
          {
            start: startId,
            end: fresh[fresh.length - 1].id,
            bytes: bytes,
            count: fresh.length,
          },
        ];
        finish();
      },
      fail: (data, code) => cb && cb("seg write fail " + code),
    });
    return;
  }

  if ((last.bytes || 0) + bytes <= SEG_ROLL) {
    file.readText({
      uri: segUri(roomId, last.start),
      success: (res) => {
        const text = (res.text || "") + lines;
        file.writeText({
          uri: segUri(roomId, last.start),
          text: text,
          success: () => {
            last.bytes = byteLen(text);
            last.count = (last.count || 0) + fresh.length;
            last.end = fresh[fresh.length - 1].id;
            finish();
          },
          fail: (data, code) => cb && cb("seg append fail " + code),
        });
      },
      fail: () => {
        const startId = fresh[0].id;
        file.writeText({
          uri: segUri(roomId, startId),
          text: lines,
          success: () => {
            meta.shards.push({
              start: startId,
              end: fresh[fresh.length - 1].id,
              bytes: bytes,
              count: fresh.length,
            });
            finish();
          },
          fail: (data, code) => cb && cb("seg write fail " + code),
        });
      },
    });
    return;
  }

  const startId = fresh[0].id;
  file.writeText({
    uri: segUri(roomId, startId),
    text: lines,
    success: () => {
      meta.shards.push({
        start: startId,
        end: fresh[fresh.length - 1].id,
        bytes: bytes,
        count: fresh.length,
      });
      finish();
    },
    fail: (data, code) => cb && cb("seg write fail " + code),
  });
}

/** 插入“更旧”的一页（id 小于 oldest_id），单独成片。 */
function insertOlder(roomId, meta, msgs, cb) {
  if (!msgs || !msgs.length) {
    cb && cb(null, meta);
    return;
  }
  const oldest = meta.oldest_id || Infinity;
  const older = msgs
    .filter((m) => (m.id || 0) < oldest)
    .sort((a, b) => a.id - b.id);
  if (!older.length) {
    cb && cb(null, meta);
    return;
  }
  const lines = older.map((m) => JSON.stringify(m)).join("\n") + "\n";
  const startId = older[0].id;
  file.writeText({
    uri: segUri(roomId, startId),
    text: lines,
    success: () => {
      meta.shards = sortShards(
        (meta.shards || []).concat([
          {
            start: startId,
            end: older[older.length - 1].id,
            bytes: byteLen(lines),
            count: older.length,
          },
        ])
      );
      meta.oldest_id = startId;
      trim(roomId, meta);
      saveMeta(roomId, meta, (e) => cb && cb(e, meta));
    },
    fail: (data, code) => cb && cb("seg write fail " + code),
  });
}

/** 读取最新 limit 条（升序）。 */
function readNewest(roomId, meta, limit, cb) {
  const shards = sortShards((meta.shards || []).slice());
  let acc = [];
  const step = (idx) => {
    if (idx < 0 || acc.length >= limit) {
      cb(null, acc.slice(acc.length > limit ? acc.length - limit : 0));
      return;
    }
    readShard(roomId, shards[idx], (e, arr) => {
      acc = arr.concat(acc);
      step(idx - 1);
    });
  };
  step(shards.length - 1);
}

/** 读取比 beforeId 更旧的 limit 条（升序）；第三个参数为是否还有更多。 */
function readOlder(roomId, meta, beforeId, limit, cb) {
  const shards = sortShards((meta.shards || []).slice());
  let acc = [];
  const step = (idx) => {
    if (idx < 0 || acc.length >= limit + 1) {
      cb(null, acc.slice(0, limit), acc.length > limit);
      return;
    }
    const sh = shards[idx];
    if (sh.start >= beforeId) {
      step(idx - 1);
      return;
    }
    readShard(roomId, sh, (e, arr) => {
      const older = arr.filter((m) => (m.id || 0) < beforeId);
      acc = older.concat(acc);
      step(idx - 1);
    });
  };
  step(shards.length - 1);
}

function trim(roomId, meta) {
  const shards = sortShards(meta.shards || []);
  let total = 0;
  for (let i = 0; i < shards.length; i++) total += shards[i].bytes || 0;
  while (shards.length > 1 && total > ROOM_BUDGET) {
    const old = shards.shift();
    total -= old.bytes || 0;
    try {
      file.delete({ uri: segUri(roomId, old.start) });
    } catch (e) {}
  }
  meta.shards = shards;
  if (shards.length) meta.oldest_id = shards[0].start;
}

function clearRoom(roomId, meta, cb) {
  const shards = (meta && meta.shards) || [];
  let pending = shards.length + 1;
  const done = () => {
    if (--pending <= 0) cb && cb(null);
  };
  for (let i = 0; i < shards.length; i++) {
    file.delete({ uri: segUri(roomId, shards[i].start), success: done, fail: done });
  }
  file.delete({ uri: metaUri(roomId), success: done, fail: done });
}

/* 房间列表（本地）：[{id, title, unread?}] */
function listRooms(cb) {
  storage.get({
    key: ROOM_KEY,
    success: (data) => {
      let list = [];
      try {
        list = JSON.parse(data) || [];
      } catch (e) {}
      cb(list);
    },
    fail: () => cb([]),
  });
}
function saveRooms(list, cb) {
  storage.set({
    key: ROOM_KEY,
    value: JSON.stringify(list || []),
    success: () => cb && cb(null),
    fail: () => cb && cb("room list save fail"),
  });
}

/* 引擎开关：true=微聊 X，false=腕上微聊（旧版） */
function getEngine(cb) {
  storage.get({
    key: ENGINE_KEY,
    success: (data) => cb(data !== false),
    fail: () => cb(true),
  });
}
function setEngine(iswgchatx, cb) {
  storage.set({
    key: ENGINE_KEY,
    value: !!iswgchatx,
    success: () => cb && cb(null),
    fail: () => cb && cb("engine save fail"),
  });
}

export default {
  PAGE_SIZE,
  emptyMeta,
  readMeta,
  saveMeta,
  appendNew,
  insertOlder,
  readNewest,
  readOlder,
  clearRoom,
  listRooms,
  saveRooms,
  getEngine,
  setEngine,
};
