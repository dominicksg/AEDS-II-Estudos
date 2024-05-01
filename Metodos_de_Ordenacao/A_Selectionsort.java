import java.util.Scanner;

class A_Selectionsort {
    static boolean selectionsort(int[] array) {
        int comp = 0, moves = 0;
        boolean flag = false;

        return flag;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        // Funcoes.preencheOrdenadamente(array);
        Funcoes.preencheAleatoriamente(array);
        Funcoes.imprimeArray(array);

        selectionsort(array);
        Funcoes.imprimeArray(array);   

        Sc.close();
    }
}
