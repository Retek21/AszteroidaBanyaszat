package game.userinterface;

import game.logic.Teleport;
import game.logic.Whereabout;

import java.awt.*;
import java.util.ArrayList;

public class TeleportDisplay extends WhereaboutDisplay {

    public Teleport GetSubject() {
        return subject;
    }

    private Teleport subject;

    private Color color;

    public int GetSectorpoint() {
        return sectorpoint;
    }

    public void SetSectorpoint(int sectorpoint) {
        this.sectorpoint = sectorpoint;
    }

    private int sectorpoint;

    public TeleportDisplay(Teleport subject) {
        this.subject = subject;
        subject.SetDisplay(this);
   /*     if (!subject.GetPairReadiness())
            color = new Color(4, 46, 135);
        else
            color = new Color(153,50,204);

        Display pair = subject.GetPair().GetDisplay();
        if (pair != null) pair.Notify();*/

        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.TeleportSectorAllocation(this);

    }

    public void SetSelectedAsPair(boolean selected) {
        super.selected = selected;
    }

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

    @Override
    public void Clear(){
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.TeleportSectorAllocation(this);
        ad.GetAllocatedTeleportSectors()[sectorpoint] = false;
        DisplayManager.GetInstance().RemoveTeleportDisplay(this);
    }

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
