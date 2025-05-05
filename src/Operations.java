import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    public void moveLeft(Tile[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            List<Tile> row = compressAndMerge(getRow(matrix, y));
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = x < row.size() ? row.get(x) : null;
            }
        }
    }

    public void moveRight(Tile[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            List<Tile> row = getRow(matrix, y);
            Collections.reverse(row);
            row = compressAndMerge(row);
            Collections.reverse(row);
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = x < row.size() ? row.get(x) : null;
            }
        }
    }

    public void moveUp(Tile[][] matrix) {
        for (int x = 0; x < matrix[0].length; x++) {
            List<Tile> col = getColumn(matrix, x);
            col = compressAndMerge(col);
            for (int y = 0; y < matrix.length; y++) {
                matrix[y][x] = y < col.size() ? col.get(y) : null;
            }
        }
    }

    public void moveDown(Tile[][] matrix) {
        for (int x = 0; x < matrix[0].length; x++) {
            List<Tile> col = getColumn(matrix, x);
            Collections.reverse(col);
            col = compressAndMerge(col);
            Collections.reverse(col);
            for (int y = 0; y < matrix.length; y++) {
                matrix[y][x] = y < col.size() ? col.get(y) : null;
            }
        }
    }

    private List<Tile> getRow(Tile[][] matrix, int rowIndex) {
        List<Tile> row = new ArrayList<>();
        for (Tile tile : matrix[rowIndex]) {
            if (tile != null) row.add(tile);
        }
        return row;
    }

    private List<Tile> getColumn(Tile[][] matrix, int colIndex) {
        List<Tile> col = new ArrayList<>();
        for (int y = 0; y < matrix.length; y++) {
            if (matrix[y][colIndex] != null) col.add(matrix[y][colIndex]);
        }
        return col;
    }

    private List<Tile> compressAndMerge(List<Tile> tiles) {
        List<Tile> result = new ArrayList<>();
        int i = 0;
        while (i < tiles.size()) {
            if (i + 1 < tiles.size() && tiles.get(i).getNumber() == tiles.get(i + 1).getNumber()) {
                tiles.get(i).add(tiles.get(i + 1).getNumber());
                result.add(tiles.get(i));
                i += 2;
            } else {
                result.add(tiles.get(i));
                i++;
            }
        }
        return result;
    }
}
