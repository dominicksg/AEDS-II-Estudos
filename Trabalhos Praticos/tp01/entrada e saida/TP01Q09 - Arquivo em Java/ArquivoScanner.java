
// Este arquivo printa a linha 1 sem a necessidade do código após o loop e usa Scanner
import java.io.RandomAccessFile;
import java.util.Scanner;

class ArquivoScanner {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        int num;

        try {
            RandomAccessFile arquivo = new RandomAccessFile("numeros.txt", "rw"); // Estudar
            // num = MyIO.readInt();
            num = Sc.nextInt();

            float valor;
            for (int i = 0; i < num; i++) {
                // valor = MyIO.readInt();
                valor = Sc.nextInt();
                arquivo.writeFloat(valor);
                // arquivo.writeChar('\n'); // ISSO FUNCIONA
            }
            // int posicao = (int) arquivo.length() - 4;
            int posicao = (int) arquivo.length();
            arquivo.seek(posicao);
            // int posicao = (int) arquivo.length() - 1;
            // arquivo.seek(arquivo.length()-2);

            // while (posicao > 0) {
            while (posicao >= 4) {

                posicao -= 4;
                arquivo.seek(posicao);

                /*
                 * arquivo.seek(--posicao);
                 * if (arquivo.readChar() == '\n') {
                 * 
                 * valor = arquivo.readFloat();
                 * MyIO.println(valor);
                 * posicao--;
                 * }
                 */

                valor = arquivo.readFloat();
                // System.out.printf("%g\n", valor);
                System.out.println(valor);
                // MyIO.println(valor);
                // posicao -= 4;
            }
            // arquivo.seek(0);
            // valor = arquivo.readFloat();
            // MyIO.println(valor);
            // System.out.printf("%g\n", valor);

            arquivo.close();
            Sc.close();
        } catch (

        Exception E) {
            E.printStackTrace();
        }
    }
}