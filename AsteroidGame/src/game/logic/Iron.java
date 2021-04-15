package game.logic;

/**
 * Iron nyersanyag, dolgok(robot, teleport, bázis) építéséhez szükséges.\
 * @author torok
 */
public class Iron extends Material{

    /**
     * Az Iron konstruktora ami beallitja a nevet
     */
    public Iron(Controller _c){
        c = _c;
        name = "Iron";
    }

    /**
     * Meghívja a kontroller IronDisintegrate metódusát
     */
    @Override
    public void Disintegrate() {
        super.Disintegrate();
        c.IronDisintegrate();
    }
}
