import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;


public class MancalaTester {
    static MancalaBoard board;

    /**
     * paints a full board
     */

    public static void main(String... args) {
        JFrame initialWindow = new JFrame("Settings");

        String[] stylesList = {"Blue", "Green", "Pink"};
        String[] stonesList = {"3", "4"};

        JComboBox stylesMenu = new JComboBox(stylesList);
        JComboBox stonesMenu = new JComboBox(stonesList);
        JButton startButton = new JButton("Start");
        JButton undo = new JButton("Undo");
        undo.setBounds(0, 0, 75, 50);

        startButton.addActionListener(e -> {
            initialWindow.dispatchEvent(new WindowEvent(initialWindow, WindowEvent.WINDOW_CLOSING));
            String styleStr = stylesMenu.getSelectedItem().toString().toLowerCase();
            int numStones = Integer.parseInt(stonesMenu.getSelectedItem().toString());
            Style style = null;
            switch (styleStr) {
                case "blue":
                    style = new BlueStyle();
                    break;
                case "green":
                    style = new GreenStyle();
                    break;
                case "pink":
                    style = new PinkStyle();
                    break;
            }

            board = new MancalaBoard(style, numStones);
            for (Pit p : board.pits) {
                p.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        Point mousePoint = e.getPoint();
                        Ellipse2D.Double pitBoundaries = p.getPitBoundaries();
                        if (pitBoundaries.contains(mousePoint.getX(), mousePoint.getY())) {
                            board.pitPressed(p.getPosition());
                        }
                    }

                });
                board.listeners.add(p.getListener());
            }
            SwingUtilities.invokeLater(() -> {
                JFrame jf = new JFrame("Mancala");
                jf.add(undo);
                jf.setLayout(new BorderLayout());
                jf.add(board, BorderLayout.CENTER);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setSize(800, 300);
                jf.setVisible(true);

            });

        });

        initialWindow.setSize(500, 300);
        initialWindow.setLayout(new FlowLayout());
        initialWindow.add(stylesMenu);
        initialWindow.add(stonesMenu);
        initialWindow.add(startButton);
        initialWindow.setVisible(true);

    }
}
