package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LabelList extends Box{

    private JLabel[] labels = {
        new JLabel("nbGraines: "),
        new JLabel("nbFourmis: "),
    };

    public LabelList() {
        super(BoxLayout.PAGE_AXIS);
        for(JLabel l : labels){
            this.add(l); 
       }
    }
    /*
    public Box createLabelList(JLabel [] labels){
        Box box = Box.createVerticalBox(); 
        for(JLabel l : labels){
            box.add(l); 
       }
       return box; 
    } */
    

}




