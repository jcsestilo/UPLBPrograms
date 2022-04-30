// no need to include `list.h`
// since we are here to implement
// stack ADT

#include "stack.h"


STACK* createStack(){
	// since `STACK` is the same as the `LIST` structure
	// we can reuse the function `createList()` to create
	// a queue.
	return createList();
}

// implement other functions here

/*
** function: push
** requirements:
	stack must not be full.

** results: inserts `node` before the `head` of the list.

** sample: L = [12, 6, 4, 10]
** push(L, createNode(55)) -> L = [55, 12, 6, 4, 10]
** push(L, createNode(44)) -> L = [44, 55, 12, 6, 4, 10]
** push(L, createNode(66)) -> L = [66, 44, 55, 12, 6, 4, 10]
*/
void push(STACK *S, NODE* node){
	insertHead(S,node);
}

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
int pop(STACK *S){
	return(deleteHead(S));
}
