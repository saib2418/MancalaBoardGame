package sandstone;

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
     * paints a full board
     */
    public static class BoardPainter extends JPanel {
        private ArrayList<PotPainter> pots = new ArrayList<>();
        private java.util.List<Integer> mancalaBoard;

        public BoardPainter(java.util.List<Integer> mancalaBoard) {
            this.mancalaBoard = mancalaBoard;

            setLayout(new BorderLayout());

            // a 2x6 grid in the center
            JPanel center = new JPanel();
            center.setLayout(new GridLayout(2, 6));
            for (int i = 0; i < 12; i++) {
                PotPainter p = new PotPainter(i);
                pots.add(p);
                center.add(p);
            }
            add(center, BorderLayout.CENTER);

            // one side
            PotPainter east = new PotPainter(13);
            pots.add(east);
            add(east, BorderLayout.EAST);
            east.setPreferredSize(new Dimension(100, 0));

            // the other side
            PotPainter west = new PotPainter(14);
            pots.add(west);
            add(west, BorderLayout.WEST);
            west.setPreferredSize(new Dimension(100, 0));
        }

        // This method will handle moving stones when a pit is clicked.
        public void pitPressed(int position) {
            System.out.println("Pit " + position + " has been clicked.");
            pots.get(position);
        }

        public void paint(Graphics g) {
            for (int i = 0; i < mancalaBoard.size(); i++) {
                pots.get(i).setStones(mancalaBoard.get(i));
            }
            super.paint(g);
        }
    }

    /**
     * paints a pot
     */
    public static class PotPainter extends JPanel {
        private static final int MARGIN = 2;
        private static final int STONE_SIZE = 10;
        private int stones = 0;
        private int position;

        public PotPainter(int pos) {
            this.position = pos;
            addMouseListener(new MousePressedListener());
        }

        public void setStones(int stones) {
            this.stones = stones;
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            g.drawOval(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
            Random r = new Random();
            int d = Math.min(getWidth(), getHeight()) / 2;
            Point center = new Point(getWidth() / 2, getHeight() / 2);
            for (int i = 0; i < stones; i++) {
                g.drawOval(center.x + r.nextInt(d) - d / 2, center.y + r.nextInt(d) - d / 2,
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
        SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame();
            jf.add(new BoardPainter(new ArrayList<>(
                    Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 4, 2))));
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setSize(800, 300);
            jf.setVisible(true);
        });
    }
}
