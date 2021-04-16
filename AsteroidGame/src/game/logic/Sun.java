package game.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * A napvihar elinditasaert felelos osztaly.
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
        asteroid.OnFire();
        for(int i=0; i<neighbours.size(); i++)
        {
            neighbours.get(i).OnFire();
        }
    }

    /**
     * A parameterkent kapott aszteroidatol lekeri annak szomszedait,
     * majd az aszteroidara es annak szomszedaira meghivja az OnFire()
     * metodusukat, ezzel elinditva a napvihart.
     * @param asteroid - Az aszteroida, aminek a szomszedsagaban be fog
     *                   kovetkezni a napvihar.
     */
    public void Sunstorm(Asteroid asteroid) {
        ArrayList<Whereabout> neighbours = asteroid.GetNeighbours();
        asteroid.OnFire();
        for(int i=0; i<neighbours.size(); i++)
        {
            neighbours.get(i).OnFire();
        }
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
