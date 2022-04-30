//params02_exer2_ans1.c 
//Answer to params02_exer2.c
#include<stdio.h>

void input(int *a, int *b){ //ask user input for a and b, respectively
	printf("Enter two integers (e.g. 21 123): ");
	scanf("%i %i",a,b);  
}

void output(int a, int b){	//prints the values of a and b
	printf("a==%i b==%i\n",a,b); 
}

void swap(int *a,int *b){ //swaps values of "a and b in main"
	int temp;
	
	temp=*a;	// assign the value of "a in main" to temp
				// "a in swap" has the address of "a in main"
				// *a is the indirection operation of pointer "a in swap"
				// which synonymous to "a in main"
	
	*a=*b;		// assign the value of "b in main" to "a in main"
	*b=temp;	// assign the value of temp to "b in main"
	
}


int main(){
	int a=1, b=2;
	
	input(&a, &b); 	
	output(a,b);
	swap(&a,&b); //should swap the values of and b using pass-by-reference
	output(a,b);
}