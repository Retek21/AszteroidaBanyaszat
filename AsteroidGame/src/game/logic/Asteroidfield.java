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
        System.out.println("\tAsteroidfield: Asteroidfield()");
        asteroids=_asteroids;
    }
    public Asteroidfield(){
        asteroids = new ArrayList<Asteroid>();
    }

    //METHODS, FUNCTIONS:

    //calls the Rearrange() method
    public void Event(){
        System.out.println("\tAsteroidfield: Event()");
        Rearrange();
    }

    //sets the sunnerarness of the of each asteroid separately randomly
    public void Rearrange(){
        System.out.println("\tAsteroidfield: Rearrange()");

        //only an idea, not final
        /*for(int i=0;i<asteroids.size();i++){
            //sets an asteroid close to the sun with a 10% chance
            Random random= new Random();
            int tmp=random.nextInt(10)+1;
            if(i==1)asteroids.get(i).SetSunnearness(true);
        }*/
        for(int i=0;i<asteroids.size();i++)
        {

        }
    }

    public ArrayList<Asteroid> GetAsteroids(){
        System.out.println("\tAsteroidfield: GetAsteroids()");
        System.out.println("\tAsteroidfield: GetAsteroids() return:asteroids");
        return asteroids;
    }

    //removes an asteroid from the asteroids
    public void RemoveAsteroid(Asteroid asteroid){
        System.out.println("\tAsteroidfield: RemoveAsteroid(asteroid)");
        asteroids.remove(asteroid);
    }

    //adds an asteroid to asteroids
    public void AddAsteroid(Asteroid asteroid){
        System.out.println("\tAsteroidfield: AddAsteroid(asteroid)");
        asteroids.add(asteroid);
    }

    //append asteroids with another set of collection of asteroids
    public void AddAsteroids(ArrayList<Asteroid> asteroids){
        System.out.println("\tAsteroidfield: AddAsteroids(asteroids)");
        asteroids.addAll(asteroids);
    }
}
