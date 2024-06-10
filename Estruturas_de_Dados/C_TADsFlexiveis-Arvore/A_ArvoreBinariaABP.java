class NoABP {
    public int elemento;
    public NoABP dir, esq;

    NoABP() {
        this.elemento = 0;
        this.dir = this.esq = null;
    }

    NoABP(int x) {
        this.elemento = x;
        this.dir = this.esq = null;
    }
}

class ArvoreABP {
    private NoABP raiz;

    ArvoreABP(int x) {
        raiz = new NoABP(x);
    }

    // ====================================//
    // --------- Metodos Publicos ---------//
    // ====================================//

    public void inserir(int x) {
        raiz = inserir(raiz, x);
    }

    public boolean pesquisa(int x) {
        boolean flag = pesquisa(raiz, x);
        return flag;
    }

    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");

    }

    public int soma() {
        int soma = soma(raiz);
        return soma;
    }

    public int getAltura() {
        int altura = getAltura(raiz);
        return altura;
    }

    // ====================================//
    // --------- Metodos Privados ---------//
    // ====================================//

    private NoABP inserir(NoABP i, int x) {
        if (i == null) {
            i = new NoABP(x);
        } else if (x > i.elemento) {
            i.dir = inserir(i.dir, x);
        } else if (x < i.elemento) {
            i.esq = inserir(i.esq, x);
        } else {
            System.out.println("ERRO");
        }
        return i;
    }

    private boolean pesquisa(NoABP i, int key) {
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

    private void caminharCentral(NoABP i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento + " "); // Pre ordem, sysout antes, pos ordem depois
            caminharCentral(i.dir);
        }
    }
    // Pre ordem prioriza o pai, pq vem antes
    // Pos ordem prioriza os filhos, pq vem depois,
    // a raiz será o ultimo a ser impresso

    private int soma(NoABP i) {
        // if (i == null) {
        // return 0;
        // }
        // int soma = i.elemento + soma(i.esq) + soma(i.dir); // são os "i.elemento" que
        // realizam a soma
        // // int soma = (i == null) ? 0 : i.elemento + soma(i.esq) + soma(i.dir);
        // return soma;

        // --------- Seguindo a lógica do caminharCentral ---------//
        int soma = 0;
        if (i != null) {
            soma += i.elemento; // Adiciona o elemento do nó atual
            soma += soma(i.esq);
            soma += soma(i.dir);
        }
        return soma;
    }

    private int getAltura(NoABP i) {
        if (i == null) {
            return -1;
        }
        int alturaEsq = 1 + getAltura(i.esq);
        int alturaDir = 1 + getAltura(i.dir);

        int altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;

        return altura;
    }
}

public class A_ArvoreBinariaABP {
    public static void main(String args[]) {
        ArvoreABP arv = new ArvoreABP(5);
        arv.caminharCentral();

        arv.inserir(2);
        arv.caminharCentral();

        arv.inserir(3);
        arv.caminharCentral();

        arv.inserir(1);
        arv.caminharCentral();

        System.out.println(arv.pesquisa(3));
        System.out.println(arv.pesquisa(4));

        System.out.println(arv.soma());
        System.out.println(arv.getAltura());

    }
}
