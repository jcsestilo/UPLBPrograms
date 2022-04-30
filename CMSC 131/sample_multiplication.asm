global _start

section .data
	SYS_EXIT	equ		60

	bnum1		db		42
	bnum2		db		73
	wprod		dw		0

	wnum1		dw		4321
	wnum2		dw		1234
	dprod		dd		0

	dnum1		dd 		42000
	dnum2		dd 		73000
	qprod		dq		0

section .text

_start:

	; wprod = bnum1 * bnum2
	mov al, [bnum1]
	mul byte[bnum2]		; product ax
	mov [wprod], ax
	; wprod = 3066

	; dprod = wnum1 * wnum2
	mov ax, [wnum1]
	mul word[wnum2]			; product dx:ax
	mov word[dprod], ax 	; lower 16 bits of dprod is ax
	mov word[dprod+2], dx	; upper 16 bits of dprod is dx
	; dprod = 5332114

	; qprod = dnum1 * dnum2
	mov eax, [dnum1]
	mul dword[dnum2]		; product in edx:eax
	mov dword[qprod], eax	; lower 32 bits of qprod is eax
	mov dword[qprod+4], edx	; upper 32 bits of qprod is edx
	; qprod = 3066000000


exit_here:
	;exit code
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall