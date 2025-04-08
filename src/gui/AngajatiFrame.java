package gui;

import Model.Angajat;
import Model.Magazin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AngajatiFrame extends JFrame {
    private Magazin magazin;
    private DefaultListModel<String> modelListaAngajati;
    private JList<String> listaAngajati;
    private JTextField numeField, varstaField, salariuField, rolField;

    public AngajatiFrame(Magazin magazin) {
        this.magazin = magazin;
        setTitle("Gestionare Angajați");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelListaAngajati = new DefaultListModel<>();
        listaAngajati = new JList<>(modelListaAngajati);
        incarcaListaAngajati();

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.add(new JLabel("Nume:"));
        numeField = new JTextField();
        inputPanel.add(numeField);

        inputPanel.add(new JLabel("Vârstă:"));
        varstaField = new JTextField();
        inputPanel.add(varstaField);

        inputPanel.add(new JLabel("Salariu:"));
        salariuField = new JTextField();
        inputPanel.add(salariuField);

        inputPanel.add(new JLabel("Rol:"));
        rolField = new JTextField();
        inputPanel.add(rolField);

        JButton adaugaButton = new JButton("Adaugă Angajat");
        JButton stergeButton = new JButton("Șterge Angajat");
        JButton checkInButton = new JButton("Check-in");
        JButton checkOutButton = new JButton("Check-out");
        JButton servireButton = new JButton("Servește Client");

        adaugaButton.addActionListener(e -> adaugaAngajat());
        stergeButton.addActionListener(e -> stergeAngajat());
        checkInButton.addActionListener(e -> checkInAngajat());
        checkOutButton.addActionListener(e -> checkOutAngajat());
        servireButton.addActionListener(e -> servireClient());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        buttonPanel.add(adaugaButton);
        buttonPanel.add(stergeButton);
        buttonPanel.add(checkInButton);
        buttonPanel.add(checkOutButton);
        buttonPanel.add(servireButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(listaAngajati), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void incarcaListaAngajati() {
        modelListaAngajati.clear();
        for (Angajat angajat : magazin.getAngajati()) {
            modelListaAngajati.addElement(angajat.getNume() + " - " + angajat.getRolAngajat() + " - Serviți: " + angajat.getClientiServiti());
        }
    }

    private void adaugaAngajat() {
        String nume = numeField.getText().trim();
        String varstaText = varstaField.getText().trim();
        String salariuText = salariuField.getText().trim();
        String rol = rolField.getText().trim();

        if (nume.isEmpty() || varstaText.isEmpty() || salariuText.isEmpty() || rol.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completați toate câmpurile.");
            return;
        }
        try {
            int varsta = Integer.parseInt(varstaText);
            double salariu = Double.parseDouble(salariuText);
            Angajat angajat = new Angajat(nume, varsta, salariu, rol);
            magazin.adaugaAngajat(angajat);
            incarcaListaAngajati();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Introduceți valori numerice valide pentru vârstă și salariu!");
        }
    }

    private void stergeAngajat() {
        int indexSelectat = listaAngajati.getSelectedIndex();
        if (indexSelectat == -1) {
            JOptionPane.showMessageDialog(this, "Selectați un angajat pentru a-l șterge.");
            return;
        }
        List<Angajat> angajati = new ArrayList<>(magazin.getAngajati());
        magazin.eliminaAngajat(angajati.get(indexSelectat));
        incarcaListaAngajati();
    }

    private void checkInAngajat() {
        int indexSelectat = listaAngajati.getSelectedIndex();
        if (indexSelectat == -1) {
            JOptionPane.showMessageDialog(this, "Selectați un angajat pentru check-in.");
            return;
        }
        Angajat angajatSelectat = magazin.getAngajati().get(indexSelectat);
        angajatSelectat.checkIn();
        JOptionPane.showMessageDialog(this, "Angajatul a făcut check-in la ora: " + angajatSelectat.getOraCheckIn());
        incarcaListaAngajati();
    }

    private void checkOutAngajat() {
        int indexSelectat = listaAngajati.getSelectedIndex();
        if (indexSelectat == -1) {
            JOptionPane.showMessageDialog(this, "Selectați un angajat pentru check-out.");
            return;
        }
        Angajat angajatSelectat = magazin.getAngajati().get(indexSelectat);
        angajatSelectat.checkOut();
        JOptionPane.showMessageDialog(this, "Angajatul a făcut check-out la ora: " + angajatSelectat.getOraCheckOut());
        incarcaListaAngajati();
    }

    private void servireClient() {
        int indexSelectat = listaAngajati.getSelectedIndex();
        if (indexSelectat == -1) {
            JOptionPane.showMessageDialog(this, "Selectați un angajat pentru servirea clientului.");
            return;
        }
        Angajat angajatSelectat = magazin.getAngajati().get(indexSelectat);
        angajatSelectat.servesteClient();
        JOptionPane.showMessageDialog(this, "Angajatul a servit un client.");
        incarcaListaAngajati();
    }
}