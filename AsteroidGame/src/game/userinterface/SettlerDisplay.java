package game.userinterface;

import game.logic.Settler;

import java.awt.*;

public class SettlerDisplay extends EntityDisplay {

    public SettlerDisplay(Settler subject) {
        SetSubject(subject);
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.EnititySectorAllocation(this);
        subject.SetDisplay(this);
    }


    @Override
    public void Paint(Graphics g2d) {
        g2d.setColor(new Color(255, 180, 120));
        g2d.fillRect(GetShape().x, GetShape().y, 15, 15);
        System.out.println(g2d.getColor());
        if (IsSelected()) {
            g2d.setColor(new Color(255, 20, 20));
        } else if (IsRoundoutline()) {
            g2d.setColor(new Color(250, 230, 20));
        }
        g2d.drawRect(GetShape().x, GetShape().y, 15, 15);
    }

    @Override
    public void Clear() {
        super.Clear();
        DisplayManager.GetInstance().RemoveSettlerDisplay(this);
    }
}