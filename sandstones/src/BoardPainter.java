import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;

public class BoardPainter extends JPanel {
    protected ArrayList<PitPanel> pits = new ArrayList<>();
    protected ArrayList<EventListener> listeners = new ArrayList<>();
    protected JLabel player = new JLabel();


    protected Style style;

    public BoardPainter(Style style, int stonesPerPit) {
        this.style = style;
        setLayout(new BorderLayout());

        // a 2x6 grid in the center
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2, 6));

        for (int i = 0; i < 12; i++) {
            Pit p = new Pit(i);
            PitPanel pitPanel = new PitPanel(p, style);
            pits.add(pitPanel);

            // listeners.add(p.getListener());
            center.add(pitPanel);
        }

        add(center, BorderLayout.CENTER);
        // one side
        Pit east = new Pit(12);
        PitPanel eastPanel = new PitPanel(east, style);
        // listeners.add(east.getListener());
        pits.add(eastPanel);

        eastPanel.setPreferredSize(new Dimension(100, 100));

        add(eastPanel, BorderLayout.EAST);

        // the other side
        Pit west = new Pit(13);
        PitPanel westPanel = new PitPanel(west, style);
        //listeners.add(west.getListener());
        pits.add(westPanel);

        westPanel.setPreferredSize(new Dimension(100, 100));

        add(westPanel, BorderLayout.WEST);

        // link pits to each other for dropping stones
        for (int i = 1; i < 5; i++) {
            pits.get(i).getPit().setNext(pits.get(i - 1).getPit());
            pits.get(i).getPit().setPrev(pits.get(i + 1).getPit());
        }
        pits.get(0).getPit().setPrev(pits.get(1).getPit());
        pits.get(0).getPit().setNext(pits.get(13).getPit()); // This might need to be changed when the skip other player's mancala part works.

        pits.get(5).getPit().setPrev(pits.get(12).getPit());
        pits.get(5).getPit().setNext(pits.get(4).getPit());

        for (int i = 7; i < 11; i++) {
            pits.get(i).getPit().setNext(pits.get(i + 1).getPit());
            pits.get(i).getPit().setPrev(pits.get(i - 1).getPit());
        }
        pits.get(6).getPit().setPrev(pits.get(13).getPit());
        pits.get(6).getPit().setNext(pits.get(7).getPit());

        pits.get(11).getPit().setPrev(pits.get(10).getPit());
        pits.get(11).getPit().setNext(pits.get(12).getPit());

        pits.get(12).getPit().setPrev(pits.get(11).getPit());
        pits.get(12).getPit().setNext(pits.get(5).getPit());

        pits.get(13).getPit().setPrev(pits.get(0).getPit());
        pits.get(13).getPit().setNext(pits.get(6).getPit());

        for (int i = 0; i < 12; i++) {
            pits.get(i).getPit().setStones(stonesPerPit);
        }
        for (PitPanel p : pits) {
            System.out.println(p.getPit());
        }

        player.setText("Player -'s turn");
        add(player, BorderLayout.SOUTH);
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        super.paintComponent(g2);
        for (PitPanel p : pits) {
            p.paintComponent(g2);
        }
    }
}
