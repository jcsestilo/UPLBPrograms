global _start

section .data
	SYS_EXIT			equ		60
	largestNum 			db		0
	largestInput 		db		""

	bNumA				db		15
	bNumB				db		7
	bNumC				db		21

section .text
_start:
	
	; compare bNumA and bNumB
	jmp compare_AB						; jump to compare_AB label

	; mov al, byte[bNumA]				; x != y
	; cmp al, byte[bNumB]
	; jne exit_here

	; mov byte[isEqual], "Y"			; isEqual = "Y"

compare_AB:
	mov al, byte[bNumA]					; move value of bNumA to al register
	cmp al, byte[bNumB]					; compare bNumA with bNumB
	ja compare_AC						; if A > B, go to label that compares A and C
	jmp compare_BC						; if A < B, go to label that compares B and C

compare_AC:
	mov al, 0 							; reset the value of al
	mov al, byte[bNumA]					; move the value of bNumA to al register
	cmp al, byte[bNumC]					; compare bNumA with bNumC
	ja largest_A						; if A > C, jump to the label largest_A
	jmp largest_C						; else, jump to the label largest_C

compare_BC:
	mov al, 0 							; reset the value of al
	mov al, byte[bNumB]					; move value of bNumB to al register
	cmp al, byte[bNumC]					; compare bNumB with bNumC
	ja largest_B						; if B > C, jump to the label largest_B
	jmp largest_C						; else, if B < C, jump to the label largest_C

largest_A:
	mov al, 0 							; reset the value of al register
	mov al, byte[bNumA]					; move the value of bNumA to al register
	mov byte[largestNum], al 			; move the value of al register to largestNum memory variable
	mov byte[largestInput], "A" 		; make the value of largestInput "A"
	jmp exit_here 						; jump to the exit code

largest_B:
	mov al, 0 							; reset the value of al register
	mov al, byte[bNumB]					; move the value of bNumB to al register
	mov byte[largestNum], al 			; move the value of al register to largestNum memory variable
	mov byte[largestInput], "B" 		; make the value of largestInput "B"
	jmp exit_here 						; jump to the exit code

largest_C:
	mov al, 0 							; reset the value of al register
	mov al, byte[bNumC]					; move the value of bNumC to al register
	mov byte[largestNum], al 			; move the value of al register to largestNum memory variable
	mov byte[largestInput], "C" 		; make the value of largestInput "C"
	jmp exit_here 						; jump to the exit code


exit_here:
	mov rax, SYS_EXIT
	xor rdi, rdi
	syscall