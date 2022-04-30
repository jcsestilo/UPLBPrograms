global _start

section .data
	SYS_EXIT		equ	60
	lpCnt			dq	5
	sum 			dq	0

section .text
_start:
	mov rcx, qword[lpCnt]
	mov rax, 1

sum_loop:
	cmp rcx, 0
	je exit_here
	add word[sum], rax		; sum current odd integer
	add rax, 2				; set next odd integer
	dec rcx					; decrement loop counter
	jmp sum_loop

exit_here:
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall