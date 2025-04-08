package Model;

import java.time.LocalDateTime;

public class Angajat extends Persoana {
    private double salariu;
    private String rolAngajat;
    private LocalDateTime oraCheckIn;
    private LocalDateTime oraCheckOut;
    private int persoaneServiteAzi;
    private int persoaneServiteInTotal;
    private int clientiServiti;

    // Constructor pentru Angajat
    public Angajat(String nume, int varsta, double salariu, String rolAngajat) {
        super(nume, varsta);
        this.salariu = salariu;
        this.rolAngajat = rolAngajat;
    }

    // Getteri și setteri
    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public String getRolAngajat() {
        return rolAngajat;
    }

    public void setRolAngajat(String rolAngajat) {
        this.rolAngajat = rolAngajat;
    }

    public LocalDateTime getOraCheckIn() {
        return oraCheckIn;
    }

    public LocalDateTime getOraCheckOut() {
        return oraCheckOut;
    }

    public void setOraCheckOut() {
        this.oraCheckOut = LocalDateTime.now();
    }

    public int getPersoaneServiteAzi() {
        return persoaneServiteAzi;
    }

    public int getPersoaneServiteInTotal() {
        return persoaneServiteInTotal;
    }

    public int getClientiServiti() {
        return clientiServiti;
    }

    public String getRol() {
        return rolAngajat; // Folosim rolAngajat în loc de rol
    }

    // Metodă pentru check-in
    public void checkIn() {
        this.persoaneServiteAzi = 0;
        this.oraCheckIn = LocalDateTime.now();
        System.out.println("Angajatul " + getNume() + " a făcut check-in la ora " + oraCheckIn);
    }

    // Metodă pentru check-out
    public void checkOut() {
        this.oraCheckOut = LocalDateTime.now();
        System.out.println("Angajatul " + getNume() + " a făcut check-out la ora " + oraCheckOut);
    }

    // Metodă care incrementează numărul de clienți serviți
    public void servesteClient() {
        clientiServiti++;
        System.out.println("Angajatul " + getNume() + " a servit un client. Total clienți serviți: " + clientiServiti);
    }

    // Implementarea metodei abstracte din Persoana
    @Override
    public void afiseazaDetalii() {
        System.out.println("Angajatul " + getNume() + " | ID: " + getId() + " | Vârsta: " + getVarsta() +
                " | Salariu: " + salariu + " | Rol: " + rolAngajat +
                " | Persoane servite azi: " + persoaneServiteAzi +
                " | Persoane servite în total: " + persoaneServiteInTotal);
    }
}