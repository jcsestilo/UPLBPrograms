#include "stack.h"
#include <stdio.h>
#include <string.h>
#include <ctype.h>

int postfix(char *postfixString){
	STACK *S = createStack(); //uncomment to create stack
	int i,n=strlen(postfixString);
	int ch;

    //uncomment this block comment to check some useful loops
	printf("check characters from the postfixString\n");
	for(i=0;i<n;i++){
		ch=postfixString[i];
		printf("char code:%i character:\'%c\'  int value:%i\n", ch, ch, ch-'0');
	}

	printf("\ncheck digits from the postfixString\n");
	for(i=0;i<n;i++){
		ch=postfixString[i];
		if(isdigit(ch)){ //check if ch is a digit (from ctype.h)
			printf("char code:%i character:\'%c\'  int value:%i\n", ch, ch, ch-'0');
		}
	}


	//In-Lab Exercise: Fill the missing codes

	// looping throughout the string
	int C=0;
	for(i=0;i<n;i++){
		ch = postfixString[i];
		
		if(isdigit(ch)){ // if it is a digit, push it to the stack
			push(S, createNode(ch-'0'));
		} else{ // if it is not a digit

			// pop two elements from stack
			int A = pop(S);
			int B = pop(S);
			//printf("A: %i\tB: %i\n", A, B);

			// use the operator character for the two popped elements
			switch(ch){
				case '+':
					C = B + A;
					break;
				case '-':
					C = B - A;
					break;
				case '*':
					C = B * A;
					break;
				case '/':
					C = B / A;
					break;
			}

			// after doing the operation, push it to the stack
			push(S,createNode(C));
		}
	}
	
    return (pop(S)); // will return the result after loop ends
}

int main(){

	//char postfixString[100];
	char *postfixString="62/12+*";
	int i;



	//printf("Enter Postfix expr: ");
	//scanf("%s",postfixString);

	printf("the postfix value of %s = %i\n\n",postfixString, postfix(postfixString));

}
