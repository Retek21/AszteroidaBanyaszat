package game.logic;

public class Teleport implements WhereAbout, Placeable{

    private Asteroid asteroid;

    private Inventory inventory;

    private Teleport pair;

    private boolean pairready;


    public boolean AddEntity(Entity e)
    {
        System.out.println("\nTeleport: AddEntity(Entity e)");
        return pair.GetAsteroid().AddEntity(e);;
    }

    public void NearbyExplosion(Asteroid a)
    {
        System.out.println("\nTeleport: NearbyExplosion(Asteroid a)");
        ExplodeWithPair();
    }

    public void ExplodeWithPair(){
        System.out.println("\nTeleport: ExplodeWithPair()");
        if(pair != null)
            pair.Explode();
        Explode();
    }

    public void Explode()
    {
        System.out.println("\nTeleport: Explode()");
        if(asteroid != null)
            asteroid.RemoveNeighbour(this);
        else if(inventory != null)
            inventory.RemoveTeleport(this);
    }

    public void SetInventory(Inventory i)
    {
        System.out.println("\nTeleport: SetInventory(Inventory i)");
        inventory = i;
    }

    public void Deploy(Asteroid a)
    {
        System.out.println("\nTeleport: Deploy(Asteroid a)");
        a.AddNeighbour(this);
        asteroid = a;
        if(pair != null)
            pair.SetPairReady(true);
        //nem lesz többet az inventoryban
        inventory = null;
    }

    public void SetPair(Teleport t)
    {
        System.out.println("\nTeleport: SetPair(Teleport t)");
        pair = t;
        pairready = false;
    }

    public void SetPairReady(boolean b)
    {
        System.out.println("\nTeleport: SetPairReady(boolean b)");
        pairready = b;
    }

    public Asteroid GetAsteroid()
    {
        System.out.println("Teleport: GetAsteroid()");
        return asteroid;
    }
}
