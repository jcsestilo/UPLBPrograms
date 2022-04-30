//params03_exer1.c
//Exercise: Fill-in the blanks to make this program work like params03.c
#include<stdio.h>

void input(int *a, int n){ // pointing to array a, pointing to the first element of a
	for (int i=0; i<n; i++){
		printf("Enter an integer: ");
		scanf("%i", &a[i]); // will store to the certain address of pointer a, since it is similar to array a,
							// we just need to put & and the index
	}
}

void output(int *a, int n){ // pointing to array a, pointing to the first element of a
	for (int i=0; i<n; i++){
		printf("a[%i]==%i\n", i, a[i]); // no & because we are not getting the address but the value
	}
}


int main(){
	int i, n=5;
	int a[n];
	
	input(a, n); // array a has the address of the first element, so it is "similar to a pointer"
	
	
	output(a, n);// array a has the address of the first element, so it is "similar to a pointer"
}
					