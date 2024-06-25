//----------------------------------------------------------------------------------------//
// Avisos
//----------------------------------------------------------------------------------------//
// Olá, esse código provavelmente não está organizado como eu gostaria,
// por conta da urgência das entregas.
// E é normal ter muitas linhas de código comentadas, 
// já que é para uma atividade, eu aproveito isso para documentar todo o estudo também.
//----------------------------------------------------------------------------------------//

import java.util.Scanner;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

class NoABPFilho {
    public Personagem personagem;
    public NoABPFilho dir, esq;

    public NoABPFilho() {
        this.personagem = null;
        this.dir = this.esq = null;
    }

    public NoABPFilho(Personagem x) {
        this.personagem = x;
        this.dir = this.esq = null;
    }
}

class NoABP {
    int elemento;
    NoABP esq, dir;
    NoABPFilho raizFilho;

    NoABP(int x) {
        this.elemento = x % 15;
        esq = dir = null;
        raizFilho = null;
    }
}

class ArvoreABP {
    private NoABP raiz;

    public ArvoreABP() {
        this.raiz = null;
    }

    // ====================================//
    // --------- Metodos Publicos ---------//
    // ====================================//

    public void inserirABP(int x) {
        raiz = inserir(raiz, x);
    }

    public void inserirPersonagem(Personagem personagem) {
        int pos = personagem.getYearOfBirth() % 15;
        inserirPersonagem(raiz, personagem, pos);
    }

    public void pesquisar(String nome) {
        boolean flag;
        System.out.print(nome + " => raiz");
        flag = pesquisar(raiz, nome);
        if (flag) {
            System.out.println(" NAO");
        } else {
            System.out.println(" SIM");
        }
    }

    // ====================================//
    // --------- Metodos Privados ---------//
    // ====================================//

    private int compararNomes(String nome1, String nome2) {
        if (nome1 == null && nome2 == null) {
            return 0; // Ambos são null, considerados iguais
        }
        if (nome1 == null) {
            return -1; // nome1 é null, considerado menor
        }
        if (nome2 == null) {
            return 1; // nome2 é null, considerado menor
        }
        return nome1.compareTo(nome2);
    }

    private void inserirPersonagem(NoABP i, Personagem personagem, int pos) {
        if (i == null) {
            System.out.println("ERRO inserirPersonagem");
        } else if (pos > i.elemento) {
            inserirPersonagem(i.dir, personagem, pos);
        } else if (pos < i.elemento) {
            inserirPersonagem(i.esq, personagem, pos);
        } else {
            i.raizFilho = inserirABP(i.raizFilho, personagem);
        }
    }

    private NoABP inserir(NoABP i, int x) {
        if (i == null) {
            i = new NoABP(x);
        } else if (x > i.elemento) {
            i.dir = inserir(i.dir, x);
        } else if (x < i.elemento) {
            i.esq = inserir(i.esq, x);
        } else {
            System.out.println("ERRO inserir");
        }
        return i;
    }

    private NoABPFilho inserirABP(NoABPFilho i, Personagem x) {
        if (i == null) {
            i = new NoABPFilho(x);
        } else if (compararNomes(x.getName(), i.personagem.getName()) < 0) {
            i.esq = inserirABP(i.esq, x);
        } else if (compararNomes(x.getName(), i.personagem.getName()) > 0) {
            i.dir = inserirABP(i.dir, x);
        } else {
            return i; // ERRO, NOME IGUAL
        }
        return i;
    }

    private boolean pesquisar(NoABP i, String x) {
        boolean flag = true;
        if (i != null) {
            if (i.raizFilho != null) {
                flag = pesquisarSubArvore(i.raizFilho, x);
            }
            if (flag) {
                System.out.print(" ESQ");
                flag = pesquisar(i.esq, x);
            }
            if (flag) {
                System.out.print(" DIR");
                flag = pesquisar(i.dir, x);
            }
        }
        return flag;
    }

