import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.ui.Button;

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
        AiHelperButton();
        run();
    }

//     public void run() {
// >>>>>>> ce8e9c6683c39cd131e24c7c627a2c245e1e9cf5
//         canvas.onKeyDown(event -> {
//             switch (event.getKey()) {
//                 case LEFT_ARROW -> {
//                     operations.moveLeft(graph.getMatrix());
//                     newTile();
//                 }

//                 case RIGHT_ARROW -> {
//                     operations.moveRight(graph.getMatrix());
//                     newTile();
//                 }

//                 case UP_ARROW -> {
//                     operations.moveUp(graph.getMatrix());
//                     newTile();
//                 }

//                 case DOWN_ARROW -> {
//                     operations.moveDown(graph.getMatrix());
//                     newTile();
//                 }
//             }
//             winLose();

//         });

//         // run();
//     }

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
                        newTile();
                    }

                    case RIGHT_ARROW -> {
                        operations.moveRight(graph.getMatrix());
                        newTile();
                        newTile();
                    }

                    case UP_ARROW -> {
                        operations.moveUp(graph.getMatrix());
                        newTile();
                        newTile();
                    }

                    case DOWN_ARROW -> {
                        operations.moveDown(graph.getMatrix());
                        newTile();
                        newTile();
                    }
                }
            });
        newTile();
        newTile();
    }

    private boolean winLose(){

        if(graph.isFull()){
           if(graph.openSpaces() > 0){
            
                run();
            
            //System.out.println("open spaces: " + graph.openSpaces());
            //if (graph.openSpaces() == 0){
            //    System.out.println("hello");
           }
            
        }
        if(graph.isFull())
            System.out.println("Graph is full");
        if(graph.isFull() && !operations.canMerge) {
            System.out.println("game is over");
            gameOver();
            return true;
        }

        return false;

    }

    private void gameOver() {
        canvas.onClick(event -> {
            if (winLose()) {
                canvas.removeAll();
                canvas.closeWindow();
            }
        });
    }

    public void AiHelperButton() {
        Button AiHelper = new Button("Play The Game For Me");
        Button AiHelperToo = new Button("Just One Step More");
        AiHelper.setPosition(10, 10);
        AiHelperToo.setPosition(10,50);
        
        Timer autoTimer = new Timer();
        AiAutoHelper helper = new AiAutoHelper(graph);

        TimerTask task = new TimerTask() {
            public void run() {
                if (winLose()) {
                    autoTimer.cancel();
                    return;
                }

                runAiOnce(helper);
            }
        };

        AiHelper.onClick(() -> {
            autoTimer.schedule(task, 0, 300);
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

    public static void main(String[] args) {
        new Game2048();
    }
}
