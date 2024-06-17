class Celula {
    public int elemento; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.

    Celula(int elemento) {
        this.elemento = elemento;
        this.prox = null;
    }

    Celula(int elemento, Celula prox) {
        this.elemento = elemento;
        this.prox = prox;
    }
}

class Lista {
    private Celula primeiro; // Primeira celula: SEM elemento valido.
    private Celula ultimo; // Ultima celula: COM elemento valido.

    public Lista() {
        primeiro = new Celula(-1);
        ultimo = primeiro;
    }

    public void mostrar() {
        System.out.print("[ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("] ");
    }

    public boolean pesquisar(int x) {
        boolean retorno = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                retorno = true;
                i = ultimo;
            }
        }
        return retorno;
    }

    public void inserirInicio(int elemento) {
        Celula tmp = new Celula(elemento);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserirFim(int elemento) {
        Celula tmp = new Celula(elemento);
        ultimo.prox = tmp;
        ultimo = ultimo.prox;
        tmp = null;
    }

    public void inserirMeio(int x, int posicao) throws Exception {
        Celula i;
        int cont;

        // Caminhar ate chegar na posicao anterior a insercao
        for (i = primeiro, cont = 0; (i.prox != ultimo && cont < posicao); i = i.prox, cont++)
            ;

        // Se indice for incorreto:
        if (posicao < 0 || posicao > cont + 1) {
            throw new Exception("Erro ao inserir (posicao " + posicao + "(cont = " + cont + ") invalida)!");

        } else if (posicao == cont + 1) {
            inserirFim(x);
        } else {
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public int removerInicio() throws Exception {
        int resp = -1;

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        } else {
            primeiro = primeiro.prox;
            resp = primeiro.elemento;
        }
        return resp;
    }

    public int removerFim() throws Exception {
        int resp = -1;
        Celula i = null;

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        } else {

            resp = ultimo.elemento;

            // Caminhar ate a penultima celula:
            for (i = primeiro; i.prox != ultimo; i = i.prox)
                ;

            ultimo = i;
            i = ultimo.prox = null;
        }
        return resp;
    }

    public int removerMeio(int posicao) throws Exception {
        Celula i;
        int resp = -1, cont;

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        } else {

            // Caminhar ate chegar na posicao anterior a insercao
            for (i = primeiro, cont = 0; (i.prox != ultimo && cont < posicao); i = i.prox, cont++)
                ;

            // Se indice for incorreto:
            if (posicao < 0 || posicao > cont + 1) {
                throw new Exception("Erro ao remover (posicao " + posicao + " invalida)!");

            } else if (posicao == cont + 1) {
                resp = removerFim();
            } else {
                Celula tmp = i.prox;
                resp = tmp.elemento;
                i.prox = tmp.prox;
                tmp.prox = null;
                i = tmp = null;
            }
        }
        return resp;
    }

}

class HashIndireto {
    Lista tabela[];
    int tamanho;
    final int NULO = -1;

    public HashIndireto() {
        this(7);
    }

    public HashIndireto(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Lista[tamanho];

        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new Lista();
        }
    }

    public int h(int elemento) {
        return elemento % tamanho;
    }

    public boolean pesquisar(int elemento) {
        int pos = h(elemento);
        return tabela[pos].pesquisar(elemento);
    }

    public void inserirInicio(int elemento) {
        int pos = h(elemento);
        tabela[pos].inserirInicio(elemento);
    }

    public int remover(int elemento) throws Exception {
        int pos = h(elemento);
        int resp = tabela[pos].remover(elemento);
        if (resp == NULO) {
            throw new Exception("Erro ao remover! Elemento não encontrado.");
        }
        return resp;
    }

}

public class C_HashIndiretoLista {
    public static void main(String args[]) {
        HashIndireto tabelaHash = new HashIndireto();

    }

}
// cls && javac D_ArvoreBinariaRedBlack.java && java D_ArvoreBinariaRedBlack