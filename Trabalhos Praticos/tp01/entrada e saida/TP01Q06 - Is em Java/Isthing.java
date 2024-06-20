class Isthing {

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    // ------------------------------------------//

    public static boolean IsVogal(String frase) {
        int tam = frase.length();
        int tamchecker = frase.length();
        for (int i = 0; i < tam; i++) {
            if ((frase.charAt(i) == 'a' || frase.charAt(i) == 'A') ||
                    (frase.charAt(i) == 'e' || frase.charAt(i) == 'E') ||
                    (frase.charAt(i) == 'i' || frase.charAt(i) == 'I') ||
                    (frase.charAt(i) == 'o' || frase.charAt(i) == 'O') ||
                    (frase.charAt(i) == 'u' || frase.charAt(i) == 'U') ||
                    frase.charAt(i) == ' ') {
                tamchecker -= 1;
            }
        }
        if (tamchecker == 0) {
            return true;
        }
        return false;
        // return tamchecker == 0 ? true : false;
    }

    // ------------------------------------------//

    public static boolean IsConsoante(String frase) {
        int tam = frase.length();
        int tamchecker = 0;
        for (int i = 0; i < tam; i++) {
            // Verifica se é uma letra do alfabeto
            if ((frase.charAt(i) >= 'a' && frase.charAt(i) <= 'z')
                    || (frase.charAt(i) >= 'A' && frase.charAt(i) <= 'Z')) {
                // Verifica se é uma vogal
                if (frase.charAt(i) == 'a' || frase.charAt(i) == 'A' ||
                        frase.charAt(i) == 'e' || frase.charAt(i) == 'E' ||
                        frase.charAt(i) == 'i' || frase.charAt(i) == 'I' ||
                        frase.charAt(i) == 'o' || frase.charAt(i) == 'O' ||
                        frase.charAt(i) == 'u' || frase.charAt(i) == 'U') {
                    tamchecker--; // Decrementa se for uma vogal
                }
            } else {
                tamchecker--; // Decrementa se não for uma letra do alfabeto
            }
        }
        if (tamchecker == 0) {
            return true;
        }
        return false;
    }

    // ------------------------------------------//

    public static boolean IsInteiro(String frase) {
        int tam = frase.length();
        int tamchecker = frase.length();
        for (int i = 0; i < tam; i++) {
            if (frase.charAt(i) >= '0' && frase.charAt(i) <= '9') {
                tamchecker -= 1;
            }
        }
        if (tamchecker == 0) {
            return true;
        }
        return false;
    }

    // ------------------------------------------//

    public static boolean IsReal(String frase) {
        int tam = frase.length();
        int tamchecker = frase.length();
        int virgula = 0;
        for (int i = 0; i < tam; i++) {
            if ((frase.charAt(i) >= '0') && (frase.charAt(i) <= '9')) {
                tamchecker -= 1;
            } else if ((frase.charAt(i) == '.') || (frase.charAt(i) == ',')) {
                tamchecker -= 1;
                virgula += 1;
            }
            if (virgula >= 2) {
                return false;
            }
        }
        if (tamchecker == 0) {
            return true;
        }
        return false;
    }

    // ------------------------------------------//

    public static void main(String[] args) {
        String str;

        str = MyIO.readLine();
        while (isFim(str) == false) {
            if (IsVogal(str) == true) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }
            // ------------------------------------------//
            if (IsConsoante(str) == true) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }
            // ------------------------------------------//
            
              if (IsInteiro(str) == true)
              {
              System.out.print("SIM ");
              }
              else
              {
              System.out.print("NAO ");
              }
              //------------------------------------------//
              if (IsReal(str) == true)
              {
              System.out.println("SIM ");
              }
              else
              {
              System.out.println("NAO ");
              }
              //------------------------------------------//             

            str = MyIO.readLine();
        }

    }
}