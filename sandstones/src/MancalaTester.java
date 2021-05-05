import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;


public class MancalaTester {
    static MancalaBoard board;
    static int count;

    /**
     * paints a full board
     */

    public static <clicked> void main(String... args) {
        JFrame initialWindow = new JFrame("Settings");

        String[] stylesList = {"Blue", "Green", "Pink"};
        String[] stonesList = {"3", "4"};

        JComboBox stylesMenu = new JComboBox(stylesList);
        JComboBox stonesMenu = new JComboBox(stonesList);
        JButton startButton = new JButton("Start");
        JButton undo = new JButton("Undo");
        undo.setEnabled(false);
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
            undo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    count++;
                    if (count > 3) {
                        undo.setEnabled(false);
                        count = 0;
                        throw new IllegalStateException("Error: Undo cannot be clicked.");
                    }
                    board.cm.undo();
                    board.repaint();
                    if (board.cm.getLastCommand() == null) {
                        undo.setEnabled(true);
                    }


                }
            });
            for (PitPanel p : board.pits) {
                p.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent event) {
                        Point mousePoint = event.getPoint();
                        Ellipse2D.Double pitBoundaries = p.getPitBoundaries();
                        if (pitBoundaries.contains(mousePoint.getX(), mousePoint.getY())) {
                            try {
                                board.pitPressed(p.getPit().getPosition());
                                board.repaint();
                                undo.setEnabled(true);
                            } catch (IllegalStateException illegalStateException) {
                                JFrame errorMessage = new JFrame("ERROR");
                                JPanel panel = new JPanel();
                                JTextArea error = new JTextArea(illegalStateException.getMessage());
                                error.setFont(new Font("ARIAL", Font.PLAIN, 20));
                                panel.add(error);
                                errorMessage.add(panel);
                                errorMessage.setPreferredSize(new Dimension(400, 400));
                                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                                errorMessage.setLocation(dim.width / 2 - errorMessage.getSize().width / 2, dim.height / 2 - errorMessage.getSize().height / 2);
                                errorMessage.pack();
                                errorMessage.setVisible(true);
                                new Timer(1200, (e) -> {
                                    errorMessage.setVisible(false);
                                    errorMessage.dispose();
                                }).start();
                            }
                        }
                    }

                });
                // board.listeners.add(p.getListener());
            }
            SwingUtilities.invokeLater(() -> {
                JFrame jf = new JFrame("Mancala");
                jf.setLayout(new BorderLayout());
                jf.add(board, BorderLayout.CENTER);
                jf.add(undo, BorderLayout.SOUTH);

                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setSize(800, 300);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                jf.setLocation(dim.width / 2 - jf.getSize().width / 2, dim.height / 2 - jf.getSize().height / 2);
                jf.setVisible(true);

            });

        });

        initialWindow.setSize(500, 300);
        initialWindow.setLayout(new FlowLayout());
        initialWindow.add(stylesMenu);
        initialWindow.add(stonesMenu);
        initialWindow.add(startButton);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        initialWindow.setLocation(dim.width / 2 - initialWindow.getSize().width / 2, dim.height / 2 - initialWindow.getSize().height / 2);
        initialWindow.setVisible(true);

    }

}
