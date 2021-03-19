package game.logic;

import java.util.ArrayList;

//Ez az osztály reprezentálja a napot amik körül az aszteroidák keringenek.
public class Sun{

    // A naphoz tartozó aszteroidamező.
    private Asteroidfield asteroidfield;

    //Ez a függvény hívja meg a nap aszteroidamezejéhez tartozó aszeroidákon a napvihar végbemenését.
    public void Sunstorm()
    {
        Skeleton.WriteName("\nSun: Sunstorm()");
        Skeleton.tab++;
        ArrayList<Asteroid> asteroids = asteroidfield.GetAsteroids();
        for(int i=0; i<asteroids.size(); i++)
        {
            asteroids.get(i).OnFire();
        }
        Skeleton.tab--;
    }
}
