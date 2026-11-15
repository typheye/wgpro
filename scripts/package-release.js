#!/usr/bin/env node

"use strict";

const fs = require("fs");
const os = require("os");
const path = require("path");
const { spawnSync } = require("child_process");

const root = path.resolve(__dirname, "..");
const packageFile = path.join(root, "package.json");
const buildIndexFile = path.join(root, "dist", "build-index.json");
const screenshotRoot = path.join(os.homedir(), "Downloads", "vela-wear-screenshots");
const siteSource = path.join(
  root,
  "..",
  "_guide",
  "web-community",
  "wgpro.typheye.cn",
  "down",
  "index.php"
);
const pkg = readJson(packageFile);
const index = readJson(buildIndexFile);
const buildTimestamp =
  process.env.VELA_BUILD_TIMESTAMP || index.lastBuildTimestamp;
const outputRoot =
  process.argv[2] ||
  path.join(os.homedir(), "Downloads", `vela-wear-output-${buildTimestamp}`);

const commonChangelog = [
  "统一发布 Vela 3.26.11，覆盖 Watch、Band 与 Band Pro 共 17 款设备。",
  "修复 Xiaomi Watch S5、Xiaomi Smart Band 11、Xiaomi Smart Band 10 Pro 和 REDMI Watch 6 因启用 Protobuf 编译导致设备重启的问题。",
  "下载页 Home 与键盘预览图改用 WebP，17 张图片总体积减少约 80%，降低加载等待。",
  "每款设备使用独立设备代号、真实屏幕宽度和设备专属 designWidth 编译。",
  "输入法按实体屏幕宽度重新布局，避免圆屏、方屏和胶囊屏显示残缺。",
  "Watch 与 Band Pro 使用设备代理独立联网，普通 Band 使用手机客户端桥接。",
  "Band Pro 以 Watch 源码为基线，保留主页双列布局，并裁剪浏览器和音乐播放器。",
  "同步更新设备图标资源、皮肤适配和下载页独立设备发布信息。",
];

const familyNotes = {
  watch: "完整 Watch 功能包，支持浏览器、音乐播放器和设备代理独立联网。",
  band: "胶囊屏精简包，已裁剪浏览器和音乐播放器，通过手机客户端桥接联网。",
  "band-pro": "方屏 Watch 基线精简包，主页双列显示，已裁剪浏览器和音乐播放器，支持代理独立联网。",
};

function readJson(file) {
  return JSON.parse(fs.readFileSync(file, "utf8"));
}

function writeJson(file, value) {
  fs.mkdirSync(path.dirname(file), { recursive: true });
  fs.writeFileSync(file, JSON.stringify(value, null, 2) + "\n");
}

function formatBytes(bytes) {
  if (bytes >= 1024 * 1024) return `${(bytes / 1024 / 1024).toFixed(2)} MiB`;
  return `${(bytes / 1024).toFixed(1)} KiB`;
}

function shanghaiTimestamp(date) {
  const parts = new Intl.DateTimeFormat("en-CA", {
    timeZone: "Asia/Shanghai",
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
    second: "2-digit",
    hour12: false,
  })
    .formatToParts(date)
    .reduce((result, part) => {
      result[part.type] = part.value;
      return result;
    }, {});
  return (
    parts.year + parts.month + parts.day + parts.hour + parts.minute + parts.second
  );
}

function familyOf(codename) {
  const profile = readJson(path.join(root, "profiles", `${codename}.json`));
  return profile.family;
}

function validateBuild(report) {
  if (report.versionName !== pkg.version) {
    throw new Error(
      `${report.codename} 版本名称不是 ${pkg.version}：${report.versionName}`
    );
  }
  if (report.artifactTimestamp !== buildTimestamp) {
    throw new Error(
      `${report.codename} 构建时间戳不是本次发布：${report.artifactTimestamp}`
    );
  }
  const artifact = path.resolve(root, report.artifact);
  if (!artifact.startsWith(root + path.sep) || !fs.existsSync(artifact)) {
    throw new Error(`${report.codename} 构建产物不存在：${report.artifact}`);
  }
  if (fs.statSync(artifact).size !== report.bytes) {
    throw new Error(`${report.codename} 构建产物大小与索引不一致`);
  }
  return artifact;
}

if (!buildTimestamp || !/^\d{14}$/.test(buildTimestamp)) {
  throw new Error(`无效的构建时间戳：${buildTimestamp}`);
}
if (!Array.isArray(index.builds) || index.builds.length !== 17) {
  throw new Error(`正式版必须包含 17 款设备，当前为 ${index.builds?.length || 0}`);
}
if (fs.existsSync(outputRoot)) {
  throw new Error(`输出目录已存在，拒绝覆盖：${outputRoot}`);
}

const builds = [...index.builds].sort((a, b) =>
  a.codename.localeCompare(b.codename)
);
const expectedCodes = fs
  .readdirSync(path.join(root, "profiles"))
  .filter((name) => name.endsWith(".json"))
  .map((name) => path.basename(name, ".json"))
  .sort();
const actualCodes = builds.map((build) => build.codename).sort();
if (JSON.stringify(actualCodes) !== JSON.stringify(expectedCodes)) {
  throw new Error("build-index.json 与 profiles/ 的设备集合不一致");
}

