/* wgpro 自制 exec-stub：把"引擎要 exec 的路径"trampoline 到 glibc loader。
 *
 * 事实（来自公开的 Android 行为 + 本项目实测）：
 *   - untrusted_app 不能 execve 私有目录（app_data_file）里的文件，连脚本也不行；
 *   - 但 mmap(PROT_EXEC) 允许，而 glibc loader 正是用 mmap 把程序读起来的；
 *   - 所以 jniLibs 里只放【loader】+【这个静态 stub】（安装后在 apk_data_file，可 exec），
 *     引擎要 exec 的路径做软链接指向 stub，stub 再用 loader --library-path 拉起真负载。
 *
 * 本文件为 wgpro 自行实现（仅取事实，不复制他人代码）。
 */
typedef long i64;

#define SYS_readlinkat 78
#define SYS_execve 221
#define SYS_exit 93
#define SYS_write 64
#define AT_FDCWD (-100)

static i64 sc3(i64 n, i64 a, i64 b, i64 c)
{
    register i64 x8 __asm__("x8") = n, x0 __asm__("x0") = a;
    register i64 x1 __asm__("x1") = b, x2 __asm__("x2") = c;
    __asm__ volatile("svc #0" : "+r"(x0) : "r"(x8), "r"(x1), "r"(x2) : "memory");
    return x0;
}

static i64 sc4(i64 n, i64 a, i64 b, i64 c, i64 d)
{
    register i64 x8 __asm__("x8") = n, x0 __asm__("x0") = a;
    register i64 x1 __asm__("x1") = b, x2 __asm__("x2") = c, x3 __asm__("x3") = d;
    __asm__ volatile("svc #0" : "+r"(x0) : "r"(x8), "r"(x1), "r"(x2), "r"(x3) : "memory");
    return x0;
}

static long slen(const char *s);

static void say(const char *m)
{
    sc3(SYS_write, 2, (i64)m, slen(m));
}

static long slen(const char *s)
{
    long n = 0;
    while (s[n]) {
        n++;
    }
    return n;
}

static int has_prefix(const char *s, const char *p)
{
    while (*p) {
        if (*s++ != *p++) {
            return 0;
        }
    }
    return 1;
}

static const char *base_of(const char *p)
{
    const char *s = p;
    for (const char *q = p; *q; q++) {
        if (*q == '/') {
            s = q + 1;
        }
    }
    return s;
}

static const char *env_get(char **envp, const char *key)
{
    for (long i = 0; envp[i]; i++) {
        if (has_prefix(envp[i], key)) {
            return envp[i] + slen(key);
        }
    }
    return 0;
}

#define CAP 1024

static char g_native_dir[CAP];
static char g_loader[CAP];
static char g_payload[CAP];
static char g_libpath[CAP];
static char g_ldenv[CAP + 32];
static char *g_argv[24];
static char *g_envp[80];

void wgpro_start(void *sp_in);

