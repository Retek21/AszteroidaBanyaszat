package game.userinterface;

import game.logic.Ufo;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class UfoDisplay extends Display{
    private Ufo subject;

    public UfoDisplay(Ufo subject, int x, int y) {
        this.subject = subject;
        setX(x);
        setY(y);
        setShape(new Ellipse2D.Float( getX(),getY(),50,50));
    }
    @Override
    public void SelectOutline(Graphics2D g2d){
        g2d.setColor(Color.getHSBColor(2,100,28));
        g2d.drawOval(getShape().getBounds().x, getShape().getBounds().y,getShape().getBounds().width,getShape().getBounds().height);
    }
    @Override
    public void RoundOutline(Graphics2D g2d){
        g2d.setColor(Color.getHSBColor(48,100,128));
        g2d.drawOval(getShape().getBounds().x, getShape().getBounds().y,getShape().getBounds().width,getShape().getBounds().height);
    }
    @Override
    public void Update(Graphics2D g2d){

        g2d.fill(getShape());
    }

    @Override
    public void Clear(){
        DisplayManager.GetInstance().RemoveUfoDisplay(this);
    }
}
