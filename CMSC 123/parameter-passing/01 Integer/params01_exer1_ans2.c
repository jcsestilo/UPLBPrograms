//params01_exer1_ans2.c
//Alternative answer to params01_exer1_ans1.c
#include<stdio.h>

int input(){ // returns an integer based on the user input
	int i;
	
	printf("Enter an integer: ");
	scanf("%i",&i); 	//in doing scanf("%i",&i); here in input function,
					// we are asking scanf to store the user input
					// into "i in input", and then return it
	return(i);
}


void output(int i){	//pass-by-value 
	printf("i==%i\n",i); 
}

int main(){
	int i=123;
	
	//#1 do this in the input function
	//printf("Enter an integer: ");
	//scanf("%i",&i); 
	
	//input(&i); 	// pass-by-reference
	i=input();		// ALTERNATIVE to pass-by-reference	
					// BUT, what if we have to get user input for
					// two or more integers?
					// We have to use pass-by-reference (see params02.c)
	
	//#2 do this in the output function	
	//printf("i==%i\n",i);
	
	output(i); 	// pass-by-value
				// we only need to pass the value of i
				// if we only need to USE its value in output function
}
