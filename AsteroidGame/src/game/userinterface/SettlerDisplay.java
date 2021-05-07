package game.userinterface;

import game.logic.Asteroid;
import game.logic.Entity;
import game.logic.Settler;

import java.awt.*;
import java.util.ArrayList;

public class SettlerDisplay extends Display {

    private Settler subject;
    private boolean moved;

    public Settler GetSubject() {
        return subject;
    }

    public SettlerDisplay(Settler subject) {
        this.subject = subject;
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.CoordinateServer(this);
    }

    @Override
    public void Paint(Graphics g2d) {
        Asteroid asteroid = subject.GetAsteroid();
        AsteroidDisplay ad = (AsteroidDisplay) asteroid.GetDisplay();
        if(moved){
            ad.CoordinateServer(this);
        }
        g2d.setColor(new Color(255, 180, 120));
        g2d.fillRect(GetShape().x, GetShape().y, 20, 20);
        if (IsSelected()) {
            g2d.setColor(new Color(1, 225, 150));
        } else if (IsRoundoutline()) {
            g2d.setColor(new Color(130, 140, 150));
        }
        g2d.drawRect(GetShape().x, GetShape().y, 20, 20);
        SetSelected(false);
        SetRoundoutline(false);
        moved = false;
    }

    @Override
    public void Clear() {
        DisplayManager.GetInstance().RemoveSettlerDisplay(this);
    }
}
