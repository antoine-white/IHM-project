package UI.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RandomInitializationButton extends JButton {

    public RandomInitializationButton() {
       //super("Reinitialisation aleatoire ");
        super(); 
        ImageIcon img = new ImageIcon("UI/Resources/dice.png");
        Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon tmp = new ImageIcon( newimg );
        this.setIcon(tmp); 
        this.setContentAreaFilled(false);
    } 

    

}