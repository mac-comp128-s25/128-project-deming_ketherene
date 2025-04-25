import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;

public class Game2048 {
    private CanvasWindow canvas;
    private TileStorageGraph graph;
    private Random random;

    public Game2048() {
        canvas = new CanvasWindow("2048", 500, 500);
        graph = new TileStorageGraph(4);
        random = new Random();
    }

    public void newTile() {
        List<MatrixCoordinateStorage> storeList = new ArrayList<MatrixCoordinateStorage>();
        Tile[][] tileMatrix = graph.getMatrix();
        
        for (int y = 0; y < tileMatrix.length; y++) {
            for (int x = 0; x < tileMatrix[y].length; x++) {
                if (!graph.hasTile(x, y)) {
                    storeList.add(new MatrixCoordinateStorage(x, y));
                }
            }
        }
        
        MatrixCoordinateStorage target = storeList.get(random.nextInt(storeList.size()));
        graph.create(target.getX(), target.getY());
    }

    public void run() {
        
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
