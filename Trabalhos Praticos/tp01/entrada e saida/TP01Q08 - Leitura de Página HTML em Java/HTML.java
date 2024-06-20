import java.io.*;
import java.net.*;
//import java.util.Scanner;

class HTML {
    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }

        return resp;
    }

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        // Scanner Sc = new Scanner(System.in);

        String nome, endereco, html;
        // endereco = "http://maratona.crc.pucminas.br/series/Friends.html";
        // html = getHtml(endereco);
        // System.out.print(html);

        // nome = Sc.nextLine();
        nome = MyIO.readLine();
        while (isFim(nome) == false) {

            int a = 0, e = 0, i = 0, o = 0, u = 0;
            int a1 = 0, e1 = 0, i1 = 0, o1 = 0, u1 = 0;
            int a2 = 0, e2 = 0, i2 = 0, o2 = 0, u2 = 0;
            int a3 = 0, o3 = 0, a4 = 0, e3 = 0, i3 = 0, o4 = 0, u3 = 0;
            int consoante = 0;
            int br = 0, table = 0;

            // endereco = Sc.nextLine();
            endereco = MyIO.readLine();
            html = getHtml(endereco);

            for (int num = 0; num < html.length(); num++) {
                // char caractere = Character.toLowerCase(html.charAt(num));
                char caractere = html.charAt(num);
                switch (caractere) {
                    case 'a':
                        a++;
                        break;
                    case 'e':
                        e++;
                        break;
                    case 'i':
                        i++;
                        break;
                    case 'o':
                        o++;
                        break;
                    case 'u':
                        u++;
                        break;
                    case 225: // á
                        a1++;
                        break;
                    case 233: // é
                        e1++;
                        break;
                    case 237: // í
                        i1++;
                        break;
                    case 243: // ó
                        o1++;
                        break;
                    case 250: // ú
                        u1++;
                        break;
                    case 224: // à
                        a2++;
                        break;
                    case 232: // è
                        e2++;
                        break;
                    case 236: // ì
                        i2++;
                        break;
                    case 242: // ò
                        o2++;
                        break;
                    case 249: // ù
                        u2++;
                        break;
                    case 227: // ã
                        a3++;
                        break;
                    case 245: // õ
                        o3++;
                        break;
                    case 226: // â
                        a4++;
                        break;
                    case 234: // ê
                        e3++;
                        break;
                    case 238: // î
                        i3++;
                        break;
                    case 244: // ô
                        o4++;
                        break;
                    case 251: // û
                        u3++;
                        break;
                    case '<':
                        if ((html.charAt(num + 1) == 't') && (html.charAt(num + 2) == 'a') &&
                                (html.charAt(num + 3) == 'b') && (html.charAt(num + 4) == 'l') &&
                                (html.charAt(num + 5) == 'e') && (html.charAt(num + 6) == '>')) {
                            table++;
                            a--;
                            e--;
                            consoante -= 3;
                        } else if ((html.charAt(num + 1) == 'b') && (html.charAt(num + 2) == 'r') &&
                                (html.charAt(num + 3) == '>')) {
                            br++;
                            consoante -= 2;
                        }
                    default:
                        // Verifica se é uma consoante
                        if (caractere >= 'a' && caractere <= 'z') {
                            consoante++;
                        }
                }// SWITCH
            } // FOR

            System.out.print("a(" + a + ") ");
            System.out.print("e(" + e + ") ");
            System.out.print("i(" + i + ") ");
            System.out.print("o(" + o + ") ");
            System.out.print("u(" + u + ") ");

            // System.out.print("á(" + a1 + ") ");
            System.out.printf("%c(%d) ", 225,a1);

            //System.out.print("é(" + e1 + ") ");
            System.out.printf("%c(%d) ", 233,e1);

            //System.out.print("í(" + i1 + ") ");
            System.out.printf("%c(%d) ", 237,i1);

            //System.out.print("ó(" + o1 + ") ");
            System.out.printf("%c(%d) ", 243,o1);

            //System.out.print("ú(" + u1 + ") ");
            System.out.printf("%c(%d) ", 250,u1);

            //System.out.print("à(" + a2 + ") ");
            System.out.printf("%c(%d) ", 224,a2);

            //System.out.print("è(" + e2 + ") ");
            System.out.printf("%c(%d) ", 232,e2);

            //System.out.print("ì(" + i2 + ") ");
            System.out.printf("%c(%d) ", 236,i2);

            //System.out.print("ò(" + o2 + ") ");
            System.out.printf("%c(%d) ", 242,o2);

            //System.out.print("ù(" + u2 + ") ");
            System.out.printf("%c(%d) ", 249,u2);

            //System.out.print("ã(" + a3 + ") ");
            System.out.printf("%c(%d) ", 227,a3);

            //System.out.print("õ(" + o3 + ") ");
            System.out.printf("%c(%d) ", 245,o3);

            //System.out.print("â(" + a4 + ") ");
            System.out.printf("%c(%d) ", 226,a4);

            //System.out.print("ê(" + e3 + ") ");
            System.out.printf("%c(%d) ", 234,e3);

            //System.out.print("î(" + i3 + ") ");
            System.out.printf("%c(%d) ", 238,i3);

            //System.out.print("ô(" + o4 + ") ");
            System.out.printf("%c(%d) ", 244,o4);

            //System.out.print("û(" + u3 + ") ");
            System.out.printf("%c(%d) ", 251,u3);

            System.out.print("consoante(" + consoante + ") ");
            System.out.print("<br>(" + br + ") ");
            System.out.print("<table>(" + table + ") ");
            System.out.println(nome);

            // nome = Sc.nextLine();
            nome = MyIO.readLine();

        } // WHILE
          // Sc.close();
    }
}

/*
 * for (int num = 0; num < html.length(); num++) {
 * if (html.charAt(num) == 'a') {
 * a++;
 * }
 * }
 */