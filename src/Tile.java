import edu.macalester.graphics.Rectangle;

public class Tile {
    private int number;
    private Rectangle tile;

    public Tile(double x, double y) {
        number = 2;
        tile = new Rectangle(x, y, 100, 100);
    }

    public void add(int num) {
        number += num;
    }

    public int getNumber() {
        return number;
    }
}
