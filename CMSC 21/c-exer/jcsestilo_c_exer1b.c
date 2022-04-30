/* Jan Coleen S. Estilo
Contributors: Nathaniel Enciso, Jan Andrew Germinal
Contribution: Nat made a guide for us to follow on our own. Jandrew showed a part of his code to be added
in Nat's guide.
*/

#include <stdio.h>

int daysOfMonth(int mm);

int main() {
    int oddDays2021 = 4, month, totalDays=1, oddDays, monthTotoo=1;
    int position=0;
    /*
    1. totalDays=1 because 'yung first day of the month lang ang kinukuha.
    2. How we came up with oddDays2021 = 4:
        For example, get odd days for July 1, 2021. It will be equal to "2020 years + Period from (January 1, 2021) to (July 1, 2021)"
        2020 years:
            = 1600 years + 400 years + 20 years
            No. of odd in 1600 = 0 (1)
            No. of odd in 400 = 0 (2)
            No. of odd in 20 computation:
                5 leap years + 15 ordinary years meron in 20 years
                (5 * 2) + (15 * 1) [Times 2 sa leap year kasi sabi sa reference ay may two odd days every leap year. For ordinary years, 1 odd day lang]
                = 25 days = 3 weeks and 4 days
                Therefore, since may apat na sobra, 4 odd days sa year 2021.
    3. The position is like the "column" nung mga ipiprint.
    */

    printf("Enter month (1-12): ");
    scanf("%d", &month);



    while (monthTotoo < month) // This is to get the total days in the months before. Since totalDays=1, no need to add first day of that month.
    {
        totalDays = totalDays + daysOfMonth(monthTotoo); // iaadd dito lahat nung days from January 1 up to the point of first day of the month.
        // Similar to the computation in line 25. Add lang mga days.
        monthTotoo++; // Increment si monthTotoo hangga't hindi niya nakakamit si month.
    }
    /*
    1. Difference of month and monthTotoo
        month
            Ito yung iiinput ni user. All throughout the program, same lang value nito.
        monthTotoo
            Ito naman yung "umaakyat" doon sa loop above. Para maget kung ilang days ba per month hanggang sa makapunta
            doon sa certain month (user input).
    */

    oddDays = (oddDays2021 + (totalDays % 7)) % 7;
    // (4 + (yung lahat ng inadd na days sa loop converted into weeks tapos kinuha yung sobra)) 
    // Yung result nito ^ imomodulo pa ulit with 7 para makuha ulit yung sobra kapag inadd na si oddDays2021, kung ano magiging result non, doon
    // na madedetermine which day of the week it is.

    printf("SUN\tMON\tTUE\tWED\tTHU\tFRI\tSAT\n");
    
    switch (oddDays)
    {
    case 0:
        position = 0;
        break;
    case 1: // oddDays is 1 so it would be under MON, sa second position siya so dapat may \t
        position = 1;
        printf("\t");
        break;
    case 2:
        position = 2;
        printf("\t\t");
        break;
    case 3:
        position = 3;
        printf("\t\t\t");
        break;
    case 4:
        position = 4;
        printf("\t\t\t\t");
        break;
    case 5:
        position = 5;
        printf("\t\t\t\t\t");
        break;
    default:
        position = 6;
        printf("\t\t\t\t\t\t");
        break;
    }

    int d = 1, days = daysOfMonth(month); // d = 1 kasi first day of the month. days naman is yung total days ng month na yun.


    while (days >= d) // habang hindi pa naaabot ni d si days...
    {
        printf("%d", d); // print yung d
        position++; // increment si position para meron tayong "limit checker" for the calendar or something

        if (position == 7) // kapag nag-increment si position at naging 7 na (meaning lumampas na),
        {
            position = 0; //  babalik siya sa 0 or sa pinakasimula ng calendar.
            printf("\n"); // next line
        }
        else {
            printf("\t"); // else, move lang sa next position
        }
        d++;
        
    }

    printf("\n");
    
    return 0; // exit yey
    // Thanks for coming to my TED Talk : D
}

int daysOfMonth(int mm) { // given code snippet
    switch(mm){
        case 9:
        case 4:
        case 6:
        case 11:
            return(30);
        case 2:
            return(28);
        default:
        return(31);
    }
}