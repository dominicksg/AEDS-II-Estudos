
//package Metodos_de_Pesquisa.Funcoes;
import java.util.Scanner;

class A_Sequencial {
    static boolean pesquisaSequencial(int[] vetor, int key) {
        boolean flag = false;
        int comp = 0;
        for (int i = 0; i < vetor.length; i++) { // Consigo melhorar esse for, sem o If
            comp++;
            if (vetor[i] == key) {
                flag = true;
                i = vetor.length;
            }
        }
        System.out.println("Foram feitas " + comp + " comparacoes.");
        return flag;
    }

    static boolean pesquisaSequencial(String[] vetor, String key) {
        boolean flag = false;

        return flag;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] vetor = new int[50];
        Funcoes.preencheOrdenadamente(vetor);
        // Funcoes.preencheAleatoriamente(vetor);
        Funcoes.imprimeVetor(vetor);

        System.out.println("Pesquise um numero (digite -1 para sair): ");
        int num = Sc.nextInt();
        while (num != -1) {
            if (pesquisaSequencial(vetor, num) == true) {
                System.out.println("Numero " + num + " encontrado.");
            } else {
                System.out.println("Numero " + num + " não encontrado.");
            }
            System.out.println("Pesquise um numero (digite -1 para sair): ");
            num = Sc.nextInt();
        }
        Sc.close();
    }
}
