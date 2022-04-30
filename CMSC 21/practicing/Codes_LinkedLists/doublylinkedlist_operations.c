/*
	MEDCCunanan
	Creates a program that demonstrates the sorted operations on a doubly linked list.

	FUNCTIONS AVAILABLE:
		- Add Node
		- Delete Node
		- Print Next (Prints using next pointer)
		- Print Prev (Prints using the prev pointer)
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct node_tag {
	int data;
	struct node_tag *prev;
	struct node_tag *next;	
} NODE;


//Adds data to the linked-list; Sorted in descending order
void addNode(NODE **head) {
	NODE *new = (NODE *) malloc (sizeof(NODE));
	printf("Enter new node: ");
	scanf("%d", &new->data);
	new->next = NULL;
	new->prev = NULL;

	if ((*head) == NULL) { //Insert at empty
		(*head) = new;
	} else if ((*head)->data < new->data) { //Insert at head
		new->next = (*head);
		(*head)->prev = new;
		(*head) = new;
	} else {
		NODE *temp = (*head);
		while(temp->next != NULL && temp->next->data > new->data) {
			temp = temp->next;
		}

		if (temp->next == NULL) { //Insert at tail
			new->prev = temp;
			temp->next = new;
		} else { //Insert at middle
			new->next = temp->next;
			new->prev = temp;
			temp->next->prev = new;
			temp->next = new;
		}
	}

	printf("Successfully added node!\n");

}

//Deletes the node given by user if found
void deleteNode(NODE **head) {
	int x;
	printf("Enter node to be deleted: ");
	scanf("%d", &x);

	NODE *del = (*head);
	while(del != NULL) {
		if (del->data == x) {
			break;
		}
		del = del->next;
	}

	if (del == NULL) { //Node given by user is not found
		printf("Node not found!\n");
	} else {
		if (del == (*head)) { //Delete at head
			(*head) = (*head)->next;

			if ((*head) != NULL) {
				(*head)->prev = NULL;
			}

			free(del);
		} else { 
			NODE *beforeDel = (*head);
			while(beforeDel->next != del) {
				beforeDel = beforeDel->next;
			}

			if (beforeDel->next->next == NULL) { //Delete at tail
				beforeDel->next = NULL;
				free(del);
			} else { //Delete at middle
				del->next->prev = beforeDel;
				beforeDel->next = del->next;
				free(del);
			}
		}

		printf("Successfully deleted node!\n");
	}

}

//Function that prints from start to end
void printNext(NODE *head) {
	printf("PRINT NEXT: ");
	while(head != NULL) {
		printf("%d ", head->data);
		head = head->next;
	}
	printf("\n");
}

//Function that prints from end to start
void printPrev(NODE *head) {
	NODE *temp = head;
	while(temp->next != NULL) {
		temp = temp->next;
	}

	printf("PRINT PREV: ");
	while(temp != NULL) {
		printf("%d ", temp->data);
		temp = temp->prev;
	}
	printf("\n");

}

//Frees all nodes in the linked list
void deleteAllNodes(NODE **head) {
	while((*head) != NULL) {
		NODE *temp = (*head);
		(*head) = (*head)->next;
		free(temp);
	}
}	

int main() {
	NODE *head = NULL;

	while(1) {
		printf("[1] Add Node\n");
		printf("[2] Delete Node\n");
		printf("[3] Print Next\n");
		printf("[4] Print Prev\n");
		printf("[5] Exit\n");

		int choice;
		printf("Enter choice: ");
		scanf("%d", &choice);

		printf("\n");

		if (choice == 1) {
			addNode(&head);
		} else if (choice == 2) {
			if (head == NULL) {
				printf("Nothing to delete!\n");
			} else {
				deleteNode(&head);
			}
		} else if (choice == 3) {
			if (head == NULL) {
				printf("Nothing to print!\n");
			} else {
				printNext(head);
			}
		} else if (choice == 4) {
			if (head == NULL) {
				printf("Nothing to print!\n");
			} else {
				printPrev(head);
			}
		} else if (choice == 5) {
			printf("Goodbye!\n");
			break;
		} else {
			printf("Invalid choice!\n");
		}

		printf("\n");		
	}

	deleteAllNodes(&head);
}