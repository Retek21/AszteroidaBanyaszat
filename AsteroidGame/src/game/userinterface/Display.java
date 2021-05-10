package game.userinterface;
import java.awt.*;

public class Display {

    /**
    * a display shapeje
    */
    private Rectangle shape = new Rectangle();
    /**
    * bool hogy ki van e jelolve
    */
    protected boolean selected;
    /**
    * adott kort jelzo bool
    */
    private boolean roundoutline;

    /**
     * shape visszadasa
     * @return: a shape
     */
    public Rectangle GetShape() {
        return shape;
    }


    /**
     * selected bool visszaadasa
     * @return: a logikai ertek
     */
    public boolean IsSelected() { return selected; }


    /**
     * roundoutline bool visszaadasa
     * @return: a logikai ertek
     */
    public boolean IsRoundoutline() {
        return roundoutline;
    }


    /**
     * shape beallitasa
     * @param shape: a beallitando shape
     */
    public void SetShape(Rectangle shape) {
        this.shape = shape;
    }


    /**
     * selected bool hozzaadasa
     * @param selected: a beallitando logikai ertek
     */
    public void SetSelected(boolean selected) {
        this.selected = selected;
        DisplayManager.GetInstance().repaint();
    }

    /**
     * korkezdo bool hozzaadasa
     * @param roundoutline: a beallitando logikai ertek
     */
    public void SetRoundoutline(boolean roundoutline) {
        this.roundoutline = roundoutline;
        DisplayManager.GetInstance().repaint();
    }

    /**
     * paint metodus
     * @param g: A Graphics osztaly
     */
    public void Paint(Graphics g){}
    /**
    * clearpufferhoz adja hozza az adott itemet
    */
    public void MarkedToClear() {
        DisplayManager.GetInstance().AddToClearPuffer(this);
    }
    /**
    * adott Display torlese
    */
    public void Clear(){
        DisplayManager.GetInstance().repaint();
    }
    /**
    * megnezi, hogy a shapejebe beleesik e az adott pont
    */
    public boolean PointInArea(int x, int y){
        return shape.contains(x,y);
    }
    /**
    * Notify metodus
    */
    public void Notify(){}
}
