#!/usr/bin/env node

"use strict";

const fs = require("fs");
const os = require("os");
const path = require("path");
const { spawnSync } = require("child_process");

const root = path.resolve(__dirname, "..");
const pkg = readJson(path.join(root, "package.json"));
const emulatorModule =
  "/home/typheye/.aiot-ide/extensions/vela.aiot-emulator-1.7.22/dist/emulator/index.js";
const emu = require(emulatorModule);
const aiot = path.join(root, "node_modules", ".bin", "aiot");
const buildIndex = readJson(path.join(root, "dist", "build-index.json"));
const outputRoot = path.join(
  os.homedir(),
  "Downloads",
  "vela-wear-screenshots"
);
const workRoot = path.join(root, ".work", "screenshot-runs");
const keyboardRoot = path.join(workRoot, "keyboards");
const imageType = "vela-pre-4.0";

const SKIN_BY_DEVICE = {
  n62: "xiaomi_watch",
  n62lte: "xiaomi_watch",
  n62s: "xiaomi_s4",
  o62: "xiaomi_s4",
  o62lte: "xiaomi_s4",
  o63: "xiaomi_s4_41",
  p62: null,
  o65: "redmi_watch",
  o65m: "redmi_watch",
  p65: "redmi_watch",
  n66cn: "xiaomi_band",
  n66nfc: "xiaomi_band",
  n67cn: "xiaomi_band_pro",
  p67cn: "xiaomi_band_pro",
  o66cn: "xiaomi_band_10",
  o66nfc: "xiaomi_band_10",
  q66cn: "xiaomi_band_10",
};

function readJson(file) {
  return JSON.parse(fs.readFileSync(file, "utf8"));
}

function run(label, command, args, options = {}) {
  const result = spawnSync(command, args, {
    cwd: options.cwd || root,
    encoding: options.encoding || "utf8",
    stdio: options.stdio || "inherit",
    env: { ...process.env, ...(options.env || {}) },
  });
  if (result.error) throw new Error(`${label}: ${result.error.message}`);
  if (result.status !== 0) {
    throw new Error(`${label} failed with status ${result.status}`);
  }
  return result;
}

function runQuiet(label, command, args, options = {}) {
  return run(label, command, args, { ...options, stdio: "pipe" });
}

function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

function keyboardRpkPath(codename) {
  return path.join(keyboardRoot, `${codename}.rpk`);
}

function newestMtime(target) {
  const stat = fs.statSync(target);
  if (!stat.isDirectory()) return stat.mtimeMs;
  let newest = stat.mtimeMs;
  for (const entry of fs.readdirSync(target, { withFileTypes: true })) {
    newest = Math.max(newest, newestMtime(path.join(target, entry.name)));
  }
  return newest;
}

function ensureWorkStage(codename) {
  const stage = path.join(root, ".work", codename);
  if (!fs.existsSync(path.join(stage, "src", "pages", "index", "index.ux"))) {
    run(`prepare ${codename}`, process.execPath, [
      path.join(root, "scripts", "build.js"),
      codename,
    ]);
  }
  return stage;
}

function buildKeyboardPackage(codename) {
  const target = keyboardRpkPath(codename);
  const stage = ensureWorkStage(codename);
  const sourceMtime = newestMtime(path.join(stage, "src"));
  if (
    fs.existsSync(target) &&
    fs.statSync(target).mtimeMs >= sourceMtime
  ) {
    return target;
  }
  const indexPath = path.join(stage, "src", "pages", "index", "index.ux");
  let source = fs.readFileSync(indexPath, "utf8");
  const inject =
    'this.$app.$def.goReplacePageWithParams("pages/ui/popup/input", ' +
    '{ textValues: JSON.stringify(["腕管Pro"]), textIndex: 0, ' +
    'reqPage: "pages/ui/home" });';
  if (source.includes('goReplacePage("pages/ui/home")')) {
    source = source.replace(
      'this.$app.$def.goReplacePage("pages/ui/home");',
      inject
    );
    fs.writeFileSync(indexPath, source);
  } else if (!source.includes("pages/ui/popup/input")) {
    throw new Error(`${codename} 首页启动逻辑未找到可注入点`);
  }

  run(
    `build keyboard ${codename}`,
    aiot,
    ["release", "--enable-jsc", "--enable-protobuf"],
    { cwd: stage }
  );
  const dist = path.join(stage, "dist");
  const rpks = fs
    .readdirSync(dist)
    .filter((name) => name.endsWith(`.release.${pkg.version}.rpk`))
    .map((name) => path.join(dist, name))
    .sort((a, b) => fs.statSync(b).mtimeMs - fs.statSync(a).mtimeMs);
  if (!rpks.length) throw new Error(`${codename} 未生成输入法截图 RPK`);
  fs.mkdirSync(keyboardRoot, { recursive: true });
  fs.copyFileSync(rpks[0], target);
  return target;
}

