public class C_CompABPvsAVL {
    public static void main(String args[]) throws Exception {
        // Comparando a ABP vs AVL

        ArvoreABP abp = new ArvoreABP();
        ArvoreAVL avl = new ArvoreAVL();
        int array[] = { 4, 35, 10, 13, 3, 30, 15, 12, 7, 40, 20 };
        for (int item : array) {
            // System.out.println("Inserindo -> " + item);
            avl.inserirAVL(item);
            abp.inserirABP(item);
        }

        System.out.println("Altura da AVL: " + avl.getAltura());
        System.out.println("Altura da ABP: " + abp.getAltura());
    }
}
// cls && javac C_CompABPvsAVL.java && java C_CompABPvsAVL