/*
Depois de um belo dia de aula é função das vans levarem os estudantes para suas respectivas casas. Mas o que muitos não sabem é que além dos gastos e manutenção da van o motorista precisa ter uma rota para entregar os passageiros em suas casas. Como você é o menino(a) da informática, ele pediu sua ajuda para desenvolver essa rota ordenando os alunos pela distância (da menor para a maior), pela região (em ordem alfabética) e por último pelo nome.


Entrada
Ele te dá a quantidade Q de alunos que não faltaram, o nome do aluno A e uma sigla para a região onde ele mora S ("L" Leste, "N" Norte, "O" Oeste, "S" Sul), e C que representa o custo da entrada da cidade até sua casa. A saída dos casos será (EOF).
1-5
2-Samuel O 1
3-Fabricio L 1
4-Emanuel S 3
5-Kaio S 20
6-Hugo N 90
7

Saída
A saída será uma lista das pessoas na ordem em que devem ser entregues.
1-Fabricio
2-Samuel
3-Emanuel
4-Kaio
5-Hugo
6

*/

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
