import java.util.Scanner;

class H_Heapsort {
    static void heapsort(int[] array) {
        int comp = 0, mov = 0;

    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        // Funcoes.preencheOrdenadamente(array);
        ZFuncoes.preencheAleatoriamente(array);
        ZFuncoes.imprimeArray(array);

        heapsort(array);
        ZFuncoes.imprimeArray(array);

        Sc.close();
    }

}