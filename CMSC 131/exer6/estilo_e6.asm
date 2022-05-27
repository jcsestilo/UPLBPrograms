; References:
; https://stackoverflow.com/questions/11497966/assembly-bubble-sort-swap
; https://stackoverflow.com/questions/54271774/sorting-a-string-in-alphabetical-order-using-assembly
; https://stackoverflow.com/questions/30427394/bubble-sort-on-array-on-assembly-language
; https://stackoverflow.com/questions/15995696/how-to-create-nested-loops-in-x86-assembly-language

global _start
global get_strlen

section .data
    NULL        equ 0
    SYS_EXIT    equ 60
    string1     db  'bored girl', NULL
    string2     db  'robed girl', NULL
    strlen      dq  0
    anagram     db  'N'

section .text
_start:
    mov rdi, string1    ; (1st) move string1 address to rdi
    mov rsi, strlen     ; (2nd) move strlen address to rsi
    call get_strlen     ; get the length of the string
    
    ; reset rdi and rsi
    mov rdi, 0
    mov rsi, 0

    ; sorting the first string
    ; rdi=strlen
    mov rdi, qword[strlen]   ; 1st argument, inner loop count
    ; rsi=strlen-1
    mov rsi, qword[strlen]
    dec rsi                 ; 2nd argument, outer loop count
    mov rdx, string1        ; 3rd argument, address of string1 first character
    call sort_strings

    ; reset rdi, rsi, rdx
    mov rdi, 0
    mov rsi, 0
    mov rdx, 0

    ; sorting the second string
    ; rdi=strlen
    mov rdi, qword[strlen]   ; 1st argument, inner loop count
    ; rsi=strlen-1
    mov rsi, qword[strlen]
    dec rsi                 ; 2nd argument, outer loop count
    mov rdx, string2        ; 3rd argument, address of string1 first character
    call sort_strings

    ; time to check if the twi strings are equal
    mov rcx, qword[strlen]
    mov rsi, string1
    mov rdi, string2
    cld     ; compare function will move FORWARD
    call check_anagram

    ; after returning, move to anagram the value of al
    mov byte[anagram], al

exit_here:
    mov rax, SYS_EXIT
    xor rdi, rdi
    syscall

get_strlen:
    len_loop:
        mov al, byte[rdi]   ; move value of rdi to al
        cmp al, NULL        ; If al==NULL, return
        je return
        
        inc rdi             ; next character in rdi
        inc byte[rsi]
        jmp len_loop        ; repeat another loop
    return:
        ret

sort_strings:
    push r12            ; push r12 to stack, will be used as the temporary storage of the string address
    mov r10, 0        ; r10=outer loop counter
    outer_loop:
        cmp r10, rsi    ; if r10=rsi, go to label done
        je done
        mov r11, 0    ; else, r11=inner loop counter
        mov r12, rdx  ; r12=first character of string
    inner_loop:
        cmp r11, rdi    ; if r11=rdi, go to label inner_loopDone
        je inner_loopDone

        ; reset the value of al and bl
        mov al, 0
        mov bl, 0

        ; sorting each letters
        mov al, [r12]       ; value of r12 character move to al
        mov bl, [r12+1]     ; else, value of r12 character+1 move to bl
        cmp bl, NULL        ; end of string?
        je inner_loopDone   ; yes -> exit inner loop

        cmp al,bl           ; bl character <= al character?
        jbe inner_loop_2    ; yes -> go to inner_loop_2
        mov [r12], bl       ; else, swap the position of the two letters
        mov [r12+1], al

    inner_loop_2:
        inc r12         ; next character of the string
        inc r11         ; increment r11
        jmp inner_loop  ; another inner loop

    inner_loopDone:
        inc r10         ; for outer loop, increment r10
        jmp outer_loop  ; another outer loop
    done:
        pop r12         ; pop r12 from stack
        ret

check_anagram:
    loop1:
        cmpsb   ; compare rdi and rsi strings, going forward
        jne not_equal   ; if one character is not equal, go to not_equal
        loop loop1      ; else, another loop. when loop is done, will move to is_equal
    is_equal:
        ; if equal, change anagram marker and return
        mov al, 'Y'
        ret
    not_equal:
        mov al, 'N'
        ret




