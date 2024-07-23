/*
Você está digitando um texto longo com um teclado quebrado. Bem, não tão quebrado. O único problema com o teclado é que às vezes a tecla "home" ou a tecla "end" é automaticamente pressionada (internamente). Você não está ciente deste problema, já que você está focado no texto e nem sequer ligou o monitor! Depois que você terminar de digitar, você pode ver um texto na tela (se você ligar o monitor). Em chinês, podemos chamar este texto de Beiju. Sua tarefa é encontrar o texto Beiju.


Entrada
Há diversos casos de teste. Cada teste é uma única linha que contém pelo menos uma e, no máximo, 100.000 letras, underscores e dois caracteres especiais '[' e ']'. '[' Significa que a tecla "Home" é pressionada internamente, e ']' significa que a tecla "End" é pressionada internamente. A entrada é terminada por fim de arquivo (EOF). O tamanho do arquivo de entrada não excede 5MB.
1-This_is_a_[Beiju]_text
2-[[]][]Happy_Birthday_Tsinghua_University
3


Saída
Para cada caso, imprimir o texto Beiju na tela.
1-BeijuThis_is_a__text
2-Happy_Birthday_Tsinghua_University
3
*/



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
