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