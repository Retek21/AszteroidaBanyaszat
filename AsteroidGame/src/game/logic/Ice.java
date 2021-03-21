package game.logic;
/*
Ice nyersanyag osztaly
 */
public class Ice extends Material{
    /*
    Interact az aszteroidaval eltunik rola
     */
    public Ice(){
        Skeleton.WriteName("Ice: Ice()");
        name = "Ice";
    }
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Disintegrate();
        a.RemoveMaterial();
    }
}
