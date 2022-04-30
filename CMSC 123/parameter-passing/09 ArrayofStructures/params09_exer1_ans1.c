//params09_exer1_ans1.c
//Passing an array of C structures (or records) 
//as parameter to functions

//Answer to params09_exer1.c

#include<stdio.h>

typedef struct node_tag{
	char name[20];
	float grade;
}node;  

void input1(node *r1){ //get user input for one record
								
	printf("What is your name? "); 
	scanf("%s",r1->name); //same as scanf("%s",(*r1).name);
		
	printf("What is your GWA? ");  
	scanf("%f",&(r1->grade)); //same as scanf("%f",&((*r1).grade));
}


void output1(node r1){ //display values of fields of r1
	printf("Name: %s\n",r1.name);
	printf("GWA: %0.2f\n",r1.grade);
}


void input(node a[], int n){ // for array parameters,
							 // "node a[]" decays to "node *a"
							 // and just like in array of intergers
							 // "a in input" will be functionally
							 // the same as "a in main"
							 // both pointing to "a[0] in main"
	int i;
	for(i=0;i<n;i++) {
		input1(&a[i]);
	}						 
}


void output(node *a, int n){ //see comments in input function
	int i;
	
	for(i=0;i<n;i++) {
		output1(a[i]);
	}
}


int main(){
	int i, n=3;
	node a[n];
	
	//#1 do this loop in the input function
	//get user inputs for n records and store it into array a
	//for(i=0;i<n;i++) {
	//	input1(&a[i]);
	//}
		
	input(a,n); //array parameter passing is straight forward
	            //array variable "a" has the address of a[0]
				//and that's what is enough for us to be able
				//to manipulate the contents of the array while in input
				
	
	
	
	//#2 do this loop in the output function
	//display the contents of array a
	//for(i=0;i<n;i++) {
	//	output1(a[i]);
	//}	
	
	output(a,n);//passing array variable "a", which is the address of
				//"a[0] in main" is enough for us to be able
				//to access its contents and display in output function
}
