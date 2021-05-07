import java.awt.*;

/**
 * An interface for representing a style of the game.
 * Uses strategy pattern.
 *
 * @author Rhea
 * @version 1.0
 */
public interface Style {
    /**
     * Set a color for the pit outlines.
     *
     * @return pit outline color
     */
    Color getLineColor();

    /**
     * Set a thickness for the pit outlines.
     *
     * @return pit outine thickness
     */
    int getPitStrokeThickness();

    /**
     * Set a thickness for the stones
     *
     * @return stone thickness
     */
    int getStoneStrokeThickness();

    /**
     * Set a background color for the board
     *
     * @return board background color
     */
    Color getBoardBackgroundColor();

    /**
     * Set a background color for the pits
     *
     * @return pit background color
     */
    Color getPitBackgroundColor();

    /**
     * Set a color for the stones
     *
     * @return stone color
     */
    Color getStoneColor();
}
