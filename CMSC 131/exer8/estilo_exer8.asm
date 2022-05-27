global _start

section .data
	menu db 10, "[1] Add Patient", 10, "[2] Edit Patient", 10, "[3] Print Patients", 10, "[4] Exit", 10, "Enter choice: "
	menuLength equ $-menu

	invalidChoice db 10, "Invalid choice!", 10
	invalidChoiceLength equ $-invalidChoice

	fullPrompt db "Record is already full!", 10
	fullPromptLength equ $-fullPrompt

	addCase db 10, "Enter caseID: "		;Use this prompt for add and edit
	addCaseLength equ $-addCase

	addSex db "Enter sex (F - Female, M - Male): "
	addSexLength equ $-addSex

	addStatus db "Enter status (0 - deceased, 1 - admitted, 2 - recovered): " ;Use this prompt for add and edit
	addStatusLength equ $-addStatus

	addDate db "Enter date admitted (mm/dd/yyyy): "
	addDateLength equ $-addDate

	printCase db 10, "CaseID: "
	printCaseLength equ $-printCase

	printSex db 10, "Sex: "
	printSexLength equ $-printSex

	printStatus db 10, "Status: "
	printStatusLength equ $-printStatus

	printDate db 10, "Date Admitted: "
	printDateLength equ $-printDate

	cannotEdit db "Cannot edit records of a deceased patient.", 10
	cannotEditLength equ $-cannotEdit

	cannotFind db "Patient not found!", 10
	cannotFindPrompt equ $-cannotFind

	updateSuccessful db "Updating status successful", 10
	updateSuccessfulLength equ $-updateSuccessful

	newLine db 10
	newLineLength equ $-newLine
	
	patientCnt			db		0		; patient counter
	inputMenu			db		0		; menu input
	; array size
	arraysize			equ		5
	; structure variable
	patient_record		equ		35		; total size of the structure
	caseID 				equ		0		; starting address of caseID
	cIDLength 			equ 	20
	sex 				equ 	21		; starting address of sex
	status 				equ  	22		; starting address of status
	date 				equ 	23		; starting address of date
	dateLength 			equ 	34

section .bss
	patient resb patient_record*arraysize	; array structure size=size of one structure*array size
	inputCaseID resb cIDLength				; reserve a variable for the case id input in edit patient
	inputCaseIDLength resb 1				; reserve a variable for the length of case id input in edit patient

section .text
_start:
	
	menuLoop:
		cmp byte[inputMenu], 4	; if user input==4, exit program
		je exit_here
		
		; reset inputMenu variable
		mov byte[inputMenu], 0

		; display menu
		mov rax, 1
		mov rdi, 1
		mov rsi, menu
		mov rdx, menuLength
		syscall

		; get input from the user on menu
		mov rax, 0
		mov rdi, 0
		mov rsi, inputMenu
		mov rdx, 2
		syscall

		; convert menu input from string to integer
		sub byte[inputMenu], 30h

		; ---- MENU INPUT CONDITIONS -----
		cmp byte[inputMenu], 1		; if input==add patient,
		je addPatient

		cmp byte[inputMenu], 2			; if input==edit patient,
		je editPatient

		cmp byte[inputMenu], 3			; if input==print patients,
		je printPatients

		cmp byte[inputMenu], 4
		je exit_here

		; if invalid choice
		mov rax, 1
		mov rdi, 1
		mov rsi, invalidChoice
		mov rdx, invalidChoiceLength
		syscall
		jmp menuLoop
		; -------------------------

	addPatient:
		cmp byte[patientCnt], 5		; if patientCnt==5, prompt that records are full
		je recordsFull

		; else,
		; GETTING THE INDEX: patientCnt*patient_record
		mov rax, patient_record
		mul byte[patientCnt]
		mov r10, rax			; r10 = patientCnt * patient_record

		; start getting inputs
		; display case id instructions
		mov rax, 1
		mov rdi, 1
		mov rsi, addCase
		mov rdx, addCaseLength
		syscall

		; INPUT: CASE ID
		mov rax, 0
		mov rdi, 0
		lea rsi, [patient+r10+caseID]
		mov rdx, 20				; size of caseID is 20
		syscall

		; remove newline from caseid
		dec rax
		mov [patient+r10+cIDLength], al

		; display sex instructions
		mov rax, 1
		mov rdi, 1
		mov rsi, addSex
		mov rdx, addSexLength
		syscall

		; INPUT: SEX
		mov rax, 0
		mov rdi, 0
		lea rsi, [patient+r10+sex]
		mov rdx, 2				; size of sex is 1
		syscall

		; display status instructions
		mov rax, 1
		mov rdi, 1
		mov rsi, addStatus
		mov rdx, addStatusLength
		syscall

		; INPUT: STATUS
		mov rax, 0
		mov rdi, 0
		lea rsi, [patient+r10+status]
		mov rdx, 2				; size of status is 1
		syscall

		; display date instructions
		mov rax, 1
		mov rdi, 1
		mov rsi, addDate
		mov rdx, addDateLength
		syscall

		; INPUT: DATE
		mov rax, 0
		mov rdi, 0
		lea rsi, [patient+r10+date]
		mov rdx, 11				; size of date is 11
		syscall

		inc byte[patientCnt]	; increment patient counter
		jmp menuLoop			; another menu loop
		
	recordsFull:
		; display that records already full
		mov rax, 1
		mov rdi, 1
		mov rsi, fullPrompt
		mov rdx, fullPromptLength
		syscall
		jmp menuLoop	; go back to the menu

	editPatient:
		; display case id instructions
		mov rax, 1
		mov rdi, 1
		mov rsi, addCase
		mov rdx, addCaseLength
		syscall

		; INPUT: CASE ID
		mov rax, 0
		mov rdi, 0
		lea rsi, [inputCaseID]
		mov rdx, 20				; size of caseID is 20
		syscall

		; remove newline from inputCaseID
		dec rax
		mov [inputCaseIDLength], al

		mov r12, 0
	searchLoop:
		; GETTING THE INDEX: patientCnt*patient_record
		mov rax, 0				; reset rax value
		mov rax, patient_record
		mul r12
		mov r10, rax			; r10 = r12 * patient_record

		; compare the length of the inputCaseID and the caseID of the index
		mov r11b, [inputCaseIDLength]
		cmp [patient+r10+cIDLength], r11b		; if the length is not the same, automatically next iteration
		jne nextSearchLoop

		; reset values just in case
		mov rcx, 0
		mov rsi, 0
		mov rdi, 0

		; compare the two strings
		mov rcx, [inputCaseIDLength]
		lea rsi, [patient+r10+caseID]
		mov rdi, inputCaseID
		cld ; forward

	stringsCmp:
		cmpsb
		jne nextSearchLoop
		loop stringsCmp

	patientFound:
		cmp byte[patient+r10+status], "0" ; if patient is deceased, cannot edit
		je deceasedPatient

		; display status instructions
		mov rax, 1
		mov rdi, 1
		mov rsi, addStatus
		mov rdx, addStatusLength
		syscall

		; else, INPUT: STATUS
		mov rax, 0
		mov rdi, 0
		lea rsi, [patient+r10+status]
		mov rdx, 2				; size of status is 1
		syscall

		; display update successful
		mov rax, 1
		mov rdi, 1
		mov rsi, updateSuccessful
		mov rdx, updateSuccessfulLength
		syscall

		jmp menuLoop	; jump to menuLoop

	nextSearchLoop:
		cmp r12b, byte[patientCnt]	; if we have went through all the patients, patient not found
		je patientNotFound
		inc r12			; increment r12 for the next index
		jmp searchLoop	; another search loop
	
	deceasedPatient:
		; display deceased patient
		mov rax, 1
		mov rdi, 1
		mov rsi, cannotEdit
		mov rdx, cannotEditLength
		syscall

		jmp menuLoop	; go back to the menu

	patientNotFound:
		; display patient not found prompt
		mov rax, 1
		mov rdi, 1
		mov rsi, cannotFind
		mov rdx, cannotFindPrompt
		syscall

		jmp menuLoop		; go back to the menu
	printPatients:
		mov r12, 0 					; r12 is the temporary index counter
	loop1:
		cmp r12b, byte[patientCnt]	; if r11b==patient count, end loop
		je menuLoop

		; GETTING THE INDEX: patientCnt*patient_record
		mov rax, 0				; reset rax value
		mov rax, patient_record
		mul r12
		mov r10, rax			; r10 = r12 * patient_record

		; "Case id:"
		mov rax, 1
		mov rdi, 1
		mov rsi, printCase
		mov rdx, printCaseLength
		syscall
		; print patient case id
		mov rax, 1
		mov rdi, 1
		lea rsi, [patient+r10+caseID]
		mov dl, [patient+r10+cIDLength]
		syscall
		
		; "Sex:"
		mov rax, 1
		mov rdi, 1
		mov rsi, printSex
		mov rdx, printSexLength
		syscall
		; print patient sex
		mov rax, 1
		mov rdi, 1
		lea rsi, [patient+r10+sex]
		mov dl, 1				; size of sex is 1
		syscall

		; "Status:"
		mov rax, 1
		mov rdi, 1
		mov rsi, printStatus
		mov rdx, printStatusLength
		syscall
		; print patient status
		mov rax, 1
		mov rdi, 1
		lea rsi, [patient+r10+status]
		mov dl, 1				; size of status is 1
		syscall

		; "Date:"
		mov rax, 1
		mov rdi, 1
		mov rsi, printDate
		mov rdx, printDateLength
		syscall
		; print patient date
		mov rax, 1
		mov rdi, 1
		lea rsi, [patient+r10+date]
		mov dl, 11			; size of date is 11
		syscall

		inc r12		; increment r12 for the next index
		jmp loop1		; another loop

exit_here:
	mov rax, 60
	xor rdi, rdi
	syscall