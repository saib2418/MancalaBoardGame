import java.awt.*;

/**
 * A green-themed style for the board.
 *
 * @author Rhea
 * @version 1.0
 */
public class GreenStyle implements Style {
    /**
     * Sets pit outline to a dark green color
     *
     * @return dark green color
     */
    public Color getLineColor() {
        return Color.GREEN.darker().darker();
    }

    /**
     * Sets pit outline thickness to 4
     *
     * @return 4
     */
    public int getPitStrokeThickness() {
        return 4;
    }

    /**
     * Sets stone thickness to 2
     *
     * @return 2
     */
    public int getStoneStrokeThickness() {
        return 2;
    }

    /**
     * Sets board background color to light green.
     *
     * @return light green color
     */
    public Color getBoardBackgroundColor() {
        return new Color(146, 235, 146);
    }

    /**
     * Sets pit background color to dark green
     *
     * @return dark green color
     */
    public Color getPitBackgroundColor() {
        return Color.GREEN.darker();
    }

    /**
     * Sets stone color to very dark green color
     *
     * @return very dark green color
     */
    public Color getStoneColor() {
        return Color.GREEN.darker().darker().darker();
    }
}
