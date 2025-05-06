import edu.macalester.graphics.CanvasWindow;

public class TileStorageGraph {
    private Tile[][] matrix;
    private int num;

    public TileStorageGraph(int num) {
        this.num = num;
        matrix = new Tile[num][num];
    }

    private void createHelper(int X, int Y, CanvasWindow canvas) {
        for (int y = 0; y < matrix.length; y++) {
            if (y == Y) {
                for (int x = 0; x < matrix[y].length; x++) {
                    if (x == X) {
                        Tile tile = new Tile((X+1)*100, (Y+1)*100, canvas);
                        matrix[y][x] = tile;
                    }
                }
            }
        }
    }

    public void create(int X, int Y, CanvasWindow canvas) {
        createHelper(X, Y, canvas);
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

    public boolean isFull(){
        for (int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[y].length; x++){
                if (matrix[y][x] == null) {
                    return false;
                }
            }
        }
        System.out.println("Graph full!!!");
        return true;
    }

    public int openSpaces(){
        int openSpaces = 0;
        for (int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[y].length; x++){
                if (matrix[y][x] == null) {
                    openSpaces++;
                }
            }
        }
        return openSpaces;
    }
}
