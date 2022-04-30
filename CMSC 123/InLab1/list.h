#ifndef _LIST_H_
#define _LIST_H_

typedef struct node_tag{
	int value;
	struct node_tag* next;
}NODE;


typedef struct list_tag{
	NODE* head;
	NODE* tail;
	int length;
	int maxLength;
}LIST;

/*
** requirements: none
** results:
	if list is empty, prints "*empty*"
	otherwise, prints the contents of a list
*/

void show(LIST *L);

/*
** chkalloc requirements:
	a pointer
** results:
	checks if the pointer is NULL,
	if it is NULL, the this function will exit the program.
*/
void chkalloc(void *p);

/*
** createNode requirements:
	an integer data
** results:
	creates an empty node with value `data`
	initializes fields of the structure
	returns the created node
*/
NODE* createNode(int data);


/*
** createList requirements: none
** results:
	creates an empty list
	initializes `head` field of the structure
	returns the created list
*/
LIST* createList();


/*
** isEmpty requirements: none
** results:
	returns 1 if the list is empty; 
	otherwise return 0
*/
int isEmpty(LIST *L);

/*
** requirements:
	List must not be full.

** results: inserts `node` before the current `head` of the list.

** sample: L = [12, 6, 4, 10]
** appendTail(L, createNode(55)) -> L = [12, 6, 4, 10, 55]
** appendTail(L, createNode(44)) -> L = [12, 6, 4, 10, 55, 44]
** appendTail(L, createNode(66)) -> L = [12, 6, 4, 10, 55, 44, 66]
*/
void appendTail(LIST *L, NODE* node);

/*
** requirements:
	List must not be full.

** results: inserts `node` before the current `head` of the list.

** sample: L = [12, 6, 4, 10]
** insertHead(L, createNode(55)) -> L = [55, 12, 6, 4, 10]
** insertHead(L, createNode(44)) -> L = [44, 55, 12, 6, 4, 10]
** insertHead(L, createNode(66)) -> L = [66, 44, 55, 12, 6, 4, 10]
*/
void insertHead(LIST *L, NODE* node);

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
int deleteHead(LIST *L);

/*
** requirements: list is not empty
** results:
	deletes all nodes of the list the list
*/
void clear(LIST *L);


#endif
