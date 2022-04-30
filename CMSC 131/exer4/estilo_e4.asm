global _start

section .data
	SYS_EXIT		equ	60
	num				dw	6
	isPrime			db	1

	loopCnt			db	0

section .text
_start:
	mov al, byte[num]		; loopCnt = num,  move num byte to al register
	mov byte[loopCnt], al
	dec byte[loopCnt]		; loopCnt = loopCnt - 1
	; jump to prime_loop

prime_loop:
	cmp byte[loopCnt], 1	; if loopCnt = 1
	je is_prime 			; jump to is_prime

	mov dx, 0 				; reset dx value
	mov ax, word[num] 		; move byte value of num to ax register
	div byte[loopCnt]		; quotient = ax:dx
	cmp dx, 0 				; check if the remainder is 0
	je is_not_prime			; jump to is_prime label if the remainder dx == 0
	dec byte[loopCnt] 		; loopCnt = loopCnt - 1
	jmp prime_loop			; else, repeat prime_loop

is_prime:
	mov byte[isPrime], 1	; isPrime = 1
	jmp exit_here			; go to exit

is_not_prime:
	mov byte[isPrime], 0 	; isPrime = 0
	jmp exit_here 			; go to exit

exit_here:
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall
