#include <stdio.h>
#include <stdlib.h>

int main(){
    float *grades;
    int i, size = 3;

    grades = (float *) malloc(sizeof(float) * size);

    for (int i = 0; i < size; i++)
    {
        printf("Enter your grade for subject %d: ", i+1);
        scanf("%f", &grades[i]);
    }

    for ( i = 0; i < size; i++)
    {
        printf("Your grade in subject %d is %f.\n", i+1, grades[i]);
    }

    free(grades);
    
    
}