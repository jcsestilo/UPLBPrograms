/* 
	MEDCCunanan: 
	Contains an example of structure defined using structure tags and the type-definition and some structure variable declaration 
*/

#include <stdio.h>

typedef struct student {
	char lastName[50];
	char firstName[30];
	int age;
	float grades[42];
	float gwa;
} pupil;

int main() {
	struct student harry, ron;
	struct student gryffindor[10];

	pupil hermione;
	pupil slytherin[10];
}