package ihm.Thread;

import ihm.Components.MainFrame;
import model.terrain.Fourmiliere;

public class SimulationThread extends Thread {

    private MainFrame mainFrame;

    public SimulationThread(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        while (true) {
            // Traitement
            mainFrame.getFourmiliere().evolue();
            mainFrame.updateAntHill();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // Très important de réinterrompre
                break; // Sortie de la boucle infinie
            }
        }
    }
}