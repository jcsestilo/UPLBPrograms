//Multidimensional Arrays as Parameters
#include <stdio.h>

void printMultTable(int A[10][10]) { //will int **A work?
    int i,j;
    printf("Table\n");
     for(i=0; i<10; i++) {
         for(j=0; j<10; j++) {
             printf("%d,", A[i][j]);
         }
         printf("\n");
     }
}

int main() {
    int mult[10][10];
    int i,j;

    for(i=0; i<10; i++) {// for each row int the table
        for(j=0; j<10; j++) {// for each column in the table
            mult[i][j]=i*j;
        }
    }

    printMultTable(mult);
    return 0;
}