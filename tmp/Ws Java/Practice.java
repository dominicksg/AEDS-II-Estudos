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

    public void inserir(int x) {
        this.raiz = inserir(raiz, x);
    }

    public boolean pesquisar(int x) {
        return pesquisar(raiz, x);
    }

    public void caminharCentral() {
        caminharCentral(raiz);
    }

    public int soma() {
        return soma(raiz);
    }

    public int getAltura() {
        return getAltura(raiz);
    }

    public No remover(int x) {
        raiz = remover(raiz, x);
    }

    // ====================================//
    // --------- Metodos Privados ---------//
    // ====================================//

    private No inserir(No i, int x) {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(i.esq, x);
        } else if (x > i.elemento) {
            i.dir = inserir(i.dir, x);
        } else {
            System.out.println("ERRO");
        }
        return i;
    }

    private boolean pesquisar(No i, int x) {
        boolean flag = false;
        if (i == null) {
            return flag;
        } else if (x == i.elemento) {
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

    private int soma(No i) {
        int soma = 0;
        if (i != null) {
            soma = i.elemento + soma(i.esq) + soma(i.dir);
        }
        return soma;
    }

    private int getAltura(No i) {
        int alturaDir = 0;
        int alturaEsq = 0;
        if (i != null) {
            alturaEsq = getAltura(i.esq) + 1;
            alturaDir = getAltura(i.dir) + 1;
        }

        return (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
    }

    private No remover(No i, int x) {
        if (i == null) {
            System.out.println("ERRO");
        } else if (x < i.elemento) {
            i.esq = remover(i.esq, x);
        } else if (x > i.elemento) {
            i.dir = remover(i.dir, x);
        } else if (i.dir == null) {
            i = i.esq;
        } else if (i.dir == null) {
            i = i.dir;
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
        return i;
    }

    private No maiorEsq(No i, No j) {
        if (j.dir == null) {
            i.elemento = j.elemento;
            j = j.esq;
        } else {
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

}

public class Practice {
    public static void main(String args[]) {
    }
}
