#ifndef _STACK_H_
#define _STACK_H_

#include "list.h"

/*
	a `STACK` is a list where insertion is done
	on one end of the list (e.g. tail) and deletion
	is done on the other end (e.g. head)
*/
typedef LIST STACK;

/*
** function: createStack
** requirements: 
	an integer parameter for the max length of this qtack
** results:
	creates an empty qtack with specified max length
	initializes fields of the structure
	returns the created qtack
*/
STACK* createStack();

/*
** function: push
** requirements:
	qtack must not be full.

** results: inserts `node` before the `head` of the list.

** sample: L = [12, 6, 4, 10]
** push(L, createNode(55)) -> L = [55, 12, 6, 4, 10]
** push(L, createNode(44)) -> L = [44, 55, 12, 6, 4, 10]
** push(L, createNode(66)) -> L = [66, 44, 55, 12, 6, 4, 10]
*/
void push(STACK *S, NODE* node);

/*
** function: pop
** requirements:
	stack must not be empty.
** results:
	deletes node at position head of the list
	returns the value of the deleted node.
** sample: L = [44, 12, 6, 55, 4, 10, 66]
** pop(L) -> L = [12, 6, 55, 4, 10, 66]; returns 44
** pop(L) -> L = [6, 55, 4, 10, 66]; return 12
** pop(L) -> L = [55, 4, 10, 66]; returns 6
*/
int pop(STACK *S);


#endif