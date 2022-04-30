/*
	MEDCCunanan:
	Contains an example of file opening and closing.

	NOTE: If you are going to run this, make sure that the .txt files are present in your directory. Else, you would get a segmentation fault for closing a file that doesn't exist.
*/

#include <stdio.h>

int main() {
	//Opens the files specified in the path
	FILE *fp = fopen("data.txt", "r");
	FILE *fp1 = fopen("Data/names.txt", "r");

	//Closes the file pointer opened
	fclose(fp);
	fclose(fp1);

	//Opens another file fp2
	FILE *fp2 = fopen("a.txt", "r");

	//Checker to make sure if fp2 was opened - NULL if file does not exist.
	if (fp == NULL) {
		printf("File not opened!\n");
	} else {
		fclose(fp2); //File closing is here because if the file was not found, you don't need to close it.
	}
}