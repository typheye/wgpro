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
`android.emulation.control.EmulatorController/{getScreenshot,sendMouse}`；
`getScreenshot` 响应 `Image` 的 **field 4 就是 PNG**。

### ⚠️ 输入必须走 sendMouse，sendTouch 对 Vela 客机无效

实测（帧缓冲像素差，同一手势）：

| 通道 | 消息 | 滑动后画面差异 |
|---|---|---|
| `sendTouch` | `TouchEvent{touches=[Touch{x=1,y=2,identifier=3,pressure=4}]}` | **2.1/255（约等于秒针走动，等于没收到）** |
| **`sendMouse`** | `MouseEvent{x=1,y=2,buttons=3}` | **75.5/255（客机真的切了界面）** |

* 语义：`buttons=1` 按下/移动、`buttons=0` 抬手；**长按 = 按住期间每 ~100ms 重发 `buttons=1`**。
* 坐标用**客机显示**坐标（即 `getScreenshot` 返回帧的像素坐标，如 466×466）。
* 发送必须**单线程按序**（DOWN→MOVE→UP），且移动做合并；每个事件 new 一个线程会乱序，导致"手指卡住、点不动"。
* 结论来源：同源参考实现内的实测注释 + 小米自家自动化（npm `velajs-mcp`）同样只用 `sendMouse`。

### 按键（与 AIoT IDE 一致）

`sendKey` + `KeyboardEvent{codeType=1(Evdev), eventType=2(keypress), keyCode=<evdev>}`；
页面右侧按键条用的键码：电源 **116** / 主页 **102** / 返回 **158** / 确认 **28** / 音量 **115、114**。
实测：按"主页"后画面差异 65.3/255，客机确实跳转（走进了「设置」）。

### shell 指令（宿主侧，已实测）

启动参数带 `-port 5560`（模拟器只接受 **5554–5584 的偶数**控制台端口；adb 占 `+1`），
宿主即可对**客机内部**下发指令：

```bash
adb -s <手机序列号> forward tcp:5561 tcp:5561
adb connect 127.0.0.1:5561
adb -s 127.0.0.1:5561 shell "uname -a"
# → NuttX Xiaomi Watch S4 12.3.0 ... arm miwear
```

### 手表皮肤（与 AIoT IDE 同源）

皮肤取自 SDK/负载里的 `engine/skins/<skin>/`：`background.png`（机身）+ `foreground.png`（表圈遮罩）+ `layout`。
`xiaomi_s4` 的 layout：画布 **601×973**、屏幕 **466×466**、屏幕偏移 **(65,253)**、圆角 233。
页面按"画布 fitCenter + 屏幕按同比例摆到 (65,253)"渲染，得到与 IDE 一致的手表外观。

### 性能要点（实测 CPU）

| 做法 | 应用 CPU |
|---|---|
| `getScreenshot` + PNG，每 250ms 轮询 | ~120%（且卡顿） |
| **`streamScreenshot` + RGBA8888 直通**（免 PNG 编解码）+ 复用同一张 Bitmap + 拷贝前限帧 30fps + 日志面板限流 + `-smp 2` | **48–90%** |

注意：`Image.width/height` 恒为 0（proto 已标注废弃），必须用**像素数开平方**反推边长，否则每帧都会被丢掉 → 黑屏。

---

## 客机日志（默认看不到的那部分）

外壳 stdout/stderr 只有 **模拟器自身** 的日志；**客机（NuttX/Vela）自己的日志**默认走客机内核/串口，不会进外壳输出。
启动参数加 **`-show-kernel`** 即可把客机内核日志直接打到外壳 stdout，现有日志通道就能显示（实测能看到 `[ap]`、`AT_RIL`、带客机时间戳的行）。

## 前台服务守护 + 唤醒锁

引擎运行期间由 `core/emu/VelaGuardService` 常驻：**前台服务**（`foregroundServiceType=specialUse`，通知渠道 `virtual_device_channel_v1`，标题「虚拟设备运行中」）
+ **`PARTIAL_WAKE_LOCK`（tag `wgpro:vela-emulator`）**，避免熄屏/后台被回收。
只在**有实例运行**时存在：引擎起来时启动，最后一个实例结束（含崩溃）即撤；`START_NOT_STICKY`。

