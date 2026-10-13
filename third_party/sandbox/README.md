# sandbox —— proot + GNU tar 沙盒（支线）

用途：在应用私有目录里跑 Linux 用户态任务（glibc 程序）。

* 随包内容：`jniLibs/arm64-v8a/{libproot.so,libproot-loader.so,libtar.so}`、
  `assets/sandbox/arm64-v8a/*`（tar/proot 的 Termux 依赖）、`assets/` 下的 rootfs 说明。
* 代码位置：`core/sandbox/SandboxManager.java`（当前**无 UI 入口**，保留能力）。
* 注意：proot 只能跑 **glibc** 负载；Android/bionic 二进制（如我们的 QEMU）在 glibc rootfs 里
  一定会 `not found`（`PT_INTERP=/system/bin/linker64`），不要走这条路。
