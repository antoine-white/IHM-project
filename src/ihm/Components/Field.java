package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import model.terrain.*;
import ihm.consts.*;

public class Field extends JPanel {

    private Fourmiliere fourmiliere;

    private static final Dimension MIN_SIZE = new Dimension(200, 200);
    private static final Dimension INITIAL_SIZE = new Dimension(500, 500);
    private static final Color WALL = Color.BLACK;
    private static final Color EMPTY = Color.WHITE;
    private static final Color GRAINE = Color.RED;
    private static final Color FOURMI = Color.GREEN;

    public Field(Fourmiliere f) {
        super();
        this.setPreferredSize(Field.INITIAL_SIZE);
        this.setMinimumSize(Field.MIN_SIZE);
        this.setBorder(BorderFactory.createLineBorder(ConstColors.BORDER));
        this.fourmiliere = f;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();
        int largeur = this.fourmiliere.getLargeur();
        int hauteur = this.fourmiliere.getHauteur();
        for (int i = 0; i < largeur; i++) {
            int curr = (width * i / largeur);
            int nextOffset = (width * (i + 1) / largeur) - (width * i / largeur);
            for (int j = 0; j < hauteur; j++) {
                g.setColor(colorAtPos(i, j));
                g.fillRect(curr, (height * j / hauteur), nextOffset,
                        (height * (j + 1) / hauteur) - (height * j / hauteur));
                if (fourmiliere.contientFourmi(i, j)) {
                    g.setColor(Field.FOURMI);
                    g.fillOval(curr , (height * j / hauteur) , nextOffset,
                            nextOffset);
                }
            }
        }
    }

    private Color colorAtPos(int x, int y) {
        if (this.fourmiliere.getMur(x, y)) {
            return Field.WALL;
        } else {
            int nb_graine = this.fourmiliere.getQteGraines(x, y);
            if (nb_graine == 0) {
                return Field.EMPTY;
            } else {
                return new Color(Field.GRAINE.getRed(), Field.GRAINE.getBlue(), Field.GRAINE.getGreen(), 255);
            }
        }
    }

}
