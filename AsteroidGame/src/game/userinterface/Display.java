package game.userinterface;
import java.awt.*;

public class Display{

    private Rectangle shape;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    private Color color;

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    public void Update(Graphics2D g2d){}
    public void SelectOutline(Graphics2D g2d){}
    public void RoundOutline(Graphics2D g2d) {}
    public void Clear(){}
    public boolean PointInArea(int x, int y){
        return shape.contains(x,y);
    }
    public boolean Intersect(Display display){
       return shape.intersects(display.getShape());
    }
}
