package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import ihm.consts.ConstPaths;

/**
 * Button that open a little window that zoom on 
 * a specific spot of the simulation
 */
public class MagnifyingGlassButton extends CustomButton {

    private ZoomFrame zoomFrame;

    public MagnifyingGlassButton() {
        super(); 
        ImageIcon img = new ImageIcon(ConstPaths.IMAGE_PATH + "loupe2.png");
        Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
        ImageIcon tmp = new ImageIcon( newimg );
        this.setIcon(tmp); 
        this.setContentAreaFilled(false);
        this.zoomFrame = new ZoomFrame();
        this.zoomFrame.setVisible(false);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zoomFrame.setVisible(!zoomFrame.isVisible());
            }
        });
    }

    /**
     * 
     * @return the frame that this button opens
     */
    public ZoomFrame getZoomFrame(){
        return this.zoomFrame;
    }

    

    

}