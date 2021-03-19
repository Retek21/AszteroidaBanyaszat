package game.logic;

//Ez az interfész a mozgás, robbanás egységesítéséért szolgál.
public interface Whereabout {

    //Ez kezeli azt az esetet amikor a whereabout-ra egy entitás kerül.
    public boolean AddEntity(Entity e);

    //Ezt hívja meg a felrobbanó whereabout a szomszédjaira.
    public void NearbyExplosion(Asteroid a);

    //Ez hívódik meg amikor felrobban egy whereabout.
    public void Explode();
}
