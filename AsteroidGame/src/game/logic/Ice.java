package game.logic;

/**
 * Ice nyersanyag, dolgok(robot, teleport, bázis) építéséhez szükséges.
 * @author torok
 */
public class Ice extends Material{
    /**
     * Az Ice konstruktora ami beallitja a nevet.
     */
    public Ice(Controller _c){
        c = _c;
        name = "Ice";
    }

    /**
     * Meghívja a jég Ice::Disintegrate() metódusát. Ezután meghívja a paraméterként kapott aszteroida RemoveMaterial metódusát, a jeget adva paraméterül.
     * @param a - az aszteroida amivel interaktal
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Disintegrate();
        a.RemoveMaterial();
    }

    /**
     * Meghívja a kontroller IceDisintegrate metódusát.
     */
    @Override
    public void Disintegrate() {
        super.Disintegrate();
        c.IceDisintegrate();
    }
}
