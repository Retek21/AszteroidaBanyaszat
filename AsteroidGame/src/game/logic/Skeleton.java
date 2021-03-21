package game.logic;

import java.util.Scanner;

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
}
