#!/usr/bin/env node

"use strict";

const fs = require("fs");
const path = require("path");

const root = path.resolve(__dirname, "..");
const targets = [".work", "build"];
const buildLockFile = path.join(root, ".work", ".build.lock");

if (fs.existsSync(buildLockFile)) {
  let lock = null;
  try {
    lock = JSON.parse(fs.readFileSync(buildLockFile, "utf8"));
  } catch (error) {
    // 损坏的锁按陈旧锁处理。
  }
  const pid = Number(lock && lock.pid);
  if (Number.isInteger(pid) && pid > 0) {
    try {
      process.kill(pid, 0);
      throw new Error(`构建进程 ${pid} 正在运行，不能清理`);
    } catch (error) {
      if (error.code === "EPERM") {
        throw new Error(`构建进程 ${pid} 正在运行，不能清理`);
      }
      if (error.code !== "ESRCH") throw error;
    }
  }
}

for (const relative of targets) {
  const target = path.resolve(root, relative);
  if (!target.startsWith(root + path.sep)) {
    throw new Error(`拒绝清理仓库外路径：${target}`);
  }
  fs.rmSync(target, { recursive: true, force: true });
  console.log(`[vela-wear:clean] 已清理 ${relative}`);
}

console.log("[vela-wear:clean] dist 产物已保留");
