//params05_exer1.c
//Exercise: Fill-in the blanks to make this program work like params05.c
#include<stdio.h>


void input(int (*a)[3], int m, int n){ // when passing arrays, it decays to a pointer of the first element. 
						// making a single-dimensional array, we add [3] so that there will be "more dimensions"
						// and the function will know the array's size.

	for(int i=0;i<m;i++) {
		printf("\nEnter %i integers for row %i\n", n,i+1);
		for(int j=0;j<n;j++) {
			printf("Enter an integer: ");
			scanf("%i",&a[i][j]);
		}		
	}
	
}

void output(int (*a)[3], int m, int n){ 
	for(int i=0;i<m;i++) {
		for(int j=0;j<n;j++) {
			printf("%3i ",a[i][j]);
		}		
		printf("\n");
	}	
}


int main(){
	int m=2, n=3;
	int a[m][n];
	int i,j;

	input(a, m, n); // pass a 2D array variable a as parameter into input function
	
	output(a, m, n);// pass a 2D array variable a as parameter into output function
}
					