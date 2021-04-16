package game.logic;

import game.controller.Controller;

/**
 * Coal nyersanyag, dolgok(robot, teleport, bazis) epitesehez szukseges.
 * @author torok
 */
public class Coal extends Material{

    /**
     * Default konstruktor
     */
    public Coal() {}

    /**
     * A nyersanyag konstruktora ami beallitja a nevet.
     */
    public Coal(Controller _c){
        c = _c;
        name = "Coal";
    }

    /**
     *Meghívja a kontroller CoalDisintegrate metódusát.
     */
    @Override
    public void Disintegrate() {
        c.CoalDisintegrate(this);
    }
}
