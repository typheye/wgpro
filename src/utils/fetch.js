import deffetch from "@system.fetch";
import interconnect from "@system.interconnect";

const enableFetch = true; // 启用系统fetch，否则使用代理

class fetch {
  constructor() {
    if (!enableFetch) {
      this.conn = interconnect.instance();
      this.msgid_HELLO = 0;
      this.pendingRequests = new Map(); // 使用Map存储待处理请求
      this._setupMessageHandler(); // 设置全局消息处理器
      this._sayHello();
    }
  }

  // 安全返回 enableFetch 状态
  isEnableFetch() {
    return enableFetch;
  }

  _sayHello() {
    const id = this._generateUniqueId();
    this.msgid_HELLO = id;
    const message = JSON.stringify({
      msgtype: "HELLO",
      message: "",
    });
    this.conn.send({
      data: {
        id,
        message,
      },
    });
  }

  // 设置全局消息处理器 (只设置一次)
  _setupMessageHandler() {
    this.conn.onmessage = (event) => {
      try {
        const message = JSON.parse(event.data);
        const { id, response } = message;

        // 查找对应的请求处理器
        const handler = this.pendingRequests.get(id);
        if (handler && id != this.msgid_HELLO) {
          const res = JSON.parse(response);
          handler.success({
            data: res.data,
            code: res.code,
          });
          // 请求完成后移除处理器
          this.pendingRequests.delete(id);
        }
      } catch (e) {
        //console.error("Failed to parse message:", e);
        //fail(JSON.stringify(e), -1);
      }
    };
  }

  _generateUniqueId() {
    return Math.random().toString(36).substr(2, 9);
  }

  fetch(options = {}) {
    const {
      url,
      data_GET = {},
      data = {},
      header,
      method = "GET",
      responseType = "text",
      success,
      fail,
    } = options;
    const toQueryString = (obj) => {
      try {
        // 类型检查
        if (!obj || typeof obj !== "object") {
          //console.warn("Invalid data for query string:", obj);
          return "";
        }

        return Object.entries(obj)
          .filter(([_, value]) => value !== null && value !== undefined)
          .flatMap(([key, value]) => {
            // 处理嵌套对象
            if (value && typeof value === "object") {
              return Object.entries(value).map(
                ([subKey, subValue]) =>
                  `${encodeURIComponent(key)}[${encodeURIComponent(
                    subKey
                  )}]=${encodeURIComponent(subValue)}`
              );
            }
            return `${encodeURIComponent(key)}=${encodeURIComponent(value)}`;
          })
          .join("&");
      } catch (e) {
        //console.error("Query string generation error:", e);
        return "";
      }
    };
    var headers = {
      Accept:
        "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/jxl,image/webp,image/png,image/svg+xml,*/*;q=0.8",
      "Accept-Encoding": "",
      "Accept-Language": "zh-CN,zh;q=0.8",
      "User-Agent":
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:129.0) Gecko/20100101 Firefox/129.0",
    };
    if (header) headers = header;
    var url0 = url;
    if (data_GET) url0 += "?" + toQueryString(data_GET);
    if (enableFetch) {
      // 使用系统fetch（保持不变）
      deffetch.fetch({
        url: url0,
        data: data,
        headers: headers,
        method: method,
        responseType: responseType,
        success: (response) => success(response),
        fail: (data, code) => fail(data, code),
      });
    } else {
      // 使用同步器代理
      const requestId = this._generateUniqueId();
      // 保存请求处理器
      this.pendingRequests.set(requestId, { success, fail });
      // 设置请求超时（防止请求卡死）
      const timeoutId = setTimeout(() => {
        if (this.pendingRequests.has(requestId)) {
          fail("Request timeout", 408);
          this.pendingRequests.delete(requestId);
        }
      }, 30000); // 30秒超时
      // 发送请求
      // 添加默认Content-Type
      let data_text = "";
      if (method == "POST" && (!headers || !headers["Content-Type"])) {
        headers = {
          ...headers,
          "Content-Type": "application/x-www-form-urlencoded",
        };
        if (data) data_text = toQueryString(data);
      }
      var id = requestId;
      const message = JSON.stringify({
        msgtype: "FETCH",
        message: JSON.stringify({
          url: url0,
          responseType: responseType,
          method: method,
          data: data_text,
          header: headers,
        }),
      });

      setTimeout(() => {
        this.conn.send({
          data: {
            id,
            message,
          },
          fail: (err) => {
            clearTimeout(timeoutId);
            fail(`Send failed: ${err.data}`, err.code || -1);
            this.pendingRequests.delete(requestId);
          },
        });
      }, 300);
    }
  }
}

export default new fetch();
