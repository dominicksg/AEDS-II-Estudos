import java.io.RandomAccessFile;

class Arquivo {
    public static void main(String[] args) {
        int num;

        try {
            RandomAccessFile arquivo = new RandomAccessFile("numeros.txt", "rw"); // Estudar
            num = MyIO.readInt();

            float valor;

            for (int i = 0; i < num; i++) {
                valor = MyIO.readFloat();
                arquivo.writeFloat(valor);
                // arquivo.writeChar('\n'); // ISSO FUNCIONA
            }

            while (num > 0) {
                arquivo.seek(4*(num-1));

                valor = arquivo.readFloat();
                if (valor == (int) valor) {
                    MyIO.println((int) valor);
                } else {
                    MyIO.println(valor);
                }
                num--;
            }
            arquivo.close();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}