import java.util.Scanner;

public class TP01Q01 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String palavra = scanner.nextLine();
      if (palavra.equals("FIM")) {
        break;
      }
      boolean ehPalindromo = verificaPalindromo(palavra);
      if (ehPalindromo) {
        System.out.println("SIM");
      } else {
        System.out.println("NAO");
      }
    }

    scanner.close();
  }

  public static boolean verificaPalindromo(String palavra) {
    int inicio = 0;
    int fim = palavra.length() - 1;

    while (inicio < fim) {
      while (inicio < fim && !Character.isLetterOrDigit(palavra.charAt(inicio))) {
        inicio++;
      }
      while (inicio < fim && !Character.isLetterOrDigit(palavra.charAt(fim))) {
        fim--;
      }
      if ((palavra.charAt(inicio)) != (palavra.charAt(fim))) {
        return false;
      }
      inicio++;
      fim--;
    }

    return true;
  }
}
