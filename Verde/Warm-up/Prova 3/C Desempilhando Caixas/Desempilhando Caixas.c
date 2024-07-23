/*
Joãozinho e sua família acabaram de se mudar. Antes da mudança, ele colocou todos os seus livros dentro de várias caixas numeradas. Para facilitar a retirada dos livros, ele fez um inventário, indicando em qual caixa cada livro foi colocado, e o guardou na caixa de número 1. Chegando no seu novo quarto, ele viu que seus pais guardaram as caixas em várias pilhas, arrumadas em fila, com cada pilha encostada na pilha seguinte.


Joãozinho é um garoto muito sistemático. Por isso, antes de abrir qualquer outra caixa, ele quer recuperar seu inventário. Joãozinho também é um garoto muito desajeitado. Para tirar uma caixa de uma pilha, ele precisa que a caixa esteja no topo da pilha e que ao menos um de seus lados, não importa qual, esteja livre (isto é, não tenham nenhuma caixa adjacente).


Para isso, Joãozinho precisa desempilhar algumas das caixas. Como o quarto dele é bem grande, ele sempre tem espaço para colocar as caixas retiradas em outro lugar, sem mexer nas pilhas que os pais dele montaram. Para minimizar seu esforço, Joãozinho quer que você escreva um programa que, dadas as posições das caixas nas pilhas, determine quantas caixas Joãozinho precisa desempilhar para recuperar seu inventário.


Entrada
A entrada é composta de vários casos de teste. A primeira linha de cada caso de teste contém dois números inteiros N e P , indicando, respectivamente, o número de caixas e o número de pilhas (1 ≤ P ≤ N ≤ 1.000). As caixas são numeradas seqüencialmente de 1 a N.


Cada uma das P linhas seguintes descreve uma pilha. Cada linha contém um inteiro Qi, indicando quantas caixas há na pilha i, seguido de um espaço em branco, seguido de uma lista de Qi números, que são os identificadores das caixas. Os elementos da lista são separados por um espaço em branco. Todas as pilhas contêm pelo menos uma caixa, e todas as caixas aparecem exatamente uma vez na entrada. As caixas em cada pilha são listadas em ordem, da base até o topo da pilha. Todas as caixas têm o mesmo formato.
O final da entrada é indicado por N = P = 0.
A entrada deve ser lida da entrada padrão.
1-4 3
2-1 3
3-2 1 2
4-1 4
5-4 3
6-1 3
7-2 2 1
8-1 4
9-0 0
10

Saída
Para cada caso de teste da entrada, seu programa deve imprimir uma única linha, contendo um único inteiro: o número mínimo de caixas, além da caixa 1, que Joãozinho precisa desempilhar para recuperar o seu inventário.
A saída deve ser escrita na saída padrão.
1-2
2-0
3

*/

#include <stdio.h>
#include <stdlib.h>

typedef struct Celula
{
    int numero;
    struct Celula *prox;
} Celula;

typedef struct Pilha
{
    Celula *topo;
    int maxCaixas;
} Pilha;

typedef struct Fila
{
    Pilha *pilhas;
    int numPilhas;
} Fila;

void lerPilhas(int posLinha, int num, Pilha *p)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));

    if (posLinha == 0)
    {
        p->maxCaixas = num;
    }
    else
    {
        nova->numero = num;
        nova->prox = p->topo;
        p->topo = nova;
    }
}

void liberarCaixas(Fila *c)
{
    for (int i = 0; i < c->numPilhas; i++)
    {
        Celula *atual = c->pilhas[i].topo;
        while (atual != NULL)
        {
            Celula *prox = atual->prox;
            free(atual);
            atual = prox;
        }
    }
    free(c->pilhas);
}

void inicializarCaixas(Fila *c, int numPilhas, int maxCaixas)
{
    c->pilhas = (Pilha *)malloc(numPilhas * sizeof(Pilha));
    c->numPilhas = numPilhas;
    for (int i = 0; i < numPilhas; i++)
    {
        c->pilhas[i].topo = NULL;
        c->pilhas[i].maxCaixas = maxCaixas;
    }
}

int main()
{
    int nCaixas, pPilhas;
    scanf("%d %d", &nCaixas, &pPilhas);

    while (nCaixas != 0 && pPilhas != 0)
    {
        Fila caixas;
        inicializarCaixas(&caixas, pPilhas, nCaixas);

        for (int i = 0; i < pPilhas; i++)
        {
            int numCaixas;
            scanf("%d", &numCaixas);

            for (int j = 0; j < numCaixas; j++)
            {
                int numeroCaixa;
                scanf("%d", &numeroCaixa);
                lerPilhas(j, numeroCaixa, &caixas.pilhas[i]);
            }
        }

        int caixasDesempilhadas = 0;
        int encontrouCaixaUm = 0;

        for (int i = 0; i < pPilhas && !encontrouCaixaUm; i++)
        {
            Celula *celulaAtual = caixas.pilhas[i].topo;
            while (celulaAtual != NULL && !encontrouCaixaUm)
            {
                if (celulaAtual->numero == 1)
                {
                    encontrouCaixaUm = 1;
                }
                else if (celulaAtual->numero != 1)
                {
                    caixasDesempilhadas += 1;
                }
                celulaAtual = celulaAtual->prox;
            }
        } // PQ TA IMPRIMINDO UMMMMMM??????

        printf("%d\n", caixasDesempilhadas);

        liberarCaixas(&caixas);

        scanf("%d %d", &nCaixas, &pPilhas);
    }

    return 0;
}

// cls && gcc Practice.c && a

// void enumeraCaixas(Fila *c)
// {
//     int nPilhas = c->numPilhas;
//     int numeros[c->pilhas->maxCaixas];

//     for (Celula *i = c->pilhas->topo; i != NULL; i = i->prox)
//     {
//         for (int j = 0; j < c->pilhas->maxCaixas; j++)
//         {
//             if (numeros[j] != NULL)
//             {
//                 i->numero = numeros[j];
//             }
//         }
//     }
// }