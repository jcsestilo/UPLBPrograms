global _start

section .data
	SYS_EXIT	equ		60

	bnum1		db		19
	bquo		db		0
	brem		db		0

	wnum1		dw		4321
	wnum2		dw		1234
	wquo		dw		0
	wrem		dw 		0

	qnum1		dq 		73000000
	qnum2		dq		42000000
	qquo		dq 		0
	qrem		dq		0

section .text

_start:

	; bquo = bnum1 / 3 			; 3 is byte-sized
	mov ax, [bnum1]
	mov bl, 3
	div bl						; quotient-al	remainder-ah
	mov [bquo], al
	mov [brem], ah
	; bquo = 6, brem = 1

	; wquo = wnum1 / wnum2		; word
	mov dx, 0
	mov ax, [wnum1]
	div word[wnum2]				; quo-ax, rem-dx
	mov [wquo], ax
	mov [wrem], dx
	; wquo = 3
	; wrem = 619

	; qquo = qnum1 / qnum1		; qword
	mov rdx, 0
	mov rax, [qnum1]
	div qword[qnum2]			; quo-rax, rem-rdx
	mov [qquo], rax
	mov [qrem], rdx
	; qquo=1
	; qrem = 31000000

exit_here:
	;exit code
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall