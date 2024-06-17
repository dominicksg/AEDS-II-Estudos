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

class ListaSimples {
    private Celula primeiro, ultimo;
    // Primeira celula: SEM elemento valido.
    // Ultima celula: COM elemento valido.

    public ListaSimples() {
        primeiro = new Celula();
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

    public int removerFim() {
        if (primeiro == ultimo) {
            System.out.println("ERRO");
        }
        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;
        int num = i.prox.elemento; // ou (ultimo.elemento)
        ultimo = i;
        ultimo.prox = i = null;
        return num;
    }

    public int removerInicio() {
        if (primeiro == ultimo) {
            System.out.println("ERRO");
        }
        int elemento = primeiro.prox.elemento;
        Celula tmp = primeiro.prox;
        primeiro.prox = primeiro.prox.prox;
        tmp.prox = null;
        tmp = null;
        return elemento;
    }

    public void inserir(int x, int pos) {// 1 elemento = primeiro.prox.elemento
        int tam = tamanho();
        if (pos < 0 || pos > tam) {
            System.out.println("ERRO");
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

    public int remover(int pos) {
        int tam = tamanho();
        int num = -1;
        if (primeiro == ultimo || pos < 0 || pos >= tam) {
            System.out.println("ERRO");
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

public class A_ListaflexSimples { // ou Lista Encadeada
    public static void main(String[] args) {
        ListaSimples lista = new ListaSimples();
        int tam = 0;

        lista.inserirInicio(1);
        lista.mostrar();
        tam = lista.tamanho();
        System.out.println(tam + "\n");

        lista.inserirInicio(0);
        lista.mostrar();
        tam = lista.tamanho();
        System.out.println(tam + "\n");

        lista.inserirFim(4);
        lista.mostrar();
        tam = lista.tamanho();
        System.out.println(tam + "\n");

        lista.inserir(2, 2);
        lista.mostrar();
        tam = lista.tamanho();
        System.out.println(tam + "\n");

        lista.inserir(3, 3);
        lista.mostrar();
        tam = lista.tamanho();
        System.out.println(tam + "\n");

        lista.remover(2);
        lista.mostrar();
        tam = lista.tamanho();
        System.out.println(tam + "\n");

        lista.removerFim();
        lista.mostrar();
        tam = lista.tamanho();
        System.out.println(tam + "\n");

        lista.removerInicio();
        lista.mostrar();
        tam = lista.tamanho();
        System.out.println(tam + "\n");

        System.out.println("Fim");
    }
}