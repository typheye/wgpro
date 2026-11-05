## 🚀 项目介绍

**wgpro(vela-wear)** 是腕管Pro 的 Vela 统一源码仓库。一个公共源码树，
按真实设备代号选择 overlay、屏幕参数、`designWidth`、页面集合和网络实现，
生成彼此独立的 RPK。

当前设备 profile：

| 代号 | 设备 | designWidth | 屏幕 | 类型/网络 |
|---|---|---:|---|---|
| `n66cn` | Xiaomi Smart Band 9 | 192 | 192×490 pill | band / bridge |
| `n66nfc` | Xiaomi Smart Band 9 NFC | 192 | 192×490 pill | band / bridge |
| `n67cn` | Xiaomi Smart Band 9 Pro | 336 | 336×480 rect | band-pro / proxy |
| `o66cn` | Xiaomi Smart Band 10 | 212 | 212×520 pill | band / bridge |
| `o66nfc` | Xiaomi Smart Band 10 NFC | 212 | 212×520 pill | band / bridge |
| `p67cn` | Xiaomi Smart Band 10 Pro | 336 | 336×480 rect | band-pro / proxy |
| `n62` | Xiaomi Watch S3 | 466 | 466×466 circle | watch / system |
| `n62lte` | Xiaomi Watch S3 eSIM | 466 | 466×466 circle | watch / system |
| `o62` | Xiaomi Watch S4 | 466 | 466×466 circle | watch / system |
| `o62lte` | Xiaomi Watch S4 eSIM | 466 | 466×466 circle | watch / system |
| `n62s` | Xiaomi Watch S4 Sport | 466 | 466×466 circle | watch / system |
| `o63` | Xiaomi Watch S4 41mm | 466 | 466×466 circle | watch / system |
| `o65` | REDMI Watch 5 | 432 | 432×514 rect | watch / system |
| `o65m` | REDMI Watch 5 eSIM | 432 | 432×514 rect | watch / system |
| `p65` | REDMI Watch 6 | 432 | 432×514 rect | watch / system |
| `p62` | Xiaomi Watch S5 | 480 | 480×480 circle | watch / system |

Vela 的手环工程类型同样是 `watch`，因此所有设备 profile 的
`deviceTypeList` 都必须是 `["watch"]`。设备差异由 profile 和 overlay 处理，
而不是伪造 `band` 工程类型。

## 构建

```bash
# 所有设备全部构建
npm run release

# 单个设备，例如 Band 10
npm run release:device -- o66cn

# 单个设备开发构建
npm run build:device -- p65 --build
```

产物：

```text
dist/<codename>/wgpro-<codename>-<version>.rpk
dist/build-index.json
```

构建脚本会：

1. 复制公共 `src/`；
2. 叠加 `variants/<codename>/`；
3. Band 设备删除浏览器、音乐播放器和手表专属资源；
4. 把设备代号对应的真实屏幕宽度写入 `designWidth`；
5. 生成 build profile；
6. 普通 Band 写入 `enableFetch=false` 使用客户端桥接；Band Pro 和 Watch
   写入 `enableFetch=true`，Band Pro 另外按 product 切换到 proxy URL；
7. 使用 release 证书签名；
8. 解包回读 manifest，校验配置确实进入 RPK。

签名证书不进入 Git，首次构建前需把 `sign/release/{private.pem,certificate.pem}`
放到仓库根目录的 `sign/release/` 下。

## 新增设备

在 `profiles/` 增加真实设备代号 JSON，并在必要时创建
`variants/<codename>/`。不要复制整套 `src/`；只有与公共实现确实不同的文件
才进入 overlay。

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
# 构建项目
npm run build

# 生成发布包
npm run release

# 生成发布包(高性能)
npm run release --enable-jsc --enable-protobuf
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
