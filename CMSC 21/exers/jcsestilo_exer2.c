// Jan Coleen S. Estilo
#include <stdio.h>
#include <stdlib.h>

int prime(int i, int tempN);

int main()
{
    int n; // n is the input of the user.
    printf("Enter an integer: ");
    scanf("%d", &n);
    
    if (n<=2)
    {
        printf("No prime numbers present in this range.\n");
        exit(0);
    }
    printf("Prime number/s between 1 to %d are: ",n);

    
    
    for (int i = 2; i <= n; i++) { //  i is to be used in the loop at parang 'umaangat'
        if(prime(2,i)==0){
            printf("%d  ",i);
        // yung arguments ko is 2 kasi 1 is not a prime number. i ko is 2 kasi kapag 1 ginamit ko, lagi lang yung
        // masasatisfy ng second condition sa function and we wont have a result.
        }    
    }
    printf("\n");

    return 0;
}

int prime(int i, int tempN){
    if(tempN == i){return 0;} // if i is umakyat kay tempN, or equal kay tempN, ibig sabihin prime si tempN.
    else if(tempN % i == 0){return 1;} // if walang remainder yung modulo nung dalawa, ibig sabihin hindi prime si 
                                       // tempN at divisible ito sa other num than 1.
    else{prime(i+1,tempN);} //aakyat nang aakyat si i hanggang sa makamit niya ang either sa if condition o else if condition.
    
}
