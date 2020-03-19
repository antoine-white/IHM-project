package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RandomInitializationButton extends CustomButton {

    public RandomInitializationButton(MainFrame mainFrame) {
        // super("Reinitialisation aleatoire ");
        super();
        ImageIcon img = new ImageIcon("ihm/Resources/dice.png");
        Image newimg = img.getImage().getScaledInstance(85, 70, java.awt.Image.SCALE_SMOOTH);
        ImageIcon tmp = new ImageIcon(newimg);
        this.setIcon(tmp);
        this.setContentAreaFilled(false);
        this.addActionListener(new ActionListener() {
            private static final float ERROR = -1.0f;
            @Override
            public void actionPerformed(ActionEvent e) {
                float seed;
                float ant;
                float wall;
                seed = this.checkInput(JOptionPane.showInputDialog("Probabilité d'une graine", 0.5f));
                if (seed == ERROR) return;
                ant = this.checkInput(JOptionPane.showInputDialog("Probabilité d'une fourmie", 0.1f));
                if (ant == ERROR) return;
                wall = this.checkInput(JOptionPane.showInputDialog("Probabilité d'un mur", 0.2f));
                if (wall == ERROR) return;
                mainFrame.newRandomField(seed, ant, wall);
            }

            private float checkInput(String inputStr){                
                float toReturn = 0f;
                try {
                    toReturn = Float.parseFloat(inputStr);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Nombre flottant attendu", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return ERROR;
                }
                if (toReturn >= 0 && toReturn <= 1) {
                    return toReturn;
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre flottant entre 0.0 et 1.0 attendu", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return ERROR;                    
                }
            }
        });
    }


}