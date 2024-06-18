class No {
    public int elemento;
    public No esq, dir;
    public int altura;

    public No() {
        this(-1, 0, null, null);
    }

    public No(int elemento) {
        this(elemento, 0, null, null); // OLHAR AQUI
    }

    public No(int elemento, int altura, No esq, No dir) {
        this.elemento = elemento;
        this.altura = altura;
        this.esq = this.dir = null;
    }
}

class Arvore {
    private No raiz;

    Arvore() {
        this(-1);
    }

    Arvore(int elemento) {
        this.raiz = new No(elemento);
    }

    // ====================================//
    // --------- Metodos Publicos ---------//
    // ====================================//

    public void inserir() {
    }

    public boolean pesquisar() {
    }

    public void caminharCentral() {
    }

    public int soma() {
    }

    public int getAltura() {
    }

    public No remover() {
    }

    // ====================================//
    // --------- Metodos Privados ---------//
    // ====================================//

}

public class Practice {
    public static void main(String args[]) {
    }
}
