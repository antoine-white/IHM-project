package UI.Components;

import javax.swing.*;
import java.awt.*;

import UI.Components.*;
import UI.consts.*;

public class MainFrame extends JFrame{

    private JLabel title;
    private JPanel panel; 

    private static final String titleStr = "Simulation de fourmilliere";
    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        f.pack();
        f.setVisible(true);
    }

    public MainFrame(){
        super();/*
        this.panel = new JPanel(); 
        this.panel.setBackground(ConstColors.BACKGROUND);
        this.panel.setForeground(ConstColors.TEXT);*/
        this.setBackground(ConstColors.BACKGROUND);
        this.setForeground(ConstColors.TEXT);
        this.title = new JLabel(MainFrame.titleStr);
        this.title.setFont(ConstFonts.TITLE);
        this.createMainLayout(); 
        //this.createMainLayout(this.panel);
        //this.add(panel);
    }
    
    private void createMainLayout(){
        this.add(this.titleBox(),BorderLayout.NORTH);
        this.add(this.quitBox(),BorderLayout.SOUTH);
        this.add(centralBox(leftVerticalBox(),new Field(),rightVerticalBox()),BorderLayout.CENTER); 
    } 
    /*
    private void createMainLayout(JPanel pan){
        pan.add(titleBox(),BorderLayout.NORTH);
        pan.add(centralBox(leftVerticalBox(),new Field(),rightVerticalBox()),BorderLayout.CENTER); 
        pan.add(quitBox(),BorderLayout.SOUTH);
    }
    */

    private Box titleBox(){
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(this.title);
        box.add(Box.createHorizontalGlue());
        return box;
    }

    private Box quitBox(){
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(new QuitButton());
        return box;
    }

    private Box leftVerticalBox(){
        Box box = Box.createVerticalBox();
        box.add(new ReinitializationButton());
        box.add(Box.createVerticalStrut(10));
        box.add(new RandomInitializationButton());
        return box;
    }
    private Box rightVerticalBox(){
        Box box = Box.createVerticalBox();
        box.add(new MagnifyingGlassButton());
        box.add(Box.createVerticalStrut(10));
        box.add(new LabelList());
        return box;
    }

    // CHANGER LE JPANEL PAR LE TERRAIN
    private Box centralBox(Box b1, Field field ,Box b2){
        Box box = Box.createHorizontalBox();
        //box.add(Box.createHorizontalStrut(20));
        box.add(Box.createHorizontalGlue());
        box.add(b1);
        box.add(Box.createHorizontalGlue());
        box.add(field);
        box.add(Box.createHorizontalGlue());
        box.add(b2);
        return box;
    }
}