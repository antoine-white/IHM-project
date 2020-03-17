package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import model.terrain.*;
import ihm.consts.*;

public class Field extends JPanel implements KeyListener, MouseListener, MouseWheelListener {

    private Fourmiliere fourmiliere;
    private boolean shiftOn;

    private static final Dimension MIN_SIZE = new Dimension(200, 200);
    private static final Dimension INITIAL_SIZE = new Dimension(500, 500);
    private static final Color WALL = Color.BLACK;
    private static final Color EMPTY = Color.WHITE;
    private static final Color GRAINE = Color.RED;
    private static final Color FOURMI = Color.GREEN;
    private static final int SHIFT_KEY_CODE = 16;

    public Field(Fourmiliere f) {
        super();
        this.setPreferredSize(Field.INITIAL_SIZE);
        this.setMinimumSize(Field.MIN_SIZE);
        this.setBorder(BorderFactory.createLineBorder(ConstColors.BORDER));
        this.fourmiliere = f;
        this.shiftOn = false;
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
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
                    g.fillOval(curr, (height * j / hauteur), nextOffset, nextOffset);
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
                float coeff = ((float)nb_graine / (float)Fourmiliere.getQMax());
                return new Color(
                    (int)(Field.GRAINE.getRed() * coeff),
                    (int)(Field.GRAINE.getBlue() * coeff),
                    (int)(Field.GRAINE.getGreen() * coeff)
                    );
            }
        }
    }

    private Point getCoordFromPoint(Point evtPoint){
        return new Point(
            (int) ((int)((double)evtPoint.getX())/((double)this.getWidth()) * (double)this.fourmiliere.getLargeur()),
            (int) ((int)((double)evtPoint.getY())/((double)this.getHeight()) * (double)this.fourmiliere.getHauteur())
        );        
    }

    private void destroyCreateWall(Point p){
        this.fourmiliere.setMur(p.x, p.y, !this.fourmiliere.getMur(p.x, p.y));
        this.repaint();
    }

    private void addFourmi(Point p){
        this.fourmiliere.ajouteFourmi(p.x, p.y);
        this.repaint();
    }

    private void addGraine(Point p, boolean adding){
        int toAdd = (adding?1:-1);
        this.fourmiliere.setQteGraines(p.x, p.y, this.fourmiliere.getQteGraines(p.x, p.y) + toAdd);
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Field.SHIFT_KEY_CODE) {            
            this.shiftOn = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {       
        if (e.getKeyCode() == Field.SHIFT_KEY_CODE) {            
            this.shiftOn = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        // we only handle left click
        if(SwingUtilities.isLeftMouseButton(e)){
            Point coordOfClick = this.getCoordFromPoint(e.getPoint());
            if (this.shiftOn) {
                this.addFourmi(coordOfClick);
            } else {
                this.destroyCreateWall(coordOfClick);
            }
        }        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //grad focus to use keyboard event
        this.grabFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
        this.addGraine(this.getCoordFromPoint(e.getPoint()), e.getWheelRotation() > 0);
    }

}
