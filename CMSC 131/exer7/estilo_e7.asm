global _start
global get_arrlen
global get_largest

section .data
    NULL equ 0
    SYS_EXIT equ 60
    all_negative db 0
    num_arr dw 0, -2, -3, -4, -5
    len db 5
    largest dw -1

section .text
_start:
    mov rdi, num_arr ; (1st argument) rdi=first element of num_arr
    mov rsi, largest ; (2nd argument) address of largest move to rsi
    mov rdx, all_negative ; (3rd argument) address of all_negative move to rdx
    mov cl, byte[len]
    call get_largest

exit_here:
    mov rax, SYS_EXIT
    xor rdi, rdi
    syscall
get_largest:
    mov ax, [rdi]    ; largest = first element of num_arr
    mov [rsi], ax
    mov r10, 0      ; r10=counter of the loop
    dec rcx         ; rcx--
    loop1:
        cmp r10, rcx    ; if r10==rcx, jump to "done"
        je done

        mov r11w, [rdi+2*r10]

        cmp r11w, NULL
        je done

        cmp [rsi], r11w  ; if value[rsi]<value[rdi(r10th element)]
        jl next_large   ; then jump to next_large
        jmp loop1_1     ; else, jump to loop1_1

    next_large:
        mov [rsi], r11w    ; rsi (address of largest variable)=r11
    
    loop1_1:
        inc r10         ; r10++
        jmp loop1       ; another loop
    
    done:
        mov ax, 0
        cmp [rsi], ax    ; if value[rsi]<0
        jl all_is_negative  ; jump to all_is_negative
        jmp return          ; else, return

    all_is_negative:
        mov ax, -1
        mov [rsi], ax     ; largest = -1
        mov ax, 1
        mov [rdx], ax      ; all_negative = 1
    return:
        ret


