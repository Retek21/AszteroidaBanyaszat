package game.logic;

//created by: Turiák Anita 2021.03.19.
//PLaceable class for objects that can be placed
public interface Placeable {

    /*
    Az interfészt megvalósító objektum le/felkerül az aszteroidára.
     */
    public boolean Deploy(Asteroid a);
}
