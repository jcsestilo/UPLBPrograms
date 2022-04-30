//params07.c
//Passing C strings as parameter to functions
#include<stdio.h>

int main(){
	char name[20]; //can store up to 19 input chars
	
	printf("What is your name? ");
	scanf("%s",name); //why don't we use & for strings?
					  //HINT: a string in C is an array
	
	printf("Your name is %s.\n",name);
	
}

