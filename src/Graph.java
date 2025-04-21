public class Graph {
    private Tile[][] matrix;

    public Graph(int num) {
        matrix = new Tile[num][num];
    }

    private void addHelper(Tile tile, int X, int Y) {
        for (int y = 0; y < matrix.length; y++) {
            if (y == Y - 1) {
                for (int x = 0; x < matrix[y].length; x++) {
                    if (x == X - 1) {
                        matrix[y][x] = tile;
                    }
                }
            }
        }
    }

    
    
    public void add(Tile tile, int X, int Y) {
        addHelper(tile, X, Y);
    }
}
