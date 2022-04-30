#include "sort.h"
#include <stdio.h>
#include <time.h>


int main(){
    double t1;
	char command,mode;
	int n;
	int a[1000000];

	while(1){
		scanf("%c ", &command);
		scanf("%i %c\n", &n, &mode);
		init(a,n,mode);
		switch(command){
			case 'i':
				printf("initial:"); view(a,n);
				t1=clock();
				isort(a,n);
				printf("elaspsed time for isort(a,%i,%c): %f\n", n, mode, 
				(clock() - t1) / CLOCKS_PER_SEC);
				printf("isorted:"); view(a,n); printf("\n");
				break;
			case 'b':
				printf("initial:"); view(a,n);
				t1=clock();
				bsort(a,n);
				printf("elaspsed time for bsort(a,%i,%c): %f\n", n, mode, 
				(clock() - t1) / CLOCKS_PER_SEC);
				printf("bsorted:"); view(a,n); printf("\n");
				break;
			case 'm':
				printf("initial:"); view(a,n);
				t1=clock();
				msort(a,0,n-1);
				printf("elaspsed time for msort(a,%i,%c): %f\n", n, mode, 
				(clock() - t1) / CLOCKS_PER_SEC);
				printf("msorted:"); view(a,n); printf("\n");
				break;
			case 'Q':
				return 0;
			default:
				printf("Unknown command: %c\n", command);
		}
	}

	return 0;
}