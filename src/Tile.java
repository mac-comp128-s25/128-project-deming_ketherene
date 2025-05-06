
import java.awt.*;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

public class Tile {
    private int number;
    private Rectangle rectangle;
    private GraphicsText label;
    private CanvasWindow canvas;

    public Tile(double x, double y, CanvasWindow canvas) {
        this.canvas = canvas;
        number = 2;
        rectangle = new Rectangle(x, y, 100, 100);
        rectangle.setFillColor(Color.ORANGE);
        label = new GraphicsText(Integer.toString(number), x + 50, y + 50);

        canvas.add(rectangle);
        canvas.add(label);
    }

    public void add(int num) {
        number += num;
        label.setText(Integer.toString(number));
    }

    public int getNumber() {
        return number;
    }

    public Rectangle getTileRectangle() {
        return rectangle;
    }

    public void moveTo(double x, double y) {
        rectangle.setPosition(x, y);
        label.setPosition(x + 50, y + 50);
    }

    public void removeFromCanvas() {
        canvas.remove(rectangle);
        canvas.remove(label);
    }
    
}
