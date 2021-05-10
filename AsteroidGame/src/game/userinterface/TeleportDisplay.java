package game.userinterface;

import game.logic.Teleport;
import game.logic.Whereabout;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Kristof Torok
 * teleport displaye
 */
public class TeleportDisplay extends WhereaboutDisplay {
    /**
     * teleport tipusu subject
     */
    private Teleport subject;
    /**
    * teleport szine
    */
    private Color color;
    /**
     * display sectoranak koordinatai
     */
    private int sectorpoint;

    /**
     *
     * @return: a teleport
     */
    public Teleport GetSubject() {
        return subject;
    }

    /**
     * sectorpontok visszadasa
     * @return: sectorpont
     */
    public int GetSectorpoint() {
        return sectorpoint;
    }


    /**
     * sectorpontok beallitasa
     * @param sectorpoint: a sectorpont
     */
    public void SetSectorpoint(int sectorpoint) {
        this.sectorpoint = sectorpoint;
    }
    /**
     * konstruktor, beallitja a subjectet es a subject asteroidajan allokal maganak helyet
     * beallitja a subject display-et is
     */

    /**
     * konstruktor, beallitja a subjectet es a subject asteroidajan allokal maganak helyet
     * beallitja a subject display-et is
     * @param subject: A teleport, amit a display megjelenit
     */
    public TeleportDisplay(Teleport subject) {
        this.subject = subject;
        subject.SetDisplay(this);
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.TeleportSectorAllocation(this);
    }

    /**
     * beallitja a selected booljat
     * @param selected: a logikai ertek
     */
    public void SetSelectedAsPair(boolean selected) {
        super.selected = selected;
    }


    /**
     * a selected bool beallitsa a parnak
     * @param selected
     */
    @Override
    public void SetSelected(boolean selected) {
        super.selected = selected;
        if (selected)
            if (subject.GetPairReadiness()) {
                TeleportDisplay pair = (TeleportDisplay) subject.GetPair().GetDisplay();
                pair.SetSelectedAsPair(true);
            }
        DisplayManager.GetInstance().repaint();
    }



    /**
     * allitja a blinkeles alapjan a szinet
     * kirajzolja a koordinatak alapjan
     * beallitja a szint a selected es a roundoutline bool alapjan
     * @param g2d: A Graphics osztaly
     */
    @Override
    public void Paint(Graphics g2d){
        Color color;
        if(underSunStorm && blink % 2 == 0)
            color = new Color(255, 255, 0);
        else if (!subject.GetPairReadiness())
            color = new Color(4, 46, 135);
        else
            color = new Color(153,50,204);
        g2d.setColor(color);
        g2d.fillOval(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        if (IsRoundoutline()){
            g2d.setColor(new Color(250, 230, 20));
        } else if(IsNeighbour()){
            g2d.setColor(new Color(20,200,0));
        }
        else if(IsSelected()){
            g2d.setColor(new Color(255, 20, 20));
        }
        g2d.drawOval(GetShape().x, GetShape().y,GetShape().width,GetShape().height);
    }


    /**
     * display torlese, lefoglalt sectorok visszaallitasa
     */
    @Override
    public void Clear(){
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.TeleportSectorAllocation(this);
        ad.GetAllocatedTeleportSectors()[sectorpoint] = false;
        DisplayManager.GetInstance().RemoveTeleportDisplay(this);
    }

    /**
    * ertesiti a displayt az objektum valtozasairol
    */
    public void Notify() {

        if (!underSunStorm && blink == 0) {
            underSunStorm = subject.GetOnFireness();
            if (underSunStorm) {
                blink = DisplayManager.GetInstance().GetBlinkingTime();;
            }
        }

        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.TeleportSectorAllocation(this);

    }
}
