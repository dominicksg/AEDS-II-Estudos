//----------------------------------------------------------------------------------------//
// Avisos
//----------------------------------------------------------------------------------------//
// Olá, esse código provavelmente não está organizado como eu gostaria,
// por conta da urgência das entregas.
// E é normal ter muitas linhas de código comentadas, 
// já que é para uma atividade, eu aproveito isso para documentar todo o estudo também.
//----------------------------------------------------------------------------------------//

import java.util.Scanner;

class Celula {
    public int elemento;
    public Celula dir, esq, sup, inf;

    Celula() {
        this.elemento = 0;
        this.sup = this.inf = this.dir = this.esq = null;
    }
}

class Matriz {
    private Celula inicio;
    private int numLinhas, numColunas; // y x

    public Matriz(int linhas, int colunas) {
        inicio = criarMatriz(linhas, colunas);
        numLinhas = linhas;
        numColunas = colunas;
    }

    public Celula criarMatriz(int linhas, int colunas) {
        Celula linhaInicial = criarLinha(colunas);
        Celula linhaAtual = linhaInicial;

        for (int i = 1; i < linhas; i++) {
            Celula novaLinha = criarLinha(colunas);
            conectarLinhaAbaixo(linhaAtual, novaLinha);
            linhaAtual = novaLinha;
        }

        return linhaInicial;
    }

    public Celula criarLinha(int colunas) {
        Celula primeiraCelula = new Celula();
        Celula celulaAtual = primeiraCelula;

        for (int i = 1; i < colunas; i++) {
            Celula novaCelula = new Celula();
            conectarCelulas(celulaAtual, novaCelula, null, null);
            celulaAtual = novaCelula;
        }

        return primeiraCelula;
    }

    public void conectarLinhaAbaixo(Celula inicio, Celula linhaAbaixo) {
        for (Celula i = inicio, tmp = linhaAbaixo; i != null; i = i.dir) {
            i.inf = tmp;
            tmp.sup = i;

            tmp = tmp.dir;
        }
    }

    public void conectarCelulas(Celula celulaEsquerda, Celula celulaDireita, Celula celulaSuperior,
            Celula celulaInferior) {
        if (celulaEsquerda != null) {
            celulaEsquerda.dir = celulaDireita;
            celulaDireita.esq = celulaEsquerda;
        }
        if (celulaSuperior != null) {
            celulaSuperior.inf = celulaInferior;
            celulaInferior.sup = celulaSuperior;
        }
    }

    public void mostrarDiagonalPrincipal() {
        if (numLinhas != numColunas) {
            System.out.println("A matriz não possui diagonal principal.");
            return;
        }
        Celula celulaAtual = inicio;
        while (celulaAtual != null) {
            System.out.print(celulaAtual.elemento + " ");
            celulaAtual = celulaAtual.inf != null ? celulaAtual.inf.dir : null;
        }
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        Celula celulaAtual = inicio;
        for (Celula i = inicio; i != null; i = i.dir) {
            celulaAtual = i;
        }
        while (celulaAtual != null) {
            System.out.print(celulaAtual.elemento + " ");
            celulaAtual = celulaAtual.inf != null ? celulaAtual.inf.esq : null;
        }
        System.out.println();
    }

    public Matriz soma(Matriz outraMatriz) {
        // Verifica se as matrizes têm o mesmo tamanho
        if (outraMatriz.numLinhas != numLinhas || outraMatriz.numColunas != numColunas) {
            System.out.println("As matrizes devem ter o mesmo tamanho para realizar a soma.");
            return null;
        }

        // Cria uma nova matriz para armazenar a soma
        Matriz matrizSoma = new Matriz(numLinhas, numColunas);

        // Ponteiros para percorrer as células das matrizes
        Celula celulaAtual = inicio;
        Celula celulaOutra = outraMatriz.inicio;
        Celula celulaSoma = matrizSoma.inicio;

        // Percorre as células das matrizes
        while (celulaAtual != null) {
            Celula linhaAtual = celulaAtual;
            Celula linhaOutra = celulaOutra;
            Celula linhaSoma = celulaSoma;

            while (linhaAtual != null) {
                // Soma os elementos das células correspondentes
                linhaSoma.elemento = linhaAtual.elemento + linhaOutra.elemento;

                // Move para a próxima célula na mesma linha
                linhaAtual = linhaAtual.dir;
                linhaOutra = linhaOutra.dir;
                linhaSoma = linhaSoma.dir;
            }

            // Move para a próxima coluna
            celulaAtual = celulaAtual.inf;
            celulaOutra = celulaOutra.inf;
            celulaSoma = celulaSoma.inf;
        }

        return matrizSoma;
    }

