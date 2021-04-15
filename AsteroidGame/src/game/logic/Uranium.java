package game.logic;

/**
 * Uranium nyersanyag, dolgok(robot, teleport, bázis) építéséhez szükséges. Aszteroidába helyezve a megfelelő körülmények között felrobbantja az aszteroidát.
 * @author torok
 */
public class Uranium extends Material{

    /**
     * Konstruktor ami beallitja az uranium nevet.
     */
    public Uranium(Controller _c){
        c = _c;
        name = "Uranium";
    }

    /**
     * Ha interactCount attribútum értéke nagyobb, mint 2, meghívja a Uranium::Disintegrate() metódusát, majd a paraméterként kapott aszteroida Explode() metódusát. Ha 2 vagy annál kisebb, a metódus egyszerűen visszatér.
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
     * Meghívja a kontroller UraniumDisintegrate metódusát.
     */
    @Override
    public void Disintegrate() {
        super.Disintegrate();
        c.UraniumDisintegrate();
    }
}
