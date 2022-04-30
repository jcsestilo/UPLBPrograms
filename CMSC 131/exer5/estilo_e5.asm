global _start
global repeated_subtraction
section .data
	SYS_EXIT 	equ 	60
	dividend 	dq		4
	divisor 	dq		2
	quo			dq		0

section .text
_start:
	mov rdi, dividend		; move dividend to 1st argument
	mov rsi, divisor		; move divisor to 2nd argument
	mov rax, quo

	mov r15, qword[divisor]	; if dividend < divisor, end program immediately
	cmp qword[dividend], r15
	jb exit_here

	call repeated_subtraction
	mov qword[quo], rax			; move return value of rax to quo

exit_here:
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall

repeated_subtraction:
	push rbp ; preserve the value of the rbp
	mov rbp, rsp ; store initial value of rsp to rbp
	sub rsp, 8 ; create local variable of 8 bytes
	mov qword[rbp-8],0 ; initial value of quotient, put to stack

	mov r10, qword[rsi] ; r10 = rsi
	mov r11, qword[rdi]	; r11 = rdi
	; start repeated subtraction
	jmp loop_subtract 	; jump to loop

loop_subtract:
	cmp r11, 0
	je finished		; if rdi[dividend] <= rsi[divisor], go to finished
	sub r11, r10	; subtract dividend to divisor
	inc qword[rbp-8]	; increment local stack variable
	jmp loop_subtract	; another loop
finished:				; if loop is done
	pop rax			; move local stack variable to rax
	pop rbp
	ret
	
