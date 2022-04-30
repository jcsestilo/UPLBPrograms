/*
	MEDCCunanan
	Creates a program that demonstrates the basic operations on a singly linked list.

	FUNCTIONS AVAILABLE:
		- Add Head
		- Add Tail
		- Delete Head
		- Delete Tail
		- Print While
		- Print For
*/

#include<stdio.h>
#include<stdlib.h>

typedef struct node_tag {
	int data;
	struct node_tag *next;
} NODE;

//Adds data to the head of the linked-list
void addHead(NODE **head) {
	NODE *new = (NODE *) malloc (sizeof(NODE));
	printf("Enter data: ");
	scanf("%d", &new->data);

	new->next = (*head);
	(*head) = new;

	printf("Successfully added!\n");
}

//Adds data to the tail of the linked-list
void addTail(NODE **head) {
	NODE *new = (NODE*) malloc (sizeof(NODE));
	printf("Enter data: ");
	scanf("%d", &new->data);
	new->next = NULL;

	if ((*head) == NULL) {
		new->next = (*head);
		(*head) = new;
	} else {
		NODE *temp = (*head);
		while(temp->next != NULL) {
			temp = temp->next;
		}	

		temp->next = new;
	}

	printf("Successfully added!\n");
}

//Deletes data at the head of the linked list
void deleteHead(NODE **head) {
	if ((*head) == NULL) {
		printf("List is still empty!\n");
	} else {
		NODE *temp = (*head);
		(*head) = (*head)->next; //temp->next;
		free(temp);

		printf("Successfully deleted data!\n");
	}
}

//Deletes data at the tail of the linked-list
void deleteTail(NODE **head) {
	if ((*head) == NULL) {
		printf("Linked list is still empty!\n");
	} else if ((*head)->next == NULL) {
		NODE *temp = (*head);
		(*head) = (*head)->next; //temp->next;
		free(temp);

		printf("Successfully deleted data!\n");
    } else {
		NODE *temp = (*head);
		while(temp->next->next != NULL) {
			temp = temp->next;
		}

		free(temp->next);
		temp->next = NULL;

		printf("Successfully deleted data!\n");
	}
}

//Frees all nodes before exiting the program
void deleteAll(NODE **head) {
	while((*head) != NULL) {
		NODE *temp = *head;
		(*head) = (*head)->next;
		free(temp);
	}
}

//Prints the information available in the linked list using while loop
void whilePrint(NODE*head) {
	while(head != NULL) {
		printf("%d ", head->data);
		head = head->next;
	}
}

//Prints the information available in the linked list using for loop
void forPrint(NODE*head) {
	for(NODE *temp = head; temp != NULL; temp = temp->next) {
		printf("%d ", temp->data);
	}
}

int main() {
	NODE *head = NULL;

	int choice;

	while(1) {
		printf("[1] Add Head\n");
		printf("[2] Add Tail\n");
		printf("[3] Delete Head\n");
		printf("[4] Delete Tail\n");
		printf("[5] Print While\n");
		printf("[6] Print For\n");
		printf("[7] Exit\n");

		printf("Enter choice: ");
		scanf("%d", &choice);

		printf("\n");

		if (choice == 1) {
			addHead(&head);
		} else if (choice == 2) {
			addTail(&head);
		} else if (choice == 3) {
			deleteHead(&head);
		} else if (choice == 4) {
			deleteTail(&head);
		} else if (choice == 5) {
			if (head == NULL) {
				printf("List is still empty!\n");
			} else {
				whilePrint(head);
			}
		} else if (choice == 6) {
			if (head == NULL) {
				printf("List is still empty!\n");
			} else {
				forPrint(head);
			}
		} else if (choice == 7) {
			break;
		} else {
			printf("Choice is invalid!\n");
		}

		printf("\n");

	}
	
	deleteAll(&head);
}