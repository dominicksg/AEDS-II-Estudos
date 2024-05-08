import java.util.Scanner;

class J_Radixsort {
    static void radixsort(int[] array) {
        // int comp = 0, mov = 0;

        int n = array.length;
        int max = getMax(array);

        for (int exp = 1; max / exp > 0; exp *= 10) {

            int[] count = new int[10];
            int[] output = new int[n];

            // Inicializar cada posicao do array de contagem
            for (int i = 0; i < 10; count[i] = 0, i++)
                ;

            // Agora, o count[i] contem o numero de elemento iguais a i
            for (int i = 0; i < n; i++) {
                count[(array[i] / exp) % 10]++;
            }

            // Inicializar cada posicao do array de contagem
            // for (int i = 0; i < n; i++) // Corrigindo a inicialização do array count
            // count[(array[i] / exp) % 10]++;

            // Agora, o count[i] contem o numero de elemento menores ou iguais a i
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            // Ordenando
            for (int i = n - 1; i >= 0; i--) {
                output[count[(array[i] / exp) % 10] - 1] = array[i];
                count[(array[i] / exp) % 10]--;
            }

            // Copiando para o array original
            for (int i = 0; i < n; i++) {
                array[i] = output[i];
            }

            // System.out.println("Foram feitas " + comp + " comparacoes e " + mov + "
            // movimentacoes.");
        }
    }

    static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        long startTime = System.currentTimeMillis();
        radixsort(array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}