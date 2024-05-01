import java.util.Scanner;

class E_Shellsort {
    static void shellsort(int[] array) {
        int comp = 0, mov = 0;
        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        // Funcoes.preencheOrdenadamente(array);
        ZFuncoes.preencheAleatoriamente(array);
        ZFuncoes.imprimeArray(array);

        shellsort(array);
        ZFuncoes.imprimeArray(array);

        Sc.close();
    }

}