import file from "@system.file";

const FILE_PATH = "internal://files/storage.dat";

class Storage {
  constructor() {
    this.defSettings = {
      wgsettings_init: false,
      wgsettings_autoCheckUpdate: true,
      wgsettings_autoCheckUpdateIsShow: false,
      wgsettings_keyboardUseT9: false,
      wgsettings_keyboardUseLongVibrate: false,
      wgsettings_enableX1WebView: false,
      wgsettings_fm_saveCopyCache: false,
      wgsettings_fm_cachePath: "",
      wgsettings_fm_cacheCtrl: "",
      wgsettings_browser_hideDebug: false,
      wgsettings_browser_defUri: "https://www.bilibili.com",
      wgsettings_taccount_username: "",
      wgsettings_taccount_password: "",
      wgsettings_taccount_cookie: "",
      wgsettings_taccount_nick: "",
      wgsettings_taccount_userinfo: "",
      wgsettings_taccount_allowShare: false,
      wgsettings_wgchat_roomlist: JSON.stringify([
        { id: "10001", name: "官方聊天室" },
      ]),
      wgsettings_plugin_pluglist: JSON.stringify([
        { id: "10000", name: "扩展推荐" },
      ]),
    };
    this.data = {};
    this.ready = false;
  }

  init(options = {}) {
    const { success, fail } = options;

    file.access({
      uri: FILE_PATH,
      success: () => {
        this._readFile(success, fail);
      },
      fail: () => {
        this._resetStorage(success, fail);
      },
    });
  }

  _readFile(success, fail) {
    file.readText({
      uri: FILE_PATH,
      success: (res) => {
        try {
          this.data = JSON.parse(res.text || "{}");
          this.ready = true;
          success && success("初始化成功");
        } catch (e) {
          fail && fail("解析失败: " + e.message);
        }
      },
      fail: (err, code) => {
        fail && fail(`读取失败: ${code} - ${err}`);
      },
    });
  }

  _resetStorage(success, fail) {
    this.data = { ...this.defSettings };
    file.writeText({
      uri: FILE_PATH,
      text: JSON.stringify(this.data),
      success: () => {
        this.ready = true;
        success && success("存储重置成功");
      },
      fail: (err, code) => {
        fail && fail(`保存失败: ${code} - ${err}`);
      },
    });
  }

  get(options = {}) {
    const { key, success, fail } = options;

    if (!this.ready) {
      this.init({
        success: () => this.get(options),
        fail,
      });
      return;
    }

    success && success(this.data[key]);
  }

  gets(options = {}) {
    const { keys, success, fail } = options;

    // 参数校验：确保 keys 是数组
    if (!Array.isArray(keys)) {
      const errorMsg = "参数错误: keys 必须是数组";
      //console.error(errorMsg);
      fail && fail(errorMsg);
      return;
    }

    if (!this.ready) {
      this.init({
        success: () => this.gets(options),
        fail,
      });
      return;
    }

    const result = {};
    for (const key of keys) {
      // 安全访问，防止未定义键
      result[key] = this.data.hasOwnProperty(key) ? this.data[key] : undefined;
    }

    success && success(result);
  }

  set(options = {}) {
    const { key, value, success, fail } = options;

    if (!this.ready) {
      this.init({
        success: () => this.set(options),
        fail,
      });
      return;
    }

    this.data[key] = value;
    file.writeText({
      uri: FILE_PATH,
      text: JSON.stringify(this.data),
      success: () => {
        success && success("保存成功");
      },
      fail: (err, code) => {
        fail && fail(`保存失败: ${code} - ${err}`);
      },
    });
  }

  sets(options = {}) {
    const { keys, values, success, fail } = options;
    // 参数校验：确保keys和values都是数组，且长度相同
    if (!Array.isArray(keys) || !Array.isArray(values)) {
      const errorMsg = "参数错误: keys和values都必须是数组";
      //console.error(errorMsg);
      fail && fail(errorMsg);
      return;
    }
    if (keys.length !== values.length) {
      const errorMsg = `参数错误: keys和values长度不一致 (keys:${keys.length}, values:${values.length})`;
      //console.error(errorMsg);
      fail && fail(errorMsg);
      return;
    }
    if (!this.ready) {
      this.init({
        success: () => this.sets(options),
        fail,
      });
      return;
    }
    // 更新数据
    for (let i = 0; i < keys.length; i++) {
      this.data[keys[i]] = values[i];
    }
    // 写入文件
    file.writeText({
      uri: FILE_PATH,
      text: JSON.stringify(this.data),
      success: () => {
        success && success("批量保存成功");
      },
      fail: (err, code) => {
        fail && fail(`批量保存失败: ${code} - ${err}`);
      },
    });
  }

  delete(options = {}) {
    const { key, success, fail } = options;

    if (!this.ready) {
      this.init({
        success: () => this.delete(options),
        fail,
      });
      return;
    }

    delete this.data[key];
    file.writeText({
      uri: FILE_PATH,
      text: JSON.stringify(this.data),
      success: () => {
        success && success("删除成功");
      },
      fail: (err, code) => {
        fail && fail(`保存失败: ${code} - ${err}`);
      },
    });
  }

  clear(options = {}) {
    const { success, fail } = options;
    this._resetStorage(success, fail);
  }
}

export default new Storage();
