package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import ihm.consts.ConstPaths;

/**
 * button that initialize the simulation with probabilities given by the user
 * through dialog boxes
 */
public class RandomInitializationButton extends CustomButton {

    public RandomInitializationButton(MainFrame mainFrame) {
        super();
        ImageIcon img = new ImageIcon(ConstPaths.IMAGE_PATH + "dice.png");
        Image newimg = img.getImage().getScaledInstance(85, 70, java.awt.Image.SCALE_SMOOTH);
        ImageIcon tmp = new ImageIcon(newimg);
        this.setIcon(tmp);
        this.setContentAreaFilled(false);
        this.addActionListener(new ActionListener() {
            private static final float ERROR = -1.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                // only perfoms an action if the simulation is not running
                if (mainFrame.getSimulationRunning())
                    return;
                float seed;
                float ant;
                float wall;
                seed = this.checkInput(JOptionPane.showInputDialog("Probabilité d'une graine", 0.5f));
                if (seed == ERROR)
                    return;
                ant = this.checkInput(JOptionPane.showInputDialog("Probabilité d'une fourmie", 0.1f));
                if (ant == ERROR)
                    return;
                wall = this.checkInput(JOptionPane.showInputDialog("Probabilité d'un mur", 0.2f));
                if (wall == ERROR)
                    return;
                mainFrame.newRandomField(seed, ant, wall);
            }

            /**
             * 
             * @param inputStr the string given by the user
             * @return the probability through a float if the string can be converted is a
             * probability ([0.0..1.0]) returns ERROR otherwise 
             */
            private float checkInput(String inputStr) {
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
                    JOptionPane.showMessageDialog(null, "Nombre flottant entre 0.0 et 1.0 attendu", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return ERROR;
                }
            }
        });
    }

}