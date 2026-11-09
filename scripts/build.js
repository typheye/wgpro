#!/usr/bin/env node

"use strict";

const fs = require("fs");
const path = require("path");
const crypto = require("crypto");
const { spawnSync } = require("child_process");

const root = path.resolve(__dirname, "..");
const workRoot = path.join(root, ".work");
const distRoot = path.join(root, "dist");
const pkg = readJson(path.join(root, "package.json"));
const EXPECTED_NETWORK_MODE_BY_FAMILY = {
  watch: "proxy",
  "band-pro": "proxy",
  band: "bridge",
};
const VALID_NETWORK_MODES = new Set(["system", "proxy", "bridge"]);

const EXCLUDED_ROUTES = [
  "pages/ui/function/browser",
  "pages/ui/acts/settings/browser",
  "pages/ui/acts/filemanger/open/musicplayer/player",
  "pages/ui/acts/filemanger/open/musicplayer/volume",
  "pages/ui/acts/filemanger/open/musicplayer/list",
  "pages/ui/acts/browser/x1web",
  "pages/ui/acts/browser/setdefuri",
];

function readJson(file) {
  try {
    return JSON.parse(fs.readFileSync(file, "utf8"));
  } catch (error) {
    throw new Error(`无法读取 JSON ${file}: ${error.message}`);
  }
}

