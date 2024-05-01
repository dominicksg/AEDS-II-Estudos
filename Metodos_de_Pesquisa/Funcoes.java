import java.util.Random;

public class Funcoes {
    public static void preencheAleatoriamente(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100); // Gera números aleatórios entre 0 e 99
        }
    }

    public static void preencheOrdenadamente(int[] array){
        for(int i=1; i<array.length;i++){
            array[i] = i;
        }
    }

    public static void imprimeArray(int[] array) {
        System.out.print("[ ");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("]");
        System.out.println();
    }
}
