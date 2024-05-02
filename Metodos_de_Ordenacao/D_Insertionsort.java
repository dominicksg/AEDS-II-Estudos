import java.util.Scanner;

class D_Insertionsort {
    static void insertionsort(int[] array) {
        int comp = 0, mov = 0;
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i - 1;
            while ((j >= 0) && (array[j] > tmp)) {
                array[j + 1] = array[j];
                j--;
                mov++;
                comp++;
            }
            array[j + 1] = tmp;
            mov++;
        }
        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        insertionsort(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}
// Foram feitas 600 comparacoes e 649 movimentacoes.