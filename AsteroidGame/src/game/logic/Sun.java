package game.logic;

import java.util.ArrayList;

//Ez az osztály reprezentálja a napot amik körül az aszteroidák keringenek.
public class Sun{

    // A naphoz tartozó aszteroidamező.
    private Asteroidfield asteroidfield;

    public Sun()
    {
        Skeleton.WriteName("Sun()");
    }

    //A függvény meghívja a nap aszteroidamezejéhez tartozó aszeroidákon az OnFire() metódust.
    public void Sunstorm()
    {
        Skeleton.WriteName("Sun: Sunstorm()");
        Skeleton.tab++;

        ArrayList<Asteroid> asteroids = asteroidfield.GetAsteroids();
        for(int i=0; i<asteroids.size(); i++)
        {
            asteroids.get(i).OnFire();
        }
        Skeleton.tab--;
    }

    //Beállítja a kapott aszeroidát a nap aszteroidamezejének
    public void AddAsteroidfield(Asteroidfield af)
    {
        asteroidfield = af;
    }
}
