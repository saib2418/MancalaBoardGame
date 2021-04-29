import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;

public class BoardPainter extends JPanel {
    protected ArrayList<Pit> pits = new ArrayList<>();
    protected java.util.List<Integer> mancalaBoard;
    protected ArrayList<EventListener> listeners = new ArrayList<>();
    protected Style style;

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


            listeners.add(p.getListener());
            center.add(p);
        }

        add(center, BorderLayout.CENTER);

        // one side
        Pit east = new Pit(style, 12);
        listeners.add(east.getListener());
        pits.add(east);

        add(east, BorderLayout.EAST);
        east.setPreferredSize(new

                Dimension(100, 0));

        // the other side
        Pit west = new Pit(style, 13);
        listeners.add(west.getListener());
        pits.add(west);

        add(west, BorderLayout.WEST);
        west.setPreferredSize(new

                Dimension(100, 0));

        // link pits to each other for dropping stones
        for (int i = 1; i < 5; i++) {
            pits.get(i).setNext(pits.get(i - 1));
            pits.get(i).setPrev(pits.get(i + 1));
        }
        pits.get(0).

                setPrev(pits.get(1));
        pits.get(0).

                setNext(pits.get(13)); // This might need to be changed when the skip other player's mancala part works.

        pits.get(5).

                setPrev(pits.get(12));
        pits.get(5).

                setNext(pits.get(4));

        for (int i = 7; i < 11; i++) {
            pits.get(i).setNext(pits.get(i + 1));
            pits.get(i).setPrev(pits.get(i - 1));
        }
        pits.get(6).

                setPrev(pits.get(13));
        pits.get(6).

                setNext(pits.get(7));

        pits.get(11).

                setPrev(pits.get(10));
        pits.get(11).

                setNext(pits.get(12));

        pits.get(12).

                setPrev(pits.get(11));
        pits.get(12).

                setNext(pits.get(5));

        pits.get(13).

                setPrev(pits.get(0));
        pits.get(13).

                setNext(pits.get(6));

        for (
                int i = 0; i < mancalaBoard.size(); i++) {
            pits.get(i).setStones(4);
        }
        for (Pit p : pits) {
            System.out.println(p);
        }


    }


    public void paint(Graphics g) {
        super.paint(g);
    }
}
