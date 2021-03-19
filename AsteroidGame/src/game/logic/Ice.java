package game.logic;
/*
Ice nyersanyag osztaly
 */
public class Ice extends Material{
    /*
    Interact az aszteroidaval eltunik rola
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Disintegrate();
        a.RemoveMaterial(this);
    }
}
