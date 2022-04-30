/*
	MEDCCunanan
	Creates a program that uses structures to get the information of demigods and store them in an array. 

	After the program runs, the information will be loaded to the array.
	After the program terminates, the information will be saved to a file.

	FUNCTIONS AVAILABLE:
		- Add Demigods
		- Print All Demigods
		- Edit Demigod (Name)
		- Save Demigods
		- LOad Demigods
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
		printf("PARENTS: %s and %s\n\n", camphalfblood[i].parents.motherName, camphalfblood[i].parents.fatherName);
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

//Saves the demigods available in the array to a file.
void saveDemigods(struct demigod *camphalfblood, int count) {
	FILE *fp = fopen("demigods.txt", "w");
	
	//Saves the counter to keep track how many demigods are there.
	fprintf(fp, "%d\n", count); 

	for(int i=0; i < count; i++) {
		fprintf(fp, "%s %s\n", camphalfblood[i].firstName, camphalfblood[i].lastName);
		fprintf(fp, "%d %d %d\n", camphalfblood[i].birthday.month, camphalfblood[i].birthday.day, camphalfblood[i].birthday.year);
		fprintf(fp, "%d\n", camphalfblood[i].age);
		fprintf(fp, "%s %s\n", camphalfblood[i].parents.motherName, camphalfblood[i].parents.fatherName);
	}

	printf("Successfully saved data!\n");

	fclose(fp);
}

//Loads the demigods available in the file to the array.
void loadDemigods(struct demigod *camphalfblood, int *count) {
	FILE *fp = fopen("demigods.txt", "r");

	//Updates the counter of the array to add the demigods in the array.
	fscanf(fp, "%d\n", count);


	//Uses the count for the number of iterations of load statements
	for(int i=0; i < (*count); i++) {
		fscanf(fp, "%s %s\n", camphalfblood[i].firstName, camphalfblood[i].lastName);
		fscanf(fp, "%d %d %d\n", &camphalfblood[i].birthday.month, &camphalfblood[i].birthday.day, &camphalfblood[i].birthday.year);
		fscanf(fp, "%d\n", &camphalfblood[i].age);
		fscanf(fp, "%s %s\n", camphalfblood[i].parents.motherName, camphalfblood[i].parents.fatherName);
	}

	printf("Successfully loaded data!\n");

	fclose(fp);
}

int main() {
	struct demigod camphalfblood[20];
	int choice;
	int index = 0;

	//Calls the loadDemigods function each time the program is run
	loadDemigods(camphalfblood, &index);

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
		} else if (choice == 6) {
			break;
		} 

		printf("\n");
	}

	//Calls the saveDemigods function each time the user picks exit
	saveDemigods(camphalfblood, index);
}