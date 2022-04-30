global _start

section .data
	SYS_EXIT	equ	60

	qnum1		dq 	42000000
	qnum2		dq	73000000
	qsum		dq	0

section .text

_start:

	mov rax, 10
	mov rbx, 8

	; rax = rax + rbx
	add rax, rbx
	; rax = 18

	; qnum1 = qnum1 + 1
	add qword[qnum1], 1
	; qnum1 = 42000001

	inc qword[qnum1]
	; qnum1 = 42000002

	; qnum1 = qnum1 + rax
	add [qnum1], rax
	; qnum1 = 42000020

	; qnum1 = qnum1 + qnum2
	mov rcx, [qnum2]
	add [qnum1], rcx
	; qnum1 = 115000020

	; qsum = qnum1 + 5
	add qword[qnum1], 5 ; qnum1 = qnum1 + 5
	mov rdx, [qnum1]
	mov [qsum], rdx
	; qsum = 115000020 + 5

exit_here:
	;exit code
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall