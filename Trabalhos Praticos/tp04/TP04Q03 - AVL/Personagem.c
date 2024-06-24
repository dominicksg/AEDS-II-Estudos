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

//------------------------------------------------------//
//-------------------------------------------------------//

typedef struct NoAVL
{
    Personagem elemento;
    struct NoAVL *esq, *dir;
    int altura;
} NoAVL;

NoAVL *inicializaNoAVL(Personagem x, NoAVL *esq, NoAVL *dir, int altura);
NoAVL *criarNoAVL(Personagem x);
int getAltura(NoAVL *no);
void setAltura(NoAVL *no);
NoAVL *inserirAVL(NoAVL *i, Personagem x);
NoAVL *removerAVL(NoAVL *i, Personagem x);
NoAVL *balancear(NoAVL *no);
NoAVL *rotacionarEsq(NoAVL *no);
NoAVL *rotacionarDir(NoAVL *no);
NoAVL *maiorEsq(NoAVL *i, NoAVL *j);
int compararPersonagem(Personagem a, Personagem b);
int pesquisar(NoAVL *i, char *x);
void caminharCentral(NoAVL *i);
void imprimirPersonagem(Personagem p);

NoAVL *inicializaNoAVL(Personagem x, NoAVL *esq, NoAVL *dir, int altura)
{
    NoAVL *novoNo = (NoAVL *)malloc(sizeof(NoAVL));
    novoNo->elemento = x;
    novoNo->esq = esq;
    novoNo->dir = dir;
    novoNo->altura = altura;
    return novoNo;
}

NoAVL *criarNoAVL(Personagem x)
{
    return inicializaNoAVL(x, NULL, NULL, 1);
}

int getAltura(NoAVL *no)
{
    return (no == NULL) ? 0 : no->altura;
}

void setAltura(NoAVL *no)
{
    no->altura = 1 + (getAltura(no->esq) > getAltura(no->dir) ? getAltura(no->esq) : getAltura(no->dir));
}

NoAVL *balancear(NoAVL *no)
{
    if (no != NULL)
    {
        int fator = getAltura(no->dir) - getAltura(no->esq);

        if (abs(fator) <= 1)
        {
            setAltura(no);
        }
        else if (fator == 2)
        {
            int fatorFilhoDir = getAltura(no->dir->dir) - getAltura(no->dir->esq);

            if (fatorFilhoDir == -1)
            {
                no->dir = rotacionarDir(no->dir);
            }

            no = rotacionarEsq(no);
        }
        else if (fator == -2)
        {
            int fatorFilhoEsq = getAltura(no->esq->dir) - getAltura(no->esq->esq);

            if (fatorFilhoEsq == 1)
            {
                no->esq = rotacionarEsq(no->esq);
            }

            no = rotacionarDir(no);
        }
    }
    return no;
}

NoAVL *rotacionarEsq(NoAVL *no)
{
    // printf("Rotacionar ESQ(%s)\n", no->elemento.name);
    NoAVL *noDir = no->dir;
    NoAVL *noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    setAltura(no);
    setAltura(noDir);

    return noDir;
}

NoAVL *rotacionarDir(NoAVL *no)
{
    // printf("Rotacionar DIR(%s)\n", no->elemento.name);
    NoAVL *noEsq = no->esq;
    NoAVL *noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;

    setAltura(no);
    setAltura(noEsq);

    return noEsq;
}

int compararPersonagem(Personagem a, Personagem b)
{
    return strcmp(a.name, b.name);
}

NoAVL *inserirAVL(NoAVL *i, Personagem x)
{
    if (i == NULL)
    {
        i = criarNoAVL(x);
    }
    else if (compararPersonagem(x, i->elemento) < 0)
    {
        i->esq = inserirAVL(i->esq, x);
    }
    else if (compararPersonagem(x, i->elemento) > 0)
    {
        i->dir = inserirAVL(i->dir, x);
    }
    else
    {
        printf("Erro: Personagem já existe\n");
    }
    return balancear(i);
}

NoAVL *removerAVL(NoAVL *i, Personagem x)
{
    if (i == NULL)
    {
        printf("Erro: Personagem não encontrado\n");
        return NULL;
    }
    else if (compararPersonagem(x, i->elemento) < 0)
    {
        i->esq = removerAVL(i->esq, x);
    }
    else if (compararPersonagem(x, i->elemento) > 0)
    {
        i->dir = removerAVL(i->dir, x);
    }
    else if (i->dir == NULL)
    {
        i = i->esq;
    }
    else if (i->esq == NULL)
    {
        i = i->dir;
    }
    else
    {
        i->esq = maiorEsq(i, i->esq);
    }
    return balancear(i);
}

NoAVL *maiorEsq(NoAVL *i, NoAVL *j)
{
    if (j->dir == NULL)
    {
        i->elemento = j->elemento;
        j = j->esq;
    }
    else
    {
        j->dir = maiorEsq(i, j->dir);
    }
    return j;
}

int pesquisar(NoAVL *i, char *x)
{
    int flag = 0;
    if (i == NULL)
    {
        printf("NAO\n");
        return flag;
    }
    else if (strcmp(x, i->elemento.name) == 0)
    {
        printf("SIM\n");
        flag = 1;
    }
    else if (strcmp(x, i->elemento.name) < 0)
    {
        printf("esq ");
        flag = pesquisar(i->esq, x);
    }
    else
    {
        printf("dir ");
        flag = pesquisar(i->dir, x);
    }
    return flag;
}

void imprimirPersonagem(Personagem p)
{
    printf("Nome: %s, Casa: %s, Ano de Nascimento: %d\n", p.name, p.house, p.yearOfBirth);
}

void caminharCentral(NoAVL *i)
{
    if (i != NULL)
    {
        caminharCentral(i->esq);
        imprimirPersonagem(i->elemento);
        caminharCentral(i->dir);
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
    NoAVL *noAVL = NULL;

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
        noAVL = inserirAVL(noAVL, *p);
        scanf(" %[^\r\n]s", ID);
    }

    scanf(" %[^\r\n]s", ID);
    ID[strcspn(ID, "\r")] = '\0';

    while (strcmp(ID, "FIM") != 0)
    {
        printf("%s => raiz ", ID);
        pesquisar(noAVL, ID);
        scanf(" %[^\r\n]s", ID);
        ID[strcspn(ID, "\r")] = '\0';
    }

    return 0;
}

// cls && gcc Personagem.c && .\a.exe < pub.in > saida.txt