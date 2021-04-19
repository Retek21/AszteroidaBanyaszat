package game.logic;

import game.controller.Controller;

/**
 * Uranium nyersanyag, objektumok(robot, teleport, bazis) epitesehez szukseges.
 * Aszteroidaba helyezve a megfelelo korulmenyek kozott felrobbantja az aszteroidat.
 * @author torok
 */
public class Uranium extends Material{

    /**
     * Konstruktor ami beallitja az uranium nevet.
     */
    public Uranium(){
        name = "Uranium";
    }

    /**
     * Ha interactCount attributum erteke nagyobb, mint 2, a nyersanyag kikerul a jatekbol es felrobbantja az aszteroidat.
     * @param a az aszteroida ahol nap eri
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        if(interactCount > 2) {
            Disintegrate();
            a.Explode();
        }
    }

    /**
     * Meghivja a kontroller UraniumDisintegrate metodusat.
     */
    @Override
    public void Disintegrate() {
        Controller.GetInstanceOf().UraniumDisintegrate(this);
    }
}
