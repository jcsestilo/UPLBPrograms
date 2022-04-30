//params08.c
//Passing C structure (or record) as parameter to functions
#include<stdio.h>

struct node_tag{
	char name[20];
	float grade;
};

int main(){
	struct node_tag r1;
	
	printf("What is your name? ");
	scanf("%s",r1.name);
	
	printf("What is your GWA? ");
	scanf("%f",&r1.grade);
	
	printf("Name: %s\n",r1.name);
	printf("GWA: %0.2f\n",r1.grade);
	
}

