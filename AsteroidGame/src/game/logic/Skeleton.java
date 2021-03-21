package game.logic;

import java.util.Scanner;

//class for testing basic business logic of the game
public class Skeleton {

    //STATIC:

    //attribute storing the number of tabulators:
    public static int tab = 0;

    //method for the indented console output:
    public static void WriteName(String s) {
        //whenever the method is called, itt writes
        //the number of tabulators before the text it receives
        //in the header of the method:
        for (int i = 0; i < tab; i++)
            System.out.print("\t");
        //finally breaks the line
        System.out.print(s + "\n");
    }



    //TEST CASES:

    //test case for settler drills asteroid with uranium
    public void SettlerDrillsAsteroidWithUranium(){
        WriteName("[START] SETTLERS DRILLS ASTEROID WITH URANIUM");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Uranium u=new Uranium();
        Asteroid a=new Asteroid();
        a.AddMaterial(u);
        Asteroidfield af=new Asteroidfield();
        af.AddAsteroid(a);
        Settler s=new Settler();
        Inventory i=new Inventory();
        s.SetInventory(i);
        a.AddEntity(s);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        s.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu
    }

    //test case for settler drills asteroid with ice
    public void SettlerDrillsAsteroidWithIce(){
        WriteName("[START] SETTLERS DRILLS ASTEROID WITH ICE");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Ice i=new Ice();
        Asteroid a=new Asteroid();
        a.AddMaterial(i);
        Settler s=new Settler();
        a.AddEntity(s);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        s.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu

    }

    //test case for settler drills asteroid with coal
    public void SettlerDrillsAsteroidWithCoal(){
        WriteName("[START] SETTLERS DRILLS ASTEROID WITH COAL");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Coal c=new Coal();
        Asteroid a=new Asteroid();
        a.AddMaterial(c);
        Settler s=new Settler();
        a.AddEntity(s);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        s.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu


    }

    //test case for robot drills asteroid with uranium
    public void RobotDrillsAsteroidWithUranium(){
        WriteName("[START] ROBOT DRILLS ASTEROID WITH URANIUM");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Uranium u=new Uranium();
        Asteroid a=new Asteroid();
        a.AddMaterial(u);
        Asteroidfield af=new Asteroidfield();
        af.AddAsteroid(a);
        Robot r=new Robot();
        r.Deploy(a);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        r.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu
    }

    //test case for robot drills asteroid with ice
    public void RobotDrillsAsteroidWithIce(){
        WriteName("[START] ROBOT DRILLS ASTEROID WITH ICE");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Ice i=new Ice();
        Asteroid a=new Asteroid();
        a.AddMaterial(i);
        Robot r=new Robot();
        r.Deploy(a);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        r.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu
    }

