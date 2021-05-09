package game.userinterface;

import game.logic.Asteroid;
import game.logic.Entity;
import game.logic.Robot;

import java.awt.*;
import java.util.ArrayList;

public class RobotDisplay extends EntityDisplay{


    public RobotDisplay(Robot subject){
        SetSubject(subject);
        Asteroid a = subject.GetAsteroid();
        AsteroidDisplay ad = (AsteroidDisplay) a.GetDisplay();
        ad.EnititySectorAllocation(this);
    }

    public void Paint(Graphics g2d){
        g2d.setColor(new Color(1,100,100));
        g2d.fillRect(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        if(IsSelected()){
            g2d.setColor(new Color(255, 20, 20));
        }else if(IsRoundoutline()){
            g2d.setColor(new Color(250, 230, 20));
        }
        g2d.drawRect(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
    }
    @Override
    public void Clear(){
        super.Clear();
        DisplayManager.GetInstance().RemoveRobotDisplay(this);}

}
