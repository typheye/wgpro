# 腕管Pro Vela 统一端

`vela-wear` 是腕管Pro Vela 设备后续唯一维护主线。一个公共源码树按真实设备
代号选择 overlay、屏幕参数、`designWidth`、页面集合和网络实现，为每台设备
生成独立的签名 RPK。`vela-watch` 与 `vela-band` 仅作为历史实现和问题对照。

## 网络策略

| 设备类型 | networkMode | enableFetch | 数据通道 |
|---|---|---|---|
| Watch | `proxy` | `true` | 设备独立联网，经 proxy 域名 |
| Band Pro | `proxy` | `true` | 设备独立联网，经 proxy 域名 |
| Band / Band NFC | `bridge` | `false` | 客户端 Interconnect 桥接 |

构建脚本会强制校验上述策略。需要调整某类设备的联网方式时，应集中修改
`scripts/build.js` 中的策略表，而不是只改单个产物。

## 设备配置

| 代号 | 设备 | designWidth | 屏幕 | 类型/网络 |
|---|---|---:|---|---|
| `n66cn` | Xiaomi Smart Band 9 | 192 | 192×490 pill | band / bridge |
| `n66nfc` | Xiaomi Smart Band 9 NFC | 192 | 192×490 pill | band / bridge |
| `n67cn` | Xiaomi Smart Band 9 Pro | 336 | 336×480 rect | band-pro / proxy |
| `o66cn` | Xiaomi Smart Band 10 | 212 | 212×520 pill | band / bridge |
| `o66nfc` | Xiaomi Smart Band 10 NFC | 212 | 212×520 pill | band / bridge |
| `p67cn` | Xiaomi Smart Band 10 Pro | 336 | 336×480 rect | band-pro / proxy |
| `q66cn` | Xiaomi Smart Band 11 | 212 | 212×520 pill | band / bridge |
| `n62` | Xiaomi Watch S3 | 466 | 466×466 circle | watch / proxy |
| `n62lte` | Xiaomi Watch S3 eSIM | 466 | 466×466 circle | watch / proxy |
| `o62` | Xiaomi Watch S4 | 466 | 466×466 circle | watch / proxy |
| `o62lte` | Xiaomi Watch S4 eSIM | 466 | 466×466 circle | watch / proxy |
| `n62s` | Xiaomi Watch S4 Sport | 466 | 466×466 circle | watch / proxy |
| `o63` | Xiaomi Watch S4 41mm | 466 | 466×466 circle | watch / proxy |
| `o65` | REDMI Watch 5 | 432 | 432×514 rect | watch / proxy |
| `o65m` | REDMI Watch 5 eSIM | 432 | 432×514 rect | watch / proxy |
| `p65` | REDMI Watch 6 | 432 | 432×514 rect | watch / proxy |
| `p62` | Xiaomi Watch S5 | 480 | 480×480 circle | watch / proxy |

Vela 的手环工程类型同样是 `watch`，因此所有设备 profile 的
`deviceTypeList` 都必须是 `["watch"]`。设备差异由 profile 和 overlay 处理，
而不是伪造 `band` 工程类型。

## 构建

```bash
# 首次进入仓库先安装本地依赖
npm ci

# 只校验全部设备配置，不执行 AIoT 编译
npm run check

# 所有设备全部构建
npm run release

# 单个设备，例如 Band 10
npm run release:device -- o66cn

# 单个设备开发构建
npm run build:device -- p65 --build

# 查看命令帮助
node scripts/build.js --help
```

产物：

```text
dist/<codename>/<codename>_<device-name>_<version>_<YYYYMMDDHHmmss>.rpk
dist/build-index.json
```

构建脚本会：

1. 复制公共 `src/`；
2. 叠加 `variants/<codename>/`；
3. Band 设备删除浏览器、音乐播放器和手表专属资源；
4. 把设备代号对应的真实屏幕宽度写入 `designWidth`；
5. 生成 build profile；
6. 普通 Band 写入 `enableFetch=false` 使用客户端桥接；Band Pro 和 Watch
   写入 `enableFetch=true`，并在构建时固定 `networkProxy=true`；
7. 使用 release 证书签名；
8. 解包回读 manifest，校验屏幕、页面、能力和设备配置；
9. 生成包含产物 SHA256、大小和网络模式的 `build.json`。

签名证书不进入 Git。首次构建前需把
`sign/release/{private.pem,certificate.pem}` 放到仓库根目录的
`sign/release/` 下。构建只使用 `vela-wear/node_modules`，不再借用其他仓库。

## 新增设备

在 `profiles/` 增加真实设备代号 JSON，并在必要时创建
`variants/<codename>/`。不要复制整套 `src/`；只有与公共实现确实不同的文件
才进入 overlay。`productName`、真实屏幕宽度、`designWidth`、页面能力和
`networkMode` 必须与构建策略一致，提交前先运行 `npm run check`。

---

## ⚙️ 项目构建指南  

### 🛠️ 开发环境  
```bash
# 安装依赖
npm install

# 启动开发服务器
npm run start
```

### 📦 生产构建  
```bash
# 全部设备的开发构建
npm run build

# 全部设备的签名发布包（已启用 JSC 与 Protobuf）
npm run release
```

### 🐞 调试模式  
```bash
# 实时监控模式
npm run watch
```

---

## 📜 开源协议  
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)  
本项目采用 **GNU General Public License v3.0** 许可证

```text
版权所有 (C) 2025 wgpro 开发团队

本程序是自由软件：您可以根据自由软件基金会发布的 GNU 通用公共许可证第3版或
（根据您的选择）任何更高版本重新分发和/或修改它。

本程序的发布是希望它有用，但没有任何担保；甚至没有适销性或特定用途适用性的隐含担保。
有关更多详细信息，请参阅 GNU 通用公共许可证。

您应该已经收到了 GNU 通用公共许可证的副本。如果没有，请参阅 <https://www.gnu.org/licenses/>.
```

> ✨ 由Typheye提供技术支持  
> 🐞 问题反馈: [typheye@typheye.com](mailto:typheye@typheye.com)
