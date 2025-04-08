package gui;

import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Magazin;
import Model.Client;

public class CosCumparaturiFrame extends JFrame {
    private Magazin magazin;
    private JList<String> cosList;
    private DefaultListModel<String> cosModel;
    private JButton btnElimina, btnFinalizeaza;
    private Client client;

    public CosCumparaturiFrame(Magazin magazin, Client client) {
        this.magazin = magazin;
        this.client = client;

        setTitle("Coș de Cumpărături");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Model pentru lista
        cosModel = new DefaultListModel<>();
        cosList = new JList<>(cosModel);
        cosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(cosList);

        // Populăm lista cu produsele din coș
        actualizeazaCos();

        // Butoane
        btnElimina = new JButton("Elimină produs");
        btnFinalizeaza = new JButton("Finalizează achiziția");

        // Panel pentru butoane
        JPanel panelButoane = new JPanel();
        panelButoane.add(btnElimina);
        panelButoane.add(btnFinalizeaza);

        // Adăugare componente în fereastră
        add(scrollPane, BorderLayout.CENTER);
        add(panelButoane, BorderLayout.SOUTH);

        // Listeners
        btnElimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaProdusDinCos();
            }
        });

        btnFinalizeaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizeazaAchizitia();
            }
        });

        setVisible(true);
    }

    private void actualizeazaCos() {
        cosModel.clear();
        for (String produs : client.getCosCumparaturi()) {
            cosModel.addElement(produs);
        }
    }

    private void eliminaProdusDinCos() {
        int index = cosList.getSelectedIndex();
        if (index != -1) {
            String produs = cosModel.get(index);
            client.getCosCumparaturi().remove(produs);
            actualizeazaCos();
        } else {
            JOptionPane.showMessageDialog(this, "Selectează un produs pentru a-l elimina.");
        }
    }

    private void finalizeazaAchizitia() {
        if (client.getCosCumparaturi().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Coșul de cumpărături este gol!");
            return;
        }

        client.plateste(); // Metoda care resetează coșul și procesează plata
        actualizeazaCos();
        JOptionPane.showMessageDialog(this, "Achiziție finalizată cu succes!");
    }
}
