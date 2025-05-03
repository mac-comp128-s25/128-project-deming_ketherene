import java.util.ArrayList;
import java.util.List;

public class Operations {
    private TileStorageGraph graph;
    
    public Operations(TileStorageGraph graph) {
        this.graph = graph;
    }

    public void moveLeft(Tile[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            List<Tile> tempList = new ArrayList<>();

            for (int x = 0; x < matrix[y].length; y++) {
                if (matrix[y][x] != null) {
                    tempList.add(matrix[y][x]);
                }

                for (int n = 0; n < tempList.size(); n++) {
                    if (tempList.get(n) == null) {
                        tempList.remove(n);
                    }
                }
            }
        }
    }

    public void moveRight(Tile[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; y++) {
                
            }
        }
    }

    public void moveUp(Tile[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; y++) {
                
            }
        }
    }

    public void moveDown(Tile[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; y++) {
                
            }
        }
    }
}
