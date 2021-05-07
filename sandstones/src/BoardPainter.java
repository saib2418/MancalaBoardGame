import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents GUI part of the game. Constructs and draws the pits and mancalas with stones.
 *
 * @version 3.0
 * @authors Sai, Rammy, Rhea
 */
public class BoardPainter extends JPanel {
    protected ArrayList<PitPanel> pitPanels = new ArrayList<>();
    protected JLabel player = new JLabel();

    protected Style style;

    /**
     * Constructor that takes a style and the number of stones per pit, and
     * creates a panel with 12 pits and 2 end mancalas.
     *
     * @param style        a theme in which the board is drawn
     * @param stonesPerPit the number of stones in each pit at start of game
     */
    public BoardPainter(Style style, int stonesPerPit) {
        this.style = style;
        setLayout(new BorderLayout());

        // a 2x6 grid in the center
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2, 6));

        for (int i = 0; i < 12; i++) {
            Pit p = new Pit(i);
            PitPanel pitPanel = new PitPanel(p, style);
            pitPanels.add(pitPanel);

            center.add(pitPanel);
        }

        add(center, BorderLayout.CENTER);
        // one side
        Pit east = new Pit(12);
        PitPanel eastPanel = new PitPanel(east, style);
        pitPanels.add(eastPanel);

        eastPanel.setPreferredSize(new Dimension(100, 100));

        add(eastPanel, BorderLayout.EAST);

        // the other side
        Pit west = new Pit(13);
        PitPanel westPanel = new PitPanel(west, style);
        pitPanels.add(westPanel);

        westPanel.setPreferredSize(new Dimension(100, 100));

        add(westPanel, BorderLayout.WEST);

        // link pits to each other for dropping stones
        for (int i = 1; i < 5; i++) {
            pitPanels.get(i).getPit().setNext(pitPanels.get(i - 1).getPit());
            pitPanels.get(i).getPit().setPrev(pitPanels.get(i + 1).getPit());
        }
        pitPanels.get(0).getPit().setPrev(pitPanels.get(1).getPit());
        pitPanels.get(0).getPit().setNext(pitPanels.get(13).getPit());

        pitPanels.get(5).getPit().setPrev(pitPanels.get(12).getPit());
        pitPanels.get(5).getPit().setNext(pitPanels.get(4).getPit());

        for (int i = 7; i < 11; i++) {
            pitPanels.get(i).getPit().setNext(pitPanels.get(i + 1).getPit());
            pitPanels.get(i).getPit().setPrev(pitPanels.get(i - 1).getPit());
        }
        pitPanels.get(6).getPit().setPrev(pitPanels.get(13).getPit());
        pitPanels.get(6).getPit().setNext(pitPanels.get(7).getPit());

        pitPanels.get(11).getPit().setPrev(pitPanels.get(10).getPit());
        pitPanels.get(11).getPit().setNext(pitPanels.get(12).getPit());

        pitPanels.get(12).getPit().setPrev(pitPanels.get(11).getPit());
        pitPanels.get(12).getPit().setNext(pitPanels.get(5).getPit());

        pitPanels.get(13).getPit().setPrev(pitPanels.get(0).getPit());
        pitPanels.get(13).getPit().setNext(pitPanels.get(6).getPit());

        for (int i = 0; i < 12; i++) {
            pitPanels.get(i).getPit().setStones(stonesPerPit);
        }

        player.setText("Player -'s turn");
        add(player, BorderLayout.SOUTH);

    }

    /**
     * Getter method for the arraylist of pit panels
     *
     * @return pitPanels
     */
    protected ArrayList<PitPanel> getPitPanels() {
        return pitPanels;
    }
    

    /**
     * Sets each pit to display the number of stones currently in it
     *
     * @param g Graphics drawer
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        super.paintComponent(g2);
        for (PitPanel p : pitPanels) {
            p.paintComponent(g2);
        }
    }
}
