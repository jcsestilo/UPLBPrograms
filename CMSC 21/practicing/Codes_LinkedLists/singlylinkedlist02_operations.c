/*
	MEDCCunanan
	Creates a program that demonstrates the sorted operations on a singly linked list.

	FUNCTIONS AVAILABLE:
		- Add Node
		- Delete Node
		- Print Nodes
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct node_tag {
	int data;
	struct node_tag *next;
} NODE; 

//Adds data to the linked-list; Sorted in descending order
void addNode(NODE **head) {
	NODE *new = (NODE*) malloc (sizeof(NODE));

	printf("Enter data: ");
	scanf("%d", &new->data);

	new->next = NULL;

	//Insert at empty and head
	if ((*head) == NULL || ((*head) != NULL && new->data > (*head)->data)) {
		new->next = (*head);
		(*head) = new;
	} else { //Insert at tail and middle
		NODE *temp = (*head);
		while(temp->next != NULL && temp->next->data > new->data) {
			temp = temp->next;
		}

		new->next = temp->next;
		temp->next = new;
	}

	printf("Successfully added node!\n");
}

//Prints all the nodes available in the linked-list
void printAll(NODE*head) {
	printf("DATA AVAILABLE: ");
	while(head != NULL) {
		printf("%d ", head->data);
		head = head->next;
	}

	printf("\n");
}

//Deletes a specific node given by the user (if it exists)
void deleteNode(NODE **head) {
	if((*head) == NULL) {
		printf("Empty linked list!\n");
	} else {
		printf("Enter data to delete: ");
		int x;
		scanf("%d", &x);

		//Locates the node to be deleted
		NODE *del = (*head);
		while(del != NULL) {
			if (del->data == x) {
				break;
			}
			del = del->next;
		}

		if (del == NULL) {
			printf("Node not found!\n");
		} else {
			if (del == (*head)) { //Delete at head
				(*head) = (*head)->next;
				free(del);
			} else { //Delete at middle or tail
				NODE *temp = (*head);
				while(temp->next != del) {
					temp = temp->next;
				}

				temp->next = del->next;
				free(del);
			}

			printf("Successfully deleted node!\n");
		}
	}
}

//Frees all nodes before exiting the program
void deleteAll(NODE **head) {
	while((*head) != NULL) {
		NODE *temp = (*head);
		(*head) = (*head) -> next;
		free(temp);
	}
}

int main() {
	NODE *head = NULL;

	while(1) {
		printf("[1] Add Node\n");
		printf("[2] Delete Node\n");
		printf("[3] Print Nodes\n");
		printf("[4] Exit\n");

		printf("Enter choice: ");
		int choice;
		scanf("%d", &choice);

		printf("\n");

		if (choice == 1) {
			addNode(&head);
		} else if (choice == 2) {
			deleteNode(&head);
		} else if (choice == 3) {
			printAll(head);
		} else if (choice == 4) {
			break;
		} else {
			printf("Choice is invalid!\n");
		}

		printf("\n");
	}

	deleteAll(&head);
}