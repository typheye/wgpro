import file from '@system.file';

const FILE_PATH = "internal://files/storage.bin";

class Storage {
  constructor() {
    this.defSettings = {
      wgsettings_init: "false",
      wgsettings_autoCheckUpdate: "true",
      wgsettings_autoCheckUpdateIsShow: "false",
      wgsettings_taccount_username: "",
      wgsettings_taccount_password: "",
      wgsettings_taccount_cookie: "",
      wgsettings_taccount_nick: "",
      wgsettings_taccount_userinfo: "",
      wgsettings_taccount_allowShare: "",
      wgsettings_wgchat_roomlist: JSON.stringify([
        { id: "10001", name: "官方聊天室" },
        { id: "10002", name: "用户反馈" },
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
      }
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
      }
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
      }
    });
  }

  get(options = {}) {
    const { key, success, fail } = options;
    
    if (!this.ready) {
      this.init({
        success: () => this.get(options),
        fail
      });
      return;
    }
    
    success && success(this.data[key]);
  }

  set(options = {}) {
    const { key, value, success, fail } = options;
    
    if (!this.ready) {
      this.init({
        success: () => this.set(options),
        fail
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
      }
    });
  }

  delete(options = {}) {
    const { key, success, fail } = options;
    
    if (!this.ready) {
      this.init({
        success: () => this.delete(options),
        fail
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
      }
    });
  }

  clear(options = {}) {
    const { success, fail } = options;
    this._resetStorage(success, fail);
  }
}

export default new Storage();