function parseSkin(name) {
  const dir = path.join(emu.defaultSkinHome, "builtin", name);
  const layout = fs.readFileSync(path.join(dir, "layout"), "utf8");
  const display = layout.match(
    /display\s*\{([\s\S]*?)\n\s*\}/
  )?.[1];
  const portrait = layout.match(
    /portrait\s*\{([\s\S]*?)\n\s*\}\s*\n\s*\}/
  )?.[1];
  const part2 = layout.match(
    /part2\s*\{([\s\S]*?)\n\s*\}/
  )?.[1];
  if (!display || !portrait || !part2) {
    throw new Error(`无法解析皮肤 ${name}`);
  }
  const number = (source, key) =>
    Number(source.match(new RegExp(`\\b${key}\\s+(-?\\d+)`))?.[1]);
  return {
    name,
    dir,
    canvasWidth: Number(layout.match(/layouts\s*\{[\s\S]*?width\s+(\d+)/)?.[1]),
    canvasHeight: Number(layout.match(/layouts\s*\{[\s\S]*?height\s+(\d+)/)?.[1]),
    x: number(part2, "x"),
    y: number(part2, "y"),
    width: number(display, "width"),
    height: number(display, "height"),
    radius: number(display, "corner_radius") || 0,
    background: path.join(
      dir,
      layout.match(/background\s*\{[\s\S]*?image\s+(\S+)/)?.[1]
    ),
    foreground: (() => {
      const value = layout.match(/foreground\s*\{[\s\S]*?mask\s+(\S+)/)?.[1];
      return value ? path.join(dir, value) : null;
    })(),
  };
}

function scaledCircle480Skin() {
  const source = parseSkin("xiaomi_s4");
  const scale = 480 / source.width;
  const dir = path.join(workRoot, "skins", "circle480");
  fs.mkdirSync(dir, { recursive: true });
  const background = path.join(dir, "background.png");
  const foreground = path.join(dir, "foreground.png");
  if (!fs.existsSync(background)) {
    runQuiet("scale 480 skin background", "convert", [
      source.background,
      "-resize",
      `${(scale * 100).toFixed(6)}%`,
      background,
    ]);
    runQuiet("scale 480 skin foreground", "convert", [
      source.foreground,
      "-resize",
      `${(scale * 100).toFixed(6)}%`,
      foreground,
    ]);
  }
  return {
    ...source,
    name: "xiaomi_s4_480",
    width: 480,
    height: 480,
    radius: 240,
    x: Math.round(source.x * scale),
    y: Math.round(source.y * scale),
    canvasWidth: Math.round(source.canvasWidth * scale),
    canvasHeight: Math.round(source.canvasHeight * scale),
    background,
    foreground,
  };
}

function imageInfo(file) {
  const result = runQuiet("identify", "identify", [
    "-format",
    "%w %h",
    file,
  ]);
  const [width, height] = result.stdout.trim().split(/\s+/).map(Number);
  return { width, height };
}

function findCheckButton(file, screenWidth, screenHeight) {
  const result = runQuiet("detect init buttons", "convert", [
    file,
    "-alpha",
    "off",
    "-fuzz",
    "12%",
    "-fill",
    "white",
    "-opaque",
    "rgb(60,60,60)",
    "-fill",
    "black",
    "+opaque",
    "white",
    "-define",
    "connected-components:verbose=true",
    "-connected-components",
    "8",
    "null:",
  ]);
  const candidates = [];
  for (const line of result.stdout.split(/\r?\n/)) {
    const match = line.match(
      /^\s*\d+:\s+(\d+)x(\d+)\+(-?\d+)\+(-?\d+)\s+([\d.]+),([\d.]+)\s+(\d+)/
    );
    if (!match) continue;
    const [, width, height, , , x, y, area] = match.map(Number);
    if (
      width >= 50 &&
      height >= 50 &&
      Math.abs(width - height) <= 8 &&
      area >= 1000 &&
      y > screenHeight * 0.35
    ) {
      candidates.push({ x, y, area });
    }
  }
  if (!candidates.length) return null;
  candidates.sort((a, b) => b.x - a.x || b.area - a.area);
  return candidates[0];
}

function click(agent, point) {
  agent.sendMouse({ x: Math.round(point.x), y: Math.round(point.y), buttons: 1 });
  return sleep(120).then(() => {
    agent.sendMouse({
      x: Math.round(point.x),
      y: Math.round(point.y),
      buttons: 0,
    });
  });
}

function createVvd(manager, codename, profile, skin) {
  const name = `render_${codename}`;
  try {
    manager.deleteVvd(name);
  } catch (error) {
    // 临时 VVD 不存在时无需处理。
  }
  const common = {
    name,
    arch: emu.IVvdArchType.arm,
    imageDir: path.join(emu.defaultSDKHome, "system-images", imageType),
    imageType,
    shape: profile.screen.shape,
    flavor: profile.family === "watch" ? "watch" : "band",
    density: profile.screen.density,
    marketName: profile.productName,
  };
  if (skin) {
    manager.createVvd({
      ...common,
      skin: skin.name,
      ["skin.path"]: path.join(
        emu.defaultSkinHome,
        "builtin",
        skin.name === "xiaomi_s4_480" ? "xiaomi_s4" : skin.name
      ),
    });
  } else {
    manager.createVvd({
      ...common,
      width: String(profile.designWidth),
      height: String(profile.screen.height),
      customLcdRadius:
        profile.screen.shape === "circle"
          ? Math.round(profile.designWidth / 2)
          : undefined,
    });
  }
  return name;
}

function compositeSkin(raw, output, skin) {
  const dir = path.dirname(output);
  const mask = path.join(dir, ".screen-mask.png");
  const masked = path.join(dir, ".screen-masked.png");
  const info = imageInfo(raw);
  const draw = [
    "roundrectangle",
    "0,0",
    `${skin.width - 1},${skin.height - 1}`,
    `${skin.radius},${skin.radius}`,
  ].join(" ");
  runQuiet("create screen mask", "convert", [
    "-size",
    `${skin.width}x${skin.height}`,
    "xc:none",
    "-fill",
    "white",
    "-draw",
    draw,
    mask,
  ]);
  runQuiet("mask screen", "convert", [
    raw,
    "-resize",
    `${skin.width}x${skin.height}!`,
    mask,
    "-alpha",
    "off",
    "-compose",
    "CopyOpacity",
    "-composite",
    masked,
  ]);
  const args = [
    skin.background,
    masked,
    "-geometry",
    `+${skin.x}+${skin.y}`,
    "-compose",
    "over",
    "-composite",
  ];
  if (skin.foreground && fs.existsSync(skin.foreground)) {
    args.push(
      skin.foreground,
      "-geometry",
      `+${skin.x}+${skin.y}`,
      "-compose",
      "over",
      "-composite"
    );
  }
  args.push(output);
  runQuiet("composite skin", "convert", args);
  fs.rmSync(mask, { force: true });
  fs.rmSync(masked, { force: true });
  if (info.width !== skin.width || info.height !== skin.height) {
    console.warn(
      `[screenshots] resized ${raw} from ${info.width}x${info.height} ` +
        `to ${skin.width}x${skin.height} for skin`
    );
  }
}

function skinForDevice(codename) {
  if (codename === "p62") return scaledCircle480Skin();
  const skinName = SKIN_BY_DEVICE[codename];
  return skinName ? parseSkin(skinName) : null;
}

function combinedScreenshot(home, keyboard, output) {
  runQuiet("combine screenshots", "convert", [
    home,
    keyboard,
    "+append",
    output,
  ]);
}

function writeGallery(results) {
  const cards = results
    .map(
      (item) => `
      <article>
        <h2>${item.codename} · ${item.productName}</h2>
        <p>designWidth ${item.designWidth} · ${item.shape} · ${item.networkMode}</p>
        <div class="pair">
          <figure><img src="${item.codename}/home.png"><figcaption>Home</figcaption></figure>
          <figure><img src="${item.codename}/keyboard.png"><figcaption>Keyboard</figcaption></figure>
        </div>
      </article>`
    )
    .join("\n");
  const html = `<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>腕管Pro Vela 设备截图</title>
<style>
body{margin:0;padding:32px;background:#111318;color:#eef2f7;font:15px/1.5 system-ui,sans-serif}
h1{margin:0 0 24px}article{margin:0 0 42px;padding:20px;background:#1b1e25;border:1px solid #303540;border-radius:18px}
h2{margin:0 0 4px;font-size:20px}p{margin:0 0 18px;color:#9aa4b2}.pair{display:flex;gap:24px;align-items:flex-end;flex-wrap:wrap}
figure{margin:0;text-align:center;color:#9aa4b2}img{display:block;max-height:680px;max-width:100%;object-fit:contain}
</style>
</head>
<body><h1>腕管Pro Vela 设备截图</h1>${cards}</body>
</html>`;
  fs.writeFileSync(path.join(outputRoot, "index.html"), html);
}

function writeContactSheet(results) {
  const args = [];
  for (const item of results) {
    args.push(
      "-label",
      item.codename,
      path.join(outputRoot, item.codename, "combined.png")
    );
  }
  args.push(
    "-thumbnail",
    "320x320",
    "-tile",
    "4x",
    "-geometry",
    "+18+28",
    "-background",
    "#16181d",
    "-fill",
    "white",
    "-pointsize",
    "20",
    path.join(outputRoot, "all-devices-contact-sheet.png")
  );
  runQuiet("create contact sheet", "montage", args);
}

async function renderDevice(manager, entry) {
  const profile = readJson(path.join(root, "profiles", `${entry.codename}.json`));
  const normalRpk = path.join(root, entry.artifact);
  const keyboardRpk = buildKeyboardPackage(entry.codename);
  const mappedSkin = SKIN_BY_DEVICE[entry.codename];
  const skin = skinForDevice(entry.codename);
  const vvdName = createVvd(
    manager,
    entry.codename,
    profile,
    mappedSkin ? parseSkin(mappedSkin) : null
  );
  const deviceDir = path.join(outputRoot, entry.codename);
  fs.mkdirSync(deviceDir, { recursive: true });
  const homeRaw = path.join(deviceDir, "home-screen.png");
  const keyboardRaw = path.join(deviceDir, "keyboard-screen.png");
  const homeSkin = path.join(deviceDir, "home.png");
  const keyboardSkin = path.join(deviceDir, "keyboard.png");

  let session;
  let keyboardSession;
  try {
    session = await manager.startVvd({
      vvdName,
      grpcPort: 8600,
      qtHideWindow: true,
      origin: emu.IStartOrigin.Ide,
      stdoutCallback: () => {},
    });
    const instance = session.emulatorInstance;
    const agent = session.velaAgent;

    await instance.pushAndInstall(normalRpk, "com.typheye.wgpro");
    await instance.startApp("com.typheye.wgpro", false);
    await sleep(5000);
    let image = await agent.getScreenshot();
    fs.writeFileSync(path.join(deviceDir, "init-screen.png"), image);
    let check = findCheckButton(
      path.join(deviceDir, "init-screen.png"),
      profile.screen.width,
      profile.screen.height
    );
    if (check) {
      await click(agent, check);
      await sleep(4000);
      image = await agent.getScreenshot();
      fs.writeFileSync(path.join(deviceDir, "init-screen.png"), image);
    }
    fs.writeFileSync(homeRaw, image);

    await manager.stopVvd(vvdName);
    session = null;
    keyboardSession = await manager.startVvd({
      vvdName,
      grpcPort: 8600,
      qtHideWindow: true,
      origin: emu.IStartOrigin.Ide,
      stdoutCallback: () => {},
    });
    await keyboardSession.emulatorInstance.uninstall("com.typheye.wgpro");
    await keyboardSession.emulatorInstance.pushAndInstall(
      keyboardRpk,
      "com.typheye.wgpro"
    );
    await keyboardSession.emulatorInstance.startApp(
      "com.typheye.wgpro",
      false
    );
    await sleep(6000);
    image = await keyboardSession.velaAgent.getScreenshot();
    fs.writeFileSync(keyboardRaw, image);
  } finally {
    if (keyboardSession) await manager.stopVvd(vvdName);
    if (session) await manager.stopVvd(vvdName);
    try {
      manager.deleteVvd(vvdName);
    } catch (error) {
      // 临时 VVD 已删除时无需处理。
    }
  }

  if (skin) {
    compositeSkin(homeRaw, homeSkin, skin);
    compositeSkin(keyboardRaw, keyboardSkin, skin);
  } else {
    fs.copyFileSync(homeRaw, homeSkin);
    fs.copyFileSync(keyboardRaw, keyboardSkin);
  }
  combinedScreenshot(homeSkin, keyboardSkin, path.join(deviceDir, "combined.png"));
  console.log(
    `[screenshots] ${entry.codename} ${profile.productName} ${profile.designWidth}px`
  );
  return {
    codename: entry.codename,
    productName: profile.productName,
    designWidth: profile.designWidth,
    shape: profile.screen.shape,
    networkMode: profile.features.networkMode,
  };
}

function composeExisting(entry) {
  const profile = readJson(path.join(root, "profiles", `${entry.codename}.json`));
  const deviceDir = path.join(outputRoot, entry.codename);
  const homeRaw = path.join(deviceDir, "home-screen.png");
  const keyboardRaw = path.join(deviceDir, "keyboard-screen.png");
  if (!fs.existsSync(homeRaw) || !fs.existsSync(keyboardRaw)) {
    throw new Error(`${entry.codename} 缺少已有纯屏截图`);
  }
  const skin = skinForDevice(entry.codename);
  const homeSkin = path.join(deviceDir, "home.png");
  const keyboardSkin = path.join(deviceDir, "keyboard.png");
  if (skin) {
    compositeSkin(homeRaw, homeSkin, skin);
    compositeSkin(keyboardRaw, keyboardSkin, skin);
  }
  combinedScreenshot(homeSkin, keyboardSkin, path.join(deviceDir, "combined.png"));
  console.log(`[screenshots:compose] ${entry.codename}`);
  return {
    codename: entry.codename,
    productName: profile.productName,
    designWidth: profile.designWidth,
    shape: profile.screen.shape,
    networkMode: profile.features.networkMode,
  };
}

async function main() {
  const selected = new Set(
    (process.env.RENDER_DEVICES || "")
      .split(",")
      .map((value) => value.trim())
      .filter(Boolean)
  );
  const entries = buildIndex.builds.filter(
    (entry) => !selected.size || selected.has(entry.codename)
  );
  if (!entries.length) throw new Error("没有可渲染的设备");
  fs.mkdirSync(outputRoot, { recursive: true });
  fs.mkdirSync(keyboardRoot, { recursive: true });
  if (process.env.COMPOSE_ONLY === "1") {
    const results = entries.map(composeExisting);
    writeGallery(results);
    writeContactSheet(results);
    console.log(`[screenshots:compose] 完成 ${results.length} 台设备`);
    return;
  }
  const manager = new emu.VvdManager({
    sdkHome: emu.defaultSDKHome,
    vvdHome: emu.defaultVvdHome,
  });
  const results = [];
  for (const entry of entries) results.push(await renderDevice(manager, entry));
  writeGallery(results);
  writeContactSheet(results);
  console.log(`[screenshots] 完成 ${results.length} 台设备：${outputRoot}`);
}

main().catch((error) => {
  console.error(`[screenshots] ${error.stack || error.message}`);
  process.exit(1);
});
