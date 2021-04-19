package game.logic;

import game.controller.Controller;

/**
 * Ice nyersanyag, objektumok(robot, teleport, bazis) epitesehez szukseges.
 * @author torok
 */
public class Ice extends Material{

    /**
     * Az Ice konstruktora ami beallitja a nevet.
     */
    public Ice(){
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
        Controller.GetInstanceOf().IceDisintegrate(this);
    }
}
