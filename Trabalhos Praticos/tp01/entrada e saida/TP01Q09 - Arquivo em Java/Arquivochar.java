import java.io.RandomAccessFile;

class Arquivochar {
    public static void main(String[] args) {
        int num;

        try {
            RandomAccessFile arquivo = new RandomAccessFile("numeros.txt", "rw"); // Estudar
            num = MyIO.readInt();
            float valor;
            for (int i = 0; i < num; i++) {
                valor = MyIO.readInt();
                arquivo.writeFloat(valor);
                arquivo.writeChar('\n'); // ISSO FUNCIONA
            }

            int posicao = (int) arquivo.length() - 2;
            while (posicao > 0) {
                arquivo.seek(posicao);
                arquivo.seek(--posicao);
                if (arquivo.readChar() == '\n') {
                    valor = arquivo.readFloat();
                    MyIO.println(valor);
                    posicao--;
                }
                // System.out.printf("%g\n", valor);
                posicao -= 4;

            }
            arquivo.seek(0);
            valor = arquivo.readFloat();
            // System.out.printf("%g\n", valor);
            MyIO.println(valor);

            arquivo.close();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}