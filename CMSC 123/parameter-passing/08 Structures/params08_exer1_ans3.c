//params08_exer1_ans3.c
//Passing C structure (or record) as parameter to functions

//Alternative answer to params08_exer1_ans2.c
//just to emphasize the how to simply struct datatype with typedef

#include<stdio.h>

//int myint; //myint is declared as a global int variable
typedef int myint; //using typedef, myint will be a user-defined data type
//myint xyz; // with typdef, myint can now be used to declare int variables


//struct node_tag{
//	char name[20];
//	float grade;
//}node;  //without typedef, node here will be declared as a global
          //struct variable, specifically, a "struct node_tag" variable

typedef struct node_tag{
	char name[20];
	float grade;
}node;  //with typedef node here will be a user-defined data type
		//we can now all replace "struct node_tag" with "node"


//void input(struct node_tag *r1){ // pass-by-reference using a pointer
void input(node *r1){ // pass-by-reference using a pointer
								
	printf("What is your name? "); 
	scanf("%s",r1->name); //same as scanf("%s",(*r1).name);
		
	printf("What is your GWA? ");  
	scanf("%f",&(r1->grade)); //same as scanf("%f",&((*r1).grade));
}

//void output(struct node_tag r1){ //display values of fields of r1
void output(struct node_tag r1){ //display values of fields of r1
	printf("Name: %s\n",r1.name);
	printf("GWA: %0.2f\n",r1.grade);
}


int main(){
	//struct node_tag r1;
	node r1;
	
	//#1 do these lines in input function
	//printf("What is your name? ");
	//scanf("%s",r1.name);
	
	//printf("What is your GWA? ");
	//scanf("%f",&r1.grade);
	
	input(&r1); 
	
	//#2 do this in output function
	//printf("Name: %s\n",r1.name);
	//printf("GWA: %0.2f\n",r1.grade);
	
	output(r1); 
}
