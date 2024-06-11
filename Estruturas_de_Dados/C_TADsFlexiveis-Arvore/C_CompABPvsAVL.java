public class C_CompABPvsAVL {
    public static void main(String args[]) throws Exception {
        // Comparando a ABP vs AVL

        ArvoreABP abp = new ArvoreABP();
        ArvoreAVL avl = new ArvoreAVL();
        int array[] = { 4, 35, 10, 13, 3, 30, 15, 12, 7, 40, 20 };
        for (int item : array) { // for each
            // System.out.println("Inserindo -> " + item);
            avl.inserirAVL(item);
            abp.inserirABP(item);
        }

        System.out.println("Altura da AVL: " + avl.getAltura());
        System.out.println("Altura da ABP: " + abp.getAltura());
    }
}
// cls && javac C_CompABPvsAVL.java && java C_CompABPvsAVL

// Guia for each:
// int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
// for (int i = 0; i < array.length; i++) {
// int item = array[i];
// System.out.println("Inserindo -> " + item);