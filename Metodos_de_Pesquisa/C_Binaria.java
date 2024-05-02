import java.util.Scanner;

class C_Binaria {
    static boolean pesquisaBinaria(int[] array, int key) {
        int comp = 0;
        boolean flag = false;
        int dir = array.length - 1, esq = 0, meio;
        System.out.print("Meios: [ ");
        while (esq <= dir) {
            meio = (esq + dir) / 2;
            System.out.print(meio + " ");
            comp++;
            if (key == array[meio]) {
                flag = true;
                esq = array.length;
            } else if (key > array[meio]) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        System.out.println("]");
        System.out.println("Foram feitas " + comp + " comparacoes.");
        return flag;
    }

    static boolean pesquisaBinaria(String[] array, String key) {
        boolean flag = false;

        return flag;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheOrdenadamente(array);
        // Funcoes.preencheAleatoriamente(array);
        ArrayIO.imprimeArray(array);

        System.out.print("Pesquise um numero (digite -1 para sair): ");
        int num = Sc.nextInt();
        while (num != -1) {
            if (pesquisaBinaria(array, num) == true) {
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