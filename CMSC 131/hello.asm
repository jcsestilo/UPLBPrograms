global _start

section .data
    SYS_WRITE   equ 1
    SYS_EXIT    equ 60
    message     db "Hello World", 10

section .text
_start:
    mov rax, SYS_WRITE
    mov rdi, 1
    mov rsi, message
    mov rdx, 13
    syscall

    mov rax, SYS_EXIT
    xor rdi, rdi
    syscall
