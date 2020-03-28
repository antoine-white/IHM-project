package ihm.Components;

import java.awt.*;
import javax.swing.*;

import model.terrain.Fourmiliere;

import ihm.consts.ConstPaths;

import java.awt.event.*;

public class ReinitializationButton extends CustomButton {

    public ReinitializationButton(MainFrame mainFrame) {
        //super("Reinitialisation du jeu ");
        super(); 
        ImageIcon img = new ImageIcon(ConstPaths.IMAGE_PATH + "initialization.png");
        Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon tmp = new ImageIcon( newimg );
        this.setIcon(tmp); 
        this.setContentAreaFilled(false);
        //this.setPreferredSize(new Dimension(75,50));
        //this.setBorder(BorderFactory.createEmptyBorder());
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reponse = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment réinitialiser ? ", null, JOptionPane.YES_NO_OPTION);
                if (reponse == 0) {
                    mainFrame.cleanField();
                }
            }
        });
    }

    

}




