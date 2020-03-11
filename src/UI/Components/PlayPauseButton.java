package UI.Components;

import javax.swing.*;
import java.awt.event.*;

public class PlayPauseButton extends JButton{

    private static final String PAUSE_TXT = "Pause";
    private static final String PLAY_TXT = "Play";

    //TODO : relier à la simulation 
    public PlayPauseButton(){
        super(" Pause ");
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayPauseButton emitter = ((PlayPauseButton)e.getSource());
                if (emitter.getText() == ) {
                    
                } else {
                    
                }
            }
        });
    }
}