package game.logic;

public class Coal extends Material{

    public Coal(){
        Skeleton.WriteName("Coal: Coal()");
        name = "Coal";
    }

    /*
    A metódus nem definiálja felül az ős metódusát.
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
    }
}
