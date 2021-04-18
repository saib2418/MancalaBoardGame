import java.awt.*;

public class PinkStyle implements Style {
    public Color getLineColor() {
        return Color.PINK.darker().darker();
    }

    public int getPitStrokeThickness() {
        return 4;
    }

    public int getStoneStrokeThickness() {
        return 1;
    }

    public Color getBoardBackgroundColor() {
        return Color.PINK;
    }

    public Color getPitBackgroundColor() {
        return Color.PINK.darker().brighter().brighter();
    }

    public Color getStoneColor() {
        return Color.RED.darker().darker();
    }
}
