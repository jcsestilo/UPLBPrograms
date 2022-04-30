//Multidimensional Arrays
#include <stdio.h>

int main() {
    int mult[10][5]; //10x5 2d array 
    int i,j;
    //Putting Values in the Array
    for(i=0; i<10; i++) {
        for(j=0; j<5; j++) {
            mult[i][j] = i*j;
        }
    }

    //Printing Values of the Array

    for(i=0; i<10; i++) {
        for(j=0; j<5; j++) {
            printf("%d\t", mult[i][j]);
        }
        printf("\n");
    }

    return 0;
}