/*
Jan Coleen S. Estilo

Contributors:
Nathaniel Enciso
Jan Andrew Germinal
Kyla Julienne Macayan

Contribution:
For this exercise, the group has decided to each code on their own due to the 
the fact that we will just be using our code from previous exercises. Instead, 
the group consulted each other while performing the exercise through Discord voice 
and text channels. Furthermore, the group helped debug other collaborator's code 
when they encountered errors.
*/

#include <stdio.h>

int daysOfMonth(int mm) { // code snippet provided
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

int firstDayOfMonth(int oddDays, int month){ // prints first day of the month
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

    if (oddDays==0) printf("Sunday\n");
    else if (oddDays==1) printf("Monday\n");
    else if (oddDays==2) printf("Tuesday\n");
    else if (oddDays==3) printf("Wednesday\n");
    else if (oddDays==4) printf("Thursday\n");
    else if (oddDays==5) printf("Friday\n");
    else printf("Saturday\n");
    
    return 0;
}

int monthCalendar(int oddDays, int month){ //prints calendar
    int position=0;
    char weekDay [7][10] = {
        "SUN",
        "MON",
        "TUES",
        "WED",
        "THURS",
        "FRI",
        "SAT"  };
        
    for (int i = 0; i < 7; i++)
    {
        if (i == 6)
        {
            printf("%s\n", weekDay[i]);
            break;
        }
        
        printf("%s", weekDay[i]);
        printf("\t");
    }
    
    switch (oddDays) {
    case 0:
        position = 0;
        break;
    case 1:
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
    
    int d = 1, days = daysOfMonth(month);

    while (days >= d)
    {
        printf("%d", d);
        position++;

        if (position == 7)
        {
            position = 0;
            printf("\n");
        }
        else {
            printf("\t");
        }
        d++;
        
    }

    printf("\n");
    
    return 0;
}

int main()
{
    int oddDays2021 = 4, month, totalDays=1, oddDays, monthTotoo=1;

    printf("Enter month (1-12): ");
    scanf("%d", &month);
    printf("\n");



    while (monthTotoo < month) // This is to get the total days in the months before. Since totalDays=1, no need to add first day of that month.
    {
        totalDays = totalDays + daysOfMonth(monthTotoo);
        monthTotoo++;
    }

    oddDays = (oddDays2021 + (totalDays % 7)) % 7;
    
    firstDayOfMonth(oddDays,month); // function from c exer 1 a
    
    printf("\n"); // for format
    
    monthCalendar(oddDays,month); // function from c exer 1 b

    return 0;
}