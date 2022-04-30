#include "closed_hashtable.h"
//insert other headers here

//PreLab6 INSTRUCTIONS:
// You are allowed to add functions not declared in the h file
// h1 (Linear Probing) and h3 (Double Hashing) are already given, and you are not allowed to change them
// Implement the insert and find function.
// You may check the solutions in the answer subfolder


//H->type==1 Linear Probing
int h1(int k,int m, int i){
   return(((k%m)+i)%m);
}

//Second Hash function for Double Hashing
int h4(int k,int m, int i){
   return(i*(m-(k%m)));
}

//H->type==3 Double Hashing
int h3(int k,int m, int i){
   return(((k%m)+h4(k,5,i))%m);
}


//print contents of hash table
//refer to expected output for formatting
void printTable(HASH_TABLE *H){
	int i=0;
	if(!isEmpty(H)){
		//printf("\nH->array: ");
		for(i=0;i<H->m;i++)
			printf("%3i ", H->array[i]);
		printf("\n");
	}
}

//create hashTable given tableSize and hash type
HASH_TABLE * createHashTable(int m, int type){
	//Initialize an hash instance
    HASH_TABLE *H = (HASH_TABLE *) malloc(sizeof(HASH_TABLE));
    int i;

    if(H == NULL){
        printf("Error: Operation not permitted. Lack of memory space.\n");
    }else{
        H->array=(int *)malloc(sizeof(int)*m);
		for(i=0;i<m;i++) H->array[i]=-1;

        H->m = m;
        H->type = type;
    }
    return H;
}

//return 1 for empty, 0 for not
int isEmpty(HASH_TABLE *H){
	return(H->size == 0);
}

//full means table size has reached the capacity
//note: capacity 3/4 of table size
int isFull(HASH_TABLE *H){
	return(H->size >= 3*(H->m)/4);
}

//given a key, insert the key into the hash table
//perform closed hashing, using linear Probing and Double Hashing as collision resolution strategy
//on successful insert, return the number of collisions encountered, otherwise return -1
int insert(HASH_TABLE *H, int key) {
	//PreLab6 Exercise
	//You may see the solution in the answer sub-folder

	int index=0;
	if(!isFull(H)){
		switch(H->type){
			case 1: // Linear Probing
				index=0;
				while (H->array[h1(key, H->m, index)] > 0){
					index++;
				}
				H->array[h1(key, H->m, index)] = key;
				break;
			case 3: // Double Hashing
				index=0;
				while (H->array[h3(key,H->m,index)] > 0) 
					index++;
				H->array[h3(key,H->m,index)]=key;  
				break; 
		}
		(H->size)++;
		return(index);
	}
	return(-1);

}

//given a key, find the index of the key from the hash table
//if key is found, return the index of the key, otherwise return -1
int find(HASH_TABLE *H, int key) {
	//PreLab6 Exercise
	//You may see the solution in the answer sub-folder
	int i=0;
	if(!isEmpty(H)){
		switch(H->type){
			case 1: 
				i=0;
				while (H->array[h1(key,H->m,i)]>0){
					if(H->array[h1(key,H->m,i)]==key) break;
					i++;
				}
				return(H->array[h1(key,H->m,i)]==key?h1(key,H->m,i):-1);
			case 3: 
				while (H->array[h3(key,H->m,i)]>0){
					if(H->array[h3(key,H->m,i)]==key) break;
					i++;
				}
				return(H->array[h3(key,H->m,i)]==key?h3(key,H->m,i):-1);
		}
	}
	return(-1);
}

void destroy(HASH_TABLE **H) {
	if(H!=NULL && *H!=NULL){
		if((*H)->array!=NULL)
			free((*H)->array);
		free(*H);
		*H=NULL;
	}
}

