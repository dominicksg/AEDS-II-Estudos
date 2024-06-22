#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <stdbool.h>

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

//-------------------------------------------------------//

typedef struct Celula
{
    Personagem personagem;
    struct Celula *prox;

} Celula;

Celula *construtorCelulaPrimeira()
{
    Celula *tmp = (Celula *)malloc(sizeof(Celula));
    tmp->prox = NULL;

    return tmp;
}

Celula *construtorCelula(Personagem personagem)
{
    Celula *tmp = (Celula *)malloc(sizeof(Celula));
    tmp->personagem = personagem;
    tmp->prox = NULL;

    return tmp;
}

//-------------------------------------------------------//

typedef struct HashList
{
    Celula *primeira;
    Celula *ultimo;

} HashList;

HashList *construtorHashList()
{
    HashList *hashList = (HashList *)malloc(sizeof(HashList));

    hashList->primeira = construtorCelulaPrimeira();
    hashList->ultimo = hashList->primeira;

    return hashList;
}

void inserirHashList(HashList *hashList, Personagem personagem)
{

    hashList->ultimo->prox = construtorCelula(personagem);
    hashList->ultimo = hashList->ultimo->prox;
}

void imprimir(const Personagem *p);

void mostrarHashList(HashList *hashList)
{
    for (Celula *i = hashList->primeira->prox; i != NULL; i = i->prox)
    {
        imprimir(&i->personagem);
        printf("\n");
    }
}

//-------------------------------------------------------//

typedef struct HashTable
{
    HashList *hashList[21];
} HashTable;

HashTable *construtorHashTable()
{
    HashTable *table = (HashTable *)malloc(sizeof(HashTable));

    for (int i = 0; i < 21; i++)
    {
        table->hashList[i] = construtorHashList();
    }

    return table;
}

int hash(char nome[])
{
    int soma = 0;

    for (int i = 0; i < strlen(nome); i++)
    {
        soma += nome[i];
    }

    return soma % 21;
}

void inserir(HashTable *table, Personagem personagem)
{
    int pos = hash(personagem.name);
    inserirHashList(table->hashList[pos], personagem);
}

void mostrar(HashTable *table)
{
    for (int i = 0; i < 21; i++)
    {
        if (table->hashList[i]->primeira->prox != NULL)
        {
            mostrarHashList(table->hashList[i]);
        }
    }
}

int comparar(char nome[], Personagem a)
{
    return strcmp(nome, a.name);
}

bool pesquisarHashList(HashList *hashList, char nome[])
{
    bool flag = false;
    for (Celula *i = hashList->primeira; i != NULL; i = i->prox)
    {
        if (comparar(nome, i->personagem) == 0)
        {
            flag = true;
            i = hashList->ultimo;
        }
    }
    return flag;
}

void pesquisar(HashTable *table, char nome[])
{
    if (pesquisarHashList(table->hashList[hash(nome)], nome))
    {
        printf(" (pos: %d) SIM\n", hash(nome));
    }
    else
    {
        printf(" NAO\n");
    }
}

//------------------------------------------------------//
//------------------------------------------------------//

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

int main()
{
    HashTable *table = construtorHashTable();

    char ID[256];
    scanf(" %[^\r\n]s", ID);

    while (strcmp(ID, "FIM") != 0)
    {
        Personagem *p = (Personagem *)malloc(sizeof(Personagem));
        if (p == NULL)
        {
            printf("Erro: memória insuficiente.\n");
            break;
        }
        ler(p, ID);
        inserir(table, *p);
        scanf(" %[^\r\n]s", ID);
    }

    scanf(" %[^\r\n]s", ID);
    ID[strcspn(ID, "\r")] = '\0';

    while (strcmp(ID, "FIM") != 0)
    {
        printf("%s", ID);

        pesquisar(table, ID);
        scanf(" %[^\r\n]s", ID);
        ID[strcspn(ID, "\r")] = '\0';
    }

    return 0;
}

// cls && gcc Personagem.c && .\a.exe < pub.in > saida.txt