//params08_exer1_ans1.c
//Passing C structure (or record) as parameter to functions
//Answer to params08_exer1.c
#include<stdio.h>

struct node_tag{
	char name[20];
	float grade;
};


void input(struct node_tag *r1){ // pass-by-reference using a pointer
								
	printf("What is your name? "); 
	scanf("%s",(*r1).name);	//"r1 in input" has the address of "r1 in main"
							//we are telling scanf to store the user 
							//input into "r1.name in main"
					
							//todo that, first we do the indirection of 
							// "r1 in input" which is given by "(*r1)",
							// allowing us to access "r1 in main"
							// even while in input.
							
							// then, we access the field using the dot <.field>
							// notation, i.e. "(*r1).name"
							
							// since,"(*r1).name is a string, 
							// we can pass it to scanf even without the 
							// "&" or the address operator
							// it is already the address of 
							// "(*r1).name[0] in main"
							
	//you can also use the arrow notation ->
	//since r1 in input is a structure pointer
	//scanf("%s",r1->name);
		
	printf("What is your GWA? ");  
	scanf("%f",&((*r1).grade)); //here we have to explicitly
								//pass to scanf the address of
								//"r1.grade in main"
	
	//you can also use the arrow notation ->
	//since r1 in input is a structure pointer
	//scanf("%f",&(r1->grade));
}

void output(struct node_tag r1){ //display values of fields of r1
	printf("Name: %s\n",r1.name);
	printf("GWA: %0.2f\n",r1.grade);
}


int main(){
	struct node_tag r1;
	
	//#1 do these lines in input function
	//printf("What is your name? ");
	//scanf("%s",r1.name);
	
	//printf("What is your GWA? ");
	//scanf("%f",&r1.grade);
	
	input(&r1); // pass-by-reference
				// we need to pass the address of r1
				// if we need to CHANGE its value while in input function
				// (see additional comments in output function call)
	  
	
	
	//#2 do this in output function
	//printf("Name: %s\n",r1.name);
	//printf("GWA: %0.2f\n",r1.grade);
	
	output(r1); //structures, defined by structure <tag> 
				//are collections like array
				//unlike array variables
				// - unlike array, structures is collection of different 
				//   data types
				// - unlike array the value of the structure variable
				//   is not the address of its first element
				// - structures can be passed to a function 
				//   using pass-by-value parameter passing
				//   like a single variable e.g. int, float, char
				//   "r1 in main" will be copied entirely to "r1 in output"
				//   they are stored separately in the memory
}
