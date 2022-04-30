/* Jan Coleen S. Estilo
Exercise 1 - CMSC 21 */

#include <stdio.h>

int main() {
    int choice;
    float w,h,result=0;

    do
    {
        printf("⋇⋆✦⋆⋇ M E N U ⋇⋆✦⋆⋇\n");
        printf("⋇⋆✦⋆⋇ [1] Kilogram and Centimeters\n");
        printf("⋇⋆✦⋆⋇ [2] Pounds and Feet\n");
        printf("⋇⋆✦⋆⋇ [3] Exit\n");
        printf("Input: ");
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            printf("Enter your weight (kg): ");
            scanf("%f",&w);
            printf("Enter your height (cm): ");
            scanf("%f",&h);

            result = (w/(h*h)) * 10000;
            break;
        case 2:
            printf("Enter your weight (pounds): ");
            scanf("%f",&w);
            printf("Enter your height (ft): ");
            scanf("%f",&h);
            float inch = h*12;

            result = (w/(inch*inch))*703;
            break;
        case 3:
            continue;
        default:
            printf("Invalid input!\n\n");
            continue;
        }

        printf("Your BMI is %f.\n",result);
        if (result < 18.5)
        {
            printf("You are underweight.\n");
        }
        else if ((result >= 18.5) && (result <= 24.9))
        {
            printf("You have normal weight.\n");
        }
        else if ((result > 24.9) && (result <= 29.9))
        {
            printf("You are overweight.\n");
        }
        else
        {
            printf("You are obese.\n");
        }
        
    } while (choice != 3);
    printf("Goodbye! (╯°□°）╯︵ ┻━┻  \n");

    return 0;
}