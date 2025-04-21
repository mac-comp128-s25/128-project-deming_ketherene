package Game;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class Game {
    private CanvasWindow canvas;
    private Image titleImage;


    public Game() {
        this.canvas = new CanvasWindow("2048", 600, 600);
        
    }

    // public double[][] fourByFour(){

    // }
    public static void main(String[] args) {
        new Game();
    }
}
