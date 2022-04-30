//params03.c
//Passing an array as parameter to functions
#include<stdio.h>

int main(){
	int i, n=5;
	int a[n];
	
	//get user inputs for n integers and store it into array a
	for(i=0;i<n;i++) {
		printf("Enter an integer: ");
		scanf("%i",&a[i]);
	}
					
		
	//display the contents of array a
	for(i=0;i<n;i++) {
		printf("a[%i]==%i\n",i,a[i]);
	}	
}
