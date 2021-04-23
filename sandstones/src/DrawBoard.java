package sandstone;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.Random;

import javax.swing.*;


public class DrawBoard {
    static BoardPainter board;
    /**
     * paints a full board
     */
    public static class BoardPainter extends JPanel {
        private ArrayList<Pit> pots = new ArrayList<>();
        private java.util.List<Integer> mancalaBoard;
        private ArrayList<EventListener> listeners = new ArrayList<>();
        private Style style;
        private boolean turn;

        public BoardPainter(Style style, java.util.List<Integer> mancalaBoard) {
            turn = true;
        	this.mancalaBoard = mancalaBoard;
            this.style = style;
            setLayout(new BorderLayout());

            // a 2x6 grid in the center
            JPanel center = new JPanel();
            center.setLayout(new GridLayout(2, 6));

            for (int i = 0; i < 12; i++) {
                Pit p = new Pit(style, i);
                pots.add(p);
                listeners.add(p.getListener());
                center.add(p);
            }


            add(center, BorderLayout.CENTER);

            // one side
            Pit east = new Pit(style, 12);
            listeners.add(east.getListener());
            pots.add(east);
            add(east, BorderLayout.EAST);
            east.setPreferredSize(new Dimension(100, 0));

            // the other side
            Pit west = new Pit(style, 13);
            listeners.add(west.getListener());
            pots.add(west);
            add(west, BorderLayout.WEST);
            west.setPreferredSize(new Dimension(100, 0));

            // link pits to each other for dropping stones
            for (int i = 1; i < 5; i++) {
                pots.get(i).setNext(pots.get(i - 1));
                pots.get(i).setPrev(pots.get(i + 1));
            }
            pots.get(0).setPrev(pots.get(1));
            pots.get(0).setNext(pots.get(13)); // This might need to be changed when the skip other player's mancala part works.

            pots.get(5).setPrev(pots.get(12));
            pots.get(5).setNext(pots.get(4));

            for (int i = 7; i < 11; i++) {
                pots.get(i).setNext(pots.get(i + 1));
                pots.get(i).setPrev(pots.get(i - 1));
            }
            pots.get(6).setPrev(pots.get(13));
            pots.get(6).setNext(pots.get(7));

            pots.get(11).setPrev(pots.get(10));
            pots.get(11).setNext(pots.get(12));

            pots.get(12).setPrev(pots.get(11));
            pots.get(12).setNext(pots.get(5));

            pots.get(13).setPrev(pots.get(0));
            pots.get(13).setNext(pots.get(6));

            for (int i = 0; i < mancalaBoard.size(); i++) {
                pots.get(i).setStones(4);
            }
            for (Pit p : pots) {
                System.out.println(p);
            }
        }

        // This method will handle moving stones when a pit is clicked.
        public void pitPressed(int position) {
            System.out.println("Pit " + position + " has been clicked.");
            if (position == 12 || position == 13)
                return; // TODO add error message saying goal pits can't be clicked
            // TODO this should NOT count as a move or cause game to switch to other player
            if(position >= 0 && position <= 5)
            {
            	if(turn)
            	{
            		return;// TODO add error message saying goal pits can't be clicked
                    // TODO this should NOT count as a move or cause game to switch to other player
            	}
            }
            if(position >= 6 && position <= 11)
            {
            	if(!turn)
            	{
            		return;// TODO add error message saying goal pits can't be clicked
                    // TODO this should NOT count as a move or cause game to switch to other player
            	}
            }
            Pit current = pots.get(position);
            int stones = current.getStones();
            if (stones == 0)
                return; // TODO add error message saying empty pits don't count as a move
            // TODO this should NOT count as a move or cause game to switch to other player

            // TODO check whether this pit belongs to the current player, add error message if not

            Pit next = current.getNext();
            while (stones > 0) {
                current.loseStone();
                next.addStone();
                this.repaint();
                next = next.getNext();
                stones--;
            }
            System.out.println("After stones loop");
            if(turn)
            {
            	turn = false;
            }
            else if(!turn)
            {
            	turn = true;
            }
            if(turn)
            {
            	pots.get(11).setNext(pots.get(12));
            }
            else if(!turn)
            {
            	pots.get(11).setNext(pots.get(5));
            }
            if(!turn)
            {
            	pots.get(0).setNext(pots.get(13));
            }
            else if(turn)
            {
            	pots.get(0).setNext(pots.get(6));
            }
            for (Pit p : pots) {
                System.out.println("Pit " + p.position + " stones: " + p.stones);
            }
        }


        public void paint(Graphics g) {
            super.paint(g);
        }
    }

    /**
     * paints a pot
     */
    public static class Pit extends JPanel {
        private static final int MARGIN = 2;
        private static final int STONE_SIZE = 10;
        private int stones;
        private final int position;
        private String label;
        public Pit next;
        public Pit prev;
        private MouseListener listener;
        private Style style;

        public Pit(Style style, int pos) {
            this.style = style;
            this.position = pos;
            addMouseListener(this.listener = new MousePressedListener());
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
        }

        public int getStones() {
            return this.stones;
        }

        public void addStone() {
            stones++;
        }

        public void loseStone() {
            stones--;
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
                Ellipse2D.Double pitBoundaries = new Ellipse2D.Double(MARGIN, MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
                if (pitBoundaries.contains(mousePoint.getX(), mousePoint.getY()))
                    board.pitPressed(position);
            }
        }
    }

    public static class End extends Pit {

        public End(Style style, int pos) {
            super(style, pos);
        }
    }


    public static void main(String... args) {
        Style boardStyle = new BlueStyle();
        board = new BoardPainter(boardStyle, new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 4, 2)));
        SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame();
            /*
            Add button for changing styles here. Whenever new style is chosen, call redraw method
             on board.
             */
            jf.add(board);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setSize(800, 300);
            jf.setVisible(true);
        });
    }
}
