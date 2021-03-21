package game.logic;
/*
Uranium nyersanyag osztaly
 */
public class Uranium extends Material{
    /*
    Interact az aszteroidaval felrobbantja az aszteroidat
     */
    public Uranium(){
        Skeleton.WriteName("Uranium: Uranium()");
        name = "Uranium";
    }
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Skeleton.tab++;
        Disintegrate();
        a.Explode();
        Skeleton.tab--;
    }
}
