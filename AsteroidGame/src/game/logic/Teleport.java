package game.logic;

//A játékban a teleportkapu, ami 2 aszteroidát köt össze a párja segítségével.
//A Whereabout, és Placeable interfészeket valósítja meg.
public class Teleport implements Whereabout, Placeable{

    //Az aszteroida amihez kapcsolódik.
    private Asteroid asteroid;

    //Az Inventory amiben van.
    private Inventory inventory;

    //A teleportnak a párja.
    private Teleport pair;

    //Logikai érték, ami azt jelzi, hogy a párját lehelyezték-e már (igaz-lehelyezték/hamis-még nem).
    private boolean pairready;

    //Teleport konstruktora
    public Teleport() {}

    //Amikor egy entitás ide lép ez hívódik meg.
    //Átrakja az aszteroidára amihez a párja tartozott.
    //Visszatér a művelet sikerességével.
    public boolean AddEntity(Entity e)
    {
        System.out.println("\nTeleport: AddEntity(Entity e)");
        //ha a pár még nincs lerakva false-val tér vissza
        if (pairready)
            return pair.GetAsteroid().AddEntity(e);
        else
            return false;
    }

    //A felrobbanó aszteroida hívja meg ezt a függvényt a szomszédjain.
    //Ilyenkor a párjával együtt felrobbanást végző függvényt meghívja a teleport.
    public void NearbyExplosion(Asteroid a)
    {
        System.out.println("\nTeleport: NearbyExplosion(Asteroid a)");
        ExplodeWithPair();
    }

    //A párjával együtt felrobban a teleport.
    //Miután meghívta a párjára a felrobbanást végző metódust, magával is ezt teszi.
    public void ExplodeWithPair(){
        System.out.println("\nTeleport: ExplodeWithPair()");
        if(pair != null)
            pair.Explode();
        Explode();
    }

    //A teleport felrobban
    //Ha Inventoryban volt, akkor kiszedi magát onnan
    //Ha aszteroida szomszédságában volt, akkor kiveszi magát az aszteroida szomszédjai közül.
    public void Explode()
    {
        System.out.println("\nTeleport: Explode()");
        if(asteroid != null)
            asteroid.RemoveNeighbour(this);
        else if(inventory != null)
            inventory.RemoveTeleport(this);
    }

    //Beállítja a megadott Inventory-t a sajátjának.
    public void SetInventory(Inventory i)
    {
        System.out.println("\nTeleport: SetInventory(Inventory i)");
        inventory = i;
    }

    //Egy aszteroida szomszédságába települ a teleport.
    public void Deploy(Asteroid a)
    {
        System.out.println("\nTeleport: Deploy(Asteroid a)");
        a.AddNeighbour(this);
        asteroid = a;
        //Jelzi a párjának, hogy letelepítették.
        if(pair != null)
            pair.SetPairReady(true);

        //nem lesz többet az inventoryban
        inventory = null;
    }

    //Beállítja a megadott teleportot a párjának
    public void SetPair(Teleport t)
    {
        System.out.println("\nTeleport: SetPair(Teleport t)");
        pair = t;
        //Mivel ez elkészítéskor hívódik meg még nincs lerakva a párja
        pairready = false;
    }

    //Beállítja, hogy a pár le van-e telepítve
    public void SetPairReady(boolean b)
    {
        System.out.println("\nTeleport: SetPairReady(boolean b)");
        pairready = b;
    }

    //Visszaadja az aszteroidát, aminek a teleport a szomszédságában van
    //Erre akkor van szükség ha a lerakott párra rálép egy entitás
    public Asteroid GetAsteroid()
    {
        System.out.println("Teleport: GetAsteroid()");
        return asteroid;
    }
}
