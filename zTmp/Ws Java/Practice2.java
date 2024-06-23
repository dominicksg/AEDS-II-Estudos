class HashTable {
    public int tabela[];
    final public int NULO = -1;
    public int m1, m2, m, inReserva;

    public HashTable(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new int[m];

        for (int i = 0; i < m; i++) {
            tabela[i] = NULO;
        }

        inReserva = 0;
    }

    public int h(int elemento) {
        return elemento % m1;
    }

    public boolean pesquisar(int elemento) {


    }

    public mostrar(){

    }
}

public class Practice2 {
    public static void main(String args[]) {
        int teste[] = new int[10];

        System.out.println(teste.length);

    }
}
// cls && javac Practice2.java && java Practice2