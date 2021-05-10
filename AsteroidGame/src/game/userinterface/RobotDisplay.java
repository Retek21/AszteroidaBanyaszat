package game.userinterface;

import game.logic.Asteroid;
import game.logic.Entity;
import game.logic.Robot;

import java.awt.*;
import java.util.ArrayList;

/*
 * @author Kristof Torok
 * robot displaye*/
public class RobotDisplay extends EntityDisplay{

    /*
    * konstruktor, beallitja a subjectet es a subject asteroidajan allokal maganak helyet
    * beallitja a subject display-et is
    * */
    public RobotDisplay(Robot subject){
        SetSubject(subject);
        Asteroid a = subject.GetAsteroid();
        AsteroidDisplay ad = (AsteroidDisplay) a.GetDisplay();
        ad.EnititySectorAllocation(this);
        subject.SetDisplay(this);
    }
    /*
    * kirajzolas a koordinatak alapjan
    * beallitja a szint a selected es a roundoutline bool alapjan
    * */
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
    /*
    * torli a robot display-et. ujrarajzolja a palyat
    * */
    @Override
    public void Clear(){
        DisplayManager.GetInstance().RemoveRobotDisplay(this);
        super.Clear();
    }
}
