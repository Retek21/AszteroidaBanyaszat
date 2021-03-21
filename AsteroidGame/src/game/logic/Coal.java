package game.logic;

/*
 Coal nyersanyag osztaly
 */
public class Coal extends Material{
    /*
    Interact az aszteroidával
    később bővíthető
    */
    public Coal(){
        Skeleton.WriteName("Coal: Coal()");
        name = "Coal";
    }
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
    }
}
