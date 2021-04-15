package game.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Singleton osztaly, ami a napvihar inditasaert felelos.
 */
public class Sun{

    /**
     * A jatekban szereplo aszteroidakat egyesito
     * asteroidfield objektum. Ezek kozul valogat, amikor
     * meghivja a Sunstorm() fuggvenyt.
     */
    private Asteroidfield asteroidfield;

    /**
     * A metodus kivalaszt veletlenszeruen egy aszteroidat az
     * aszteroidamezotol lekert tombbol,
     * lekeri az aszteroidatol annak szomszedjait tartalmazo tombot ,
     * majd meghivja a Whereabout::OnFire() metodust az aszteroidara és annak osszes szomszedjara.
     */
    public void Sunstorm()
    {
        ArrayList<Asteroid> asteroids = asteroidfield.GetAsteroids();
        int rand = new Random().nextInt(asteroids.size());
        Asteroid asteroid = asteroids.get(rand);
        ArrayList<Whereabout> neighbours = asteroid.GetNeighbours();
        for(int i=0; i<neighbours.size(); i++)
        {
            neighbours.get(i).OnFire();
        }
        asteroid.OnFire();
    }

    /**
     *  Felveszi a kapott aszteroidamezot a nyilvantartasba.
     * @param af: a beallitando aszteroidamezo.
     */

    public void AddAsteroidfield(Asteroidfield af)
    {
        asteroidfield = af;
    }
}
