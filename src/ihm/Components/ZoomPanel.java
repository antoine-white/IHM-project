package ihm.Components;

import java.awt.*;
import javax.swing.*;

import ihm.consts.*;
import model.terrain.Fourmiliere;

/**
 * This is panel where the zoomed spot on the field is rendered
 */
public class ZoomPanel extends JPanel {

    private Fourmiliere tempF;
    private int xStart;
    private int yStart;

    private static final int SIZE_ZOOM = 11;
    private static final int ZOOM_MULT = 30;
    private static final String ANT = "🐜";
    private static final String SEED = "🌰";
    private static final String WALL = "⬛";

    public ZoomPanel() {
        super();
    }

    public void repaint(Fourmiliere f, int x, int y) {
        this.tempF = f;
        this.xStart = x - SIZE_ZOOM/2;
        this.yStart = y - SIZE_ZOOM/2;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.tempF != null) {
            for (int i = this.xStart; i < this.tempF.getLargeur() && i < this.xStart + ZoomPanel.SIZE_ZOOM; i++) {
                for (int j = this.yStart; j < this.tempF.getHauteur() && j < this.yStart + ZoomPanel.SIZE_ZOOM; j++) {
                    //finding the coord to where we should draw
                    Point drawPoint = new Point((i - this.xStart) * ZoomPanel.ZOOM_MULT,
                            (j - this.yStart) * ZoomPanel.ZOOM_MULT);
                    //draw the ant if necessary
                    if (this.tempF.contientFourmi(i, j)) {
                        g.setColor(this.tempF.fourmiPorteuse(i, j) ? ConstColors.FOURMI_PORTEUSE : ConstColors.FOURMI);
                        g.drawString(ZoomPanel.ANT,drawPoint.x, drawPoint.y);
                    }
                    int nb_graine = this.tempF.getQteGraines(i, j);
                    //draw seed
                    if (nb_graine > 0) {
                        float coeff = ((float) nb_graine / (float) Fourmiliere.getQMax());
                        g.setColor(new Color((int) (ConstColors.GRAINE.getRed() * coeff),
                                (int) (ConstColors.GRAINE.getBlue() * coeff),
                                (int) (ConstColors.GRAINE.getGreen() * coeff)));
                        g.drawString(ZoomPanel.SEED,drawPoint.x, drawPoint.y);
                    }
                    //draw wall
                    if (this.tempF.getMur(i, j)){
                        g.setColor(ConstColors.WALL);
                        g.drawString(ZoomPanel.WALL, drawPoint.x, drawPoint.y);
                    }
                }
            }
        }
    }
}