    //test case for robot drills asteroid with coal
    public void RobotDrillsAsteroidWithCoal(){
        WriteName("[START] ROBOT DRILLS ASTEROID WITH COAL");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Coal c=new Coal();
        Asteroid a=new Asteroid();
        a.AddMaterial(c);
        Robot r=new Robot();
        r.Deploy(a);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        r.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu
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


    //Tests Settler places teleport
    public void SettlerPlacesTeleport(){

        //INITIALIZATIONS
        Settler settler = new Settler();
        Inventory inventory = new Inventory();
        settler.SetInventory(inventory);
        Teleport teleport1 = new Teleport();
        inventory.AddTeleport(teleport1);
        Asteroid asteroid1 = new Asteroid();
        asteroid1.AddEntity(settler);

        //Call function
        settler.PlaceTeleport(teleport1);
    }

    //Tests Settler tries to place material into full asteroid
    public void SettlerTriesToPlaceMaterialIntoFullAsteroid(){

        //INITIALIZATIONS
        Settler settler = new Settler();
        Inventory inventory = new Inventory();
        Asteroid asteroid1 = new Asteroid();
        Coal coal = new Coal();
        Iron iron = new Iron();
        settler.SetInventory(inventory);
        inventory.AddMaterial(coal);
        asteroid1.AddEntity(settler);
        asteroid1.AddMaterial(iron);

        //Call function
        settler.PlaceMaterial(coal);
    }

    //Tests Settler places coal
    public void SettlerPlacesCoal(){

        //INITIALIZATIONS
        Settler settler = new Settler();
        Inventory inventory = new Inventory();
        Coal coal = new Coal();
        Asteroid asteroid1 = new Asteroid();
        settler.SetInventory(inventory);
        inventory.AddMaterial(coal);
        asteroid1.AddEntity(settler);

        //Call function
        settler.PlaceMaterial(coal);
    }

    //Tests Settler places uranium
    public void SettlerPlacesUranium(){

        //INITIALIZATIONS
        Settler settler = new Settler();
        Inventory inventory = new Inventory();
        Uranium uranium = new Uranium();
        Asteroid asteroid1 = new Asteroid();
        Teleport teleport1 = new Teleport();
        Teleport teleport2 = new Teleport();
        Asteroid asteroid2 = new Asteroid();
        Asteroidfield asteroidfield = new Asteroidfield();

        asteroid1.AddEntity(settler);
        settler.SetInventory(inventory);
        inventory.AddMaterial(uranium);
        inventory.AddTeleport(teleport1);
        asteroidfield.AddAsteroid(asteroid1);
        asteroidfield.AddAsteroid(asteroid2);
        asteroid1.AddNeighbour(teleport1);
        teleport1.SetPair(teleport2);
        asteroid2.AddNeighbour(asteroid1);
        asteroid1.AddNeighbour(asteroid2);
        asteroid1.SetSunnearness(true);

        //Call function
        settler.PlaceMaterial(uranium);
    }

    public void SettlerTriesToCraftRobot() {
        System.out.println("[START] SETTLER TRIES TO CRAFT ROBOT");
        System.out.print("\n");
        System.out.println("[INITIALIZATION] Initialization of the required objects:");

        Factory f = new Factory();
        Settler s = new Settler(f);
        Inventory i = new Inventory();
        Iron iron = new Iron();
        Ice ice = new Ice();
        Uranium uran = new Uranium();
        i.AddMaterial(iron);
        i.AddMaterial(ice);
        i.AddMaterial(uran);
        s.SetInventory(i);

        System.out.println("[LAUNCHING] Launching the test:");
        System.out.print("\n");

        s.CraftRobot();

        System.out.printf("[END]");
    }

    public void SettlerCraftsRobot() {
        System.out.println("[START] SETTLER CRAFTS ROBOT");
        System.out.print("\n");
        System.out.println("[INITIALIZATION] Initialization of the required objects:");

        Factory f = new Factory();
        Settler s = new Settler(f);
        Inventory i = new Inventory();
        Iron iron = new Iron();
        Coal coal = new Coal();
        Uranium uran = new Uranium();
        i.AddMaterial(iron);
        i.AddMaterial(coal);
        i.AddMaterial(uran);
        s.SetInventory(i);
        Asteroid a = new Asteroid();
        a.AddEntity(s);

        System.out.println("[LAUNCHING] Launching the test:");
        System.out.print("\n");

        s.CraftRobot();

        System.out.printf("[END]");
    }

    public void SettlerTriesToCraftTeleports() {
        System.out.println("[START] SETTLER TRIES TO CRAFT TELEPORTS");
        System.out.print("\n");
        System.out.println("[INITIALIZATION] Initialization of the required objects:");

        Factory f = new Factory();
        Settler s = new Settler(f);
        Inventory i = new Inventory();
        Iron iron = new Iron();
        Ice ice = new Ice();
        Uranium uran = new Uranium();
        i.AddMaterial(iron);
        i.AddMaterial(ice);
        i.AddMaterial(uran);
        s.SetInventory(i);

        System.out.println("[LAUNCHING] Launching the test:");
        System.out.print("\n");

        s.CraftTeleport();

        System.out.printf("[END]");
    }

    public void SettlerTriesToCraftTeleportWithoutFreeSlot() {
        System.out.println("[START] SETTLER TRIES TO CRAFT TELEPORT WITHOUT FREE SLOT");
        System.out.print("\n");
        System.out.println("[INITIALIZATION] Initialization of the required objects:");

        Settler s = new Settler();
        Inventory i = new Inventory();
        Teleport t = new Teleport();
        i.AddTeleport(t);
        s.SetInventory(i);

        System.out.println("[LAUNCHING] Launching the test:");
        System.out.print("\n");

        s.CraftTeleport();

        System.out.printf("[END]");
    }

    public void SettlerCraftsTeleports() {
        System.out.println("[START] SETTLER CRAFTS TELEPORTS");
        System.out.print("\n");
        System.out.println("[INITIALIZATION] Initialization of the required objects:");

        Factory f = new Factory();
        Settler s = new Settler(f);
        Inventory i = new Inventory();
        Iron iron1 = new Iron();
        Iron iron2 = new Iron();
        Ice ice = new Ice();
        Uranium uran = new Uranium();
        i.AddMaterial(iron1);
        i.AddMaterial(iron2);
        i.AddMaterial(uran);
        i.AddMaterial(ice);
        s.SetInventory(i);

        System.out.println("[LAUNCHING] Launching the test:");
        System.out.print("\n");

        s.CraftTeleport();

        System.out.printf("[END]");
    }

    public void SettlerTriesToMineEmptyAsteroid() {
        System.out.println("[START] SETTLER TRIES TO MINE EMPTY ASTEROID");
        System.out.print("\n");
        System.out.println("[INITIALIZATION] Initialization of the required objects:");

        Settler s = new Settler();
        Inventory i = new Inventory();
        Asteroid a = new Asteroid();
        s.SetInventory(i);
        a.AddEntity(s);

        System.out.println("[LAUNCHING] Launching the test:");
        System.out.print("\n");

        s.Mine();

        System.out.printf("[END]");
    }

    public void SettlerTriesToMineWithInventoryFull() {
        System.out.println("[START] SETTLER TRIES TO MINE WITH INVENTORY FULL");
        System.out.print("\n");
        System.out.println("[INITIALIZATION] Initialization of the required objects:");

        Settler s = new Settler();
        Inventory i = new Inventory();
        for(int j = 0; j < 10; j++)
            i.AddMaterial(new Ice());
        s.SetInventory(i);

        System.out.println("[LAUNCHING] Launching the test:");
        System.out.print("\n");

        s.Mine();

        System.out.printf("[END]");
    }

    public void SettlerMines() {
        System.out.println("[START] SETTLER MINES");
        System.out.print("\n");
        System.out.println("[INITIALIZATION] Initialization of the required objects:");

        Settler s = new Settler();
        Inventory i = new Inventory();
        s.SetInventory(i);
        Asteroid a = new Asteroid();
        Coal coal = new Coal();
        a.AddMaterial(coal);
        a.AddEntity(s);

        System.out.println("[LAUNCHING] Launching the test:");
        System.out.print("\n");

        System.out.println("[INPUT] How many layers has the asteroid? (Integer, greater than or equal to 0)");
        Scanner in = new Scanner(System.in);
        int layers = in.nextInt();
        a.SetLayer(layers);

        s.Mine();

        System.out.printf("[END]");
    }

    //Tests Settler places ice
    public void SettlerPlacesIce(){

        //INITIALIZATIONS
        Settler settler = new Settler();
        Inventory inventory = new Inventory();
        Ice ice = new Ice();
        Asteroid asteroid1 = new Asteroid();

        settler.SetInventory(inventory);
        inventory.AddMaterial(ice);
        asteroid1.AddEntity(settler);

        //Call function
        settler.PlaceMaterial(ice);
    }
}
