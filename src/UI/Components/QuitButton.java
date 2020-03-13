package UI.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class QuitButton extends JButton {

    public QuitButton() {
        super("Quitter");
        //super(); 
        //ImageIcon img = new ImageIcon("src/UI/Images/quitbut2.jpg");
          /*  ImageIcon img = new ImageIcon("/Resources/13694.jpg");
            //ImageIcon img = new ImageIcon("C:/Users/RKoub/Desktop/wallpaper/13694.jpg");
            Image newimg = img.getImage().getScaledInstance( 500, 500,  java.awt.Image.SCALE_SMOOTH ) ;  
            ImageIcon tmp = new ImageIcon( newimg );
            this.setIcon(tmp); 
            */
        
        //this.setPreferredSize(new Dimension(200,200));
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