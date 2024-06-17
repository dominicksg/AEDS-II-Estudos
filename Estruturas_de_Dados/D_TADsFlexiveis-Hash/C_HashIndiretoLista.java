class Celula {
    public int elemento;
    public Celula prox;

    public Celula() {
        this.elemento = 0;
        this.prox = null;
    }

    public Celula(int x) {
        this.elemento = x;
        this.prox = null;
    }

    Celula(int x, Celula prox) {
        this.elemento = x;
        this.prox = prox;
    }
}

class Lista {
    public Celula primeiro; // Primeira celula: SEM elemento valido.
    public Celula ultimo; // Ultima celula: COM elemento valido.

    public Lista() {
        primeiro = new Celula(-1);
        ultimo = primeiro;
    }

    public void inserirFim(int x) {
        Celula tmp = new Celula(x);
        ultimo.prox = tmp;
        ultimo = ultimo.prox;
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if (primeiro == ultimo)
            ultimo = tmp;
        tmp = null;
    }

    public int removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }
        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;
        int num = i.prox.elemento; // ou (ultimo.elemento)
        ultimo = i;
        ultimo.prox = i = null;
        return num;
    }

    public int removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }
        int elemento = primeiro.prox.elemento;
        Celula tmp = primeiro.prox;
        primeiro.prox = primeiro.prox.prox;
        tmp.prox = null;
        tmp = null;
        return elemento;
    }

    public void inserir(int x, int pos) throws Exception {// 1 elemento = primeiro.prox.elemento
        int tam = tamanho();
        if (pos < 0 || pos > tam) {
            throw new Exception("Erro ao inserir (posicao " + pos + ") invalida)!");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tam) {
            inserirFim(x);
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public int remover(int pos) throws Exception {
        int tam = tamanho();
        int num = -1;
        if (primeiro == ultimo || pos < 0 || pos >= tam) {
            throw new Exception("Erro ao remover (vazia ou posicao " + pos + " invalida)!");
        } else if (pos == 0) {
            num = removerInicio();
        } else if (pos == tam - 1) {
            num = removerFim();
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            Celula tmp = i.prox;
            num = tmp.elemento;
            i.prox = tmp.prox;
            tmp = tmp.prox = null;
        }
        return num;
    }

    public int tamanho() {
        int tam = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox) { // Nao conta o "ultimo", mas conta "primeiro"
            tam++;
        }
        return tam;
        // Retorna o numero de elementos DENTRO da lista
        // Então, se houver 3 numeros, ele conta 2 numeros,
        // + a primeira celula vazia = 3
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

    public int getPosicao(int x) {
        boolean flag = false;
        int count = -1;

        for (Celula i = primeiro; i != null; i = i.prox) {
            if (i.elemento == x) {
                flag = true;
                i = ultimo;
            }
            count++;
        }
        return (flag) ? count : -1;
    }

    public void mostrar() {
        Celula i;
        System.out.print("[ ");

        for (i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
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

    public void inserirFim(int elemento) {
        int pos = h(elemento);
        tabela[pos].inserirFim(elemento);
    }

    public int remover(int elemento) throws Exception {
        int num = -1;
        int pos = h(elemento);
        int posLista = tabela[pos].getPosicao(elemento);
        if (posLista != NULO) {
            num = tabela[pos].remover(posLista);
        } else {
            throw new Exception("Erro ao remover! Elemento não encontrado.");
        }
        return num;
    }

    public void mostrar() {
        for (int i = 0; i < tamanho; i++) { // ou tabela.length
            if (tabela[i].primeiro != null) {
                tabela[i].mostrar();
            }
        }
    }
}

public class C_HashIndiretoLista {
    public static void main(String args[]) {
        HashIndireto tabelaHash = new HashIndireto();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserirFim(0);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserirFim(3);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserirFim(4);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserirFim(2);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserirFim(5);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserirFim(1);
        tabelaHash.mostrar();

        System.out.println("========== Inserindo ==========");
        tabelaHash.inserirFim(7);
        tabelaHash.mostrar();

        System.out.println(tabelaHash.pesquisar(6));
        System.out.println(tabelaHash.pesquisar(0));
        System.out.println(tabelaHash.pesquisar(7));
    }

}
// cls && javac C_HashIndiretoLista.java && java C_HashIndiretoLista