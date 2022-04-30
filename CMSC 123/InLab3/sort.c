#include "sort.h"
#include <stdio.h>
#include <stdlib.h>



void swap(int *a, int *b){
  int temp=*a; *a=*b; *b=temp;
}

//initialize the array from 1 to n if mode is 'a'
//from n down to 1 if mode is 'd'
void init(int a[], int n, char mode){
    int i;

	switch(mode) {
		case 'a': //ascending
			for(i=0;i<n;i++)
				a[i]=i+1;
			break;
		case 'd': //descending
			for(i=0;i<n;i++)
				a[i]=n-i;
			break;
	}
}



//prints the contents of the array
void view(int a[], int n){
    int i;

    for(i=0;i<n;i++)
        printf("%3i ",a[i]);
    printf("\n");

}


//bubble sort
//sorts the array in ascending order 
void bsort(int a[], int n){
    int i,j;

    for(i=0;i<n-1;i++)
        for(j=0;j<n-1-i;j++)
			if(a[j]>a[j+1]) //for ascending order
                swap(&a[j],&a[j+1]);
}

//insertion sort
//sorts the array in ascending order 
void isort(int a[], int n){
    int i,j;

    for(i=1;i<n;i++)
        for(j=i;j>0;j--)
			if(a[j-1]>a[j]) //for ascending order
                swap(&a[j-1],&a[j]);
            else break;

}


//called by msort
//merges the two sorted half o the arrays
//from a[lower] to a[mid] and from a[mid+1] to a[upper]
void merge(int a[], int lower, int mid, int upper){
	int *temp,i,j,k;			

	temp=(int *)malloc((upper-lower+1)*sizeof(int));

	for(i=0,j=lower,k=mid+1; j<=mid || k<=upper; i++) 
		temp[i]=(j<=mid && (k>upper || a[j]<a[k]))?a[j++]:a[k++];
	for(i=0,j=lower;j<=upper; i++, j++)				
		a[j]=temp[i];
	//memcpy(&a[lower],temp,(upper-lower+1)*sizeof(int));
	
	free(temp);
}

//merge sort
//sorts the array in ascending order, recursively
void msort(int a[], int lower, int upper){
	int mid;			

	if (upper-lower>0) {
		mid=(lower+upper)/2;
		msort(a, lower, mid);
		msort(a, mid+1, upper);
		merge(a, lower, mid, upper);
		//printf("Result of mergesort(a,%i,%i): ",lower,upper);
		//output(a,N);
	} 
}

