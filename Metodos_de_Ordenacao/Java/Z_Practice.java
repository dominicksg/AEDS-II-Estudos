class Z_Practice {

    static void Sort(int[] array, int esq, int dir) {

    }

    public static void main(String[] args) {
        int[] array = new int[50];
        ArrayIO.preencheAleatoriamente(array, 42);
        ArrayIO.imprimeArray(array);

        Sort(array, 0, 49);
        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);
    }
}

// int[] array = new int[50];
// ArrayIO.preencheAleatoriamente(array, 42);
// ArrayIO.imprimeArray(array);

// Heap(array);
// ArrayIO.checkOrdenado(array);
// ArrayIO.imprimeArray(array);
