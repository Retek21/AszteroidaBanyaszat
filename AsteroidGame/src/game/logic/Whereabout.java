package game.logic;

/**
 * Egy interface, amely a szomszedossagi viszonyokert felelos
 * teleportok, aszteroidak kozott.
 */
public interface Whereabout {

    /**
     * Az interfeszt megvalosito objektum magara lepteti
     * a parameterkent kapott entitast.
     * @param e: entitas, ami az objektumra lepett
     */
    public void AddEntity(Entity e);

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
     * Az interfeszt megvalosito objektum felveszi a
     * szomszedai koze a parameterkent kapott objektumot.
     * Ezutan kapcsolatba tud lepni vele.
     * @param w: az uj szomszed,amit felvesz a nyilvantartasba.
     * @return : a hozzaadas sikeressege
     */
    public boolean AddNeighbour(Whereabout w);
}