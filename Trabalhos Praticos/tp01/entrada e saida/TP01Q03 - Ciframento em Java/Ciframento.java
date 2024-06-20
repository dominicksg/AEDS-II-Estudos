import java.util.Scanner;

class Ciframento {
    public static String Codificar(String frase) {
        char[] stringcifrada = new char[frase.length()];

        for (int i = 0; i < frase.length(); i++) {
            if ((int) frase.charAt(i) >= 1 && (int) frase.charAt(i) <= 127) {
                stringcifrada[i] = (char) (frase.charAt(i) + 3);
            } else {
                stringcifrada[i] = (char) (frase.charAt(i));

            }
        }

        String StringDONE = new String(stringcifrada);
        return StringDONE;
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

            str = Codificar(str);

            System.out.println(str);

            str = Sc.nextLine();
            // str = MyIO.readLine();
        }

        // System.out.println(str);
        // MyIO.println(str);
        Sc.close();
    }

}