package game.userinterface;

import game.logic.Asteroid;
import game.logic.Entity;
import game.logic.Robot;

import java.awt.*;
import java.util.ArrayList;

public class RobotDisplay extends EntityDisplay{
    public Robot GetSubject() {
        return subject;
    }

    private Robot subject;


    public RobotDisplay(Robot subject){
        this.subject = subject;
        Asteroid a = subject.GetAsteroid();
        AsteroidDisplay ad = (AsteroidDisplay) a.GetDisplay();
        ad.CoordinateServer(this);
        SetMoved(false);
    }

    public void Paint(Graphics g2d){
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        if(GetMoved()){
            ad.CoordinateServer(this);
        }
        g2d.setColor(new Color(1,100,100));
        g2d.fillRect(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        if(IsSelected()){
            g2d.setColor(new Color(1,100,100));
        }else if(IsRoundoutline()){
            g2d.setColor(new Color(100,100,100));
        }
        g2d.drawRect(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        SetSelected(false);
        SetRoundoutline(false);
        SetMoved(false);
    }
    @Override
    public void Clear(){ DisplayManager.GetInstance().RemoveRobotDisplay(this);}

}
