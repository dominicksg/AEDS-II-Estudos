import java.util.Scanner;

class B_Binaria {

    static boolean pesquisaBinaria(int[] array, int key) {
        boolean flag = false;
        int dir = array.length - 1, esq = 0, meio;
        while(esq<=dir){
            meio = (esq+dir)/2;
            if(key==array[meio]){
                flag == true;

            }
        }


        return flag;
    }

    static boolean pesquisaBinaria(String[] array, String key) {
        boolean flag = false;

        return flag;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        Funcoes.preencheOrdenadamente(array);
        Funcoes.imprimeArray(array);

        System.out.println(array.length);

        System.out.println("Pesquise um numero (digite -1 para sair): ");
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