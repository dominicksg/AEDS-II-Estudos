//----------------------------------------------------------------------------------------//
// Avisos
//----------------------------------------------------------------------------------------//
// Olá, esse código provavelmente não está organizado como eu gostaria,
// por conta da urgência das entregas.
// E é normal ter muitas linhas de código comentadas,
// já que é para uma atividade, eu aproveito isso para documentar todo o estudo também.
//----------------------------------------------------------------------------------------//
// Anotações
//----------------------------------------------------------------------------------------//
// Esse código está com um erro intencional nos "printf".
// Isso foi necessário para formatar a saída igual
// ao do Verde (plataforma de correção de código) que também estava errada.
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
    // for (int i = 0; i < 256; i++)    {
    //     lista->codenomes[i][0] = '\0'; // Inicializa todos os nomes como strings vazias
    // }
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

int toUSbool(const char *frase)
{

    return strcmp(frase, "VERDADEIRO") == 0;
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
            // Verifica se não é o último elemento e se o próximo não está vazio
            if (i < 8 && strcmp(lista->codenomes[i + 1], "VAZIO") != 0)
            {
                printf(","); // Adiciona uma vírgula apenas se houver próximo elemento
            }
        }
    }
    printf("} ## ");
}

void imprimir(const Personagem *p)
{
    // printf("--------------------------\n");
    // printf("aa");
    // printf("%s", p->patronus);
    // printf("aa");
    // printf("--------------------------\n");

    printf("[%s ## %s ## ", p->id, p->name);
    imprimirLista(&p->alternate_name);
    printf("%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n",
           p->house, p->ancestry, p->species, p->patronus,
           p->hogwartsStaff ? "true" : "false",   // Diretamente usando o valor booleano
           p->hogwartsStudent ? "true" : "false", // Diretamente usando o valor booleano
           p->actorName,
           p->alive ? "true" : "false", // Diretamente usando o valor booleano
           p->dateOfBirth,
           p->yearOfBirth,
           p->eyeColour, p->gender, p->hairColour,
           p->wizard ? "true" : "false"); // Diretamente usando o valor booleano
}


// Função para remover caracteres especiais de uma string
void limparCaracteresEspeciais(char *string)
{
    int i, j;
    for (i = 0, j = 0; string[i] != '\0'; i++)
    {
        // Verifica se o caractere atual é especial
        if (string[i] != '\'' && string[i] != '[' && string[i] != ']')
        {
            // Se não for especial, copia para a nova posição
            string[j++] = string[i];
        }
    }
    // Adiciona o terminador nulo à nova posição final
    string[j] = '\0';
}

// Função para ler e processar os nomes alternativos
void processarNomesAlternativos(char *nomesAlternativos, Lista *lista)
{
    inicializarLista(lista);
    char *nome = strtok(nomesAlternativos, ",");
    int i = 0;
    while (nome != NULL && i < 10)
    {
        // Remove caracteres especiais do nome alternativo
        limparCaracteresEspeciais(nome);
        // Copia o nome limpo para a lista de codenomes
        strcpy(lista->codenomes[i++], nome);
        // Proximo token
        nome = strtok(NULL, ",");
    }
}

