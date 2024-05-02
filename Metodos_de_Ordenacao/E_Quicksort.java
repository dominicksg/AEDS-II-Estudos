import java.util.Scanner;

class E_Quicksort {
    static void quicksort(int esq, int dir, int[] array, int[] counts) {
        int i = esq, j = dir, pivo = array[(esq + dir) / 2];
        while (i <= j) {
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
            if (i < dir)
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

        quicksort(0, array.length - 1, array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}
// Foram feitas 8293 comparacoes e 7611 movimentacoes.