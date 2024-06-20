#include <stdio.h>
#include <string.h>

#define bool short
#define true 1
#define false 0

bool IsVogal(char str[])
{
    int tam = strlen(str);
    int tamchecker = strlen(str);
    // printf("%d", tamchecker);
    for (int i = 0; i < tam; i++)
    {
        if ((str[i] == 'a' || str[i] == 'A') ||
            (str[i] == 'e' || str[i] == 'E') ||
            (str[i] == 'i' || str[i] == 'I') ||
            (str[i] == 'o' || str[i] == 'O') ||
            (str[i] == 'u' || str[i] == 'U') ||
            str[i] == ' ')
        {
            tamchecker -= 1;
        }
        /*
        if (tamchecker == 0)
        {
            return true;
        }
        */
    }
    return !tamchecker;
    // return false;
}

bool IsConsoante(char str[])
{
    int tam = strlen(str);
    int tamchecker = 0;
    // printf("%d", tamchecker);
    for (int i = 0; i < tam; i++)
    {
        // Verifica se é uma letra do alfabeto
        if ((str[i] >= 'a' && str[i] <= 'z') || (str[i] >= 'A' && str[i] <= 'Z'))
        {
            // Verifica se é uma vogal
            if (str[i] == 'a' || str[i] == 'A' ||
                str[i] == 'e' || str[i] == 'E' ||
                str[i] == 'i' || str[i] == 'I' ||
                str[i] == 'o' || str[i] == 'O' ||
                str[i] == 'u' || str[i] == 'U')
            {
                tamchecker--; // Decrementa  se for uma vogal
            }
        }
        else
        {
            tamchecker--; // Decrementa se não for uma letra do alfabeto
        }
    }
    // printf("%d", tamchecker);
    return !tamchecker;
}

bool IsInteiro(char str[])
{
    int tam = strlen(str);
    int tamchecker = strlen(str);
    for (int i = 0; i < tam; i++)
    {
        if (str[i] >= '0' && str[i] <= '9')
        {
            tamchecker -= 1;
        }
    }
    // printf("%d", tamchecker);
    return !tamchecker;
}

bool IsReal(char str[])
{
    int tam = strlen(str);
    int tamchecker = strlen(str);
    int virgula = 0;
    for (int i = 0; i < tam; i++)
    {
        if ((str[i] >= '0') && (str[i] <= '9'))
        {
            tamchecker -= 1;
        }
        else if ((str[i] == '.') || (str[i] == ','))
        {
            tamchecker -= 1;
            virgula += 1;
        }
        if (virgula >= 2)
        {
            return false;
        }
    }
    // tamchecker = tamchecker - virgula;
    // printf("%d", tamchecker);
    return !tamchecker;
}

bool isFim(char s[])
{
    return ((strlen(s) >= 3) && (s[0] == 'F') && (s[1] == 'I') && (s[2] == 'M'));
}

int main()
{
    char string[1000];

    scanf(" %[^\n]", string);

    while (isFim(string) == false)
    {
        if (IsVogal(string) == true)
        {
            printf("SIM ");
        }
        else
        {
            printf("NAO ");
        }
        //------------------------------------------//
        if (IsConsoante(string) == true)
        {
            printf("SIM ");
        }
        else
        {
            printf("NAO ");
        }
        //------------------------------------------//
        if (IsInteiro(string) == true)
        {
            printf("SIM ");
        }
        else
        {
            printf("NAO ");
        }
        //------------------------------------------//
        if (IsReal(string) == true)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
        //------------------------------------------//

        scanf(" %[^\n]", string);
    }

    return 0;
}