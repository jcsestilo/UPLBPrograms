#include<stdio.h>
#include<stdlib.h> //for malloc
#include<pthread.h> //for threads
#include<string.h>

typedef struct ARG{
	int Arow;
	int Bcol;
	int ans;
}args;

int **A;
int **B;
int numRowA, numRowB;
int numColA, numColB;

void *multiply(void *argument){
	args * temp;
	temp = (args *) argument;
	// will be used for the result
	int *result = (int*) malloc(sizeof(int));
	// put the matrix in temporary pointer variables
	int *matrixA = *(A);
	int *matrixB = *(B);
	// printf("%d, %d\n", temp->Arow, temp->Bcol);

	// get the row of A in the row temp->Arow
	int rowA[numColA];
	for(int i=0; i<numColA; i++){
		rowA[i] = *(matrixA+temp->Arow*numColA+i);
	}

	// get the column of B in the column temp->Bcol
	int colB[numRowB];
	for(int i=0; i<numRowB; i++){
		colB[i] = *(matrixB+i*numColB+temp->Bcol);
	}

	// compute for the result
	for(int i=0; i<numColA; i++){ // we can use either numColA or numRowB since they are always the same
		temp->ans += rowA[i] * colB[i];
		// printf("rowA[i] * colB[i]: %d\n", rowA[i] * colB[i]);
	}
	pthread_exit(NULL);
}

int main(){
	args *arguments; //dynamic number of arguments since the number of threads is unknown;
		//pwede nyo tong gawing 2D array, mahihirapan lang kayo mag-loop
	char buffer[255], *ptr;
	FILE *fp;
	int testCases, threadsAmount;
	int *matrixA, *matrixB;

	fp = fopen("matrix.in", "r");
	if(fp!=NULL){
		//read file here
		//you can use fscanf for reading the first and second lines
		fscanf(fp, "%d ", &testCases);
		fscanf(fp, "%d %d ", &numRowA, &numColA);

		// read matrix A
		matrixA = (int *)malloc(numRowA*numColA*sizeof(int));
		A = &matrixA;
		// check if error
		if(matrixA==NULL){
			printf("Error in allocating for matrix A");
			exit(1);
		}

		for(int i=0; i<numRowA; i++){
			fgets(buffer, 255, fp);
			ptr = strtok(buffer, " ");
			for(int j=0; j<numColA; j++){
				*(matrixA + i*numColA+ j) = atoi(ptr);
				ptr = strtok(NULL, " ");
			}
		}
		// read matrix B
		fscanf(fp, "%d %d ", &numRowB, &numColB);
		//check if the size is invalid, i.e. colA != rowB
		if(numColA != numRowB){
			printf("Matrix multiplication cannot be conducted.\n");
			exit(1);
		}

		matrixB = (int *)malloc(numRowB*numColB*sizeof(int));
		B = &matrixB;
		// check if error
		if(matrixB==NULL){
			printf("Error in allocating for matrix B");
			exit(1);
		}
		// get values for matrix B
		for(int i=0; i<numRowB; i++){
			fgets(buffer, 255, fp);
			ptr = strtok(buffer, " ");
			for(int j=0; j<numColB; j++){
				*(matrixB + i*numColB+ j) = atoi(ptr);
				ptr = strtok(NULL, " ");
			}
		}

		//print Matrix A
		printf("Matrix A:\n");
		for(int i=0; i<numRowA; i++){
			for(int j=0; j<numColA; j++){
				printf("%d\t", *(matrixA + i*numColA+ j));
			}
			printf("\n");
		}

		//print Matrix B
		printf("Matrix B:\n");
		for(int i=0; i<numRowB; i++){
			for(int j=0; j<numColB; j++){
				printf("%d\t", *(matrixB + i*numColB+ j));
			}
			printf("\n");
		}

		//create your threads here. Pass to the thread the row of A and the column of B they need to check.
		threadsAmount = numRowA * numColB;
		pthread_t tid[threadsAmount];
		int *temp[threadsAmount];
		arguments = (args *)malloc(threadsAmount*sizeof(args));
		for(int i=0; i<numRowA; i++){
			for(int j=0; j<numColB; j++){
				arguments[i*numColB+j].Arow = i;
				arguments[i*numColB+j].Bcol = j;
				arguments[i*numColB+j].ans = 0;
				pthread_create(&tid[i*numColB+j], NULL, multiply, (void *) &arguments[i*numColB+j]);
			}
		}
		//join your threads here
		for(int i=0;i<threadsAmount;i+=1){
			pthread_join(tid[i], NULL);
		}

		//manage the return values of the threads here
		int resultingMatrix[numRowA][numColB];
		for(int i=0; i<numRowA; i++){
			for(int j=0; j<numColB; j++){
				resultingMatrix[i][j] = arguments[i*numColB+j].ans;
			}
		}

		//print the solution here
		printf("Resulting Matrix:\n");
		for(int i=0; i<numRowA; i++){
			for(int j=0; j<numColB; j++){
				printf("%d\t", resultingMatrix[i][j]);
			}
			printf("\n");
		}
	}else{
		printf("File not found!\n");
		
	}
	// close the file
	fclose(fp);
	return 0;
}