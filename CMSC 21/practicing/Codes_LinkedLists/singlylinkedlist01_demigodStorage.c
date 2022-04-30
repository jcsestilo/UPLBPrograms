/*
	MEDCCunanan
	Creates a program that demonstrates the basic operations on a singly linked list.

	FUNCTIONS AVAILABLE:
		- Add Demigod at Head
		- Delete Demigod at Tail
		- Edit Age
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
} halfblood;

//Adds new instance of fdemigod at heads
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

	new->next = (*head);
	(*head) = new;

	printf("Successfully added!\n");
}

//Prints all of the demigods available in the linked list
void printAll(struct demigod *head) {
	for(struct demigod *temp = head; temp !=NULL; temp = temp->next) {
		printf("\nNAME: %s\n", temp->name);
		printf("BIRTHDAY: %d %d %d\n", temp->birthday.month, temp->birthday.day, temp->birthday.year);
		printf("AGE: %d\n", temp->age);
		printf("PARENTS: %s and %s\n\n", temp->parents.motherName, temp->parents.fatherName);
	}
}

//Delete demigod at tail
void deleteDemigod(struct demigod **head) {
	if ((*head) == NULL) {
		printf("Still empty!\n");
	} else if ((*head)->next == NULL) {
		struct demigod *temp = (*head);
		(*head) = (*head)->next;
		free(temp);
		printf("Successfully deleted!\n");
	} else {
		struct demigod *temp = (*head);
		while(temp->next->next != NULL) {
			temp = temp->next;
		}

		free(temp->next);
		temp->next = NULL;
		printf("Successfully deleted!\n");
	}
}

//Edits age of the given demigod if found
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
}

//Frees all of the nodes in the linked list
void deleteAll(struct demigod **head) {
	while((*head) != NULL) {
		struct demigod *temp = (*head);
		(*head) = (*head)->next;
		free(temp);
	}
}

int main() {
	struct demigod *head = NULL;
	int choice;

	while(1) {
		printf("[1] Add Demigod at Head\n");
		printf("[2] Delete Demigod at Tail\n");
		printf("[3] Edit Age\n");
		printf("[4] Print All Demigods\n");
		printf("[5] Exit\n");

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
		} else if (choice == 5) {
			break;
		} else if (choice == 3) {
			if (head == NULL) {
				printf("Nothing to edit!\n");
			} else {
				editAge(head);
			}
		}

		printf("\n");
	}

	deleteAll(&head);
}