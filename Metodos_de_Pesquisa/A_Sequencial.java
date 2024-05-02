import java.util.Scanner;

class A_Sequencial {
    static boolean pesquisaSequencial(int[] array, int key) {
        int comp = 0;
        boolean flag = false;
        for (int i = 0; i < array.length; i++) { // Consigo melhorar esse for, sem o If
            comp++;
            if (array[i] == key) {
                flag = true;
                i = array.length;
            }
        }
        System.out.println("Foram feitas " + comp + " comparacoes.");
        return flag;
    }

    static boolean pesquisaSequencial(String[] array, String key) {
        boolean flag = false;

        return flag;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ZFuncoes.preencheOrdenadamente(array);
        // Funcoes.preencheAleatoriamente(array);
        ZFuncoes.imprimeArray(array);

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
