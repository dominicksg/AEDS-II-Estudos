import java.util.Scanner;

class ParImpar {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        int num = 0;

        num = Sc.nextInt();
        while (num != 0) {

            if (num % 2 == 0) {
                System.out.println("P");
            } else {
                System.out.println("I");
            }

            // System.out.println(num);
            num = Sc.nextInt();

        }
    }
}
