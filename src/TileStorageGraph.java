import edu.macalester.graphics.CanvasWindow;

public class TileStorageGraph {
    private Tile[][] matrix;
    private int num;
    public boolean canMerge = false;

    public TileStorageGraph(int num) {
        this.num = num;
        matrix = new Tile[num][num];
    }

    private void createHelper(int X, int Y, CanvasWindow canvas) {
        for (int y = 0; y < matrix.length; y++) {
            if (y == Y) {
                for (int x = 0; x < matrix[y].length; x++) {
                    if (x == X) {
                        Tile tile = new Tile((X+1)*100, (Y+1)*100, canvas);
                        matrix[y][x] = tile;
                    }
                }
            }
        }
    }

    public void create(int X, int Y, CanvasWindow canvas) {
        createHelper(X, Y, canvas);
    }

    public boolean hasTile(int x, int y) {
        if (matrix[y][x] == null) {
            return false;
        }
        return true;
    }

    public void setMatrix(Tile[][] newMatrix, CanvasWindow canvas){
        for (int y = 0; y < newMatrix.length; y++) {
            for (int x = 0; x < newMatrix[y].length; x++) {
                if(newMatrix[y][x] != null){
                    matrix[y][x] = new Tile(newMatrix[y][x].getX(),newMatrix[y][x].getY(),canvas);                    matrix[y][x].getTileRectangle().setPosition(newMatrix[y][x].getX(),newMatrix[y][x].getY());
                    matrix[y][x].setNum(newMatrix[y][x].getNumber());
                    matrix[y][x].add(0);
                    matrix[y][x].updateTile();
                } 
                else{
                    if(matrix[y][x] != null){
                    matrix[y][x].removeFromCanvas();
                    }
                    matrix[y][x] = null;

                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return matrix[y][x];
    }

    public Tile[][] getMatrix() {
        return matrix;
    }

    public int getNum() {
        return num;
    }

    public boolean isFull(){
        for (int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[y].length; x++){
                if (matrix[y][x] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public int openSpaces(){
        int openSpaces = 0;
        for (int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[y].length; x++){
                if (matrix[y][x] == null) {
                    openSpaces++;
                }
            }
        }
        return openSpaces;
    }

    public boolean is2048(){
        for (int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[y].length; x++){
                if(matrix[y][x] != null){
                    if (matrix[y][x].getNumber() == 2048 ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean canMerge(){
        for (int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[y].length; x++){
                Tile currentTile = matrix[y][x];
                if (x > 0 && matrix[y][x-1].getNumber() == matrix[y][x].getNumber()
                && matrix[y][x-1] != null) {
                    return  true;
                }
                if((y < matrix[y].length-1 && matrix[y+1][x].getNumber() == matrix[y][x].getNumber()
                && matrix[y+1][x] != null)){
                    return true;
                }

            }
        }
        return false;
    }
}


