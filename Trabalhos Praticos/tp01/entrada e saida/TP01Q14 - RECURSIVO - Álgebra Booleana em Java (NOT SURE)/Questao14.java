
import java.util.Scanner;

//2 0 0 and(not(A) , not(B))
public class Questao14 {

    public static char[] convert(String expressao, int tam_exp){
        char[] exp = new char[tam_exp];
        for(int i = 1; i < tam_exp; i++){
            exp[i-1] = expressao.charAt(i);
        }

        return exp;
    }

    //Função para substituir os caracteres 'A' por seu respectivo valor binario
    public static void ReplaceA(char[] expressao, int valor, int tam_exp){
        
        for(int i = 0; i < tam_exp; i++){
            if(expressao[i] == 'A'){
                expressao[i] = (char) (valor + '0');
            }
        }

    }

    //Função para substituir os caracteres 'B' por seu respectivo valor binario
    public static void ReplaceB(char[] expressao, int valor, int tam_exp){
        
         for(int i = 0; i < tam_exp; i++){
             if(expressao[i] == 'B'){
                 expressao[i] = (char) (valor + '0');
             }
         }
 
    }

    //Função para substituir os caracteres 'C' por seu respectivo valor binario
    public static void ReplaceC(char[] expressao, int valor, int tam_exp){
        
         for(int i = 0; i < tam_exp; i++){
             if(expressao[i] == 'C'){
                 expressao[i] = (char) (valor + '0');
             }
         }
 
    }

    /* Leio o vetor de tras para frente buscando o caracter 't', quando o encontro verifico o valor que esta na posição i+2
    * caso seja 0 percorro da posição i até a posição do próximo ')' encontrado trocando os valores por '-' e a posição i
    * por 1, caso o valor encontrado em i+2 seja 1 faço o mesmo processo porem trocando por 0
    */

    public static void not(char[] exp, int tam_exp){ //not(0)
        for(int i = tam_exp-1; i >= 0; i--){
            if(exp[i] == 't'){
                if(exp[i+2] == '0'){
                    exp[i-2] = '1';
                    for(int j = i + 1; j < tam_exp; j++){
                        if(exp[j] == ')'){
                            for(int k = j; k > i-2; k--){
                                exp[k] = ' ';
                            }
                            j = tam_exp;
                            i = -1;
                        }
                    }
                } else if(exp[i+2] == '1'){
                    exp[i-2] = '0';
                    for(int j = i + 1; j < tam_exp; j++){
                        if(exp[j] == ')'){
                            for(int k = j; k > i-2; k--){
                                exp[k] = ' ';
                            }
                            j = tam_exp;
                            i = -1;
                        }
                    }
                }
            } 
        } //
    }

    public static void and(char[] exp, int tam_exp){
        int parantese = 0;
        boolean valid = true;
        //Itero pelo vetor de tras para frente até encontrar o caracter ´d´ que representar a operação and
        for(int i = tam_exp-1; i >= 0; i--){
            if(exp[i] == 'd'){
                // quando encontro faço a varredura da posição do 'd' até o primeiro fechamento de parantese encontrado 
                for(int j = i+1; j < tam_exp; j++){
                    if(exp[j] == ')'){
                        parantese = j;
                        j = tam_exp;
                    }
                }

                //Depois de descobrir o indice inicial e final da minha operação perocorro o intervalo buscando pelo valor 0
                for(int k = i + 1; k < parantese; k++){ //and
                    if(exp[k] == '0'){// caso encontre, mudo minha valid para false e substituo o primeiro caracter do intervalo da minha operação por 0 e o resto por '-' para não interferir
                        valid = false;// em operações posteriores
                        exp[i-2] = '0';
                        for(int h = i-1; h <= parantese; h++){
                            exp[h] = ' ';
                        }
                    } 
                }

                if(valid == true){ // se não entrar no if anterior faço o mesmo processo porem atribuindo o valor 1
                    exp[i-2] = '1';
                    for(int h = i-1; h <= parantese; h++){
                        exp[h] = ' ';
                    }
                }

                i = -1;
            } 
        } //
    }

    public static void or(char[] exp, int tam_exp){
        int parantese = 0;
        boolean valid = false;
        //Itero pelo vetor de tras para frente até encontrar o caracter ´r´ que representar a operação or
        for(int i = tam_exp-1; i >= 0; i--){
            if(exp[i] == 'r'){
                // quando encontro faço a varredura da posição do 'r' até o primeiro fechamento de parantese encontrado 
                for(int j = i+1; j < tam_exp; j++){
                    if(exp[j] == ')'){
                        parantese = j;
                        j = tam_exp;
                    }
                }

                //Depois de descobrir o indice inicial e final da minha operação perocorro o intervalo buscando pelo valor 1
                for(int k = i + 1; k < parantese; k++){
                    if(exp[k] == '1'){
                        valid = true; // caso encontre, mudo minha valid para true e substituo o primeiro caracter do intervalo da minha operação por 1 e o resto por '-' para não interferir em
                        exp[i-1] = '1'; // operações posteriores
                        for(int h = i; h <= parantese; h++){
                            exp[h] = ' ';
                        }
                    } 
                }

                if(valid == false){
                    exp[i-1] = '0'; // se não entrar no if anterior faço o mesmo processo porem atribuindo o valor 0
                    for(int h = i; h <= parantese; h++){
                        exp[h] = ' ';
                    }
                }

                i = -1;
            } 
        } //
    }

    // Função onde leio minha expressão de trás pra frente verificando a próxima operação a ser efetuada
    public static String nextOper(char[] exp, int tam_exp){
        String resp = "";
        for(int i = tam_exp-1; i >= 0; i--){
            if (exp[i] == 'd' ){
                resp = "and";
                i = -1;
            } else if(exp[i] == 't'){
                resp = "not";
                i = -1;
            } else if(exp[i] == 'r'){
                resp = "or";
                i = -1;
            }
        }

        return resp;
    }

    public static boolean AlgebraBoolean(char[] exp, int tam_exp){
        String next;
        boolean result;

        if(exp[0] == '1'){
            result = true;
        } else if (exp[0] == '0'){
            result = false;
        } else {
            next = nextOper(exp, tam_exp);
    
            if(next.equals("not")){
                not(exp, tam_exp);
            } else if(next.equals("and")) {
                and(exp, tam_exp);
            } else if(next.equals("or")){
                or(exp, tam_exp);
            }

            result = AlgebraBoolean(exp, tam_exp);
        }

        return result;
        
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expressao;
        int quant_number, tam_exp;
        boolean retorno;
        char[] exp; 

        
        do {
            quant_number = sc.nextInt();
            
            if(quant_number > 0){
                int[] valores = new int[quant_number];
                for(int i = 0; i < quant_number; i++){
                    valores[i] = sc.nextInt();
                }
    
                expressao = sc.nextLine();
                tam_exp = expressao.length();
                exp = convert(expressao, tam_exp);

                if(quant_number == 1){
                    ReplaceA(exp, valores[0],tam_exp);
                } else if(quant_number == 2){
                    ReplaceA(exp, valores[0], tam_exp);
                    ReplaceB(exp, valores[1], tam_exp);
                } else if (quant_number == 3){
                    ReplaceA(exp, valores[0], tam_exp);
                    ReplaceB(exp, valores[1], tam_exp);
                    ReplaceC(exp, valores[2], tam_exp);
                }
               
                retorno = AlgebraBoolean(exp,tam_exp);

                if (retorno  == true){
                    System.out.println("1");
                }else {
                    System.out.println("0");
    
                }
                
            }

        }while(quant_number > 0);

        sc.close();
    }
}
