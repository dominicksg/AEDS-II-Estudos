import java.util.Scanner;

class C_Insertionsort {
    static void insertionsort(int[] array) {
        int comp = 0, mov = 0;
        
        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ZFuncoes.preencheAleatoriamente(array);
        ZFuncoes.imprimeArray(array);

        insertionsort(array);
        ZFuncoes.imprimeArray(array);

        Sc.close();
    }

}