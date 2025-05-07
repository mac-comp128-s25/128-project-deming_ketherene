public class AiAutoHelper {
    private TileStorageGraph graph;
    private Operations operations;

    public AiAutoHelper(TileStorageGraph graph, Operations operations) {
        this.graph = graph;
        this.operations = operations;
    }

    private int scoreForEmpty(Tile[][] testArray) {
        int scoreEmpty = 0;

        for (Tile[] row : testArray) {
            for (Tile tile : row) {
                if (tile == null) {
                    scoreEmpty ++;
                }
            }
        }

        return scoreEmpty;
    }

    private int scoreForCombination(Tile[][] testArray) {
        int scoreCombination = 0;
        return 0;

    }

    private Tile[][] testLeft() {
        Tile[][] leftArray = graph.getMatrix();
        operations.moveLeft(leftArray);
        return leftArray;
    }

    private Tile[][] testRight() {
        Tile[][] rightArray = graph.getMatrix();
        operations.moveRight(rightArray);
        return rightArray;
    }

    private Tile[][] tesUp() {
        Tile[][] upArray = graph.getMatrix();
        operations.moveUp(upArray);
        return upArray;
    }

    private Tile[][] testDown() {
        Tile[][] downArray = graph.getMatrix();
        operations.moveDown(downArray);
        return downArray;
    }
    
}
