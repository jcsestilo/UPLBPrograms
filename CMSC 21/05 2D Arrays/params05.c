//params05.c
//Passing a static 2D array as parameter to functions
#include<stdio.h>

int main(){
	int m=2, n=3;
	int a[m][n];
	int i,j;
	
	//get user inputs for m*n integers and store it into 2D array a
	for(i=0;i<m;i++) {
		printf("\nEnter %i integers for row %i\n", n,i+1);
		for(j=0;j<n;j++) {
			printf("Enter an integer: ");
			scanf("%i",&a[i][j]);
		}		
	}
	
	//display the contents of 2D array a
	for(i=0;i<m;i++) {
		for(j=0;j<n;j++) {
			printf("%3i ",a[i][j]);
		}		
		printf("\n");
	}		

}
					