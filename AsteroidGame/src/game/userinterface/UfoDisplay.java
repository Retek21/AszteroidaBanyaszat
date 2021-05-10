package game.userinterface;

import game.logic.Ufo;

import java.awt.*;

/*
 * @author Kristof Torok
 * ufo displaye*/
public class UfoDisplay extends EntityDisplay{
    /*
     * konstruktor, beallitja a subjectet es a subject asteroidajan allokal maganak helyet
     * beallitja a subject display-et is
     * */
    public UfoDisplay(Ufo subject){
        SetSubject(subject);
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.EnititySectorAllocation(this);
        subject.SetDisplay(this);
    }
    /*
     * kirajzolas a koordinatak alapjan
     * beallitja a szint a selected es a roundoutline bool alapjan
     * */
    @Override
    public void Paint(Graphics g2d) {
        g2d.setColor(new Color(1, 150, 250));
        g2d.fillRect(GetShape().x, GetShape().y, 15, 15);
        if (IsSelected()) {
            g2d.setColor(new Color(255, 20, 20));
        } else if (IsRoundoutline()) {
            g2d.setColor(new Color(250, 230, 20));
        }
        g2d.drawRect(GetShape().x, GetShape().y, 15, 15);
    }
    /*
     * torli az ufo display-et. ujrarajzolja a palyat
     * */
    @Override
    public void Clear(){
        super.Clear();
        DisplayManager.GetInstance().RemoveUfoDisplay(this);
    }
}
