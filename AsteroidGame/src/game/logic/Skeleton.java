package game.logic;

//class for testing basic business logic of the game
public class Skeleton {

    public static int tab = 0;

    public static void WriteName(String s) {
        for (int i = 0; i < tab; i++)
            System.out.print("\t");
        System.out.print(s + "\n");
    }

    public void RobotMovesThroughTeleport(){
        Asteroidfield asteroidfield = new Asteroidfield();
        Asteroid asteroid1 = new Asteroid();
        Asteroid asteroid2 = new Asteroid();
        Teleport teleport = new Teleport();
        Teleport pair = new Teleport();
        Robot robot = new Robot();

        asteroid1.AddEntity(robot);
        teleport.SetPair(pair);
        pair.SetPair(teleport);
        teleport.Deploy(asteroid1);
        pair.Deploy(asteroid2);
        robot.Deploy(asteroid1);

        robot.Move(0);
    }

    public void RobotMovesToAsteroid(){
        Asteroidfield asteroidfield = new Asteroidfield();
        Asteroid asteroid1 = new Asteroid();
        Asteroid asteroid2 = new Asteroid();
        Robot robot = new Robot();

        asteroid1.AddNeighbour(asteroid2);
        robot.Deploy(asteroid1);
        asteroid2.AddNeighbour(asteroid1);

        robot.Move(0);
    }

    public void RobotTriesToMoveTroughTeleport(){
        Asteroidfield asteroidfield = new Asteroidfield();
        Asteroid asteroid1 = new Asteroid();
        Asteroid asteroid2 = new Asteroid();
        Teleport teleport = new Teleport();
        Teleport pair = new Teleport();
        Robot robot = new Robot();

        robot.Deploy(asteroid1);
        teleport.SetPair(pair);
        pair.SetPair(teleport);
        teleport.Deploy(asteroid1);

        robot.Move(0);
    }

    public void SettlerMovesThroughTeleport(){
        Asteroidfield asteroidfield = new Asteroidfield();
        Asteroid asteroid1 = new Asteroid();
        Asteroid asteroid2 = new Asteroid();
        Teleport teleport = new Teleport();
        Teleport pair = new Teleport();
        Settler settler = new Settler();

        asteroid1.AddEntity(settler);
        teleport.SetPair(pair);
        pair.SetPair(teleport);
        settler.SetAsteroid(asteroid1);
        teleport.Deploy(asteroid1);
        pair.Deploy(asteroid2);

        settler.Move(0);
    }

    public void SettlerMovesToAsteroid(){
        Asteroidfield asteroidfield = new Asteroidfield();
        Asteroid asteroid1 = new Asteroid();
        Asteroid asteroid2 = new Asteroid();
        Settler settler = new Settler();

        asteroid1.AddNeighbour(asteroid2);
        asteroid1.AddEntity(settler);
        asteroid2.AddNeighbour(asteroid1);
        settler.SetAsteroid(asteroid1);

        settler.Move(0);
    }

    public void SettlerTriesToMoveTroughTeleport(){
        Asteroidfield asteroidfield = new Asteroidfield();
        Asteroid asteroid1 = new Asteroid();
        Asteroid asteroid2 = new Asteroid();
        Teleport teleport = new Teleport();
        Teleport pair = new Teleport();
        Settler settler = new Settler();

        settler.SetAsteroid(asteroid1);
        teleport.SetPair(pair);
        pair.SetPair(teleport);
        teleport.Deploy(asteroid1);

        settler.Move(0);
    }
}
