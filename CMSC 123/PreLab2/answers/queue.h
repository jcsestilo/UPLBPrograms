#ifndef _QUEUE_H_
#define _QUEUE_H_

#include "list.h"

/*
	a `QUEUE` is a list where insertion is done
	on one end of the list (e.g. tail) and deletion
	is done on the other end (e.g. head)
*/
typedef LIST QUEUE;

/*
** function: createQueue
** requirements:
	none
** results:
	creates an empty queue with specified max length
	initializes fields of the structure
	returns the created queue
*/
QUEUE* createQueue();

/*
** function: enqueue
** requirements:
	queue must not be full.

** results: appends `node` after the tail of the list.

** sample: L = [12, 6, 4, 10]
** enqueue(L, createNode(55)) -> L = [12, 6, 4, 10, 55]
** enqueue(L, createNode(44)) -> L = [12, 6, 4, 10, 55, 44]
** enqueue(L, createNode(66)) -> L = [12, 6, 4, 10, 55, 44, 66]
*/
void enqueue(QUEUE *Q, NODE* node);

/*
** function: dequeue
** requirements:
	queue must not be empty.
** results:
	deletes node at position head of the list
	returns the value of the deleted node.
** sample: L = [44, 12, 6, 55, 4, 10, 66]
** dequeue(L) -> L = [12, 6, 55, 4, 10, 66]; returns 44
** dequeue(L) -> L = [6, 55, 4, 10, 66]; return 12
** dequeue(L) -> L = [55, 4, 10, 66]; returns 6
*/
int dequeue(QUEUE *Q);


#endif
