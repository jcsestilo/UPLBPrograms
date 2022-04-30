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

int swap(int *x, int *y){
    int temp;
    
    temp = *x; // assign temp to what x is pointing to
    *x = *y; // x-pointing-to will become y-pointing-to 
    *y = temp; // y-pointing-to will become temp's value
}
int prime(int i, int tempN){
    if(tempN == i){return 0;} 
    else if(tempN % i == 0){return 1;} 
    else{return prime(i+1,tempN);} 
    
}
int printLargest(int primeArr[]){
    
    printf("Prime numbers: ");

    for (int i = 0; i < 3; i++)
    {
        if (primeArr[0]==0) {
            printf("No prime numbers.");
            break;
        }

        printf("%d ",primeArr[i]);
    }
}
int main()
{
    int x, y, primeArr[3], counter=0;
    
    printf("Enter an integer x: "); // getting x
    scanf("%d", &x);
    
    printf("Enter an integer y: "); // getting y
    scanf("%d", &y);
    
    if ((x<0)&&(y<0)){
        printf("Invalid range!\n");
        exit(0);
    }
    else if (x==y&& prime(2,x)!=0)
    {
        printf("Invalid range!\n");
        exit(0);
    }
    else if (x==y&& prime(2,x)==0)
    {
        printf("Prime numbers: %d\n",x);
        exit(0);
    }
    
    if(x>y) swap(&x, &y); // swap function
    
    // check for prime
    for (x; x <= y; x++)
    {
        if (prime(2,x)==0)
        {
            primeArr[counter] = x;
            counter++;
            if(counter==3) counter=0;
        }
        
    }


    printLargest(primeArr);
    printf("\n");
    return 0;
}

