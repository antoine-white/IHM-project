package UI.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MagnifyingGlassButton extends JButton {

    public MagnifyingGlassButton() {
        //super("Loupe");
        super(); 
        ImageIcon img = new ImageIcon("UI/Resources/loupe2.png");
        //ImageIcon img = new ImageIcon("C:/Users/RKoub/Desktop/wallpaper/13694.jpg");
        Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon tmp = new ImageIcon( newimg );
        this.setIcon(tmp); 
        this.setContentAreaFilled(false);
        //this.setPreferredSize(new Dimension(75,50));
        //this.setBorder(BorderFactory.createEmptyBorder());
    }

    

}