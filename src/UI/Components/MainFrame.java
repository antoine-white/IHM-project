package UI.Components;

import javax.swing.*;
import java.awt.*;

import UI.Components.*;
import UI.consts.*;

public class MainFrame extends JFrame{

    private JLabel title;

    private static final String titleStr = "Simulation de fourmillière";
    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        f.pack();
        f.setVisible(true);
    }

    public MainFrame(){
        super();
        this.setBackground(ConstColors.BACKGROUND);
        this.setForeground(ConstColors.TEXT);
        this.title = new JLabel(MainFrame.titleStr);
        this.title.setFont(ConstFonts.TITLE);
        this.createMainLayout();
    }

    private void createMainLayout(){
        this.add(this.titleBox(),BorderLayout.NORTH);

        this.add(this.quitBox(),BorderLayout.SOUTH);
    }

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
}