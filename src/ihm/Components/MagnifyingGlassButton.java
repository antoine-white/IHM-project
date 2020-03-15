package ihm.Components;

import java.awt.*;
import javax.swing.*;


import java.awt.event.*;

public class MagnifyingGlassButton extends JButton {

    public class ClickAction implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            frameParameter();
        }
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        private void frameParameter(){
            JFrame jf = new JFrame();
            jf.setVisible(true); 
            jf.setPreferredSize(new Dimension(330,330));
            jf.setMinimumSize(new Dimension(330, 330));  
            jf.setLocationRelativeTo(null); 
            jf.setTitle("Zoom sur certaines cellules"); 
        }    

    }

    public MagnifyingGlassButton() {
        //super("Loupe");
        super(); 
        ImageIcon img = new ImageIcon("ihm/Resources/loupe2.png");
        //ImageIcon img = new ImageIcon("C:/Users/RKoub/Desktop/wallpaper/13694.jpg");
        Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon tmp = new ImageIcon( newimg );
        this.setIcon(tmp); 
        this.setContentAreaFilled(false);
        this.addMouseListener(new ClickAction());
        //this.setPreferredSize(new Dimension(75,50));
        //this.setBorder(BorderFactory.createEmptyBorder());
    }

    

    

}