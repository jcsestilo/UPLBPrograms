//params05_exer1.c
//Exercise: Fill-in the blanks to make this program work like params05.c
#include<stdio.h>


//try these alternative formal parameters
//void input(int a[2][3], int n){	//valid, int a[2][3] decays to int a[][3]

void input(int a[][3] , int m, int n){
	//should get user inputs for m*n integers
	// and store it in "2D array a in main"
	
	for(int i=0;i<m;i++) {
		printf("\nEnter %d integers for row %d\n", n,i+1);
		for(int j=0;j<n;j++) {
			printf("Enter an integer: ");
			scanf("%d",&a[i][j]);
		}
	}
}

void output(int a[][3], int m, int n){ 
	//should print m*n integers from "2D array a in main"
	for(int i=0;i<m;i++) {
		for(int j=0;j<n;j++) {
			printf("%d ",a[i][j]);
		}
		printf("\n");
	}
}


int main(){
	int m=2, n=3;
	int a[m][n];
	int i,j;
	
	//#1 get user inputs for m*n integers and store it into 2D array a
	//for(i=0;i<m;i++) {
	//	printf("\nEnter %i integers for row %i\n", n,i+1);
	//	for(j=0;j<n;j++) {
	//		printf("Enter an integer: ");
	//		scanf("%i",&a[i][j]);
	//	}		
	//}
	input(a, m, n); // pass a 2D array variable a as parameter into input function

	
	//#2 display the contents of 2D array a
	//for(i=0;i<m;i++) {
	//	for(j=0;j<n;j++) {
	//		printf("%3i ",a[i][j]);
	//	}		
	//	printf("\n");
	//}			
	output(a, m, n);// pass a 2D array variable a as parameter into output function
}
					