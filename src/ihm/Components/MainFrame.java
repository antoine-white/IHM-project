package ihm.Components;

import javax.swing.*;

import java.awt.*;

import model.terrain.*;
import ihm.Runnable.SimulationRunner;
import ihm.consts.*;

public class MainFrame extends JFrame{

    private JLabel title;
    private Field field;
    private LabelList labelList;
    private Dimension currAnthillDim;
    private Fourmiliere fourmiliere;
    private MagnifyingGlassButton magnifyingGlassButton;

    private SimulationRunner simulationRunner;
    private Thread simulationThread;

    private static final String TITTLE_STRING = "Simulation de fourmilliere";
    private static final Dimension DEFAULT_ANTHILL = new Dimension(100,100);
    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        f.pack();
        f.setLocationRelativeTo(null);   
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
    public MainFrame(){
        super();
        this.currAnthillDim = MainFrame.DEFAULT_ANTHILL;
        this.fourmiliere = new Fourmiliere((int)currAnthillDim.getWidth(), (int)currAnthillDim.getHeight());
        this.setBackground(ConstColors.BACKGROUND);
        this.setForeground(ConstColors.TEXT);
        this.title = new JLabel(MainFrame.TITTLE_STRING);
        this.title.setFont(ConstFonts.TITLE);
        this.field = new Field(this);
        this.labelList = new LabelList();
        this.magnifyingGlassButton = new MagnifyingGlassButton();
        this.createMainLayout(); 
        this.frameParameter();
        this.setFrameIcon();
        this.setTitle("Logiciel Mehdi-Antoine");        
        //this.setIconImages(new ImageIcon("ihm/Resources/fourmi_logo.png").getImage());
        //this.createMainLayout(this.panel);
        //this.add(panel);
        this.simulationRunner = new SimulationRunner(this);
        
        this.simulationThread = new Thread(this.simulationRunner);
    }
    private void setFrameIcon(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image tmp = kit.getImage("ihm/Resources/m_logo.jpg");
        Image icone = tmp.getScaledInstance( 48, 48,  java.awt.Image.SCALE_SMOOTH ) ;  
        this.setIconImage(icone);
    }
    private void frameParameter(){
        this.setPreferredSize(new Dimension(800, 750));  
        this.setMinimumSize(new Dimension(800, 650));  
        this.setLocationRelativeTo(null);  
        //this.setResizable(false); 
    }    
    private void createMainLayout(){
        this.add(this.titleBox(),BorderLayout.NORTH);
        this.add(this.quitBox(),BorderLayout.SOUTH);
        this.add(centralBox(leftVerticalBox(),this.field,rightVerticalBox()),BorderLayout.CENTER); 
    } 
    /*
    private void createMainLayout(JPanel pan){
        pan.add(titleBox(),BorderLayout.NORTH);
        pan.add(centralBox(leftVerticalBox(),new Field(),rightVerticalBox()),BorderLayout.CENTER); 
        pan.add(quitBox(),BorderLayout.SOUTH);
    }
    */
    private Box titleBox(){
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(this.title);
        box.add(Box.createHorizontalGlue());
        return box;
    }
    private Box quitBox(){
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(new QuitButton());
        return box;
    }
    private Box leftVerticalBox(){
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
    private Box rightVerticalBox(){
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
    private Box centralBox(Box b1, Field field ,Box b2){
        Box box = Box.createHorizontalBox();
        //box.add(Box.createHorizontalStrut(20));
        box.add(b1);
        box.add(Box.createHorizontalStrut(5));
        box.add(field);
        box.add(Box.createHorizontalStrut(5));
        box.add(b2);
        return box;
    }    

    public void cleanField(){
        this.fourmiliere = new Fourmiliere(this.fourmiliere.getLargeur(), this.fourmiliere.getHauteur());
        this.updateAntHill();
    }

    public void newRandomField(float seed,float ant, float wall){
        //we start by emptying the anthill
        this.cleanField();
        // then we fill the field according to the given probabilities
        for (int i = 1; i < this.fourmiliere.getLargeur(); i++) {
            for (int j = 1; j < this.fourmiliere.getHauteur(); j++) {                
                this.fourmiliere.setMur(i, j, ((float)(Math.random()) <= wall));
                this.fourmiliere.setQteGraines(i, j, ((float)(Math.random()) <= seed)?Fourmiliere.getQMax():0);
                if ((float)(Math.random()) <= ant) {
                    this.fourmiliere.ajouteFourmi(i, j);
                }
            }
        }
        this.updateAntHill();
    }

    public void resizeAntHill(Dimension newDimension){
        this.currAnthillDim = newDimension;
        this.fourmiliere = new Fourmiliere((int)currAnthillDim.getWidth(), (int)currAnthillDim.getHeight());
        this.updateAntHill();
    }

    public void updateAntHill(){
        this.labelList.setTextLbl(this.getNbGraine(), this.fourmiliere.getNbFourmi());
        this.field.repaint();
    }

    private int getNbGraine(){
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

    public MagnifyingGlassButton getMagnifyingGlassButton(){
        return this.magnifyingGlassButton;
    }

    public void startSimulation(){        
        this.simulationThread.start();
    }

    public void endSimulation(){        
        this.simulationThread.interrupt();
    }
}