#include "heap.h"
#include <stdio.h>
#include <malloc.h>
#include <math.h>
#include <string.h>

/*IMPLEMENT THE FUNCTIONS HERE*/

//Helper functions

int left(int index){ //zero-indexing
  return(2*index+1); 
}

int right(int index){ //zero-indexing
  return(2*index+2); 
}

int parent(int index){ //zero-indexing
  return((index-1)/2); 
}

void swap(int *x, int *y){
	int temp;
	temp = *x;
	*x = *y;
	*y = temp;
}

void percolateup(HEAP *H,int index){ //called by INSERT
  if (index>0){
    if (H->heap[index]>H->heap[parent(index)]){
      swap(&(H->heap[index]),&(H->heap[parent(index)]));
      percolateup(H,parent(index));
    }
  }  
}

void percolatedown(HEAP *H,int index){ //called by DELETE
  if (right(index) < H->size){ //has valid children
    if ((H->heap[index] < H->heap[right(index)])&&
    	(H->heap[left(index)] < H->heap[right(index)])){
      		swap(&(H->heap[index]),&(H->heap[right(index)]));
      		percolatedown(H,right(index)); //percolate down to right child
    }else if (H->heap[index] < H->heap[left(index)]){
      		   swap(&(H->heap[index]),&(H->heap[left(index)]));
      		   percolatedown(H,left(index)); //percolate down to left child
    }
  }
  else if (left(index) < H->size){ //has valid left child only
	  if (H->heap[index] < H->heap[left(index)])
		swap(&(H->heap[index]),&(H->heap[left(index)]));
  }
}

void printHeapHelper(HEAP *H, int index, int tabs){
	if(index >= H->size) return;
	else{
		printHeapHelper(H, right(index), tabs + 1);
		for(int i=0; i<tabs; i++) printf("\t");
		printf("%d\n", H->heap[index]);
		printHeapHelper(H, left(index), tabs + 1);
	}
}

//.h functions

void printHeap(HEAP *H){  // PRINT HEAP
	if(H!=NULL && H->size>0){
		printHeapHelper(H, 0, 0);
	}else{
		printf("Empty heap!\n");
	}
}


HEAP* createHeap(int maxSize){
	HEAP *h = (HEAP *) malloc(sizeof(HEAP));

	h->heap = (int *) malloc((maxSize) * sizeof(int));
	h->size = 0;
	h->maxSize = maxSize;
}

int isFull(HEAP *H){
	if(H!=NULL && H->size == H->maxSize) return 1;
	else return 0;
}

int isEmpty(HEAP *H){
	if(H!=NULL && H->size == 0) return 1;
	else return 0;
}

void clear(HEAP *H){
	if(H!=NULL && !isEmpty(H)) H->size = 0;
}

void insert(HEAP *H, int key){ //INSERT
	int index;
	
	if(H->size<H->maxSize){
		H->heap[(H->size)++]=key;
        percolateup(H,H->size - 1);
    }  
}

int deleteM(HEAP *H){ //DELETE M
	if(H!=NULL && !isEmpty(H)){
		swap(&(H->heap[0]), &(H->heap[--(H->size)]));
		percolatedown(H,0);
		return H->heap[(H->size)]; // return the deleted key
	}
	else printf("Heap is empty.\n");
	
	return -1;
}


/*
 *  HEAP SORT
 *  requirements:
      a non-null HEAP pointer
      a non-empty *heap
 *  results:
 	  returns an array containing the sorted values of the heap
	  (must not modify the original heap)
*/
int *heapSort(HEAP *H){

//insert missing codes;
//HINT: 1. you need to create another HEAP a copy of H	
//      2. you need create a dynamic array that will be used to return the sorted keys
	if(H!=NULL && !isEmpty(H)){ // just copied the condition from deleteM(), since same requirements
		
		// create another HEAP a copy of H
		HEAP *copyH = createHeap(H->maxSize);
		
		// memcpy(&(copyH->heap), &(H->heap), sizeof(copyH->size));

		// buildHeap(copyH);
		for(int index=0; index<(H->size); index++){
			insert(copyH, (H->heap[index]));
		}

		// create a dynamic array that will be used to return the sorted keys
		int * sortedKeys = (int *) malloc(copyH->maxSize * sizeof(int));

		for(int i=((copyH->size)-1); i>=0; i--){
			// delete the max from copyH, and put it to the certain element in sortedKeys
			sortedKeys[i] = deleteM(copyH);
		}
		return(sortedKeys);
	} else{
		printf("Heap is empty!\n");
		return 0; //by default if H is empty
	}
}

/*
 *  BUILD HEAP
 *  requirements:
      none
 *  results:
 	  modifies the copyH heap to percolatedown from n/2 to 0
*/

// void buildHeap(HEAP *copyH){
// 	for(int i=((copyH->size)/2); i >= 0; i--){
// 		percolatedown(copyH, i);
// 	}

// 	// printf("After build heap\n");
// 	// printHeap(copyH);
// 	// printf("\n\n");
// }



