//params06.c
//Passing a dynamic 2D array as parameter to functions
#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>

void chkalloc(void *p){ //checks if pointer is NULL, usually used after malloc
	if (p==NULL){
		printf("insufficient allocation error\n");
		exit(1);
	}
}

int main(){
	int i, j, m=2, n=3;
	//int a[m][n];

	//dynamic allocation using a pointer to a pointer
	//int **a will be an array of pointers
	//each a[i] will be an array of integers
	
	int **a = (int **)malloc(m*sizeof(int *)); // allocate m pointers
	chkalloc(a);
	for(i=0;i<m;i++){
		a[i] = (int *)malloc(n*sizeof(int)); // allocate n integers
		chkalloc(a[i]);						 // for each of the m pointers 
	}
	
	//get user inputs for m*n integers and store it into 
	//dynamic 2D array "a"
	for(i=0;i<m;i++) {
		printf("\nEnter %i integers for row %i\n", n,i+1);
		for(j=0;j<n;j++) {
			printf("Enter an integer: ");
			scanf("%i",&a[i][j]);
		}		
	}
	
	//display the contents of the dynamic 2D array "a"
	for(i=0;i<m;i++) {
		for(j=0;j<n;j++) {
			printf("%3i ",a[i][j]);
		}		
		printf("\n");
	}
	
	//dynamic de-allocation
	for(i=0;i<m;i++)
		if (a[i]!=NULL) free(a[i]);
	if (a!=NULL) free(a);

}
					