import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {
    public boolean canMerge = false;

    public void moveLeft(Tile[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            List<Tile> row = compressAndMerge(getRow(matrix, y));

            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = null;
            }

            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = x < row.size() ? row.get(x) : null;
            }
        }

        updateTilePositions(matrix);
    }

    public void moveRight(Tile[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            List<Tile> row = getRow(matrix, y);
            Collections.reverse(row);
            row = compressAndMerge(row);
            Collections.reverse(row);
    
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = null;
            }
    
            int start = matrix[y].length - row.size();
            for (int i = 0; i < row.size(); i++) {
                matrix[y][start + i] = row.get(i);
            }
        }
    
        updateTilePositions(matrix);
    }
    

    public void moveUp(Tile[][] matrix) {
        for (int x = 0; x < matrix[0].length; x++) {
            List<Tile> col = getColumn(matrix, x);
            col = compressAndMerge(col);
            for (int y = 0; y < matrix.length; y++) {
                matrix[y][x] = y < col.size() ? col.get(y) : null;
            }
        }

        updateTilePositions(matrix);
    }

    public void moveDown(Tile[][] matrix) {
        for (int x = 0; x < matrix[0].length; x++) {
            List<Tile> col = getColumn(matrix, x);
            Collections.reverse(col);
            col = compressAndMerge(col);
            Collections.reverse(col);
    
            for (int y = 0; y < matrix.length; y++) {
                matrix[y][x] = null;
            }
    
            int start = matrix.length - col.size();
            for (int i = 0; i < col.size(); i++) {
                matrix[start + i][x] = col.get(i);
            }
        }
    
        updateTilePositions(matrix);
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
                canMerge = true;
                Tile merged = tiles.get(i);
                Tile toRemove = tiles.get(i + 1);
    
                merged.add(toRemove.getNumber());
                toRemove.removeFromCanvas();
    
                result.add(merged);
                i += 2;
            } else {
                result.add(tiles.get(i));
                i++;
            }
        }
        canMerge = false;
        return result;
    }
    

    private void updateTilePositions(Tile[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] != null) {
                    matrix[y][x].moveTo((x + 1) * 100, (y + 1) * 100);
                    matrix[y][x].changeColor();
                }
            }
        }
    }


    
}
