import java.util.Scanner;

class B_Bubblesort {
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
        ZFuncoes.preencheAleatoriamente(array, 4);
        ZFuncoes.imprimeArray(array);

        int[] array2 = new int[50];
        ZFuncoes.preencheAleatoriamente(array2, 4);
        ZFuncoes.imprimeArray(array);

        Buublesort(array);
        ZFuncoes.imprimeArray(array);

        Buublesort(array2);
        ZFuncoes.imprimeArray(array2);

        Sc.close();
    }

}