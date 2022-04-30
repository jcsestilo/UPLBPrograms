#include "closed_hashtable.h"
#include <stdlib.h>
#include <stdio.h>

//REMINDER: you are NOT allowed to change this file
int main(){

	char command;
	int key, result, i, tsize, hType;
	HASH_TABLE *H;


	// get table size then maxsize
	scanf("%d\n", &hType);
	scanf("%d\n", &tsize);
	H = createHashTable(tsize,hType);

	while(1){
		scanf("%c ", &command);
		switch(command){
			case '+':
				scanf(" %i\n", &key);
				printf("Inserting key %i\n", key);
				result=insert(H, key);
				if (result==-1) printf("Cannot Insert! Hash table is full.\n");
				else printf("Inserted key %i with %i collision(s)\n", key,result);
				break;
			case '?':
				scanf(" %i\n", &key);
				printf("Finding key %i\n", key);
				result = find(H, key);
				if(result == -1){
					printf("Key: %i not found!\n", key);
				}else{
					printf("Key: %i found in index: %d\n", key, result);
				}
				break;
			case 'p':
				printf("Hash Table: ");
				printTable(H);
				printf("\n");
				break;
			case 'E':
				printf("Hash table %s empty.\n", isEmpty(H)?"is":"is not");
				break;
			case 'F':
				printf("Hash table %s full.\n", isFull(H)?"is":"is not");
				break;
			case 'Q':
				destroy(&H);
				return 0;
			default:
				printf("Unknown command: %c\n", command);
		}
	}

	return 0;
}
