import java.util.Scanner;

public class Algbool {

    public static int NOT(int variable) {
        return (variable == 1) ? 0 : 1;
    }

    public static int AND(int variable, int variable2) {
        return (variable != 0 && variable2 != 0) ? 1 : 0;
    }

    public static int OR(int variable, int variable2) {
        return (variable != 0 || variable2 != 0) ? 1 : 0;
    }

    public static int AlgebraCheck(String frase, int variable, int variable2, int res) {

        for (int i = frase.length() - 1; i >= 0; i--) {
            if (frase.charAt(i) == 't') {
                if (frase.charAt(i + 2) == 'A') {
                    variable = NOT(variable);
                }
                if (frase.charAt(i + 2) == 'B') {
                    variable2 = NOT(variable2);
                } else {
                    res = NOT(res);
                }
            }
            if (frase.charAt(i) == 'd') {
                res = AND(variable, variable2);
            }
        }
        return res;
    }

    public static int AlgebraCheck(String frase, int variable, int variable2, int variable3, int res) {

        for (int i = frase.length() - 1; i >= 0; i--) {
            if (frase.charAt(i) == '(') {
                // PAREI AQUI


                if (frase.charAt(i + 2) == 'A') {
                    variable = NOT(variable);
                }
                if (frase.charAt(i + 2) == 'B') {
                    variable2 = NOT(variable2);
                }
                if (frase.charAt(i + 2) == 'C') {
                    variable3 = NOT(variable3);
                } else {
                    res = NOT(res);
                }
            }
            if (frase.charAt(i) == 'd') {
                res = AND(variable, variable2);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        int a, b, c;
        int index;
        int resp = 0;
        String linha;

        linha = Sc.nextLine();
        index = Character.getNumericValue(linha.charAt(0));

        while (index != 0) {
            if (index == 2) {
                a = Character.getNumericValue(linha.charAt(2));
                b = Character.getNumericValue(linha.charAt(4));
                // System.out.println(b); // TESTES

                resp = AlgebraCheck(linha, a, b, resp);
            }

            if (index == 3) {
                a = Character.getNumericValue(linha.charAt(2));
                b = Character.getNumericValue(linha.charAt(4));
                c = Character.getNumericValue(linha.charAt(6));
                // System.out.println(c); // TESTES

            }

            System.out.println(resp);
            linha = Sc.nextLine();
            index = Character.getNumericValue(linha.charAt(0));
        }

        Sc.close();
    }

}
