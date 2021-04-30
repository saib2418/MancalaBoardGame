import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initialWindow.dispatchEvent(new WindowEvent(initialWindow, WindowEvent.WINDOW_CLOSING));
                String styleStr = stylesMenu.getSelectedItem().toString().toLowerCase();
                int numStones = Integer.parseInt(stonesMenu.getSelectedItem().toString());
                Style style = null;
                if (styleStr.equals("blue")) {
                    style = new BlueStyle();
                } else if (styleStr.equals("green")) {
                    style = new GreenStyle();
                } else if (styleStr.equals("pink")) {
                    style = new PinkStyle();
                }

                board = new MancalaBoard(style, numStones);
                for (Pit p : board.pits) {
                    p.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                        }

                        public void mousePressed(MouseEvent e) {
                            Point mousePoint = e.getPoint();
                            Ellipse2D.Double pitBoundaries = p.getPitBoundaries();
                            if (pitBoundaries.contains(mousePoint.getX(), mousePoint.getY())) {
                                board.pitPressed(p.getPosition());
                            }
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                        }

                        public void mouseExited(MouseEvent e) {
                        }
                    });
                    board.listeners.add(p.getListener());
                }
                SwingUtilities.invokeLater(() -> {
                    JFrame jf = new JFrame("Mancala");
                    jf.setLayout(new BorderLayout());

                    jf.add(board, BorderLayout.CENTER);

                    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    jf.setSize(800, 300);
                    jf.setVisible(true);
                });

            }
        });

        initialWindow.setSize(500, 300);
        initialWindow.setLayout(new FlowLayout());
        initialWindow.add(stylesMenu);
        initialWindow.add(stonesMenu);
        initialWindow.add(startButton);
        initialWindow.setVisible(true);

    }
}
