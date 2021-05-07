package game.userinterface;

import java.awt.*;

public class SunDisplay extends Display{

    public SunDisplay(int x, int y){
        GetShape().setBounds(x,y,80,80);
    }

    @Override
    public void Paint(Graphics g) {
        g.setColor(new Color(232, 65, 24));
        g.fillOval(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
    }
}