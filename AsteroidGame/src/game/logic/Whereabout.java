package game.logic;

import game.userinterface.Display;

/**
 * Egy interface, amely a szomszedossagi viszonyokert felelos
 * teleportok, aszteroidak kozott.
 */
public interface Whereabout {

    /**
     * Az interfeszt megvalosito objektum magara lepteti
     * a parameterkent kapott entitast.
     * @param e : entitas, ami az objektumra lepett
     * @return: hozzaadas sikeressege
     */
    public boolean AddEntity(Entity e);

    /**
     *Az interfeszt megvalosito objektum ertesitese arrol,hogy
     * a parameterkent kapott aszteroida felrobbant.
     * @param a: a felrobbant aszteroida
     */
    public void NearbyExplosion(Asteroid a);

    /**
     * Az interfeszt megvalosito objektum felrobban.
     */
    public void Explode();


    /**
     * Az interfeszt megvalosito objektumot napvihar eri.
     */
    public void OnFire();

    /**
     * Visszaadja azt az aszteroidat, amely a whereabout-ra lepo
     * objektumnak celaszteroidaja lesz.
     * @return - Ralepo objektum celaszteroidaja.
     */
    public Asteroid GetLandingPad();

    public Display GetDisplay();
}