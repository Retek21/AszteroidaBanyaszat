package game.logic;

/**
 * Coal nyersanyag, dolgok(robot, teleport, bázis) építéséhez szükséges.
 * @author torok
 */
public class Coal extends Material{

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
        super.Disintegrate();
        c.CoalDisintegrate();
    }
}
