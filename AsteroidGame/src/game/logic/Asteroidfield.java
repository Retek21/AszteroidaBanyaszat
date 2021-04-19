package game.logic;
import java.util.ArrayList;
import java.util.Random;

/**
 * Asteroidfield class
 * Az aszteroidamezo az aszteroidak nyilvantartasaert felelos.
 * Szabalyozza a napkozelseget, tarolja az aszteroidakat.
 * @author torok
 */
public class Asteroidfield{

    /**
     * Aszteroidak listaja
     */
    ArrayList<Asteroid> asteroids;

    /**
     * Konstruktor ami kap egy aszteroidfield listat
     * @param _asteroids
     */
    public Asteroidfield(ArrayList<Asteroid> _asteroids){
        asteroids=_asteroids;
    }

    /**
     * Default konstruktor, ami egy uj ures listat keszit
     */
    public Asteroidfield(){
        asteroids = new ArrayList<Asteroid>();
    }

    /**
     * visszater a jatekban levo aszteroidak listajaval.
     * @return ArrayList<Asteroid> asteroids - aszteroidak listaja
     */
    public ArrayList<Asteroid> GetAsteroids(){
        return asteroids;
    }

    /**
     * a paraméterként megadott aszteroidát eltávolítja a nyilvántartásból.
     * @param asteroid - megadott aszteroida
     */
    public void RemoveAsteroid(Asteroid asteroid){
        asteroids.remove(asteroid);
    }

    /**
     * Uj aszteroidat ad az aszteroidamezohoz
     * @param asteroid
     */
    public void AddAsteroid(Asteroid asteroid){
        asteroid.SetAsteroidfield(this);
        asteroids.add(asteroid);
    }

    /**
     * Az aszteroidak listjajat fuzi hozza a meglevo aszteroidahoz.
     * @param _asteroids - az uj aszteroidak listaja
     */
    public void AddAsteroids(ArrayList<Asteroid> _asteroids){

        for(int i=0;i<_asteroids.size();i++)
            _asteroids.get(i).SetAsteroidfield(this);

        asteroids.addAll(_asteroids);
    }
}
