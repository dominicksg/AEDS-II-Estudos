#include <stdio.h>
#include <string.h>

char tiraParenteses(char *linha)
{
    int tam = strlen(linha);
    char copy[tam + 1];

    int j = 0;
    for (int i = 0; i < tam; i++)
    {
        if (linha[i] != ')' && linha[i] != '(')
        {
            copy[j++] = linha[i];
        }
    }
    copy[j] = '\0';
    strcpy(linha, copy);
}

void posFrase(char *linha)
{
    tiraParenteses(linha);
    int tam = strlen(linha);

    for (int i = 0; i < tam - 1; i++)
    {
        if (linha[i] == '*' || linha[i] == '+' || linha[i] == '-' || linha[i] == '/' || linha[i] == '^')
        {
            char tmp = linha[i];
            linha[i] = linha[i + 1];
            linha[i + 1] = tmp;
            i++;
        }
    }
}

int main()
{
    char linha[300];
    int nEntradas;

    scanf("%d", &nEntradas);

    while (nEntradas > 0)
    {
        scanf(" %[^\n]", linha);
        posFrase(linha);

        printf("%s", linha);
        nEntradas--;
    }
}
// cls && gcc Practice.c && a
