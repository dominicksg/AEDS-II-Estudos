class Celula {
    public int elemento;
    public Celula ant;
    public Celula prox;

    public Celula() {
        this.elemento = 0;
        this.ant = null;
        this.prox = null;
    }

    public Celula(int x) {
        this.elemento = x;
        this.ant = null;
        this.prox = null;
    }
}

class ListaDupla {
    private Celula primeiro, ultimo;
    

    ListaDupla() {
        this.primeiro = new Celula();
        this.ultimo = primeiro;
    }

    public void inserirFim(int x) {
        Celula tmp = new Celula(x);
        ultimo.prox = tmp;

        tmp.ant = ultimo; // ultimo.prox.ant = ultimo;
        ultimo = tmp; // ultimo.prox
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            primeiro.prox.ant = tmp;
        }
        tmp = null;
    }

    public int removerFim() {
        if (primeiro == ultimo) {
            System.out.println("ERRO");
        }
        int num = ultimo.elemento;
        ultimo = ultimo.ant;

        ultimo.prox.ant = null;
        ultimo.prox = null;
        return num;
    }

    public int removerInicio() {
        if (primeiro == ultimo) {
            System.out.println("ERRO");
        }
        Celula tmp = primeiro.prox;
        int num = tmp.elemento;

        primeiro.prox = primeiro.prox.prox;
        tmp.prox.ant = primeiro;

        tmp.ant = tmp.prox = null;
        tmp = null;
        return num;
    }

    public void inserir(int x, int pos) {
        int tam = tamanho();
        if (pos < 0 || pos > tam) {
            System.out.println("ERRO");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tam) {
            inserirFim(x);
        }
        Celula i = primeiro;
        for (int j = 0; j < pos; j++, i = i.prox)
            ;
        Celula tmp = new Celula(x);
        tmp.ant = i;
        tmp.prox = i.prox;
        tmp.ant.prox = tmp;
        tmp.prox.ant = tmp;
        tmp = i = null;
    }

    public int remover(int pos) {
        int tam = tamanho();
        int num = -1;
        if (pos < 0 || pos >= tam || primeiro == ultimo) {
            System.out.println("ERRO");
        } else if (pos == 0) {
            num = removerInicio();
        } else if (pos == tam - 1) {
            num = removerFim();
        }
        Celula i = primeiro;
        for (int j = 0; j < pos; j++, i = i.prox)
            ;
        Celula tmp = i.prox;
        num = tmp.elemento;
        
        tmp.ant.prox = tmp.prox;
        tmp.prox.ant = tmp.ant;

        tmp.prox = tmp.ant = null;
        tmp = null;

        return num;
    }

    public void mostrar() {
        System.out.print("[ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");

        }
        System.out.println("]");

    }

    public int tamanho() {
        int tam = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox) {
            tam++;
        }
        return tam;
    }

}

public class B_ListaduplaFlex {
    public static void main(String[] args) {
        ListaDupla lista = new ListaDupla();
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