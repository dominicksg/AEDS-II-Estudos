#include <stdio.h>
#include <string.h>
#include <time.h>

char *randomChar(char frase[], int seed, int i)
{
    static char frasefinal[1000];
    strcpy(frasefinal, frase);

    srand(seed);
    char letraold = 'a' + rand() % 26;
    char letranew = 'a' + rand() % 26;

    if(i == '\0'){
        return frasefinal;
    }

    //for (int i = 0; i < strlen(frase); i++)
            if (frase[i] == letraold)
        {
            frasefinal[i] = letranew;
        }
    
    return randomChar(frase, seed, 0);
}

int isFim(char s[])
{
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int main()
{
    char str[1000];
    int seed = 4;

    fgets(str, sizeof(str), stdin);
    str[strcspn(str, "\n")] = '\0'; // Remove a nova linha

    while (!isFim(str))
    {

        char *fraseAlterada = randomChar(str, seed, 0);
        printf("%s\n", fraseAlterada);

        fgets(str, sizeof(str), stdin);
        str[strcspn(str, "\n")] = '\0'; // Remove a nova linha
    }

    return 0;
}
