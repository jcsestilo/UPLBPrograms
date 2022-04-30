//params09.c
//Passing an array of C structures (or records) as parameter to functions
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

int main(){
	int i, n=3;
	node a[n];
	
	//get user inputs for n records and store it into array a
	for(i=0;i<n;i++) {
		input1(&a[i]);
	}
					
		
	//display the contents of array a
	for(i=0;i<n;i++) {
		output1(a[i]);
	}	
}
