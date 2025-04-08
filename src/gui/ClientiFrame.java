package gui;
import Model.Magazin;
import Model.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;


public class ClientiFrame extends JFrame {
    private DefaultListModel<String> clientListModel;
    private JList<String> clientList;
    private JTextField numeField, varstaField, bugetField, puncteBonusField;
    private Magazin magazin;


    public ClientiFrame(Magazin magazin) {
        this.magazin = magazin;
        setTitle("Gestionare Clienti");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        clientListModel = new DefaultListModel<>();
        clientList = new JList<>(clientListModel);
        add(new JScrollPane(clientList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nume:"));
        numeField = new JTextField();
        inputPanel.add(numeField);
        inputPanel.add(new JLabel("Varsta:"));
        varstaField = new JTextField();
        inputPanel.add(varstaField);
        inputPanel.add(new JLabel("Buget:"));
        bugetField = new JTextField();
        inputPanel.add(bugetField);
        inputPanel.add(new JLabel("Puncte Bonus:"));
        puncteBonusField = new JTextField();
        inputPanel.add(puncteBonusField);

        JButton addButton = new JButton("Adauga Client");
        JButton deleteButton = new JButton("Sterge Client");
        JButton updateBugetButton = new JButton("Modifica Buget");
        JButton usePointsButton = new JButton("Utilizeaza Puncte Bonus");

        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(updateBugetButton);
        inputPanel.add(usePointsButton);
        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> adaugaClient());
        deleteButton.addActionListener(e -> stergeClient());
        updateBugetButton.addActionListener(e -> modificaBuget()); // Adăugat eveniment
        usePointsButton.addActionListener(e -> folosestePuncteBonus()); // Adăugat eveniment
        afiseazaClienti();
    }

    private void adaugaClient() {
        String nume = numeField.getText().trim();
        String varstaText = varstaField.getText().trim();
        String bugetText = bugetField.getText().trim();
        String puncteText = puncteBonusField.getText().trim();

        if (nume.isEmpty() || varstaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduceti toate datele!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int varsta = Integer.parseInt(varstaText);
            double buget = Double.parseDouble(bugetText);
            int puncte = Integer.parseInt(puncteText);
            Client clientNou = new Client(nume, varsta, buget, puncte);
            magazin.adaugaClient(clientNou);

            clientListModel.addElement(clientNou.getId() + ":" + clientNou.getNume() + "-" + clientNou.getVarsta());

            numeField.setText("");
            varstaField.setText("");
            bugetField.setText("");
            puncteBonusField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Varsta trebuie sa fie un numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void stergeClient() {
        int selectedIndex = clientList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedClient = clientListModel.getElementAt(selectedIndex);
            int idClient = Integer.parseInt(selectedClient.split(":")[0].trim());

            magazin.stergeClient(idClient);
           clientListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Selectați un client pentru a-l șterge.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void modificaBuget() {
        int selectedIndex = clientList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedClient = clientListModel.getElementAt(selectedIndex);
            int idClient = Integer.parseInt(selectedClient.split(":")[0].trim());
            Client client = magazin.getClientById(idClient);

            if (client != null) {
                String nouBuget = JOptionPane.showInputDialog("Introduceti nou buget:", client.getBuget());
                try {
                    double buget = Double.parseDouble(nouBuget);
                    client.setBuget(buget);
                    afiseazaClienti();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Bugetul trebuie sa fie un numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
                }
            else {
                JOptionPane.showMessageDialog(this, "Selectati un client pentru a modifica bugetul", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void folosestePuncteBonus() {
        int selectedIndex = clientList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedClient = clientListModel.getElementAt(selectedIndex);
            int idClient = Integer.parseInt(selectedClient.split(":")[0].trim());
            Client client = magazin.getClientById(idClient);

            if (client != null) {
                String puncteText = JOptionPane.showInputDialog(this, "Introduceți numărul de puncte de utilizat:", client.getPuncteBonus());
                try {
                    int puncte = Integer.parseInt(puncteText);
                    if (puncte <= client.getPuncteBonus()) {
                        client.folosestePuncteBonus(puncte);
                        afiseazaClienti();
                    } else {
                        JOptionPane.showMessageDialog(this, "Nu aveți suficiente puncte!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Introduceți un număr valid de puncte!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selectați un client pentru a utiliza punctele bonus.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

        private void afiseazaClienti () {
            clientListModel.clear();
            if (magazin == null) {
                System.out.println("Nu sunt clienti in magazin.");
                return;
            }
            List<Client> clienti = magazin.getClienti();
            for (Client c : clienti) {
                clientListModel.addElement(c.getId() + ":" + c.getNume() + "-" + c.getVarsta());
            }
        }
    }