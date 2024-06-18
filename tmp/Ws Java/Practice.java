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

    public static int getAltura(No no) {
    }

    public void setAltura() {
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

    public void remover(int x) throws Exception {
        raiz = remover(raiz, x);
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

    private No remover(No i, int x) throws Exception {
        if (i == null) {
            throw new Exception("Erro");
        } else if (x < i.elemento) {
            i.esq = remover(i.esq, x);
        } else if (x > i.elemento) {
            i.dir = remover(i.dir, x);
        } else if (i.dir == null) {
            i = i.esq;
        } else if (i.esq == null) {
            i = i.dir;
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
        return balancear(i);
    }

    private No maiorEsq(No i, No j) {
        if (j.dir == null) {
            i.elemento = j.elemento;
            j = j.esq;
        }
        j.dir = maiorEsq(i, j.dir);
        return j;
    }

    private No balancear(No i) throws Exception {

    }

    private No rotacionarEsq(No no) {

    }

    private No rotacionarDir(No no) {

    }
}

public class Practice {
    public static void main(String args[]) {
    }
}

// cls && javac Practice.java && java Practice