#include <stdio.h>

void checkfile(FILE *filetext)
{
    if (filetext == NULL)
    {
        printf("Error opening the file.\n");
    }
}

int main()
{
    int num;
    // char str[100];
    FILE *arquivo = fopen("texto.txt", "w");

    checkfile(arquivo);

    // printf("Digite o numero de valores a serem inseridos: ");
    scanf("%d", &num);

    float valor;
    for (int i = 0; i < num; i++)
    {
        // printf("Digite o valor %d: ", i + 1);
        scanf("%f", &valor);
        fprintf(arquivo, "%f\n", valor);
    }

    fclose(arquivo);
    arquivo = fopen("texto.txt", "r");

    checkfile(arquivo);

    // Posicionando o cursor no final do arquivo
    fseek(arquivo, 0, SEEK_END);
    long posicao = ftell(arquivo) - 2;

    // Lendo os valores do arquivo de trás para frente
    while (posicao >= 0)
    {
        fseek(arquivo, --posicao, SEEK_SET);

        if (fgetc(arquivo) == '\n') // || (posicao==0)
        {
            fscanf(arquivo, "%f", &valor);
            printf("%g\n", valor);

            // fseek(arquivo, posicao +1, SEEK_SET); // Movendo o cursor para trás um float (4 bytes)
            // printf("PASSEI");

            // fgets(str, 100, arquivo);
            // printf("%s\n", str);

            posicao--;
        }
        /*
         fseek(arquivo, posicao - sizeof(float), SEEK_SET); // Movendo o cursor para trás um float (4 bytes)

         fscanf(arquivo, "%f", &valor); // Lendo o valor

         printf("%.3f\n", valor); // Mostrando o valor na tela
         posicao -= sizeof(float); // Atualizando a posição do cursor
         */
    }

    fseek(arquivo, 0, SEEK_SET);
    fscanf(arquivo, "%f", &valor);
    printf("%g", valor);

    // Fechando o arquivo
    fclose(arquivo);

    return 0;
}