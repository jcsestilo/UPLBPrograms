/*
	lab08_estilo_A
	Jan Coleen Estilo
	Lab06 - Shared Memory
	note: 1 test case only.
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
int main() {
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
	// printf("%d %d\n", rowA, colA);

	// MATRIX A
	// after getting the row and column for the matrix A, let us get the matrix values
	segment_id_A = shmget(MY_KEY_A, rowA*colA*sizeof(int), IPC_CREAT | 0666);
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

	// put the matrix to our 2d array
	for(int i=0; i<rowA; i++){
		fgets(buffer, 255, file);
		ptr = strtok(buffer, " ");
		for(int j=0; j<colA; j++){
			*(matrixA+i*colA+j) = atoi(ptr);
			ptr = strtok(NULL, " ");
		}
	}

	// MATRIX B
	// get the row and column of the matrix
	fgets(buffer, 5, file);
	ptr = strtok(buffer, " ");
	rowB = atoi(ptr);
	ptr = strtok(NULL, " ");
	colB = atoi(ptr);

	// check if multiplication can be done
	if(colA!=rowB){
		printf("Matrix Multiplication cannot be done in this test case.\n");
		exit(1);
	}
	segment_id_B = shmget(MY_KEY_B, rowB*colB*sizeof(int), IPC_CREAT | 0666);
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
		ptr = strtok(buffer, " ");
		for(int j=0; j<colB; j++){
			*(matrixB+i*colB+j) = atoi(ptr);
			ptr = strtok(NULL, " ");
		}
	}

	// make a 2d matrix for the result. this is also in the shared memory
	segment_id_Res = shmget(MY_KEY_RES, rowA*colB*sizeof(int), IPC_CREAT | 0666);
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
	for (int i=0; i<rowA; i++){
		for(int j=0; j<colB; j++){
			*(matrixRes+i*colB+j) = 0;
		}
	}

	// determine the number of columns that the A process will solve
	int numOfCols = colB/2;
	if(colB % 2 == 1){
		numOfCols++;
	}
	// printf("numOfCols: %d\n", numOfCols);

	// let us solve the matrix for the column 0 to numOfCols
	for(int i=0; i<rowA; i++){
		for(int j=0; j<numOfCols; j++){
			for (int k=0; k<colA; k++){
				// matrixRes[i][j] += matrixA[i][k] * matrixB[k][j];
				*(matrixRes+i*colB+j) += *(matrixA+i*colA+k) * *(matrixB+k*colB+j);
			}
		}
	}

	printf("Run ./lab08_estilo_B.c\n");
	sleep(10);
	printf("10 seconds have elapsed.\n");

	// detach the matrices
	shmdt(matrixA);
	shmdt(matrixB);
	shmdt(matrixRes);

	// remove the shared memory
	shmctl(segment_id_A, IPC_RMID, NULL);
	shmctl(segment_id_B, IPC_RMID, NULL);
	shmctl(segment_id_Res, IPC_RMID, NULL);
}