/*
	MEDCCunanan:
	Contains an example of reading n lines of data using the feof() function.

	FILE INPUT:
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

	//Loop that iterates until the file reaches the EOF character
	while(!feof(fp)) { //feof() checks if you are at the EOF
		fscanf(fp, " %[^\n]s", storage[i]);
		i++;
	}		

	//Prints the output read from the file
	for(int count=0; count < i; count++) {
		printf("%s\n", storage[count]);
	}

	//Closes the file
	fclose(fp);

}