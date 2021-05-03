package game.userinterface;

import java.awt.*;

public class ConnectionDisplay extends Display {

    private Display d1;
    private Display d2;

    ConnectionDisplay(Display d1, Display d2){
        this.d1 = d1;
        this.d2 = d2;
        (AsteroidDisplay)d1.
    }

    @Override
    public void Update(Graphics2D g2d) {
        int x1 = (int)d1.getShape().getCenterX();
        int y1 = (int)d1.getShape().getCenterY();
        int x2 = (int)d1.getShape().getCenterX();
        int y2 = (int)d1.getShape().getCenterY();
        g2d.drawLine(x1,y1,x2,y2);
    }
}
