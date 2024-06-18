class NoAVL {
    public int elemento;
    public NoAVL esq, dir;
    public int altura;

    public NoAVL() {
        this(-1, null, null, 1);
    }

    public NoAVL(int x) {
        this(x, null, null, 1);
    }

    public NoAVL(int x, NoAVL esq, NoAVL dir, int altura) {
        this.elemento = x;
        this.esq = esq;
        this.dir = dir;
        this.altura = altura;
    }

    public static int getAltura(NoAVL no) {
        return (no == null) ? 0 : no.altura;
    }

    public void setAltura() {
        this.altura = 1 + Math.max(getAltura(esq), getAltura(dir));
    }
}

class ArvoreAVL {
    private NoAVL raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    public ArvoreAVL(int x) {
        raiz = new NoAVL(x);
    }

    // ====================================//
    // --------- Metodos Publicos ---------//
    // ====================================//

    public void inserirAVL(int x) throws Exception {
        raiz = inserirAVL(raiz, x);
    }

    public boolean pesquisar(int x) {
        boolean flag = pesquisar(raiz, x);
        return flag;
    }

    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    public void removerAVL(int x) throws Exception {
        raiz = removerAVL(raiz, x);
    }

    public int getAltura() {
        int altura = getAltura(raiz);
        return altura;
    }

    // ====================================//
    // --------- Metodos Privados ---------//
    // ====================================//

    private NoAVL inserirAVL(NoAVL i, int x) throws Exception {
        if (i == null) {
            i = new NoAVL(x);
        } else if (x < i.elemento) {
            i.esq = inserirAVL(i.esq, x);
        } else if (x > i.elemento) {
            i.dir = inserirAVL(i.dir, x);
        } else {
            throw new Exception("Erro");
        }
        return balancear(i);
    }

    private boolean pesquisar(NoAVL i, int x) {
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

    private void caminharCentral(NoAVL i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    private NoAVL removerAVL(NoAVL i, int x) throws Exception {
        if (i == null) {
            throw new Exception("Erro");
        } else if (x < i.elemento) {
            i.esq = removerAVL(i.esq, x);
        } else if (x > i.elemento) {
            i.dir = removerAVL(i.dir, x);
        } else if (i.dir == null) { // Sem no a direita.
            i = i.esq;
        } else if (i.esq == null) { // Sem no a esquerda.
            i = i.dir;
        } else {
            i.esq = maiorEsq(i, i.esq); // No a esquerda e no a direita.
        }
        return balancear(i);
    }

    private NoAVL maiorEsq(NoAVL i, NoAVL j) {
        // Encontrou o maximo da subarvore esquerda.
        if (j.dir == null) {
            i.elemento = j.elemento;// Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.
        } else { // Existe no a direita.
            j.dir = maiorEsq(i, j.dir); // Caminha para direita.
        }
        return j;
    }

    private NoAVL balancear(NoAVL no) throws Exception {
        if (no != null) {
            int fator = NoAVL.getAltura(no.dir) - NoAVL.getAltura(no.esq);

            // Se balanceada
            if (Math.abs(fator) <= 1) {
                no.setAltura();

                // Se desbalanceada para a direita
            } else if (fator == 2) {
                int fatorFilhoDir = NoAVL.getAltura(no.dir.dir) - NoAVL.getAltura(no.dir.esq);

                // Se o filho a direita tambem estiver desbalanceado
                if (fatorFilhoDir == -1) {
                    no.dir = rotacionarDir(no.dir);
                }

                no = rotacionarEsq(no);

                // Se desbalanceada para a esquerda
            } else if (fator == -2) {
                int fatorFilhoEsq = NoAVL.getAltura(no.esq.dir) - NoAVL.getAltura(no.esq.esq);

                // Se o filho a esquerda tambem estiver desbalanceado
                if (fatorFilhoEsq == 1) {
                    no.esq = rotacionarEsq(no.esq);
                }

                no = rotacionarDir(no);
            } else {
                throw new Exception(
                        "Erro no (" + no.elemento + ") com fator de balanceamento (" + fator + ") invalido!");
            }
        }
        return no;
    }

    private NoAVL rotacionarEsq(NoAVL no) {
        System.out.println("Rotacionar ESQ(" + no.elemento + ")");
        NoAVL noDir = no.dir;
        NoAVL noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        no.setAltura(); // Atualizar o nivel do no
        noDir.setAltura(); // Atualizar o nivel do noDir

        return noDir;

        // O no(raiz) vai para a esquerda, e o noDir vira raiz
        // Desenhe 1,2,5 (raiz=2)
    }

    private NoAVL rotacionarDir(NoAVL no) {
        System.out.println("Rotacionar DIR(" + no.elemento + ")");
        NoAVL noEsq = no.esq;
        NoAVL noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        no.setAltura(); // Atualizar o nivel do no
        noEsq.setAltura(); // Atualizar o nivel do noEsq

        return noEsq;

        // O no(raiz) vai para a direita, e o noEsq vira raiz
    }

    private int getAltura(NoAVL i) {
        if (i == null) {
            return -1;
        }
        int alturaEsq = 1 + getAltura(i.esq);
        int alturaDir = 1 + getAltura(i.dir);

        return (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
    }
}

public class C_ArvoreBinariaAVL {
    public static void main(String args[]) throws Exception {
        ArvoreAVL arv = new ArvoreAVL();

        arv.inserirAVL(5);
        arv.caminharCentral();

        arv.inserirAVL(2);
        arv.caminharCentral();

        arv.inserirAVL(3);
        arv.caminharCentral();

        arv.inserirAVL(1);
        arv.caminharCentral();

        System.out.println(arv.pesquisar(3));
        System.out.println(arv.pesquisar(4));

        arv.inserirAVL(0);
        arv.caminharCentral();

        arv.removerAVL(0);
        arv.caminharCentral();
    }
}
// cls && javac C_ArvoreBinariaAVL.java && java C_ArvoreBinariaAVL

// Diferença do código da BST e da AVL é no
// atributo da classe altura e seus metodos
// e nos retornos com o método balancear