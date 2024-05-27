#include <stdio.h>
#include <string.h>

int main()
{
    char linha[1000];
    char linhafinal[1000];

    FILE *arquivo;

    arquivo = fopen("arquivo.txt", "r");

    while (fgets(linha, sizeof(linha), arquivo) != NULL)
    {
        int beijupos = 0;

        for (int i = 0; linha[i] != '\n'; i++)
        {
            if (linha[i] == 'B' && linha[i + 1] == 'e' && linha[i + 2] == 'i' && linha[i + 3] == 'j' && linha[i + 4] == 'u')
            {
                beijupos = i;
                break;
            }
        }
        // printf("%d\n", beijupos);

        int j = 0; // Index for linhafinal
        if (beijupos > 0)
        {
            strcpy(linhafinal, "Beiju");
            j = 5;
            for (int i = 0; linha[i] != '\0'; i++)
            {
                if (i < beijupos || i >= beijupos + 5)
                {
                    if (linha[i] != '[' && linha[i] != ']')
                    {
                        linhafinal[j++] = linha[i];
                    }
                }
            }
        }
        else
        {
            for (int i = 0; linha[i] != '\0'; i++)
            {
                if (linha[i] != '[' && linha[i] != ']')
                {
                    linhafinal[j++] = linha[i];
                }
            }
        }
        linhafinal[j] = '\0'; 
        printf("%s", linhafinal);
    }
    fclose(arquivo);
    return 0;
}
