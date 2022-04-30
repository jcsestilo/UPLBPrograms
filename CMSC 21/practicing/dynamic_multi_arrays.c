//Dynamic Multidimensional Arrays
#include <stdio.h>
#include <stdlib.h>

int main() {
    int **array; //number of asterisks determines the number of dimensions of the array
    int rowsize, colsize, i,j;

    printf("Enter number of rows: ");
    scanf("%d", &rowsize);
    printf("Enter number of columns: ");
    scanf("%d", &colsize);

    //create array of int pointers
    array = (int**) malloc(sizeof(int*)*rowsize);
    //for each array of int pointers allocate int arrays
    for(i=0; i<rowsize; i++) {
        array[i] = (int*) malloc(sizeof(int)*colsize);
    }

    //Putting Values in the Array
    for(i=0; i<rowsize; i++) {
        for(j=0; j<colsize; j++) {
            array[i][j] = i*j;
        }
    }

    //Printing Values of the Array

    for(i=0; i<rowsize; i++) {
        for(j=0; j<colsize; j++) {
            printf("%d\t", array[i][j]);
        }
        printf("\n");
    }

    //Deallocation
    for(i=0; i<rowsize; i++)
        free(array[i]); //free muna yung contents ni array
    free(array); // free array itself

    return 0;   
}