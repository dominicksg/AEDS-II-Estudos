import java.util.Scanner;

class I_Heapsort {
    static int[] heapsort(int[] array) {
        // int comp = 0, mov = 0;

        // int n = array.length - 1; // Desconsiderando a posição zero

        // ----------------------- (OPCIONAL INICIO) -----------------------
        // Alterar o vetor ignorando a posicao zero
        int n = array.length;
        int[] tmp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            tmp[i + 1] = array[i];
        }
        array = tmp;
        // Criando uma nova referência local, e isso NÃO altera o array original.
        // Preciso RETORNAR o array, caso queira isso
        // ----------------------- (OPCIONAL FIM) -----------------------

        // Constroi Heap
        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            construir(tamHeap, array);
        }

        // Ordenando
        int tamHeap = n;
        while (tamHeap > 1) {
            swap(1, tamHeap--, array);
            reconstruir(tamHeap, array);
        }

        // ----------------------- (OPCIONAL INICIO) -----------------------
        // Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = tmp[i + 1];
        }
        return array;
        // ----------------------- (OPCIONAL FIM) -----------------------

        // System.out.println("Foram feitas " + comp + " comparacoes e " + mov + "
        // movimentacoes.");
    }

    static void construir(int tamHeap, int[] array) {
        for (int i = tamHeap; i > 1 && array[i] > array[i / 2]; i /= 2) {
            swap(i, i / 2, array);
        }
    }

    static void reconstruir(int tamHeap, int[] array) {
        int i = 1;
        // while (hasFilho(i, tamHeap) == true) {
        while (i <= (tamHeap / 2)) { // Mesmo código de cima
            int filho = getMaiorFilho(i, tamHeap, array); // Retorna a POSICAO
            if (array[i] < array[filho]) {
                swap(i, filho, array);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    static int getMaiorFilho(int i, int tamHeap, int[] array) {
        int filho;
        if (2 * i == tamHeap || array[2 * i] > array[2 * i + 1]) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    static boolean hasFilho(int i, int tamHeap, int[] array) {
        return (i <= (tamHeap / 2));
    }

    static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static int[] heapParcial(int[] array, int k) {
        int n = array.length;

        // Constroi Heap com os k primeiros elementos
        for (int tamHeap = 2; tamHeap <= k; tamHeap++) {
            construir(tamHeap, array);
        }

        // Para cada um dos (n-k) demais elementos, se ele for
        // menor que a raiz, inserir do heap
        for (int i = k + 1; i <= n; i++) {
            if (array[i] < array[1]) {
                swap(i, 1, array);
                reconstruir(k, array);
            }
        }

        // Ordenando
        int tamHeap = k;
        // int tamHeap = array.length - 1; // Desconsiderando a posição zero
        while (tamHeap > 1) {
            swap(1, tamHeap--, array);
            reconstruir(tamHeap, array);
        }

        return array;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        long startTime = System.currentTimeMillis();
        array = heapsort(array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}

// int[] comp_mov = new int[2];

// for (int tam = 2; tam < personagens.length; tam++) {
// construir(tam, personagens, comp_mov);
// }

// int tam = personagens.length - 1;
// while (tam > 1) {
// swap(1, tam, personagens);
// comp_mov[1] += 3;
// tam--;
// reconstruir(tam, personagens, comp_mov);

// }