package game.logic;

public class Uranium extends Material{
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Disintegrate();
        a.Explode();
    }
}
