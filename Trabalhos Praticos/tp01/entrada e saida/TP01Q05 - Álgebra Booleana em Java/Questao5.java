import java.util.Scanner;

//2 0 0 and(not(A) , not(B))
public class Questao5 {

    public static char[] convert(String expressao, int tam_exp){
        char[] exp = new char[tam_exp];
        for(int i = 1; i < tam_exp; i++){
            exp[i-1] = expressao.charAt(i);
        }

        return exp;
    }

    public static void ReplaceA(char[] expressao, int valor, int tam_exp){
        
        for(int i = 0; i < tam_exp; i++){
            if(expressao[i] == 'A'){
                expressao[i] = (char) (valor + '0');
            }
        }

    }

    public static void ReplaceB(char[] expressao, int valor, int tam_exp){
        
         for(int i = 0; i < tam_exp; i++){
             if(expressao[i] == 'B'){
                 expressao[i] = (char) (valor + '0');
             }
         }
 
    }

    public static void ReplaceC(char[] expressao, int valor, int tam_exp){
        
         for(int i = 0; i < tam_exp; i++){
             if(expressao[i] == 'C'){
                 expressao[i] = (char) (valor + '0');
             }
         }
 
    }

    public static void not(char[] exp, int tam_exp){ //1     
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
        for(int i = tam_exp-1; i >= 0; i--){
            if(exp[i] == 'd'){
                for(int j = i+1; j < tam_exp; j++){
                    if(exp[j] == ')'){
                        parantese = j;
                        j = tam_exp;
                    }
                }

                for(int k = i + 1; k < parantese; k++){ //and
                    if(exp[k] == '0'){
                        valid = false;
                        exp[i-2] = '0';
                        for(int h = i-1; h <= parantese; h++){
                            exp[h] = ' ';
                        }
                    } 
                }

                if(valid == true){
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
        for(int i = tam_exp-1; i >= 0; i--){
            if(exp[i] == 'r'){
                for(int j = i+1; j < tam_exp; j++){
                    if(exp[j] == ')'){
                        parantese = j;
                        j = tam_exp;
                    }
                }

                for(int k = i + 1; k < parantese; k++){
                    if(exp[k] == '1'){
                        valid = true;
                        exp[i-1] = '1';
                        for(int h = i; h <= parantese; h++){
                            exp[h] = ' ';
                        }
                    } 
                }

                if(valid == false){
                    exp[i-1] = '0';
                    for(int h = i; h <= parantese; h++){
                        exp[h] = ' ';
                    }
                }

                i = -1;
            } 
        } //
    }

    public static String nextOper(char[] exp, int tam_exp){ //not or and
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

    public static boolean AlgebraBoolean(String expressao, int quant_valores, int[] valores){
        int tam_exp = expressao.length();
        char[] exp;
        String next;

        exp = convert(expressao, tam_exp);

        if(quant_valores == 1){
            ReplaceA(exp, valores[0],tam_exp);
            //System.out.println("Dentro de replace: " + exp[1]);
        } else if(quant_valores == 2){
            ReplaceA(exp, valores[0], tam_exp);
            ReplaceB(exp, valores[1], tam_exp);
            //System.out.println("Dentro de replace: " + exp[1]);
        } else if (quant_valores == 3){
            ReplaceA(exp, valores[0], tam_exp);
            ReplaceB(exp, valores[1], tam_exp);
            ReplaceC(exp, valores[2], tam_exp);
            //System.out.println("Dentro de replace: " + exp[1]);
        }

        while(true){

            next = nextOper(exp, tam_exp);

            if(next.equals("not")){
                not(exp, tam_exp);
            } else if(next.equals("and")) {
                and(exp, tam_exp);
            } else if(next.equals("or")){
                or(exp, tam_exp);
            } else {
                break;
            }

            System.err.println(exp);
        }
        
        if(exp[0] == '1'){
            return true;
        } else{
            return false;
        }


        //return true;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expressao;
        int quant_number;
        boolean retorno; 

        
        do {
            quant_number = sc.nextInt(); //2 0 0 and
            
            if(quant_number > 0){
                int[] valores = new int[quant_number];
                for(int i = 0; i < quant_number; i++){
                    valores[i] = sc.nextInt();
                }
    
                expressao = sc.nextLine();
               
                retorno = AlgebraBoolean(expressao,quant_number, valores);
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
