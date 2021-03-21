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
    Teleport teleport1;
    Teleport teleport2;
    Settler settler;
    Inventory inventory;
    Coal coal;
    Iron iron;
    Uranium uranium;
    Ice ice;


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

    public void SettlerPlacesTeleport(){
        settler = new Settler();
        inventory = new Inventory();
        settler.SetInventory(inventory);
        teleport1 = new Teleport();
        inventory.AddTeleport(teleport1);
        asteroid1 = new Asteroid();
        asteroid1.AddEntity(settler);

        settler.PlaceTeleport(teleport1);
    }

    public void SettlerTriesToPlaceMaterialIntoFullAsteroid(){
        settler = new Settler();
        inventory = new Inventory();
        asteroid1 = new Asteroid();
        coal = new Coal();
        iron = new Iron();
        settler.SetInventory(inventory);
        inventory.AddMaterial(coal);
        asteroid1.AddEntity(settler);
        asteroid1.AddMaterial(iron);

        settler.PlaceMaterial(coal);
    }

    public void SettlerPlacesCoal(){
        settler = new Settler();
        inventory = new Inventory();
        coal = new Coal();
        asteroid1 = new Asteroid();
        settler.SetInventory(inventory);
        inventory.AddMaterial(coal);
        asteroid1.AddEntity(settler);

        settler.PlaceMaterial(coal);
    }

    public void SettlerPlacesUranium(){
        settler = new Settler();
        inventory = new Inventory();
        uranium = new Uranium();
        asteroid1 = new Asteroid();
        teleport1 = new Teleport();
        teleport2 = new Teleport();
        asteroid2 = new Asteroid();
        asteroidfield = new Asteroidfield();

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

        settler.PlaceMaterial(uranium);
    }

    public void SettlerPlacesIce(){
        settler = new Settler();
        inventory = new Inventory();
        ice = new Ice();
        asteroid1 = new Asteroid();

        settler.SetInventory(inventory);
        inventory.AddMaterial(ice);
        asteroid1.AddEntity(settler);

        settler.PlaceMaterial(ice);
    }
}
