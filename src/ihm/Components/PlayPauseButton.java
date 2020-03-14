package ihm.Components;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PlayPauseButton extends JButton{

    //private static final String PAUSE_TXT = "Pause";
    //private static final String PLAY_TXT = "Jouer";
    private static boolean isPlay; 

    //TODO : relier à la simulation 
    public PlayPauseButton(){
        //super(PlayPauseButton.PAUSE_TXT);
        super(); 
        PlayPauseButton.isPlay = true;
        this.setJButtonIcon(PlayPauseButton.isPlay);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {/*
                PlayPauseButton emitter = ((PlayPauseButton)e.getSource());
                if (emitter.getText() == PlayPauseButton.PAUSE_TXT) {
                    emitter.setText(PlayPauseButton.PLAY_TXT);
                    //TODO start simulation
                } else {
                    emitter.setText(PlayPauseButton.PAUSE_TXT);
                    //TODO end simualtion
                }*/
                PlayPauseButton emitter = ((PlayPauseButton)e.getSource());
                Boolean isPlay = emitter.getIsPlay();  
                if (isPlay) {
                    emitter.setJButtonIcon(false);
                    emitter.setIsPlay(false);
                } else {
                    emitter.setJButtonIcon(true);
                    emitter.setIsPlay(true);
                }
            }
        }); 
    }

    private void setJButtonIcon(Boolean isIconPlay){
        if(isIconPlay){
            ImageIcon img = new ImageIcon("ihm/Resources/circleplay.png");
            Image newimg = img.getImage().getScaledInstance( 85, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
            ImageIcon tmp = new ImageIcon(newimg);
            this.setIcon(tmp); 
            
        } else {
            ImageIcon img = new ImageIcon("ihm/Resources/circlepause.png"); 
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
        PlayPauseButton.isPlay = isPlay;
    }

    

    

   
}