void ler(Personagem *p, char *ID)
{

   // FILE *file = fopen("/tmp/characters.csv", "r");
    FILE *file = fopen("characters.csv", "r");
    if (!file)
    {
        perror("Erro ao abrir o arquivo");
        return;
    }

    char linha[1024];
    // fgets(linha, sizeof(linha), file); // Descarta a primeira linha

    while (fgets(linha, sizeof(linha), file))
    {
        char vetor[18][256];
        int index = 0;
        char *token = linha;
        char *next_token;

        // while (token)
        // {
        //     strcpy(vetor[index++], token);
        //     token = strtok(NULL, ";");
        // }

        // while (token)
        // {
        //         next_token = strchr(token, ';');
        //         if (next_token)
        //         {
        //             *next_token = '\0'; // Termina o token atual
        //             if (*token == '\0')
        //             {                              // Verifica se o token está vazio
        //                 strcpy(vetor[index], " "); // Atribui um espaço ao vetor
        //             }
        //             else
        //             {
        //                 strcpy(vetor[index], token);
        //             }
        //             index++;
        //             token = next_token + 1;
        //         }
        //         else
        //         {
        //             if (*token == '\0')
        //             { // Último token pode ser vazio
        //                 strcpy(vetor[index], " ");
        //             }
        //             else
        //             {
        //                 strcpy(vetor[index], token);
        //             }
        //             break; // Não há mais delimitadores, finaliza o loop
        //         }
        //     }

        while (token)
        {
            next_token = strchr(token, ';');
            if (next_token)
            {
                *next_token = '\0'; // Termina o token atual
                strcpy(vetor[index++], token);
                token = next_token + 1;
            }
            else
            {
                strcpy(vetor[index++], token);
                break; // Não há mais delimitadores, finaliza o loop
            }
        }

        // printf("\n%s\n", linhaID);
        // printf("\n%s\n", vetor[0]);
        if (strcmp(ID, vetor[0]) == 0)
        {
            strcpy(p->id, vetor[0]);
            strcpy(p->name, vetor[1]);

            // Trata a Lista
            processarNomesAlternativos(vetor[2], &p->alternate_name);

            // Trata a Lista
            // char *alt_names = strtok(vetor[2], ",");
            // int j = 0;
            // while (alt_names && j < 10)
            // {
            //     // Remove os caracteres "[" "]" e "'"
            //     char *clean_name = alt_names;
            //     int len = strlen(clean_name);
            //     if (clean_name[0] == '[' || clean_name[0] == '\'')
            //         clean_name++;
            //     if (clean_name[len - 1] == ']' || clean_name[len - 1] == '\'')
            //         clean_name[len - 1] = '\0';

            //     // Remove as aspas, se existirem
            //     char *ptr = clean_name;
            //     while (*ptr)
            //     {
            //         if (*ptr == '\'')
            //         {
            //             memmove(ptr, ptr + 1, strlen(ptr)); // Remove o caractere '
            //         }
            //         else
            //         {
            //             ptr++;
            //         }
            //     }

            //     // Copia para o vetor sem os caracteres "[" "]" e "'"
            //     strcpy(p.alternate_name.codenomes[j++], clean_name);
            //     alt_names = strtok(NULL, ",");
            // }

            // Copia as outras informações
            strcpy(p->house, vetor[3]);
            strcpy(p->ancestry, vetor[4]);
            strcpy(p->species, vetor[5]);
            strcpy(p->patronus, vetor[6]);
            p->hogwartsStaff = toUSbool(vetor[7]);
            p->hogwartsStudent = toUSbool(vetor[8]);
            strcpy(p->actorName, vetor[9]);
            p->alive = toUSbool(vetor[10]);
            strcpy(p->dateOfBirth, vetor[12]); // Supondo que a data esteja formatada corretamente
            p->yearOfBirth = atoi(vetor[13]);
            strcpy(p->eyeColour, vetor[14]);
            strcpy(p->gender, vetor[15]);
            strcpy(p->hairColour, vetor[16]);
            p->wizard = toUSbool(vetor[17]);

            // imprimir(&p);
            break;
        }
    }
    fclose(file);
}

void selectionSort(Personagem **vetor, int n)
{
    for (int i = 0; i < n - 1; i++)
    {
        int menor = i;
        for (int j = i + 1; j < n; j++)
        {
            if (strcmp(vetor[j]->name, vetor[menor]->name) < 0)
            {
                menor = j;
            }
        }
        Personagem temp = *vetor[i];
        *vetor[i] = *vetor[menor];
        *vetor[menor] = temp;
    }
}

int main()
{
    Personagem *personagens[30];

    char ID[256];
    int count = 0;

    // scanf(" %[^\n]", ID);
    scanf(" %[^\r\n]s", ID);

    while (strcmp(ID, "FIM") != 0)
    {
        // ID[strcspn(ID, "\n\r")] = 0;

        ID[strcspn(ID, "\n")] = 0; // Remove newline
        personagens[count] = (Personagem *)malloc(sizeof(Personagem));
        ler(personagens[count], ID);

        count++;
        scanf(" %[^\r\n]s", ID);
    }

    //selectionSort(personagens, count);

    for (int i = 0; i < count; i++)
    {
        imprimir(personagens[i]);
        free(personagens[i]);
    }
    return 0;
}