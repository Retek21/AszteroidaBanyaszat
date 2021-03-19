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

}
