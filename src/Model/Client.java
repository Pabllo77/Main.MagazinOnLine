package Model;

import java.util.ArrayList;
import java.util.List;


public class Client extends Persoana {
    private static int idCounter = 0;
    private int id;
    private List<String> cosCumparaturi;
    private double sumaTotalaCheltuita;
    private double buget;
    private int puncteBonus;

    //Constructor pt Client
    public Client(String nume, int varsta, double buget, int puncteBonus) {
        super(nume, varsta);
        this.id = ++idCounter;
        this.cosCumparaturi = new ArrayList<>();
        this.sumaTotalaCheltuita = 0;
        this.buget = buget;
        this.puncteBonus = puncteBonus;
    }
    public int getId() {
        return id;
    }

    public double getBuget() {
        return buget;
    }
    public void setBuget(double buget){
        this.buget = buget;
    }
    public int getPuncteBonus(){
        return puncteBonus;
    }
    public void setPuncteBonus(int puncteBonus){
        this.puncteBonus = puncteBonus;
    }
    public void folosestePuncteBonus(int puncte){
        if (puncte <= this.puncteBonus){
            this.puncteBonus -= puncte;
        }
        else {
            System.out.println("Nu ai suficiente puncte bonus!");
        }
    }

    //Metoda de a adauga in cos produse
    public void adaugaInCos(String produs, double pret) {
        if (buget >= pret) {
            cosCumparaturi.add(produs);
            sumaTotalaCheltuita += pret;
            buget -= pret;
            System.out.println(getNume() + "a adaugat in cos:" + produs + " (+" + pret + "lei)");
        } else {
            System.out.println("Fonduri insuficiente");
        }
    }
    //Getter pt cos de cumparaturi
    public List<String> getCosCumparaturi() {
        return cosCumparaturi;
    }

    //Getter pt suma cheltuita
    public double getSumaTotalaCheltuita(){
        return sumaTotalaCheltuita;
    }
    public void plateste() {
        if (cosCumparaturi.isEmpty()) {
            System.out.println(getNume() + "Cosul de cumparaturi este gol.");
        } else {
            System.out.println(getNume() + "a platit " + sumaTotalaCheltuita + "lei" + cosCumparaturi);
            cosCumparaturi.clear();
            sumaTotalaCheltuita = 0;
        }
    }
    @Override
    public void afiseazaDetalii(){
        System.out.println("Clientul "+ getNume()+ "| ID: "+getId()+"| Varsta: "+getVarsta()+"| Buget: "+ getBuget()+"lei"+"Puncte bonus: "+getPuncteBonus()+"|Cos de cumparaturi: "+getCosCumparaturi()+"| Suma totala cheltuita: "+getSumaTotalaCheltuita());
    }
}

