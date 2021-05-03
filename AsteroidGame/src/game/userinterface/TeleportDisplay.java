package game.userinterface;

import game.logic.Teleport;

import java.awt.*;

public class TeleportDisplay extends Display {

    public Teleport getSubject() {
        return subject;
    }

    private Teleport subject;

    public TeleportDisplay(Teleport subject, int x, int y) {
        this.subject = subject;
        subject.AddDisplay(this);
        getShape().setBounds(x,y,50,50);
    }

    @Override
    public void SelectOutline(Graphics2D g2d){
        g2d.setColor(new Color(100,255,100));
        g2d.drawRect(getShape().x, getShape().y,getShape().width,getShape().height);
    }

    @Override
    public void RoundOutline(Graphics2D g2d) {
        g2d.setColor(new Color(1,250,250));
        g2d.drawRect(getShape().x, getShape().y,getShape().width,getShape().height);
    }

    public void NeighbourOutLine(Graphics2D g2d) {
        g2d.setColor(new Color(0, 200, 255));
        g2d.drawOval(getShape().x, getShape().y, getShape().width, getShape().height);
    }
    @Override
    public void Update(Graphics2D g2d){
        g2d.setColor(new Color(250,0,200));
        g2d.fillOval(getShape().x,getShape().y,getShape().width,getShape().height);
    }

    @Override
    public void Clear(){
        DisplayManager.GetInstance().RemoveTeleportDisplay(this);
    }

}
