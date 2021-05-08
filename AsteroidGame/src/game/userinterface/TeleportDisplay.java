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
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.TeleportSectorAllocation(this);
    }

    @Override
    public void Paint(Graphics g2d){
        g2d.setColor(new Color(153,50,204));
        g2d.fillOval(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        if(IsSelected()){
            subject.GetPair().GetDisplay().SetSelected(true);
            g2d.setColor(new Color(255, 20, 20));
        }else if(IsRoundoutline()){
            g2d.setColor(new Color(250, 230, 20));
        }else if(IsNeighbour()){
            g2d.setColor(new Color(20,200,0));
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
            AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
            ad.TeleportSectorAllocation(this);
    }
}
