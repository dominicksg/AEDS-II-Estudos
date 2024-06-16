import java.util.Random;

public class ArrayIO {
    public static void preencheAleatoriamente(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100); // Gera números aleatórios entre 0 e 99
        }
    }

    public static void preencheAleatoriamente(int[] array, int seed) {
        Random rand = new Random(seed); // Define a semente (pode ser qualquer número)
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100); // Gera números aleatórios entre 0 e 99
        }
    }

    public static void preencheAleatoriamenteParcial(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            if (i % 5 == 0) {
                array[i] = rand.nextInt(20); // valores menores para parte ordenada
            } else {
                array[i] = rand.nextInt(100); // valores maiores para parte desordenada
            }
        }
    }

    public static void preencheAleatoriamenteParcial(int[] array, int seed) {
        Random rand = new Random(seed);
        for (int i = 0; i < array.length; i++) {
            if (i % 5 == 0) {
                array[i] = rand.nextInt(20); // valores menores para parte ordenada
            } else {
                array[i] = rand.nextInt(100); // valores maiores para parte desordenada
            }
        }
    }

    public static void preencheOrdenadamente(int[] array) {
        for (int i = 1; i < array.length; i++) {
            array[i] = i;
        }
    }

    public static void imprimeArray(int[] array) {
        System.out.print("[ ");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    public static void checkOrdenado(int[] array) {
        boolean flag = true;

        // Verifica se o array é nulo ou tem menos de 2 elementos
        if (array == null || array.length < 2) {
            flag = true;
            //return true;
        }

        // Verifica se o array está ordenado
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                flag = false;
                i= array.length;
                //return false;
            }
        }

        // Faça uma (pergunta)? verdadeiro : falso
        System.out.println(flag ? "Esta ordenado:" : "Nao esta ordenado:");
        //return true;
    }
}
// Autoria de: Douglas Nícolas Silva Gomes