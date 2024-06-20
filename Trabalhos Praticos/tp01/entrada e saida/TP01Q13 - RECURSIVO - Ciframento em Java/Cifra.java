import java.util.Scanner;

class Cifra {
    public static String CodificarRec(String frase, int i) {
        if (i >= frase.length()) {
            return "";
        } else {
            char letra = frase.charAt(i);
            char letracifrada;

            if ((int) frase.charAt(i) >= 1 && (int) frase.charAt(i) <= 127) {
                letracifrada = (char) (letra + 3);
            } else {
                letracifrada = letra;
            }
            return letracifrada + CodificarRec(frase, i + 1);
        }
    }

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        String str;
        Scanner Sc = new Scanner(System.in);
        // Scanner Sc = new Scanner(System.in, "ISO-8859-1");
        // StringBuilder resultado = new StringBuilder();

        str = Sc.nextLine();
        // str = MyIO.readLine();
        // while (str.equals("FIM") != true) {
        while (isFim(str) == false) {

            str = CodificarRec(str, 0);

            System.out.println(str);

            str = Sc.nextLine();
            // str = MyIO.readLine();
        }

        // System.out.println(str);
        // MyIO.println(str);
        Sc.close();
    }

}