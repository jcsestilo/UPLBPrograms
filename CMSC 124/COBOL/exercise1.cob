      IDENTIFICATION DIVISION.
      PROGRAM-ID. exercise1.

      ENVIRONMENT DIVISION.

      DATA DIVISION.
       WORKING-STORAGE SECTION.
           77 EXITED PIC 9 VALUE 0.
           77 CHOICE PIC 9.
	
      PROCEDURE DIVISION.
       PERFORM PMENU UNTIL EXITED = 6.
       STOP RUN.
       PMENU.
       DISPLAY " MENU ".
       DISPLAY " [1] Fill Array ".
       DISPLAY " [2] Print Array ".
       DISPLAY " [3] Find the Duplicate ".
       DISPLAY " [4] Largest Odd Number ".
       DISPLAY " [5] Palindrome Checker ".
       DISPLAY " [6] Exit ".
       DISPLAY " Choice: " WITH NO ADVANCING.
       ACCEPT CHOICE.
       IF CHOICE = 1
       ELSE
           IF CHOICE = 2
           ELSE
               IF CHOICE = 3
               ELSE
                   IF CHOICE = 4
                   ELSE
                       IF CHOICE = 5
                       ELSE
                           IF CHOICE = 6
                               DISPLAY " Bye! "
                               MOVE 6 TO EXITED
                           ELSE
                               DISPLAY " Invalid Choice. Try again! ".
                           END-IF
                       END-IF
                   END-IF
               END-IF
           END-IF
       END-IF.