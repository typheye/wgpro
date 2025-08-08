// file.js
import file from "@system.file";

const INDEX_FILE = "internal://files/file.dat";
const DEFAULT_PATHS = [
  "internal://cache/",
  "internal://mass/",
  "internal://files/",
  "internal://files/mnt/",
  "internal://files/mnt/Documents/",
  "internal://files/mnt/Download/",
  "internal://files/mnt/Pictures/",
  "internal://files/mnt/Movies/",
  "internal://files/mnt/Music/",
  "internal://files/wgchat/",
];

// 系统默认存在的目录（不自动创建）
const SYSTEM_PATHS = [
  "internal://cache/",
  "internal://mass/",
  "internal://files/",
];

// 受保护的索引路径（不允许删除或重命名）
const PROTECTED_PATHS = [
  ...DEFAULT_PATHS,
  "internal://files/mnt/",
  "internal://files/mnt/Documents/",
  "internal://files/mnt/Download/",
  "internal://files/mnt/Pictures/",
  "internal://files/mnt/Movies/",
  "internal://files/mnt/Music/",
  "internal://files/wgchat/",
];

class FileManager {
  constructor() {
    this.pathIndex = new Set();
    this.ready = false;
  }

  init(success, fail) {
    file.access({
      uri: INDEX_FILE,
      success: () => {
        file.readText({
          uri: INDEX_FILE,
          success: (res) => {
            try {
              const data = JSON.parse(res.text);
              this.pathIndex = new Set(data.path || []);
              this.ready = true;

              // 初始化时自动创建非系统目录
              this.createNonSystemPaths(success, fail);
            } catch (e) {
              this.resetIndex(success, fail);
            }
          },
          fail: () => this.resetIndex(success, fail),
        });
      },
      fail: () => this.resetIndex(success, fail),
    });
  }

  // 创建非系统默认路径
  createNonSystemPaths(success, fail) {
    const pathsToCreate = [...this.pathIndex].filter(
      (path) => !SYSTEM_PATHS.includes(path)
    );

    if (pathsToCreate.length === 0) {
      success && success();
      return;
    }

    let createdCount = 0;
    const total = pathsToCreate.length;

    pathsToCreate.forEach((uri) => {
      file.mkdir({
        uri,
        recursive: true,
        success: () => {
          if (++createdCount === total) {
            success && success();
          }
        },
        fail: (err) => {
          //console.error(`自动创建目录失败: ${uri}`, err);
          if (++createdCount === total) {
            success && success();
          }
        },
      });
    });
  }

  resetIndex(success, fail) {
    this.pathIndex = new Set(DEFAULT_PATHS);
    this.saveIndex(() => {
      // 重置后自动创建非系统目录
      this.createNonSystemPaths(success, fail);
    }, fail);
  }

  saveIndex(success, fail) {
    const data = JSON.stringify({ path: [...this.pathIndex] });
    file.writeText({
      uri: INDEX_FILE,
      text: data,
      success: () => success && success(),
      fail: (err, code) => fail && fail(`保存索引失败: ${code}`),
    });
  }

  // 检查路径是否受保护
  isProtectedPath(uri) {
    const normalizedUri = uri.endsWith("/") ? uri : uri + "/";
    return PROTECTED_PATHS.some(path => normalizedUri.startsWith(path));
  }

  // 添加文件夹路径到索引
  addPath(uri, success, fail) {
    if (!uri.endsWith("/")) uri += "/";

    this.init(() => {
      // 添加路径时自动创建非系统目录
      if (!SYSTEM_PATHS.includes(uri)) {
        file.mkdir({
          uri,
          recursive: true,
          success: () => {
            this.pathIndex.add(uri);
            this.saveIndex(success, fail);
          },
          fail: (err) => fail && fail(`创建目录失败: ${err}`),
        });
      } else {
        this.pathIndex.add(uri);
        this.saveIndex(success, fail);
      }
    }, fail);
  }

  // 从索引中移除文件夹路径
  removePath(uri, success, fail) {
    if (!uri.endsWith("/")) uri += "/";

    // 检查是否是受保护路径
    if (this.isProtectedPath(uri)) {
      fail && fail(`不能删除受保护路径: ${uri}`);
      return;
    }

    this.init(() => {
      // 删除所有子路径
      const toRemove = [];
      for (const path of this.pathIndex) {
        if (path.startsWith(uri)) {
          // 检查子路径是否受保护
          if (this.isProtectedPath(path)) {
            fail && fail(`不能删除受保护路径: ${path}`);
            return;
          }
          toRemove.push(path);
        }
      }

      toRemove.forEach((path) => this.pathIndex.delete(path));
      this.saveIndex(success, fail);
    }, fail);
  }

