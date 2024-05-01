import java.util.Scanner;

class A_Selectionsort {
    static boolean selectionsort(int[] array, int key) {
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

        System.out.print("Pesquise um numero (digite -1 para sair): ");
        int num = Sc.nextInt();
        while (num != -1) {
            if (pesquisaSequencial(array, num) == true) {
                System.out.println("Numero " + num + " encontrado.");
            } else {
                System.out.println("Numero " + num + " nao encontrado.");
            }
            System.out.println("Pesquise um numero (digite -1 para sair): ");
            num = Sc.nextInt();
        }
        Sc.close();
    }
}
