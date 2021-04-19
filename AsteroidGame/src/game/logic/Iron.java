package game.logic;

import game.controller.Controller;

/**
 * Iron nyersanyag, objektumok(robot, teleport, bazis) epitesehez szukseges.
 * @author torok
 */
public class Iron extends Material{

    /**
     * Az Iron konstruktora ami beallitja a nevet
     */
    public Iron(){
        name = "Iron";
    }

    /**
     * Meghivja a kontroller IronDisintegrate metodusat
     */
    @Override
    public void Disintegrate() {
        Controller.GetInstanceOf().IronDisintegrate(this);
    }
}
