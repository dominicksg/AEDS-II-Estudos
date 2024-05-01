import java.util.Random;

public class Funcoes {
    public static void preencheAleatoriamente(int[] vetor) {
        Random rand = new Random();
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = rand.nextInt(100); // Gera números aleatórios entre 0 e 99
        }
    }

    public static void imprimeVetor(int[] vetor) {
        System.out.print("[ ");

        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.print("]");
        System.out.println();
    }
}
