//params03_exer1_ans1.c
//Answer to params03_exer1.c
#include<stdio.h>


//try these alternative formal parameters
// all these decay to int *a
// interesting how will this work for 2 or more dimensional arrays
//void input(int a[10], int n){	//valid, int a[10] decays to int *a
//void input(int a[5], int n){	//valid, int a[5] decays to int *a
//void input(int a[], int n){	//valid, int a[] decays to int *a

void input(int *a, int n){ 
	//"a in main" has the address of "a[0] in main"
	
	//if we pass "a in main" to "a in input" pointer, then, 
	//"a in input" will now be functionally the same as "array a in main"

	//"a in input" will also have the address of  "a[0] in main"
	
	//"a[0] in input" is now the same "a[0] in main" and the rest of a[i]

	//this makes array parameter passing pretty straightforward
	
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
	int a[n];
		
	//#1 do this in the input function
	//get user inputs for n integers and store it into array a
	//for(i=0;i<n;i++) {
	//	printf("Enter an integer: ");
	//	scanf("%i",&a[i]);
	//}
	
	input(a, n); // pass array variable a as parameter into input function
					
	
	//#2 do this in the output function
	//display the contents of array a
	//for(i=0;i<n;i++) {
	//	printf("a[%i]==%i\n",i,a[i]);
	//}	
	
	output(a, n);// pass array variable a as parameter into output function
}
					