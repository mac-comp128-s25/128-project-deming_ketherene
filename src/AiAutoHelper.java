public class AiAutoHelper {
    private TileStorageGraph graph;
    private Operations operations;

    private static final String[] DIRECTIONS = {"LEFT", "RIGHT", "UP", "DOWN"};

    public AiAutoHelper(TileStorageGraph graph, Operations operations) {
        this.graph = graph;
        this.operations = operations;
    }

    public void doBestMove(int depth) {
        Tile[][] current = graph.getMatrix();
        String bestDir = null;
        int bestScore = Integer.MIN_VALUE;

        for (String dir : DIRECTIONS) {
            Tile[][] newBoard = deepCopy(current);
            moveBoard(newBoard, dir);
            if (!boardsEqual(current, newBoard)) {
                int score = evaluateMove(newBoard, depth - 1);
                if (score > bestScore) {
                    bestScore = score;
                    bestDir = dir;
                }
            }
        }

        if (bestDir != null) {
            switch (bestDir) {
                case "LEFT" -> operations.moveLeft(graph.getMatrix());
                case "RIGHT" -> operations.moveRight(graph.getMatrix());
                case "UP" -> operations.moveUp(graph.getMatrix());
                case "DOWN" -> operations.moveDown(graph.getMatrix());
            }
        }
    }

    private int evaluateMove(Tile[][] board, int depth) {
        if (depth == 0 || isGameOver(board)) {
            return evaluateBoard(board);
        }

        int maxScore = Integer.MIN_VALUE;
        for (String dir : DIRECTIONS) {
            Tile[][] newBoard = deepCopy(board);
            moveBoard(newBoard, dir);
            if (!boardsEqual(board, newBoard)) {
                int score = evaluateMove(newBoard, depth - 1);
                maxScore = Math.max(maxScore, score);
            }
        }
        return maxScore;
    }

    private int evaluateBoard(Tile[][] matrix) {
        int empty = 0;
        int maxTile = 0;
        for (Tile[] row : matrix) {
            for (Tile tile : row) {
                if (tile == null) empty++;
                else maxTile = Math.max(maxTile, tile.getNumber());
            }
        }
        return empty * 10 + maxTile;
    }

    private boolean isGameOver(Tile[][] board) {
        for (String dir : DIRECTIONS) {
            Tile[][] test = deepCopy(board);
            moveBoard(test, dir);
            if (!boardsEqual(test, board)) return false;
        }
        return true;
    }

    private void moveBoard(Tile[][] board, String direction) {
        switch (direction) {
            case "LEFT" -> operations.moveLeft(board);
            case "RIGHT" -> operations.moveRight(board);
            case "UP" -> operations.moveUp(board);
            case "DOWN" -> operations.moveDown(board);
        }
    }

    private boolean boardsEqual(Tile[][] a, Tile[][] b) {
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[0].length; x++) {
                int aNum = a[y][x] == null ? 0 : a[y][x].getNumber();
                int bNum = b[y][x] == null ? 0 : b[y][x].getNumber();
                if (aNum != bNum) return false;
            }
        }
        return true;
    }

    private Tile[][] deepCopy(Tile[][] original) {
        Tile[][] copy = new Tile[original.length][original[0].length];
        for (int y = 0; y < original.length; y++) {
            for (int x = 0; x < original[0].length; x++) {
                if (original[y][x] != null) {
                    int num = original[y][x].getNumber();
                    copy[y][x] = new FakeTile(num);
                }
            }
        }
        return copy;
    }

    private static class FakeTile extends Tile {
        private int number;

        public FakeTile(int num) {
            super(0, 0, null);
            this.number = num;
        }

        @Override
        public void add(int num) {
            number += num;
        }

        @Override
        public int getNumber() {
            return number;
        }

        @Override
        public void moveTo(double x, double y) {
            
        }
    }
}
