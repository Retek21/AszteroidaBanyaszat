package game.logic;

public class Uranium extends Material{

    public Uranium(){
        Skeleton.WriteName("Uranium: Uranium()");
        name = "Uranium";
    }

    /*
    Az urán felrobbantja az aszteroidát, ha nap éri.
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Skeleton.tab++;

        Disintegrate();
        a.Explode();

        Skeleton.tab--;
    }
}
