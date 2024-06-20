#include <stdio.h>
#include <string.h>
#include <stdbool.h>

// #define bool short
// #define true 1
// #define false 0

bool IsPalindromo(char str[])
{
    int tam = strlen(str);
    for (int i = 0; i < tam; i++)
    {
        if (str[i] != str[tam - 1 - i])
            return false;
    }
    return true;
}

bool Verifier(char str[])
{
    if (strcmp(str, "FIM") == 0)
    {
        return false;
    }
    return true;
}

void TirarEnter(char str[])
{
    if (str[strlen(str) - 1] == '\n')
    {
        str[strlen(str) - 1] = '\0';
    }
}

int main(void)
{
    char string[1000];

    scanf(" %[^\n]", string);

    // fgets(string, 1000, stdin);
    // TirarEnter(string);

    while (Verifier(string))
    {
        if (IsPalindromo(string))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }

        scanf(" %[^\n]", string);
        // fgets(string, 1000, stdin);
        // TirarEnter(string);
    }

    return 0;
}
