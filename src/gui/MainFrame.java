package gui;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gui.ClientiFrame;
import gui.ProduseFrame;
import gui.MagazinFrame;
import gui.AngajatiFrame;
import gui.CosCumparaturiFrame; // Dacă folosești această clasă
import Model.Magazin;
import Model.Client;
import Model.Angajat;
import Model.Produse;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private Magazin magazin;

    // Constructor care primește un obiect Magazin
    public MainFrame(Magazin magazin) {
        this.magazin = magazin;

        setTitle("Management Magazin");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        JButton clientiButton = createButton("Gestionare Clienți", e -> new ClientiFrame(magazin).setVisible(true));
        JButton produseButton = createButton("Gestionare Produse", e -> new ProduseFrame(magazin).setVisible(true));
        JButton magazinButton = createButton("Gestionare Magazin", e -> new MagazinFrame(magazin).setVisible(true));
        JButton angajatiButton = createButton("Gestionare Angajați", e -> new AngajatiFrame(magazin).setVisible(true));
        JButton cosButton = createButton("Coș de Cumpărături", e -> deschideCosCumparaturi());
        JButton exitButton = createButton("Ieșire", e -> System.exit(0));

        add(clientiButton);
        add(produseButton);
        add(magazinButton);
        add(angajatiButton);
        add(cosButton);
        add(exitButton);

        setVisible(true);
    }

    // Metoda pentru a crea un buton
    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void deschideCosCumparaturi() {
        List<Client> clienti = magazin.getClienti();
        if (clienti.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu există clienți înregistrați");
            return;
        }

        // Creăm un Array cu numele clienților
        String[] numeClienti = clienti.stream().map(Client::getNume).toArray(String[]::new);

        String numeSelectat = (String) JOptionPane.showInputDialog(
                this,
                "Selectați un client: ",
                "Alege client: ",
                JOptionPane.QUESTION_MESSAGE,
                null,
                numeClienti,
                numeClienti[0]
        );

        if (numeSelectat != null) {
            Client clientSelectat = clienti.stream()
                    .filter(client -> client.getNume().equals(numeSelectat))
                    .findFirst()
                    .orElse(null);
            if (clientSelectat != null) {
                new CosCumparaturiFrame(magazin, clientSelectat).setVisible(true);
            }
        }
    }

    // Punctul de intrare al aplicației
    public static void main(String[] args) {
        // Crează un obiect magazin cu date reale
        Magazin magazin = new Magazin("Magazinul Meu", "Descrierea magazinului");
        new MainFrame(magazin);

        // Lansează fereastra principală
        new MainFrame(magazin);
    }
}