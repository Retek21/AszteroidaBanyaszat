package game.logic;
/*
Iron nyersanyag osztaly
 */
public class Iron extends Material{
    /*
    Interact az aszteroidaval, kesobb bovitheto
     */
    public Iron(){
        Skeleton.WriteName("Iron: Iron()");
        name = "Iron";
    }
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
    }
}
