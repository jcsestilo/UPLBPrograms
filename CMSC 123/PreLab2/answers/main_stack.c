#include "stack.h"
#include <stdio.h>


int main(){

	char command;
	int value, result;

	STACK *S = createStack(4);

	while(1){
		scanf("%c ", &command);

		switch(command){
			case '+':
				scanf("%d\n", &value);
				printf("Pushing %d\n", value);
				push(S, createNode(value));
				break;
			case '-':
				printf("Popping\n");
				result = pop(S); // result is unused. print if u want
				break;
			case 'p':
				printf("Stack: ");
				show(S);
				printf("\n");
				break;
			case 'E':
				printf("Stack %s empty.\n", isEmpty(S)?"is":"is not");
				break;
			case 'C':
				printf("Popping all contents.\n");
				clear(S);
				break;
			case 'Q':
				return 0;
			default:
				printf("Unknown command: %c\n", command);
		}
	}

	return 0;
}