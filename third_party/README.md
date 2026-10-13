# third_party —— 第三方/自建运行时的规范目录

本目录**只放**：说明文档、源码、编译脚本、下载清单。
**大二进制（引擎、镜像、glibc、proot 等）一律不进 git**（见根 `.gitignore`），由脚本按需获取。

## 索引

| 子目录 | 内容 | 是否进 APK |
|---|---|---|
| `vela/` | **产品线**：Vela 模拟器（官方引擎 + 系统镜像），自制 exec-stub 源码与编译脚本 | ✅ 仅 `libwgpro-ld.so`（glibc loader）+ `libwgpro_vela_exec_stub.so`；引擎与镜像**按需下载** |
| `bes-qemu/` | **支线**：`0x504B0304/bes-qemu` 的 bes1503 板级 QEMU（跑零售固件，研究中） | ✅ 当前随包（`jniLibs/libqemu-system-arm.so` + 依赖） |
| `sandbox/` | **支线**：proot + tar 沙盒（跑 Linux 任务） | ✅ 当前随包（`jniLibs/libproot*.so` / `libtar.so` + `assets/sandbox`） |

## 约定

1. **能下载的不打包**：Vela 引擎（约 125MB）与系统镜像（约 1.1GB）走 `res.typheye.cn/vela/*`；
   包内只留 loader + stub（约 250KB）。
2. **随包的东西必须有说明**：来源、版本、许可、用途，写在各自 README 里。
3. **来源与许可**：各子目录 README 必须标注出处（官方/社区/自建）与许可情况；
   自建部分（如 `vela/runtime/wgpro_stub.c`）为 wgpro 自行实现。
