package game.logic;

import game.controller.Controller;

/**
 * A kulonbozo absztrakt nyersanyagok ososztalya, megvalositja a nyersanyagok megegyezo fuggvenyeit.
 * @author torok
 */
public abstract class Material {
    /**
     * A materialt azonosíto nev, a nyersanyagok osszehasonlitasahoz szukseges.
     */
    protected String name;

    /**
     * Az interakciok szamlaloja, a nyersanyagokkal torteno interakciok lebonyolítasahoz szukseges.
     */
    protected int interactCount;

    public String GetName() {
        return name;
    }

    /**
     * A metodus valositja meg, hogy mi torténik a nyersanyaggal, ha nap eri.
     * @param a az aszteroida ahol nap eri
     */
    public void Interact(Asteroid a){
        interactCount++;
    }

    /**
     * Lekeri a parameterkent kapott Material-tol a nevet. A kapott string-et osszehasonlitja a name attributummal.
     * Egyezés esetén igazzal, egyeb esetben hamissal ter vissza.
     * @param m material amivel osszehasonlitja magat
     * @return a nyersanyagok egyezese
     */
    public Boolean CompareMaterial(Material m){
        if(m.name.compareTo(name) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Beallitja az interactCount erteket a parameterkent kapott szamra.
     * @param i- az ertek amivel noveli az interactcountot
     */
    public void SetInteractCount(int i){
        interactCount = i;
    }

    /**
     * Visszaadja az interactcount értékét
     * @return az interactcount
     */
    public int GetInteractCount(){
        return interactCount;
    }

    /**
     * A nyersanyag megsemmisuleset a kontrollernek jelzo absztrakt metodus.
     */
    public abstract void Disintegrate();
}
