
import java.awt.*;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

public class Tile {
    private int number;
    private Rectangle rectangle;
    private GraphicsText label;
    private CanvasWindow canvas;

    public Tile(double x, double y, CanvasWindow canvas) {
        this.canvas = canvas;
        number = 2;
        setUpGraphics(x, y);
        
    }

    protected void setUpGraphics(double x, double y) {
        rectangle = new Rectangle(x, y, 100, 100);
        rectangle.setStrokeWidth(8);
        rectangle.setStrokeColor(Color.WHITE);
        changeColor();
        label = new GraphicsText(Integer.toString(number), x + 25, y + 50);
        label.setFillColor(Color.WHITE);
        label.setStrokeColor(Color.BLACK);
        label.setStrokeWidth(1);
        label.setFontSize(25);

        canvas.add(rectangle);
        canvas.add(label);
    }

    public void add(int num) {
        number += num;
        label.setText(Integer.toString(number));
    }

    public double getX(){
        return rectangle.getX();
    }
    public double getY(){
        return rectangle.getY();
    }


    public int getNumber() {
        return number;
    }

    public void setNum(int number) {
        this.number = number;
    }

    public void updateTile(){
        label.setText(Integer.toString(number));
        changeColor();
    }

    public Rectangle getTileRectangle() {
        return rectangle;
    }

    public void moveTo(double x, double y) {
        rectangle.setPosition(x, y);
        label.setPosition(x + 25, y + 50);
    }    

    public void changeColor(){
        switch (number) {
            case 2 -> {
                rectangle.setFillColor(Color.LIGHT_GRAY);
            }

            case 4 -> {
                rectangle.setFillColor(Color.GRAY);
            }

            case 8 -> {
                rectangle.setFillColor(Color.DARK_GRAY);
            }

            case 16-> {
                rectangle.setFillColor(Color.GREEN);
            }
            case 32-> {
                rectangle.setFillColor(Color.CYAN);
            }
            case 64-> {
                rectangle.setFillColor(Color.BLUE);
            }
            case 128-> {
                rectangle.setFillColor(Color.PINK);
            }
            case 256-> {
                rectangle.setFillColor(Color.MAGENTA);
            }
            case 512-> {
                rectangle.setFillColor(Color.RED);
            }
            case 1024-> {
                rectangle.setFillColor(Color.ORANGE);
            }
            case 2048-> {
                rectangle.setFillColor(Color.YELLOW);
            }
        }
    }

    public void removeFromCanvas() {
        canvas.remove(rectangle);
        canvas.remove(label);
    }
    
}
