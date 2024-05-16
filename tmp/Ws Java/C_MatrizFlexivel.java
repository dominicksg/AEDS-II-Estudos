class Celula {
    public int elemento;
    public Celula dir, esq, sup, inf;

    Celula() {
        this.elemento = 0;
        this.sup = this.inf = this.dir = this.esq = null;
    }

}

class Matrizflex {
    private Celula inicio;
    private int numLinhas, numColunas; // y x

    public Matrizflex() {
        inicio = createLinha();
        Celula linhaAbaixo = createLinha();

        conectarLinhaAbaixo(inicio, linhaAbaixo);

        numLinhas = 2;
        numColunas = 2;
    }

    public Matrizflex(int y, int x) {

    } // FAZER ISSO AQUI

    public void conectarLinhaAbaixo(Celula inicio, Celula linhaAbaixo) {
        for (Celula i = inicio, tmp = linhaAbaixo; i != null; i = i.dir) {
            i.inf = tmp;
            tmp.sup = i;

            tmp = tmp.dir;
        }
    }

    // public void conectarLinhaLateral(Celula inicio, Celula linhaAbaixo) {
    //     for (Celula i = inicio, tmp = linhaAbaixo; i != null; i = i.dir) {

    //     }
    // }

    public Celula createLinha() {
        Celula i = new Celula();
        Celula j = new Celula();

        i.dir = j;
        j.esq = i;

        return i;
    }

    public Celula createLinha(int x) { // x = tam lacunas
        Celula i = new Celula();
        Celula tmp = i;

        for (int j = 0; j < x - 1; j++) {
            Celula nova = new Celula();
            tmp.dir = nova;
            nova.esq = tmp;
            tmp = nova;
        }
        return i;
    }

    public Celula createColuna(int y) { // y = tam linhas
        Celula i = new Celula();
        Celula tmp = i;

        for (int j = 0; j < y - 1; j++) {
            Celula nova = new Celula();
            tmp.inf = nova;
            nova.sup = tmp;

            tmp = nova;
        }
        return i;
    }

    public void addLinha() {
        Celula i;
        for (i = inicio; i.inf != null; i = i.inf)
            ;

        for (Celula j = createLinha(numColunas); j != null; j = j.dir) {
            i.inf = j;
            j.sup = i;

            i = i.dir;
        }
        numLinhas++;
    }

    public void addColuna() {
        Celula i;
        for (i = inicio; i.dir != null; i = i.dir)
            ;

        // Celula j = createLinha(2);
        // conectaLinhaAbaixo(i, j);
        for (Celula j = createColuna(numLinhas); j != null; j = j.inf) {
            i.dir = j;
            j.esq = i;

            i = i.inf;
        }
        numColunas++;
    }

    public void removeLinha() {
        if (numLinhas == 0) {
            try {
                throw new Exception("Matriz vazia");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (numLinhas == 1) {
            inicio = null;
            numLinhas--;
        } else {
            Celula i;
            for (i = inicio; i.inf != null; i = i.inf)
                ;
            while (i != null) {
                i.sup.inf = null;
                i.sup = null;

                i = i.dir;
            }
            numLinhas--;
        }
    }

    public void removeColuna() {
        if (numColunas == 0) {
            try {
                throw new Exception("Matriz vazia");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (numColunas == 1) {
            inicio = null;
            numColunas--;
        } else {
            Celula i;
            for (i = inicio; i.dir != null; i = i.dir)
                ;
            while (i != null) {
                i.esq.dir = i.esq = null;

                i = i.inf;
            }
            numColunas--;
        }
    }

    void addElemento(int x, int linhaPos, int colunaPos) {
        if (linhaPos >= numLinhas || colunaPos >= numColunas || linhaPos < 0 || colunaPos < 0) {
            try {
                throw new Exception("Posicao invalida");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Celula tmp = inicio;
            for (int i = 0; i < colunaPos; i++) {
                tmp = tmp.dir;
            }
            for (int i = 0; i < linhaPos; i++) {
                tmp = tmp.inf;
            }
            tmp.elemento = x;
        }
    }

    // SE A CELULA NAO EXISTIR, ELE CRIA UM COMO NAS LISTAS;    
    void addElementoBloco(int x, int linhaPos, int colunaPos) { 

    }

    void getDiagonal() {
        if (numColunas != numLinhas) {
            try {
                throw new Exception("Esta matriz nao possui Diagonal Principal");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Celula i = inicio;

            while (i != null) {
                System.out.print(i.elemento + " ");
                i = i.dir;
                if (i != null) {
                    i = i.inf;
                }
            }
            System.out.println("\n");
        }

    }

    public void mostrar() {
        for (Celula i = inicio; i != null; i = i.inf) {

            System.out.print("[ ");
            for (Celula j = i; j != null; j = j.dir) {
                System.out.print(j.elemento + " ");
            }
            System.out.println("]");
        }
        System.out.println("----------------------------------------------------------");
    }
}

public class C_MatrizFlexivel {
    public static void main(String[] args) {
        Matrizflex matriz = new Matrizflex();
        matriz.mostrar();

        matriz.addLinha();
        matriz.mostrar();

        matriz.addColuna();
        matriz.mostrar();

        matriz.removeColuna();
        matriz.mostrar();

    }
}

// Matrizflex(int x, int y) {
// this.numLinhas = x;
// this.numColunas = y;
// }