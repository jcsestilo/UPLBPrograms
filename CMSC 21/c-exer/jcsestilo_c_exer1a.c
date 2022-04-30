/* Name: Jan Coleen S. Estilo
Contributors: Nathaniel Enciso, Jan Andrew Germinal
Reference: https://www.onlinemath4all.com/calendar-problems.html 
Contribution: We discussed on the algorithm of the codes but did the coding on our own. We also 
collaborated in a Discord Server so we can ask questions real time.
*/

#include <stdio.h>

int daysOfMonth(int mm);

int main() {
    int oddDays2021 = 4, month, totalDays=1, oddDays, monthTotoo=1;
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

    // Printing kemerut
    printf("1 ");

    if (month==1) printf("January ");
    else if (month==2) printf("February ");
    else if (month==3) printf("March ");
    else if (month==4) printf("April ");
    else if (month==5) printf("May ");
    else if (month==6) printf("June ");
    else if (month==7) printf("July ");
    else if (month==8) printf("August ");
    else if (month==9) printf("September ");
    else if (month==10) printf("October ");
    else if (month==11) printf("November ");
    else printf("December ");

    printf("2021 is ");

    // This is what I am talking about in line 52.
    if (oddDays==0) printf("Sunday\n");
    else if (oddDays==1) printf("Monday\n");
    else if (oddDays==2) printf("Tuesday\n");
    else if (oddDays==3) printf("Wednesday\n");
    else if (oddDays==4) printf("Thursday\n");
    else if (oddDays==5) printf("Friday\n");
    else printf("Saturday\n");
    
    return 0; // exit
    // Thanks for coming to my TED Talk : D

}

int daysOfMonth(int mm) {
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