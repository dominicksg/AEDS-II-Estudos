import java.util.Scanner;

class No {
    public char letra;
    public No prox[];
    public final int tamanho = 255;
    public boolean folha;

    public No() {
        this(' ');
    }

    public No(char letra) {
        this.letra = letra;
        this.folha = false;
        this.prox = new No[tamanho];
        for (int i = 0; i < tamanho; i++) {
            prox[i] = null;
        }

    }
}

class ArvoreTrie {

}

public class Practice {
    public static void main(String args[]) {

    }
}