package game.logic;

public class Iron extends Material{

    public Iron(){
        Skeleton.WriteName("Iron: Iron()");
        name = "Iron";
    }

    /*
    A metódus nem definiálja felül az ős metódusát.
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
    }
}
