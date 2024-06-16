import java.util.Scanner;

class C_Bubblesort {
    static void Buublesort(int[] array) {
        int comp = 0, mov = 0;
        // CODIGO INEFICIENTE, PRESENTE APENAS PARA ESTUDO
        // for (int rep = (array.length - 1); rep > 0; rep--) {
        // for (int i = 0; i < rep; i++) {
        for (int rep = 0; rep < array.length - 1; rep++) {
            for (int i = 0; i < array.length - 1 - rep; i++) {
                comp++;
                if (array[i] > array[i + 1]) {
                    int tempmaior = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tempmaior;
                    mov += 3;
                }
            }
        }
        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    static void BuublesortOtimizado(int[] array) { // ?
        int comp = 0, mov = 0;
        boolean houveTroca = true;
        for (int rep = 0; rep < array.length - 1 && houveTroca; rep++) {
            houveTroca = false;
            for (int i = 0; i < array.length - 1 - rep; i++) {
                // for (int i = 0; i < array.length - (rep + 1); i++) {
                comp++;
                if (array[i] > array[i + 1]) {
                    int tempmaior = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tempmaior;
                    houveTroca = true;
                    mov += 3;
                }
            }
        }
        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    public static void swap(int i, int[] array) {
        int tempmaior = array[i];
        array[i] = array[i + 1];
        array[i + 1] = tempmaior;
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
        Buublesort(array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);
        System.out.println();

        long startTime2 = System.currentTimeMillis();
        BuublesortOtimizado(array2);
        long endTime2 = System.currentTimeMillis();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("Tempo de execucao: " + executionTime2 + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);
        
        Sc.close();
    }
}
// Foram feitas 1225 comparacoes e 1800 movimentacoes.
// Tempo de execucao: 4 ms
// Foram feitas 1180 comparacoes e 1800 movimentacoes.
// Tempo de execucao: 1 ms