package game.userinterface;

import java.awt.*;

/*
 * @author Kristof Torok
 * sun displaye*/
public class SunDisplay extends Display{

    /*
    * beallitja a nap shapejenek a mereteeit a kapott koordinatak alapjan
    * */
    public SunDisplay(int x, int y){
        GetShape().setBounds(x,y,150,150);
    }

    /*
    * beallitja a nap szinet es kirajzolja a meretek alapjan
    * */
    @Override
    public void Paint(Graphics g) {
        g.setColor(new Color(232, 65, 24));
        g.fillOval(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
    }
}
