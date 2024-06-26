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

typedef struct CelulaPilha
{
    Personagem *personagem;
    struct CelulaPilha *prox;
} CelulaPilha;

typedef struct PilhaPersonagem
{
    CelulaPilha *topo;
} PilhaPersonagem;

void inicializarPilhaPersonagem(PilhaPersonagem *pilha)
{
    pilha->topo = NULL;
}

void push(PilhaPersonagem *pilha, Personagem *personagem)
{
    CelulaPilha *novaCelula = (CelulaPilha *)malloc(sizeof(CelulaPilha));
    if (novaCelula == NULL)
    {
        printf("Erro: memória insuficiente.\n");
        return;
    }

    novaCelula->personagem = personagem;
    novaCelula->prox = pilha->topo;
    pilha->topo = novaCelula;
}

Personagem *pop(PilhaPersonagem *pilha)
{
    if (pilha->topo == NULL)
    {
        printf("Erro: pilha vazia.\n");
        return NULL;
    }

    CelulaPilha *tmp = pilha->topo;
    Personagem *personagem = tmp->personagem;
    pilha->topo = tmp->prox;
    free(tmp);
    return personagem;
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

void hubFuncao(char *linha, PilhaPersonagem *pilha)
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
        Personagem *personagem = (Personagem *)malloc(sizeof(Personagem));
        if (personagem == NULL)
        {
            printf("Erro: memória insuficiente.\n");
            return;
        }
        ler(personagem, palavra[1]);
        push(pilha, personagem);
    }
    else if (strcmp(palavra[0], "R") == 0)
    {
        Personagem *personagem = pop(pilha);
        if (personagem)
        {
            printf("(R) %s\n", personagem->name);
            free(personagem);
        }
    }
}

void imprimirPilha(PilhaPersonagem *pilha)
{
    if (pilha->topo == NULL)
    {
        printf("Erro: pilha vazia.\n");
        return;
    }

    CelulaPilha *celula_atual = pilha->topo;
    int cont = 0;
    while (celula_atual != NULL)
    {
        printf("[%d ## ", cont++);
        imprimir(celula_atual->personagem);
        CelulaPilha *proxima_celula = celula_atual->prox;
        free(celula_atual->personagem);
        free(celula_atual);
        celula_atual = proxima_celula;
    }
}

int main()
{
    PilhaPersonagem pilha;
    inicializarPilhaPersonagem(&pilha);

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
        push(&pilha, p);
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
        hubFuncao(linha, &pilha);
    }

    printf("[ Top ]\n");
    imprimirPilha(&pilha);
    printf("[ Bottom ]\n");

    return 0;
}

// cls && gcc Personagem.c && .\a.exe < pub.in > saida.txt