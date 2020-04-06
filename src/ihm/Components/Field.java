package ihm.Components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import model.terrain.*;
import ihm.consts.*;

/**
 * This is the components that displays the simulation
 */
public class Field extends JPanel implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener {

    private MainFrame mainFrame;
    private boolean shiftOn;

    private static final Dimension MIN_SIZE = new Dimension(200, 200);
    private static final Dimension INITIAL_SIZE = new Dimension(500, 500);
    private static final int SHIFT_KEY_CODE = 16;

    /**
     * 
     * @param mFrame
     */
    public Field(MainFrame mFrame) {
        super();
        this.setPreferredSize(Field.INITIAL_SIZE);
        this.setMinimumSize(Field.MIN_SIZE);
        this.setBorder(BorderFactory.createLineBorder(ConstColors.BORDER));
        this.mainFrame = mFrame;
        this.shiftOn = false;
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();
        int largeur = this.mainFrame.getFourmiliere().getLargeur();
        int hauteur = this.mainFrame.getFourmiliere().getHauteur();
        for (int i = 0; i < largeur; i++) {
            int curr = (width * i / largeur);
            int nextOffset = (width * (i + 1) / largeur) - (width * i / largeur);
            for (int j = 0; j < hauteur; j++) {
                g.setColor(colorAtPos(i, j));
                g.fillRect(curr, (height * j / hauteur), nextOffset,
                        (height * (j + 1) / hauteur) - (height * j / hauteur));
                if (mainFrame.getFourmiliere().contientFourmi(i, j)) {
                    // the color is different if the ant is carrying a seed
                    g.setColor(mainFrame.getFourmiliere().fourmiPorteuse(i, j) ? ConstColors.FOURMI_PORTEUSE : ConstColors.FOURMI);
                    g.fillOval(curr, (height * j / hauteur), nextOffset, nextOffset);
                }
            }
        }
    }

    /**
     * 
     * @param x x coord in the anthill
     * @param y y coord in the anthill
     * @return the color at this position, for instance ConstColors.WALL if there is a
     *         wall
     */
    private Color colorAtPos(int x, int y) {
        if (this.mainFrame.getFourmiliere().getMur(x, y)) {
            return ConstColors.WALL;
        } else {
            int nb_graine = this.mainFrame.getFourmiliere().getQteGraines(x, y);
            if (nb_graine == 0) {
                return ConstColors.EMPTY;
            } else {
                float coeff = ((float) nb_graine / (float) Fourmiliere.getQMax());
                return new Color((int) (ConstColors.GRAINE.getRed() * coeff), (int) (ConstColors.GRAINE.getBlue() * coeff),
                        (int) (ConstColors.GRAINE.getGreen() * coeff));
            }
        }
    }

    /**
     * 
     * @param evtPoint the precise point of the event append in pixel
     * @return the matching point in the anthill
     */
    private Point getCoordFromPoint(Point evtPoint) {
        return new Point(
                (int) ((int) ((double) evtPoint.getX()) / ((double) this.getWidth())
                        * (double) this.mainFrame.getFourmiliere().getLargeur()),
                (int) ((int) ((double) evtPoint.getY()) / ((double) this.getHeight())
                        * (double) this.mainFrame.getFourmiliere().getHauteur()));
    }

    /**
     * set a wall at the given coordinates if there any wall before remove this wall
     * otherwise
     * 
     * @param p the point in the anthill
     */
    private void destroyCreateWall(Point p) {
        this.mainFrame.getFourmiliere().setMur(p.x, p.y, !this.mainFrame.getFourmiliere().getMur(p.x, p.y));
        this.repaint();
    }

    /**
     * add an ant at the given coordinates
     * 
     * @param p the point in the anthill
     */
    private void addAnt(Point p) {
        this.mainFrame.getFourmiliere().ajouteFourmi(p.x, p.y);
        this.mainFrame.updateAntHill();
    }

    /**
     * add or remove one seed at the given coordinates
     * 
     * @param p      the point in the anthill
     * @param adding if true add one seed else remove one (if possible in both case)
     */
    private void addSeed(Point p, boolean adding) {
        int toAdd = (adding ? 1 : -1);
        this.mainFrame.getFourmiliere().setQteGraines(p.x, p.y,
        this.mainFrame.getFourmiliere().getQteGraines(p.x, p.y) + toAdd);
        this.mainFrame.updateAntHill();
    }

    /**
     * Handle clicks from click and drag of the mouse 
     * Only cares about left click
     * @param e 
     */
    private void handleClick(MouseEvent e){
        // we only handle left click
        if (SwingUtilities.isLeftMouseButton(e)) {
            Point coordOfClick = this.getCoordFromPoint(e.getPoint());
            if (this.shiftOn) {
                this.addAnt(coordOfClick);
            } else {
                this.destroyCreateWall(coordOfClick);
            }
        }
    }

    // === KeyListener === //

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
    public void keyTyped(KeyEvent e) {
    }

    // === MouseListener === //

    @Override
    public void mouseClicked(MouseEvent e) {
        this.handleClick(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // grad focus to use keyboard event
        this.grabFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // === MouseWheelListener === //

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.addSeed(this.getCoordFromPoint(e.getPoint()), e.getWheelRotation() > 0);
    }

    // === MouseMotionListener === //

    @Override
    public void mouseMoved(MouseEvent e) {
        Point tmpP = this.getCoordFromPoint(e.getPoint());
        this.mainFrame.getMagnifyingGlassButton().getZoomFrame().repaint(this.mainFrame.getFourmiliere(),tmpP.x,tmpP.y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.handleClick(e);
    }

}
