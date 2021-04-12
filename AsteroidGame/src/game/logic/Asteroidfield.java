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
        asteroids=_asteroids;
    }
    public Asteroidfield(){
        asteroids = new ArrayList<Asteroid>();
    }

    //METHODS, FUNCTIONS:

    //calls the Rearrange() method
    public void Event(){
        Rearrange();
    }

    //sets the sunnerarness of the of each asteroid separately randomly
    public void Rearrange(){

        //temporary function for tests, will be changed later:
        for(int i=0;i<asteroids.size();i++)
            asteroids.get(i).SetSunnearness(true);
    }

    public ArrayList<Asteroid> GetAsteroids(){
        return asteroids;
    }

    //removes an asteroid from the asteroids
    public void RemoveAsteroid(Asteroid asteroid){

        asteroids.remove(asteroid);
    }

    //adds an asteroid to asteroids and sets it's asteroidfield
    public void AddAsteroid(Asteroid asteroid){
        asteroid.SetAsteroidfield(this);

        asteroids.add(asteroid);
    }

    //append asteroids with another set of collection of asteroids and sets their asteroidfield
    public void AddAsteroids(ArrayList<Asteroid> _asteroids){

        for(int i=0;i<_asteroids.size();i++)
            _asteroids.get(i).SetAsteroidfield(this);

        asteroids.addAll(_asteroids);
    }
}
