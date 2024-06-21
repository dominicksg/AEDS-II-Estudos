class NoRB {
    public boolean cor;
    public int elemento;
    public NoRB esq, dir;

    public NoRB() {
        this(-1);
    }

    public NoRB(int x) {
        this(x, false, null, null);
    }

    public NoRB(int x, boolean cor) {
        this(x, cor, null, null);
    }

    public NoRB(int x, boolean cor, NoRB esq, NoRB dir) {
        this.cor = cor;
        this.elemento = x;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreRB {
    private NoRB raiz;

    public ArvoreRB() {
        raiz = null;
    }

    // ====================================//
    // --------- Metodos Publicos ---------//
    // ====================================//

    public boolean pesquisar(int x) {
        return pesquisar(raiz, x);
    }

    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    public void inserir(int x) throws Exception {
        // Se vazia
        if (raiz == null) {
            raiz = new NoRB(x);
            System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento + ").");
        }

        // Senao, se tiver um elemento
        else if (raiz.esq == null && raiz.dir == null) {
            if (x < raiz.elemento) {
                raiz.esq = new NoRB(x);
                System.out.println(
                        "Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e esq(" + raiz.esq.elemento + ").");
            } else {
                raiz.dir = new NoRB(x);
                System.out.println(
                        "Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }

            // Senao, se tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            if (x < raiz.elemento) {
                raiz.esq = new NoRB(x);
                System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento + "), esq ("
                        + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            } else if (x < raiz.dir.elemento) {
                raiz.esq = new NoRB(raiz.elemento);
                raiz.elemento = x;
                System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento + "), esq ("
                        + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            } else {
                raiz.esq = new NoRB(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = x;
                System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento + "), esq ("
                        + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            if (x > raiz.elemento) {
                raiz.dir = new NoRB(x);
                System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento + "), esq ("
                        + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            } else if (x > raiz.esq.elemento) {
                raiz.dir = new NoRB(raiz.elemento);
                raiz.elemento = x;
                System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento + "), esq ("
                        + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            } else {
                raiz.dir = new NoRB(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = x;
                System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento + "), esq ("
                        + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se tem tres ou mais elementos
        } else {
            System.out.println("Arvore com tres ou mais elementos...");
            inserir(x, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    public boolean isNoTipo4(NoRB i) {
        return (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true);
    }

    // ====================================//
    // --------- Metodos Privados ---------//
    // ====================================//

    private boolean pesquisar(NoRB i, int x) {
        boolean flag;
        if (i == null) {
            flag = false;
        } else if (x == i.elemento) {
            flag = true;
        } else if (x < i.elemento) {
            flag = pesquisar(i.esq, x);
        } else {
            flag = pesquisar(i.dir, x);
        }
        return flag;
    }

    private void caminharCentral(NoRB i) { // Aqui é o caminharPre, para visualizar
        if (i != null) {
            System.out.print(i.elemento + ((i.cor) ? "(r) " : "(b) ")); // red:black = p:b
            caminharCentral(i.esq);
            caminharCentral(i.dir);
        }
    }

    private void inserir(int x, NoRB bisavo, NoRB avo, NoRB pai, NoRB i) throws Exception {
        if (i == null) {
            if (x < pai.elemento) {
                i = pai.esq = new NoRB(x, true);
            } else {
                i = pai.dir = new NoRB(x, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (x < i.elemento) {
                inserir(x, avo, pai, i, i.esq);
            } else if (x > i.elemento) {
                inserir(x, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private void balancear(NoRB bisavo, NoRB avo, NoRB pai, NoRB i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {

            // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento > avo.elemento) { // rotacao a esquerda ou direita-esquerda
                if (i.elemento > pai.elemento) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }

            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento < pai.elemento) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento < bisavo.elemento) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }

            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
            System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e avo.esq / avo.dir("
                    + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
        }
    }

    private NoRB rotacaoEsq(NoRB no) {
        System.out.println("Rotacao ESQ(" + no.elemento + ")");
        NoRB noDir = no.dir;
        NoRB noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoRB rotacaoDir(NoRB no) {
        System.out.println("Rotacao DIR(" + no.elemento + ")");
        NoRB noEsq = no.esq;
        NoRB noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoRB rotacaoEsqDir(NoRB no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }

    private NoRB rotacaoDirEsq(NoRB no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }
}

public class D_ArvoreBinariaRB { // RedBlack
    public static void main(String args[]) throws Exception {
        ArvoreRB arv = new ArvoreRB();

        arv.inserir(4);
        arv.caminharCentral(); // fazendo caminharPre
        arv.inserir(35);
        arv.caminharCentral();
        arv.inserir(10);
        arv.caminharCentral();
        arv.inserir(13);
        arv.caminharCentral();
        arv.inserir(3);
        arv.caminharCentral();
        arv.inserir(30);
        arv.caminharCentral();
        arv.inserir(15);
        arv.caminharCentral();
        arv.inserir(12);
        arv.caminharCentral();
        arv.inserir(7);
        arv.caminharCentral();
        arv.inserir(40);
        arv.caminharCentral();
        arv.inserir(20);
        arv.caminharCentral();
    }
}
// cls && javac D_ArvoreBinariaRedBlack.java && java D_ArvoreBinariaRedBlack
