/*
	MEDCCunanan:
	Contains examples on operations of structure variables
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
	struct demigod *love_interest;

	// int questYear[10];
	// struct demigod me;
} halfblood;

int main() {
	//Structure Variable Declarations
	struct demigod percy, annabeth, *demigodPointer;
	halfblood theSeven[7];

	//theSeven[0] = percy + annabeth; -> NOT VALID arithmetic operation
	//if (percy == annabeth); 		  -> NOT VALID relational operation

	//OPERATION #1: DOT OPERATOR
	percy.love_interest = NULL;
	strcpy(percy.firstName, "Perseus");
	strcpy(percy.lastName, "Jackson");

	percy.love_interest = &annabeth;

	printf("Enter birth month: ");
	scanf("%d", &percy.birthday.month);

	printf("Enter birth date: ");
	scanf("%d", &percy.birthday.day);

	printf("Enter birth year: ");
	scanf("%d", &percy.birthday.year);

	percy.age = 2020 - percy.birthday.year;

	strcpy(percy.parents.motherName, "Sally Jackson");
	strcpy(percy.parents.fatherName, "Poseidon");

	printf("\nNAME: %s %s\n", percy.firstName, percy.lastName);
	printf("BIRTHDAY: %d-%d-%d\n", percy.birthday.month, percy.birthday.day, percy.birthday.year);
	printf("AGE: %d\n", percy.age);
	printf("MOTHER: %s\n", percy.parents.motherName);
	printf("FATHER: %s\n\n", percy.parents.fatherName);

	//OPERATION #2: ASSIGNING A STRUCTURE VARIABLE TO ANOTHER
	annabeth = percy;

	theSeven[0] = percy;
	theSeven[1] = annabeth;

	printf("NAME: %s %s\n", annabeth.firstName, annabeth.lastName);
	printf("BIRTHDAY: %d-%d-%d\n", annabeth.birthday.month, annabeth.birthday.day, annabeth.birthday.year);
	printf("AGE: %d\n", annabeth.age);
	printf("MOTHER: %s\n", annabeth.parents.motherName);
	printf("FATHER: %s\n\n", annabeth.parents.fatherName);

	printf("NAME: %s %s\n", theSeven[0].firstName, theSeven[0].lastName);
	printf("BIRTHDAY: %d-%d-%d\n", theSeven[0].birthday.month, theSeven[0].birthday.day, theSeven[0].birthday.year);
	printf("AGE: %d\n", theSeven[0].age);
	printf("MOTHER: %s\n", theSeven[0].parents.motherName);
	printf("FATHER: %s\n\n", theSeven[0].parents.fatherName);
	
	//OPERATION #3: sizeof()
	int size = sizeof (struct demigod);
	int size1 = sizeof(struct birthdate);
	int size2 = sizeof(parentage);

	printf("SIZE: %d\n", size);
	printf("SIZE: %d\n", size1);
	printf("SIZE: %d\n\n", size2);

	//OPERATION #4: TAKING THE ADDRESS
	demigodPointer = &annabeth;

	//OPERATION #4.5: ARROW OPERATOR
	strcpy(demigodPointer->firstName, "Annabeth");
	strcpy(demigodPointer->lastName, "Chase");

	demigodPointer->birthday.month = 7;
	demigodPointer->birthday.day = 12;

	(*demigodPointer).birthday.year = 1993;
	(*demigodPointer).age = 2020 - demigodPointer->birthday.year;

	strcpy((*demigodPointer).parents.motherName, "Athena");
	strcpy(demigodPointer->parents.fatherName, "Frederick Chase");

	demigodPointer->love_interest = &percy;

	printf("NAME: %s %s\n", annabeth.firstName, annabeth.lastName);
	printf("BIRTHDAY: %d-%d-%d\n", annabeth.birthday.month, annabeth.birthday.day, annabeth.birthday.year);
	printf("AGE: %d\n", annabeth.age);
	printf("MOTHER: %s\n", annabeth.parents.motherName);
	printf("FATHER: %s\n\n", annabeth.parents.fatherName);

}