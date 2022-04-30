//params10_exer1_ans1.c
//Parameter passing for linked list in C

//Answer to params10_exer1.c

#include<stdio.h>
#include<malloc.h>

typedef struct node_tag{
	int x;
	struct node_tag *next;
}node; 


void output(node *h){ //should display all the contents of the list
					  //"h in main" has the address of the first node
					  // or we can say that it points to the first node
					  
					  //we passed it to "h in output", therefore,
					  //"h in output" will also point to the first node
					  
	node *temp;		  //that's why except for this local variable 
					  // declaration for node *temp;
					  // we simply have to copy the loop from main function
	
	temp=h;
	while(temp!=NULL){
		printf("%3i ",temp->x);
		temp=temp->next;
	}
	printf("\n");
}

void deallocate(node **hptr){ //should deallocate all the nodes of the list	
							  //the indirection (*hptr) in deallocate
							  //is synonymous to "h in main"
							  
	node *temp;				  //we simply declare the local variable, temp	
							  //and copy the loop from main, replacing
							  // h with (*hptr)
	while((*hptr)!=NULL){
		temp=(*hptr);
		(*hptr)=(*hptr)->next;
		free(temp);
	}
}

int main(){
	node *h, *temp;

	//first node
	h=(node *)malloc(sizeof(node));
	h->x=1;
	h->next=NULL;
	
	//second node
	h->next=(node *)malloc(sizeof(node));
	h->next->x=2;
	h->next->next=NULL;
	
	//third node
	h->next->next=(node *)malloc(sizeof(node));
	h->next->next->x=3;
	h->next->next->next=NULL;
	
	//#1 do this loop in the output function
	//display the contents of the linked list
	//temp=h;
	//while(temp!=NULL){
	//	printf("%3i ",temp->x);
	//	temp=temp->next;
	//}
	//printf("\n");
	
	output(h); //passing the value of h to output is enough for using 
	           // to access and print the contents of the list 
			   // starting from the first node
		
	//#2 do this loop in the deallocate function
	//dynamic de-allocation
	//while(h!=NULL){
	//	temp=h;
	//	h=h->next;
	//	free(temp);
	//}
	
	deallocate(&h); //we are already in final topic of linked list yet
					//we still follow the same rule when it is necessary
					//to pass the address of the variable to a function
					
					//if there is a need to CHANGE the value of the variable
					//then, we have to pass its address
					
					//unlike in the output function where you only
					// need to access and display the contents of the list,
					// in deallocate we need to CHANGE the value of h
					// eventually from the address of the first node to NULL
					// i.e. when we remove the last remaining node
					
					// THEREFORE, we need to pass the address of h 
					// to deallocate function
					
					//if you will only pass the value of h to deallocate, 
					// there will be no way for us to change the value of
					// "h in main" while in the deallocate function			
					
}
