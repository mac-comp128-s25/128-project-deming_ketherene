import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;

public class Game2048 {
    private CanvasWindow canvas;
    private Graph graph;
    private Random random;

    public Game2048() {
        canvas = new CanvasWindow("2048", 500, 500);
        graph = new Graph(4);
        random = new Random();
    }

    public void newTile() {
        int n = 1;
        int m = 1;
        List<Storage> storeList = new ArrayList<Storage>();
        while (!graph.hasTile(n, m)) {
            Storage store = new Storage(n, m);
            storeList.add(store);
            n++;
            if (n == 4) {
                m++;
                n = 1;
            }
        }
        Storage target = storeList.get(random.nextInt(storeList.size()));
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
