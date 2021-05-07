package game.userinterface;

import game.logic.Entity;
import game.logic.Ufo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class UfoDisplay extends Display{
    public Ufo GetSubject() {
        return subject;
    }

    private Ufo subject;

    public UfoDisplay(Ufo subject){
        this.subject = subject;
        AsteroidDisplay asteroid = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        asteroid.CoordinateServer(this);
    }

    @Override
    public void Paint(Graphics g2d){
        AsteroidDisplay asteroid = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        g2d.setColor(new Color(1,150,250));
        g2d.fillRect(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        if(IsSelected()){
            g2d.setColor(new Color(255, 20, 20));
        }else if(IsRoundoutline()){
            g2d.setColor(new Color(250, 230, 20));
        }
        g2d.drawRect(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        SetSelected(false);
        SetRoundoutline(false);
    }

    @Override
    public void Clear(){
        DisplayManager.GetInstance().RemoveUfoDisplay(this);
    }
}
