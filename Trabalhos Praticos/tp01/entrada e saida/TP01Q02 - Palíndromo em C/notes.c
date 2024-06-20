#include <stdio.h>
// #include <ctype.h>
// #include <stdbool.h>

int IsPalindromo(char str[])
{
    int left = 0, right = strlen(str) - 1;

    while (left <= right)
    {
        // while (!isalpha(str[left]) && left <= right) // Pula os caracteres não letras
        //     left++;
        // while (!isalpha(str[right]) && left <= right)
        //     right--;
        if (str[left] != str[right])
        {
            return 0;
        }
        left++;
        right--;
    }
    return 1;
}

//----------------------------------------------------------------------------------------------------------------//

int IsPalindromo(char str[])
{
    int tam = strlen(str);
    for (int i = 0; i < tam / 2; i++) // Percorrer apenas metade da string
    {
        if (str[i] != str[tam - i - 1]) // Comparar o caractere atual com o seu correspondente na outra extremidade
        {
            return 0; // Se encontrarmos caracteres diferentes, não é um palíndromo
        }
    }
    return 1; // Se o loop terminar sem retornar, é um palíndromo
}

//----------------------------------------------------------------------------------------------------------------//

int IsPalindromo(char str[])
{
    int tam = strlen(str);
    for (int i = 0, j = tam - 1; i < j; i++, j--)
    {
        if (str[i] != str[j])
        {
            return 0; // If characters don't match, it's not a palindrome
        }
    }
    return 1; // If the loop completes, it's a palindrome
}

//----------------------------------------------------------------------------------------------------------------//

int main(void)
{
    char string[1000];

    char FIM[] = "FIM";
    // char num[] = {'1','2'};

    fgets(string, 1000, stdin);
    // scanf(" %[^\n]%*c", string);

    while (strcmp(string, "FIM") != 0)
    {
        TirarEnter(string);

        if (IsPalindromo(string) == 0)
        {
            printf("NAO\n");
        }
        else
        {
            printf("SIM\n");
        }

        fgets(string, 1000, stdin);
    }

    return 0;
}