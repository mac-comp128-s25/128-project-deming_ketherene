
import java.awt.*;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

public class Tile {
    private int number;
    private Rectangle rectangle;
    private GraphicsText label;

    public Tile(double x, double y) {
        number = 2;
        rectangle = new Rectangle(x, y, 100, 100);
        rectangle.setFillColor(Color.ORANGE);
        label = new GraphicsText(Integer.toString(number), x + 35, y + 35);
    }

    public void add(int num) {
        number += num;
        label.setText(Integer.toString(number));
    }

    public int getNumber() {
        return number;
    }

    public Rectangle getTilePoint() {
        return rectangle;
    }
}
