/*
	MEDCCunanan:
	Contains an example of file writing using fprintf() and fputc().

	FILE OUTPUT:
		William Herondale
		William Herondale
		James Carstairs
		Tessa Gray
*/

#include<stdio.h>
#include<string.h>

int main() {
	char will[] = "William Herondale";
	char james[] = "James Carstairs";
	char tessa[] = "Tessa Gray";

	//Re-writes the data in the file
	FILE *fp = fopen("names.txt", "w");

	//Saves string using fprintf
	fprintf(fp, "%s\n", will);

	//Saves every letter in the string using fputc
	int length = strlen(will);
	for(int i=0; i < length; i++) {
		fputc(will[i], fp);
	}

	fclose(fp);

	//Appends the next data to the file
	fp = fopen("names.txt", "a");

	fprintf(fp, "\n%s\n", james);
	fprintf(fp, "%s\n", tessa);

	fclose(fp);
}