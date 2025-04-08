package Model;

import java.util.List;
import java.util.ArrayList;

public class Magazin {
    private int id;
    private String nume;
    private String descriere;
    private boolean esteDeschis;
    private List<Client> clienti;
    private List<Produse> produse;
    private List<Angajat> angajati;
    private static int nrTotalMagazine = 0;

    public List<Produse> getProduse() {
        return produse;
    }

    // Constructor clasa Magazin
    public Magazin(String nume, String descriere) {
        this.id = ++nrTotalMagazine;
        this.nume = nume;
        this.descriere = descriere;
        this.esteDeschis = false;
        this.produse = new ArrayList<>();
        this.clienti = new ArrayList<>();
        this.angajati = new ArrayList<>();
    }

    public void adaugaProdus(Produse produs) {
        produse.add(produs);
        System.out.println("Produsul " + produs.getNume() + " a fost adăugat în magazin.");
    }

    // Eliminare produs din magazin după ID
    public void eliminaProdus(int id) {
        produse.removeIf(produs -> produs.getID() == id);
        System.out.println("Produsul cu ID-ul " + id + " a fost șters din magazin.");
    }

    // Găsirea unui produs după nume
    public Produse gasesteProdus(String nume) {
        for (Produse produs : produse) {
            if (produs.getNume().equalsIgnoreCase(nume)) {
                return produs;
            }
        }
        return null;
    }

    // Afișarea tuturor produselor din magazin
    public void afiseazaProduse() {
        if (produse.isEmpty()) {
            System.out.println("Nu sunt produse în magazin.");
        } else {
            System.out.println("Produsele disponibile în magazin: ");
            for (Produse produs : produse) {
                produs.afisazaDetalii();
            }
        }
    }

    // Adăugare client nou
    public void adaugaClient(Client client) {
        for (Client c : clienti) {
            if (c.getId() == client.getId()) {
                System.out.println("Clientul cu ID-ul " + client.getId() + " există deja în magazin.");
                return;
            }
        }
        clienti.add(client);
        System.out.println("Clientul " + client.getNume() + " a fost adăugat în magazin.");
    }

    // Ștergere client după ID
    public void stergeClient(int id) {
        clienti.removeIf(client -> client.getId() == id);
        System.out.println("Clientul cu ID-ul " + id + " a fost șters din magazin.");
    }

    // Returnează lista de clienți
    public List<Client> getClienti() {
        return clienti;
    }

    public List<Angajat> getAngajati() {
        return angajati;
    }

    public void adaugaAngajat(Angajat angajat) {
        angajati.add(angajat);
        System.out.println("Angajatul " + angajat.getNume() + " a fost adăugat.");
    }

    public void eliminaAngajat(Angajat angajat) {
        angajati.remove(angajat);
        System.out.println("Angajatul " + angajat.getNume() + " a fost șters.");
    }

    // Metoda pentru a obține ID-ul magazinului
    public int getId() {
        return id;
    }

    // Deschide magazinul
    public void deschideMagazinul() {
        esteDeschis = true;
        System.out.println("Magazinul " + nume + " a fost deschis.");
    }

    public void inchideMagazinul() {
        esteDeschis = false;
        System.out.println("Magazinul " + nume + " a fost închis.");
    }

    // Metodă care caută un client după ID
    public Client getClientById(int id) {
        for (Client client : clienti) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null; // Returnează null dacă nu găsește clientul
    }
}