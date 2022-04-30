//params07_exer2_ans1.c
//Answer to params07_exer2.c
#include<stdio.h>
#include<ctype.h>	//character manipulation functions
#include<string.h>	//standard C string functions

//void input(char s[20]) { // decays to char *s
//void input(char s[]) { //decays to char *s
void input(char *s) { //s==&name[0] in main
	printf("What is your name? ");
	scanf("%s",s);
	
}

void toupperStr(char *s){
	int i, n=strlen(s);
	for(i=0;i<n;i++)
		s[i]=toupper(s[i]);
}

void output(char *s){
	printf("Your name is %s.\n",s);
}
	

int main(){
	char name[20]; //can store up to 19 input chars
	
	//printf("What is your name? ");
	//scanf("%s",name);
	
	input(name); //same as array of integers parameter passing 
	
				 //BUT, you have to be aware of the allocation
				 //if s is a C String then,
				 // s[strlen(s)]==0 or the terminating character
				 
				 //e.g. char name[20]; can store up to 19 input chars only
	
	//printf("Your name is %s.\n",name);
	output(name);
	
	toupperStr(name); //repeatedly call toupper functin in ctype.h to 
					  // convert name to upper case
	
	output(name);
	
}