    public Matriz multiplicacao(Matriz outraMatriz) {
        if (numColunas != outraMatriz.numLinhas) {
            System.out.println(
                    "O número de colunas da primeira matriz deve ser igual ao número de linhas da segunda matriz.");
            return null;
        }

        Matriz resultado = new Matriz(numLinhas, outraMatriz.numColunas);

        Celula linhaResultado = resultado.inicio;
        Celula linhaMatriz1 = inicio;

        for (int i = 0; i < numLinhas; i++) {
            Celula colunaResultado = linhaResultado;
            Celula colunaMatriz2 = outraMatriz.inicio;

            for (int j = 0; j < outraMatriz.numColunas; j++) {
                int valorCelula = 0;
                Celula celulaAux1 = linhaMatriz1;
                Celula celulaAux2 = colunaMatriz2;

                for (int k = 0; k < numColunas; k++) {
                    valorCelula += celulaAux1.elemento * celulaAux2.elemento;
                    celulaAux1 = celulaAux1.dir;
                    celulaAux2 = celulaAux2.inf;
                }

                colunaResultado.elemento = valorCelula;
                colunaResultado = colunaResultado.dir;
                colunaMatriz2 = colunaMatriz2.dir;
            }

            linhaResultado = linhaResultado.inf;
            linhaMatriz1 = linhaMatriz1.inf;
        }

        return resultado;
    }

    public void mostrar() {
        for (Celula celulaAtual = inicio; celulaAtual != null; celulaAtual = celulaAtual.inf) {
            for (Celula celulaLinha = celulaAtual; celulaLinha != null; celulaLinha = celulaLinha.dir) {
                System.out.print(celulaLinha.elemento + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int numCasosTeste = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        for (int casoTeste = 0; casoTeste < numCasosTeste; casoTeste++) {
            int linhas1 = scanner.nextInt();
            int colunas1 = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            Matriz matriz1 = new Matriz(linhas1, colunas1);

            for (Celula i = matriz1.inicio; i != null; i = i.inf) {
                for (Celula j = i; j != null; j = j.dir) {
                    j.elemento = scanner.nextInt();
                }
                scanner.nextLine(); // Consumir a quebra de linha
            }

            // matriz1.mostrar();

            int linhas2 = scanner.nextInt();
            int colunas2 = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            Matriz matriz2 = new Matriz(linhas2, colunas2);

            for (Celula i = matriz2.inicio; i != null; i = i.inf) {
                for (Celula j = i; j != null; j = j.dir) {
                    j.elemento = scanner.nextInt();
                }
                scanner.nextLine(); // Consumir a quebra de linha
            }

            // matriz2.mostrar();
            // --- FIM PREENCHIMENTO DE MATRIZES ---

            // Exibindo diagonais
            matriz1.mostrarDiagonalPrincipal();
            matriz1.mostrarDiagonalSecundaria();

            // Somando matrizes
            Matriz soma = matriz1.soma(matriz2);
            if (soma != null) {
                soma.mostrar();
            }

            // Multiplicando matrizes
            Matriz multiplicacao = matriz1.multiplicacao(matriz2);
            if (multiplicacao != null) {
                multiplicacao.mostrar();
            }
        }
        scanner.close();
    }
}
// cls && javac Matriz.java && java Matriz < pub.in > saida.txt
