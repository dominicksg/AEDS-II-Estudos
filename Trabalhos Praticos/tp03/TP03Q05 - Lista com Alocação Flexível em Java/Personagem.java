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

class CelulaLista {
    Personagem personagem;
    CelulaLista prox;

    CelulaLista() {
        this.personagem = new Personagem();
        this.prox = null;
    }

    CelulaLista(Personagem p) {
        this.personagem = p;
        this.prox = null;
    }
}

class ListaPersonagem {
    private int tam;
    private CelulaLista primeiro, ultimo;

    public ListaPersonagem() {
        primeiro = ultimo = new CelulaLista();
        tam = 0;
    }

    public int getTam() {
        return this.tam;
    }

    public void imprimir() {
        if (this.tam == 0) {
            System.out.println("Erro: lista vazia.");
            return;
        }

        int cont = 0;
        for (CelulaLista i = this.primeiro.prox; i != null; i = i.prox) {
            System.out.print("[" + cont++ + " ## ");
            i.personagem.imprimir();
        }
    }

    // ---------- Funcoes INICIO -----------//

    public void InserirInicio(Personagem personagem) {
        CelulaLista tmp = new CelulaLista(personagem);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if (primeiro == ultimo)
            ultimo = tmp;
        tmp = null;
        this.tam++;
    }

    public void InserirFim(Personagem personagem) {
        CelulaLista tmp = new CelulaLista(personagem);
        ultimo.prox = tmp;
        ultimo = ultimo.prox;
        this.tam++;
    }

    public void Inserir(String posSTRING, Personagem personagem) throws Exception {
        int pos = Integer.parseInt(posSTRING);
        if (pos < 0 || pos > this.tam) {
            throw new Exception("Erro: posicao inexistente");
        } else if (pos == 0) {
            InserirInicio(personagem);
        } else if (pos == this.tam) {
            InserirFim(personagem);
        } else {
            CelulaLista i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            CelulaLista tmp = new CelulaLista(personagem);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
            this.tam++;
        }
    }

    public Personagem RemoverInicio() throws Exception {
        if (this.primeiro == this.ultimo) {
            throw new Exception("Erro: lista vazia.");
        }
        Personagem personagem = primeiro.prox.personagem;
        CelulaLista tmp = primeiro.prox;
        primeiro.prox = primeiro.prox.prox;
        tmp.prox = null;
        tmp = null;
        this.tam--;
        return personagem;
    }

    public Personagem RemoverFim() throws Exception {
        if (this.primeiro == this.ultimo) {
            throw new Exception("Erro: lista vazia.");
        }
        CelulaLista i;
        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;
        Personagem personagem = i.prox.personagem; // ou (ultimo.elemento)
        ultimo = i;
        ultimo.prox = i = null;
        this.tam--;
        return personagem;
    }

    public Personagem Remover(String posSTRING) throws Exception {
        int pos = Integer.parseInt(posSTRING);
        Personagem personagem = new Personagem();
        if (pos < 0 || pos >= this.tam) {
            throw new Exception("Erro: posicao inexistente");
        } else if (pos == 0) {
            RemoverInicio();
        } else if (pos == this.tam - 1) {
            RemoverFim();
        } else {
            CelulaLista i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            CelulaLista tmp = i.prox;
            personagem = tmp.personagem;
            i.prox = tmp.prox;
            tmp = tmp.prox = null;
            this.tam--;
        }
        return personagem;
    }

    // ---------- Funcoes FIM -----------//
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

    public static void hubFuncao(String linha, ListaPersonagem lista) throws Exception {

        String palavra[] = linha.split(" ");

        if (palavra[0].equals("II")) {
            lista.InserirInicio(ler(palavra[1]));
        } else if (palavra[0].equals("IF")) {
            lista.InserirFim(ler(palavra[1]));
        } else if (palavra[0].equals("I*")) {
            lista.Inserir(palavra[1], ler(palavra[2]));
        } else if (palavra[0].equals("RI")) {
            System.out.println("(R) " + lista.RemoverInicio().getName());
        } else if (palavra[0].equals("RF")) {
            System.out.println("(R) " + lista.RemoverFim().getName());
        } else if (palavra[0].equals("R*")) {
            System.out.println("(R) " + lista.Remover(palavra[1]).getName());
        }
    }

    // ---------- Outros Metodos FINAL -----------//

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Personagem personagem[] = new Personagem[30];
        ListaPersonagem lista = new ListaPersonagem();

        String ID = sc.nextLine();

        // while (sc.hasNextLine()) {
        while (ID.equals("FIM") != true) {
            lista.InserirFim(ler(ID));
            // personagem[i].imprimir();
            ID = sc.nextLine();
        }

        int numEntradas = sc.nextInt();

        for (int j = 0; j <= numEntradas; j++) {
            ID = sc.nextLine(); // ID = Linha
            hubFuncao(ID, lista);
        }

        lista.imprimir();
        sc.close();
    }
}
// cls && javac Personagem.java && java Personagem < pub.in > saida.txt