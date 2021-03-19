package game.logic;
/*
Uranium nyersanyag osztaly
 */
public class Uranium extends Material{
    /*
    Interact az aszteroidaval felrobbantja az aszteroidat
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Disintegrate();
        a.Explode();
    }
}
