package game.logic;

//absztrakt osztály a bányászoknak, azaz a robot és a settler őse
public abstract class Entity {
    protected Asteroid asteroid;        //nyilvántartjuk, hogy melyik aszteroidán áll

    public abstract void Die();

    public abstract void BlowUp();      //Az aszteroida robbanásakor hívódik meg

    public Asteroid GetAsteroid() {
        return asteroid;
    }

    public void Drill() {           //Az aszteroida külső rétegét csökkenti
        asteroid.ThinLayers();
    }

    public void Move(int i) {       //A megadott számú szomszédra lép (lehet aszteroida vagy teleport)
        WhereAbout w;
        w = asteroid.GetNeighbour(i);
        w.AddEntity(this);
        if(w.AddEntity(this)==true)
            asteroid.RemoveEntity(this);
    }

    public abstract void DoPhase();     //Egy fázisban lévő cselekvés
}