package ihm.Components;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import ihm.consts.ConstPaths;

public class PlayPauseButton extends CustomButton{

    //private static final String PAUSE_TXT = "Pause";
    //private static final String PLAY_TXT = "Jouer";
    private boolean isPlay; 
    private MainFrame mainFrame;

    //TODO : relier à la simulation 
    public PlayPauseButton(MainFrame mainFrame){
        //super(PlayPauseButton.PAUSE_TXT);
        super(); 
        this.isPlay = false;
        this.mainFrame = mainFrame;
        this.setJButtonIcon(this.isPlay);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayPauseButton emitter = ((PlayPauseButton)e.getSource());
                Boolean isPlay = emitter.getIsPlay();  
                if (isPlay) {
                    emitter.setJButtonIcon(false);
                    emitter.setIsPlay(false);
                    
                    System.out.println("loll");
                    mainFrame.endSimulation();
                } else {
                    emitter.setJButtonIcon(true);
                    emitter.setIsPlay(true);
                    System.out.println("lolllll");
                    mainFrame.startSimulation();
                }
            }
        }); 
    }

    private void setJButtonIcon(Boolean isIconPlay){
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
        //this.setBorder(BorderFactory.createEmptyBorder());
    }

    public boolean getIsPlay() {
        return isPlay;
    }

    public void setIsPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }

    

    

   
}