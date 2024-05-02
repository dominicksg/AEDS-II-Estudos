import java.util.Scanner;

class H_Mergesort {
    static void mergesort(int esq, int dir, int[] array) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(esq, meio, array); // Ordenar recursivamente a metade esquerda
            mergesort(meio + 1, dir, array); // Ordenar recursivamente a metade direita
            intercalar(esq, meio, dir, array); // Mesclar as duas metades
        }
    }

    static void intercalar(int esq, int meio, int dir, int[] array) {
        int nEsq = meio - esq + 1; // Tamanho do subarray esquerdo
        int nDir = dir - meio; // Tamanho do subarray direito

        int[] arrayEsq = new int[nEsq + 1]; // +1 para a sentinela
        int[] arrayDir = new int[nDir + 1]; // +1 para a sentinela

        int iEsq, iDir;

        // Copiar dados para os subarrays temporários
        for (iEsq = 0; iEsq < nEsq; iEsq++) {
            arrayEsq[iEsq] = array[esq + iEsq];
        }

        for (iDir = 0; iDir < nDir; iDir++) {
            arrayDir[iDir] = array[(meio + 1) + iDir];
        }

        // Sentinela no final dos dois arrays
        arrayEsq[nEsq] = arrayDir[nDir] = 0x7FFFFFFF;

        iEsq = iDir = 0;

        // Mesclar/Intercalar de volta para o array principal
        // for (iEsq = iDir = 0, i = esq; i <= dir; i++) {
        // array[i] = (arrayEsq[iEsq] <= arrayDir[iDir]) ? arrayEsq[iEsq++] :
        // arrayDir[iDir++];
        // }

        for (int i = esq; i <= dir; i++) {
            if (arrayEsq[iEsq] <= arrayDir[iDir]) {
                array[i] = arrayEsq[iEsq++];
            } else {
                array[i] = arrayDir[iDir++];
            }
        }
    }

    // -----------------------------------------------------------------
    // ------------- PARA CONTAGEM, INGORE O CODIGO ABAIXO -------------
    // -----------------------------------------------------------------

    static void mergesortconta(int esq, int dir, int[] array, int[] counts) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesortconta(esq, meio, array, counts);
            mergesortconta(meio + 1, dir, array, counts);
            intercalarconta(esq, meio, dir, array, counts);
        }
    }

    static void intercalarconta(int esq, int meio, int dir, int[] array, int[] counts) {
        int nEsq = meio - esq + 1;
        int nDir = dir - meio;

        int[] arrayEsq = new int[nEsq];
        int[] arrayDir = new int[nDir];

        for (int i = 0; i < nEsq; i++) {
            arrayEsq[i] = array[esq + i];
        }
        for (int j = 0; j < nDir; j++) {
            arrayDir[j] = array[meio + 1 + j];
        }

        int iEsq = 0, iDir = 0, i = esq;
        while (iEsq < nEsq && iDir < nDir) {
            counts[0]++; // Contagem de comparações
            if (arrayEsq[iEsq] <= arrayDir[iDir]) {
                array[i] = arrayEsq[iEsq++];
            } else {
                array[i] = arrayDir[iDir++];
            }
            counts[1]++; // Contagem de movimentações
            i++;
        }

        while (iEsq < nEsq) {
            array[i++] = arrayEsq[iEsq++];
            counts[1]++; // Contagem de movimentações
        }
        while (iDir < nDir) {
            array[i++] = arrayDir[iDir++];
            counts[1]++; // Contagem de movimentações
        }
    }

    static void mergesortconta(int esq, int dir, int[] array) {
        int[] counts = { 0, 0 }; // counts[0] para comparações, counts[1] para movimentações
        mergesortconta(esq, dir, array, counts);
        System.out.println("Foram feitas " + counts[0] + " comparacoes e " + counts[1] + " movimentacoes.");
    }

    // -----------------------------------------------------------------
    // ------------- PARA CONTAGEM, INGORE O CODIGO ACIMA -------------
    // -----------------------------------------------------------------

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        long startTime = System.currentTimeMillis();
        // mergesort(0, array.length - 1, array);
        mergesortconta(0, array.length - 1, array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}
// Foram feitas 226 comparacoes e 286 movimentacoes.
// Tempo de execucao: 4 ms