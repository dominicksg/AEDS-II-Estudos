import java.util.Scanner;

class Pokemon {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        int n = 0;
        int faltam = 151;
        int igual = 0;

        n = Sc.nextInt();

        String[] pokemon = new String[n];
        Sc.nextLine(); // Consumindo a quebra de linha excedente

        for (int i = 0; i < n; i++) {
            pokemon[i] = Sc.nextLine();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pokemon[i].equals(pokemon[j]) == true) {
                    igual++;
                    j = n;
                }
            }
        }
        faltam = faltam - (n - igual);

        System.out.println("Falta(m) " + faltam + " pomekon(s).");
        Sc.close();
    }
}