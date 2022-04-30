//params04_exer3_ans2.c
//Passing a dynamically allocated array as parameter to functions

//Alternative answer to params04_exer3.c
#include<stdio.h>
#include<malloc.h> //for malloc function
#include<stdlib.h> //for exit function
#include<string.h> //for memcpy

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

void duplicate(int **a, int *nptr){ //same as formal parameters as in ans1 
									//but with obfuscation
	*a=realloc(*a, (*nptr)*2*sizeof(int)); 
	memcpy(&((*a)[(*nptr)]),*a,(*nptr)*sizeof(int));
	(*nptr)*=2;
}

int main(){
	int i, n=5;
	//int a[n];
	
	//dynamic allocation using a pointer "int *a"
	int *a = (int *)malloc(n*sizeof(int));
	if (a==NULL){
		printf("insufficient allocation error\n");
		exit(1);
	}

	input(a,n);
	output(a,n);
	duplicate(&a, &n); //duplicate function has to do the following
	                     //1. double the value of n, e.g. from n=5 to 10. 
						 //   we need to change the value of n while in duplicate function
						 //   that's why we have to pass its value
						 
						 //2. resize, size of array a by doubling it, e.g. from 5 elements to 10
						 //   retain the original elements entered by the user as the first half
						 //     of the resized array.
						 //   copy the first half of the resized array, to its secondhalf,
						 //   thereby duplicating the original array,
						 //   i.e. from [1][2][3][4][5] to [1][2][3][4][5][1][2][3][4][5]

	output(a,n);
	
	//dynamic de-allocation
	if (a!=NULL){
		free(a);  //frees or allows other programs to use the memory
				  // initially assigned to "int *a"
	}
}
