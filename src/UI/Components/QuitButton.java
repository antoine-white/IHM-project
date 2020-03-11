package UI.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class QuitButton extends JButton {

    public QuitButton() {
        super("Quitter");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tmp = JOptionPane.showConfirmDialog(null, "Voulez vous quitter ? ", null, JOptionPane.YES_NO_OPTION);
                if (tmp == 0) 
                    System.exit(0);
            }
        });
    }

    

}