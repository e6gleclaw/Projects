#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct movie
{
    int ID;
    char movie_name[50];
    int rem_tkt;
    float tktprice;
    float rev_ern;
} screen;

void getValue(screen *s, int l)
{
    for (int i = 0; i < l; i++)
    {
        for (int i = 0; i < 28; i++)
        {
            printf("==");
        }
        printf("\n");
        printf("Enter the details of screen number %d:\n", i + 1);
        printf("Enter the Movie ID: ");
        scanf("%d", &s->ID);
        printf("Enter the Movie Name: ");
        scanf(" %[^\n]", s->movie_name);
        printf("Enter the number of remaining tickets: ");
        scanf("%d", &s->rem_tkt);
        printf("Enter the ticket price of the Movie: ");
        scanf("%f", &s->tktprice);
        printf("Enter the revenue earned till now: ");
        scanf("%f", &s->rev_ern);
        s++;
        for (int i = 0; i < 28; i++)
        {
            printf("==");
        }
        printf("\n");
    }
}

void printValue(screen *s, int l)
{
    for (int i = 0; i < l; i++)
    {
        for (int i = 0; i < 28; i++)
        {
            printf("==");
        }
        printf("\n");
        printf("The details of screen number %d:\n", i + 1);
        printf("ID:- %d\n", s->ID);
        printf("Movie name:- %s\n", s->movie_name);
        printf("Remnaining tickets:- %d\n", s->rem_tkt);
        printf("Ticket price:- %f\n", s->tktprice);
        printf("Revenue earned:- %f\n", s->rev_ern);
        s++;
        for (int i = 0; i < 28; i++)
        {
            printf("==");
        }
        printf("\n");
    }
}

int checkAvl(screen *s, int l)
{
    char movnam[50];
    printf("Enter the movie name:");
    scanf(" %[^\n]", movnam);

    int k, cnt = 0, t;

    for (t = 0; t < l; t++)
    {
        k = strcmp(s->movie_name, movnam);

        if (k == 0)
        {
            for (int i = 0; i < 16; i++)
            {
                printf("==");
            }
            printf("\n");
            printf("Remaining Tickets:- %d\n", s->rem_tkt);
            printf("Ticket price:- %f\n", s->tktprice);
            cnt++;
            for (int i = 0; i < 16; i++)
            {
                printf("==");
            }
            printf("\n");
        }

        if (cnt > 0)
            break;
        s++;
    }

    if (cnt == 0)
    {
        printf("The screen with desired movie name is not available\n");
    }
}

void buyticket(screen *s, int l)
{
    char movnam[50];
    printf("Enter the movie name:");
    scanf(" %[^\n]", movnam);
    int f;
    int mp = 0;
    for (int g = 0; g < l; g++)
    {
        f = strcmp(s->movie_name, movnam);

        if (f == 0)
        {
            mp = 1;
            break;
        }

        s++;
    }
    if (mp == 0)
    {
        printf("No Such Movie\n");
        return;
    }
    int numtkt;
    printf("Enter the number of tickets required:");
    scanf("%d", &numtkt);

    int v;

    if ((s->rem_tkt) > numtkt)
    {
        printf("The total ticket price:- %f\n", numtkt * (s->tktprice));
        printf("Do you want to proceed? Press 1 or 0:\n");
        scanf("%d", &v);
        if (v == 1)
        {
            printf("Tickets booked\n");
            s->rem_tkt = s->rem_tkt - numtkt;
            s->rev_ern = s->rev_ern + numtkt * (s->tktprice);
        }
        else if (v == 0)
            printf("Better luck next time\n");

        else
            printf("Invalid input\n");
    }

    else
    {
        printf("Number of tickets ordered is more than remaining. Please enter a valid number of tickets\n");
    }
}

void totalRev(screen *s, int l)
{
    int h;
    for (h = 0; h < l; h++)
    {
        for (int i = 0; i < 28; i++)
        {
            printf("==");
        }
        printf("\n");
        printf("The screen no. %d:\n", h + 1);
        printf("The total revenue earned:- %f\n", s->rev_ern);
        printf("The number of tickets remaining:- %d\n", s->rem_tkt);
        s++;
        for (int i = 0; i < 28; i++)
        {
            printf("==");
        }
        printf("\n");
    }
}

int main()
{
    screen *p;
    int n;
    printf("Enter the number of screens:");
    scanf("%d", &n);
    p = (screen*)malloc(n*sizeof(screen));
    getValue(p, n);

    int option;
    while (1)
    {
        for (int i = 0; i < 28; i++)
        {
            printf("**");
        }
        printf("\n");
        printf("1. Enter 1 to see details of each screen\n2. Enter 2 to check availability of tickets of a movie\n3. Enter 3 to buy a movie ticket \n4. Enter 4 to check total revenue earned per screen\n5. Enter 5 to close the system\n");
        for (int i = 0; i < 28; i++)
        {
            printf("**");
        }
        printf("\n");
        printf("Enter the option:");
        scanf("%d", &option);
        if (option == 1)
            printValue(p, n);

        else if (option == 2)
            checkAvl(p, n);

        else if (option == 3)
            buyticket(p, n);

        else if (option == 4)
            totalRev(p, n);

        else if (option == 5)
            break;

        else
        {
            printf("Invalid Option Entered");
        }
    }

    return 0;
}
