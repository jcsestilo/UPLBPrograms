/*
	MEDCCunanan:
	Contains an example of reading n lines of data using the a counter value for the elements in the file.

	FILE INPUT:
		3
		James Carstairs
		William Herondale
		Tessa Gray
*/

#include <stdio.h>

int main() {
	char james[30], tessa[30], will[19];

	//Reads data from the file
	FILE *fp = fopen("names.txt", "r");
	
	//Storage for array of strings
	char storage[10][30];
	int i=0;

	//Loads the counter for the number of elements
	fscanf(fp, "%d\n", &i);

	//Uses the counter to iterate n times over the file
	for(int j=0; j < i; j++) { 
		fscanf(fp, " %[^\n]s", storage[j]);
	}

	//Prints the output read from the file
	for(int count=0; count < i; count++) {
		printf("%s\n", storage[count]);
	}

	//Closes the file
	fclose(fp);
}