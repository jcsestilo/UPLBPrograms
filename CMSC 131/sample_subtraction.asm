global _start

section .data
	SYS_EXIT	equ		60

	bnum1		db		42
	bnum2		db		73

	wnum1		dw		4321
	wnum2		dw		1234
	wdiff		dw		0

section .text

_start:

	mov al, 12
	mov ah, 7

	; al = al - ah
	sub al, ah
	; al = 5

	; bnum1 = bnum1 - al
	sub byte[bnum1], al
	; bnum1 = 37

	; wdiff = wnum1 - wnum2
	mov bx, [wnum1]
	sub bx, [wnum2]		; bx
	mov [wdiff], bx
	; wdiff = 3087

	; bx = bx - 1
	dec bx
	; bx = 3086


exit_here:
	;exit code
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall