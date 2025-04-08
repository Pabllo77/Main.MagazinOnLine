import gui.MainFrame;
import javax.swing.SwingUtilities;
import Model.Magazin;

public class Main {
    public static void main(String[] args) {
        // Crează un obiect magazin cu date reale
        Magazin magazin = new Magazin("Magazinul Meu", "Descrierea magazinului");

        // Rulează MainFrame și trimite obiectul magazin constructorului
        SwingUtilities.invokeLater(() -> new MainFrame(magazin));
    }
}