import java.util.Scanner;

class B_Selectionsort {
    static void selectionsort(int[] array) {
        int comp = 0, mov = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < array.length; j++) {
                comp++;
                if (array[j] < array[menor]) {
                    menor = j;
                }
            }
            swap(menor, i, array);
            mov += 3;
        }
        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    public static void swap(int menor, int i, int[] array) {
        int temp = array[menor];
        array[menor] = array[i];
        array[i] = temp;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        long startTime = System.currentTimeMillis();
        selectionsort(array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}
// Foram feitas 1225 comparacoes e 147 movimentacoes.
// Tempo de execucao: 3 ms