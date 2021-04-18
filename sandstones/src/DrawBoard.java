import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class DrawBoard {
    /**
     * @author saibharathula for this class of painting a board with pits and dots.
     */

    public static class BoardPainter extends JPanel {
        private ArrayList<Pit> pits = new ArrayList<>();
        private java.util.List<Integer> mancalaBoard;
        private Style style;

        public BoardPainter(Style style, java.util.List<Integer> mancalaBoard) {
            this.mancalaBoard = mancalaBoard;
            this.style = style;
            setLayout(new BorderLayout());

            // a 2x6 grid in the center
            JPanel center = new JPanel();
            center.setLayout(new GridLayout(2, 6));
            for (int i = 0; i < 12; i++) {
                Pit p = new Pit(style, i);
                pits.add(p);
                center.add(p);
            }
            add(center, BorderLayout.CENTER);

            // one side
            Pit east = new Pit(style, 13);
            pits.add(east);
            add(east, BorderLayout.EAST);
            east.setPreferredSize(new Dimension(100, 0));

            // the other side
            Pit west = new Pit(style, 14);
            pits.add(west);
            add(west, BorderLayout.WEST);
            west.setPreferredSize(new Dimension(100, 0));
        }

        // This method will handle moving stones when a pit is clicked.
        public void pitPressed(int position) {
            System.out.println("Pit " + position + " has been clicked.");
            pits.get(position);
        }

        public void paint(Graphics g) {

            for (int i = 0; i < mancalaBoard.size(); i++) {
                pits.get(i).setStones(mancalaBoard.get(i));
            }
            super.paint(g);
        }
    }

    /**
     * paints a pot
     */
    public static class Pit extends JPanel {
        private static final int MARGIN = 2;
        private static final int STONE_SIZE = 10;
        private int stones = 0;
        private int position;
        private Style style;

        public Pit(Style style, int pos) {
            this.style = style;
            this.position = pos;
            addMouseListener(new MousePressedListener());
        }

        public void setStones(int stones) {
            this.stones = stones;
        }

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            super.paint(g2);
            setBackground(style.getBoardBackgroundColor());
            g2.setStroke(new BasicStroke(style.getPitStrokeThickness()));
            g2.setColor(style.getLineColor());
            g2.drawOval(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
            g2.setColor(style.getPitBackgroundColor());
            g2.fillOval(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
            Random r = new Random();
            int d = Math.min(getWidth(), getHeight()) / 2;
            Point center = new Point(getWidth() / 2, getHeight() / 2);
            for (int i = 0; i < stones; i++) {
                g2.setColor(style.getStoneColor());
                g2.setStroke(new BasicStroke(style.getStoneStrokeThickness()));
                g.fillOval(center.x + r.nextInt(d) - d / 2, center.y + r.nextInt(d) - d / 2,
                        STONE_SIZE, STONE_SIZE);
            }
        }

        private class MousePressedListener extends MouseAdapter {
            @Override
            public void mousePressed(MouseEvent event) {
                Point mousePoint = event.getPoint();
                System.out.println("Pit # " + position + " clicked");
                Ellipse2D.Double pitBoundaries = new Ellipse2D.Double(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
                System.out.println(mousePoint.getX() + " " + mousePoint.getY());
                System.out.println(pitBoundaries.contains(mousePoint.getX(), mousePoint.getY()));
            }
        }
    }


    public static void main(String... args) {
        Style boardStyle = new BlueStyle();
        SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame();
            jf.add(new BoardPainter(boardStyle, new ArrayList<>(
                    Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 4, 2))));
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setSize(800, 300);
            jf.setVisible(true);
        });
    }
}