## 设备状态实时写库（含崩溃）

`VelaEmulator.setExitListener(...)` 在引擎输出泵结束时回调 → 管理器把 `devices.db` 里该虚拟设备的 `connected` 置 0
并撤守护。实测：引擎运行 `connected=1` + 守护在；引擎被杀 `connected=0` + 守护自动撤。

## 客机指令通道（Terminal/单条指令）

两条路，**都已带进包**：

1. **静态 adb 二进制**：`jniLibs/arm64-v8a/libwgpro_adb.so`（3.13MB，静态链接、零依赖）。
   来源是 NDK 编译的 Android 原生 adb（Magisk 模块 adb-ndk）。
   ⚠️ **它的 prefs 目录写死 `/sdcard/.android` 且不认 `$HOME`**，应用域建不了该目录 → `adb server` 起不来
   （`Cannot mkdir '/sdcard/.android': Permission denied` → `failed to start daemon`）。除非申请 `MANAGE_EXTERNAL_STORAGE`，否则不能走它。
2. **Java 直连 adb 协议**：`core/emu/VelaAdbClient`（CNXN/OPEN/WRTE，约 250 行），**不需要 adb server、不需要额外权限**。
   引擎监听在 **IPv6 回环 `::1`**（`netstat` 可见 `::1:5560`/`::1:5561`）；`127.0.0.1` 会被立刻关闭（`read=-1` 秒断），
   `::1` + `maxdata=1048576` + `host::` 会被保持连接。**这条通道尚待在真机端确认完整握手**。

宿主侧已验证可行（用于对照）：`adb -s <手机> forward tcp:5561 tcp:5561` → `adb connect 127.0.0.1:5561` → `adb -s 127.0.0.1:5561 shell "uname -a"`。

## 云端字段与镜像节点（设备详情页）

`service.typheye.cn/api.php?type=vela_devices` 每个机型除原有字段外新增：
`version` / `updatedAt` / `coverUrl`（**1:1**，一张）/ `description` / `download_mirrors`。

```json
"imageUrl": "https://res.typheye.cn/vela/vela-miwear-watch-5.0-full.zip",
"download_mirrors": [
  { "id": "dl2", "url": "https://dl2.otodone.com/vela/vela-miwear-watch-5.0-full.zip" },
  { "id": "dl",  "url": "" }
]
```

- `imageUrl` 是权威地址（下载失败一律回退它）；`download_mirrors[].id` 与 `res.typheye.cn/api.php?action=nodes` 的 `nodes[].id` 对应。
- 本次 `dl2` 已就位（与 res 上同一个包做**硬链接**，不占额外空间）；`dl` 按计划**先空着**（客户端会跳过空 url）。
- 头图：`https://res.typheye.cn/vela/cover/vela-miwear-watch-5.0.png`（由 `background.png` 合成 1:1 深色底）。

## 设备详情页（创建设备页内的 Fragment）

`ui/function/virtualdevice/VirtualDeviceDetailFragment` + `res/layout/fragment_virtual_device_detail.xml`
（**照 `fragment_resource_detail.xml` 抄改**：内容容器 / 数据条 / 封面 / 关于此设备 / 更多 / 底部药丸）。

- 入口：创建设备页点机型卡片；下载管理里系统镜像任务（id 前缀 `vela-`）的「查看设备详情」也会带 `EXTRA_DEVICE_ID` 跳进来。
- 底部药丸三态：未创建=「获取」（复用 `DownloadProbeSheet` → `DownloadNodes` **先测速选最快节点**再入队）→ 下载中=进度（点按进下载管理）→ 已创建=「进入模拟页面」。
- 顶部留白要自己补：`AppBarBlur` 只给列表加了 `paddingTop`，本页滚动视图需 `AppBarBlur.padScrollableTop(scroll, appBar.getHeight())`，否则标题被应用栏压住。
- 安装（解包/铺 AVD/写库）统一走 `core/emu/VelaProvision`，创建设备页与详情页共用。

## 默认设备名

新建虚拟设备默认名 **`设备名称 XXXX`**（四位随机大写字母/数字，见 `VelaProvision.defaultDeviceName()`），
仍写入设备库 `note` 字段，用户后续可在长按菜单「改名备注」里改。
