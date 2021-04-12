package game.logic;

//A játékban a teleportkapu, ami 2 aszteroidát köt össze a párja segítségével.
//A Whereabout, és Placeable interfészeket valósítja meg.
public class Teleport implements Whereabout, Placeable{

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
        Skeleton.WriteName("Teleport: Teleport()");
    }

    /*
    Amikor egy entitás a teleportra lép ez hívódik meg.
    Átrakja az entitást arra az aszteroidára, amihez a párja tartozik.
    Ha ez sikeresen megtörtént, true-val tér vissza a metódus.
    Ha nem (nincs még lehelyezve a párja) false-szal tér vissza.
     */
    public boolean AddEntity(Entity e)
    {
        Skeleton.WriteName("Teleport: AddEntity(Entity e)");
        Skeleton.tab++;

        //ha a pár még nincs lerakva false-val tér vissza
        if (pairready)
        {
            //Azért kell ez külön, mert az indentáló számlálót csökkenteni a fvhívás után kell
            boolean successful = pair.GetAsteroid().AddEntity(e);

            Skeleton.tab--;

            return successful;
        }
        else
        {
            Skeleton.tab--;

            return false;
        }
    }

    //A felrobbanó aszteroida hívja meg ezt a függvényt a szomszédjain.
    //Ilyenkor a teleport a párjával együtt felrobban.
    public void NearbyExplosion(Asteroid a)
    {
        Skeleton.WriteName("Teleport: NearbyExplosion(Asteroid a)");
        Skeleton.tab++;

        ExplodeWithPair();

        Skeleton.tab--;
    }

    //A párjával együtt felrobban a teleport.
    //Miután meghívta a párjára a felrobbanást végző metódust, magával is ezt teszi.
    public void ExplodeWithPair(){
        Skeleton.WriteName("Teleport: ExplodeWithPair()");
        Skeleton.tab++;
        if(pair != null)
            pair.Explode();
        Explode();
        Skeleton.tab--;
    }

    //A teleport felrobban
    //Ha Inventoryban volt, akkor kiszedi magát onnan.
    //Ha aszteroida szomszédságában volt, akkor kiveszi magát az aszteroida szomszédjai közül.
    public void Explode()
    {
        Skeleton.WriteName("Teleport: Explode()");
        Skeleton.tab++;

        if(asteroid != null)
            asteroid.RemoveNeighbour(this);
        else if(inventory != null)
            inventory.RemoveTeleport(this);

        Skeleton.tab--;
    }

    //Beállítja a megadott Inventory-t a tartózkodási helyének.
    public void SetInventory(Inventory i)
    {
        Skeleton.WriteName("Teleport: SetInventory(Inventory i)");
        Skeleton.tab++;

        inventory = i;

        Skeleton.tab--;
    }

    //Egy aszteroida szomszédságába települ a teleport.
    public boolean Deploy(Asteroid a)
    {
        Skeleton.WriteName("Teleport: Deploy(Asteroid a)");
        Skeleton.tab++;

        a.AddNeighbour(this);
        asteroid = a;

        //Jelzi a párjának, hogy letelepítették.
        if(pair != null)
            pair.SetPairReady(true);

        //nem lesz többet az inventoryban
        inventory = null;

        Skeleton.tab--;
        Skeleton.WriteName("Teleport: Deploy(Asteroid a) return: true");

        return true;
    }

    //Beállítja a megadott teleportot a párjának
    public void SetPair(Teleport t)
    {
        Skeleton.WriteName("Teleport: SetPair(Teleport t)");
        Skeleton.tab++;

        pair = t;
        //Mivel ez elkészítéskor hívódik meg még nincs lerakva a párja
        pairready = false;

        Skeleton.tab--;
    }

    //Beállítja, hogy a pár le van-e telepítve
    public void SetPairReady(boolean b)
    {
        Skeleton.WriteName("Teleport: SetPairReady("+b+")");
        Skeleton.tab++;

        pairready = b;

        Skeleton.tab--;
    }

    //Visszaadja az aszteroidát, aminek a teleport a szomszédságában van.
    public Asteroid GetAsteroid()
    {
        Skeleton.WriteName("Teleport: GetAsteroid()");
        Skeleton.tab++;
        Skeleton.tab--;
        return asteroid;
    }
}
