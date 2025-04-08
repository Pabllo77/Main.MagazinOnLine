package gui;
import Model.Magazin;
import Model.Produse;
import Model.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ProduseFrame extends JFrame {
    private DefaultListModel<String> modelLista;
    private JList<String> listaProduse;
    private JTextField numeField, descriereField, pretField, stocField;
    private JButton adaugaButton, stergeButton, adaugaInCosButton;
    private Magazin magazin;

    // Constructor
    public ProduseFrame(Magazin magazin) {
        this.magazin = magazin;
        setTitle("Gestiune Produse");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelLista = new DefaultListModel<>();
        listaProduse = new JList<>(modelLista);
        incarcaListaProduse();

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        inputPanel.add(new JLabel("Nume:"));
        numeField = new JTextField();
        inputPanel.add(numeField);

        inputPanel.add(new JLabel("Descriere:"));
        descriereField = new JTextField();
        inputPanel.add(descriereField);

        inputPanel.add(new JLabel("Pret:"));
        pretField = new JTextField();
        inputPanel.add(pretField);

        inputPanel.add(new JLabel("Stoc:"));
        stocField = new JTextField();
        inputPanel.add(stocField);

        adaugaButton = new JButton("Adauga Produs");
        inputPanel.add(adaugaButton);

        stergeButton = new JButton("Sterge Produs");
        inputPanel.add(stergeButton);

        JButton actualizeazaButton = new JButton("Actualizează");
        inputPanel.add(actualizeazaButton);

        adaugaInCosButton = new JButton("Adaugă în Coș");
        inputPanel.add(adaugaInCosButton);

        actualizeazaButton.addActionListener(e -> actualizeazaProdus());
        adaugaButton.addActionListener(e -> adaugaProdus());
        stergeButton.addActionListener(e -> stergeProdus());
        adaugaInCosButton.addActionListener(e -> adaugaProdusInCos());

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(listaProduse), BorderLayout.CENTER);

        setVisible(true);
    }

    private void incarcaListaProduse() {
        modelLista.clear();
        for (Produse produs : magazin.getProduse()) {
            modelLista.addElement("ID:" + produs.getID() + "-" + produs.getNume() + "-" + produs.getPret() + "RON, Stoc:" + produs.getStoc());
        }
    }

    private void adaugaProdus() {
        String nume = numeField.getText().trim();
        String descriere = descriereField.getText().trim();
        String pretText = pretField.getText().trim();
        String stocText = stocField.getText().trim();

        if (nume.isEmpty() || descriere.isEmpty() || pretText.isEmpty() || stocText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completati toate campurile.");
            return;
        }
        try {
            double pret = Double.parseDouble(pretText);
            int stoc = Integer.parseInt(stocText);
            Produse produsNou = new Produse(nume, descriere, stoc, pret);
            magazin.adaugaProdus(produsNou);
            incarcaListaProduse();

            numeField.setText("");
            descriereField.setText("");
            pretField.setText("");
            stocField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Introduceti un numar valid pentru pret si stoc!");
        }
    }

    private void stergeProdus() {
        int indexSelectat = listaProduse.getSelectedIndex();
        if (indexSelectat == -1) {
            JOptionPane.showMessageDialog(this, "Selectați un produs din listă pentru a-l șterge.");
            return;
        }
        Produse produs = new ArrayList<>(magazin.getProduse()).get(indexSelectat);
        magazin.eliminaProdus(produs.getID());
        incarcaListaProduse();
    }

    private void actualizeazaProdus() {
        int indexSelectat = listaProduse.getSelectedIndex();
        if (indexSelectat == -1) {
            JOptionPane.showMessageDialog(this, "Selectați un produs din listă pentru a-l actualiza.");
            return;
        }
        java.util.List<Produse> listaProduse = new ArrayList<>(magazin.getProduse());
        Produse produsSelectat = listaProduse.get(indexSelectat);
        String numeNou = numeField.getText().trim();
        String descriereNoua = descriereField.getText().trim();
        String pretText = pretField.getText().trim();
        String stocText = stocField.getText().trim();

        if (numeNou.isEmpty() || descriereNoua.isEmpty() || pretText.isEmpty() || stocText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completați toate câmpurile pentru actualizare.");
            return;
        }
        try {
            double pretNou = Double.parseDouble(pretText);
            int stocNou = Integer.parseInt(stocText);
            produsSelectat.setNume(numeNou);
            produsSelectat.setDescriere(descriereNoua);
            produsSelectat.setPret(pretNou);
            produsSelectat.setStoc(stocNou);
            incarcaListaProduse();
            JOptionPane.showMessageDialog(this, "Produs actualizat cu succes!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Introduceți un număr valid pentru preț și stoc!");
        }
    }

    private void adaugaProdusInCos() {
        int indexSelectat = listaProduse.getSelectedIndex();
        if (indexSelectat == -1) {
            JOptionPane.showMessageDialog(this, "Selectați un produs din listă pentru a-l adăuga în coș.");
            return;
        }
        List<Client> clienti = magazin.getClienti();
        if (clienti.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu există clienți înregistrați.");
            return;
        }
        String[] numeClienti = clienti.stream().map(Client::getNume).toArray(String[]::new);
        String numeSelectat = (String) JOptionPane.showInputDialog(this, "Selectați un client:", "Alege client", JOptionPane.QUESTION_MESSAGE, null, numeClienti, numeClienti[0]);
        if (numeSelectat != null) {
            Client clientSelectat = clienti.stream().filter(client -> client.getNume().equals(numeSelectat)).findFirst().orElse(null);
            Produse produs = new ArrayList<>(magazin.getProduse()).get(indexSelectat);
            clientSelectat.adaugaInCos(produs.getNume(), produs.getPret());
            JOptionPane.showMessageDialog(this, "Produs adăugat în coș!");
        }
    }
}
