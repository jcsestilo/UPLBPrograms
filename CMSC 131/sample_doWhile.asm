global _start

section .data
	SYS_EXIT		equ	60
	lpCnt			dq	5
	sum 			dq	0

section .text
_start:
	mov rcx, qword[lpCnt]		; loop counter
	mov rax, 1					; odd integer counter

sum_loop:
	
	add word[sum], rax		; sum += current odd integer
	add rax, 2				; set next odd integer
	dec rcx					; decrement loop counter
	cmp rcx, 0
	jne sum_loop

exit_here:
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall