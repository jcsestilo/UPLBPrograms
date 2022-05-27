; Reference:
; https://stackoverflow.com/questions/19461476/convert-string-to-int-x86-32-bit-assembler-using-nasm
; https://stackoverflow.com/questions/13523530/printing-an-int-or-int-to-string

global _start

section .data
    LF equ 10   ; line feed (newline)
    NULL equ 0  ; end of string
    STDOUT equ 1
    SYS_WRITE equ 1
    STDIN equ 0
    SYS_READ equ 0

    newLine db LF, NULL
    newLineLength equ $-newLine

    menu db 10, "************ MENU ***********", 10, "[1] Convert to Minutes", 10, "[2] Convert to Hours", 10, "[0] Exit", 10, "**************************", 10, "Choice: ", NULL
    menuLength equ $-menu

    divisorTable:
        dd 1000
        dd 100
        dd 10
        dd 1
        dd 0
    
    converter10 dd 10

    enterSeconds db 10, "Enter time in seconds (5-digits): ", NULL
    enterSecondsLength equ $-enterSeconds

    inputChoice db "0"
    inputSeconds dd 0
    result dd 0
section .text
_start:
    menuLoop:
        ; print menu
        mov rax, SYS_WRITE
        mov rdi, STDOUT
        mov rsi, menu
        mov rdx, menuLength
        syscall

        ; get choice input
        mov rax, SYS_READ
        mov rdi, STDIN
        mov rsi, inputChoice
        mov rdx, 2
        syscall

        ; ---- MENU INPUT CONDITIONS ----
        cmp byte[inputChoice], "0"  ; if user picks exit
        je exit_here

        ; else, get seconds user input

    getSeconds:
        ; print get seconds instructions
        mov rax, SYS_WRITE
        mov rdi, STDOUT
        mov rsi, enterSeconds
        mov rdx, enterSecondsLength
        syscall

        ; get seconds input
        mov rax, SYS_READ
        mov rdi, STDIN
        mov rsi, inputSeconds
        mov rdx, 6      ; 5 digits + \n
        syscall

        ; convert seconds input from string to integer
        ; jump to fromStringToInt

    fromStringToInt:
        mov edx, inputSeconds ; input string
        atoi:
            xor eax, eax    ; eax = 0
        top:
            movzx ecx, byte[edx]  ; get a character, the left part of ecx will become 0
            inc edx         ; go to the next character for the next loop
            
            cmp ecx, '0'        ; if encounters a character, loop is done
            jb done
            cmp ecx, '9'
            ja done

            sub ecx, '0'        ; convert character to number
            imul eax, 10        ; multiply eax by 10
            add eax, ecx        ; add in current digit
            jmp top             ; another loop
        done:
            mov dword[inputSeconds], eax    ; move the result to inputSeconds

            cmp byte[inputChoice], "1"
            je toMinutes

            cmp byte[inputChoice], "2"
            je toHours

    fromIntToString:
        xor eax, eax    ; make eax value 0
        mov eax, dword[result]  ; move to eax the value of the result
        xor ebx, ebx    ; make ebx value 0
        mov ebx, divisorTable
        convertCharacter:

            xor edx, edx    ; make edx value 0
            div dword[ebx]  ; divide eax by divisorTable, eax= quotient, edx=remainder

            add eax, '0'    ; 
            mov dword[result], 0
            mov dword[result], eax

            mov eax, edx    ; the remainder will be the one to be divided in the next loop

            push rax        ; preserve the value of eax and edx since we will change it for printing
            push rdx
            
            ; print the quotient (one character only)
            mov rax, SYS_WRITE
            mov rdi, STDOUT
            mov rsi, result
            mov rdx, 1
            syscall

            pop rdx         ; after printing, get the preserved value of edx and eax
            pop rax 

            add ebx, 4      ; go to the next double word in divisorTable, 4 because 4 bytes

            cmp dword[ebx], 0      ; all digits divided?
            jne convertCharacter    ; while not equal, convert characters

            jmp menuLoop            ; else, go back to menu
    toMinutes:
        ; reset rdx, rax, and rbx(register for divisor) value
        mov rdx, 0
        mov rax, 0
        mov rbx, 0

        mov eax, dword[inputSeconds]    ; move inputSeconds to eax
        mov ebx, 60  ; divisor

        div ebx      ; eax:edx = edx:eax / 60

        mov dword[result], eax    ; move to variable result the quotient 

        ; convert result from integer to string
        jmp fromIntToString
    
    toHours:
        ; reset rdx, rax, and rbx(register for divisor) value
        xor rdx, rdx
        xor rax, rax
        xor rbx, rbx

        mov eax, dword[inputSeconds]    ; move inputSeconds to eax
        mov ebx, 3600     ; divisor

        div ebx     ; eax:edx = edx:eax / 3600

        mov dword[result], eax  ; move to variable result the quotient

        ; convert result from integer to string
        jmp fromIntToString
    

exit_here:
    mov rax, 60
    xor rdi, rdi
    syscall