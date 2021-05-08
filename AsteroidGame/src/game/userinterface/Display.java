package game.userinterface;
import java.awt.*;

public class Display {

    private Rectangle shape = new Rectangle();
    private boolean selected;
    private boolean roundoutline;
    private Color fillColor;

    public Color GetFillColor() {
        return fillColor;
    }

    public void SetFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color GetOutlineColor() {
        return outlineColor;
    }

    public void SetOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    private Color outlineColor;

    public Rectangle GetShape() {
        return shape;
    }

    public boolean IsSelected() { return selected;
    }

    public boolean IsRoundoutline() {
        return roundoutline;
    }

    public void SetShape(Rectangle shape) {
        this.shape = shape;
    }

    public void SetSelected(boolean selected) {
        this.selected = selected;
        //DisplayManager.GetInstance().repaint();
    }

    public void SetRoundoutline(boolean roundoutline) {
        this.roundoutline = roundoutline;
        DisplayManager.GetInstance().repaint();
    }

    public void Paint(Graphics g){}
    public void Clear(){
        DisplayManager.GetInstance().repaint();
    }
    public boolean PointInArea(int x, int y){
        return shape.contains(x,y);
    }
    public boolean Intersect(Display display){
       return shape.intersects(display.GetShape());
    }
    public void Notify(){}
}
