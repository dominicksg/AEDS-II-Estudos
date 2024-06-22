class NoTrie {
    public char letra;
    public final int tamanho = 255; // Ascii table
    public NoTrie[] prox;
    public boolean folha;

    public NoTrie() {
        this(' ');
    }

    public NoTrie(char letra) {
        this.letra = letra;
        prox = new NoTrie[tamanho];
        for (int i = 0; i < tamanho; i++) {
            prox[i] = null;
        }
        folha = false;
    }

    public static int hash(char x) {
        return (int) x;
    }
}

class ArvoreTrie {
    private NoTrie raiz;

    public ArvoreTrie() {
        raiz = new NoTrie();
    }

    // ==============================
    // ------ Metodos publicos ------
    // ==============================

    public boolean pesquisar(String palavra) throws Exception {
        return pesquisar(raiz, palavra, 0);
    }

    public void inserir(String palavra) throws Exception {
        inserir(raiz, palavra, 0);
    }

    public void mostrar() {
        mostrar("", raiz);
    }

    public int contarAs() { // Isso tá certo?
        int resp = 0;
        if (raiz != null) {
            resp = contarAs(raiz);
        }
        return resp;
    }

    // ==============================
    // ------ Metodos privados ------
    // ==============================

    private void inserir(NoTrie no, String palavra, int pos) throws Exception {
        // System.out.print("\nEM NO(" + no.letra + ") (" + pos + ")");
        if (no.prox[palavra.charAt(pos)] == null) {
            // System.out.print("--> criando filho(" + palavra.charAt(pos) + ")");
            no.prox[palavra.charAt(pos)] = new NoTrie(palavra.charAt(pos));

            if (pos == palavra.length() - 1) {
                // System.out.print("(folha)");
                no.prox[palavra.charAt(pos)].folha = true;
            } else {
                inserir(no.prox[palavra.charAt(pos)], palavra, pos + 1);
            }

        } else if (no.prox[palavra.charAt(pos)].folha == false && pos < palavra.length() - 1) {
            inserir(no.prox[palavra.charAt(pos)], palavra, pos + 1);
        } else {
            throw new Exception("Erro ao inserir!");
        }
    }

    private boolean pesquisar(NoTrie no, String palavra, int pos) throws Exception {
        boolean flag;
        if (no.prox[palavra.charAt(pos)] == null) {
            flag = false;
        } else if (pos == palavra.length() - 1) {
            flag = (no.prox[palavra.charAt(pos)].folha == true);
        } else if (pos < palavra.length() - 1) {
            flag = pesquisar(no.prox[palavra.charAt(pos)], palavra, pos + 1);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return flag;
    }

    private void mostrar(String palavra, NoTrie no) {
        if (no.folha == true) {
            System.out.println("Palavra: " + (palavra + no.letra));
        } else {
            for (int i = 0; i < no.prox.length; i++) { // 255
                if (no.prox[i] != null) {
                    System.out.println("ESTOU EM (" + no.letra + ") E VOU PARA (" + no.prox[i].letra + ")");
                    mostrar(palavra + no.letra, no.prox[i]);
                }
            }
        }
    }

    private int contarAs(NoTrie no) {
        int resp = (no.letra == 'A') ? 1 : 0;

        if (no.folha == false) {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    resp += contarAs(no.prox[i]);
                }
            }
        }
        return resp;
    }

}

public class A_ArvoreTrie {
    public static void main(String args[]) throws Exception {
        ArvoreTrie arv = new ArvoreTrie();

        // String array[] = new String[8];
        // array[0] = "ABACAXI";
        // array[1] = "BALA";
        // array[2] = "BOLO";
        // array[3] = "ABACATE";
        // array[4] = "galo";
        // array[5] = "pata";
        // array[6] = "pato";
        // array[7] = "gato";

        // for (int i = 0; i < array.length; i++) {
        // arv.inserir(array[i]);
        // }
        // arv.mostrar();

        // for (int i = 0; i < array.length; i++) {
        // System.out.println("Pesquisar(" + array[i] + "):" + arv.pesquisar(array[i]));
        // }

        // System.out.println(arv.contarAs());

        // System.out.println("Pesquisar(ABACA):" + arv.pesquisar("ABACA"));
        // System.out.println("Pesquisar(ABACAXIS):" + arv.pesquisar("ABACAXIS"));
        // System.out.println("Pesquisar(gaga):" + arv.pesquisar("gaga"));

    }
}
// cls && javac A_ArvoreTrie.java && java A_ArvoreTrie