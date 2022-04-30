/*
	MEDCCunanan:
	Contains examples on passing structures as parameters to functions (pass by reference)
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

//PASS BY REFERENCE: Assign values in a member structure
void scanDemigodInformation(struct demigod *member) {
	printf("Enter name: ");
	scanf("%s %s", (*member).firstName, member->lastName);

	printf("Enter age: ");
	scanf("%d", &(member->age));

	printf("Enter mother's name: ");
	scanf("%s", member->parents.motherName);

	printf("Enter father's name: ");
	scanf("%s", member->parents.fatherName);
}

int main() {
	struct demigod hazel, frank, *incognito;

	scanDemigodInformation(&hazel); //FUNCTION CALL

	incognito = &frank;
	scanDemigodInformation(incognito); //FUNCTION CALL

	printDemigodInformation(hazel);
	printDemigodInformation(frank);

}