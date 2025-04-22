
import edu.macalester.graphics.CanvasWindow;

public class Game2048 {
    private CanvasWindow canvas;
    private Graph graph;

    public Game2048() {
        this.canvas = new CanvasWindow("2048", 500, 500);
        this.graph = new Graph(4);
        

    }

    

    public static void main(String[] args) {
        new Game2048();
    }
}
