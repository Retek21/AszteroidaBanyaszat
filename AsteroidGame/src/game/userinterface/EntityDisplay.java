package game.userinterface;

import game.logic.Asteroid;
import game.logic.Entity;

import java.awt.*;

public class EntityDisplay extends Display{
    /*
    * entitas tipusu subject
    * */
    private Entity subject;
    /*
    * display sectoranak koordinatai
    * */
    private Point sectorpoints;
    /*
    * entitydisplay subjectjenek visszadasa
    * */
    public Entity GetSubject() {
        return subject;
    }
    /*
    * subject beallitasa
    * */
    public void SetSubject(Entity subject) {
        this.subject = subject;
    }
    /*
    * sectorpontok visszadasa
    * */
    public Point GetSectorpoints() {
        return sectorpoints;
    }
    /*
    * sectorpontok beallitasa
    * */
    public void SetSectorpoints(Point sectorpoints) {
        this.sectorpoints = sectorpoints;
    }
    /*
    * ha mozgast tortent beallit egy uj koordinatat az entitynek
    * */
    @Override
    public void Notify() {
        Asteroid asteroid = subject.GetAsteroid();
        AsteroidDisplay ad = (AsteroidDisplay) asteroid.GetDisplay();
        ad.EnititySectorAllocation(this);
        DisplayManager.GetInstance().repaint();
    }

    /*
    * display torlese, lefoglalt sectorok visszaallitasa
    * */
    @Override
    public void Clear() {
        AsteroidDisplay ad = (AsteroidDisplay) subject.GetAsteroid().GetDisplay();
        ad.GetAllocatedAsteroidSectors()[sectorpoints.x][sectorpoints.y] = false;
        DisplayManager.GetInstance().repaint();
    }
}
