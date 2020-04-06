package ihm.Components;

import javax.swing.*;
import java.awt.*;

import model.terrain.*;
import ihm.Thread.SimulationThread;
import ihm.consts.*;

/**
 * mother frame of the application
 * also the entry point i.e the main method is here
 */
public class MainFrame extends JFrame {

    private boolean simulationRunning;
    private JLabel title;
    private Field field;
    private LabelList labelList;
    private Dimension currAnthillDim;
    private Fourmiliere fourmiliere;
    private MagnifyingGlassButton magnifyingGlassButton;

    private SimulationThread simulationThread;

    private static final int threadSleep = 100;//in millisecond
    private static final String TITTLE_STRING = "Simulation de fourmilliere";
    private static final Dimension DEFAULT_ANTHILL = new Dimension(100, 100);

    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }

    public MainFrame() {
        super();
        //the simulation is off at the start
        this.simulationRunning = false;
        this.currAnthillDim = MainFrame.DEFAULT_ANTHILL;
        this.fourmiliere = new Fourmiliere((int) currAnthillDim.getWidth(), (int) currAnthillDim.getHeight());
        this.setBackground(ConstColors.BACKGROUND);
        this.setForeground(ConstColors.TEXT);
        this.title = new JLabel(MainFrame.TITTLE_STRING);
        this.title.setFont(ConstFonts.TITLE);
        this.field = new Field(this);
        this.labelList = new LabelList();
        this.magnifyingGlassButton = new MagnifyingGlassButton();
        //create the layout of this component
        this.createMainLayout();
        //set sizes parameters for this component
        this.frameParameter();
        this.setFrameIcon();
        this.setTitle("Logiciel Mehdi-Antoine");
    }

    private void setFrameIcon() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image tmp = kit.getImage(ConstPaths.IMAGE_PATH + "m_logo.jpg");
        Image icone = tmp.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
        this.setIconImage(icone);
    }

    private void frameParameter() {
        this.setPreferredSize(new Dimension(800, 750));
        this.setMinimumSize(new Dimension(800, 650));
        this.setLocationRelativeTo(null);
    }

    private void createMainLayout() {
        this.add(this.titleBox(), BorderLayout.NORTH);
        this.add(this.quitBox(), BorderLayout.SOUTH);
        this.add(centralBox(leftVerticalBox(), this.field, rightVerticalBox()), BorderLayout.CENTER);
    }

    /**
     * 
     * @return a box with the title center horizontally
     */
    private Box titleBox() {
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(this.title);
        box.add(Box.createHorizontalGlue());
        return box;
    }

    /**
     * 
     * @return a box with the button to quit the application in the right corner
     */
    private Box quitBox() {
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(new QuitButton());
        return box;
    }

    /**
     * 
     * @return a box with (from top to bottom):
     * _the resize field panel
     * _the ReinitializationButton
     * _the RandomInitializationButton
     */
    private Box leftVerticalBox() {
        Box box = Box.createVerticalBox();
        box.add(new ResizePanel(this));
        box.add(Box.createVerticalStrut(5));
        box.add(new ReinitializationButton(this));
        box.add(Box.createVerticalStrut(5));
        box.add(new JLabel("Reinitialisation du jeu"));
        box.add(Box.createVerticalStrut(10));
        box.add(new RandomInitializationButton(this));
        box.add(Box.createVerticalStrut(5));
        box.add(new JLabel("Reinitialisation aleatoire "));
        return box;
    }

    private Box rightVerticalBox() {
        Box box = Box.createVerticalBox();
        box.add(this.magnifyingGlassButton);
        box.add(Box.createVerticalStrut(5));
        box.add(new JLabel("    Loupe"));
        box.add(Box.createVerticalStrut(10));
        box.add(new PlayPauseButton(this));
        box.add(Box.createVerticalStrut(10));
        box.add(this.labelList);
        return box;
    }

    private Box centralBox(Box b1, Field field, Box b2) {
        Box box = Box.createHorizontalBox();
        box.add(b1);
        box.add(Box.createHorizontalStrut(5));
        box.add(field);
        box.add(Box.createHorizontalStrut(5));
        box.add(b2);
        return box;
    }

    /**
     * create a new Fourmiliere object and redraw the field
     */
    public void cleanField() {
        this.fourmiliere = new Fourmiliere(this.fourmiliere.getLargeur(), this.fourmiliere.getHauteur());
        this.updateAntHill();
    }

    /**
     * create a new Fourmiliere object with given probabilities and redraw the field 
     * @param seed the probability of the maximum of seeds at any point in the anthill
     * @param ant the probability of an ant at any point in the anthill
     * @param wall the probability of a wall at any point in the anthill
     */
    public void newRandomField(float seed, float ant, float wall) {
        // we start by emptying the anthill
        this.cleanField();
        // then we fill the field according to the given probabilities
        for (int i = 1; i < this.fourmiliere.getLargeur(); i++) {
            for (int j = 1; j < this.fourmiliere.getHauteur(); j++) {
                this.fourmiliere.setMur(i, j, ((float) (Math.random()) <= wall));
                this.fourmiliere.setQteGraines(i, j, ((float) (Math.random()) <= seed) ? Fourmiliere.getQMax() : 0);
                if ((float) (Math.random()) <= ant) {
                    this.fourmiliere.ajouteFourmi(i, j);
                }
            }
        }
        this.updateAntHill();
    }

    /**
     * create a new Fourmiliere object with the given dimension and redraw the field
     * @param newDimension
     */
    public void resizeAntHill(Dimension newDimension) {
        this.currAnthillDim = newDimension;
        this.fourmiliere = new Fourmiliere((int) currAnthillDim.getWidth(), (int) currAnthillDim.getHeight());
        this.updateAntHill();
    }

    /**
     * redraw the field and update the information labels
     */
    public void updateAntHill() {
        this.labelList.setTextLbl(this.getNbGraine(), this.fourmiliere.getNbFourmi());
        this.field.repaint();
    }

    /**
     * 
     * @return the number of seed in the anthill
     */
    private int getNbGraine() {
        int total = 0;
        for (int i = 0; i < this.fourmiliere.getHauteur(); i++) {
            for (int j = 0; j < this.fourmiliere.getLargeur(); j++) {
                total += this.fourmiliere.getQteGraines(i, j);
            }
        }
        return total;
    }

    public Fourmiliere getFourmiliere() {
        return fourmiliere;
    }

    public MagnifyingGlassButton getMagnifyingGlassButton() {
        return this.magnifyingGlassButton;
    }

    public boolean getSimulationRunning() {
        return simulationRunning;
    }

    public void startSimulation() {
        this.simulationThread = new SimulationThread(this,this.threadSleep);
        this.simulationRunning = true;
        this.simulationThread.start();
    }

    public void endSimulation() {
        this.simulationRunning = false;
        this.simulationThread.interrupt();
    }
}