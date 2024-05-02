import java.util.Scanner;

class D_Insertionsort {
    static void insertionsort(int[] array) {
        int comp = 0, mov = 0;
        
        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamente(array);
        ArrayIO.imprimeArray(array);

        insertionsort(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }

}