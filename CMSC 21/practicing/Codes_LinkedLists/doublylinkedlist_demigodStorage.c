/*
	MEDCCunanan
	Creates a program that demonstrates the basic operations on a doubly linked list.

	FUNCTIONS AVAILABLE:
		- Add Demigod (Sorted by Age)
		- Delete Demigod
		- Edit Age (For you to Explore)
		- Print All Demigods
*/

#include<stdio.h>
#include <string.h>
#include <stdlib.h>

struct birthdate {
	int month, day, year;
};

typedef struct {
	char fatherName[30];
	char motherName[30];
} parentage;

typedef struct demigod {
	char name[50];
	int age;
	parentage parents;
	struct birthdate birthday;
	struct demigod *next;
	struct demigod *prev;
} halfblood;

//Adds new node to linked list; Sorted by Age, Descending
void addDemigod(struct demigod **head) {
	struct demigod *new = (struct demigod *) malloc (sizeof(struct demigod));

	printf("Enter name: ");
	scanf("%s", new->name);

	printf("Enter birth month: ");
	scanf("%d", &new->birthday.month);

	printf("Enter birth day: ");
	scanf("%d", &new->birthday.day);

	printf("Enter birth year: ");
	scanf("%d", &new->birthday.year);

	new->age = 2020 - new->birthday.year;

	printf("Enter mother's name: ");
	scanf("%s", new->parents.motherName);

	printf("Enter father's name: ");
	scanf("%s", new->parents.fatherName);

	new->next = NULL;
	new->prev = NULL;

	if ((*head) == NULL) { //Insert at empty
		(*head) = new;
	} else if (new->age > (*head)->age) { //Insert at head
		new->next = (*head);
		(*head)->prev = new;
		(*head) = new;
	} else {
		struct demigod *temp = (*head);
		while(temp->next != NULL && temp->next->age > new->age) {
			temp = temp->next;
		}

		if (temp->next == NULL) { //Insert at tail
			new->prev = temp;
			temp->next = new;
		} else { //Insert at middle
			new->next = temp->next;
			temp->next->prev = new;
			new->prev = temp;
			temp->next = new;
		}
	}

	printf("Successfully added demigod!\n");
}

//Prints all of the information available in the linked list
void printAll(struct demigod *head) {
	for(struct demigod *temp = head; temp !=NULL; temp = temp->next) {
		printf("\nNAME: %s\n", temp->name);
		printf("BIRTHDAY: %d %d %d\n", temp->birthday.month, temp->birthday.day, temp->birthday.year);
		printf("AGE: %d\n", temp->age);
		printf("PARENTS: %s and %s\n\n", temp->parents.motherName, temp->parents.fatherName);
	}
}

//Deletes a demigod using the name given by the user
void deleteDemigod(struct demigod **head) {
	if ((*head) == NULL) { //Empty linked list
		printf("Still empty!\n");
	} else {
		char name[30];
		printf("Enter name: ");
		scanf("%s", name);

		struct demigod *del = (*head);
		while(del != NULL) {
			if (strcmp(del->name, name) == 0) {
				break;
			}
			del = del->next;
		}

		if (del == NULL) { //Not found
			printf("Demigod not found!\n");
		} else {
			if (del == (*head)) { //Delete at head
				(*head) = (*head) -> next;

				if ((*head) != NULL) {
					(*head)->prev = NULL;
				}
				free(del);
			} else {
				struct demigod *temp = (*head);
				while(temp->next != del) {
					temp = temp->next;
				}

				if (temp->next->next == NULL) { //Delete at tail
					temp->next = NULL;
					free(del);
				} else { //Delete at middle
					del->next->prev = temp;
					temp->next = del->next;
					free(del);
				}
			}

			printf("Successfully deleted demigod!\n");
		}
	}
}

//Edits the age of a demigod given the name
void editAge(struct demigod *head) {
	char name[50];
	printf("Enter demigod name: ");
	scanf("%s", name);

	int checker = 0;

	for(struct demigod*temp = head; temp != NULL; temp = temp->next) {
		if(strcmp(name, temp->name) == 0) {
			printf("Enter updated age: ");
			scanf("%d", &temp->age);

			checker = 1;

			printf("Successfully edited age!\n");
		}
	}

	if (checker == 0) {
		printf("Demigod not found!\n");
	}

	//NOTE: You still need to modify this because we sorted the linked list using the age so if you update the age, the sorting would not be correct anymore. 
	//HINT: You should disconnect the node to be edited in the linked-list, and then re-insert it so that you won't have to re-sort the whole linked list.
}

//Frees all the nodes of the linked list
void deleteAll(struct demigod **head) {
	while((*head) != NULL) {
		struct demigod *temp = (*head);
		(*head) = (*head)->next;
		free(temp);
	}
}

//Prints all of the information available in the linked list in reverse
void printAllReverse(struct demigod *head) {
	struct demigod *temp = head;
	while(temp->next != NULL) {
		temp = temp->next;
	}

	while(temp != NULL) {
		printf("\nNAME: %s\n", temp->name);
		printf("BIRTHDAY: %d %d %d\n", temp->birthday.month, temp->birthday.day, temp->birthday.year);
		printf("AGE: %d\n", temp->age);
		printf("PARENTS: %s and %s\n\n", temp->parents.motherName, temp->parents.fatherName);
		temp = temp->prev;
	}
}

int main() {
	struct demigod *head = NULL;
	int choice;

	while(1) {
		printf("[1] Add Demigod\n");
		printf("[2] Delete Demigod\n");
		printf("[3] Edit Age\n");
		printf("[4] Print All Demigods\n");
		printf("[5] Print All in Reverse\n");
		printf("[6] Exit\n");

		printf("Enter choice: ");
		scanf("%d", &choice);

		printf("\n");

		if (choice == 1) {
			addDemigod(&head);
		} else if (choice == 2) {
			deleteDemigod(&head);
		} else if (choice == 4) {
			if (head == NULL) {
				printf("Nothing to print!\n");
			} else {
				printAll(head);
			}
		} else if (choice == 6) {
			break;
		} else if (choice == 3) {
			if (head == NULL) {
				printf("Nothing to edit!\n");
			} else {
				editAge(head);
			}
		} else if (choice == 5) {
			if (head == NULL) {
				printf("Nothing to print!\n");
			} else {
				printAllReverse(head);
			}
		} else{
			printf("Invalid choice!\n");
		}

		printf("\n");
	}

	deleteAll(&head);
}