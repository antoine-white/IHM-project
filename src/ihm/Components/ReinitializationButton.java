package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ReinitializationButton extends JButton {

    public ReinitializationButton() {
        //super("Reinitialisation du jeu ");
        super(); 
        ImageIcon img = new ImageIcon("ihm/Resources/initialization.png");
        Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon tmp = new ImageIcon( newimg );
        this.setIcon(tmp); 
        this.setContentAreaFilled(false);
        //this.setPreferredSize(new Dimension(75,50));
        //this.setBorder(BorderFactory.createEmptyBorder());
    }

    

}




