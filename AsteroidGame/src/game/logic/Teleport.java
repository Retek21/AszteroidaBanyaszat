package game.logic;

import game.controller.Controller;

/**
 * Teleport osztaly, a Whereabout, és Placeable interfeszeket valositja meg.
 * Felelossége, hogy ket nem szomszedos aszteroida között
 * biztositson lepesi lehetoseget.
 */
public class Teleport implements Whereabout{

    /**
     * az aszteroida, aminek a szomszedsagaban a teleport aktualisan megtalahato
     */
    private Asteroid asteroid;

    /**
     * az inventory, amiben a teleport aktualisan tarolva van
     */
    private Inventory inventory;

    /**
     * a teleportkapu parja
     */
    private Teleport pair;

    /**
     * logikai ertek, ami azt jelzi, hogy a kapu aktív-e,
     * azaz a parjat mar lehelyeztek-e
     */
    private boolean pairready;

    /**
     * logikai ertek, ami  azt jelzi, hogy a teleport megkergult-e
     * akkor igaz, ha a teleportot napszel erte
     */
    private boolean gonecrazy;

    /**
     * Default konstruktor
     */
    public Teleport() {
        asteroid = null;
        inventory = null;
        pair = null;
        gonecrazy = false;
    }

    /**
     * Teleport konstruktor, a parameterkent kapott inventory-t allitja be tagvaltozonak.
     * @param i
     */
    public Teleport(Inventory i) {
        asteroid = null;
        pair = null;
        gonecrazy = false;

        inventory = i;
    }

    /**
     * Visszaadja a teleport párját.
     * @return - A teleport párja
     */
    public Teleport GetPair() {
        return pair;
    }

    /**
     * Ha a teleport parja le van helyezve (pairready),
     * elkeri a parjatol annak gazdaaszteroidajat es
     * ralepteteti a parameterkent kapott entitást.
     * @param e : entitas, ami az objektumra lepett
     * @return a leptetes sikeressege. Igaz, ha a teleport parja le van helyezve,
     * azaz a pairready = true.
     */
    public boolean AddEntity(Entity e)
    {
        if (pairready)
        {
            pair.GetAsteroid().AddEntity(e);
            return true;

        }
        return false;
    }

    /**
     * A felrobbano aszteroida hivja meg ezt a szomszedjain.
     * Ilyenkor a parjaval egyutt felrobbanast vegzo fuggvényt meghivja a teleport.
     * @param a: a felrobbant aszteroida
     */
    public void NearbyExplosion(Asteroid a)
    {
        ExplodeWithPair();
    }

    /**
     * A parjaval egyutt felrobban a teleport.
     * Miutan meghivta a parjara a felrobbanast vegzo metodust, magaval is ezt teszi.
     */
    public void ExplodeWithPair(){
        if(pair != null)
            pair.Explode();
        Explode();
    }

    /**
     * A teleport felrobban.
     * Ha Inventoryban volt, akkor eltavolitja magat onnan.
     * Ha aszteroida szomszedsagaban volt, akkor eltavolitja magat
     * az aszteroida szomszedai kozul.
     * A controller-nek is szol, hogy felrobbant.
     */
    public void Explode()
    {
        if(asteroid != null)
            asteroid.RemoveNeighbour(this);
        else if(inventory != null)
            inventory.RemoveTeleport(this);
        Controller.GetInstanceOf().TeleportExplode(this);
    }

    /**
     * inventory beallitasa
     * @param i: a beallitando inventory
     */
    public void SetInventory(Inventory i)
    {
        inventory = i;
    }

    /**
     * A teleport parjanak a beallitasa. Ha a par mar le van helyezve,
     * pairready flag aktiv.
     * @param t: a teleport parja, amivel kapcsolatba lep a lehelyezes utan
     */
    public void SetPair(Teleport t)
    {
        pair = t;
        if (pair.GetAsteroid() != null) {
            pairready = true;
        }
        else {
            pairready = false;
        }
    }


    /**
     * Visszaadja az aszteroidat, aminek a teleport a szomszedsagaban van.
     * Erre akkor van szukseg ha a lerakott parra ralep egy entitas.
     * @return: gazdaaszteroida
     */
    public Asteroid GetAsteroid()
    {
        return asteroid;
    }

    /**
     * A teleportot napszel eri.
     * Hatasara megkergul, es true-ra allitja a gonecrazy flag-et
     */
    public void OnFire(){
        gonecrazy = true;
    }


    /**
     * Visszaadja azt az aszteroidat, amely egy a teleportra lepo
     * objektumnak celaszteroidaja lesz.
     * Teleport eseteben, ez parjanak a gazdaszteroidaja.
     * Ha nincs lehelyezve meg a parja, a metodus null-lal ter vissza.
     * @return - Ralepo objektum celaszteroidaja.
     */
    public Asteroid GetLandingPad(){
        if(pairready){
            Asteroid target = pair.GetAsteroid();
            return target;
        }
        return null;
    }

    /**
     * A teleport miutan megkergult, mozogni kezd az aszteroidak kozott
     * A kapott parameteru objektumot allitja be uj szomszedjakent.
     * @param w: A whereabout, amire ralep a teleport
     * @return: a mozgas sikeressege
     */
    public boolean Move(Whereabout w){
        for(int i= 0; i<asteroid.GetNumberOfNeighbours();i++){
            if (this.equals(asteroid.GetNeighbours().get(i))) continue;
            if(w.equals(asteroid.GetNeighbours().get(i))){
                Asteroid target = w.GetLandingPad();
                if(target != null){
                    asteroid.RemoveNeighbour(this);
                    Deploy(target);
                    return true;
                }
                else return false;
            }
        }
        return false;
    }

    /**
     * A teleport lehelyezese a parameterkent kapott aszteroidara.
     * Akkor hivodik meg, amikor a telepes lehelyezi arra az aszteroidara, ahol eppen all.
     * @param a: az aktualis aszteroida, amire lehelyezodik a teleport
     * @return: a lehelyezes sikeressege
     */
    public boolean Deploy(Asteroid a){
       boolean added =  a.AddNeighbour(this);
       if(added){
           asteroid = a;
           inventory = null;
           if(pair != null)
               pair.SetPairReadyness(true);
           return true;
       }
       return false;
    }

    /**
     * beallitja a pairready booleant a kapott parameterre.
     * @param ready: a kapott logikai ertek
     */
    public void SetPairReadyness(boolean ready){
        pairready = ready;
    }

    /**
     * Visszaadja azt, hogy a pár le van-e rakva
     * @return a visszaadott logikai érték
     */
    public boolean GetPairReadiness(){
        return pairready;
    }

    /**
     * visszaadja a gonecrazy boolean erteket
     * @return: gonecrazy logikai erteke
     */
    public boolean GetCraziness(){
        return gonecrazy;
    }
}
