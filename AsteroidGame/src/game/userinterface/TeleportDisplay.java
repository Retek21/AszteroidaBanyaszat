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
            g2d.setColor(GetOutlineColor());
        }
        g2d.drawOval(GetShape().x, GetShape().y,GetShape().width,GetShape().height);
        SetSelected(false);
        SetRoundoutline(false);
        SetisNeigbhour(false);
    }

    @Override
    public void Clear(){
        DisplayManager.GetInstance().RemoveTeleportDisplay(this);
    }

    @Override
    public void Notify() {
            AsteroidDisplay ad = (AsteroidDisplay) subject.GetDisplay();
            ad.TeleportSectorAllocation(this);
    }
}
