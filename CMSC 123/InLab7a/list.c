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
	NODE *temp = (NODE *)malloc(sizeof(NODE));
	chkalloc(temp);
	temp->next=NULL;
	temp->value=data;
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
	LIST *temp = (LIST *)malloc(sizeof(LIST));
	chkalloc(temp);
	temp->head=NULL;
	temp->tail=NULL; //needed when appendTail is incorporated
	return(temp);	
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
** appendTail(L, createNode(55)) -> L = [12, 6, 4, 10, 55]
** appendTail(L, createNode(44)) -> L = [12, 6, 4, 10, 55, 44]
** appendTail(L, createNode(66)) -> L = [12, 6, 4, 10, 55, 44, 66]
*/
void appendTail(LIST *L, NODE* node){
	if (L->head == NULL){
		L->head=L->tail=node;
	}
	else{
		L->tail->next=node;
		L->tail=node;
	}
	//In-Lab Exercise: fill the missing codes
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
	if(isEmpty(L)) //In-Lab Exercise: fill the missing condition
		L->tail=node; //needed when appendTail is incorporated
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
	NODE *temp;
	int x = 0;
	if (!isEmpty(L)){
		temp=L->head;
		L->head=temp->next;
		x=temp->value;
		free(temp);
		if(L->head==NULL) //In-Lab Exercise: fill the missing condition
			L->tail=NULL; //needed when appendTail is incorporated
	}
	return(x);
}


/*
** requirements: list is not empty
** results:
	deletes all nodes of the list the list
*/
void clear(LIST *L){
	while(!isEmpty(L))
		deleteHead(L);
}

