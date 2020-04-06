package ihm.Thread;

import ihm.Components.MainFrame;
import model.terrain.Fourmiliere;

/**
 * Thread use to simulate the anhill 
 */
public class SimulationThread extends Thread {

    private MainFrame mainFrame;
    private int sleepTime;

    /**
     * 
     * @param mainFrame
     * @param sleepTime in millisecond
     */
    public SimulationThread(MainFrame mainFrame, int sleepTime) {
        super();
        this.mainFrame = mainFrame;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true) {
            // processing
            mainFrame.getFourmiliere().evolue();
            mainFrame.updateAntHill();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); 
                break; // exit the infinte loop
            }
        }
    }
}