package game.logic;

//A játékban a teleportkapu, ami 2 aszteroidát köt össze a párja segítségével.
//A Whereabout, és Placeable interfészeket valósítja meg.
public class Teleport implements Whereabout {

    //Az aszteroida amihez kapcsolódik.
    private Asteroid asteroid;

    //Az Inventory, amiben van.
    private Inventory inventory;

    //A teleportnak a párja.
    private Teleport pair;

    //Logikai érték, ami azt jelzi, hogy a párját lehelyezték-e már (igaz-lehelyezték/hamis-még nem).
    private boolean pairready;

    //Teleport konstruktora
    public Teleport()
    {

    }

    /*
    Amikor egy entitás a teleportra lép ez hívódik meg.
    Átrakja az entitást arra az aszteroidára, amihez a párja tartozik.
    Ha ez sikeresen megtörtént, true-val tér vissza a metódus.
    Ha nem (nincs még lehelyezve a párja) false-szal tér vissza.
     */
    public boolean AddEntity(Entity e)
    {
        //ha a pár még nincs lerakva false-val tér vissza
        if (pairready)
        {
            //Azért kell ez külön, mert az indentáló számlálót csökkenteni a fvhívás után kell
            boolean successful = pair.GetAsteroid().AddEntity(e);;
            return successful;
        }
        else
        {
            return false;
        }
    }

    //A felrobbanó aszteroida hívja meg ezt a függvényt a szomszédjain.
    //Ilyenkor a teleport a párjával együtt felrobban.
    public void NearbyExplosion(Asteroid a)
    {
        ExplodeWithPair();
    }

    //A párjával együtt felrobban a teleport.
    //Miután meghívta a párjára a felrobbanást végző metódust, magával is ezt teszi.
    public void ExplodeWithPair(){
        if(pair != null)
            pair.Explode();
        Explode();
    }

    //A teleport felrobban
    //Ha Inventoryban volt, akkor kiszedi magát onnan.
    //Ha aszteroida szomszédságában volt, akkor kiveszi magát az aszteroida szomszédjai közül.
    public void Explode()
    {
        if(asteroid != null)
            asteroid.RemoveNeighbour(this);
        else if(inventory != null)
            inventory.RemoveTeleport(this);
    }

    //Beállítja a megadott Inventory-t a tartózkodási helyének.
    public void SetInventory(Inventory i)
    {
        inventory = i;
    }

    //Egy aszteroida szomszédságába települ a teleport.
    public boolean Deploy(Asteroid a)
    {
        a.AddNeighbour(this);
        asteroid = a;

        if(pair != null)
            pair.SetPairReady(true);

        inventory = null;

        return true;
    }

    //Beállítja a megadott teleportot a párjának
    public void SetPair(Teleport t)
    {
        pair = t;
        pairready = false;
    }

    //Beállítja, hogy a pár le van-e telepítve
    public void SetPairReady(boolean b)
    {
        pairready = b;
    }

    //Visszaadja az aszteroidát, aminek a teleport a szomszédságában van.
    public Asteroid GetAsteroid()
    {
        return asteroid;
    }
}
