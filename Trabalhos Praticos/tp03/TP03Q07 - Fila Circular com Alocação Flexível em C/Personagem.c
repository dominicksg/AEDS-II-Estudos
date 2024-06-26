#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAX_FILA 5

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

typedef struct ListaPersonagem
{
    int tam, maxTam;
    int head, tail;
    Personagem **array;
} ListaPersonagem;

void inicializarListaPersonagem(ListaPersonagem *fila, int maxTam)
{
    fila->tam = 0;
    fila->maxTam = maxTam;
    fila->head = 0;
    fila->tail = -1;
    fila->array = (Personagem **)malloc(maxTam * sizeof(Personagem *));
}

void imprimirLista(const Lista *lista)
{
    printf("{");
    int i;
    for (i = 0; i < 10; i++)
    {
        if (strcmp(lista->codenomes[i], "VAZIO") != 0)
        {
            printf("%s", lista->codenomes[i]);
            if (i < 9 && strcmp(lista->codenomes[i + 1], "VAZIO") != 0)
            {
                printf(","); // Adiciona uma vírgula apenas se houver próximo elemento
            }
        }
    }
    printf("} ## ");
}

void imprimir(const Personagem *p)
{
    printf("%s ## %s ## ", p->id, p->name);
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
    inicializarLista(lista);
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

Personagem *desenfileirar(ListaPersonagem *fila)
{
    if (fila->tam == 0)
    {
        printf("Erro: fila vazia.\n");
        return NULL;
    }
    Personagem *tmp = fila->array[fila->head];
    fila->head = (fila->head + 1) % fila->maxTam;
    fila->tam--;
    return tmp;
}

void enfileirar(ListaPersonagem *fila, Personagem *p)
{
    if (fila->tam == fila->maxTam)
    {
        Personagem *removido = desenfileirar(fila);
        if (removido)
        {
            free(removido);
        }
    }
    fila->tail = (fila->tail + 1) % fila->maxTam;
    fila->array[fila->tail] = p;
    fila->tam++;
}

double calcularMediaAno(const ListaPersonagem *fila)
{
    if (fila->tam == 0)
        return 0.0;

    int soma = 0;
    for (int i = 0; i < fila->tam; i++)
    {
        int pos = (fila->head + i) % fila->maxTam;
        soma += fila->array[pos]->yearOfBirth;
    }
    return (double)soma / fila->tam;
}

void hubFuncao(char *linha, ListaPersonagem *lista)
{
    char *palavra[3];
    int i = 0;
    char *token = strtok(linha, " ");

    while (token != NULL && i < 3)
    {
        palavra[i++] = token;
        token = strtok(NULL, " ");
    }

    if (strcmp(palavra[0], "I") == 0)
    {
        Personagem *p = (Personagem *)malloc(sizeof(Personagem));
        ler(p, palavra[1]);
        enfileirar(lista, p);
        double media = calcularMediaAno(lista);
        printf(">> Year Birthday Average: %d\n", (int)(media)); // round
    }
    else if (strcmp(palavra[0], "R") == 0)
    {
        Personagem *p = desenfileirar(lista);
        if (p)
        {
            printf("(R) %s\n", p->name);
            free(p);
        }
    }
}

int main()
{
    ListaPersonagem lista;
    inicializarListaPersonagem(&lista, MAX_FILA);

    char ID[256];
    scanf(" %[^\r\n]s", ID);

    while (strcmp(ID, "FIM") != 0)
    {
        Personagem *p = (Personagem *)malloc(sizeof(Personagem));
        ler(p, ID);
        enfileirar(&lista, p);
        double media = calcularMediaAno(&lista);
        printf(">> Year Birthday Average: %d\n", (int)(media)); // round
        scanf(" %[^\r\n]s", ID);
    }

    int numEntradas;
    scanf("%d", &numEntradas);
    getchar();

    for (int j = 0; j < numEntradas; j++)
    {
        char linha[256];
        scanf(" %[^\r\n]s", linha);
        linha[strcspn(linha, "\n")] = 0; // Remove newline character
        hubFuncao(linha, &lista);
    }

    printf("[ Head ]\n");
    for (int i = 0; i < lista.tam; i++)
    {
        printf("[%d ## ", i);
        imprimir(lista.array[(lista.head + i) % lista.maxTam]);
        free(lista.array[(lista.head + i) % lista.maxTam]);
    }
    printf("[ Tail ]\n");

    free(lista.array);
    return 0;
}

// cls && gcc Personagem.c && .\a.exe < pub.in > saida.txt