    private boolean pesquisar(NoABPFilho i, String x) {
        boolean flag = false;
        if (i == null) {
            System.out.println("NAO");
            return flag;
        } else if (compararNomes(x, i.personagem.getName()) == 0) {
            System.out.println("SIM");
            flag = true;
        } else if (compararNomes(x, i.personagem.getName()) < 0) {
            System.out.print("esq ");
            flag = pesquisar(i.esq, x);
        } else {
            System.out.print("dir ");
            flag = pesquisar(i.dir, x);
        }
        return flag;
    }

    private boolean pesquisarSubArvore(NoABPFilho i, String nome) {
        boolean flag;
        if (i == null) {
            flag = true;
        } else if (compararNomes(nome, i.personagem.getName()) > 0) {
            System.out.print("->dir");
            flag = pesquisarSubArvore(i.dir, nome);
        } else if (compararNomes(nome, i.personagem.getName()) < 0) {
            System.out.print("->esq");
            flag = pesquisarSubArvore(i.esq, nome);
        } else {
            flag = false;
        }

        return flag;
    }

}

class Lista {
    String[] codenomes = new String[10];

    public Lista() {
        // Inicializando o array com strings vazias
        for (int i = 0; i < codenomes.length; i++) {
            codenomes[i] = " ";
        }
    }

    public Lista(String nomes) {
        int tam = nomes.length();

        StringBuilder nomesedit = new StringBuilder();

        int j = 0;
        for (int i = 0; i < tam; i++) {
            if (nomes.charAt(i) != '\'' && nomes.charAt(i) != '[' && nomes.charAt(i) != ']') {
                if (nomes.charAt(i) != ',') {
                    nomesedit.append(nomes.charAt(i));
                } else {
                    nomesedit.append(nomes.charAt(i));
                    this.codenomes[j] = nomesedit.toString();
                    nomesedit.setLength(0);
                    j++;
                }
            }
        }
        // }
        this.codenomes[j] = nomesedit.toString();
    }
}

public class Personagem {
    private String id;
    private String name;
    private Lista alternate_name;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    // alternate_actors
    private Date dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    public Personagem(String id, String name, Lista alternate_name, String house, String ancestry, String species,
            String patronus, boolean hogwartsStaff, boolean hogwartsStudent, String actorName, boolean alive,
            Date dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour, boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternate_name = alternate_name;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }

    public Personagem() {
        id = " ";
        name = " ";
        alternate_name = new Lista();
        house = " ";
        ancestry = " ";
        species = " ";
        patronus = " ";
        hogwartsStaff = false;
        hogwartsStudent = false;
        actorName = " ";
        alive = false;
        dateOfBirth = null;
        yearOfBirth = -1;
        eyeColour = " ";
        gender = " ";
        hairColour = " ";
        wizard = false;
    }

    // ---------- Metodos GET/SET INICIO -----------//
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lista getAlternate_name() {
        return alternate_name;
    }

