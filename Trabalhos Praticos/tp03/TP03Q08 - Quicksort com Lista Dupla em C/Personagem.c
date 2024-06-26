//----------------------------------------------------------------------------------------//
// Avisos
//----------------------------------------------------------------------------------------//
// Olá, esse código provavelmente não está organizado como eu gostaria,
// por conta da urgência das entregas.
// E é normal ter muitas linhas de código comentadas,
// já que é para uma atividade, eu aproveito isso para documentar todo o estudo também.
//----------------------------------------------------------------------------------------//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Lista
{
    char codenomes[10][256]; // Supondo que cada nome não exceda 255 caracteres
} Lista;

void inicializarLista(Lista *lista)
{
    for (int i = 0; i < 10; i++)
    {
        strcpy(lista->codenomes[i], "VAZIO"); // Inicializa todos os nomes como strings vazias
    }
}

typedef struct Personagem
{
    char id[256];
    char name[256];
    Lista alternate_name;
    char house[256];
    char ancestry[256];
    char species[256];
    char patronus[256];
    int hogwartsStaff;   // bool
    int hogwartsStudent; // bool
    char actorName[256];
    int alive;            // bool
    char dateOfBirth[11]; // Formato "dd-mm-yyyy"
    int yearOfBirth;
    char eyeColour[256];
    char gender[256];
    char hairColour[256];
    int wizard; // bool
} Personagem;

typedef struct CelulaLista
{
    Personagem personagem;
    struct CelulaLista *prox;
    struct CelulaLista *ant;
} CelulaLista;

typedef struct ListaPersonagem
{
    int tam;
    CelulaLista *primeiro;
    CelulaLista *ultimo;
} ListaPersonagem;

void inicializarListaPersonagem(ListaPersonagem *lista)
{
    lista->tam = 0;
    lista->primeiro = (CelulaLista *)malloc(sizeof(CelulaLista));
    lista->ultimo = lista->primeiro;
    lista->primeiro->prox = NULL;
    lista->primeiro->ant = NULL;
}

void imprimirLista(const Lista *lista)
{
    printf("{");
    for (int i = 0; i < 10; i++)
    {
        if (strcmp(lista->codenomes[i], "VAZIO") != 0)
        {
            printf("%s", lista->codenomes[i]);
            if (i < 10 - 1 && strcmp(lista->codenomes[i + 1], "VAZIO") != 0)
            {
                printf(","); // Adiciona uma vírgula apenas se houver próximo elemento
            }
        }
    }
    printf("} ## ");
}

void imprimir(const Personagem *p)
{
    printf("[%s ## %s ## ", p->id, p->name);
    imprimirLista(&p->alternate_name);
    printf("%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n",
           p->house, p->ancestry, p->species, p->patronus,
           p->hogwartsStaff ? "true" : "false",
           p->hogwartsStudent ? "true" : "false",
           p->actorName,
           p->alive ? "true" : "false",
           p->dateOfBirth,
           p->yearOfBirth,
           p->eyeColour, p->gender, p->hairColour,
           p->wizard ? "true" : "false");
}

int toUSbool(const char *frase)
{
    return strstr(frase, "VERDADEIRO") != NULL;
}

void limparCaracteresEspeciais(char *string)
{
    int i, j;
    for (i = 0, j = 0; string[i] != '\0'; i++)
    {
        if (string[i] != '\'' && string[i] != '[' && string[i] != ']')
        {
            string[j++] = string[i];
        }
    }
    string[j] = '\0';
}

void processarNomesAlternativos(char *nomesAlternativos, Lista *lista)
{
    for (int i = 0; i < 10; i++)
    {
        strcpy(lista->codenomes[i], "VAZIO");
    }
    char *nome = strtok(nomesAlternativos, ",");
    int i = 0;
    while (nome != NULL && i < 10)
    {
        limparCaracteresEspeciais(nome);
        strcpy(lista->codenomes[i++], nome);
        nome = strtok(NULL, ",");
    }
}

void ler(Personagem *p, char *ID)
{
    // FILE *file = fopen("./characters.csv", "r");
    FILE *file = fopen("/tmp/characters.csv", "r");
    if (!file)
    {
        perror("Erro ao abrir o arquivo");
        return;
    }

    char linha[1024];

    while (fgets(linha, sizeof(linha), file))
    {
        char vetor[18][256];
        int index = 0;
        char *token = linha;
        char *next_token;

        while (token)
        {
            next_token = strchr(token, ';');
            if (next_token)
            {
                *next_token = '\0';
                strcpy(vetor[index++], token);
                token = next_token + 1;
            }
            else
            {
                strcpy(vetor[index++], token);
                break;
            }
        }

        if (strcmp(ID, vetor[0]) == 0)
        {
            strcpy(p->id, vetor[0]);
            strcpy(p->name, vetor[1]);
            processarNomesAlternativos(vetor[2], &p->alternate_name);
            strcpy(p->house, vetor[3]);
            strcpy(p->ancestry, vetor[4]);
            strcpy(p->species, vetor[5]);
            strcpy(p->patronus, vetor[6]);
            p->hogwartsStaff = toUSbool(vetor[7]);
            p->hogwartsStudent = toUSbool(vetor[8]);
            strcpy(p->actorName, vetor[9]);
            p->alive = toUSbool(vetor[10]);
            strcpy(p->dateOfBirth, vetor[12]);
            p->yearOfBirth = atoi(vetor[13]);
            strcpy(p->eyeColour, vetor[14]);
            strcpy(p->gender, vetor[15]);
            strcpy(p->hairColour, vetor[16]);
            p->wizard = toUSbool(vetor[17]);
            break;
        }
    }
    fclose(file);
}

