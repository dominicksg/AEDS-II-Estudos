import java.util.Random;
import java.util.Scanner;

class Alteracao {

    public static String Randomchar(String frase, Random gerador) {
        // String frasefinal = new String();
        // stringbuilder
        char[] frasefinalchar = new char[frase.length()];

        char letraold = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
        char letranew = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
        // System.out.println((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));

        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == letraold) {
                frasefinalchar[i] = letranew;
            } else {
                frasefinalchar[i] = frase.charAt(i);
            }
        }
        String frasefinalstr = new String(frasefinalchar);
        return frasefinalstr;

    }

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        Random gerador = new Random();
        
        gerador.setSeed(4);
        String str = Sc.nextLine();

        // while (str.equals("FIM") != true) {
        while (isFim(str) == false) {

            str = Randomchar(str, gerador);

            System.out.println(str);
            str = Sc.nextLine();
        }

    }
}