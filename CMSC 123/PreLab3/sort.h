#ifndef _SORT_H_
#define _SORT_H_


void swap(int *a, int *b);

//initialize the array from 1 to n if mode is 'a'
//from n down to 1 if mode is 'd'
void init(int a[], int n, char mode); 

//print the contents of the array
void view(int a[], int n);

//sorts the array in ascending order
void bsort(int a[], int n); 

//sorts the array in ascending order 
void isort(int a[], int n); 

//called by msort
//merges the two sorted half o the arrays
//from a[lower] to a[mid] and from a[mid+1] to a[upper]
void merge(int a[], int lower, int mid, int upper); 

//sorts the array in ascending order, recursively
void msort(int a[], int lower, int upper); 


#endif
