global _start

section.data
	SYS_EXIT		equ	60
	x 				db	25
	y 				db	5
	isEqual			db	"N"

section .text
_start:

	mov al, byte[x]					; x != y
	cmp al, byte[y]
	jne exit_here

	mov byte[isEqual], "Y"			; isEqual = "Y"

exit_here:
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall