global _start
global swap
section .data
    SYS_EXIT equ 50
    num1 dq 19
    num2 dq 42

section .text
_start:
    mov rdi, num1 ; place the 1st argument to rdi
    mov rsi, num2 ; place the 2nd argument to rsi
    call swap

exit_here:
    mov rax, SYS_EXIT
    xor rdi, rdi 
    syscall

swap:
    push rbp ; preserve the value of rbp
    mov rbp, rsp ; store the initial value of rsp
    sub rsp,8 ; create a local variable of 8 bytes

    ; swap num1 and num2
    mov r10, qword[rdi] ; temp=num1
    mov qword[rbp-8],r10 ; put to stack
    mov r10, qword[rsi] ; num1=num2
    mov qword[rdi], r10
    mov r10, qword[rbp-8] ; num2=temp
    mov qword[rsi], r10

    add rsp, 8 ; release the local variable
    pop rbp ; restore the value of rbp
    ret