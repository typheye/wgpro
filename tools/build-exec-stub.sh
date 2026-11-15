#!/usr/bin/env bash
set -euo pipefail

root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/.." && pwd)
source_file="$root/app/src/main/native/vela-exec-stub/wgpro_stub.c"
output="$root/app/src/main/jniLibs/arm64-v8a/libwgpro_vela_exec_stub.so"

if [[ -n "${ANDROID_NDK_HOME:-}" ]]; then
    ndk=$ANDROID_NDK_HOME
elif [[ -n "${ANDROID_NDK_ROOT:-}" ]]; then
    ndk=$ANDROID_NDK_ROOT
else
    ndk=$(find "${ANDROID_SDK_ROOT:-$HOME/Android/Sdk}/ndk" \
        -mindepth 1 -maxdepth 1 -type d 2>/dev/null | sort -V | tail -1)
fi
if [[ -z "${ndk:-}" || ! -d "$ndk" ]]; then
    printf 'Android NDK not found; set ANDROID_NDK_HOME\n' >&2
    exit 1
fi

toolchain="$ndk/toolchains/llvm/prebuilt/linux-x86_64/bin"
compiler="$toolchain/aarch64-linux-android24-clang"
strip="$toolchain/llvm-strip"
readelf="$toolchain/llvm-readelf"

mkdir -p "$(dirname "$output")"
"$compiler" \
    -Os -static -nostdlib -fno-builtin -fno-stack-protector -fno-pic \
    -Wl,-e,_start \
    -Wl,-z,max-page-size=16384 \
    -Wl,-z,common-page-size=16384 \
    -Wl,-z,separate-code \
    -Wl,--build-id=none \
    -Wl,--gc-sections \
    -o "$output" "$source_file"
"$strip" --strip-all "$output"
chmod 755 "$output"

if ! "$readelf" -lW "$output" | awk '/LOAD/ { if ($NF != "0x4000") exit 1 }'; then
    printf 'exec-stub contains a non-16KB PT_LOAD segment\n' >&2
    exit 1
fi
if "$readelf" -lW "$output" | grep -q 'INTERP'; then
    printf 'exec-stub unexpectedly has PT_INTERP\n' >&2
    exit 1
fi

printf 'Built 16KB-aligned exec-stub: %s\n' "$output"