    public void setAlternate_name(Lista alternate_name) {
        this.alternate_name = alternate_name;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public boolean isHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public boolean isWizard() {
        return wizard;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }

    // ---------- Metodos GET/SET FINAL -----------//
    // ---------- Outros Metodos INICIO -----------//

    public static boolean toUSbool(String frase) {
        if (frase.equals("VERDADEIRO")) {
            return true;
        } else {
            return false;
        }
    }

    public static void imprimirlista(Lista lista) {
        System.out.print("{");
        for (int i = 0; i < 10; i++) {
            if (lista.codenomes[i] != null) {
                System.out.print(lista.codenomes[i]);
            }
        }
        System.out.print("}");
        System.out.print(" ## ");

    }

    public void imprimir() {
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");

        System.out.print(id + " ## ");
        System.out.print(name + " ## ");
        imprimirlista(alternate_name);
        System.out.print(house + " ## ");
        System.out.print(ancestry + " ## ");
        System.out.print(species + " ## ");
        System.out.print(patronus + " ## ");
        System.out.print(hogwartsStaff + " ## ");
        System.out.print(hogwartsStudent + " ## ");
        System.out.print(actorName + " ## ");
        System.out.print(alive + " ## ");
        System.out.print(sdfOutput.format(dateOfBirth) + " ## ");
        System.out.print(yearOfBirth + " ## ");
        System.out.print(eyeColour + " ## ");
        System.out.print(gender + " ## ");
        System.out.print(hairColour + " ## ");
        System.out.println(wizard + "]");
    }

    public Personagem clone(Personagem personagem) {

        Personagem clone = new Personagem(
                personagem.getId(),
                personagem.getName(),
                personagem.getAlternate_name(),
                personagem.getHouse(),
                personagem.getAncestry(),
                personagem.getSpecies(),
                personagem.getPatronus(),
                personagem.isHogwartsStaff(),
                personagem.isHogwartsStudent(),
                personagem.getActorName(),
                personagem.isAlive(),
                personagem.getDateOfBirth(),
                personagem.getYearOfBirth(),
                personagem.getEyeColour(),
                personagem.getGender(),
                personagem.getHairColour(),
                personagem.isWizard());

        return clone;
    }

    public static Personagem ler(String linhaID) {
        // File file = new File("characters.csv");
        File file = new File("/tmp/characters.csv");

        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine(); // Descarta a primeira linha
            while (scanner.hasNextLine()) {
                SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
                // SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");

                String linha = scanner.nextLine();
                String[] vetor = separarlinha(linha);

                String id = vetor[0];
                if (linhaID.equals(id)) {

                    String name = vetor[1];
                    Lista alternate_name = new Lista(vetor[2]);
                    // System.out.println(vetor[2]); // testa
                    // System.out.println(alternate_name.codenomes[3]); // testa
                    String house = vetor[3];
                    String ancestry = vetor[4];
                    String species = vetor[5];
                    String patronus = vetor[6];
                    boolean hogwartsStaff = toUSbool(vetor[7]);
                    boolean hogwartsStudent = toUSbool(vetor[8]);
                    String actorName = vetor[9];
                    boolean alive = toUSbool(vetor[10]);
                    // alternate_actors VETOR 11 NULO
                    Date dateOfBirth = sdfInput.parse(vetor[12]);
                    // String teste = sdfOutput.format(dateOfBirth);
                    int yearOfBirth = Integer.parseInt(vetor[13]);

                    String eyeColour = vetor[14];
                    String gender = vetor[15];
                    String hairColour = vetor[16];

                    boolean wizard = toUSbool(vetor[17]);

                    return new Personagem(id, name, alternate_name, house, ancestry,
                            species,
                            patronus, hogwartsStaff, hogwartsStudent, actorName, alive,
                            dateOfBirth, yearOfBirth, eyeColour, gender, hairColour, wizard);
                }
            }
            // scanner.close(); // Bugado?
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] separarlinha(String linha) {
        StringBuilder res = new StringBuilder();
        String[] resfinal = new String[18];
        int tam = linha.length();

        int j = 0;
        for (int i = 0; i < tam; i++) {
            if (linha.charAt(i) != ';') {
                res.append(linha.charAt(i));
            } else {
                resfinal[j] = res.toString();
                res.setLength(0);
                j++;
            }
        }
        resfinal[j] = res.toString();
        return resfinal;
    }

    // ---------- Outros Metodos FINAL -----------//

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ArvoreABP personagens = new ArvoreABP();
        personagens.inserirABP(7);
        personagens.inserirABP(3);
        personagens.inserirABP(11);
        personagens.inserirABP(1);
        personagens.inserirABP(5);
        personagens.inserirABP(9);
        personagens.inserirABP(13);
        personagens.inserirABP(0);
        personagens.inserirABP(2);
        personagens.inserirABP(4);
        personagens.inserirABP(6);
        personagens.inserirABP(8);
        personagens.inserirABP(10);
        personagens.inserirABP(12);
        personagens.inserirABP(14);

        String ID = sc.nextLine();

        // while (sc.hasNextLine()) {
        while (ID.equals("FIM") != true) {
            personagens.inserirPersonagem(ler(ID));
            // personagem[i].imprimir();
            ID = sc.nextLine();
        }

        ID = sc.nextLine();
        while (ID.equals("FIM") != true) {
            personagens.pesquisar(ID);
            ID = sc.nextLine();
        }

        sc.close();
    }
}
// cls && javac Personagem.java && java Personagem < pub.in > saida.txt