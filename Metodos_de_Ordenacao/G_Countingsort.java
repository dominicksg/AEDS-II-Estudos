import java.util.Scanner;

class G_Countingsort {
    static void countingsort(int[] array) {
        int comp = 0, mov = 0;

        int[] ordenado = new int[array.length];

        // Array para contar o numero de ocorrencias de cada elemento
        int[] count = new int[getMaior(array) + 1];

        // Inicializar cada posicao do array de contagem
        for (int i = 0; i < count.length; count[i] = 0, i++)
            ;

        // Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < array.length; count[array[i]]++, i++)
            ;

        // Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for (int i = 1; i < count.length; count[i] += count[i - 1], i++)
            ;

        // Ordenando
        // for (int i = array.length - 1; i >= 0; ordenado[count[array[i]] - 1] =
        // array[i], count[array[i]]--, i--);
        for (int i = array.length - 1; i >= 0; i--) {
            ordenado[count[array[i]] - 1] = array[i];
            mov++; // Movimentação para colocar o elemento ordenado
            comp += array.length - i; // Comparação no loop anterior
            count[array[i]]--;
        }

        // Copiando para o array original
        for (int i = 0; i < array.length; array[i] = ordenado[i], i++)
            ;

        System.out.println("Foram feitas " + comp + " comparacoes e " + mov + " movimentacoes.");
    }

    // Retorna o maior elemento do array
    static int getMaior(int[] array) {
        int maior = array[0];

        for (int i = 0; i < array.length; i++) {
            if (maior < array[i]) {
                maior = array[i];
            }
        }
        return maior;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        long startTime = System.currentTimeMillis();
        countingsort(array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}
// Foram feitas 1275 comparacoes e 50 movimentacoes.
// Tempo de execucao: 4 ms