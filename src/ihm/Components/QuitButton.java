package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class QuitButton extends JButton {

    public QuitButton() {
        super(); 
        ImageIcon img = new ImageIcon("ihm/Resources/quit_logo.jpg");
        //ImageIcon img = new ImageIcon("C:/Users/RKoub/Desktop/wallpaper/13694.jpg");
        Image newimg = img.getImage().getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon tmp = new ImageIcon( newimg );
        this.setIcon(tmp); 
        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(50,50));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tmp = JOptionPane.showConfirmDialog(null, "Voulez vous quitter ? ", null, JOptionPane.YES_NO_OPTION);
                if (tmp == 0) 
                    System.exit(0);
            }
        });
        validate();
       
    }

    

}