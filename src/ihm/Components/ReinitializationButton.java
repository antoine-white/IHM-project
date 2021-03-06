package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import model.terrain.Fourmiliere;
import ihm.consts.ConstPaths;

/**
 * the button to clear the field
 */
public class ReinitializationButton extends CustomButton {

    public ReinitializationButton(MainFrame mainFrame) {
        super(); 
        ImageIcon img = new ImageIcon(ConstPaths.IMAGE_PATH + "initialization.png");
        Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon tmp = new ImageIcon( newimg );
        this.setIcon(tmp); 
        this.setContentAreaFilled(false);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                        
                // only perfomes an action if the simulation is not running                 
                if (mainFrame.getSimulationRunning()) return;
                int reponse = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment réinitialiser ? ", null, JOptionPane.YES_NO_OPTION);
                if (reponse == 0) {
                    mainFrame.cleanField();
                }
            }
        });
    }
}




