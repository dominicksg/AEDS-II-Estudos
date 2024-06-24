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

class NoRB {
    public Personagem personagem;
    public boolean cor; // true = vermelho, false = preto
    public NoRB esq, dir;

    public NoRB(Personagem personagem) {
        this.personagem = personagem;
        this.cor = true;
        this.esq = null;
        this.dir = null;
    }
}

class ArvoreRB {
    private NoRB raiz;

    public ArvoreRB() {
        raiz = null;
    }

    public void inserir(Personagem personagem) {
        if (raiz == null) {
            raiz = new NoRB(personagem);
        } else if (raiz.dir == null && raiz.esq == null) {
            if (compararNomes(personagem.getName(), raiz.personagem.getName()) < 0) {
                raiz.esq = new NoRB(personagem);
            } else {
                raiz.dir = new NoRB(personagem);
            }
        } else if (raiz.esq == null) {
            if (compararNomes(personagem.getName(), raiz.personagem.getName()) < 0) {
                raiz.esq = new NoRB(personagem);
            } else if (compararNomes(personagem.getName(), raiz.dir.personagem.getName()) < 0) {
                raiz.esq = new NoRB(raiz.personagem);
                raiz.personagem = personagem;
            } else {
                raiz.esq = new NoRB(raiz.personagem);
                raiz.personagem = raiz.dir.personagem;
                raiz.dir.personagem = personagem;
            }

            raiz.esq.cor = raiz.dir.cor = false;
        } else if (raiz.dir == null) {
            if (compararNomes(personagem.getName(), raiz.personagem.getName()) > 0) {
                raiz.dir = new NoRB(personagem);
            } else if (compararNomes(personagem.getName(), raiz.esq.personagem.getName()) > 0) {
                raiz.dir = new NoRB(raiz.personagem);
                raiz.personagem = personagem;
            } else {
                raiz.dir = new NoRB(raiz.personagem);
                raiz.personagem = raiz.esq.personagem;
                raiz.esq.personagem = personagem;
            }
        } else {
            inserir(personagem, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    public boolean pesquisar(String nome) {
        System.out.print(nome + " => ");
        System.out.print("raiz ");
        return pesquisar(raiz, nome);
    }

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
        return nome1.compareTo(nome2); // Compara as strings alfabeticamente
    }

    private void inserir(Personagem personagem, NoRB bisAvo, NoRB avo, NoRB pai, NoRB i) {
        if (i == null) {
            if (compararNomes(personagem.getName(), pai.personagem.getName()) < 0) {
                i = pai.esq = new NoRB(personagem);
            } else {
                i = pai.dir = new NoRB(personagem);
            }

            if (pai.cor == true) {
                balancear(bisAvo, avo, pai, i);
            }
        } else {
            is4No(bisAvo, avo, pai, i);

            if (compararNomes(personagem.getName(), i.personagem.getName()) < 0) {
                inserir(personagem, avo, pai, i, i.esq);
            } else if (compararNomes(personagem.getName(), i.personagem.getName()) > 0) {
                inserir(personagem, avo, pai, i, i.dir);
            } else {
                System.out.println("Erro, elemento repetido");
            }
        }
    }

    private void is4No(NoRB bisAvo, NoRB avo, NoRB pai, NoRB i) {
        if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;

            if (i == raiz) {
                i.cor = false;
            } else if (pai.cor == true) {
                balancear(bisAvo, avo, pai, i);
            }
        }
    }

    private boolean pesquisar(NoRB no, String nome) {
        if (no == null) {
            System.out.println("NAO");
            return false;
        } else {
            int comp = compararNomes(nome, no.personagem.getName());
            if (comp == 0) {
                System.out.println("SIM");
                return true;
            } else if (comp < 0) {
                System.out.print("esq ");
                return pesquisar(no.esq, nome);
            } else {
                System.out.print("dir ");
                return pesquisar(no.dir, nome);
            }
        }
    }

    private void balancear(NoRB bisAvo, NoRB avo, NoRB pai, NoRB i) {
        if (pai.cor == true) {
            if (compararNomes(pai.personagem.getName(), avo.personagem.getName()) > 0) {
                if (compararNomes(i.personagem.getName(), pai.personagem.getName()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo.dir = rotacaoDir(pai);
                    avo = rotacaoEsq(avo);
                }
            } else {
                if (compararNomes(i.personagem.getName(), pai.personagem.getName()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo.esq = rotacaoEsq(pai);
                    avo = rotacaoDir(avo);
                }
            }
        }

        if (bisAvo == null) {
            raiz = avo;
        } else if (compararNomes(avo.personagem.getName(), bisAvo.personagem.getName()) < 0) {
            bisAvo.esq = avo;
        } else {
            bisAvo.dir = avo;
        }

        avo.cor = false;

        avo.esq.cor = avo.dir.cor = true;
    }

    private NoRB rotacaoDir(NoRB i) {
        NoRB tmp = i.esq;
        i.esq = tmp.dir;
        tmp.dir = i;

        return tmp;
    }

    private NoRB rotacaoEsq(NoRB i) {
        NoRB tmp = i.dir;
        i.dir = tmp.esq;
        tmp.esq = i;

        return tmp;
    }
}

// -------------------------------------------------------------------//

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
        ArvoreRB personagens = new ArvoreRB();

        String ID = sc.nextLine();

        // while (sc.hasNextLine()) {
        while (ID.equals("FIM") != true) {
            personagens.inserir(ler(ID));
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