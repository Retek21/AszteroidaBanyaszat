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
    /*Asteroidfield asteroidfield;
    Asteroid asteroid1;
    Asteroid asteroid2;
    Teleport teleport1;
    Teleport teleport2;
    Settler settler;
    Inventory inventory;
    Coal coal;
    Iron iron;
    Uranium uranium;
    Ice ice;*/


    //the methods of the test class should follow the given format:
    /*
    public void NameOfTheUseCase(){
        CODE HERE
    }*/
    //should always be public void
    public void DeleteLaterTestExample(){
        Asteroidfield asteroidfield =new Asteroidfield();
        Asteroid asteroid1=new Asteroid(3,false,new Coal(),asteroidfield);
        asteroidfield.AddAsteroid(asteroid1);
        System.out.println("This is an example.");
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
