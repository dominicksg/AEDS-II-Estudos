import java.util.Scanner;

class B_Insertionsort {
    static void insertionsort(int[] array) {
        int comp = 0, mov = 0;

    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        // Funcoes.preencheOrdenadamente(array);
        ZFuncoes.preencheAleatoriamente(array);
        ZFuncoes.imprimeArray(array);

        insertionsort(array);
        ZFuncoes.imprimeArray(array);

        Sc.close();
    }

}