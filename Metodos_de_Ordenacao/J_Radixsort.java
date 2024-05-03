import java.util.Scanner;

class I_Heapsort {
    static void heapsort(int[] array) {
        // int comp = 0, mov = 0;

        // Alterar o vetor ignorando a posicao zero
        int[] tmp = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            tmp[i + 1] = array[i];
        }
        array = tmp;

        // Constroi Heap
        for (int tamHeap = 2; tamHeap <= array.length; tamHeap++) {
            construir(tamHeap, array);
        }

        // Ordenando
        int tamHeap = array.length;
        while (tamHeap > 1) {
            swap(1, tamHeap--, array);
            reconstruir(tamHeap, array);
        }

        // Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = tmp[i + 1];
        }

        // System.out.println("Foram feitas " + comp + " comparacoes e " + mov + "
        // movimentacoes.");
    }

    static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        long startTime = System.currentTimeMillis();
        heapsort(array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}