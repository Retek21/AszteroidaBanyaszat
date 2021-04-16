package game.logic;

import game.controller.Controller;

/**
 * Ice nyersanyag, dolgok(robot, teleport, bazis) epitesehez szukseges.
 * @author torok
 */
public class Ice extends Material{

    /**
     * Default konstruktor
     */
    public Ice() {}

    /**
     * Az Ice konstruktora ami beallitja a nevet.
     */
    public Ice(Controller _c){
        c = _c;
        name = "Ice";
    }

    /**
     * A jeg elolvad. Lekerul az aszteroidarol es kikerul a jatekbol.
     * @param a - az aszteroida amivel interaktal
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Disintegrate();
        a.RemoveMaterial();
    }

    /**
     * Meghivja a kontroller IceDisintegrate metodusat.
     */
    @Override
    public void Disintegrate() {
        c.IceDisintegrate(this);
    }
}
