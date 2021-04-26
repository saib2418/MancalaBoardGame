import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;


public class MancalaTester {
    static MancalaBoard board;

    /**
     * paints a full board
     */

    public static void main(String... args) {
        Style boardStyle = new BlueStyle();
        board = new MancalaBoard(boardStyle, new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 4, 2)));
        for (Pit p : board.pits) {
            p.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {
                    Point mousePoint = e.getPoint();
                    Ellipse2D.Double pitBoundaries = p.getPitBoundaries();
                    if (pitBoundaries.contains(mousePoint.getX(), mousePoint.getY()))
                        board.pitPressed(p.getPosition());
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
