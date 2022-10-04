#include <stdio.h>

//Recursive Function for Fibonacci Sequence
int fibonacciSequence(int n) {
	if (n < 2) { //Base Case
		return n;
	} else { //Recursive Case
		return fibonacciSequence(n-1) + fibonacciSequence(n-2);
	}
}

//Tail-Recursive Function for Fibonacci Sequence
int fibonacciSequence1(int n, int prev, int curr) { //Accumulators
	if (n < 2) { //Base Case
		return curr;
	} else { //Recursive Case
		fibonacciSequence1(n-1, curr, prev+curr);
	}
}

int main() {
	printf("%d\n", fibonacciSequence(7));
	printf("%d\n", fibonacciSequence1(7, 0, 1)); //Initialization

}