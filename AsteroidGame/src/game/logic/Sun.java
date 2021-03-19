package game.logic;

//Ez az osztály reprezentálja a napot amik körül az aszteroidák keringenek.
public class Sun{

    // A naphoz tartozó aszteroidamező.
    private Asteroidfield asteroidfield;

    //Ez a függvény hívja meg a nap aszteroidamezejéhez tartozó aszeroidákon a napvihar végbemenését.
    public void Sunstorm()
    {
        System.out.println("\nSun: Sunstorm()");
        Asteroid[] asteroids = asteroidfield.GetAsteroids();
        for(int i=0; i<asteroids.length; i++)
        {
            asteroids[i].OnFire();
        }
    }
}
