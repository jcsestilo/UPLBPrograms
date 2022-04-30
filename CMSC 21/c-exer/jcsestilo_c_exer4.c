/*
Jan Coleen Estilo
Contributors: Nathaniel Enciso, Jan Andrew Germinal, Kyla Julienne Macayan

Your program should be able to:
1. Scan for the two words to be checked
2. Pass those two words to a function called areAnagrams which returns an int to be
interpreted in the main() whether the given words are anagrams or not.
3. The program should loop indefinitely until the words "EXIT NOW" is used as input.
*/

#include <stdio.h>
#include <string.h>


int areAnagrams(char str1[30], char str2[30]){ // return 0 = not anagrams, return 1 anagrams
    char str1copy[30], str2copy[30], *p; // Initialize variables for copies and 1 pointer

    // If length of str1 is not equal to length of str2, automatically not anagrams.
    if(strlen(str1) != strlen(str2)) return 0; 

    //strcpy(str1copy, str1); // Copies contents from str1 to str1copy
    //strcpy(str2copy, str2); // Copies contents from str2 to str2copy

    for (int i = 0; i < strlen(str1); i++) {
        // strrchr gets the character of the last occurence of str1copy[i]
        p = strrchr(str2, str1[i]); // Checks if character sa str1copy ay nasa str2copy

        if (p == NULL) return 0; // If walang nahanap na character na yun, automatic not anagram
        p = NULL;
    }

    return 1; // If di nagreturn 0 and nag-end na yung loop, matic anagram, return 1.
    
}

int main(){
    char str1[30], str2[30];

    while(1){

        // Getting the input from the user
        printf("Enter two strings: ");
        scanf("%s %s", str1, str2);

        if ((strcmp(str1, "EXIT") == 0) && (strcmp(str2, "NOW") == 0)) return 0; // Checker for exiting

        // Dito babalik si areAnagrams
        if (areAnagrams(str1, str2) == 0) printf("%s and %s are NOT anagrams!\n\n", str1, str2);
        else if (areAnagrams(str1, str2) == 1) printf("%s and %s are anagrams!\n\n", str1, str2);

    }

}