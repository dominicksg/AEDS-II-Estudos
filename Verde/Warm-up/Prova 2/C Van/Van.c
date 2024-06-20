#include <stdio.h>
#include <string.h>

typedef struct
{
    char nome[50];
    char regiao;
    int distancia;
} Aluno;

void lerAlunos(Aluno alunos[], int nEntradas)
{
    for (int i = 0; i < nEntradas; i++)
    {
        // seta apenas se for ponteiro para estrutura
        scanf("%s", alunos[i].nome);     // como é um array de estruturas, o acesso é feito usando o ponto
        scanf(" %c", &alunos[i].regiao); // o espaço antes do %c consome qualquer espaço em branco anterior
        scanf("%d", &alunos[i].distancia);
    }
}

void organizaAlunos(Aluno alunos[], int nEntradas)
{
    for (int i = 0; i < nEntradas - 1; i++)
    {
        int menor = i;
        for (int j = i + 1; j < nEntradas; j++)
        {
            if (alunos[i].distancia > alunos[j].distancia || (alunos[i].distancia == alunos[j].distancia && (strcmp(alunos[j].nome, alunos[i].nome) < 0)))
            {
                i = j;
            }
        }

        Aluno tmp = alunos[i];
        alunos[i] = alunos[menor];
        alunos[menor] = tmp;
    }
}

void imprimeAlunos(Aluno alunos[], int nEntradas)
{
    // printf("----------------\n");
    for (int i = 0; i < nEntradas; i++)
    {
        printf("%s\n", alunos[i].nome);
        // printf(" %c", alunos[i].regiao);
        // printf(" %d\n", alunos[i].distancia);
    }
}

int main()
{
    int nEntradas;

    scanf("%d", &nEntradas);
    Aluno alunos[nEntradas];

    lerAlunos(alunos, nEntradas);
    organizaAlunos(alunos, nEntradas);

    imprimeAlunos(alunos, nEntradas);
}
