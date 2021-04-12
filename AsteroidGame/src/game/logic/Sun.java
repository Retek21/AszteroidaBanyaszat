package game.logic;

import java.util.ArrayList;

//Ez az osztály reprezentálja a napot amik körül az aszteroidák keringenek.
public class Sun{

    // A naphoz tartozó aszteroidamező.
    private Asteroidfield asteroidfield;

    public Sun()
    {

    }

    //A függvény meghívja a nap aszteroidamezejéhez tartozó aszeroidákon az OnFire() metódust.
    public void Sunstorm()
    {
        ArrayList<Asteroid> asteroids = asteroidfield.GetAsteroids();
        for(int i=0; i<asteroids.size(); i++)
        {
            asteroids.get(i).OnFire();
        }
    }

    //Beállítja a kapott aszeroidát a nap aszteroidamezejének
    public void AddAsteroidfield(Asteroidfield af)
    {
        asteroidfield = af;
    }
}
