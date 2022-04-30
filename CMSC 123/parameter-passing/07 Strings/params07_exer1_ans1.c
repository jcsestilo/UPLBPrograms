//params07_exer1_ans1.c
//Answer to params07_exer1.c
#include<stdio.h>

//void input(char s[20]) { // decays to char *s
//void input(char s[]) { //decays to char *s
void input(char *s) { //s==&name[0] in main
	printf("What is your name? ");
	scanf("%s",s);
	
}

void output(char *s){
	printf("Your name is %s.\n",s);
}
	

int main(){
	char name[20]; //can store up to 19 input chars
	
	//#1 do this in the input function
	//printf("What is your name? ");
	//scanf("%s",name);
	
	input(name); //same as array of integers parameter passing 
	
				 //BUT, you have to be aware of the allocation
				 //if s is a C String then,
				 // s[strlen(s)]==0 or the terminating character
				 
				 //e.g. char name[20]; can store up to 19 input chars only
	
	//#2 do this in the output function
	//printf("Your name is %s.\n",name);
	output(name);

}
