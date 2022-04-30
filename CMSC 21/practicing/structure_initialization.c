/*
	MEDCCunanan:
	Contains examples on initialization of structure variables
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
	//Initializes all of the members of the structure
	struct demigod will = {"Will", "Solace", 15, {"Apollo", "Naomi"}, {8, 23, 1995}, NULL};

	//Initializes some members of the structure (0 or NULL for uninitialized data)
	halfblood nico = {"Nico", "Di Angelo", 14, {"Hades", "Maria di Angelo"}, {1, 28}};

	//Assigning an existing structure variable to a new one
	struct demigod young = nico;

	//Assigning existing variables to the individual fields during initialization
	struct demigod *single = NULL;
	int age = 12;
	struct demigod unknown = {"?", "?", age, nico.parents, nico.birthday, single};

	//Updates unknown's first name
	strcpy(unknown.firstName, nico.firstName);
	printf("NAME: %s\n", unknown.firstName);
}