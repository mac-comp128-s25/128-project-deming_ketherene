import edu.macalester.graphics.Point;

public class Graph {
    private Point[][] matrix;

    public Graph(int num) {
        matrix = new Point[num][num];
    }

    private void addHelper(Point point, int X, int Y) {
        for (int y = 0; y < matrix.length; y++) {
            if (y == Y - 1) {
                for (int x = 0; x < matrix[y].length; x++) {
                    if (x == X - 1) {
                        matrix[y][x] = point;
                    }
                }
            }
        }
    }

    
    
    public void add(Point point, int X, int Y) {
        addHelper(point, X, Y);
    }
}
