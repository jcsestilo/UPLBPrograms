#ifndef _HEAP_H_
#define _HEAP_H_

typedef struct heap_tag {
  int *heap;
  int size;
  int maxSize;
} HEAP;


/*
 *  PRINT HEAP
 *  requirements:
      a non-null HEAP pointer
      a non-empty *heap
 *  results:
    Printing the heap (rotated +90 degrees)...
*/

void printHeap(HEAP *H);

/*
 *  CREATE HEAP
 *  requirements: none
 *  results:
    initializes fields of the structure
    returns the created heap
*/
HEAP* createHeap(int maxSize);


/*
 *  IS FULL
 *  requirements: none
 *  results:
      return 1 if the heap is full;
      otherwise return 0
*/
int isFull(HEAP *H);


/*
 *  IS EMPTY
 *  requirements: none
 *  results:
      return 1 if the heap is empty;
      otherwise return 0
*/
int isEmpty(HEAP *H);

/*
 *  CLEAR
 *  requirements:
      a non-null HEAP pointer
      a non-empty *heap
 *  results:
    deletes all the items in the heap
*/
void clear(HEAP *H);


/*
 *  INSERT
 *  requirements:
      a non-null HEAP pointer
      a non-full *heap
 *  results:
      inserts `key` to the `heap`
*/
void insert(HEAP *H, int key);


/*
 *  DELETE M
 *  requirements:
      a non-null HEAP pointer
      a non-empty *heap
 *  results:
	  removes the maximum value (root) from the MAX HEAP 
      returns the deleted value
*/
int deleteM(HEAP *H);



#endif
