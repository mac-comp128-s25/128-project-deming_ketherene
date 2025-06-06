import java.awt.Color;
import java.awt.Font;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;

public class Game2048 {
    private CanvasWindow canvas;
    private TileStorageGraph graph;
    private Random random;
    private Operations operations;
    private ArrayDeque<Tile[][]> undoList;
    private Tile[][] previousScreen;
    private boolean isRunning;
    private Timer autoTimer;
    private TimerTask task;

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
        isRunning = false;
        autoTimer = new Timer();
        task = new TimerTask() {
            public void run() {
                // empty
            }
        };
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
        undoList.push(copyHelper(tileMatrix));
        // System.out.println("Undo List:" + undoList);
    }

    public Tile[][] copyHelper(Tile[][] matrix){
        Tile[][] newMatrix = new Tile[matrix.length][matrix[0].length];
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                // System.out.println("hii!!!!!");
                if(matrix[y][x] != null){
                    newMatrix[y][x] = new Tile(matrix[y][x].getX(),matrix[y][x].getY(),canvas);
                    // System.out.println("New Matrix:" + newMatrix[y][x].getNumber());
                    newMatrix[y][x].getTileRectangle().setPosition(matrix[y][x].getX(),matrix[y][x].getY());
                    newMatrix[y][x].setNum(matrix[y][x].getNumber());
                    newMatrix[y][x].add(0);
                    newMatrix[y][x].updateTile();
                } 
                else{
                    // newMatrix[y][x].removeFromCanvas();
                    newMatrix[y][x] = null;
                }
            }
        }
        return newMatrix;
    }

    public void run() {
            canvas.onKeyDown(event -> {
                switch (event.getKey()) {
                    case LEFT_ARROW -> {
                        operations.moveLeft(graph.getMatrix());
                        newTile();
                        newTile();
                        winLose();
                    }

                    case RIGHT_ARROW -> {
                        operations.moveRight(graph.getMatrix());
                        newTile();
                        newTile();
                        winLose();
                    }

                    case UP_ARROW -> {
                        operations.moveUp(graph.getMatrix());
                        newTile();
                        newTile();
                        winLose();
                    }

                    case DOWN_ARROW -> {
                        operations.moveDown(graph.getMatrix());
                        newTile();
                        newTile();
                        winLose();
                    }
                }
            });
        newTile();
        newTile();
    }

    private void gameOver(String message){
        canvas.pause(3000);
        GraphicsText messageBanner = new GraphicsText(message);
        messageBanner.setFontSize(50);
        messageBanner.setFillColor(Color.RED);
        messageBanner.setFont(FontStyle.BOLD, 50);
        messageBanner.setStrokeWidth(2);
        messageBanner.setStrokeColor(Color.BLACK);
        canvas.add(messageBanner,150,300 );
    }


    private boolean winLose(){
        if(graph.isFull() && !graph.canMerge()){
            System.out.println("game is over");
            gameOver("GAME OVER");
            newGameButton();
            return true;
        }
        if (graph.is2048()) {
            gameOver("You Win!!!");
            System.out.println("You win");
            newGameButton();
            return true;
        }
        return false;

    }

    public void AiHelperButton() {
        Button AiHelper = new Button("Play The Game For Me");
        Button AiHelperToo = new Button("Just One Step More");
        AiHelper.setPosition(325, 10);
        AiHelperToo.setPosition(340,50);
        
        AiAutoHelper helper = new AiAutoHelper(graph);

        AiHelper.onClick(() -> {
            if (isRunning) {
                autoTimer.cancel();
                isRunning = false;
            } else {
                autoTimer = new Timer();
                task = new TimerTask() {
                    public void run() {
                        if (winLose()) {
                            autoTimer.cancel();
                            return;
                        }
        
                        runAiOnce(helper);
                    }
                };
                autoTimer.schedule(task, 0, 800);
                isRunning = true;
            }
            
        });


        AiHelperToo.onClick(() -> {  
            if (winLose()) return;
            
            runAiOnce(helper);
        });
        canvas.add(AiHelper);
        canvas.add(AiHelperToo);
    }

    private void runAiOnce(AiAutoHelper helper) {
        String direction = helper.theMovingDirection();
        if (direction == null) {
            System.out.println("no path");
            return;
        }

            doMove(direction);
            winLose();
    }

    private void doMove(String direction) {
        if (direction.equals("Left")) {
            operations.moveLeft(graph.getMatrix());
        }
        if (direction.equals("Right")) {
            operations.moveRight(graph.getMatrix());
        }
        if (direction.equals("Up")) {
            operations.moveUp(graph.getMatrix());
        }
        if (direction.equals("Down")) {
            operations.moveDown(graph.getMatrix());
        }
        
        newTile();
        newTile();
    }

    private void undoButton() {
        Button backButton = new Button("UNDO");
        backButton.onClick(() -> {
            // System.out.println("hello!!!!");
            // System.out.println("Current grapg" + graph.getMatrix());
            previousScreen = undoList.pop();
            // System.out.println("Popped"+ previousScreen);
            graph.setMatrix(previousScreen,canvas);
        });
        backButton.setPosition(20, 40);
        canvas.add(backButton);
    }

    private void newGameButton(){
        Button newGame = new Button("New Game");
        newGame.onClick(()->{
            canvas.removeAll();
            canvas.closeWindow();
            new Game2048();
        }
        );
        newGame.setPosition(150,350);
        canvas.add(newGame);
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
