package game.logic;

public class Sun{

    private Asteroidfield asteroidfield;

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
