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
import java.io.RandomAccessFile;
import java.util.Date;
import java.text.SimpleDateFormat;

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
        int size = 0;

        for (int i = 0; i < tam; i++) {
            if (nomes.charAt(i) == ',') {
                size++;
            }
        }
        size++;
        StringBuilder nomesedit = new StringBuilder();
        // String[] finalnomes = new String[size];

        int j = 0;
        // for (int j = 0; j < size; j++) {
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

    // public static void imprime(Lista lista) {
    // // PROBLEMA NO TAMANHO AQUI
    // for (int i = 0; i < 10; i++) {
    // if (lista.codenomes[i] != null) { // Gambiarra
    // System.out.println(lista.codenomes[i]);
    // }
    // }
    // }
}

class Personagem {
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

    // public Personagem() {
    // id = null;
    // name = null;
    // alternate_name = null;
    // house = null;
    // ancestry = null;
    // species = null;
    // patronus = null;
    // hogwartsStaff = false;
    // hogwartsStudent = false;
    // actorName = null;
    // alive = false;
    // dateOfBirth = null;
    // yearOfBirth = -1;
    // eyeColour = null;
    // gender = null;
    // hairColour = null;
    // wizard = false;
    // }

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
        // PROBLEMA NO TAMANHO AQUI
        System.out.print("{");
        for (int i = 0; i < 10; i++) {
            if (lista.codenomes[i] != null) { // Gambiarra?
                System.out.print(lista.codenomes[i]);
            }
        }
        System.out.print("}");
        System.out.print(" ## ");

    }

    public static void imprimir(Personagem p) {
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");

        System.out.print("[" + p.id + " ## ");
        System.out.print(p.name + " ## ");
        imprimirlista(p.alternate_name);
        System.out.print(p.house + " ## ");
        System.out.print(p.ancestry + " ## ");
        System.out.print(p.species + " ## ");
        System.out.print(p.patronus + " ## ");
        System.out.print(p.hogwartsStaff + " ## ");
        System.out.print(p.hogwartsStudent + " ## ");
        System.out.print(p.actorName + " ## ");
        System.out.print(p.alive + " ## ");
        System.out.print(sdfOutput.format(p.dateOfBirth) + " ## ");
        System.out.print(p.yearOfBirth + " ## ");
        System.out.print(p.eyeColour + " ## ");
        System.out.print(p.gender + " ## ");
        System.out.print(p.hairColour + " ## ");
        System.out.println(p.wizard + "]");

        // System out pra imprimit tudo com uma linha dentro do ()
    }

    public static Personagem clonar(Personagem a) {
        // Personagem b;
        // b.clone(a);
        return a;
    }

    public static void ler(String linhaID) {
        String linha;
        String[] vetor = new String[18];
        SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");

        try {
            File file = new File("/tmp/characters.csv");
            Scanner scanner = new Scanner(file);

            // RandomAccessFile file = new RandomAccessFile("/tmp/characters.csv", "rw");
            /// /tmp/characters.csv

            // file.readLine(); // Descarta a primeira linha
            // System.out.println(linha); // testa

            // while ((linha = file.readLine()) != null) {

            scanner.nextLine(); // Descarta a primeira linha
            while ((linha = scanner.nextLine()) != null) {
                vetor = separarlinha(linha);

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

                    Personagem p = new Personagem(id, name, alternate_name, house, ancestry,
                            species,
                            patronus, hogwartsStaff, hogwartsStudent, actorName, alive,
                            dateOfBirth, yearOfBirth, eyeColour, gender, hairColour, wizard);

                    imprimir(p);
                    break;
                }
            }
            // file.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public static Personagem guardar() { // Fiz para estudo
    // String linha;
    // String[] vetor = new String[18];
    // SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
    // SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");
    // Personagem erro = new Personagem();// ?????????

    // try {
    // RandomAccessFile file = new RandomAccessFile("characters.csv", "rw");
    // file.readLine(); // Descarta a primeira linha
    // // System.out.println(linha); // testa

    // // while (file.getFilePointer() < file.length()) {
    // linha = file.readLine();

    // vetor = separarlinha(linha);
    // // System.out.println(vetor[1]); // testa

    // String id = vetor[0];
    // String name = vetor[1];
    // Lista alternate_name = new Lista(vetor[2]);
    // // System.out.println(vetor[2]); // testa
    // // System.out.println(alternate_name.codenomes[3]); // testa
    // String house = vetor[3];
    // String ancestry = vetor[4];
    // String species = vetor[5];
    // String patronus = vetor[6];
    // boolean hogwartsStaff = toUSbool(vetor[7]);
    // boolean hogwartsStudent = toUSbool(vetor[8]);
    // String actorName = vetor[9];
    // boolean alive = toUSbool(vetor[10]);
    // // alternate_actors VETOR 11 NULO
    // Date dateOfBirth = sdfInput.parse(vetor[12]);
    // // String teste = sdfOutput.format(dateOfBirth);
    // // System.out.println(teste); // testa
    // int yearOfBirth = Integer.parseInt(vetor[13]);

    // String eyeColour = vetor[14];
    // String gender = vetor[15];
    // String hairColour = vetor[16];

    // boolean wizard = toUSbool(vetor[17]);

    // Personagem p = new Personagem(id, name, alternate_name, house, ancestry,
    // species,
    // patronus, hogwartsStaff, hogwartsStudent, actorName, alive,
    // dateOfBirth, yearOfBirth, eyeColour, gender, hairColour, wizard);

    // file.close();
    // return p;
    // // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return erro; // ???????
    // }

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
        // System.out.println(resfinal[2]); // testa
        return resfinal;
    }

    // ---------- Outros Metodos FINAL -----------//
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // while (sc.hasNextLine()) {
        String ID = sc.nextLine();

        while (ID.equals("FIM") != true) {
            ler(ID);
            ID = sc.nextLine();
        }
        sc.close();
    }
}
