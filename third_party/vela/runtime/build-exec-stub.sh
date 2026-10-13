#!/usr/bin/env bash
# 编译自制 exec-stub → app/src/main/jniLibs/arm64-v8a/libwgpro_vela_exec_stub.so
#
# 为什么要它：untrusted_app 不能 execve 私有目录（app_data_file）里的文件（连脚本也不行），
# 但可以 exec jniLibs（apk_data_file）里的文件；而 glibc loader 是用 mmap(PROT_EXEC) 把程序
# 读起来的，所以只要"loader + 这个静态 stub"待在 jniLibs，负载就能留在私有目录。
#
# 产物必须是：静态、无 PT_INTERP、ET_EXEC（-no-pie）。
set -euo pipefail
HERE="$(cd "$(dirname "$0")" && pwd)"
OUT="${1:-$HERE/../../../app/src/main/jniLibs/arm64-v8a/libwgpro_vela_exec_stub.so}"
NDK="${NDK:-/root/android/android-ndk-r27c}"
CLANG="$(ls -d "$NDK"/toolchains/llvm/prebuilt/*/bin/clang | head -1)"

mkdir -p "$(dirname "$OUT")"
"$CLANG" --target=aarch64-linux-android21 -static -nostdlib -fno-builtin -O2 \
  -Wl,--build-id=none -Wl,-e,_start -Wl,-no-pie -Wl,--no-dynamic-linker \
  -o "$OUT" "$HERE/wgpro_stub.c" "$HERE/wgpro_stub.S"

python3 - "$OUT" <<'PY'
import struct, sys
d = open(sys.argv[1], 'rb').read()
assert d[:4] == b'\x7fELF'
et = struct.unpack_from('<H', d, 16)[0]
phoff, = struct.unpack_from('<Q', d, 32)
phes, pn = struct.unpack_from('<HH', d, 54)
interp = None
for i in range(pn):
    o = phoff + i * phes
    if struct.unpack_from('<I', d, o)[0] == 3:
        po, = struct.unpack_from('<Q', d, o + 8)
        pf, = struct.unpack_from('<Q', d, o + 32)
        interp = d[po:po + pf].rstrip(b'\0').decode()
assert et == 2, 'e_type 必须是 ET_EXEC(-no-pie)，实际 %d' % et
assert interp is None, '不能有 PT_INTERP，实际 %r' % interp
print('OK: ET_EXEC, 无 PT_INTERP, %d 字节' % len(d))
PY