void wgpro_start(void *sp_in)
{
    i64 *sp = (i64 *)sp_in;
    long argc = sp[0];
    char **argv = (char **)(sp + 1);
    char **envp = argv + argc + 1;
    if (argc < 1 || !argv[0]) {
        say("stub: no argv\n");
        sc3(SYS_exit, 127, 0, 0);
    }

    /* nativeLibraryDir = dirname(/proc/self/exe)（stub 本体在 jniLibs） */
    i64 n = sc4(SYS_readlinkat, AT_FDCWD, (i64)"/proc/self/exe", (i64)g_native_dir, CAP - 1);
    if (n <= 0) {
        say("stub: readlink /proc/self/exe failed\n");
        sc3(SYS_exit, 126, 0, 0);
    }
    g_native_dir[n] = 0;
    long cut = 0;
    for (long i = 0; i < n; i++) {
        if (g_native_dir[i] == '/') {
            cut = i;
        }
    }
    if (!cut) {
        say("stub: no dir in /proc/self/exe\n");
        sc3(SYS_exit, 126, 0, 0);
    }
    g_native_dir[cut] = 0;

    const char *rt = env_get(envp, "WGPRO_VELA_DIR=");
    if (!rt) {
        rt = "/data/data/com.typheye.wgpro/files/vela";
    }

    /* <nativeLibDir>/libwgpro-ld.so = glibc 的 ld-linux-aarch64.so.1 */
    long L = 0;
    for (const char *p = g_native_dir; *p && L < CAP - 1; p++) {
        g_loader[L++] = *p;
    }
    for (const char *p = "/libwgpro-ld.so"; *p && L < CAP - 1; p++) {
        g_loader[L++] = *p;
    }
    g_loader[L] = 0;

    /* 负载：在几个候选目录里找 <basename>.elf（引擎的二进制分布在不同子目录） */
    const char *me = base_of(argv[0]);
    static const char *subs[] = {
        "/engine/", "/engine/qemu/linux-aarch64/", "/engine/bin64/", "/engine/bin/"
    };
    g_payload[0] = 0;
    for (unsigned s = 0; s < sizeof(subs) / sizeof(subs[0]); s++) {
        long P = 0;
        for (const char *p = rt; *p && P < CAP - 1; p++) {
            g_payload[P++] = *p;
        }
        for (const char *p = subs[s]; *p && P < CAP - 1; p++) {
            g_payload[P++] = *p;
        }
        for (const char *p = me; *p && P < CAP - 1; p++) {
            g_payload[P++] = *p;
        }
        for (const char *p = ".elf"; *p && P < CAP - 1; p++) {
            g_payload[P++] = *p;
        }
        g_payload[P] = 0;
        long fd = sc4(56 /* openat (arm64: 56, 57 是 close) */, AT_FDCWD, (i64)g_payload, 0 /* O_RDONLY */, 0);
        say("stub: try ");
        say(g_payload);
        say(fd >= 0 ? "  -> found\n" : "  -> missing\n");
        if (fd >= 0) {
            break;
        }
    }

    /* LD_LIBRARY_PATH：引擎自带的几个库目录（顺序参考引擎自身布局） */
    static const char *dirs[] = {
        "/engine/glibc/lib/", "/engine/lib64/", "/engine/",
        "/engine/lib64/gles_swiftshader/", "/engine/qemu/lib/"
    };
    long Q = 0;
    for (unsigned d = 0; d < sizeof(dirs) / sizeof(dirs[0]); d++) {
        for (const char *p = rt; *p && Q < CAP - 1; p++) {
            g_libpath[Q++] = *p;
        }
        for (const char *p = dirs[d]; *p && Q < CAP - 1; p++) {
            g_libpath[Q++] = *p;
        }
        if (Q < CAP - 1) {
            g_libpath[Q++] = ':';
        }
    }
    if (Q) {
        g_libpath[Q - 1] = 0;
    }

    long E = 0;
    for (const char *p = "LD_LIBRARY_PATH="; *p; p++) {
        g_ldenv[E++] = *p;
    }
    for (const char *p = g_libpath; *p; p++) {
        g_ldenv[E++] = *p;
    }
    g_ldenv[E] = 0;

    long m = 0;
    for (long i = 0; envp[i] && m < 76; i++) {
        if (!has_prefix(envp[i], "LD_LIBRARY_PATH=")) {
            g_envp[m++] = envp[i];
        }
    }
    g_envp[m++] = g_ldenv;
    g_envp[m] = 0;

    long a = 0;
    g_argv[a++] = g_loader;
    g_argv[a++] = (char *)"--library-path";
    g_argv[a++] = g_libpath;
    g_argv[a++] = g_payload;
    for (long i = 1; i < argc && a < 20; i++) {
        g_argv[a++] = argv[i];
    }
    g_argv[a] = 0;

    sc3(SYS_execve, (i64)g_loader, (i64)g_argv, (i64)g_envp);
    say("stub: execve ");
    say(g_loader);
    say(" failed\n");
    sc3(SYS_exit, 127, 0, 0);
}