void inserirInicio(ListaPersonagem *lista, Personagem p)
{
    CelulaLista *tmp = (CelulaLista *)malloc(sizeof(CelulaLista));
    tmp->personagem = p;
    tmp->prox = lista->primeiro->prox;
    tmp->ant = lista->primeiro;

    if (lista->primeiro == lista->ultimo)
    {
        lista->ultimo = tmp;
    }
    else
    {
        lista->primeiro->prox->ant = tmp;
    }
    lista->primeiro->prox = tmp;
    lista->tam++;
}

void inserirFim(ListaPersonagem *lista, Personagem p)
{
    CelulaLista *tmp = (CelulaLista *)malloc(sizeof(CelulaLista));
    tmp->personagem = p;
    tmp->prox = NULL;
    tmp->ant = lista->ultimo;

    lista->ultimo->prox = tmp;
    lista->ultimo = tmp;
    lista->tam++;
}

Personagem removerInicio(ListaPersonagem *lista)
{
    if (lista->primeiro == lista->ultimo)
    {
        printf("Erro: lista vazia.\n");
        Personagem vazio;
        memset(&vazio, 0, sizeof(vazio));
        return vazio;
    }

    CelulaLista *tmp = lista->primeiro->prox;
    Personagem personagem = tmp->personagem;

    lista->primeiro->prox = tmp->prox;
    if (tmp->prox != NULL)
    {
        tmp->prox->ant = lista->primeiro;
    }
    else
    {
        lista->ultimo = lista->primeiro;
    }

    free(tmp);
    lista->tam--;

    return personagem;
}

Personagem removerFim(ListaPersonagem *lista)
{
    if (lista->primeiro == lista->ultimo)
    {
        printf("Erro: lista vazia.\n");
        Personagem vazio;
        memset(&vazio, 0, sizeof(vazio));
        return vazio;
    }

    CelulaLista *tmp = lista->ultimo;
    Personagem personagem = tmp->personagem;

    lista->ultimo = tmp->ant;
    lista->ultimo->prox = NULL;

    free(tmp);
    lista->tam--;

    return personagem;
}

Personagem remover(ListaPersonagem *lista, int pos)
{
    if (pos < 0 || pos >= lista->tam)
    {
        printf("Erro: posição inválida.\n");
        Personagem vazio;
        memset(&vazio, 0, sizeof(vazio));
        return vazio;
    }

    if (pos == 0)
    {
        return removerInicio(lista);
    }

    if (pos == lista->tam - 1)
    {
        return removerFim(lista);
    }

    CelulaLista *i = lista->primeiro;
    for (int j = 0; j < pos; j++, i = i->prox)
        ;

    CelulaLista *tmp = i->prox;
    Personagem personagem = tmp->personagem;

    i->prox = tmp->prox;
    tmp->prox->ant = i;

    free(tmp);
    lista->tam--;

    return personagem;
}

void swap(ListaPersonagem *lista, int i, int j)
{
    if (i == j)
        return; // faz nada, é igual

    CelulaLista *celI = lista->primeiro->prox;
    CelulaLista *celJ = lista->primeiro->prox;

    // Posso fazer um metodo set aqui
    for (int k = 0; k < i; k++)
    {
        celI = celI->prox;
    }

    for (int k = 0; k < j; k++)
    {
        celJ = celJ->prox;
    }

    Personagem temp = celI->personagem;
    celI->personagem = celJ->personagem;
    celJ->personagem = temp;
}

int compararHouseOuName(const Personagem *a, const Personagem *b)
{
    int houseComparison = strcmp(a->house, b->house);
    if (houseComparison != 0)
    {
        return houseComparison;
    }
    else
    {
        return strcmp(a->name, b->name);
    }
}

int particao(ListaPersonagem *lista, int esq, int dir)
{
    // Posso fazer um metodo get aqui
    CelulaLista *pivotCel = lista->primeiro->prox;
    for (int i = 0; i < dir; i++)
    {
        pivotCel = pivotCel->prox;
    }
    Personagem *pivot = &pivotCel->personagem;

    int i = esq - 1;

    for (int j = esq; j < dir; j++)
    {
        CelulaLista *current = lista->primeiro->prox;
        // Metodo get aqui
        for (int k = 0; k < j; k++)
        {
            current = current->prox;
        }

        if (compararHouseOuName(&current->personagem, pivot) <= 0)
        {
            i++;
            swap(lista, i, j);
        }
    }

    swap(lista, i + 1, dir);
    return i + 1;
}

void quickSort(ListaPersonagem *lista, int esq, int dir)
{
    if (esq < dir)
    {
        int indicePivot = particao(lista, esq, dir);
        quickSort(lista, esq, indicePivot - 1);
        quickSort(lista, indicePivot + 1, dir);
    }
}

void freeListaPersonagem(ListaPersonagem *lista)
{
    CelulaLista *current = lista->primeiro;
    while (current != NULL)
    {
        CelulaLista *next = current->prox;
        free(current);
        current = next;
    }
}

int main()
{
    ListaPersonagem lista;
    inicializarListaPersonagem(&lista);

    char ID[256];
    scanf(" %[^\r\n]s", ID);

    while (strcmp(ID, "FIM") != 0)
    {
        Personagem p;
        ler(&p, ID);
        inserirFim(&lista, p);
        // imprimir(&lista.ultimo->personagem);
        scanf(" %[^\r\n]s", ID);
    }

    quickSort(&lista, 0, lista.tam - 1);

    CelulaLista *current = lista.primeiro->prox;
    // imprimir(&current->prox->prox->personagem);
    while (current != NULL)
    {
        imprimir(&current->personagem);
        current = current->prox;
    }

    freeListaPersonagem(&lista);

    return 0;
}
// cls && gcc Personagem.c && .\a.exe < pub.in > saida.txt