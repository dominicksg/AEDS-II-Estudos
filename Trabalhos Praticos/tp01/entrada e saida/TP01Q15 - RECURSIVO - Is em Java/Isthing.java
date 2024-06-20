class Isthing {

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    // ------------------------------------------//

    public static boolean IsVogal(String frase, int i) {
        if (i >= frase.length()) {
            return true;
        }
        char c = Character.toLowerCase(frase.charAt(i));
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == ' ') {
            return IsVogal(frase, i + 1);
        } else {
            return false;
        }
    }

    // ------------------------------------------//

    public static boolean IsConsoante(String frase, int i) {

        if (i >= frase.length()) {
            return true;
        }
        char c = Character.toLowerCase(frase.charAt(i));
        if (c >= 'a' && c <= 'z') {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return false;
            }
        } else {
            return false;
        }
        return IsConsoante(frase, i + 1);
    }

    // ------------------------------------------//

    public static boolean IsInteiro(String frase, int i) {
        if (i >= frase.length()) {
            return true;
        }
        char c = frase.charAt(i);
        if (c >= '0' && c <= '9') {
            return IsInteiro(frase, i + 1);
        } else {
            return false;
        }
    }

    // ------------------------------------------//

    public static boolean IsReal(String frase, int index, int tamchecker, int virgula) {
        if (index == frase.length()) {
            return tamchecker == 0 && virgula < 2;
        }

        char currentChar = frase.charAt(index);
        if (currentChar >= '0' && currentChar <= '9') {
            tamchecker--;
        } else if (currentChar == '.' || currentChar == ',') {
            tamchecker--;
            virgula++;
            if (virgula >= 2) {
                return false;
            }
        }

        return IsReal(frase, index + 1, tamchecker, virgula);
    }

    public static boolean IsReal(String frase) {
        int tam = frase.length();
        int tamchecker = tam;
        int virgula = 0;
        return IsReal(frase, 0, tamchecker, virgula);
    }

    // ------------------------------------------//

    public static void main(String[] args) {
        String str;

        str = MyIO.readLine();
        while (isFim(str) == false) {
            if (IsVogal(str, 0) == true) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }
            // ------------------------------------------//
            if (IsConsoante(str, 0) == true) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }
            // ------------------------------------------//

            if (IsInteiro(str, 0) == true) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }
            // ------------------------------------------//
            if (IsReal(str) == true) {
                System.out.println("SIM ");
            } else {
                System.out.println("NAO ");
            }
            // ------------------------------------------//

            str = MyIO.readLine();
        }

    }
}