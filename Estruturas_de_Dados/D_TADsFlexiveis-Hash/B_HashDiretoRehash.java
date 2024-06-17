class HashRehash {
    int tabela[];
    int m;
    final int NULO = -1;

    public HashRehash() {
        this(13);
    }

    public HashRehash(int m) {
        this.m = m;
        this.tabela = new int[this.m];

        for (int i = 0; i < m; i++) {
            tabela[i] = NULO;
        }
    }

    public int h(int elemento) {
        return elemento % m;
    }

    public int reH(int elemento) {
        return ++elemento % m;
    }

    public boolean inserir(int elemento) {
        boolean flag = false;
        int pos = h(elemento);

        if (elemento != NULO) {
            if (tabela[pos] == NULO) {
                tabela[pos] = elemento;
                flag = true;
            } else { // } else if (tabela[reH(elemento)] == NULO) {
                pos = reH(elemento);
                if (tabela[pos] == NULO) {
                    tabela[pos] = elemento;
                    flag = true;
                }
            }
        }
        return flag;
    }

    public boolean pesquisar(int elemento) {
        boolean flag = false;
        int pos = h(elemento);

        if (tabela[pos] == elemento) {
            flag = true;
        } else if (tabela[pos] != NULO) {
            pos = reH(elemento);
            if (tabela[pos] == elemento) {
                flag = true;
            }
        }
        return flag;
    }

    public void mostrar() {
        System.out.print("Vetor inteiro = [ ");

        for (int i = 0; i < m; i++) {
            if (tabela[i] != NULO) {
                System.out.print(tabela[i] + " ");
            }
        }
        System.out.println("]");
    }
}

public class B_HashDiretoRehash {
    public static void main(String args[]) {
        HashRehash tabelaHash = new HashRehash();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(0);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(3);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(4);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(2);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(5);
        tabelaHash.mostrar();

        // System.out.println("========== Inserindo ==========");
        // tabelaHash.inserir(1);
        // tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(13);
        tabelaHash.mostrar();

        System.out.println(tabelaHash.pesquisar(6));
        System.out.println(tabelaHash.pesquisar(0));
        System.out.println(tabelaHash.pesquisar(13));
    }
}
// cls && javac B_HashDiretoRehash.java && java B_HashDiretoRehash