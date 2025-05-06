public class AiAutoHelper {
    private TileStorageGraph graph;
    private Operations operations;

    private static final String[] DIRECTIONS = {"LEFT", "RIGHT", "UP", "DOWN"};

    public AiAutoHelper(TileStorageGraph graph, Operations operations) {
        this.graph = graph;
        this.operations = operations;
    }
}
