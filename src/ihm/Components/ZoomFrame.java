package ihm.Components;

import java.awt.*;
import javax.swing.*;

import model.terrain.Fourmiliere;

import java.awt.event.*;

public class ZoomFrame extends JFrame{
    private static final String TITLE = "Fenetre de zoom";
    private static final Dimension DIMENSION = new Dimension(330,330);

    private ZoomPanel panel;

    public ZoomFrame(){
        super();
        this.setTitle(ZoomFrame.TITLE);
        this.setPreferredSize(ZoomFrame.DIMENSION);
        this.setMinimumSize(ZoomFrame.DIMENSION);
        this.setResizable(false);
        this.setLocationRelativeTo(null); 
        this.panel = new ZoomPanel();
        this.add(panel);
    }

    public void repaint(Fourmiliere f, int x , int y){
        this.panel.repaint(f,x,y);
    }
    
}