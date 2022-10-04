#include <stdio.h>

//Recursive Function for Computing Factorial
int factorial(int n) {
	if (n < 1) { //Base Case
		return 1;
	} else { //Recursive Case
		return n * factorial(n-1);
	}
}

//Tail-Recursive Function for Computing Factorial
int factorial1(int n, int acc) { //Accumulator
	if (n < 1) { //Base Case
		return acc;
	} else { //Recursive Case
		factorial1(n-1, n*acc);
	}
}

int main() {
	printf("%d\n", factorial(5));
	printf("%d\n", factorial1(5, 1)); //Initialization!
}