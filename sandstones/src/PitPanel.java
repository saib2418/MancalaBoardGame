import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PitPanel extends JPanel {
    private final Pit pit;
    private final JLabel label;
    private final StonesPanel stonesPanel;
    private static final int MARGIN = 2;
    private static final int STONE_SIZE = 10;
    private Style style;


    public PitPanel(Pit p, Style s) {
        pit = p;
        style = s;
        label = new JLabel();
        stonesPanel = new StonesPanel();

        initializePanel(p.getPosition());
    }

    private void initializePanel(int position) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // label.setFont(new Font("Arial", Font.PLAIN, 16));

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

    public void setStyle(Style newStyle) {
        this.style = newStyle;
    }

    public Pit getPit() {
        return pit;
    }


    public Ellipse2D.Double getPitBoundaries() {
        return new Ellipse2D.Double(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
    }

    public int getWholeWidth() {
        return getWidth();
    }

    public int getWholeHeight() {
        return getHeight();
    }


    public void paintComponent(Graphics g) {
        initializePanel(pit.getPosition());
//        Graphics2D g2 = (Graphics2D) g;
//        int stones = pit.getStones();
//        super.paintComponent(g2);
//        setBackground(style.getBoardBackgroundColor());
//        int pitStroke = style.getPitStrokeThickness();
//        g2.setStroke(new BasicStroke(pitStroke));
//        g2.setColor(style.getLineColor());
//        g2.drawOval(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
//        g2.setColor(style.getPitBackgroundColor());
//        g2.fillOval(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
//
//        int position = pit.getPosition();
//        if (position <= 5)
//            g2.drawString("\nA" + pit.getPosition(), 20, 10);
//        else if (position <= 11)
//            g2.drawString("\nB" + (pit.getPosition() - 6), 20, 10);
//
//
//        int h = getWidth() / 2 - MARGIN * 6;
//        int k = getHeight() / 2 - MARGIN * 6;
//        double twoPI = Math.PI * 2;
//        Point center = new Point(getWidth() / 2, getHeight() / 2);
//        g2.setColor(style.getStoneColor());
//        g2.setStroke(new BasicStroke(style.getStoneStrokeThickness()));
//        g2.setFont(new Font("Arial", Font.BOLD, 18));
//        g2.drawString(stones + "", center.x, center.y);
//        for (int i = 0; i < stones; i++) {
//            int a = (int) (center.x + h * Math.cos(twoPI * i / stones)) - MARGIN;
//            int b = (int) (center.y + k * Math.sin(twoPI * i / stones)) - MARGIN;
//            g2.fillOval(a, b, STONE_SIZE, STONE_SIZE);
//        }
    }

    private class StonesPanel extends JPanel {

        public StonesPanel() {

            if (pit.getPosition() < 12)
                setPreferredSize(new Dimension(getWholeWidth(), (int) (getHeight() * 0.8)));
            else
                setPreferredSize(new Dimension((int) (getWholeWidth() * 0.8), getHeight()));
            System.out.println("Stones Panel Pit # " + pit.getPosition());
            System.out.println(getWidth() + " by " + getHeight());
        }

        public void paintComponent(Graphics g) {
//            System.out.println(pit.getPosition());
//            System.out.println(getWidth() + " by " + getHeight());
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
