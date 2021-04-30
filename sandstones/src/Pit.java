import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * paints a pot
 */
public class Pit extends JPanel {
    private static final int MARGIN = 2;
    private static final int STONE_SIZE = 10;
    private int stones;
    private final int position;
    public Pit next;
    public Pit prev;
    private MouseListener listener;
    private Style style;

    public Pit(Style style, int pos) {
        this.style = style;
        this.position = pos;
    }

    public int getPosition() {
        return position;
    }

    public Pit getPrev() {
        return prev;
    }

    public Pit getNext() {
        return next;
    }

    public void setPrev(Pit p) {
        this.prev = p;
    }

    public void setNext(Pit p) {
        this.next = p;
    }

    public MouseListener getListener() {
        return this.listener;
    }

    public void setStones(int stones) {
        this.stones = stones;
        repaint();
    }

    public int getStones() {
        return this.stones;
    }

    public boolean isEmpty() {
        return this.stones == 0;
    }

    public int emptyAll() {
        int numStones = stones;
        stones = 0;
        repaint();
        return numStones;

    }

    public void addStone() {
        stones++;
        repaint();
    }

    public void addMany(int newStones) {
        stones += newStones;
        repaint();
    }

    public void loseStone() {
        stones--;
        repaint();
    }

    public void setStyle(Style newStyle) {
        this.style = newStyle;
    }


    public String toString() {
        String str = "";
        str += "\nPit #" + position;
        str += "\nStones: " + stones;
        str += "\nNext: " + next.position;
        str += "\nPrev: " + prev.position;
        return str;
    }

    public Ellipse2D.Double getPitBoundaries() {
        return new Ellipse2D.Double(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        setBackground(style.getBoardBackgroundColor());
        int pitStroke = style.getPitStrokeThickness();
        g2.setStroke(new BasicStroke(pitStroke));
        g2.setColor(style.getLineColor());
        g2.drawOval(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
        g2.setColor(style.getPitBackgroundColor());
        g2.fillOval(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
        g2.drawString("\nA" + position, 20, 10);
        Random r = new Random();
        int d = Math.min(getWidth() - MARGIN * 2, getHeight() - MARGIN * 2) / 2;
        Point center = new Point(getWidth() / 2, getHeight() / 2);
        for (int i = 0; i < stones; i++) {
            g2.setColor(style.getStoneColor());
            g2.setStroke(new BasicStroke(style.getStoneStrokeThickness()));
            g2.fillOval(center.x + r.nextInt(d) - d / 2, center.y + r.nextInt(d) - d / 2,
                    STONE_SIZE, STONE_SIZE);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
