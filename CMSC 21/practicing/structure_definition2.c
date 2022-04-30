/*
	MEDCCunanan:
	Contains an example of structure defined using the type-definition and some structure variable declaration
*/

#include <stdio.h>

typedef struct {
	char lastName[50];
	char firstName[30];
	int age;
	float grades[42];
	float gwa;
} student;

int main() {
	student hermione;
	student slytherin[10];
}