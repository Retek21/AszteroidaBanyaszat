package game.logic;

import game.controller.Controller;

/**
 * Iron nyersanyag, dolgok(robot, teleport, bazis) epitesehez szukseges.\
 * @author torok
 */
public class Iron extends Material{

    /**
     * Default konstruktor
     */
    public Iron() {}

    /**
     * Az Iron konstruktora ami beallitja a nevet
     */
    public Iron(Controller _c){
        c = _c;
        name = "Iron";
    }

    /**
     * Meghivja a kontroller IronDisintegrate metodusat
     */
    @Override
    public void Disintegrate() {
        c.IronDisintegrate(this);
    }
}
