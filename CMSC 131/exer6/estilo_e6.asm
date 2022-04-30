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

    ; rdi=strlen
    mov rdi, qword[strlen]   
    dec rdi                 ; 1st argument, inner loop count
    ; rsi=strlen-1
    mov rsi, qword[strlen]
    dec rsi                 ; 2nd argument, outer loop count
    mov rdx, string1        ; 3rd argument, address of string1 first character
    call sort_strings

exit_here:
    mov rax, SYS_EXIT
    xor rdi, rdi
    syscall

get_strlen:
    len_loop:
        mov al, byte[rdi]   ; move value of rdi to al
        cmp al, NULL        ; If al==NULL, return
        je return
        
        inc rdi             
        inc byte[rsi]
        jmp len_loop        ; repeat another loop
    return:
        ret

sort_strings:
    mov r10, 0        ; r10=outer loop counter
    outer_loop:
        cmp r10, rsi    ; if r10=rsi, go to label done
        je done
        mov r11, 0    ; else, r11=inner loop counter
    inner_loop:
        cmp r11, rdi    ; if r11=rdi, go to label inner_loopDone
        je inner_loopDone

        ; sorting each letters
        mov al, [rdx]       ; value of rdx character move to al
        mov ah, [rdx+1]     ; value of rdx character+1 move to ah
        cmp ah, NULL        ; end of string?
        je inner_loopDone   ; yes -> exit inner loop

        cmp ah,al           ; ah character <= al character?
        jbe inner_loop_2        ; yes -> go to inner_loop_2
        mov [rdx], ah       ; else, swap the position of the two letters
        mov [rdx+1], al

    inner_loop_2:
        inc rdx
        inc r11         ; else, increment r11
        jmp inner_loop  ; another inner loop

    inner_loopDone:
        inc r10         ; for outer loop, increment r10
        jmp outer_loop  ; another outer loop
    done:
        ret
        



