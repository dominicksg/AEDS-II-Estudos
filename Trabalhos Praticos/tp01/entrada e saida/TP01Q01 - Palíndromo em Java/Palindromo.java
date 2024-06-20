import java.util.Scanner;

class Palindromo {

    public static Boolean IsPalindromo(String str) {
        int tam = str.length();

        for (int i = 0; i < tam; i++) {
            if (str.charAt(i) != str.charAt(tam - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        String str;
        Scanner Sc = new Scanner(System.in);
        // scanf
        str = Sc.nextLine();
        // printf
        // System.out.println(str);

        // .compareTo
        // while (str.equals("FIM") != true) {
        while (isFim(str) == false) {
            if (IsPalindromo(str)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            str = Sc.nextLine();
        }

        Sc.close();
    }
}
