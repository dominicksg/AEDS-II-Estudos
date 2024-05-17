#include <stdio.h>

int main()
{
    int num = 0;

    scanf("%d", &num);

    while (num !=0)
    {

        if (num % 2 == 0)
        {
            printf("P\n");
        }
        else
        {
            printf("I\n");
        }

        scanf("%d", &num);
    }
    return 0;
}