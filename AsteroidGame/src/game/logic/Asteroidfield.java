package game.logic;
import java.util.ArrayList;
import java.util.Random;

/**
 * Asteroidfield class
 * Az aszteroidamező az aszteroidák nyilvántartásáért felelős. Szabályozza a napközelséget, tárolja az aszteroidákat.
 * @author torok
 */
public class Asteroidfield{

    /**
     * Aszteroidák listája
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
     *Megváltoztatja, melyik aszteroida van napközelben és melyik nem. Mindegyik aszteroida sunnear változója véletlenszerűen kap egy true vagy false értéket az Asteroid::SetSunnearness(bool) metódussal.
     */
    public void Rearrange(){
        Random random = new Random();
        for (Asteroid asteroid: asteroids) {
            asteroid.SetSunnearness(random.nextBoolean());
        }

    }

    /**
     *visszatér a játékban lévő aszteroidák listájával.
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
     *Uj aszteroidat ad az aszteroidamezohoz
     * @param asteroid
     */
    public void AddAsteroid(Asteroid asteroid){
        asteroid.SetAsteroidfield(this);

        asteroids.add(asteroid);
    }

    /**
     *Az aszteroidak listjajat fuzi hozza a meglevo aszteroidahoz.
     * @param _asteroids - az uj aszteroidak listaja
     */
    public void AddAsteroids(ArrayList<Asteroid> _asteroids){

        for(int i=0;i<_asteroids.size();i++)
            _asteroids.get(i).SetAsteroidfield(this);

        asteroids.addAll(_asteroids);
    }
}
