/* wgpro exec-stub 入口：把内核给的 sp 作为第一个参数传给 C。
 * 注意不能在 C 函数里读 sp —— 编译器序言会改它（踩过：读到 argc<1）。 */
    .text
    .globl _start
    .type _start, %function
_start:
    mov x0, sp
    b wgpro_start
