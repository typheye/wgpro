#!/usr/bin/env node

"use strict";

const fs = require("fs");
const path = require("path");

const root = path.resolve(__dirname, "..");
const targets = [".work", "build"];

for (const relative of targets) {
  const target = path.resolve(root, relative);
  if (!target.startsWith(root + path.sep)) {
    throw new Error(`拒绝清理仓库外路径：${target}`);
  }
  fs.rmSync(target, { recursive: true, force: true });
  console.log(`[vela-wear:clean] 已清理 ${relative}`);
}

console.log("[vela-wear:clean] dist 产物已保留");
