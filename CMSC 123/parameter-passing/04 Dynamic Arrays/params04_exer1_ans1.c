//params04.c
//Passing a dynamically allocated array as parameter to functions
#include<stdio.h>
#include<malloc.h> //for malloc function
#include<stdlib.h> //for exit function

void input(int *a, int n){ 

	//"a in main" has the address of "a[0] in main"
	
	//if we pass "a in main" to "a in input" pointer, then, 
	//"a in input" will now be functionally the same as "a in main"

	//"a in input" will also have the address of  "a[0] in main"
	
	//"a[0] in input" is now the same "a[0] in main" and the rest of a[i]

	//passing dynamically allocated 1D array is the same as static 1D array
	
	//BUT, not for 2D or multi-dimensional arrays (see params05.c and params06.c) 

	int i;
	
	for(i=0;i<n;i++) {
		printf("Enter an integer: ");
		scanf("%i",&a[i]);
	}
	
}

void output(int *a, int n){ //see comments in input
	int i;
	
	for(i=0;i<n;i++) {
		printf("a[%i]==%i\n",i,a[i]);
	}
}

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

	//#1 get user inputs for n integers and store it into array a
	//for(i=0;i<n;i++) {
	//	printf("Enter an integer: ");
	//	scanf("%i",&a[i]);
	//}
	input(a , n); // pass the dynamic array variable a 
				   //  as parameter to the input function
	
	
	//#2 display the contents of array a
	//for(i=0;i<n;i++) {
	//	printf("a[%i]==%i\n",i,a[i]);
	//}
	output(a , n);// pass the dynamic array variable a 
				   //   as parameter to the output function
	
	
	//dynamic de-allocation
	if (a!=NULL){
		free(a);  //frees or allows other programs to use the memory
				  // initially assigned to "int *a"
	}
}
