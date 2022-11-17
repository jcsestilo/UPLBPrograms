/*
	lab08_estilo_B
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
#define MY_KEY_A 130
#define MY_KEY_B 131
#define MY_KEY_RES 132

int main(){
	FILE* file;
	char buffer[255], *ptr;
	int testCases;
	int segment_id_A, segment_id_B, segment_id_Res;
	int *matrixA, *matrixB, *matrixRes;
	int rowA, colA, rowB, colB;
	// open input file in reading mode
	file = fopen("input.txt", "r");

	if (file == NULL){
		printf("File cannot be opened \n");
		exit(1);
	}

	// first line, number of test cases
	fgets(buffer, 5, file);
	testCases = atoi(buffer);

		
	// MATRIX A
	// get the row and column of the matrix
	fgets(buffer, 5, file);
	ptr = strtok(buffer, " ");
	rowA = atoi(ptr);
	ptr = strtok(NULL, " ");
	colA = atoi(ptr);

	// MATRIX A
	// after getting the row and column for the matrix A, let us get the matrix values
	segment_id_A = shmget(MY_KEY_A, rowA*colA*sizeof(int), 0666);
	//check for errors
	if(segment_id_A < 0){
		printf("shmget error\n");
		exit(1);
	}

	// attach the shared memory
	matrixA = shmat(segment_id_A, NULL, 0);
	// check for errors
	if(matrixA == (int *) -1){
		printf("shmat error\n");
		exit(1);
	}

	// put the matrix (from file) to trash
	for(int i=0; i<rowA; i++){
		fgets(buffer, 255, file);
	}

	printf("Matrix A\n");
	for (int i = 0; i < rowA; i++) {
		for (int j = 0; j < colA; j++) {
			printf("%d ", *(matrixA+i*colA+j));
		}
		printf("\n");
	}

	// MATRIX B
	// get the row and column of the matrix
	fgets(buffer, 5, file);
	ptr = strtok(buffer, " ");
	rowB = atoi(ptr);
	ptr = strtok(NULL, " ");
	colB = atoi(ptr);
	// printf("%d %d\n", rowB, colB);

	segment_id_B = shmget(MY_KEY_B, rowB*colB*sizeof(int), 0666);
	if(segment_id_B < 0){
		printf("shmget error\n");
		exit(1);
	}

	matrixB = shmat(segment_id_B, NULL, 0);
	if(matrixB == (int*)-1){
		printf("shmat error\n");
		exit(1);
	}

	for(int i=0; i<rowB; i++){
		fgets(buffer, 255, file);
	}

	printf("Matrix B\n");
	for (int i = 0; i < rowB; i++) {
		for (int j = 0; j < colB; j++) {
			printf("%d ", *(matrixB+i*colB+j));
		}
		printf("\n");
	}

	// make a 2d matrix for the result. this is also in the shared memory
	segment_id_Res = shmget(MY_KEY_RES, rowA*colB*sizeof(int), 0666);
	// check for errors
	if(segment_id_Res < 0){
		printf("shmget error\n");
		exit(1);
	}
	matrixRes = shmat(segment_id_Res, NULL, 0);
	if(matrixA == (int*)-1){
		printf("shmat error\n");
		exit(1);
	}

	// determine the number of columns that the B process will solve
	int numOfColsA = colB/2;
	if(colB % 2 == 1){
		numOfColsA++;
	}

	// solve the matrix for the column numOfColsA(the last column process A solved) to colB(the last column)
	for(int i=0; i<rowA; i++){
		for(int j=numOfColsA-1; j<colB; j++){
			for(int k=0; k<colA; k++){
				*(matrixRes+i*colB+j) += *(matrixA+i*colA+k) * *(matrixB+k*colB+j);
			}
		}
	}

	printf("Matrix Result\n");
	// print the resulting matrix
	for(int i=0; i<rowA; i++){
		for(int j=0; j<colB; j++){
			printf("%d\t", *(matrixRes+i*colB+j));
		}
		printf("\n");
	}

	// detach the matrices
	shmdt(matrixA);
	shmdt(matrixB);
	shmdt(matrixRes);

}