//params04_exer1.c
//Exercise: Fill-in the blanks to make this program work like params04.c

#include<stdio.h>
#include<malloc.h> //for malloc function
#include<stdlib.h> //for exit function

void input(int *a, int n){ 
	for (int i=0; i<n; i++){
		printf("Enter an integer: ");
		scanf("%i", &a[i]);
	}
	
}

void output(int *a, int n){ 
	for(int i=0;i<n;i++){
		printf("a[%i]==%i\n", i, a[i]);
	}

}

int main(){
	int i, n=5;
	
	int *a = (int *)malloc(n*sizeof(int));
	if (a==NULL){
		printf("insufficient allocation error\n");
		exit(1);
	}

	input(a, n); 
	output(a, n);

	
	//dynamic de-allocation
	if (a!=NULL){
		free(a);  //frees or allows other programs to use the memory
				  // initially assigned to "int *a"
	}
}
