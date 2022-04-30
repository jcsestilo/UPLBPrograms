/*
	MEDCCunanan:
	Contains an example of structure defined using structure tags and some structure variable declaration
*/

#include <stdio.h>

struct student {
	char lastName[50];
	char firstName[30];
	int age;
	float grades[42];
	float gwa;
};

int main() {
	struct student harry, ron;
	struct student gryffindor[10];
}