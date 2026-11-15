/*
 * Minimal static AArch64 trampoline used by the Vela runtime.
 *
 * The engine execs paths inside the app's private directory, which Android's
 * untrusted_app SELinux domain does not allow. The same path is replaced by a
 * symlink to this binary in nativeLibraryDir. This trampoline invokes the glibc
 * loader shipped in nativeLibraryDir and passes the real payload from
 * WgproVelaDir/engine.
 *
 * Keep this file libc-free: it is linked with -nostdlib and all services are raw
 * Linux syscalls so the resulting ELF has no PT_INTERP and can be linked with
 * 16 KB ELF segment alignment for Android 15+ 16 KB page-size devices.
 */

typedef long i64;

enum {
    SYS_write = 64,
    SYS_readlinkat = 78,
    SYS_exit = 93,
    SYS_execve = 221,
    AT_FDCWD = -100,
};

static i64 syscall4(i64 number, i64 a, i64 b, i64 c, i64 d) {
    register i64 x0 __asm__("x0") = a;
    register i64 x1 __asm__("x1") = b;
    register i64 x2 __asm__("x2") = c;
    register i64 x3 __asm__("x3") = d;
    register i64 x8 __asm__("x8") = number;
    __asm__ volatile("svc #0"
                     : "+r"(x0)
                     : "r"(x1), "r"(x2), "r"(x3), "r"(x8)
                     : "memory");
    return x0;
}

static i64 syscall3(i64 number, i64 a, i64 b, i64 c) {
    return syscall4(number, a, b, c, 0);
}

static long text_length(const char *value) {
    long length = 0;
    if (value == 0) return 0;
    while (value[length] != 0) length++;
    return length;
}

static void report(const char *message) {
    syscall3(SYS_write, 2, (i64)message, text_length(message));
}

static int text_equal(const char *left, const char *right) {
    while (*left != 0 && *right != 0 && *left == *right) {
        left++;
        right++;
    }
    return *left == *right;
}

static int text_prefix(const char *text, const char *prefix) {
    while (*prefix != 0) {
        if (*text++ != *prefix++) return 0;
    }
    return 1;
}

static const char *base_name(const char *path) {
    const char *name = path;
    for (const char *cursor = path; *cursor != 0; cursor++) {
        if (*cursor == '/') name = cursor + 1;
    }
    return name;
}

static void append(char *target, long capacity, long *length, const char *source) {
    while (*source != 0 && *length < capacity - 1) {
        target[(*length)++] = *source++;
    }
    target[*length] = 0;
}

static const char *runtime_dir(char **environment) {
    static const char key[] = "WGPRO_VELA_DIR=";
    for (long i = 0; environment[i] != 0; i++) {
        if (text_prefix(environment[i], key)) {
            return environment[i] + sizeof(key) - 1;
        }
    }
    return "/data/data/com.typheye.wgpro/files/vela";
}

static const char *qemu_payload(const char *name) {
    if (text_equal(name, "qemu-system-armel")
            || text_equal(name, "qemu-system-armel-headless")) {
        return "/engine/qemu/linux-aarch64/qemu-system-armel-headless.elf";
    }
    if (text_equal(name, "qemu-system-aarch64")
            || text_equal(name, "qemu-system-aarch64-headless")) {
        return "/engine/qemu/linux-aarch64/qemu-system-aarch64-headless.elf";
    }
    return 0;
}

static int is_engine_tool(const char *name) {
    return text_equal(name, "e2fsck")
            || text_equal(name, "fsck.ext4")
            || text_equal(name, "mkfs.ext4")
            || text_equal(name, "resize2fs")
            || text_equal(name, "tune2fs");
}

static char native_dir[1024];
static char loader[1152];
static char payload[2048];
static char library_path[2048];
static char *program_argv[64];

void wgpro_start(long *initial_stack) {
    long argc = initial_stack[0];
    char **argv = (char **)(initial_stack + 1);
    char **environment = argv + argc + 1;
    if (argc < 1 || argv[0] == 0) {
        report("wgpro-stub: missing argv\n");
        syscall3(SYS_exit, 127, 0, 0);
    }

    i64 length = syscall4(SYS_readlinkat, AT_FDCWD, (i64)"/proc/self/exe",
                          (i64)native_dir, sizeof(native_dir) - 1);
    if (length <= 0) {
        report("wgpro-stub: readlink failed\n");
        syscall3(SYS_exit, 126, 0, 0);
    }
    native_dir[length] = 0;
    long slash = -1;
    for (long i = 0; i < length; i++) {
        if (native_dir[i] == '/') slash = i;
    }
    if (slash <= 0) {
        report("wgpro-stub: invalid nativeLibraryDir\n");
        syscall3(SYS_exit, 126, 0, 0);
    }
    native_dir[slash] = 0;

    const char *name = base_name(argv[0]);
    const char *runtime = runtime_dir(environment);
    const char *qemu = qemu_payload(name);
    long used = 0;
    if (qemu != 0) {
        append(payload, sizeof(payload), &used, runtime);
        append(payload, sizeof(payload), &used, qemu);
    } else if (is_engine_tool(name)) {
        append(payload, sizeof(payload), &used, runtime);
        append(payload, sizeof(payload), &used, "/engine/bin64/");
        append(payload, sizeof(payload), &used, name);
        append(payload, sizeof(payload), &used, ".elf");
    } else {
        report("wgpro-stub: unsupported target ");
        report(name);
        report("\n");
        syscall3(SYS_exit, 127, 0, 0);
    }

    used = 0;
    append(loader, sizeof(loader), &used, native_dir);
    append(loader, sizeof(loader), &used, "/libwgpro-ld.so");
    used = 0;
    append(library_path, sizeof(library_path), &used, runtime);
    append(library_path, sizeof(library_path), &used, "/engine/glibc/lib:");
    append(library_path, sizeof(library_path), &used, runtime);
    append(library_path, sizeof(library_path), &used, "/engine/lib64:");
    append(library_path, sizeof(library_path), &used, runtime);
    append(library_path, sizeof(library_path), &used, "/engine/lib64/gles_swiftshader:");
    append(library_path, sizeof(library_path), &used, runtime);
    append(library_path, sizeof(library_path), &used, "/engine/qemu/lib:");
    append(library_path, sizeof(library_path), &used, runtime);
    append(library_path, sizeof(library_path), &used, "/engine/bin64:");
    append(library_path, sizeof(library_path), &used, runtime);
    append(library_path, sizeof(library_path), &used, "/engine");

    long out = 0;
    program_argv[out++] = loader;
    program_argv[out++] = (char *)"--library-path";
    program_argv[out++] = library_path;
    program_argv[out++] = payload;
    for (long i = 1; i < argc && out < 63; i++) {
        program_argv[out++] = argv[i];
    }
    program_argv[out] = 0;

    syscall3(SYS_execve, (i64)loader, (i64)program_argv, (i64)environment);
    report("wgpro-stub: execve failed: ");
    report(loader);
    report("\n");
    syscall3(SYS_exit, 127, 0, 0);
}

__asm__(
    ".text\n"
    ".global _start\n"
    ".type _start, %function\n"
    "_start:\n"
    "  mov x0, sp\n"
    "  bl wgpro_start\n"
    "  mov x0, #127\n"
    "  mov x8, #93\n"
    "  svc #0\n"
    ".size _start, .-_start\n"
);