function writeJson(file, value) {
  fs.mkdirSync(path.dirname(file), { recursive: true });
  fs.writeFileSync(file, JSON.stringify(value, null, 2) + "\n");
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

function fileNamePart(value) {
  return value
    .trim()
    .toLowerCase()
    .replace(/\s+/g, "-")
    .replace(/[^a-z0-9-]/g, "")
    .replace(/-+/g, "-")
    .replace(/^-|-$/g, "");
}

function copyTree(from, to) {
  fs.cpSync(from, to, { recursive: true, force: true });
}

function safeRemove(target) {
  const resolved = path.resolve(target);
  if (!resolved.startsWith(workRoot + path.sep)) {
    throw new Error(`Refusing to remove path outside .work: ${resolved}`);
  }
  fs.rmSync(resolved, { recursive: true, force: true });
}

function findNodeModules() {
  const nodeModules = path.join(root, "node_modules");
  if (!fs.existsSync(path.join(nodeModules, ".bin", "aiot"))) {
    throw new Error("找不到 AIoT 构建工具，请先在 vela-wear 执行 npm ci");
  }
  return nodeModules;
}

function listProfiles() {
  return fs
    .readdirSync(path.join(root, "profiles"))
    .filter((name) => name.endsWith(".json"))
    .map((name) => name.slice(0, -".json".length))
    .sort();
}

function loadProfile(codename) {
  const file = path.join(root, "profiles", `${codename}.json`);
  if (!fs.existsSync(file)) {
    throw new Error(`未知设备代号：${codename}`);
  }
  const profile = readJson(file);
  if (profile.codename !== codename) {
    throw new Error(`profile codename 不一致：${profile.codename} != ${codename}`);
  }
  if (profile.deviceType !== "watch") {
    throw new Error("Vela 工程类型必须是 watch；手环设备的 deviceType 也是 watch");
  }
  if (!Number.isInteger(profile.designWidth) || profile.designWidth <= 0) {
    throw new Error(`${codename} 的 designWidth 无效`);
  }
  if (!profile.screen || profile.designWidth !== profile.screen.width) {
    throw new Error(
      `${codename} 的 designWidth 必须等于设备实际屏幕宽度：` +
        `${profile.designWidth} != ${profile.screen && profile.screen.width}`
    );
  }
  if (!["watch", "band", "band-pro"].includes(profile.family)) {
    throw new Error(`${codename} 的 family 无效`);
  }
  const features = profile.features;
  if (!features || typeof features !== "object") {
    throw new Error(`${codename} 缺少 features`);
  }
  if (!VALID_NETWORK_MODES.has(features.networkMode)) {
    throw new Error(`${codename} 的 networkMode 无效：${features.networkMode}`);
  }
  const expectedNetworkMode = EXPECTED_NETWORK_MODE_BY_FAMILY[profile.family];
  if (features.networkMode !== expectedNetworkMode) {
    throw new Error(
      `${codename} 网络策略错误：${profile.family} 必须使用 ` +
        `${expectedNetworkMode}，当前为 ${features.networkMode}`
    );
  }
  for (const overlay of profile.overlays || []) {
    if (!fs.existsSync(path.join(root, overlay))) {
      throw new Error(`${codename} 缺少 overlay：${overlay}`);
    }
  }
  return profile;
}

function generateManifest(stageDir, profile) {
  const sourceFile = path.join(root, "src", "manifest.json");
  const manifest = readJson(sourceFile);
  manifest.versionName = pkg.version;
  manifest.deviceTypeList = ["watch"];
  manifest.config = Object.assign({}, manifest.config, {
    designWidth: profile.designWidth,
    logLevel: manifest.config && manifest.config.logLevel ? manifest.config.logLevel : "log",
    deviceCode: profile.codename,
    targetProduct: profile.productName,
    targetScreen: profile.screen,
  });

  const pages = manifest.router && manifest.router.pages;
  if (!pages) {
    throw new Error("manifest.json 缺少 router.pages");
  }
  for (const route of EXCLUDED_ROUTES) {
    if ((profile.exclude || []).some((entry) => route.startsWith(entry.replace(/^src\//, "")))) {
      delete pages[route];
    }
  }
  if (profile.features && profile.features.browser === false) {
    delete pages["pages/ui/function/browser"];
    delete pages["pages/ui/acts/settings/browser"];
    delete pages["pages/ui/acts/browser/x1web"];
    delete pages["pages/ui/acts/browser/setdefuri"];
  }
  if (profile.features && profile.features.music === false) {
    delete pages["pages/ui/acts/filemanger/open/musicplayer/player"];
    delete pages["pages/ui/acts/filemanger/open/musicplayer/volume"];
    delete pages["pages/ui/acts/filemanger/open/musicplayer/list"];
  }

  writeJson(path.join(stageDir, "src", "manifest.json"), manifest);
  writeJson(path.join(stageDir, "src", "config-watch.json"), {
    deviceTypeList: ["watch"],
    config: {
      logLevel: manifest.config.logLevel,
      designWidth: profile.designWidth,
    },
  });
  return manifest;
}

function generateBuildProfile(stageDir, profile) {
  const target = path.join(stageDir, "src", "generated", "build-profile.js");
  const body = [
    "// Generated by scripts/build.js. Do not edit.",
    "export default " + JSON.stringify(profile, null, 2) + ";",
    "",
  ].join("\n");
  fs.mkdirSync(path.dirname(target), { recursive: true });
  fs.writeFileSync(target, body);
}

function removeExcluded(stageDir, profile) {
  for (const relative of profile.exclude || []) {
    const target = path.join(stageDir, relative);
    if (fs.existsSync(target)) {
      fs.rmSync(target, { recursive: true, force: true });
    }
  }
}

function validateNetworkMode(stageDir, profile) {
  const source = fs.readFileSync(path.join(stageDir, "src", "utils", "fetch.js"), "utf8");
  const match = source.match(/const\s+enableFetch\s*=\s*(true|false)\s*;/);
  if (!match) {
    throw new Error(`${profile.codename} 无法识别 fetch 网络模式`);
  }
  const mode = profile.features && profile.features.networkMode;
  const expected = mode === "system" || mode === "proxy";
  const actual = match[1] === "true";
  if (actual !== expected) {
    throw new Error(
      `${profile.codename} 网络模式不一致：profile=${mode}，` +
        `enableFetch=${actual}`
    );
  }
  if (mode === "proxy") {
    const appSource = fs.readFileSync(path.join(stageDir, "src", "app.ux"), "utf8");
    if (!appSource.includes("if (networkProxy) flag = \"proxy\";")) {
      throw new Error(`${profile.codename} 缺少 networkMode=proxy 的 URL 分支`);
    }
  }
}

function applyNetworkMode(stageDir, profile) {
  const mode = profile.features && profile.features.networkMode;
  if (!["system", "bridge", "proxy"].includes(mode)) {
    throw new Error(`${profile.codename} 的 networkMode 无效：${mode}`);
  }
  const target = path.join(stageDir, "src", "utils", "fetch.js");
  const source = fs.readFileSync(target, "utf8");
  const expected = mode === "system" || mode === "proxy" ? "true" : "false";
  const pattern = /const\s+enableFetch\s*=\s*(?:true|false)\s*;/;
  if (!pattern.test(source)) {
    throw new Error(`${profile.codename} 无法识别 enableFetch 配置`);
  }
  const next = source.replace(pattern, `const enableFetch = ${expected};`);
  fs.writeFileSync(target, next);
}

function validateOverlayResult(stageDir, profile) {
  if (profile.family !== "band-pro") {
    return;
  }
  const style = fs.readFileSync(
    path.join(stageDir, "src", "common", "style", "index.css"),
    "utf8"
  );
  for (const marker of ["136px", "Band Pro: 336x480"]) {
    if (!style.includes(marker)) {
      throw new Error(`${profile.codename} 未应用 Band Pro 尺寸覆盖：${marker}`);
    }
  }
}

function pruneBandAssets(stageDir, profile) {
  if (profile.assetSet !== "band") {
    return;
  }
  const allowed = new Set(readJson(path.join(root, "catalogs", "band-mipmap.json")));
  const mipmap = path.join(stageDir, "src", "common", "mipmap");
  if (!fs.existsSync(mipmap)) {
    return;
  }
  const walk = (dir) => {
    for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
      const absolute = path.join(dir, entry.name);
      if (entry.isDirectory()) {
        walk(absolute);
        continue;
      }
      const relative = path.relative(mipmap, absolute).replace(/\\/g, "/");
      if (!allowed.has(relative)) {
        fs.rmSync(absolute, { force: true });
      }
    }
    if (dir !== mipmap && fs.readdirSync(dir).length === 0) {
      fs.rmdirSync(dir);
    }
  };
  walk(mipmap);
}

function sha256File(file) {
  return crypto.createHash("sha256").update(fs.readFileSync(file)).digest("hex");
}

function runChecked(label, command, args, options) {
  const result = spawnSync(command, args, options);
  if (result.error) {
    throw new Error(`${label} 启动失败：${result.error.message}`);
  }
  if (result.status !== 0) {
    const reason = result.signal
      ? `信号 ${result.signal}`
      : `退出码 ${result.status}`;
    throw new Error(`${label} 失败，${reason}`);
  }
  return result;
}

function validateSigningMaterial() {
  for (const name of ["private.pem", "certificate.pem"]) {
    const file = path.join(root, "sign", "release", name);
    if (!fs.existsSync(file)) {
      throw new Error(`缺少签名文件：${path.relative(root, file)}`);
    }
  }
}

function verifyArtifact(file, profile) {
  const result = spawnSync("unzip", ["-p", file, "manifest.json"], {
    encoding: "utf8",
  });
  if (result.status !== 0) {
    throw new Error(`读取 RPK manifest 失败：${file}`);
  }
  const manifest = JSON.parse(result.stdout);
  const actualWidth = manifest.config && manifest.config.designWidth;
  if (Number(actualWidth) !== profile.designWidth) {
    throw new Error(
      `${profile.codename} designWidth 未写入产物：${actualWidth} != ${profile.designWidth}`
    );
  }
  if (
    !Array.isArray(manifest.deviceTypeList) ||
    manifest.deviceTypeList.length !== 1 ||
    manifest.deviceTypeList[0] !== "watch"
  ) {
    throw new Error(`${profile.codename} deviceTypeList 必须严格为 ["watch"]`);
  }
  if (manifest.config.deviceCode !== profile.codename) {
    throw new Error(`${profile.codename} 的 deviceCode 未写入产物`);
  }
  if (manifest.config.targetProduct !== profile.productName) {
    throw new Error(`${profile.codename} 的 targetProduct 未写入产物`);
  }
  if (
    JSON.stringify(manifest.config.targetScreen) !==
    JSON.stringify(profile.screen)
  ) {
    throw new Error(`${profile.codename} 的 targetScreen 未写入产物`);
  }
  const featureNames = new Set(
    (manifest.features || []).map((feature) => feature.name)
  );
  if (!featureNames.has("system.fetch")) {
    throw new Error(`${profile.codename} 缺少 system.fetch 能力声明`);
  }
  const routes = Object.keys((manifest.router && manifest.router.pages) || {});
  for (const relative of profile.exclude || []) {
    const route = relative.replace(/^src\//, "");
    if (routes.some((item) => item.startsWith(route))) {
      throw new Error(`${profile.codename} 仍包含应排除路由：${route}`);
    }
  }
  if (profile.features && profile.features.browser === false) {
    for (const route of [
      "pages/ui/function/browser",
      "pages/ui/acts/settings/browser",
      "pages/ui/acts/browser/x1web",
      "pages/ui/acts/browser/setdefuri",
    ]) {
      if (routes.includes(route)) {
        throw new Error(`${profile.codename} 仍包含浏览器路由：${route}`);
      }
    }
  }
  if (profile.features && profile.features.music === false) {
    for (const route of [
      "pages/ui/acts/filemanger/open/musicplayer/player",
      "pages/ui/acts/filemanger/open/musicplayer/volume",
      "pages/ui/acts/filemanger/open/musicplayer/list",
    ]) {
      if (routes.includes(route)) {
        throw new Error(`${profile.codename} 仍包含音乐路由：${route}`);
      }
    }
  }
  if (profile.assetSet === "band") {
    const listing = spawnSync("unzip", ["-l", file], { encoding: "utf8" });
    if (listing.status !== 0) {
      throw new Error(`无法检查 ${profile.codename} 的资源清单`);
    }
    for (const forbidden of ["common/mipmap/a40.png", "common/mipmap/mp/"]) {
      if (listing.stdout.includes(forbidden)) {
        throw new Error(`${profile.codename} 仍包含手表专属资源：${forbidden}`);
      }
    }
  }
  return manifest;
}

function build(codename, options) {
  const profile = loadProfile(codename);
  const stageDir = path.join(workRoot, codename);
  const nodeModules = options.nodeModules;

  safeRemove(stageDir);
  fs.mkdirSync(stageDir, { recursive: true });

  copyTree(path.join(root, "src"), path.join(stageDir, "src"));
  for (const overlay of profile.overlays || []) {
    const overlayDir = path.join(root, overlay);
    if (!fs.existsSync(overlayDir)) {
      throw new Error(`${profile.codename} 缺少 overlay：${overlay}`);
    }
    copyTree(overlayDir, stageDir);
  }
  removeExcluded(stageDir, profile);
  pruneBandAssets(stageDir, profile);
  applyNetworkMode(stageDir, profile);
  validateNetworkMode(stageDir, profile);
  validateOverlayResult(stageDir, profile);
  generateManifest(stageDir, profile);
  generateBuildProfile(stageDir, profile);

  fs.copyFileSync(path.join(root, "package.json"), path.join(stageDir, "package.json"));
  const signDir = path.join(root, "sign");
  validateSigningMaterial();
  copyTree(signDir, path.join(stageDir, "sign"));
  for (const name of ["jsconfig.json", ".eslintrc.json", ".eslintignore"]) {
    const source = path.join(root, name);
    if (fs.existsSync(source)) {
      fs.copyFileSync(source, path.join(stageDir, name));
    }
  }
  fs.symlinkSync(nodeModules, path.join(stageDir, "node_modules"), "dir");

  const tool = path.join(nodeModules, ".bin", "aiot");
  const command = options.buildOnly ? "build" : "release";
  const args = options.buildOnly
    ? [command]
    : [command, "--enable-jsc", "--enable-protobuf"];
  runChecked(`${codename} ${command}`, tool, args, {
    cwd: stageDir,
    stdio: "inherit",
    env: {
      ...process.env,
      NODE_OPTIONS:
        process.env.NODE_OPTIONS || "--max-old-space-size=1024",
    },
  });

  const outputDir = path.join(distRoot, codename);
  fs.rmSync(outputDir, { recursive: true, force: true });
  fs.mkdirSync(outputDir, { recursive: true });
  const releaseDir = path.join(stageDir, "dist");
  const rpks = fs.existsSync(releaseDir)
    ? fs.readdirSync(releaseDir).filter((name) => name.endsWith(".rpk"))
    : [];
  if (rpks.length !== 1) {
    throw new Error(`${codename} 预期产生 1 个 RPK，实际 ${rpks.length} 个`);
  }
  const deviceCode = codename.replace(/\s+/g, "-");
  const outputName =
    `${deviceCode}_${fileNamePart(profile.productName)}_` +
    `${pkg.version}_${options.timestamp}.rpk`;
  const outputFile = path.join(outputDir, outputName);
  fs.copyFileSync(path.join(releaseDir, rpks[0]), outputFile);
  const finalManifest = verifyArtifact(outputFile, profile);

  const report = {
    codename,
    productName: profile.productName,
    designWidth: profile.designWidth,
    deviceType: profile.deviceType,
    screen: profile.screen,
    networkMode: profile.features && profile.features.networkMode,
    enableFetch:
      profile.features && profile.features.networkMode !== "bridge",
    routedPages: Object.keys(finalManifest.router.pages).length,
    artifact: path.relative(root, outputFile),
    artifactTimestamp: options.timestamp,
    bytes: fs.statSync(outputFile).size,
    sha256: sha256File(outputFile),
  };
  writeJson(path.join(outputDir, "build.json"), report);
  console.log(
    `[vela-wear] ${codename} / ${profile.productName}: designWidth=${profile.designWidth}, ` +
      `${report.bytes} bytes -> ${report.artifact}`
  );
  return report;
}

function buildWithRetry(codename, options) {
  let lastError;
  for (let attempt = 1; attempt <= 2; attempt += 1) {
    try {
      return build(codename, options);
    } catch (error) {
      lastError = error;
      if (attempt === 1) {
        console.warn(
          `[vela-wear] ${codename} 首次构建失败，自动重试一次：${error.message}`
        );
      }
    }
  }
  throw lastError;
}

function main() {
  const args = process.argv.slice(2);
  const buildOnly = args.includes("--build");
  const checkOnly = args.includes("--check");
  const showHelp = args.includes("--help") || args.includes("-h");
  const allowedFlags = new Set(["--build", "--check", "--help", "-h"]);
  const unknownFlags = args.filter(
    (arg) => arg.startsWith("-") && !allowedFlags.has(arg)
  );
  if (unknownFlags.length) {
    throw new Error(`未知参数：${unknownFlags.join(", ")}`);
  }
  if (buildOnly && checkOnly) {
    throw new Error("--build 与 --check 不能同时使用");
  }
  if (showHelp) {
    console.log(
      [
        "用法：node scripts/build.js [all|设备代号...] [--build|--check]",
        "",
        "  all        构建全部设备（默认）",
        "  --build    开发构建，不启用 JSC/Protobuf 优化",
        "  --check    只校验 profile、目录和签名配置",
        "  --help     显示帮助",
      ].join("\n")
    );
    return;
  }
  const names = args.filter((arg) => !arg.startsWith("--"));
  const targets =
    names.length && !names.includes("all")
      ? [...new Set(names)]
      : listProfiles();
  const timestamp = process.env.VELA_BUILD_TIMESTAMP || shanghaiTimestamp(new Date());
  const profiles = targets.map(loadProfile);
  if (checkOnly) {
    findNodeModules();
    validateSigningMaterial();
    for (const profile of profiles) {
      console.log(
        `[vela-wear:check] ${profile.codename}: ` +
          `${profile.family}/${profile.features.networkMode}, ` +
          `designWidth=${profile.designWidth}`
      );
    }
    console.log(`[vela-wear:check] ${profiles.length} 个设备配置通过`);
    return;
  }
  const nodeModules = findNodeModules();
  fs.mkdirSync(workRoot, { recursive: true });
  const reports = targets.map((name) =>
    buildWithRetry(name, { buildOnly, timestamp, nodeModules })
  );
  const indexPath = path.join(distRoot, "build-index.json");
  let previousBuilds = [];
  if (fs.existsSync(indexPath)) {
    try {
      previousBuilds = readJson(indexPath).builds || [];
    } catch (error) {
      console.warn(`[vela-wear] 忽略损坏的 build-index.json：${error.message}`);
    }
  }
  const buildsByCode = new Map(
    previousBuilds.map((report) => [report.codename, report])
  );
  for (const report of reports) buildsByCode.set(report.codename, report);
  writeJson(indexPath, {
    generatedAt: new Date().toISOString(),
    lastBuildTimestamp: timestamp,
    version: pkg.version,
    builds: [...buildsByCode.values()].sort((a, b) =>
      a.codename.localeCompare(b.codename)
    ),
  });
}

try {
  main();
} catch (error) {
  console.error(`[vela-wear] ${error.message}`);
  process.exit(1);
}
