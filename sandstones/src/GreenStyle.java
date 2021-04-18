import java.awt.*;

public class GreenStyle implements Style {
    public Color getLineColor() {
        return Color.GREEN.darker().darker();
    }

    public int getPitStrokeThickness() {
        return 4;
    }

    public int getStoneStrokeThickness() {
        return 2;
    }

    public Color getBoardBackgroundColor() {
        return new Color(146, 235, 146);
    }

    public Color getPitBackgroundColor() {
        return Color.GREEN.darker();
    }

    public Color getStoneColor() {
        return Color.GREEN.darker().darker().darker();
    }
}
