//params04_exer3.c Passing a dynamically allocated array as parameter to functions
//Exercise: Fill the blanks to make the duplicate function work

#include<stdio.h>
#include<malloc.h> //for malloc function
#include<stdlib.h> //for exit function


void input(int *a, int n){ 

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

void duplicate(int **aptr, int *nptr){ 
	int *new;
	int n=*nptr;
	int *a=*aptr;
	int i;
	new=malloc(n*2*sizeof(int));

	for(i=0; i<n; i++)  //copy a in main as the first half of new
		new[i]=(*aptr)[i];

	for(i=0; i<n; i++)	//copy a in main also as the second half of new
		new[i+n]=a[i]; 

	free(*aptr);
	*aptr=new;
	*nptr=n*2;
	} 
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
	output(a,n); //prints the original array as entered by the user
	duplicate(&a, &n); //duplicate function has to do the following
	                     //1. double the value of n, e.g. from n=5 to 10. 
						 //2. resize, size of array a by doubling it, e.g. from 5 elements to 10
						 //   retain the original elements entered by the user as the first half
						 //     of the resized array.
						 //   copy the first half of the resized array, to its secondhalf,
						 //   thereby duplicating the original array,
						 //   e.g. from [1][2][3][4][5] to [1][2][3][4][5][1][2][3][4][5]
	output(a,n); //prints the "duplicated" array
	
	//dynamic de-allocation
	if (a!=NULL){
		free(a);  //frees or allows other programs to use the memory
				  // initially assigned to "int *a"
	}
}
