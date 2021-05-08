package game.userinterface;

import game.logic.Asteroid;
import game.logic.Entity;

import java.awt.*;

public class EntityDisplay extends Display{

    private boolean moved;

    public Entity GetSubject() {
        return subject;
    }

    public void SetSubject(Entity subject) {
        this.subject = subject;
    }

    private Entity subject;

    public Point GetSectorpoints() {
        return sectorpoints;
    }

    public void SetSectorpoints(Point sectorpoints) {
        this.sectorpoints = sectorpoints;
    }

    private Point sectorpoints;

    /**
     * egy korre moved true lesz
     */
    public void SetMoved(boolean move){moved=move;}

    public boolean GetMoved(){return moved;}

    @Override
    public void Notify() {
        Asteroid asteroid = subject.GetAsteroid();
        AsteroidDisplay ad = (AsteroidDisplay) asteroid.GetDisplay();
        ad.EnititySectorAllocation(this);
        DisplayManager.GetInstance().repaint();
    }

    @Override
    public void Clear() {
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.GetAllocatedAsteroidSectors()[sectorpoints.x][sectorpoints.y] = false;
        DisplayManager.GetInstance().repaint();
    }
}
