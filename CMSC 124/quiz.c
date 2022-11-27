#include<stdio.h>

void checker(int x, int y){
	extern int counter;
	for(int i=x; i<y; i+=1){
		if(i%2==0) counter++;
	}
}

int counter = 13;

int main() {
	checker(7,11);
	printf("%d\n", counter);

	return 0;
}