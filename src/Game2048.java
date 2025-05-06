import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;

public class Game2048 {
    private CanvasWindow canvas;
    private TileStorageGraph graph;
    private Random random;
    private Operations operations;

    public Game2048() {
        canvas = new CanvasWindow("2048", 600, 600);
        graph = new TileStorageGraph(4);
        random = new Random();
        operations = new Operations();
        run();
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
        
        if (!storeList.isEmpty()) {
            MatrixCoordinateStorage target = storeList.get(random.nextInt(storeList.size()));
            graph.create(target.getX(), target.getY(), canvas);
        }
        
    }

    public void run() {
        canvas.onKeyDown(event -> {
            switch (event.getKey()) {
                case LEFT_ARROW -> {
                    operations.moveLeft(graph.getMatrix());
                    newTile();
                }

                case RIGHT_ARROW -> {
                    operations.moveRight(graph.getMatrix());
                    newTile();
                }

                case UP_ARROW -> {
                    operations.moveUp(graph.getMatrix());
                    newTile();
                }

                case DOWN_ARROW -> {
                    operations.moveDown(graph.getMatrix());
                    newTile();
                }
            }
        });
        newTile();
        newTile();
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
