import java.util.Scanner;

class G_Countingsort {
    static void countingsort(int[] array) {
        int comp = 0, mov = 0;
        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        countingsort(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}