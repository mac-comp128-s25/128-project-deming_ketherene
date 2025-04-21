import edu.macalester.graphics.Point;

public class Tile {
    private int number;
    private Point tile;

    public Tile(double x, double y) {
        number = 2;
        tile = new Point(x, y);
    }

    public void add(int num) {
        number += num;
    }

    public int getNumber() {
        return number;
    }

    public Point getTile() {
        return tile;
    }
}
