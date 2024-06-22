#include <stdio.h>
#include <stdlib.h>

typedef struct Celula
{
    int elemento;
    struct Celula *prox;

} Celula;

Celula *ConstrutorCelula(int x)
{
    Celula *novaCelula = (Celula *)malloc(sizeof(Celula));

    novaCelula->elemento = x;
    novaCelula->prox = NULL;

    return novaCelula;
}

typedef struct Fila
{
    Celula *primeiro;
    Celula *ultimo;
} Fila;

Fila *ConstrutorFila()
{
    Fila *fila = (Fila *)malloc(sizeof(Fila));
    fila->primeiro = ConstrutorCelula(0);
    fila->ultimo = fila->primeiro;

    return fila;
}

void inserir(Fila *fila, int x)
{
    Celula *tmp = ConstrutorCelula(x);

    fila->ultimo->prox = tmp;
    fila->ultimo = fila->ultimo->prox;

    tmp = NULL;
    free(tmp);
}

void remover(Fila *fila)
{
    if (fila->primeiro == fila->ultimo)
    {
        printf("FILA VAZIA");
    }
    else
    {
        Celula *tmp = fila->primeiro;

        fila->primeiro = fila->primeiro->prox;
        tmp->prox = NULL;

        tmp = NULL;
        free(tmp);
    }
}

void mostrar(Fila *fila)
{
    for (Celula *i = fila->primeiro->prox; i != NULL; i = i->prox)
    {
        printf("%d ", i->elemento);
    }
    printf("\n");
}

int main()
{
    Fila *fila = ConstrutorFila();

    mostrar(fila);
    inserir(fila, 1);
    mostrar(fila);
    inserir(fila, 2);
    mostrar(fila);
    inserir(fila, 3);
    mostrar(fila);
    remover(fila);
    mostrar(fila);
    remover(fila);
    mostrar(fila);

    // printf("%d", fila->primeiro->elemento);

    return 0;
}