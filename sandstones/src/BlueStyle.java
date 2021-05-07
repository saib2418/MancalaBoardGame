import java.awt.*;

/**
 * A blue-themed style for the board.
 *
 * @author Rhea
 * @version 1.0
 */
public class BlueStyle implements Style {

    /**
     * Sets the pit outline to a dark blue color
     *
     * @return dark blue color
     */
    public Color getLineColor() {
        return Color.BLUE.darker().darker().darker().darker();
    }

    /**
     * Sets the pit outlines to a thickness of 8.
     *
     * @return 8
     */
    public int getPitStrokeThickness() {
        return 8;
    }

    /**
     * Sets the stones to a thickness of 2
     *
     * @return 2
     */
    public int getStoneStrokeThickness() {
        return 2;
    }

    /**
     * Sets the board background to a light blue color.
     *
     * @return light blue color
     */
    public Color getBoardBackgroundColor() {
        return new Color(146, 196, 235);

    }

    /**
     * Sets the pit background color to dark blue
     *
     * @return dark blue color
     */
    public Color getPitBackgroundColor() {
        return Color.BLUE.darker().darker().darker();
    }

    /**
     * Sets the stone color to white
     *
     * @return white color
     */
    public Color getStoneColor() {
        return Color.WHITE;
    }
}
