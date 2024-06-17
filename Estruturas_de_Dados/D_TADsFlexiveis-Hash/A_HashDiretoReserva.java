class Hash {
    int tabela[];
    int m1, m2, m, reserva;
    final int NULO = -1;
    // A palavra-chave "final" é usada para declarar constantes, que são variáveis
    // cujo valor não pode ser alterado após a inicialização.

    public Hash() {
        this(13, 7);
    }

    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new int[this.m];

        for (int i = 0; i < m1; i++) {
            tabela[i] = NULO;
        }
        reserva = 0;
    }

    public int h(int elemento) {
        return elemento % m1;
    }

    public boolean inserir(int elemento) {
        boolean resp = false;
        if (elemento != NULO) {
            int pos = h(elemento);
            if (tabela[pos] == NULO) {
                tabela[pos] = elemento;
                resp = true;
            } else if (reserva < m2) {
                tabela[m1 + reserva] = elemento;
                reserva++;
                resp = true;
            }
        }
        return resp;
    }

    public boolean pesquisar(int elemento) {
        boolean resp = false;
        int pos = h(elemento);
        if (tabela[pos] == elemento) {
            resp = true;
        } else if (tabela[pos] != NULO) {
            for (int i = 0; i < reserva; i++) {
                if (tabela[m1 + i] == elemento) {
                    resp = true;
                    i = reserva;
                }
            }
        }
        return resp;
    }

    public boolean remover(int elemento) {
        boolean resp = false;
        // ...
        return resp;
    }

    public void mostrarTudo() {
        System.out.print("Vetor inteiro = [ ");

        for (int i = 0; i < m1 + reserva; i++) {
            if (tabela[i] != NULO) {
                System.out.print(tabela[i] + " ");
            }
        }
        System.out.println("]");

    }

    public void mostrarPrincipal() {
        System.out.print("Vetor Principal = [ ");

        for (int i = 0; i < m1; i++) {
            if (tabela[i] != NULO) {
                System.out.print(tabela[i] + " ");
            }
        }
        System.out.println("]");

    }

    public void mostrarReserva() {
        System.out.print("Vetor Reserva = [ ");

        for (int i = m1; i < m1 + reserva; i++) {
            if (tabela[i] != NULO) {
                System.out.print(tabela[i] + " ");
            }
        }
        System.out.println("]");
    }

}

public class A_HashDiretoReserva {
    public static void main(String[] args) {
        Hash tabelaHash = new Hash();
        // System.out.println(0 % 13);

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(0);
        tabelaHash.mostrarPrincipal();
        tabelaHash.mostrarReserva();
        tabelaHash.mostrarTudo();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(3);
        tabelaHash.mostrarPrincipal();
        tabelaHash.mostrarReserva();
        tabelaHash.mostrarTudo();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(4);
        tabelaHash.mostrarPrincipal();
        tabelaHash.mostrarReserva();
        tabelaHash.mostrarTudo();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(2);
        tabelaHash.mostrarPrincipal();
        tabelaHash.mostrarReserva();
        tabelaHash.mostrarTudo();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(5);
        tabelaHash.mostrarPrincipal();
        tabelaHash.mostrarReserva();
        tabelaHash.mostrarTudo();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(1);
        tabelaHash.mostrarPrincipal();
        tabelaHash.mostrarReserva();
        tabelaHash.mostrarTudo();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserir(13);
        tabelaHash.mostrarPrincipal();
        tabelaHash.mostrarReserva();
        tabelaHash.mostrarTudo();

        // System.out.println(tabelaHash.pesquisar(6));

        // ----------------------------//

    }
}

// cls && javac A_HashDiretoReserva.java && java A_HashDiretoReserva