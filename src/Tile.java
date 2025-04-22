import edu.macalester.graphics.Point;

public class Tile {
    private int number;
    private Point tilePoint;

    public Tile(double x, double y) {
        number = 2;
        tilePoint = new Point(x, y);
    }

    public void add(int num) {
        number += num;
    }

    public int getNumber() {
        return number;
    }

    public Point getTilePoint() {
        return tilePoint;
    }
}
