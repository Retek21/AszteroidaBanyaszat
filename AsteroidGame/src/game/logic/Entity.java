package game.logic;

//created by: Turiák Anita 2021.03.19.
//abstract Entity class
public abstract class Entity {
    //ATTRIBUTES
    //on which asteroid the entity stands
    protected Asteroid asteroid;

    //GETTERS, SETTERS
    public Asteroid GetAsteroid() {
        return asteroid;
    }

    public void SetAsteroid(Asteroid a){
        asteroid = a;
    }

    //METHODS
    /*
    Az entitás meghal, így lekerül az aszteroidáról.
     */
    public void Die(){

        asteroid.RemoveEntity(this);
    }

    /*
    Az entitás felrobban. A leszármazottak valósítják meg a metódust.
     */
    public abstract void BlowUp();

    /*
    Az entitás lefúr egy réteget azon az aszteroidán, amelyen tartózkodik.
     */
    public void Drill() {
        asteroid.ThinLayer();
    }

    /*
    Az entitás átlép az azsteroidájáról egy szomszédosra.
     */
    public void Move(int i) {

        Whereabout w;
        w = asteroid.GetNeighbour(i);
        if(w.AddEntity(this)==true)     //Ha sikertelen a továbblépés, marad a helyén.
            asteroid.RemoveEntity(this);

    }

    /*
    Megvalósítja mit csinál az entitás egy fázisban. A leszármazottak valósítják meg.
     */
    public abstract void DoPhase();
}