package game.logic;

import game.controller.Controller;
import java.util.ArrayList;
import java.util.Random;
/**
 * Robot osztaly, az Entity leszarmazotta,
 * feladata az aszteroidak kozti mozgas, azok megfurasa.
 * @author Turiak Anita 2021.03.19.
 * @author Dengyel Bendeguz 2021.04.13.
 */
public class Robot extends Entity {

    /**
     * A robot felrobban az aszteroidaja robbanasa kovetkezteben.
     * Ha a felrobbant aszteroidanak voltak szomszedjai, atlep valamelyikre,
     * egyebkent meghal.
     * Az osbeli metodus felulirasa.
     */
    @Override
    public void BlowUp(){
        ArrayList<Whereabout> neighbours=asteroid.GetNeighbours();
        if(neighbours.size() == 0){
            Die();
        }else {
            Random random = new Random();
            int destinationIndex = random.nextInt(neighbours.size());
            Move(neighbours.get(destinationIndex));
        }
    }

    /**
     * A robot megfur egy aszteroidat.
     * Visszateresi erteke a furas sikeressege.
     * @return a metodus sikeressege
     */
    public boolean Drill(){
        return asteroid.ThinLayer();
    }

    /**
     * Az osbeli metodus felulirasa. A robot meghal, meghivva az os
     * Entity::Die() metodusat, valamint a Controller::RobotDie(Robot r)
     * metodusat is, ezzel kiveve magat a jatekbol es a fazisokbol.
     */
    @Override
    public void Die() {
        super.Die();
        Controller.GetInstanceOf().RobotDie(this);
    }
}
