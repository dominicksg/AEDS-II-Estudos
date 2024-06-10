class NoAVL {
    public int elemento;
    public NoAVL esq, dir;
    public int altura;

    public NoAVL(int x) {
        this.elemento = x;
        this.altura = 0;
        this.esq = this.dir = null;
    }

    public NoAVL(int x, NoAVL esq, NoAVL dir, int altura) {
        this.elemento = x;
        this.esq = esq;
        this.dir = dir;
        this.altura = altura;
    }

}

class ArvoreAVL {
    private NoAVL raiz;

    ArvoreAVL(int x) {
        raiz = new NoAVL(x);
    }

    // ====================================//
    // --------- Metodos Publicos ---------//
    // ====================================//

    // ====================================//
    // --------- Metodos Privados ---------//
    // ====================================//

}

public class Practice {

}
// cls && javac Practice.java && java Practice