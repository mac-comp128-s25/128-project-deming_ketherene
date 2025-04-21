
import edu.macalester.graphics.CanvasWindow;

public class Game {
    private CanvasWindow canvas;
    private Graph graph;

    public Game() {
        this.canvas = new CanvasWindow("2048", 500, 500);
        this.graph = new Graph(4);
        

    }

    public static void main(String[] args) {
        new Game();
    }
}
