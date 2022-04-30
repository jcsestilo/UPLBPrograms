//params02.c 
//Answer to params02_exer1_ans1.c
#include<stdio.h>

void input(int *a, int *b){ // pass-by-reference using pointers
	
	printf("Enter two integers (e.g. 21 123): ");
	scanf("%i %i",a,b); //"a and b in input" have the address of 
						// "a and b in main", respectively
						// we are telling scanf to store the user inputs
						// into "a and b in main", respectively
						//if we do scanf("%i %i",&a,&b); here in input function,
						// we will asking scanf to store the user inputs
						// into "a and b in input",respectively, which is WRONG
}


void output(int a, int b){	//pass-by-value 
	printf("a==%i b==%i\n",a,b); 
}

int main(){
	int a=1, b=2;
	
	//#1 do this in the input function
	//printf("Enter two integers (e.g. 21 123): ");
	//scanf("%i",&i); 
	
	input(&a, &b); 	// pass-by-reference
					// we need to pass the addresses of a and b
					// if we need to CHANGE their values 
					// while in input function
	
	
	//#2 do this in the output function	
	//printf("a==%i a==%i\n",a,b);
	
	output(a,b); 	// pass-by-value
					// we only need to pass the values of a and b
					// if we only need to USE their values 
					// in the output function
}