# vela —— Vela 模拟器运行时（已跑通）

在客户端里启动**小米官方 Vela 手表系统**（真表盘 UI），画面经 gRPC 取帧、触摸经 `sendTouch` 注入。

## 组成

| 部分 | 说明 | 来源 |
|---|---|---|
| 引擎 | Android 模拟器体系的 `emulator` / `qemu-system-armel-headless`（Linux aarch64） | 社区整理件（`StrGin/vortex` 的 Release `engine.tar`）；版权归原作者 |
| glibc | Termux 版 glibc（`ld-linux-aarch64.so.1` 等） | 同上（`glibc.tar`） |
| 系统镜像 | `nuttx` / `vela_system.bin` / `vela_data.bin` | `541236987/vela-images` Release：`vela-miwear-watch-5.0.zip` |
| exec-stub | `runtime/wgpro_stub.c` + `runtime/wgpro_stub.S` | **wgpro 自制**（仅采用公开机制） |

## 包内 vs 下载

* **进 APK**：`app/src/main/jniLibs/arm64-v8a/libwgpro-ld.so`（glibc loader 改名）、
  `libwgpro_vela_exec_stub.so`（自制 stub）。
* **按需下载**（`https://res.typheye.cn/vela/`）：`payload.tar`（引擎负载）、
  `nuttx`、`vela_system.bin`、`vela_data.bin`。

## 私有目录布局（`files/vela/`）

```
vela/
├── payload.tar                     # 下载的引擎负载（解包后即下面的 engine/）
├── engine/                         # 引擎根目录（= ANDROID_EMULATOR_LAUNCHER_DIR）
│   ├── emulator, lib64/(含 gles_swiftshader、libStubXlib.so), bin64/, qemu/linux-aarch64/*.elf
│   └── glibc/lib/, glibc/lib64/
├── images/{nuttx,vela_system.bin,vela_data.bin}
├── home/.vela/vvd/<avd>.vvd/{config.ini, 镜像软/硬链}   # 引擎按 $HOME/.vela/vvd 找 AVD
└── emulator -> engine              # 引擎按 $ANDROID_SDK_ROOT/emulator/ 找自己的 lib64
```

## 必需的环境变量（少一个就会在日志里明确报错）

| 变量 | 值 | 不设的后果 |
|---|---|---|
| `WGPRO_VELA_DIR` | `<files>/vela` | stub 找不到负载 |
| `HOME` | `<files>/vela/home` | 找不到 AVD |
| `VELA_HOME` / `ANDROID_AVD_HOME` | `<home>/.vela` / `<files>/vela/home/.vela/vvd` | `unknown virtual device name` |
| `ANDROID_SDK_ROOT` / `ANDROID_EMULATOR_HOME` / `ANDROID_HOME` | `<files>/vela` | 找不到 `emulator/` |
| `ANDROID_EMULATOR_LAUNCHER_DIR` | `<files>/vela/engine` | `Invalid executable directory` → OpenGLES 初始化失败 |
| `LD_PRELOAD` | `<engine>/lib64/libStubXlib.so` | `libOpenglRender.so: undefined symbol: XOpenDisplay` |
| `TMPDIR` / `ANDROID_TMP` | `<home>/tmp` | 去写 `/tmp`（app 不可写）后零输出退出 |
| `EMULATOR_DISABLE_GPU=1`、`QEMU_DISABLE_SNAPSHOT=1`、`ANDROID_EMULATOR_SKIP_ADB_CHECKS=1`、`LC_ALL=C` | — | GL/快照/adb 检查相关报错 |

## 启动与取帧

```
<engine>/qemu/linux-aarch64/qemu-system-armel-headless.elf   # 经软链→stub→glibc loader 拉起
  -vela -avd <id> -grpc <port> -no-window -gpu swiftshader_indirect -no-audio
  -qemu -smp 4 -semihosting
```
gRPC（`io.grpc:grpc-okhttp`，**原始字节 marshaller，无需 protoc**）：
`android.emulation.control.EmulatorController/{getScreenshot,sendTouch}`；
`getScreenshot` 响应 `Image` 的 **field 4 就是 PNG**。
