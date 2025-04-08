package Model;

public class Produse {
    private int id;
    private String nume;
    private String descriere;
    private int stoc;
    private double pret;
    private static int nrTotalProduse = 0;

    //Constructor clasa Produs
    public Produse(String nume, String descriere, int stoc, double pret) {
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
        this.stoc = stoc;
        this.id = ++nrTotalProduse;

        //Daca produsul este "Meniu" se lanseaza menuil special
        if (nume.equalsIgnoreCase("Meniu")) {
            startMeniu();
        }
    }

//Getteri si starteri pt fiecare atribut

    public int getID() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getStoc() {
        return (int) stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    //Metoda ce scade din stoc nr de produse
    public void scadeDinStoc(int cantitate) {
        if (stoc >= cantitate) {
            stoc -= cantitate;
            System.out.println("S-a scazut " + cantitate + "unitati din stocul produsului " + nume);
        } else {
            System.out.println("Stoc insuficient pentru produsul " + nume);
        }
    }

    //Metoda ce adauga in stoc
    public void adaugaInStoc() {
        stoc++;
        System.out.println("S-a adaugat o unitate in stocul produsului " + nume);
    }

    //Metoda speciala pt produsul meniu
    private void startMeniu() {
        System.out.println("Produsul " + nume + " este meniu special!");
    }

    public void afisazaDetalii() {
        System.out.println("Produs ID: " + id);
        System.out.println("Nume: " + nume);
        System.out.println("Descriere: " + descriere);
        System.out.println("Pret: " + pret + "RON");
        System.out.println("Stoc disponibil: " + stoc);
    }
}

