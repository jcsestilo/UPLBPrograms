#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>  
#include<malloc.h>
#include<sys/time.h>
#include<pthread.h>	// PTHREADS

void FCCBoundary(float *M, int size){
    int x1, x2, y1, y2, i, j;
    char str[20];

    // Interpolation for within the Bounding Box FCC method
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            if( !(i%10==0 && j%10==0) ){
                if (i%10==0){
                    x1 = (j/10)*10;
                    x2 = x1 + 10;
                    if (x2 >= size)
                    {
                        x2 = size-1;
                    }
                    y1 = M[i*size+x1];
                    y2 = M[i*size+x2];
                    sprintf(str, "%d.%d", (j-x1)/(x2-x1), (j-x1)%(x2-x1));
                    M[i*size+j] = y1 +  ((atof(str)) * (y2-y1));
                } else if(j%10==0) {
                    x1 = (i/10)*10;
                    x2 = x1 + 10;
                    if(x2 >= size){
                        x2 = size-1;
                    }
                    y1 = M[x1*size+j];
                    y2 = M[x2*size+j];
                    sprintf(str, "%d.%d", (i-x1)/(x2-x1), (i-x1)%(x2-x1));
                    M[i*size+j] = y1 +  (atof(str)) * (y2-y1);
                }
            }
        }
        
    }
}

void terrain_inter(float *M, int size, int startingRow, int endingRow){
    int x1, x2, y1, y2, i, j;
    char str[20];

    // Interpolation for inside the Bounding Box FCC method
    for (int i = 0; i < startingRow; i++)
    {
        for (int j = 0; j < endingRow; j++)
        {
            if( (i%10!=0 && j%10!=0) ){
                if (i%10!=0){
                    x1 = (j/10)*10;
                    x2 = x1 + 10;
                    if (x2 >= size)
                    {
                        x2 = size-1;
                    }
                    y1 = M[i*size+x1];
                    y2 = M[i*size+x2];
                    sprintf(str, "%d.%d", (j-x1)/(x2-x1), (j-x1)%(x2-x1));
                    M[i*size+j] = y1 +  ((atof(str)) * (y2-y1));
                } else if(j%10!=0) {
                    x1 = (i/10)*10;
                    x2 = x1 + 10;
                    if(x2 >= size){
                        x2 = size-1;
                    }
                    y1 = M[x1*size+j];
                    y2 = M[x2*size+j];
                    sprintf(str, "%d.%d", (i-x1)/(x2-x1), (i-x1)%(x2-x1));
                    M[i*size+j] = y1 +  (atof(str)) * (y2-y1);
                }
            }
        }
        
    }
    
}

int main(){
    int input_size, input_t, size, startingRow, endingRow, lastPreviousRow=0, rowsAmount;
    struct timeval stop, start;

    // Get user input
    printf("Enter size n: ");
    scanf("%d", &input_size);
    size = input_size + 1;
    printf("Enter amount of threads: ");
    scanf("%d", &input_t);

    // Initialize matrix
    float* M = (float *)malloc((size*size) * sizeof(float));
    memset(M, 0, size*size*sizeof(float));

    // Generate random values from 0 to 1000 in every grid points divisible by 10
    srand ( time(NULL) ); // seeding
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            if (i%10==0 && j%10==0) {
                M[i * size + j] = rand() % 1001;
            }
            
        }
        
    }

    // time_before
    gettimeofday(&start, NULL);

    // Compute for the within the boundary values
    FCCBoundary(M, size); 

    // Divide M into input_t sumatrices and create threads
    // If M size is not divisible by t, do not divide
    if (input_size%input_t!=0)
    {
        printf("Cannot create t submatrices. Size not divisible by t.\n");
    }

    // Create input_t threads
    pthread_t threads[input_t];

    // Divide the matrix
    rowsAmount = ((size - 2)/input_t) + 1;
    for (int i = 0; i < input_t; i++)
    {
        startingRow = lastPreviousRow + 1;
        endingRow = lastPreviousRow + rowsAmount;
        lastPreviousRow = endingRow;
        // The ending row should never be a boundary coordinate
        if (endingRow==size-1)
        {
            endingRow = endingRow - 1;
        }
        
        pthread_create(&threads[i], NULL, terrain_inter, (void *) (M, size, startingRow, endingRow));
    }
    
    // int *ids = (int *) malloc(sizeof(int) * input_t);
    
    // terrain_inter(M, size);
    // time_after
    gettimeofday(&stop, NULL); 
    printf("lab02 < %d\n", size);
    // printing of time elapsed
    printf("time elapsed: %f\n", (double)((stop.tv_sec - start.tv_sec) * 1000000 + stop.tv_usec - start.tv_usec) / 1000000);
    
    // print matrix
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++)
            printf("%f ", M[i*size + j]);
        printf("\n");
    }

    free(M);
    return 0;

}