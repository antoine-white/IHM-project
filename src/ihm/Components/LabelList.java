package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LabelList extends Box{

    private static final String SEED_STR = "nb Graines: ";
    private static final String ANT_STR = "nb Fourmis: ";

    private JLabel[] labels = {
        new JLabel(LabelList.SEED_STR),
        new JLabel(LabelList.ANT_STR),
    };

    public LabelList() {
        super(BoxLayout.PAGE_AXIS);
        for(JLabel l : labels){
            this.add(l); 
       }
    }
    
    public void setTextLbl(int nbSeed, int nbAnt){
        this.labels[0].setText(LabelList.SEED_STR + nbSeed);
        this.labels[1].setText(LabelList.ANT_STR + nbAnt);
    }
    

}




