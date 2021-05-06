package game.userinterface;
import java.awt.*;

public class Display {

    private Rectangle shape = new Rectangle();
    private boolean selected;
    private boolean roundoutline;
    private boolean isNeigbhour;
    private Point sectorCoordinates;

    public Point GetSectorCoordinates() {
        return sectorCoordinates;
    }

    public void SetSectorCoordinates(Point sectorCoordinates) {
        this.sectorCoordinates = sectorCoordinates;
    }

    public Rectangle GetShape() {
        return shape;
    }

    public boolean IsSelected() {
        return selected;
    }

    public boolean IsRoundoutline() {
        return roundoutline;
    }

    public boolean IsNeigbhour() {
        return isNeigbhour;
    }


    public void SetShape(Rectangle shape) {
        this.shape = shape;
    }

    public void SetSelected(boolean selected) {
        this.selected = selected;
    }

    public void SetRoundoutline(boolean roundoutline) {
        this.roundoutline = roundoutline;
    }

    public void SetisNeigbhour(boolean neigbhour) {
        isNeigbhour = neigbhour;
    }

    public void Paint(Graphics g){}
    public void Clear(){}
    public boolean PointInArea(int x, int y){
        return shape.contains(x,y);
    }
    public boolean Intersect(Display display){
       return shape.intersects(display.GetShape());
    }
}
