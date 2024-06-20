
import java.util.Scanner;

public class PalindromoRec {

    public static boolean isPalindromo(String str) {
        // Caso base: se a string tiver 0 ou 1 caracteres, ela é um palíndromo
        if (str.length() <= 1) {
            return true;
        }
        
        // Verifica se o primeiro e o último caractere são iguais
        if (str.charAt(0) != str.charAt(str.length() - 1)) {
            return false;
        }
        
        // Chama recursivamente a função com a substring entre o primeiro e o último caractere
        return isPalindromo(str.substring(1, str.length() - 1));
    }

    public static void main(String[] args) {
        String str;
        Scanner Sc = new Scanner(System.in);

        str = Sc.nextLine();

        // System.out.println(str);

        while (str.equals("FIM") != true) {
            if (isPalindromo(str)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            str = Sc.nextLine();
        }

        Sc.close();
    }
}