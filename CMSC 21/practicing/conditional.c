#include <stdio.h>
#define PI 3.14159

int main() {

    float l,w,b,h,r,area;
    int choice;

    printf("Please choose one: \n");
    printf("[1] Rectangle \n");
    printf("[2] Triangle \n");
    printf("[3] Circle \n");
    scanf("%d", &choice);

    if (choice ==1)
    {
        printf("Enter length: ");
        scanf("%f",&l);
        printf("Enter width: ");
        scanf("%f",&w);

        area = l*w;
        printf("The area of a rectangle with length %f and width %f is %f.\n",l,w,area);

    }

    else if (choice==2)
    {
        printf("Enter height: ");
        scanf("%f",&h);
        printf("Enter base: ");
        scanf("%f",&b);

        area = (b*h)/2;
        printf("The area of a triangle with height %f and base %f is %f.\n",h,b,area);
    }

    else if (choice==3)
    {
        printf("Enter radius: ");
        scanf("%f",&r);

        area = PI*r*r;
        printf("The area of a circle with radius %f is %f.\n",r,area);
    }

    return 0;
}