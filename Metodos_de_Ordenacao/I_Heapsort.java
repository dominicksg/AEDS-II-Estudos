import java.util.Scanner;

class I_Heapsort {
    static void heapsort(int[] array) {
        int comp = 0, mov = 0;
        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamente(array);
        ArrayIO.imprimeArray(array);

        heapsort(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }

}