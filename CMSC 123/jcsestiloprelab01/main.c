#include "list.h"
#include <stdio.h>


int main(){

	char command;
	int index, value, result;

	LIST *L = createList();

	while(1){
		scanf("%c ", &command);

		switch(command){
		/*	case 'a':
				scanf("%d\n", &value);
				printf("Appending %d as new tail node\n", value);
				appendTail(L, createNode(value));
				break;
		*/		
			case 'i':
				scanf("%d\n", &value);
				printf("Inserting %d as new head node\n", value);
				insertHead(L, createNode(value));
				break;
			case 'd':
				printf("Deleting the head node\n");
				result = deleteHead(L); // result is unused. print if u want
				printf("I have deleted: %d\n", result);
				break;
			case 'p':
				printf("List: ");
				show(L);
				printf("\n");
				break;
			case 'E':
				printf("List %s empty.\n", isEmpty(L)?"is":"is not");
				break;
			case 'C':
				printf("Deleting all contents.\n");
				clear(L);
				break;
			case 'Q':
				return 0;
			default:
				printf("Unknown command: %c\n", command);
		}
	}

	return 0;
}
