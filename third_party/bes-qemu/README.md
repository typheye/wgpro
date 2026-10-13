# bes-qemu —— bes1503 板级 QEMU（支线，研究中）

用途：在客户端里跑 **BES2700/BEST1503**（小米手环 9/10 系列）的固件，做逆向研究。

* 上游：`github.com/0x504B0304/bes-qemu`（公开核心是"执行脚手架"，非完整 SoC 模型）。
* 随包内容：`jniLibs/arm64-v8a/libqemu-system-arm.so`（aarch64）、`libglib-2.0.so` `libgio-2.0.so`
  `libgobject-2.0.so` `libgmodule-2.0.so` `libz.so` `libpixman-1.so` `libpcre2-8.so` `libffi.so`
  `libiconv.so` `libandroid-support.so`；`assets/{mock_guest.bin,mock_hardware.json,qemu/keymaps}`。
* 依赖打包要点（踩过，务必保持）：**AGP 只打包 `lib*.so`**，版本化名字会被静默丢弃 →
  依赖统一改名并 `patchelf --replace-needed` 改写 `DT_NEEDED`，并同步改 `.dynstr`（verneed 名字）；
  二进制与依赖都要有 **`$ORIGIN` rpath**（exec 时链接器不搜 nativeLibraryDir）。
* 代码位置：`core/emu/MockDeviceEngine.java`、`core/emu/MockDeviceScreen.java`、`core/emu/RfbClient.java`。
* 现状：合成客体可出画；零售固件能执行到引导上下文处（需要真机 ROM/flash dump 才能继续）。
  详细过程见仓库 `_guide/mock-device-engine.md`。
