package game.logic;

//class for testing basic business logic of the game
public class Skeleton {

    public static int tab = 0;

    public static void WriteName(String s) {
        for (int i = 0; i < tab; i++)
            System.out.print("\t");
        System.out.print(s + "\n");
    }

    //Attributes for testing may be added here:
    Asteroidfield asteroidfield;
    Asteroid asteroid1;
    Asteroid asteroid2;

    //the methods of the test class should follow the given format:
    /*
    public void NameOfTheUseCase(){
        CODE HERE
    }*/
    //should always be public void
    public void DeleteLaterTestExample(){
        asteroidfield=new Asteroidfield();
        asteroid1=new Asteroid(3,false,new Coal(),asteroidfield);
        asteroidfield.AddAsteroid(asteroid1);
        System.out.println("This is an example.");
    }

    public void AsteroidfieldMoves()
    {
        WriteName("INITIALIZATION:");
        tab++;
        Asteroidfield af = new Asteroidfield();
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        Asteroid a3 = new Asteroid();
        a1.AddNeighbour(a2);
        a1.AddNeighbour(a3);
        a2.AddNeighbour(a1);
        a2.AddNeighbour(a3);
        a3.AddNeighbour(a1);
        a3.AddNeighbour(a2);
        af.AddAsteroid(a1);
        af.AddAsteroid(a2);
        af.AddAsteroid(a3);
        Iron iron = new Iron();
        Ice ice = new Ice();
        Uranium u = new Uranium();
        a1.AddMaterial(iron);
        a2.AddMaterial(ice);
        a3.AddMaterial(u);
        tab--;
        WriteName("TESTCASE:");
        tab++;
        af.Rearrange();
        tab--;
    }

    public void SunstormHitsAsteroidfield()
    {
        WriteName("INITIALIZATION:");
        tab++;
        Sun sun = new Sun();
        Asteroidfield af = new Asteroidfield();
        sun.AddAsteroidfield(af);
        Asteroid a1 = new Asteroid();
        af.AddAsteroid(a1);
        Asteroid a2 = new Asteroid();
        af.AddAsteroid(a2);
        Robot r1 = new Robot();
        Settler s1 = new Settler();
        Inventory i1 = new Inventory();
        s1.SetInventory(i1);
        Robot r2 = new Robot();
        Settler s2 = new Settler();
        Inventory i2 = new Inventory();
        s2.SetInventory(i2);
        a1.AddEntity(s1);
        r1.Deploy(a1);
        a2.AddEntity(s2);
        r2.Deploy(a2);
        Iron iron = new Iron();
        a1.AddMaterial(iron);
        tab--;
        WriteName("TESTCASE:");
        tab++;
        sun.Sunstorm();
        tab--;
    }

    public void LonelyAsteroidRxplodesWithRobotAndSettler()
    {
        WriteName("INITIALIZATION:");
        tab++;
        Asteroidfield af = new Asteroidfield();
        Asteroid a = new Asteroid();
        af.AddAsteroid(a);
        Robot r = new Robot();
        Settler s = new Settler();
        Inventory i = new Inventory();
        s.SetInventory(i);
        a.AddEntity(s);
        r.Deploy(a);
        tab--;
        WriteName("TESTCASE:");
        tab++;
        a.Explode();
        tab--;
    }

    public void AsteroidRxplodesWithRobotAndSettler()
    {
        WriteName("INITIALIZATION:");
        tab++;
        Asteroidfield af = new Asteroidfield();
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        Asteroid a3 = new Asteroid();
        a1.AddNeighbour(a2);
        a1.AddNeighbour(a3);
        a2.AddNeighbour(a1);
        a2.AddNeighbour(a3);
        a3.AddNeighbour(a1);
        a3.AddNeighbour(a2);
        af.AddAsteroid(a1);
        af.AddAsteroid(a2);
        af.AddAsteroid(a3);
        Settler s = new Settler();
        Inventory i = new Inventory();
        s.SetInventory(i);
        Robot r = new Robot();
        a1.AddEntity(s);
        r.Deploy(a1);
        tab--;
        WriteName("TESTCASE:");
        tab++;
        a1.Explode();
        tab--;
    }

    public void ControlRobot()
    {
        WriteName("INITIALIZATION:");
        tab++;
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        Robot r = new Robot();
        r.Deploy(a1);
        tab--;
        WriteName("TESTCASE:");
        tab++;
        r.DoPhase();
        tab--;
    }

    public void AsteroidExplodesWithTeleportTheOtherPairIsNearbyAnAsteroid()
    {
        WriteName("INITIALIZATION:");
        tab++;
        Asteroidfield af = new Asteroidfield();
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        Asteroid a3 = new Asteroid();
        a1.AddNeighbour(a2);
        a1.AddNeighbour(a3);
        a2.AddNeighbour(a1);
        a2.AddNeighbour(a3);
        a3.AddNeighbour(a1);
        a3.AddNeighbour(a2);
        af.AddAsteroid(a1);
        af.AddAsteroid(a2);
        af.AddAsteroid(a3);
        Teleport t1 = new Teleport();
        Teleport t2 = new Teleport();
        t1.SetPair(t2);
        t2.SetPair(t1);
        t1.Deploy(a1);
        t2.Deploy(a2);
        tab--;
        WriteName("TESTCASE:");
        tab++;
        a1.Explode();
        tab--;
    }

    public void AsteroidExplodesWithTeleportTheOtherPairIsInAnInventory()
    {
        WriteName("INITIALIZATION:");
        tab++;
        Asteroidfield af = new Asteroidfield();
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        Asteroid a3 = new Asteroid();
        a1.AddNeighbour(a2);
        a1.AddNeighbour(a3);
        a2.AddNeighbour(a1);
        a2.AddNeighbour(a3);
        a3.AddNeighbour(a1);
        a3.AddNeighbour(a2);
        af.AddAsteroid(a1);
        af.AddAsteroid(a2);
        af.AddAsteroid(a3);
        Inventory i = new Inventory();
        Teleport t1 = new Teleport();
        Teleport t2 = new Teleport();
        t1.SetPair(t2);
        t2.SetPair(t1);
        i.AddTeleport(t2);
        t1.Deploy(a1);
        tab--;
        WriteName("TESTCASE:");
        tab++;
        a1.Explode();
        tab--;
    }

    public void SettlerDiesWithTeleportInInventory()
    {
        WriteName("INITIALIZATION:");
        tab++;
        Asteroid a = new Asteroid();
        Asteroid a2 = new Asteroid();
        a.AddNeighbour(a2);
        a2.AddNeighbour(a);
        Settler s = new Settler();
        Inventory i = new Inventory();
        s.SetInventory(i);
        Teleport t = new Teleport();
        Teleport pair = new Teleport();
        t.SetPair(pair);
        pair.SetPair(t);
        i.AddTeleport(t);
        pair.Deploy(a);
        a2.AddEntity(s);
        tab--;
        WriteName("TESTCASE:");
        tab++;
        s.Die();
        tab--;
    }


}
