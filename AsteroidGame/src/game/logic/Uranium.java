package game.logic;

public class Uranium extends Material{

    public Uranium(){
        name = "Uranium";
    }

    /*
    Az urán felrobbantja az aszteroidát, ha nap éri.
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Disintegrate();
        a.Explode();
    }
}