  // 移动文件夹路径
  movePath(oldUri, newUri, success, fail) {
    if (!oldUri.endsWith("/")) oldUri += "/";
    if (!newUri.endsWith("/")) newUri += "/";

    // 检查源路径是否受保护
    if (this.isProtectedPath(oldUri)) {
      fail && fail(`不能移动受保护路径: ${oldUri}`);
      return;
    }

    this.init(() => {
      const toUpdate = [];
      for (const path of this.pathIndex) {
        if (path.startsWith(oldUri)) {
          // 检查子路径是否受保护
          if (this.isProtectedPath(path)) {
            fail && fail(`不能移动受保护路径: ${path}`);
            return;
          }
          toUpdate.push(path);
        }
      }

      toUpdate.forEach((path) => {
        this.pathIndex.delete(path);
        this.pathIndex.add(path.replace(oldUri, newUri));
      });

      // 移动后创建新目录（如果是非系统路径）
      if (!SYSTEM_PATHS.includes(newUri)) {
        file.mkdir({
          uri: newUri,
          recursive: true,
          success: () => this.saveIndex(success, fail),
          fail: (err) => fail && fail(`创建目录失败: ${err}`),
        });
      } else {
        this.saveIndex(success, fail);
      }
    }, fail);
  }

  // 列出指定目录下的文件夹
  listdir(options) {
    const { uri, success, fail } = options;
    let dirUri = uri;

    // 确保目录路径以斜杠结尾（用于后续路径匹配）
    if (!dirUri.endsWith("/")) dirUri += "/";

    this.init(() => {
      const subDirs = [];
      for (const path of this.pathIndex) {
        if (path.startsWith(dirUri) && path !== dirUri) {
          const remaining = path.slice(dirUri.length);
          const nextSlash = remaining.indexOf("/");

          // 关键修改：检测到是子文件夹时，移除URI末尾的斜杠
          if (nextSlash === remaining.length - 1) {
            // 移除末尾斜杠（确保路径不以'/'结尾）
            const cleanPath = path.endsWith("/") ? path.slice(0, -1) : path;

            subDirs.push({
              uri: cleanPath, // 使用处理后的路径
              lastModifiedTime: Date.now(),
              length: 0,
              isDir: true,
            });
          }
        }
      }
      success && success({ dirList: subDirs });
    }, fail);
  }

  // 创建文件夹（增强版）
  mkdir(options) {
    const { uri, recursive = true, success, fail } = options;

    // 创建实际目录
    file.mkdir({
      uri,
      recursive,
      success: () => {
        // 更新索引
        this.addPath(
          uri,
          () => success && success(),
          (err) => fail && fail(err)
        );
      },
      fail,
    });
  }

  // 删除文件夹（增强版）- 增加保护路径检查
  rmdir(options) {
    const { uri, recursive = true, success, fail } = options;
    
    // 检查是否是受保护路径
    if (this.isProtectedPath(uri)) {
      fail && fail(`不能删除受保护路径: ${uri}`);
      return;
    }

    // 删除实际目录
    file.rmdir({
      uri,
      recursive,
      success: () => {
        // 更新索引
        this.removePath(
          uri,
          () => success && success(),
          (err) => fail && fail(err)
        );
      },
      fail,
    });
  }

  // 移动文件/文件夹（增强版）- 增加保护路径检查
  move(options) {
    const { srcUri, dstUri, success, fail } = options;
    
    // 如果是目录，检查源路径是否受保护
    if (srcUri.endsWith("/") && this.isProtectedPath(srcUri)) {
      fail && fail(`不能移动受保护路径: ${srcUri}`);
      return;
    }

    // 移动文件/目录
    file.move({
      srcUri,
      dstUri,
      success: () => {
        // 如果是目录，更新索引
        if (srcUri.endsWith("/")) {
          this.movePath(
            srcUri,
            dstUri,
            () => success && success({ uri: dstUri }),
            (err) => fail && fail(err)
          );
        } else {
          success && success({ uri: dstUri });
        }
      },
      fail,
    });
  }

  // 复制文件/文件夹（增强版）
  copy(options) {
    const { srcUri, dstUri, success, fail } = options;

    // 复制文件/目录
    file.copy({
      srcUri,
      dstUri,
      success: () => {
        // 如果是目录，更新索引
        if (srcUri.endsWith("/")) {
          this.addPath(
            dstUri,
            () => success && success({ uri: dstUri }),
            (err) => fail && fail(err)
          );
        } else {
          success && success({ uri: dstUri });
        }
      },
      fail,
    });
  }
}

// 创建单例实例
const fileManager = new FileManager();

// 导出增强版API
export default {
  // 原生接口
  get: file.get,
  delete: file.delete,
  writeText: file.writeText,
  writeArrayBuffer: file.writeArrayBuffer,
  readText: file.readText,
  readArrayBuffer: file.readArrayBuffer,
  access: file.access,
  list: file.list, // 原生list只返回文件

  // 增强接口
  move: (options) => fileManager.move(options),
  copy: (options) => fileManager.copy(options),
  mkdir: (options) => fileManager.mkdir(options),
  rmdir: (options) => fileManager.rmdir(options),

  // 新增文件夹列表接口
  listdir: (options) => fileManager.listdir(options),
};