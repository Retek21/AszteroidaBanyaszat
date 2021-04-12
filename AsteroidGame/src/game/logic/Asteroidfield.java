package game.logic;
import java.util.ArrayList;
import java.util.Random;

//Created by:Bendegúz Dengyel 2021.03.17
//Asteroidfield class
public class Asteroidfield{

    //ATTRIBUTES
    private ArrayList<Asteroid> asteroids;

    //CONSTRUCTOR:
    public Asteroidfield(ArrayList<Asteroid> _asteroids){
        Skeleton.WriteName("Asteroidfield: Asteroidfield()");

        asteroids=_asteroids;
    }
    public Asteroidfield(){
        Skeleton.WriteName("Asteroidfield: Asteroidfield()");

        asteroids = new ArrayList<Asteroid>();
    }

    //METHODS, FUNCTIONS:

    //calls the Rearrange() method
    public void Event(){
        Skeleton.WriteName("Asteroidfield: Event()");
        Skeleton.tab++;

        Rearrange();

        Skeleton.tab--;
    }

    //sets the sunnerarness of the of each asteroid separately randomly
    public void Rearrange(){
        Skeleton.WriteName("Asteroidfield: Rearrange()");

        //only an idea, not final
        /*for(int i=0;i<asteroids.size();i++){
            //sets an asteroid close to the sun with a 10% chance
            Random random= new Random();
            int tmp=random.nextInt(10)+1;
            if(i==1)asteroids.get(i).SetSunnearness(true);
        }*/

        //temporary function for tests, will be changed later:
        for(int i=0;i<asteroids.size();i++)
            asteroids.get(i).SetSunnearness(true);
    }

    public ArrayList<Asteroid> GetAsteroids(){
        Skeleton.WriteName("Asteroidfield: GetAsteroids()");
        Skeleton.WriteName("Asteroidfield: GetAsteroids() return:asteroids");

        return asteroids;
    }

    //removes an asteroid from the asteroids
    public void RemoveAsteroid(Asteroid asteroid){
        Skeleton.WriteName("Asteroidfield: RemoveAsteroid(asteroid)");

        asteroids.remove(asteroid);
    }

    //adds an asteroid to asteroids and sets it's asteroidfield
    public void AddAsteroid(Asteroid asteroid){
        Skeleton.WriteName("Asteroidfield: AddAsteroid(asteroid)");
        Skeleton.tab++;

        asteroid.SetAsteroidfield(this);

        Skeleton.tab--;

        asteroids.add(asteroid);
    }

    //append asteroids with another set of collection of asteroids and sets their asteroidfield
    public void AddAsteroids(ArrayList<Asteroid> _asteroids){
        Skeleton.WriteName("Asteroidfield: AddAsteroids(asteroids)");
        Skeleton.tab++;

        for(int i=0;i<_asteroids.size();i++)
            _asteroids.get(i).SetAsteroidfield(this);

        Skeleton.tab--;

        asteroids.addAll(_asteroids);
    }
}
