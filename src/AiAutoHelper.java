import java.util.HashMap;
import java.util.Map;

public class AiAutoHelper {
    private TileStorageGraph graph;

    public AiAutoHelper(TileStorageGraph graph) {
        this.graph = graph;
    }

    public String theMovingDirection() {
        int n = 0;
        String result = "";
        Map<String, Integer> storeNum = new HashMap<>();
        storeNum.put("Left", testLeft());
        storeNum.put("Right", testRight());
        storeNum.put("Up", testUp());
        storeNum.put("Down", testDown());
        for (Map.Entry<String, Integer> entry : storeNum.entrySet()) {
            if (entry.getValue() > n) {
                n = entry.getValue();
                result = entry.getKey();
            }
        }

        return result;
    }
    
    private Tile[][] deepCopy(Tile[][] original) {
        Tile[][] copy = new Tile[original.length][original[0].length];
        for (int y = 0; y < original.length; y++) {
            for (int x = 0; x < original[0].length; x++) {
                if (original[y][x] != null) {
                    int number = original[y][x].getNumber();
                    copy[y][x] = new FakeTile(number);
                }
            }
        }
        return copy;
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

        return scoreEmpty * 5;
    }

    private int scoreForCombination(int num) {
        return num * 5;

    }

    private int scoreForHighestNumber(Tile[][] testArray) {
        int num = 0;
        for (Tile[] row : testArray) {
            for (Tile tile : row) {
                if (tile != null && tile.getNumber() > num) {
                    num = tile.getNumber();
                }
            }
        }

        return num * 20;
    }

    private int testLeft() {
        Tile[][] leftArray = deepCopy(graph.getMatrix());
        Operations newOperations = new Operations();
        newOperations.moveLeft(leftArray);
        int mergeScore = newOperations.getMergeScore();
        int score = scoreForEmpty(leftArray) + scoreForCombination(mergeScore) + scoreForHighestNumber(leftArray);
        return score;
    }

    private int testRight() {
        Tile[][] rightArray = deepCopy(graph.getMatrix());
        Operations newOperations = new Operations();
        newOperations.moveRight(rightArray);
        int mergeScore = newOperations.getMergeScore();
        int score = scoreForEmpty(rightArray) + scoreForCombination(mergeScore) + scoreForHighestNumber(rightArray);
        return score;
    }

    private int testUp() {
        Tile[][] upArray = deepCopy(graph.getMatrix());
        Operations newOperations = new Operations();
        newOperations.moveUp(upArray);
        int mergeScore = newOperations.getMergeScore();
        int score = scoreForEmpty(upArray) + scoreForCombination(mergeScore) + scoreForHighestNumber(upArray);
        return score;
    }

    private int testDown() {
        Tile[][] downArray = deepCopy(graph.getMatrix());
        Operations newOperations = new Operations();
        newOperations.moveDown(downArray);
        int mergeScore = newOperations.getMergeScore();
        int score = scoreForEmpty(downArray) + scoreForCombination(mergeScore) + scoreForHighestNumber(downArray);
        return score;
    }
    
}
