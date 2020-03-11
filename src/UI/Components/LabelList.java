package UI.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LabelList{

    private JLabel[] labels = {
        new JLabel("nbGraines: "),
        new JLabel("nbFourmis: "),
    };

    public LabelList() {
        Box haut = createLabelList(this.labels);
    }

    public Box createLabelList(JLabel [] labels){
        Box box = Box.createVerticalBox(); 
        for(JLabel l : labels){
            box.add(l); 
       }
       return box; 
    }
    

}




