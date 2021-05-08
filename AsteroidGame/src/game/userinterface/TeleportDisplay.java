package game.userinterface;

import game.logic.Teleport;

import java.awt.*;

public class TeleportDisplay extends Display {

    public Teleport GetSubject() {
        return subject;
    }

    private Teleport subject;

    public TeleportDisplay(Teleport subject) {
        this.subject = subject;
        subject.AddDisplay(this);
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.TeleportSectorAllocation(this);
    }

    @Override
    public void Paint(Graphics g2d){
        if(subject.GetCraziness()){
            AsteroidDisplay ad = (AsteroidDisplay) subject.getDisplay();
            ad.TeleportSectorAllocation(this);
        }
        g2d.setColor(new Color(153,50,204));
        g2d.fillOval(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        if(IsSelected()){
            g2d.setColor(new Color(255, 20, 20));
        }else if(IsRoundoutline()){
            g2d.setColor(new Color(250, 230, 20));
        }else if(IsNeighbour()){
            g2d.setColor(new Color(20,200,0));
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
}
