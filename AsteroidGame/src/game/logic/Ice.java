package game.logic;

public class Ice extends Material{
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Disintegrate();
        a.RemoveMaterial(this);
    }
}
