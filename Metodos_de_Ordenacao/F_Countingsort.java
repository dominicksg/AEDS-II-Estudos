import java.util.Scanner;

class F_Countingsort {
    static void countingsort(int[] array) {
        int comp = 0, mov = 0;

    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        // Funcoes.preencheOrdenadamente(array);
        ZFuncoes.preencheAleatoriamente(array);
        ZFuncoes.imprimeArray(array);

        countingsort(array);
        ZFuncoes.imprimeArray(array);

        Sc.close();
    }

}