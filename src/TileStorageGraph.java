public class TileStorageGraph {
    private Tile[][] matrix;
    private int num;

    public TileStorageGraph(int num) {
        this.num = num;
        matrix = new Tile[num][num];
    }

    private void createHelper(int X, int Y) {
        for (int y = 0; y < matrix.length; y++) {
            if (y == Y) {
                for (int x = 0; x < matrix[y].length; x++) {
                    if (x == X) {
                        Tile tile = new Tile((X+1)*50, (Y+1)*50);
                        matrix[y][x] = tile;
                    }
                }
            }
        }
    }

    public void create(int X, int Y) {
        createHelper(X, Y);
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

    public Tile[][] getMatrix() {
        return matrix;
    }

    public int getNum() {
        return num;
    }
}
