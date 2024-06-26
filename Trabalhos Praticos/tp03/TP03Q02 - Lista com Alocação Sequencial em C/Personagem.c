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

typedef struct ListaPersonagem
{
    int tam, maxTam;
    Personagem **array;
} ListaPersonagem;

void inicializarListaPersonagem(ListaPersonagem *lista, int maxTam)
{
    lista->tam = 0;
    lista->maxTam = maxTam;
    lista->array = (Personagem **)malloc(maxTam * sizeof(Personagem *));
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

void inserirInicio(ListaPersonagem *lista, Personagem *p)
{
    if (lista->tam == lista->maxTam)
    {
        printf("Erro: lista cheia.\n");
        return;
    }
    for (int i = lista->tam; i > 0; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }
    lista->array[0] = p;
    lista->tam++;
}

void inserirFim(ListaPersonagem *lista, Personagem *p)
{
    if (lista->tam == lista->maxTam)
    {
        printf("Erro: lista cheia.\n");
        return;
    }
    lista->array[lista->tam++] = p;
}

void inserir(ListaPersonagem *lista, int pos, Personagem *p)
{
    if (lista->tam == lista->maxTam)
    {
        printf("Erro: lista cheia.\n");
        return;
    }
    for (int i = lista->tam; i > pos; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }
    lista->array[pos] = p;
    lista->tam++;
}

Personagem *removerInicio(ListaPersonagem *lista)
{
    if (lista->tam == 0)
    {
        printf("Erro: lista vazia.\n");
        return NULL;
    }
    Personagem *tmp = lista->array[0];
    for (int i = 0; i < lista->tam - 1; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }
    lista->tam--;
    return tmp;
}

Personagem *removerFim(ListaPersonagem *lista)
{
    if (lista->tam == 0)
    {
        printf("Erro: lista vazia.\n");
        return NULL;
    }
    return lista->array[--lista->tam];
}

Personagem *remover(ListaPersonagem *lista, int pos)
{
    if (lista->tam == 0)
    {
        printf("Erro: lista vazia.\n");
        return NULL;
    }
    Personagem *tmp = lista->array[pos];
    for (int i = pos; i < lista->tam - 1; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }
    lista->tam--;
    return tmp;
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

    if (strcmp(palavra[0], "II") == 0)
    {
        Personagem *p = (Personagem *)malloc(sizeof(Personagem));
        ler(p, palavra[1]);
        inserirInicio(lista, p);
    }
    else if (strcmp(palavra[0], "IF") == 0)
    {
        Personagem *p = (Personagem *)malloc(sizeof(Personagem));
        ler(p, palavra[1]);
        inserirFim(lista, p);
    }
    else if (strcmp(palavra[0], "I*") == 0)
    {
        Personagem *p = (Personagem *)malloc(sizeof(Personagem));
        ler(p, palavra[2]);
        inserir(lista, atoi(palavra[1]), p);
    }
    else if (strcmp(palavra[0], "RI") == 0)
    {
        Personagem *p = removerInicio(lista);
        if (p)
            printf("(R) %s\n", p->name);
        free(p);
    }
    else if (strcmp(palavra[0], "RF") == 0)
    {
        Personagem *p = removerFim(lista);
        if (p)
            printf("(R) %s\n", p->name);
        free(p);
    }
    else if (strcmp(palavra[0], "R*") == 0)
    {
        Personagem *p = remover(lista, atoi(palavra[1]));
        if (p)
            printf("(R) %s\n", p->name);
        free(p);
    }
}

int main()
{
    ListaPersonagem lista;
    inicializarListaPersonagem(&lista, 300);

    char ID[256];
    // scanf(" %[^\n]", ID);
    scanf(" %[^\r\n]s", ID);

    while (strcmp(ID, "FIM") != 0)
    {
        Personagem *p = (Personagem *)malloc(sizeof(Personagem));
        ler(p, ID);
        inserirFim(&lista, p);
        scanf(" %[^\r\n]s", ID);
    }

    int numEntradas;
    scanf("%d", &numEntradas);
    getchar();
    // while (getchar() != '\n'); // Limpar o buffer do teclado

    // scanf(" %[^\n]d", numEntradas);

    for (int j = 0; j < numEntradas; j++)
    {
        char linha[256];
        // fgets(linha, sizeof(linha), stdin); // Simplesmente me tomou 2h por causa de um /r
        scanf(" %[^\r\n]s", linha);      // scanf mvp
        linha[strcspn(linha, "\n")] = 0; // Remove newline character
        hubFuncao(linha, &lista);
    }

    for (int i = 0; i < lista.tam; i++)
    {
        printf("[%d ## ", i);
        imprimir(lista.array[i]);
        free(lista.array[i]);
    }

    free(lista.array);
    return 0;
}

// cls && gcc Personagem.c && .\a.exe < pub.in > saida.txt