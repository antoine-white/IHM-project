package UI.Components;

import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;

import Noyau.terrain.*;
import UI.consts.*;

public class Field extends JPanel{

    private static final Dimension MIN_SIZE = new Dimension(200,200);
    private static final Dimension INITIAL_SIZE = new Dimension(400,400);
    private static final int[] DEFAULT_CELL = {100,100};

    public Field(){
        super();
        this.setPreferredSize(Field.INITIAL_SIZE);
        this.setMinimumSize(Field.MIN_SIZE);
        this.setBorder(BorderFactory.createLineBorder(ConstColors.BORDER));
    }
}
