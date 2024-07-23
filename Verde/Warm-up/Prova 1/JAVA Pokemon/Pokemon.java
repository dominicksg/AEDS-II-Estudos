/*
Desde que foi lançado oficialmente o Pomekon no Brasil, Dabriel está tentando realizar seu maior sonho: Ser um Mestre Pomekon. Sua meta é conquistar os 151 Pomekons disponíveis. Ele já conseguiu capturar muitos monstrinhos, porém em sua cidade aparecem muitos Pomekons repetidos, fazendo com que ele capture diversas vezes o mesmo Pomekon.

Vendo que sua mochila está bem cheia, Dabriel pediu para que você fizesse um programa de computador que informasse a ele quantos Pomekons faltam para completar a coleção.


Entrada
A primeira linha do caso de teste consiste de um inteiro N (1 ≤ N ≤ 10³), representando a quantidade de Pomekons que Dabriel já capturou.
As próximas N linhas consistem de uma string S (1 ≤ |S| ≤ 10³), representando o nome de cada Pomekons. O nome de cada Pomekons consiste apenas de letras maiúsculas e minúsculas.
1-7
2-Charmander
3-Caterpie
4-Pidgeot
5-Rattata
6-Zubat
7-Zubat
8-Zubat
9


Saída
Você deverá imprimir: "Falta(m) X pomekon(s).", onde X representa a quantidade Pomekons não capturados.
1-Falta(m) 146 pomekon(s).
*/

import java.util.Scanner;

class Pokemon {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        int n = 0;
        int faltam = 151;
        int igual = 0;

        n = Sc.nextInt();

        String[] pokemon = new String[n];
        Sc.nextLine(); // Consumindo a quebra de linha excedente

        for (int i = 0; i < n; i++) {
            pokemon[i] = Sc.nextLine();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pokemon[i].equals(pokemon[j]) == true) {
                    igual++;
                    j = n;
                }
            }
        }
        faltam = faltam - (n - igual);

        System.out.println("Falta(m) " + faltam + " pomekon(s).");
        Sc.close();
    }
}