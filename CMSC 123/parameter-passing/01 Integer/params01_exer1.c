//params01_exer1.c Passing integer as parameter
//Exercise: fill in the blanks to make this work like params01.c
#include<stdio.h>

void input(int *i){ 
	
	printf("Enter an integer: ");
	scanf("%i",i); 
}


void output(int i){	
	printf("i==%i\n",i); 
}

int main(){
	int i=123;
	
	//#1 do this in the input function
	//printf("Enter an integer: ");
	//scanf("%i",&i); 
	
	input(&i); 
	
	//#2 do this in the output function	
	//printf("i==%i\n",i);
	
	output(i); 
}
