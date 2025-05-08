public class FakeTile extends Tile {
    private int number;

    public FakeTile(int number) {
        super(0, 0, null);
        this.number = number;
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

    @Override
    public void removeFromCanvas() {
        
    }

    @Override
    public void changeColor() {
        
    }

    @Override
    protected void setUpGraphics(double x, double y) {
        
    }
}
