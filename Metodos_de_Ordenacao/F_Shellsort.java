import java.util.Scanner;

class F_Shellsort {
    static void shellsort(int[] array) {
        int[] counts = { 0, 0 }; // counts[0] para comparações, counts[1] para movimentações
        int h = 1;
        while (h < array.length) {
            h = (h * 3) + 1;
        }
        while (h != 1) {
            h /= 3;
            for (int cor = 0; cor < h; cor++) {
                insercaoPorCor(cor, h, array, counts);
            }
        }
        System.out.println("Foram feitas " + counts[0] + " comparacoes e " + counts[1] + " movimentacoes.");
    }

    // O código abaixo é o original, e é mais eficiente também,
    // pois não precisa verificar a condição antes da primeira execução
    // do bloco de código.
    // Mesmo assim, montei o código anterior para melhor entendimento e estudo.
    // int[] counts = { 0, 0 }; // counts[0] para comparações, counts[1] para
    // movimentações
    // int h = 1;
    // do {
    // h = (h * 3) + 1;
    // } while (h < array.length);
    // do {
    // h /= 3;
    // for (int cor = 0; cor < h; cor++) {
    // insercaoPorCor(cor, h, array, counts);
    // }
    // } while (h != 1);
    // System.out.println("Foram feitas " + counts[0] + " comparacoes e " +
    // counts[1] + " movimentacoes.");
    // }

    static void insercaoPorCor(int cor, int h, int[] array, int[] counts) {
        for (int i = (h + cor); i < array.length; i += h) {
            int tmp = array[i];
            int j = i - h;
            counts[0]++;
            while ((j >= 0) && (array[j] > tmp)) {
                array[j + h] = array[j];
                j -= h;
                counts[1]++;
            }
            array[j + h] = tmp;
        }
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        shellsort(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}
// Foram feitas 142 comparacoes e 177 movimentacoes.