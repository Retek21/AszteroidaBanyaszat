package game.userinterface;

import game.logic.Asteroid;
import game.logic.Entity;
import game.logic.Settler;

import java.awt.*;
import java.util.ArrayList;

public class SettlerDisplay extends EntityDisplay {

    private Settler subject;

    public Settler GetSubject() {
        return subject;
    }

    public SettlerDisplay(Settler subject) {
        this.subject = subject;
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.CoordinateServer(this);
        SetMoved(false);
    }


    @Override
    public void Paint(Graphics g2d) {
        Asteroid asteroid = subject.GetAsteroid();
        AsteroidDisplay ad = (AsteroidDisplay) asteroid.GetDisplay();
        if(GetMoved()){
            ad.CoordinateServer(this);
        }
        g2d.setColor(new Color(255, 180, 120));
        g2d.fillRect(GetShape().x, GetShape().y, 20, 20);
        if (IsSelected()) {
            g2d.setColor(new Color(255, 20, 20));
        } else if (IsRoundoutline()) {
            g2d.setColor(new Color(250, 230, 20));
        }
        g2d.drawRect(GetShape().x, GetShape().y, 20, 20);
        SetSelected(false);
        SetRoundoutline(false);
        SetMoved(false);
    }

    @Override
    public void Clear() {
        DisplayManager.GetInstance().RemoveSettlerDisplay(this);
    }
}
