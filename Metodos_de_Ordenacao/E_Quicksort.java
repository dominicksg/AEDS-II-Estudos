import java.util.Scanner;

class E_Quicksort {
    static void quicksort(int esq, int dir, int[] array, int[] counts) {
        int i = esq, j = dir, pivo = array[(esq + dir) / 2];

        while (i <= j) { // desnecessário?
            while (array[i] < pivo) {
                i++;
                counts[0]++;
            }
            while (array[j] > pivo) {
                j--;
                counts[0]++;
            }
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
                counts[1] += 3;
            }
            if (esq < j)
                quicksort(esq, j, array, counts);
            if (i < dir) // if (i < k && i < dir) // parcial
                quicksort(i, dir, array, counts);
        }
    }

    static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static void quicksort(int esq, int dir, int[] array) {
        int[] counts = { 0, 0 }; // counts[0] para comparações, counts[1] para movimentações
        quicksort(esq, dir, array, counts);
        System.out.println("Foram feitas " + counts[0] + " comparacoes e " + counts[1] + " movimentacoes.");
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        long startTime = System.currentTimeMillis();
        quicksort(0, array.length - 1, array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}
// Foram feitas 8293 comparacoes e 7611 movimentacoes.
// Tempo de execucao: 5 ms
// Sem o while desnecessário:
// Foram feitas 2896 comparacoes e 2655 movimentacoes.
// Tempo de execucao: 4 ms