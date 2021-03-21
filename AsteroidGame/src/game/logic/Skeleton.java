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
    Teleport teleport;
    Teleport pair;
    Settler settler;
    Inventory inventory;
    Coal coal;
    Iron iron;
    Uranium uranium;
    Ice ice;
    Robot robot;

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
    public void RobotMovesThroughTeleport(){
        asteroidfield = new Asteroidfield();
        asteroid1 = new Asteroid();
        asteroid2 = new Asteroid();
        teleport = new Teleport();
        pair = new Teleport();
        robot = new Robot();

        asteroid1.AddEntity(robot);
        teleport.SetPair(pair);
        pair.SetPair(teleport);
        teleport.Deploy(asteroid1);
        pair.Deploy(asteroid2);
        robot.Deploy(asteroid1);

        robot.Move(0);
    }

    public void RobotMovesToAsteroid(){
        asteroidfield = new Asteroidfield();
        asteroid1 = new Asteroid();
        asteroid2 = new Asteroid();
        robot = new Robot();

        asteroid1.AddNeighbour(asteroid2);
        robot.Deploy(asteroid1);
        asteroid2.AddNeighbour(asteroid1);

        robot.Move(0);
    }

    public void RobotTriesToMoveTroughTeleport(){
        asteroidfield = new Asteroidfield();
        asteroid1 = new Asteroid();
        asteroid2 = new Asteroid();
        teleport = new Teleport();
        pair = new Teleport();
        robot = new Robot();

        robot.Deploy(asteroid1);
        teleport.SetPair(pair);
        pair.SetPair(teleport);
        teleport.Deploy(asteroid1);

        robot.Move(0);
    }

    public void SettlerMovesThroughTeleport(){
        asteroidfield = new Asteroidfield();
        asteroid1 = new Asteroid();
        asteroid2 = new Asteroid();
        teleport = new Teleport();
        pair = new Teleport();
        settler = new Settler();

        asteroid1.AddEntity(settler);
        teleport.SetPair(pair);
        pair.SetPair(teleport);
        settler.SetAsteroid(asteroid1);
        teleport.Deploy(asteroid1);
        pair.Deploy(asteroid2);

        settler.Move(0);
    }

    public void SettlerMovesToAsteroid(){
        asteroidfield = new Asteroidfield();
        asteroid1 = new Asteroid();
        asteroid2 = new Asteroid();
        settler = new Settler();

        asteroid1.AddNeighbour(asteroid2);
        asteroid1.AddEntity(robot);
        asteroid2.AddNeighbour(asteroid1);
        settler.SetAsteroid(asteroid1);

        settler.Move(0);
    }

    public void SettlerTriesToMoveTroughTeleport(){
        asteroidfield = new Asteroidfield();
        asteroid1 = new Asteroid();
        asteroid2 = new Asteroid();
        teleport = new Teleport();
        pair = new Teleport();
        settler = new Settler();

        settler.SetAsteroid(asteroid1);
        teleport.SetPair(pair);
        pair.SetPair(teleport);
        teleport.Deploy(asteroid1);

        robot.Move(0);
    }
}
