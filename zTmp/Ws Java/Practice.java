import java.util.Scanner;

class Carro {
    int entrada;
    int saida;

    public Carro(int entrada, int saida) {
        this.entrada = entrada;
        this.saida = saida;
    }
}

class Celula {
    Carro car;
    Celula prox;

    Celula(Carro car) {
        this.car = car;
    }
}

class Pilha {
    Celula topo;
    int tamAtual;
    int tamMax;

    Pilha(int tamMax) {
        this.topo = null;
        this.tamAtual = 0;
        this.tamMax = tamMax;
    }

    boolean inserir(Carro car) {
        Celula novoCarro = new Celula(car);
        boolean flag = false;

        if (tamAtual < tamMax) {
            flag = true;
            for (Celula i = topo; i != null && flag; i = i.prox) {
                if (i.car.saida < novoCarro.car.saida) {
                    flag = false;
                }
            }

            if (flag) {
                novoCarro.prox = topo;
                topo = novoCarro;
                tamAtual++;
            }
        } else {
            if (novoCarro.car.entrada > topo.car.saida) {
                flag = true;

                Celula tmp = topo;
                topo = topo.prox;
                tmp.prox = tmp = null;

                for (Celula i = topo; i != null && flag; i = i.prox) {
                    if (i.car.saida < novoCarro.car.saida) {
                        flag = false;
                    }
                }

                if (flag) {

                    novoCarro.prox = topo;
                    topo = novoCarro;
                    tamAtual++;
                }
            }
        }

        return flag;
    }
}// os carros saiam na ordem, necessariamente, e nao ultrapasse o tamanho

public class Practice {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int nMotoristas = sc.nextInt();
        int kMaxCars = sc.nextInt();

        while (nMotoristas != 0 && kMaxCars != 0) {

            Pilha corredor = new Pilha(kMaxCars);

            boolean flag = true;

            for (int i = 0; i < nMotoristas; i++) {
                int horaEntrada = sc.nextInt();
                int horaSaida = sc.nextInt();

                Carro carro = new Carro(horaEntrada, horaSaida);

                flag = corredor.inserir(carro);
            }

            if (flag) {
                System.out.println("Sim");
            } else {
                System.out.println("Nao");
            }

            nMotoristas = sc.nextInt();
            kMaxCars = sc.nextInt();
        }
        sc.close();
    }
}
// cls && javac Practice.java && java Practice