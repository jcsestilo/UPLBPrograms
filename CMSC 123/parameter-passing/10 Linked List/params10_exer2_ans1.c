//params10_exer2_ans1.c
//Parameter passing for linked list in C

//Answer to params10_exer2.c

#include<stdio.h>
#include<malloc.h>

typedef struct node_tag{
	int x;
	struct node_tag *next;
}node; 


void output(node *h){ //display all the contents of the list
	node *temp;				  
	
	temp=h;
	while(temp!=NULL){
		printf("%3i ",temp->x);
		temp=temp->next;
	}
	printf("\n");
}

void deallocate(node **hptr){ //deallocates all the nodes of the list	
	node *temp;				  
	
	while((*hptr)!=NULL){
		temp=(*hptr);
		(*hptr)=(*hptr)->next;
		free(temp);
	}
}

void append(node **hptr, int x){ //should append a new node at the end of the list
	node *temp,*tail;
	
	//allocation of a new node
	temp=(node *)malloc(sizeof(node));
	temp->x=x;
	temp->next=NULL;
	
	//linking part
	if((*hptr)==NULL){ //list is empty, appending the first and only node
		(*hptr)=temp;
	}
	else { //just copy this repeating pattern discussed in the main function
		tail=(*hptr);
		while(tail->next!=NULL)
			tail=tail->next;
		tail->next=temp;
	}
}

int main(){
	node *h=NULL, *temp;

	//Final Exercise: do these in an append function
	
	/*
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
	*/
	
	//STEP1: separate the codes for theallocation and creation of a new node
	//from the codes needed for the linking of the new node
	/* 
	//first node
	temp=(node *)malloc(sizeof(node));
	temp->x=1;
	temp->next=NULL;
	
	h=temp;
	
	//second node
	temp=(node *)malloc(sizeof(node));
	temp->x=2;
	temp->next=NULL;
	
	h->next=temp;
	
	//third node
	temp=(node *)malloc(sizeof(node));
	temp->x=3;
	temp->next=NULL;
	
	h->next->next=temp;
	*/
	
	//STEP2: let node *tail; point to the last node
	//       to which the new node will be appended
	/* 
	//first node
	temp=(node *)malloc(sizeof(node));
	temp->x=1;
	temp->next=NULL;
	
	tail=h=temp;
	
	//second node
	temp=(node *)malloc(sizeof(node));
	temp->x=2;
	temp->next=NULL;
	
	tail=h;
	while(tail->next!=NULL)
		tail=tail->next;
	tail->next=temp;
	
	//third node
	temp=(node *)malloc(sizeof(node));
	temp->x=3;
	temp->next=NULL;
	
	tail=h;
	while(tail->next!=NULL)
		tail=tail->next;
	tail->next=temp;
	*/
	
	//NOTE: appending the second and the third node now have the same code
	//      this pattern can even be used to append more nodes
	//      appending the first node can be treated as a special case
	//      at last, we're now ready to implement it in the append function
	
	append(&h,1); //appends the first node
	append(&h,2); //appends the second node
	append(&h,3); //appends the third node
	append(&h,4); //we can even append a fourth node
	
	//just like in the deallocate function, we need to pass the address of h-
	//to append function because when appending the first and only node,
	//we need to change the value of "h in main" from NULL to the address
	// of the first node
	
	
	//display the contents of the linked list
	output(h);
	
	//dynamic de-allocation
	deallocate(&h);
}
