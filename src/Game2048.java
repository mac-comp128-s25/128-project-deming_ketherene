import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;

public class Game2048 {
    private CanvasWindow canvas;
    private TileStorageGraph graph;
    private Random random;
    private Operations operations;
    private ArrayDeque<Tile[][]> undoList;
    private Tile[][] previousScreen;

    public Game2048() {
        canvas = new CanvasWindow("2048", 600, 600);
        GraphicsText title = new GraphicsText("2048");
        title.setFontSize(90);
        title.setFillColor(Color.YELLOW);
        title.setStrokeColor(Color.BLACK);
        title.setStrokeWidth(2);
        title.setPosition(100,80);
        canvas.add(title);
        graph = new TileStorageGraph(4);
        random = new Random();
        operations = new Operations();
        undoList = new ArrayDeque<>();
        undoButton();
        AiHelperButton();
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
        Tile[][] matrixCopy = tileMatrix.clone();
        undoList.push(matrixCopy);
        System.out.println("Undo List:" + undoList);
    }

    public void run() {
        canvas.onKeyDown(event -> {
            switch (event.getKey()) {
                case LEFT_ARROW -> {
                    operations.moveLeft(graph.getMatrix());
                    newTile();
                    winLose();
                }

                case RIGHT_ARROW -> {
                    operations.moveRight(graph.getMatrix());
                    newTile();
                    winLose();
                }

                case UP_ARROW -> {
                    operations.moveUp(graph.getMatrix());
                    newTile();
                    winLose();
                }

                case DOWN_ARROW -> {
                    operations.moveDown(graph.getMatrix());
                    newTile();
                    winLose();
                }
            }
        });
        newTile();
        newTile();
    }

    private void winLose() {
        if(graph.isFull() && !graph.canMerge()){
            System.out.println("game is over");
            gameOver("GAME OVER");
        }
        if (graph.is2048()) {
            gameOver("You Win!!!");
            System.out.println("You win");
        }
        
    }

    private void gameOver(String message){
        canvas.pause(5000);
        GraphicsText messageBanner = new GraphicsText(message);
        messageBanner.setFontSize(50);
        messageBanner.setFillColor(Color.RED);
        canvas.add(messageBanner,200,300 );
        // canvas.removeAll();
        // canvas.closeWindow();
        // new Game2048();
    }


    public void AiHelperButton() {
        Button AiHelper = new Button("Auto Run");

        AiHelper.onClick(() -> {

        });
        canvas.add(AiHelper);
    }

    private void undoButton() {
        Button backButton = new Button("UNDO");
        backButton.onClick(() -> {
            // System.out.println("hello!!!!");
            // System.out.println("Current grapg" + graph.getMatrix());
            previousScreen = undoList.pop();
            // System.out.println("Popped"+ previousScreen);
            graph.setMatrix(previousScreen);
        });
        backButton.setPosition(20, 40);
        canvas.add(backButton);
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
