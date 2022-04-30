/*
	MEDCCunanan
	Creates a program that uses structures to get the information of demigods and store them in an array. 

	FUNCTIONS AVAILABLE:
		- Add Demigods
		- Print All Demigods
		- Edit Demigod (Name)
*/

#include<stdio.h>
#include <string.h>

struct birthdate {
	int month, day, year;
};

typedef struct {
	char fatherName[30];
	char motherName[30];
} parentage;

typedef struct demigod {
	char firstName[50];
	char lastName[30];
	int age;

	parentage parents;
	struct birthdate birthday;
} halfblood;

//Asks for the information about the demigods and stores them in the array
void addDemigod(struct demigod *camphalfblood, int i) {
	printf("Enter name: ");
	scanf("%s %s", camphalfblood[i].firstName, camphalfblood[i].lastName);

	printf("Enter birth month: ");
	scanf("%d", &camphalfblood[i].birthday.month);

	printf("Enter birth day: ");
	scanf("%d", &camphalfblood[i].birthday.day);

	printf("Enter birth year: ");
	scanf("%d", &camphalfblood[i].birthday.year);

	camphalfblood[i].age = 2020 - camphalfblood[i].birthday.year;

	printf("Enter mother's name: ");
	scanf("%s", camphalfblood[i].parents.motherName);

	printf("Enter father's name: ");
	scanf("%s", camphalfblood[i].parents.fatherName);
}

//Prints all of the available information about a demigod
void printAll(struct demigod *camphalfblood, int index) {
	for(int i=0; i < index; i++) {
		printf("NAME: %s %s\n", camphalfblood[i].firstName, camphalfblood[i].lastName);
		printf("BIRTHDAY: %d %d %d\n", camphalfblood[i].birthday.month, camphalfblood[i].birthday.day, camphalfblood[i].birthday.year);
		printf("AGE: %d\n", camphalfblood[i].age);
		printf("PARENTS: %s and %s\n", camphalfblood[i].parents.motherName, camphalfblood[i].parents.fatherName);
	}
}

//Asks for the demigod to edit. If found, the name of the demigod will be updated.
void editDemigod(struct demigod *camphalfblood, int index) {
	for(int i=0; i < index; i++) {
		printf("[%d] %s %s\n", i, camphalfblood[i].firstName, camphalfblood[i].lastName);
	}

	int choice;
	printf("Enter index: ");
	scanf("%d", &choice);

	printf("Enter new first name: ");
	scanf("%s", camphalfblood[choice].firstName);
	printf("Enter new last name: ");
	scanf("%s", camphalfblood[choice].lastName);
}

int main() {
	struct demigod camphalfblood[20]; //Array of Structures
	int choice;
	int index = 0;

	while(1) {
		printf("[1] Add Demigod\n");
		printf("[2] Print All Demigods\n");
		printf("[3] Edit Demigod\n");
		printf("[4] Exit\n");

		printf("Enter choice: ");
		scanf("%d", &choice);

		printf("\n");

		if (choice == 1) {
			addDemigod(camphalfblood, index);
			index++;
			printf("Successfully added!\n");
		} else if (choice == 2) {
			printAll(camphalfblood, index);
		} else if (choice == 3) {
			editDemigod(camphalfblood, index);
		} else if (choice == 4) {
			break;
		}

		printf("\n");
	}
}