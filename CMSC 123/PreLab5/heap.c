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

    if ((H->heap[index] < H->heap[right(index)])&& // If right child is greater than index AND right child is greater than left child
    	(H->heap[left(index)] < H->heap[right(index)])){

      		swap(&(H->heap[index]),&(H->heap[right(index)]));
      		percolatedown(H,right(index)); //percolate down to right child

    }else if (H->heap[index] < H->heap[left(index)]){ // If left child is greater than index value (as well as left child > right child)

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
//insert missing codes; call percolate up
	if(!isFull(H)){ 
		H->heap[H->size++] = key; // Append key to the last position in heap array
		percolateup(H,(H->size) - 1);
	}else{
		printf("Heap is already full.\n");
	}
}

int deleteM(HEAP *H){ //DELETE M
//insert missing codes; call percolate down
	if(!isEmpty(H)){
		swap(&(H->heap[0]), &(H->heap[(H->size)-1])); // swap the root of the heap and the nodee at the last position
		percolatedown(H,0);
		return (H->heap[H->size]);

	}else{
		printf("Heap is empty.\n");
		return 0;
	}
}

