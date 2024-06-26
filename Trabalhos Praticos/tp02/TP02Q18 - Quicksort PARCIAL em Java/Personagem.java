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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
// import java.io.RandomAccessFile;
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
        // int size = 0;

        // for (int i = 0; i < tam; i++) {
        // if (nomes.charAt(i) == ',') {
        // size++;
        // }
        // }
        // size++;

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

    public void imprimir() {
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");

        System.out.print("[" + id + " ## ");
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
                // scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Retorna null se não encontrar o personagem ou ocorrer erro
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
        // System.out.println(resfinal[2]); // testa
        return resfinal;
    }

    public static int findNull(Personagem vetor[]) {
        int pos = vetor.length - 1;

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == null) {
                pos = i;
                i = vetor.length;
            }
        }

        return pos;
    }

    public static void swap(Personagem vetor[], int i, int j) {
        Personagem tmp = vetor[i].clone(vetor[i]);
        vetor[i] = vetor[j].clone(vetor[j]);
        vetor[j] = tmp.clone(tmp);
    }

    public static void SelectionSort(Personagem vetor[]) {
        for (int i = 0; i < vetor.length - 1 && vetor[i] != null; i++) {
            String maior = new String(vetor[i].getName());
            int tmp = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j] != null && vetor[j].getName().compareTo(maior) < 0) {
                    maior = new String(vetor[j].getName());
                    tmp = j;
                }
            }
            swap(vetor, i, tmp);
        }
    }

    public static int[] QuickSort(Personagem vetor[], int esq, int dir, int[] info) {
        if (esq < dir && esq < 10) {
            int[] partitionInfo = partition(vetor, esq, dir, info);

            info[0] += partitionInfo[0];
            info[1] += partitionInfo[1];

            int indicePivot = partitionInfo[0];
            info = QuickSort(vetor, esq, indicePivot - 1, info);
            info = QuickSort(vetor, indicePivot + 1, dir, info);
        }
        return info;
    }

    private static int[] partition(Personagem vetor[], int esq, int dir, int[] info) {
        Personagem pivot = vetor[dir];
        int i = esq - 1;

        for (int j = esq; j < dir; j++) {
            info[0]++;
            if (CompareHouseOrName(vetor[j], pivot) <= 0) {
                i++;
                swap(vetor, i, j);
                info[1] += 3;
            }
        }

        swap(vetor, i + 1, dir);
        info[1] += 3;
        int[] result = {i + 1, info[0], info[1]};
        return result;
    }

    private static int CompareHouseOrName(Personagem a, Personagem b) {
        int houseComparison = a.getHouse().compareTo(b.getHouse());
        if (houseComparison != 0) {
            return houseComparison;
        } else {
            return a.getName().compareTo(b.getName());
        }
    }

    public static void log(long tempoExecucao, int info[]) {
        File log = new File("831566_quicksortParcial.txt");
        double segundos = tempoExecucao / 1_000_000_000.0;

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(log, true));
            writer.println("831566\t" + info[0] + "\t" + info[1] + "\t" + segundos);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------- Outros Metodos FINAL -----------//
    public static void main(String[] args) {
        long inicio = System.nanoTime();
        int[] info = new int[2]; // info[0] = comparacoes, info[1] = movimentacoes

        Personagem personagem[] = new Personagem[30];

        Scanner sc = new Scanner(System.in);

        // while (sc.hasNextLine()) {
        String ID = sc.nextLine();

        int i = 0;

        while (ID.equals("FIM") != true) {
            personagem[i] = ler(ID);
            // personagem[i].imprimir();
            i++;
            ID = sc.nextLine();
        }

        int nulo = findNull(personagem);
        info = QuickSort(personagem, 0, nulo - 1, info);

        // sc.close();
        for (i = 0; personagem[i] != null && i < 10; i++) {
            personagem[i].imprimir();
        }

        long fim = System.nanoTime();
        long tempoExecucao = fim - inicio;
        log(tempoExecucao, info);
    }
}
