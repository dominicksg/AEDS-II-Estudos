import java.util.Scanner;

class I_Heapsort2 {
    public static void heapsort(int[] intArray) {

        int n = intArray.length;

        // Constrói o heap máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            buildMaxHeap(i, n, intArray);
        }

        // Extrai elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            swap(intArray, 0, i);
            buildMaxHeap(0, i, intArray);
        }
    }

    public static void buildMaxHeap(int root, int n, int[] intArray) {

        int largest = root, l = 2 * root + 1, r = 2 * root + 2;

        // Verifica se o filho esquerdo é maior que a raiz
        if (l < n && intArray[l] > intArray[largest])
            largest = l;

        // Verifica se o filho direito é maior que o maior até agora
        if (r < n && intArray[r] > intArray[largest])
            largest = r;

        // Se o maior não é a raiz
        if (largest != root) {
            swap(intArray, root, largest);
            buildMaxHeap(largest, n, intArray);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void heapsortParcial(int[] intArray, int k) {

        int n = intArray.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            buildMaxHeap(i, n, intArray);
        }

        // Sort only the top K elements
        for (int i = n - 1; i > n - k - 1; i--) {
            swap(intArray, 0, i);
            buildMaxHeap(0, i, intArray);
        }
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        int[] array2 = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array2, 42);
        ArrayIO.imprimeArray(array2);
        System.out.println();

        long startTime = System.currentTimeMillis();
        heapsort(array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        System.out.println();

        long startTime2 = System.currentTimeMillis();
        heapsortParcial(array2, 10);
        long endTime2 = System.currentTimeMillis();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("Tempo de execucao: " + executionTime2 + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}