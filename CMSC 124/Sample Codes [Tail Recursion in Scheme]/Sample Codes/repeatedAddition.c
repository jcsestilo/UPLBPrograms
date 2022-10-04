#include <stdio.h>

//Recursive Function for Multiplication by Repeated Addition
int repeatedAddition(int x, int y) {
	if (y == 1) { //Base Case
		return x; 
	} else { //Recursive Case
		return x + repeatedAddition(x, y-1);
	}
}

//Tail-Recursive Function for Multiplication by Repeated Addition
int repeatedAddition1(int x, int y, int acc) { //Accumulator
	if (y == 0) { //Base Case
		return acc;
	} else { //Recursive Case
		repeatedAddition1(x, y-1, x+acc);
	}
}

int main() {
	printf("%d\n", repeatedAddition(5, 3));
	printf("%d\n", repeatedAddition(3, 5));
	printf("%d\n", repeatedAddition1(3, 5, 0)); //Initialization!
	printf("%d\n", repeatedAddition1(5, 3, 0));
}