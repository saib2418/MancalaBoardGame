import java.awt.*;

public class BlueStyle implements Style {

    public Color getLineColor() {
        return Color.BLUE.darker().darker().darker().darker();
    }

    public int getPitStrokeThickness() {
        return 8;
    }

    public int getStoneStrokeThickness() {
        return 2;
    }

    public Color getBoardBackgroundColor() {
        return new Color(146, 196, 235);

    }

    public Color getPitBackgroundColor() {
        return Color.BLUE.darker().darker().darker();
    }

    public Color getStoneColor() {
        return Color.WHITE;
    }
}
