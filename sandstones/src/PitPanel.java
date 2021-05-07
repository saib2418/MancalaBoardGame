import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Constructs the GUI view of a single pit.
 */
public class PitPanel extends JPanel {
    private final Pit pit;
    private final JLabel label;
    private final StonesPanel stonesPanel;
    private static final int MARGIN = 2;
    private static final int STONE_SIZE = 10;
    private Style style;

    /**
     * Takes a pit and a style in which pit is to be drawn. Draws stones and label.
     *
     * @param p Pit
     * @param s style
     */
    public PitPanel(Pit p, Style s) {
        pit = p;
        style = s;
        label = new JLabel();
        stonesPanel = new StonesPanel();

        initializePanel(p.getPosition());
    }

    /**
     * Arranges order of label and pit drawing. If pit is in bottom row, label is beneath pit.
     * Else if pit is in top row, label is above pit.
     *
     * @param position position of pit
     */
    private void initializePanel(int position) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        if (position <= 5) {
            label.setText("B" + (position + 1));
            add(label);
            add(stonesPanel);

        } else if (position <= 11) {
            label.setText("A" + (position - 5));
            add(stonesPanel);
            add(label);
        } else if (position == 12) {
            label.setText("Mancala A");
            add(stonesPanel);
            add(label);
        } else if (position == 13) {
            label.setText("Mancala B");
            add(stonesPanel);
            add(label);

        }
    }

    /**
     * Getter for pit
     *
     * @return pit
     */
    public Pit getPit() {
        return pit;
    }

    /**
     * Gets the boundaries of the pit. Used for determining whether pit was clicked inside.
     *
     * @return pit boundaries
     */
    public Ellipse2D.Double getPitBoundaries() {
        return new Ellipse2D.Double(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
    }

    /**
     * Gets width of entire panel
     *
     * @return width of panel
     */
    public int getWholeWidth() {
        return getWidth();
    }

    /**
     * Gets height of entire panel
     *
     * @return height of panel
     */
    public int getWholeHeight() {
        return getHeight();
    }

    /**
     * Paints the panel based on the pit properties
     *
     * @param g Graphics
     */
    public void paintComponent(Graphics g) {
        initializePanel(pit.getPosition());
    }

    /**
     * Inner class responsible for drawing stones
     *
     * @author Rhea
     * @version 2.0
     */
    private class StonesPanel extends JPanel {

        /**
         * Constructor that sets dimensions of pit depending on whether it is an ordinary pit
         * or a mancala.
         */
        public StonesPanel() {
            if (pit.getPosition() < 12)
                setPreferredSize(new Dimension(getWholeWidth(), (int) (getHeight() * 0.8)));
            else
                setPreferredSize(new Dimension((int) (getWholeWidth() * 0.8), getHeight()));
        }

        /**
         * Draws stones in the form of an oval. Colors are based on the board theme. Writes current number
         * of stones at center of pit.
         *
         * @param g Graphics drawer
         */
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            int stones = pit.getStones();
            super.paintComponent(g2);
            setBackground(style.getBoardBackgroundColor());
            int pitStroke = style.getPitStrokeThickness();
            g2.setStroke(new BasicStroke(pitStroke));
            g2.setColor(style.getLineColor());
            g2.drawOval(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
            g2.setColor(style.getPitBackgroundColor());
            g2.fillOval(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);

            int h = getWidth() / 2 - MARGIN * 6;
            int k = getHeight() / 2 - MARGIN * 6;
            double twoPI = Math.PI * 2;
            Point center = new Point(getWidth() / 2, getHeight() / 2);
            g2.setColor(style.getStoneColor());
            g2.setStroke(new BasicStroke(style.getStoneStrokeThickness()));
            g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.drawString(stones + "", center.x, center.y);
            for (int i = 0; i < stones; i++) {
                int a = (int) (center.x + h * Math.cos(twoPI * i / stones)) - MARGIN;
                int b = (int) (center.y + k * Math.sin(twoPI * i / stones)) - MARGIN;
                g2.fillOval(a, b, STONE_SIZE, STONE_SIZE);
            }
        }
    }
}
