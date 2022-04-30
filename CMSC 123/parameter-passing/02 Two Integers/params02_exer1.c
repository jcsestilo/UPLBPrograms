//params02_exer1.c Passing two integers as parameters
//Exercise: fill in the blanks to make this work like params02.c

#include<stdio.h>

void input(int *a, int *b){ // pointers for a and b
	
	printf("Enter two integers (e.g. 21 123): ");
	scanf("%i %i",a,b); // a and b (from the main function) will change values
}


void output(int a, int b){	// variable a and b for this function only
	printf("a==%i b==%i\n",a,b); // print print
}

int main(){
	int a=21, b=123;
	
	input(&a,&b); // passing the address of a and b

	output(a,b); //passing the variable a and b themselves

}
					