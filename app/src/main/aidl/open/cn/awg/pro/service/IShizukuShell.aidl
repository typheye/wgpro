package open.cn.awg.pro.service;

interface IShizukuShell {
    void destroy() = 16777114;
    String execute(String command) = 0;
}
