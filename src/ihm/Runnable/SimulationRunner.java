package ihm.Runnable;

import ihm.Components.MainFrame;
import model.terrain.Fourmiliere;

public class SimulationRunner implements Runnable {

    private MainFrame mainFrame;

    public SimulationRunner(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    public void run() {
        while (!Thread.interrupted()) {            
            //Fourmiliere f= new Fourmiliere(20, 20);
            //f.evolue();
            try {
                mainFrame.getFourmiliere().evolue();
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
            mainFrame.updateAntHill();
        }
    }
}