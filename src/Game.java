
import edu.macalester.graphics.CanvasWindow;

public class Game {
    private CanvasWindow canvas;
    private Tile tile;

    public Game() {
        this.canvas = new CanvasWindow("2048", 500, 500);
    }

    public static void main(String[] args) {
        new Game();
    }
}
