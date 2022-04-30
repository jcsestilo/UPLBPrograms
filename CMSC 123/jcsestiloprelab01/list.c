#include "list.h"
#include <stdio.h>
#include <stdlib.h>

// include other headers you need

// implement each function in list.h here

// routine for printing a list is given already.
/*
** requirements: none
** results:
	if list is empty, prints "*empty*"
	otherwise, prints the contents of a list
*/

void show(LIST *L){
	if(isEmpty(L)){
		printf("*empty*");
	}else{
		for(NODE* node = L->head; node != NULL; node=node->next){
			printf("%2d", node->value);
		}
	}
}

/*
** chkalloc requirements:
	a pointer
** results:
	checks if the pointer is NULL,
	if it is NULL, the this function will exit the program.
*/
void chkalloc(void *p){
	if(p==NULL){
		printf("Error! Insufficient Memory.\n");
		exit(1);
	}
}


/*
** createNode requirements:
	an integer data
** results:
	creates an empty node with value `data`
	initializes fields of the structure
	returns the created node
*/
NODE* createNode(int data){
	//Pre-Lab Exercise: insert missing codes
	NODE *temp;
	temp = (NODE*)malloc(sizeof(NODE));
	temp->value=data;
	temp->next=NULL;
	return(temp);
}


/*
** createList requirements: none
** results:
	creates an empty list
	initializes `head` field of the structure
	returns the created list
*/
LIST* createList(){
	//Pre-Lab Exercise: insert missing codes
	LIST *L;
	L = (LIST *)malloc(sizeof(LIST));
	L->head=NULL;
	return (L);	
}


/*
** isEmpty requirements: none
** results:
	returns 1 if the list is empty; 
	otherwise return 0
*/
int isEmpty(LIST *L){
	return(L->head==NULL);
}

/*
** requirements:
	List must not be full.

** results: inserts `node` before the current `head` of the list.

** sample: L = [12, 6, 4, 10]
** insertHead(L, createNode(55)) -> L = [55, 12, 6, 4, 10]
** insertHead(L, createNode(44)) -> L = [44, 55, 12, 6, 4, 10]
** insertHead(L, createNode(66)) -> L = [66, 44, 55, 12, 6, 4, 10]
*/
void insertHead(LIST *L, NODE* node){
	//Pre-Lab Exercise: insert missing codes
	node->next=L->head;
	L->head=node;
}

/*
** requirements:
	List must not be empty.
** results:
	deletes the `head` node of the list
	returns the value of the deleted node.
** sample: L = [44, 12, 6, 55, 4, 10, 66]
** deleteHead(L) -> L = [12, 6, 55, 4, 10, 66]; returns 44
** deleteHead(L) -> L = [6, 55, 4, 10, 66]; return 12
** deleteHead(L) -> L = [55, 4, 10, 66]; returns 6
*/
int deleteHead(LIST *L){
	//Pre-Lab Exercise: insert missing codes
	NODE * toDel = L->head;
	int x=toDel->value;
	L->head = L->head->next;

	free(toDel);

	return(x);
}


/*
** requirements: list is not empty
** results:
	deletes all nodes of the list the list
*/
void clear(LIST *L){
	//Pre-Lab Exercise: insert missing codes
	NODE *node, *nxt;
	if(isEmpty(L)){
		printf("List is empty.");
	} else{
		node = L->head;
		while(node != NULL){
			nxt = node->next;
			free(node);
			node = nxt;
		}
		L->head=NULL;
		
	}
}

