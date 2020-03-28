package ihm.Components;

import java.awt.*;
import javax.swing.*;

import ihm.consts.ConstColors;

import java.awt.event.*;

public class ResizePanel extends JPanel {

    private CustomButton validate;
    private JTextField tFieldX;
    private JTextField tFieldY;

    private static final String BUTTON_TXT = "Changer de taille";
    private static final int MAX_HEIGHT = 200;
    private static final int MAX_WIDTH = 200;

    public ResizePanel(MainFrame mFrame) {
        super(); 
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.validate = new CustomButton(ResizePanel.BUTTON_TXT);
        this.validate.addActionListener(new ActionListener() {

            private static final int ERROR = -1;
            @Override
            public void actionPerformed(ActionEvent e) {                         
                if (mFrame.getSimulationRunning()) return;
                int tmpX = checkInput(ResizePanel.MAX_HEIGHT, tFieldX.getText());
                if (tmpX == ERROR) return;
                int tmpY = checkInput(ResizePanel.MAX_WIDTH, tFieldY.getText());
                if (tmpY == ERROR) return;
                int tmp = JOptionPane.showConfirmDialog(null, "Voulez vous changer la taille du plateau ? ", null, JOptionPane.YES_NO_OPTION);
                if (tmp == 0) 
                    mFrame.resizeAntHill(new Dimension(tmpX,tmpY));
            }

            private int checkInput(int max, String input){
                int res;
                try {
                    res = Integer.parseInt(input);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Nombre entier attendu", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return ERROR;
                }
                if (res <= 0){
                    JOptionPane.showMessageDialog(null, "Nombre supérieur à zéro attendu", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return ERROR;
                } else if (res > max){
                    JOptionPane.showMessageDialog(null, "Nombre inférieur à " + max + " attendu", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return ERROR;
                } else return res;
            }
        });
        this.tFieldX = new JTextField("20");
        this.tFieldX.setColumns(5);
        this.tFieldX.setMaximumSize(new Dimension(2000,30));
        this.tFieldY = new JTextField("20");
        this.tFieldX.setColumns(5);
        this.tFieldY.setMaximumSize(new Dimension(2000,30));
        this.createLayout();
        this.setBorder(BorderFactory.createLineBorder(ConstColors.BORDER));
    }

    private void createLayout(){
        this.add(Box.createVerticalStrut(10));
        this.add(this.jTextBox("x : ",this.tFieldX));
        this.add(Box.createVerticalStrut(10));
        this.add(this.jTextBox("y : ",this.tFieldY));
        this.add(Box.createVerticalStrut(10));  
        this.add(this.validate);
        this.add(Box.createVerticalStrut(10));
    }

    private Box jTextBox(String txt, JTextField textField){
        Box b = Box.createHorizontalBox();
        b.add(new JLabel(txt));
        b.add(Box.createVerticalGlue());
        b.add(textField);
        b.setMaximumSize(new Dimension(2000,30));
        return b;
    }

}