const devices = builds.map((report) => {
  const artifact = validateBuild(report);
  const filename = path.basename(artifact);
  const family = familyOf(report.codename);
  return {
    codename: report.codename,
    productName: report.productName,
    family,
    screen: report.screen,
    designWidth: report.designWidth,
    networkMode: report.networkMode,
    enableFetch: report.enableFetch,
    protobufEnabled: report.protobufEnabled,
    routedPages: report.routedPages,
    filename,
    bytes: report.bytes,
    size: formatBytes(report.bytes),
    sha256: report.sha256,
    note: familyNotes[family],
    artifact,
  };
});

fs.mkdirSync(outputRoot, { recursive: false });
const siteRoot = path.join(outputRoot, "site", "down");
const siteDataRoot = path.join(siteRoot, "src", "data");
const releaseDevices = [];

for (const device of devices) {
  const topLevel = path.join(outputRoot, device.filename);
  fs.copyFileSync(device.artifact, topLevel);
  const serverFile = path.join(
    siteRoot,
    "src",
    "rpk",
    device.codename,
    device.filename
  );
  fs.mkdirSync(path.dirname(serverFile), { recursive: true });
  fs.copyFileSync(device.artifact, serverFile);

  const screenshot = path.join(screenshotRoot, device.codename, "combined.png");
  let screenshotName = null;
  if (fs.existsSync(screenshot)) {
    screenshotName = `${device.codename}.webp`;
    const screenshotTarget = path.join(
      siteRoot,
      "src",
      "screenshots",
      screenshotName
    );
    fs.mkdirSync(path.dirname(screenshotTarget), { recursive: true });
    const optimized = spawnSync(
      "convert",
      [
        screenshot,
        "-strip",
        "-define",
        "webp:alpha-quality=90",
        "-define",
        "webp:method=6",
        "-quality",
        "80",
        screenshotTarget,
      ],
      { encoding: "utf8" }
    );
    if (optimized.status !== 0) {
      throw new Error(
        `${device.codename} 截图 WebP 转换失败：` +
          (optimized.stderr || optimized.error?.message || "unknown error")
      );
    }
  }

  releaseDevices.push({
    codename: device.codename,
    productName: device.productName,
    family: device.family,
    screen: device.screen,
    designWidth: device.designWidth,
    networkMode: device.networkMode,
    enableFetch: device.enableFetch,
    protobufEnabled: device.protobufEnabled,
    routedPages: device.routedPages,
    filename: device.filename,
    bytes: device.bytes,
    size: device.size,
    sha256: device.sha256,
    screenshot: screenshotName,
    note: device.note,
  });
}

const versionCode = devices[0] && devices[0].versionCode;
const release = {
  schema: "wgpro.vela.release/1",
  versionName: pkg.version,
  versionCode:
    versionCode ||
    readJson(path.join(root, "src", "manifest.json")).versionCode,
  buildTimestamp,
  publishTimestamp: shanghaiTimestamp(new Date()),
  generatedAt: new Date().toISOString(),
  changelog: commonChangelog,
  installNotes:
    "请选择与手表或手环型号完全一致的 RPK。普通手环需要保持腕管Pro手机客户端连接；Watch 和 Band Pro 可直接通过设备代理联网。",
  devices: releaseDevices,
};

writeJson(path.join(outputRoot, "release.json"), release);
writeJson(path.join(siteDataRoot, "release.json"), release);
fs.copyFileSync(buildIndexFile, path.join(outputRoot, "build-index.json"));
if (fs.existsSync(siteSource)) {
  fs.copyFileSync(siteSource, path.join(siteRoot, "index.php"));
} else {
  throw new Error(`找不到下载页源码：${siteSource}`);
}

const checksums = devices
  .map((device) => {
    const copied = path.join(outputRoot, device.filename);
    const actual = require("crypto")
      .createHash("sha256")
      .update(fs.readFileSync(copied))
      .digest("hex");
    if (actual !== device.sha256) {
      throw new Error(`${device.codename} 复制后的 SHA256 不一致`);
    }
    return `${actual}  ${device.filename}`;
  })
  .join("\n");
fs.writeFileSync(path.join(outputRoot, "SHA256SUMS"), checksums + "\n");

const releaseNotes = [
  `# 腕管Pro Vela v${pkg.version}`,
  "",
  `- 版本名称：${pkg.version}`,
  `- 版本号：${release.versionCode}`,
  `- 构建时间：${buildTimestamp}`,
  `- 设备数：${release.devices.length}`,
  "",
  "## 更新日志",
  "",
  ...commonChangelog.map((line) => `- ${line}`),
  "",
  "## 设备包",
  "",
  "| 设备代号 | 设备 | designWidth | 屏幕 | 网络 | 文件 |",
  "|---|---|---:|---|---|---|",
  ...release.devices.map(
    (device) =>
      `| ${device.codename} | ${device.productName} | ${device.designWidth} | ` +
      `${device.screen.width}×${device.screen.height} ${device.screen.shape} | ` +
      `${device.networkMode} | ${device.filename} |`
  ),
  "",
].join("\n");
fs.writeFileSync(path.join(outputRoot, "RELEASE-NOTES.md"), releaseNotes);

console.log(`[vela-wear:package] ${release.devices.length} 个设备包`);
console.log(`[vela-wear:package] ${outputRoot}`);
