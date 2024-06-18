class No {
    public int elemento;
    public No esq, dir;
    public int altura;

    public No() {
        this(-1, null, null, 1);
    }

    public No(int x) {
        this(x, null, null, 1);
    }

    public No(int x, No esq, No dir, int altura) {
        this.elemento = x;
        this.esq = esq;
        this.dir = dir;
        this.altura = altura;
    }

    public static int getAltura(No i) {
        return (i == null) ? 0 : i.altura;
    }

    public void setAltura() {
        this.altura = 1 + Math.max(getAltura(esq), getAltura(dir));
    }
}

class Arvore {
    private No raiz;

    Arvore() {
        this.raiz = null;
    }

    Arvore(int elemento) {
        this.raiz = new No(elemento);
    }

    // ====================================//
    // --------- Metodos Publicos ---------//
    // ====================================//

    public void inserir(int x) throws Exception {
        raiz = inserir(raiz, x);
    }

    public boolean pesquisar(int x) {
        return pesquisar(raiz, x);
    }

    public void caminharCentral() {
        caminharCentral(raiz);
    }

    public void remover(int x) {
        raiz = remover(raiz, x);
    }

    public int getAltura() {
        return getAltura(raiz);
    }

    // ====================================//
    // --------- Metodos Privados ---------//
    // ====================================//

    private No inserir(No i, int x) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(i.esq, x);
        } else if (x > i.elemento) {
            i.dir = inserir(i.dir, x);
        } else {
            throw new Exception("Erro");
        }
        return balancear(i);
    }

    private boolean pesquisar(No i, int x) {
        boolean flag = false;
        if (i == null) {
            return flag;
        } else if (i.elemento == x) {
            flag = true;
        } else if (x < i.elemento) {
            flag = pesquisar(i.esq, x);
        } else {
            flag = pesquisar(i.dir, x);
        }
        return flag;
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento);
            caminharCentral(i.dir);
        }
    }

    // maioresq
    // balancear
    // rotacionardir
    // rotacionaresq



    private int getAltura(No i) {
        int alturaEsq = 0;
        int alturaDir = 0;
        if (i != null) {
            alturaEsq = getAltura(i.esq) + 1;
            alturaDir = getAltura(i.dir) + 1;
        }

        return (alturaEsq < alturaDir) ? alturaDir : alturaEsq;

    }
}

public class Practice {
    public static void main(String args[]) {
    }
}

// cls && javac Practice.java && java Practice