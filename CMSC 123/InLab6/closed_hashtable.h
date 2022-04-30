#ifndef _CLOSED_HASHTABLE_H_
#define _CLOSED_HASHTABLE_H_
#include<stdio.h>
#include<stdlib.h>

//REMINDER: you are NOT allowed to change this file

typedef struct hash_tag {
  int size; //current size of the hash
  int m; //tableSize
  int *array; //data which is composed array of keys
              // assume keys==values; index==h_(key), where h_ is a hash function
  int type; //1-linear probing; 
			//2-quadratic probing;
			//3-double hashing
} HASH_TABLE;

//insert your solutions here

//H->type==1 Linear Probing
int h1(int k,int m, int i);

//InLab Exercise!
//H->type==2 Quadratic Probing
int h2(int k,int m, int i);

//H->type==3 Double Hashing
int h3(int k,int m, int i);

//print contents of hash table
//refer to expected output for formatting
void printTable(HASH_TABLE *H);

//create hashTable given tableSize and hash type
HASH_TABLE * createHashTable(int m, int type);

//return 1 for empty, 0 for not
int isEmpty(HASH_TABLE *h);

//full means table size has reached the capacity
//note: capacity 3/4 of table size
int isFull(HASH_TABLE *h);

//given a key, insert the key into the hash table
//perform closed hashing, using linear Probing and Double Hashing as collision resolution strategy
//on successful insert, return the number of collisions encountered, otherwise return -1
int insert(HASH_TABLE *H, int key);

//given a key, find the index of the key from the hash table
//if key is found, return the index of the key, otherwise return -1
int find(HASH_TABLE *H, int key);

//deallocate the hash table
void destroy(HASH_TABLE **H);

#endif
