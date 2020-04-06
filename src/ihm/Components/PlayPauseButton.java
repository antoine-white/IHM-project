package ihm.Components;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import ihm.consts.ConstPaths;

/**
 * Button to play or pause the simulation
 */
public class PlayPauseButton extends CustomButton{

    private boolean isPlay; 
    private MainFrame mainFrame;

    public PlayPauseButton(MainFrame mainFrame){
        super(); 
        this.isPlay = false;
        this.mainFrame = mainFrame;
        this.setJButtonIcon(this.isPlay);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayPauseButton emitter = ((PlayPauseButton)e.getSource());
                boolean isPlay = emitter.getIsPlay();  
                if (isPlay) {
                    emitter.setJButtonIcon(false);
                    emitter.setIsPlay(false);
                    mainFrame.endSimulation();
                } else {
                    emitter.setJButtonIcon(true);
                    emitter.setIsPlay(true);
                    mainFrame.startSimulation();
                }
            }
        }); 
    }

    /**
     * set up the icon given if the simulation is playing
     * @param isIconPlay
     */
    private void setJButtonIcon(boolean isIconPlay){
        if(!isIconPlay){
            ImageIcon img = new ImageIcon(ConstPaths.IMAGE_PATH + "circleplay.png");
            Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
            ImageIcon tmp = new ImageIcon(newimg);
            this.setIcon(tmp); 
            
        } else {
            ImageIcon img = new ImageIcon(ConstPaths.IMAGE_PATH + "circlepause.png"); 
            Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
            ImageIcon tmp = new ImageIcon(newimg);
            this.setIcon(tmp); 
        }
        this.setContentAreaFilled(false);
    }

    public boolean getIsPlay() {
        return isPlay;
    }

    public void setIsPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }

    

    

   
}