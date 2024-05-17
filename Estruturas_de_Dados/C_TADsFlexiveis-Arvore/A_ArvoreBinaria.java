class No {
    public int elemento;
    public No dir, esq;

    No() {
        this.elemento = 0;
        this.dir = this.esq = null;
    }

    No(int x) {
        this.elemento = x;
        this.dir = this.esq = null;
    }
}

class Arvore {
    private No raiz;

    Arvore(int x) {
        raiz = new No(x);
    }

    public int soma() {
        int soma = soma(raiz);
        return soma;
    }

    public int getAltura() {
        int altura = getAltura(raiz);
        return altura;
    }

    public void inserir(int x) {
        raiz = inserir(raiz, x);
    }

    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");

    }

    public boolean pesquisa(int x) {
        boolean flag = pesquisa(raiz, x);
        return flag;
    }

    private boolean pesquisa(No i, int key) {
        boolean flag = false;

        if (i == null) {
            return flag;
        } else if (key > i.elemento) {
            flag = pesquisa(i.dir, key);
        } else if (key < i.elemento) {
            flag = pesquisa(i.esq, key);
        } else {
            flag = true;
        }
        return flag;
    }

    private No inserir(No i, int x) {
        if (i == null) {
            i = new No(x);
        } else if (x > i.elemento) {
            i.dir = inserir(i.dir, x);
        } else if (x < i.elemento) {
            i.esq = inserir(i.esq, x);
        } else {
            System.out.println("ERRO");
        }
        return i;
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    private int soma(No i) {
        if (i == null) {
            return 0;

        }
        int soma = i.elemento + soma(i.esq) + soma(i.dir); // são os "i.elemento" que realizam a soma

        // int soma = (i == null) ? 0 : i.elemento + soma(i.esq) + soma(i.dir);
        return soma;

    }

    private int getAltura(No i) {
        if (i == null) {
            return -1;
        }

        int alturaEsq = 1 + getAltura(i.esq);
        int alturaDir = 1 + getAltura(i.dir);

        int altura = (alturaEsq > alturaDir)?alturaEsq : alturaDir;

        return altura;
             
    }

    // TREE SORT
}

public class ArvoreBinaria {
    public static void main(String args[]) {
        Arvore arv = new Arvore(5);
        arv.caminharCentral();

        arv.inserir(2);
        arv.caminharCentral();

        arv.inserir(3);
        arv.caminharCentral();

        arv.inserir(1);
        arv.caminharCentral();

        System.out.println(arv.getAltura());

    }
}
