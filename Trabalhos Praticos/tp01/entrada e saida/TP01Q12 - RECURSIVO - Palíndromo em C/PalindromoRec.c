#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool IsPalindromo(char *str, int left, int right) {
    // Caso base: se o índice esquerdo for maior ou igual ao índice direito,
    // significa que a verificação foi concluída com sucesso
    if (left >= right) {
        return true;
    }
    
    // Verifica se os caracteres nas extremidades são iguais
    if (str[left] != str[right]) {
        return false;
    }
    
    // Chama recursivamente a função com os índices atualizados
    return IsPalindromo(str, left + 1, right - 1);
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
        if (IsPalindromo(string, 0, strlen(string)-1))
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