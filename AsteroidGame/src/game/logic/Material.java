package game.logic;

/**
 * A különböző absztrakt nyersanyagok ősosztálya, megvalósítja a nyersanyagok megegyező függvényeit.
 * @author torok
 */
public abstract class Material {
    /**
     * A materialt azonosító név, a nyersanyagok összehasonlításához szükséges.
     */
    protected String name;

    /**
     * Az interakciók számlálója, a nyersanyagokkal történő interakciók lebonyolításához szükséges.
     */
    protected int interactCount;

    /**
     *  Referencia a játékot vezérlő kontrollerre. Játékból való kikerüléskor értesíteni kell a kontrollert.
     */
    protected  Controller c;

    /**
     * A metódus valósítja meg, hogy mi történik a nyersanyaggal, ha nap éri.
     * @param a az aszteroida ahol nap eri
     */
    public void Interact(Asteroid a){

    }

    /**
     * Lekéri a paraméterként kapott Material-tól a nevét a Material::GetName() metódussal. A kapott string-et összehasonlítja a name attribútummal. Egyezés esetén igazzal, egyéb esetben hamissal tér vissza.
     * @param m material amivel osszehasonlitja magat
     * @return a bool ami megmondja hogy egyeznek-e
     */
    public Boolean CompareMaterial(Material m){
        if(m.name.compareTo(name) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Beállítja az interactCount értékét a paraméterként kapott számra.
     * @param i- az ertek amivel noveli az interactcountot
     */
    public void SetInteractCount(int i){
        interactCount += i;
    }

    /**
     * A nyersanyag megsemmisülését a kontrollernek jelző absztrakt metódus.
     */
    public void Disintegrate(){

    }


}
