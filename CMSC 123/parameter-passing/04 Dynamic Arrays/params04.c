//params04.c
//Passing a dynamically allocated array as parameter to functions
#include<stdio.h>
#include<malloc.h> //for malloc function
#include<stdlib.h> //for exit function


int main(){
	int i, n=5;
	//int a[n];
	
	//dynamic allocation using a pointer "int *a"
	//"int *a" will store the starting address of the contiguously
	//allocated space for  n integers i.e. size n*sizeof(int)
	//making "int *a" a dynamically allocated array of n integers
	int *a = (int *)malloc(n*sizeof(int));
	if (a==NULL){
		printf("insufficient allocation error\n");
		exit(1);
	}

	//get user inputs for n integers and store it into array a
	for(i=0;i<n;i++) {
		printf("Enter an integer: ");
		scanf("%i",&a[i]);
	}
	
	//display the contents of array a
	for(i=0;i<n;i++) {
		printf("a[%i]==%i\n",i,a[i]);
	}
	
	//dynamic de-allocation
	if (a!=NULL){
		free(a);  //frees or allows other programs to use the memory
				  // initially assigned to "int *a"
	}
}
