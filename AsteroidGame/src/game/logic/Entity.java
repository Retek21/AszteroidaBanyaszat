package game.logic;

//created by: Turiák Anita 2021.03.19.
//abstract Entity class
public abstract class Entity {
    //ATTRIBUTES
    //on which asteroid the entity stands
    protected Asteroid asteroid;

    //GETTERS, SETTERS
    public Asteroid GetAsteroid() {
        System.out.printf("\tEntity: GetAsteroid() return: asteroid");
        return asteroid;
    }

    public void SetAsteroid(Asteroid a){
        System.out.printf("\tEntity: SetAsteroid(Asteroid a)");
        asteroid = a;
    }

    //METHODS
    //entity dies
    public void Die(){
        asteroid.RemoveEntity(this);
    }

    //the asteroid blows up
    public abstract void BlowUp();

    //entity drills the current asteroid (lowers the layers by one)
    public void Drill() {
        System.out.printf("\tEntity: Drill()");
        asteroid.ThinLayers();
    }

    //entity moves to whereabout
    public void Move(int i) {
        System.out.printf("\tEntity: Move(int i)");
        WhereAbout w;
        w = asteroid.GetNeighbour(i);
        w.AddEntity(this);
        if(w.AddEntity(this)==true)
            asteroid.RemoveEntity(this);
    }

    //entity does phase
    public abstract void DoPhase();
}