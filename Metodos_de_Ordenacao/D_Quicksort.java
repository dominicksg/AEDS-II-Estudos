import java.util.Scanner;

class D_Quicksort {
    static void quicksort(int[] array) {
        int comp = 0, mov = 0;

    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        // Funcoes.preencheOrdenadamente(array);
        ZFuncoes.preencheAleatoriamente(array);
        ZFuncoes.imprimeArray(array);

        quicksort(array);
        ZFuncoes.imprimeArray(array);

        Sc.close();
    }

}