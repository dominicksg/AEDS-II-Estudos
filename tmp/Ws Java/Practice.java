class NoAVL {
    public int elemento;
    public int altura;
    public NoAVL esq, dir;

    NoAVL() {
        this.elemento = -1;
        this.altura = 0;
        this.esq = this.dir = null;
    }

    NoAVL(int x) {
        this.elemento = x;
        this.altura = 0;
        this.esq = this.dir = null;
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
// cls && javac Practice.java && java Practice < pub.in > saida.txt