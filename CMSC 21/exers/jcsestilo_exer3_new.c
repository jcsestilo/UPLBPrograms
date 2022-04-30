/******************************************************************************
Problem Statement
Create a program that gets thee (3) largest prime numbers from x, to y, x and y are given by the user.
If x is greater than y, swap x and y.
If there are no prime numbers in the given range, the program must print zero (0). If there is only one prime
number, the program must print that number. If there are two prime numbers in the given range, then both
prime numbers are printed.

*******************************************************************************/

#include <stdio.h>
#include <stdlib.h>

void getInput(int *x, int *y){ // I used void because the functio doesn't return anything
    printf("Enter the value for X: "); // Getting the value of x
    scanf("%d", x);

    printf("Enter the value for Y: "); // Getting the value of y
    scanf("%d", y);
}

void swapValues(int *x, int *y){
    int temp;
    
    temp = *x; // Assign temp to what x is pointing to
    *x = *y; // X-pointing-to will become y-pointing-to 
    *y = temp; // Y-pointing-to will become temp's value
}

int primeChecker(int i, int tempN){ // checks if the number is prime
    if(tempN == i) {return 0;} 
    else if(tempN % i == 0) {return 1;}
    else if(tempN < 2) {return 1;}
    else{primeChecker(i+1,tempN);} 
    
}

void getLargest(int x, int y, int *f, int *s, int *t){ // Checks all the value in the range if prime and gets the largest three
    
    int counter=1; // para sa placing kung saan ilalagay yung number
    for (x; x <= y; x++)
    {
        
        if (primeChecker(2,x)==0){ // if prime, ilalagay sa either 1st, 2nd, or 3rd
            if (counter==1) *f = x;
            else if (counter==2) *s = x;
            else if (counter==3) *t = x;
            counter++;
            if (counter==4) counter=1; // para umiikot hihi
        }
    }
}

void printLargest(int *f, int *s, int *t){
    int counterPrime = 0;

    if (*f != 0) counterPrime++;
    if (*s != 0) counterPrime++;
    if (*t != 0) counterPrime++;

    switch (counterPrime)
    {
    case 1:
        printf("There is one prime number: %d\n", *f);
        break;
    case 2:
        printf("There are two prime numbers: %d %d\n", *f, *s);
        break;
    case 3:
        printf("These are three largest prime numbers: %d %d %d\n", *f, *s, *t);
        break;
    default:
        printf("No prime numbers found.\n");
        break;
    }
}

int main(){
    int x, y;
    int first=0, second=0, third=0; // My variables for the three largest

    getInput(&x,&y); // Input function for x and y

    if (x>y) swapValues(&x,&y); // Swap if x is greater
    
    // CHECKERS
    if((x<0)&&(y<0)) { // if negative
        printf("Invalid range!\n");
        exit(0);
        }
    if( (x==y) && (x==1) ) { // if same number and less than 2
        printf("Invalid range!\n");
        exit(0);
        }

    getLargest(x, y, &first, &second, &third);

    printLargest(&first, &second, &third);

    //printf("%d,%d,%d\n", first,second,third);
    
}