//params01_exer1_ans1.c
//Answer to params01_exer1.c
#include<stdio.h>

void input(int *i){ // pass-by-reference using a pointer
	
	printf("Enter an integer: ");
	scanf("%i",i); 	//"i in input" has the address of "i in main"
				  	// we are telling scanf to store the user input
					// into "i in main"
					//if we do scanf("%i",&i); here in input function,
					// we will be asking scanf to store the user input
					// into "i in input", which is WRONG
}


void output(int i){	//pass-by-value 
	printf("i==%i\n",i); 
}

int main(){
	int i=123;
	
	//#1 do this in the input function
	//printf("Enter an integer: ");
	//scanf("%i",&i); 
	
	input(&i); 	// pass-by-reference
				// we need to pass the address of i
				// because we need to CHANGE its value while in input function
	
	
	//#2 do this in the output function	
	//printf("i==%i\n",i);
	
	output(i); 	// pass-by-value
				// we only need to pass the value of i
				// because we only need to USE its value in output function
}
