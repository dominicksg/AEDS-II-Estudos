#include <stdio.h>
#include <string.h>

int main()
{
    char letra;
    char linha[1000];
    char linhafinal[1000];
    int beijupos = -1;

    int i = 0;
    FILE *arquivo;

    arquivo = fopen("arquivo.txt", "r");

    while ((letra = fgetc(arquivo)) != EOF)
    {
        if (letra != '[' && letra != ']')
        {
            linha[i++] = letra;
        }
    }
    for (i = 0; linha[i] != '\0'; i++)
    {
        if (linha[i] == 'B' && linha[i + 1] == 'e' && linha[i + 2] == 'i' && linha[i + 3] == 'j' && linha[i + 4] == 'u')
        {
            beijupos = i;
            // break;
        }
    }

    if (beijupos != -1)
    {
        linhafinal[0] = 'B';
        linhafinal[1] = 'e';
        linhafinal[2] = 'i';
        linhafinal[3] = 'j';
        linhafinal[4] = 'u';

        int j = 5;
        for (i = 0; linha[i] != '\0'; i++)
        {
            if (i < beijupos || i >= beijupos + 5)
            {
                linhafinal[j++] = linha[i];
            }
        }
        linhafinal[j] = '\0';
    }
    else
    {
        strcpy(linhafinal, linha);
    }

    printf("%s", linhafinal);

    fclose(arquivo);
    return 0;
}