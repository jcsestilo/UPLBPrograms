global _start

section .data
	SYS_EXIT	equ		60

	age		db		25		; age
	dprodDays	dd		0 		; result for multiplication of days
	qprodHrs	dq 		0 		; result for multiplication of hours

	days		dd 		0 		; result for days
	hours 		dq		0 		; result for hours


section .text

_start:
	
	; getting the days
	; dprodDays = age * 365
	mov ax, [age]			; move the value of age to ax because it is the multiplicand
	mov bx, 365					; move 365 to the register, bl
	mul bx						; product = dx:ax
	mov word[dprodDays], ax 	; mov value of ax to the right half of dprodDays
	mov word[dprodDays+2], dx 	; mov value of dx to the left half of dprodDays
	; dprodDays = 9125

	mov eax, [dprodDays]		; mov to eax the value of dprodDays
	mov [days], eax				; change the value of days to value of eax register

	; getting the hours
	; qprodHrs = days * 24
	mov eax, 0 					; reset the value of eax
	mov eax, [days]				; put value of days to eax
	mov ecx, 24					; put 24 value to ecx register
	mul ecx						; product = edx:eax
	mov dword[qprodHrs], eax	; mov value of eax to the right half of qprodHrs
	mov dword[qprodHrs+4], edx	; mov value of eax to the left half of qprodHrs; offset 4: offset 32 bits
	; qprodHrs = 219000

	mov rdx, 0 					; reset the value of rdx so we are safe
	mov rdx, [qprodHrs]			; move value of qprodHrs to rdx register
	mov [hours], rdx			; move value of rdx to hours



exit_here:
	;exit code
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall