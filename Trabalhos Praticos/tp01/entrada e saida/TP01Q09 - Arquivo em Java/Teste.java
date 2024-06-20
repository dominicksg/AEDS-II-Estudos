import java.io.RandomAccessFile;

class Teste {
    public static void main(String[] args) {
        int num;

        try {
            RandomAccessFile arquivo = new RandomAccessFile("numeros.txt", "rw"); // Estudar
            num = MyIO.readInt();
            float valor;
            for (int i = 0; i < num; i++) {
                valor = MyIO.readFloat();
                arquivo.writeFloat(valor);
            }
            // int posicao = (int) arquivo.length() - 4;
            int posicao = (int) arquivo.length();
            arquivo.seek(posicao);
            // int posicao = (int) arquivo.length() - 1;

            // while (posicao > 0) {
            while (posicao >= 4) {

                posicao -= 4;
                arquivo.seek(posicao);

                valor = arquivo.readFloat();
                if (valor == (int) valor) {
                    MyIO.println((int) valor);
                } else {
                    MyIO.println(valor);
                }
                // System.out.printf("%g\n", valor);
                // posicao -= 4;
            }

            arquivo.close();
        } catch (

        Exception E) {
            E.printStackTrace();
        }
    }
}