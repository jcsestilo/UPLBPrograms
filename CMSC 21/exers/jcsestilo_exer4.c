#include <stdio.h>
#include<string.h>

int isPalindrome(char uI[30], int length){ // Palindrome checker function
    
    for (int i = 0; i < length; i++)
    {
        if(uI[i]==uI[length-(i+1)]) continue; // If certain character = katapat niya sa hulihan, next iteration
        else return 0; // If may maencounter na di equal, end the function
 
    }
    return 1; // If natapos na ang loop tapos equal lahat, return 1

}

int main(){
    char userInput[30];
    int length;

    do{
        printf("-----PALINDROME CHECKER-----\n");
        printf("What word do you want to check? ");
        scanf("%s", userInput); // Get user input

        length = strlen(userInput); // Get length of the string
        
        for (int i = 0; i < length; i++) // Loop throughout the whole word, check each character
        {
            if (userInput[i] >= 'a' && userInput[i] <= 'z') userInput[i] = userInput[i] - 32;
            // If small character, ASCII value - 32 para maging capital
        }
        
        if(strcmp(userInput, "EXIT")==0){ // String compare function. If = EXIT, break the loop
            break;
        }
        
        if(isPalindrome(userInput, length)==1) printf("%s is a palindrome!", userInput);
        // If nireturn nung isPalindrome is 1, print na palindrome siya
        else printf("%s is not a palindrome!", userInput); // Else, hindi siya palindrome

        printf("\n\n"); // Format
    } while(1); // Keeps looping hanggat hindi nabebreak

    return 0;
}