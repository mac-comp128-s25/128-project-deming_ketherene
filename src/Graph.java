public class Graph {
    private Tile[][] matrix;

    public Graph(int num) {
        matrix = new Tile[num][num];
    }

    private void createHelper(Tile tile, int X, int Y) {
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

    public void create(Tile tile, int X, int Y) {
        createHelper(tile, X, Y);
    }

    public boolean hasTile(int x, int y) {
        if (matrix[y][x] == null) {
            return false;
        }
        return true;
    }

    public Tile getTile(int x, int y) {
        return matrix[y][x];
    }
}
