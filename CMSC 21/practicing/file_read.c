/*
	MEDCCunanan:
	Contains an example of file reading using fscanf(), fgetc(), fgets().

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

	//Reads string using fscanf - %[^\n]s used to get strings with spaces
	fscanf(fp, "%[^\n]s", james);
	printf("%s", james);

	//Reads 19 characters using fgetc (17 characters from William Herondale + 2 newline)
	for(int i=0; i < 19; i++) {
		will[i] = fgetc(fp);
	}
	printf("%s", will);

	//Reads string using fgets
	fgets(tessa, 30, fp); //Doesn't read a newline because it ends using the EOF character
	printf("%s\n", tessa); 

	fclose(fp);
	
}