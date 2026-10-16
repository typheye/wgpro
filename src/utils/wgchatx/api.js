/*
 * wgchatx 网络层。
 * 复用 app.ux 的 taccV2Request（其内部用已验证的 utils/fetch.js + taccV2Headers）。
 * 默认导出（与项目其它模块一致）。
 *
 * 用法：const api = createApi(this.$app.$def);
 */
const PAGE_SIZE = 20;

function createApi(app) {
  const call = (action, opts, cb) =>
    app.taccV2Request(
      action,
      Object.assign({ url: app.taccWgchatxUrl() }, opts || {}),
      cb
    );
  return {
    /** config: cb(ok, json, msg) */
    config: (cb) => call("config", {}, cb),
    /** rooms: GET ?type=rooms  query={page,size} */
    rooms: (query, cb) => call("rooms", { query: query }, cb),
    /** messages: GET ?type=messages  query={room_id,before_id|since_id,limit} */
    messages: (query, cb) => call("messages", { query: query }, cb),
    /** send: POST ?type=send */
    send: (data, cb) => call("send", { data: data }, cb),
    /** read: POST ?type=read */
    read: (data, cb) => call("read", { data: data }, cb),
    /** recall: POST ?type=recall */
    recall: (data, cb) => call("recall", { data: data }, cb),
    createRoom: (data, cb) => call("create", { data: data }, cb),
    joinRoom: (data, cb) => call("join", { data: data }, cb),
    leaveRoom: (data, cb) => call("leave", { data: data }, cb),
  };
}

export default { PAGE_SIZE, createApi };
