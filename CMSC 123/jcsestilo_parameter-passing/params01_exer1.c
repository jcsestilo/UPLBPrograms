//params01_exer1.c Passing integer as parameter
//Exercise: fill in the blanks to make this work like params01.c
#include<stdio.h>

void input(int *i){ // i pointer
	
	printf("Enter an integer: ");
	scanf("%i",i); // i (from the main function) will change value
}


void output(int i){	//making i variable for this function only
	printf("i==%i\n",i); // print print
}

int main(){
	int i=123;
	
	input(&i); // Pass by reference of i to input function
	
	output(i); // pass by value of i to output function
}
