import java.awt.*;

/**
 * A pink-themed style for the board.
 *
 * @author Rhea
 * @version 1.0
 */
public class PinkStyle implements Style {
    /**
     * Sets pit outline to dark pink
     *
     * @return dark pink color
     */
    public Color getLineColor() {
        return Color.PINK.darker().darker();
    }

    /**
     * Sets pit outlines to thickness of 4
     *
     * @return 4
     */
    public int getPitStrokeThickness() {
        return 4;
    }

    /**
     * Sets stone thickness to 1
     *
     * @return 1
     */
    public int getStoneStrokeThickness() {
        return 1;
    }

    /**
     * Sets board background color to pink
     *
     * @return pink color
     */
    public Color getBoardBackgroundColor() {
        return Color.PINK;
    }

    /**
     * Sets board background color to light pink
     *
     * @return light pink color
     */
    public Color getPitBackgroundColor() {
        return Color.PINK.darker().brighter().brighter();
    }

    /**
     * Sets stone color to dark red
     *
     * @return dark red color
     */
    public Color getStoneColor() {
        return Color.RED.darker().darker();
    }
}
