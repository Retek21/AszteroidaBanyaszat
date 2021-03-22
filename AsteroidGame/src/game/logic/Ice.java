package game.logic;

public class Ice extends Material{

    public Ice(){
        Skeleton.WriteName("Ice: Ice()");
        name = "Ice";
    }

    /*
    A jég "elszublimál". Meghívódik a Disintegrate(), és lekerül az aszteroidáról.
     */
    @Override
    public void Interact(Asteroid a) {
        super.Interact(a);
        Disintegrate();
        a.RemoveMaterial();
    }
}
