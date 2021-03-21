package game.logic;

//created by: Turiák Anita 2021.03.19.
//abstract Entity class
public abstract class Entity {
    //ATTRIBUTES
    //on which asteroid the entity stands
    protected Asteroid asteroid;

    //GETTERS, SETTERS
    public Asteroid GetAsteroid() {
        Skeleton.WriteName("Entity: GetAsteroid() return: "+asteroid);
        return asteroid;
    }

    public void SetAsteroid(Asteroid a){
        Skeleton.WriteName("Entity: SetAsteroid(Asteroid a)");
        asteroid = a;
    }

    //METHODS
    //entity dies
    public void Die(){
        Skeleton.WriteName("Entity: Die()");
        asteroid.RemoveEntity(this);
    }

    //the asteroid blows up
    public abstract void BlowUp();

    //entity drills the current asteroid (lowers the layers by one)
    public void Drill() {
        Skeleton.WriteName("Entity: Drill()");
        asteroid.ThinLayer();
    }

    //entity moves to whereabout
    public void Move(int i) {
        Skeleton.WriteName("Entity: Move(int i)");
        Skeleton.tab++;
        Whereabout w;
        w = asteroid.GetNeighbour(i);
        w.AddEntity(this);
        if(w.AddEntity(this)==true)
            asteroid.RemoveEntity(this);
        Skeleton.tab--;
    }

    //entity does phase
    public abstract void DoPhase();
}