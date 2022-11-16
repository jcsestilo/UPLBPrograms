/*
	lab08_estilo_A
	Jan Coleen Estilo
	Lab06 - Shared Memory
*/

#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>
#include<sys/types.h>	//for new types declared
#include<sys/ipc.h>		//for interprocess communication
#include<sys/shm.h>		//for shared memory
#define MY_KEY 125

int main() {
	FILE* file;
	// char buffer[bufferLength];

	char buffer[5];
	int testCases;

	// open input file in reading mode
	file = fopen("input.txt", "r");

	if (file == NULL){
		printf("File cannot be opened \n");
		return 0;
	}

	// first line, number of test cases
	fgets(buffer, 5, file);
	testCases = atoi(buffer);

	int rowA, colA, rowB, colB;
	// for(int i=0; i<testCases; i++){

		// get the row and column of the matrix
		fgets(buffer, 5, file);
		char *ptr = strtok(buffer, " ");
		rowA = atoi(ptr);
		ptr = strtok(NULL, " ");
		colA = atoi(ptr);

		// after getting the row and column for the matrix A, let us loop to get the matrix values
		// segment_id = shmget(key, 10*sizeof(int), 0666);
		matrixA = shmget(MY_KEY, )

		// printf("%d, %d\n", rowA, colA);

	// }

}