/*
	MEDCCunanan:
	Contains examples on passing structures as parameters to functions (pass by value)
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

//Pass By Value: Whole Structure
void printDemigodInformation(halfblood character) {
	printf("NAME: %s %s\n", character.firstName, character.lastName);
	printf("BIRTHDAY: %d-%d-%d\n", character.birthday.month, character.birthday.day, character.birthday.year);
	printf("AGE: %d\n", character.age);
	printf("MOTHER: %s\n", character.parents.motherName);
	printf("FATHER: %s\n", character.parents.fatherName);

	if (character.love_interest != NULL) {
		printf("LOVE INTEREST: %s %s\n\n", character.love_interest->firstName, character.love_interest->lastName);
	} else {
		printf("Sad no love interest :<\n\n");
	}
}

//Pass By Value: Individual Members of the Structure
void printDemigodInformation1(char *first, char *last, int age, parentage p, struct birthdate b, halfblood *l) {
	printf("NAME: %s %s\n", first, last);
	printf("BIRTHDAY: %d-%d-%d\n", b.month, b.day, b.year);
	printf("AGE: %d\n", age);
	printf("MOTHER: %s\n", p.motherName);
	printf("FATHER: %s\n", p.fatherName);

	if (l != NULL) {
		printf("LOVE INTEREST: %s %s\n\n", l->firstName, l->lastName);
	} else {
		printf("Sad no love interest :<\n\n");
	}

}

int main() {\
	struct demigod percy, annabeth, *demigodPointer;
	halfblood theSeven[7];

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

	printDemigodInformation(percy); //FUNCTION CALL

	annabeth = percy;

	theSeven[0] = percy;
	theSeven[1] = annabeth;

	printDemigodInformation(annabeth); //FUNCTION CALL
	printDemigodInformation(theSeven[0]); //FUNCTION CALL

	demigodPointer = &annabeth;

	strcpy(demigodPointer->firstName, "Annabeth");
	strcpy(demigodPointer->lastName, "Chase");

	demigodPointer->birthday.month = 7;
	demigodPointer->birthday.day = 12;

	(*demigodPointer).birthday.year = 1993;
	(*demigodPointer).age = 2020 - demigodPointer->birthday.year;

	strcpy((*demigodPointer).parents.motherName, "Athena");
	strcpy(demigodPointer->parents.fatherName, "Frederick Chase");

	demigodPointer->love_interest = &percy;

	printDemigodInformation(annabeth); //FUNCTION CALL
	printDemigodInformation1(annabeth.firstName, annabeth.lastName, annabeth.age, annabeth.parents, annabeth.birthday, annabeth.love_interest); //FUNCTION CALL
}