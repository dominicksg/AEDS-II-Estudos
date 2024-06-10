class NoAVL {
    public int elemento;
    public NoAVL esq, dir;
    public int altura;

    public NoAVL(int x) {
        this.elemento = x;
        this.altura = 1; // Pq 1?
        this.esq = this.dir = null;
    }

    public NoAVL(int x, NoAVL esq, NoAVL dir, int altura) {
        this.elemento = x;
        this.esq = esq;
        this.dir = dir;
        this.altura = altura;
    }

    public void setAltura() {
        this.altura = 1 + Math.max(getAltura(esq), getAltura(dir));
    }

    public static int getAltura(NoAVL no) {
        return (no == null) ? 0 : no.altura;
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

    public boolean pesquisar(int x) {
        boolean flag = pesquisar(x, raiz);
        return flag;
    }

    public void caminharCentral() {
        System.out.println("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    // ====================================//
    // --------- Metodos Privados ---------//
    // ====================================//

    private boolean pesquisar(int x, NoAVL i) {
        boolean flag = false;
        if (i == null) {
            return flag;
        } else if (x == i.elemento) {
            flag = true;
        } else if (x < i.elemento) {
            flag = pesquisar(x, i.esq);
        } else {
            flag = pesquisar(x, i.dir);
        }
        return flag;
    }

    private void caminharCentral(NoAVL i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    private NoAVL inserir(int x, NoAVL i) throws Exception {
        if (i == null) {
            i = new NoAVL(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro");
        }
        return balancear(i);
    }

    private NoAVL remover(int x, NoAVL i) throws Exception {
        if (i == null) {
            throw new Exception("Erro");
        } else if (x < i.elemento) {
            i.esq = remover(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = remover(x, i.dir);
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
            if (Math.abs(fator) <= 1) { // Se balanceada
                no.setAltura();
            } else if (fator == 2) { // se desbalanceada para a direita
                int fatorFilhoDir = NoAVL.getAltura(no.dir.dir) - NoAVL.getAltura(no.dir.esq);
                // Se o filho da direita tambem estiver desbalanceado
                if (fatorFilhoDir == -1) {
                    no.dir = rotacionarDir(no.dir);
                }
                no = rotacionarEsq(no);
            } else if (fator == -2) {
                int fatorFilhoEsq = NoAVL.getAltura(no.esq.dir) - NoAVL.getAltura(no.esq.esq);
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

    private NoAVL rotacionarDir(NoAVL no) {
        System.out.println("Rotacionar DIR(" + no.elemento + ")");
        NoAVL noEsq = no.esq;
        NoAVL noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;
        no.setAltura(); // Atualizar o nivel do no
        noEsq.setAltura(); // Atualizar o nivel do noEsq
        return noEsq;
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
    }

}

public class Practice {

}
// cls && javac Practice.java && java Practice