package game.logic;

//class for testing basic business logic of the game
public class Skeleton {

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
