import java.util.Scanner;

class B_Bubblesort {
    static void Buublesort(int[] array) {
        int comp = 0, mov = 0;
        for (int rep = 0; rep < array.length - 1; rep++) {
            for (int i = 0; i < array.length; i++) {
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

    static void BuublesortOtimizado(int[] array) {
        int comp = 0, mov = 0;
        boolean houveTroca = true;
        for (int rep = 0; rep < array.length - 1 && houveTroca; rep++) {
            houveTroca = false;
            for (int i = 0; i < array.length - (rep + 1); i++) {
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
        // Funcoes.preencheOrdenadamente(array);
        ZFuncoes.preencheAleatoriamente(array);
        ZFuncoes.imprimeArray(array);

        int[] array2 = new int[50];
        ZFuncoes.preencheAleatoriamente(array2);
        ZFuncoes.imprimeArray(array);

        Buublesort(array);
        Buublesort(array2);
        ZFuncoes.imprimeArray(array);
        ZFuncoes.imprimeArray(array2);

        Sc.close();
    }

}