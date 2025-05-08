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

    public void setMatrix(Tile[][] newMatrix){
        System.out.println("Old matrix: " + matrix);
        System.out.println("New matrix: " + newMatrix);
        matrix = newMatrix.clone();
        System.out.println("boolean:" + matrix.equals(newMatrix));
        for (int y = 0; y < num; y++) {
            for(int x = 0; x < num; x++){
                if(matrix[y][x] != null ) {
                System.out.println("First Line" + matrix[y][x].getNumber());
            }
            else{
                System.out.println("First Line is null");
            }
            if(newMatrix[y][x] != null ) {
                System.out.println("Second Line" + newMatrix[y][x].getNumber());
            }
            else{
                System.out.println("Second Line is null");
            }
                if(newMatrix[y][x] != null){
                    // matrix[y][x].setNum(newMatrix[y][x].getNumber());
                    matrix[y][x] = newMatrix[y][x];
                    matrix[y][x].updateTile();
                }
                else {
                    matrix[y][x] = null;
                }

                // System.out.println("Print Line" + matrix[y][x]);
                // if (matrix[y][x] != null) {
                //     matrix[y][x].moveTo((x + 1) * 100, (y + 1) * 100);
                //     matrix[y][x].changeColor();
                // }
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
        System.out.println("Graph full!!!");
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


