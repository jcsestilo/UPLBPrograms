global _start

section .data
	SYS_EXIT		equ	60
	f1				dw	0
	f2 				dw	1
	N 				dq	8

section .text
_start:
	mov ax, 0
	mov rcx, qword[N]

fibo:
	add ax, word[f1]
	mov bx, word[f2]		; f1 = f2
	mov word[f1], bx
	mov word[f2], ax 		;f2 = ax
	loop fibo

exit:
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall