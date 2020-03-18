package ihm.Components;

import javax.swing.*;
import java.awt.*;

import model.terrain.*;
import ihm.Components.*;
import ihm.consts.*;

public class MainFrame extends JFrame{

    private JLabel title;
    private Field field;
    //private JPanel panel; 
    private Fourmiliere fourmiliere;
    private static final String titleStr = "Simulation de fourmilliere";
    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        f.pack();
        f.setLocationRelativeTo(null);   
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
    public MainFrame(){
        super();
        //TEMP :
        Fourmiliere f = new Fourmiliere(100, 100);
        // On crée quelques murs
        for (int i = 1; i < 9; i++)
            f.setMur(i, 2 * i, true);
        // On ajoute 3 fourmis dans la fourmilière
        f.ajouteFourmi(1, 1);
        f.ajouteFourmi(2, 2);
        f.ajouteFourmi(3, 3);
        // On pose des graines
        for (int i = 0; i < 30; i++) {
            f.setQteGraines(2 * i, i, 1);
            f.setQteGraines(2 * i, 90 - i, 1);
        }
        this.fourmiliere = f;
        //////////////////////////////////
        
        /*
        this.panel = new JPanel(); 
        this.panel.setBackground(ConstColors.BACKGROUND);
        this.panel.setForeground(ConstColors.TEXT);*/
        this.setBackground(ConstColors.BACKGROUND);
        this.setForeground(ConstColors.TEXT);
        this.title = new JLabel(MainFrame.titleStr);
        this.title.setFont(ConstFonts.TITLE);
        this.field = new Field(this);
        this.createMainLayout(); 
        this.frameParameter();
        this.setFrameIcon();
        this.setTitle("Logiciel Mehdi-Antoine");        
        //this.setIconImages(new ImageIcon("ihm/Resources/fourmi_logo.png").getImage());
        //this.createMainLayout(this.panel);
        //this.add(panel);
        
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
        box.add(new MagnifyingGlassButton());
        box.add(Box.createVerticalStrut(5));
        box.add(new JLabel("    Loupe")); 
        box.add(Box.createVerticalStrut(10));
        box.add(new PlayPauseButton());
        box.add(Box.createVerticalStrut(10));
        box.add(new LabelList());
        return box;
    }
    private Box centralBox(Box b1, Field field ,Box b2){
        Box box = Box.createHorizontalBox();
        //box.add(Box.createHorizontalStrut(20));
        box.add(Box.createHorizontalGlue());
        box.add(b1);
        box.add(Box.createHorizontalStrut(5));
        box.add(field);
        box.add(Box.createHorizontalGlue());
        box.add(b2);
        return box;
    }    

    public void cleanField(){
        this.fourmiliere = new Fourmiliere(this.fourmiliere.getLargeur(), this.fourmiliere.getHauteur());
        this.field.repaint();
    }

    public void newRandomField(float seed,float ant, float wall){
        //we start by emptying the anthill
        this.cleanField();
        // then we fill the field according to the given probabilities
        for (int i = 0; i < this.fourmiliere.getLargeur(); i++) {
            for (int j = 0; j < this.fourmiliere.getHauteur(); j++) {                
                this.fourmiliere.setMur(i, j, ((float)(Math.random()) <= wall));
                this.fourmiliere.setQteGraines(i, j, ((float)(Math.random()) <= seed)?1:0);
                if ((float)(Math.random()) <= ant) {
                    this.fourmiliere.ajouteFourmi(i, j);
                }
            }
        }
    }

    public Fourmiliere getFourmiliere() {
        return fourmiliere;
    }
}