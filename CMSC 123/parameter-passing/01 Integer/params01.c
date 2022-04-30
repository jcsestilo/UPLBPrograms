//params01.c
//Passing integer as parameter
#include<stdio.h>

int main(){
	int i=123;
	
	printf("Enter an integer: ");
	scanf("%i",&i); //why do we have & in scanf?
					//first time encounter with a C POINTER!
	printf("i==%i\n",i);
}
