package gui;

import Model.Magazin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MagazinFrame extends JFrame {
    private Magazin magazin;
    private JLabel statusLabel;

    public MagazinFrame(Magazin magazin) {
        this.magazin = magazin; // Inițializare cu magazinul primit
        setTitle("Gestiune Magazin");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        statusLabel = new JLabel("Status: Magazin inchis");
        add(statusLabel);

        JButton deschideMagazinButton = new JButton("Deschide Magazin");
        JButton inchideMagazinButton = new JButton("Inchide Magazin");
        JButton adaugaClientButton = new JButton("Adauga Client");
        JButton afisazaClientiButton = new JButton("Afiseaza Clienti");
        JButton selecteazaClientButton = new JButton("Selecteaza Client");
        JButton stergeClientButton = new JButton("Sterge Client");
        JButton adaugaProdusButton = new JButton("Adauga Produs");
        JButton afisazaProduseButton = new JButton("Afiseaza Produse");
        JButton cumparaProdusButton = new JButton("Cumpara Produs");
        JButton afiseazaCosButton = new JButton("Afiseaza Cos");
        JButton platesteButton = new JButton("Plateste");
        JButton iesireButton = new JButton("Iesire");

        add(deschideMagazinButton);
        add(inchideMagazinButton);
        add(adaugaClientButton);
        add(afisazaClientiButton);
        add(selecteazaClientButton);
        add(stergeClientButton);
        add(adaugaProdusButton);
        add(afisazaProduseButton);
        add(cumparaProdusButton);
        add(afiseazaCosButton);
        add(platesteButton);
        add(iesireButton);

        deschideMagazinButton.addActionListener(e -> deschideMagazin());
        inchideMagazinButton.addActionListener(e -> inchideMagazin());
        adaugaClientButton.addActionListener(e -> adaugaClient());
        afisazaClientiButton.addActionListener(e -> afiseazaClienti());
        selecteazaClientButton.addActionListener(e -> selecteazaClient());
        stergeClientButton.addActionListener(e -> stergeClient());
        adaugaProdusButton.addActionListener(e -> adaugaProdus());
        afisazaProduseButton.addActionListener(e -> afiseazaProduse());
        cumparaProdusButton.addActionListener(e -> cumparaProdus());
        afiseazaCosButton.addActionListener(e -> afiseazaCos());
        platesteButton.addActionListener(e -> plateste());
        iesireButton.addActionListener(e -> iesire());

        setVisible(true);
    }

    private void deschideMagazin() {
        magazin.deschideMagazinul();
        statusLabel.setText("Status: Magazin deschis");
    }

    private void inchideMagazin() {
        magazin.inchideMagazinul();
        statusLabel.setText("Status: Magazin inchis");
    }

    private void adaugaClient() {
        JOptionPane.showMessageDialog(this, "Functie in curs de implementare: Adauga Client");
    }

    private void afiseazaClienti() {
        JOptionPane.showMessageDialog(this, "Functie in curs de implementare: Afiseaza Clienti");
    }

    private void selecteazaClient() {
        JOptionPane.showMessageDialog(this, "Functie in curs de implementare: Selecteaza Client");
    }

    private void stergeClient() {
        JOptionPane.showMessageDialog(this, "Functie in curs de implementare: Sterge Client");
    }

    private void adaugaProdus() {
        JOptionPane.showMessageDialog(this, "Functie in curs de implementare: Adauga Produs");
    }

    private void afiseazaProduse() {
        JOptionPane.showMessageDialog(this, "Functie in curs de implementare: Afiseaza Produse");
    }

    private void cumparaProdus() {
        JOptionPane.showMessageDialog(this, "Functie in curs de implementare: Cumpara Produs");
    }

    private void afiseazaCos() {
        JOptionPane.showMessageDialog(this, "Functie in curs de implementare: Afiseaza Cos");
    }

    private void plateste() {
        JOptionPane.showMessageDialog(this, "Functie in curs de implementare: Plateste");
    }

    private void iesire() {
        System.exit(0);
    }
}
