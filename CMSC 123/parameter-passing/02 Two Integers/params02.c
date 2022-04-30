//params02.c
//Passing two integers as parameters
#include<stdio.h>

int main(){
	int a=1, b=2;
	
	printf("Enter two integers (e.g. 21 123): ");
	scanf("%i %i",&a, &b); //pass-by-reference
	
	printf("a==%i a==%i\n",a,b); // pass-by-value
}
					