package game.userinterface;

import game.logic.Settler;

import java.awt.*;

/**
* @author Kristof Torok
* settler displaye
 */
public class SettlerDisplay extends EntityDisplay {

    /**
    * konstruktor, beallitja a subjectet es a subject asteroidajan allokal maganak helyet
    * beallitja a subject display-et is
    */

    /**
     * konstruktor, beallitja a subjectet es a subject asteroidajan allokal maganak helyet
     * beallitja a subject display-et is
     * @param subject: a telepes, amit a display megjelenit
     */
    public SettlerDisplay(Settler subject) {
        SetSubject(subject);
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.EnititySectorAllocation(this);
        subject.SetDisplay(this);
    }


    /**
     * kirajzolas a koordinatak alapjan
     * beallitja a szint a selected es a roundoutline bool alapjan
     * @param g2d: A Graphics osztaly
     */
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

    /**
     * torli a settler display-et, majd ujrarajzolja a palyat
     */
    @Override
    public void Clear() {
        super.Clear();
        DisplayManager.GetInstance().RemoveSettlerDisplay(this);
    }
}