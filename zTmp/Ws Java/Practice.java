class No {
    public char letra;
    public No prox[];
    public final int tam = 255;
    public boolean folha;

    No() {
        this(' ');
    }

    No(char letra) {
        this.letra = letra;
        folha = false;
        this.prox = new No[tam];
        for (int i = 0; i < tam; i++) {
            prox[i] = null;
        }
    }

}

class Arvore {
    private No raiz;

    Arvore() {
        raiz = new No();
    }

    public void mostrar() {
        mostrar("", raiz);

    }

    public int contar() {
        int count = 0;

        if (raiz != null) {
            count = contar(raiz);

        }
        return count;
    }

    private int contar(No no) {
        int count = (no.letra == 'a') ? 1 : 0;

        if(no

    }

}

public class Practice {
    public static void main(String args[]) {

    }
}
// cls && javac Practice.java && java Practice