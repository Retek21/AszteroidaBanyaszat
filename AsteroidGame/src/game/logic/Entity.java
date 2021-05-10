package game.logic;

import game.userinterface.Display;
import game.userinterface.EntityDisplay;

/**
 * Entity absztrakt ososztaly. Az aszteroidaovben tartozkodi entitasok
 * egyes lepeseit valositja meg, es az azokhoz tartozo belso mukodest.
 * @author Turiák Anita 2021.03.19.
 * @author Dengyel Bendeguz 2021.04.13.
 */
public abstract class Entity {

    /**
     * Az aszteroida, melyen a telepes tartozkodik.
     */
    protected Asteroid asteroid;
    /**
     * entity displaye
     * */
    protected EntityDisplay display;
    /**
     * beallitja az adott display-et
    * */
    public Display GetDisplay() {
        return display;
    }


    /**
     * Visszaadja a telepes gazdaaszteroidajat.
     * @return az aszteroida referenciaja
     */
    public Asteroid GetAsteroid() {
        return asteroid;
    }

    /**
     * Beallitja a telepes aszteroidajat az parameterul kapottra.
     * @param a a telepes uj aszteroidaja
     */
    public void SetAsteroid(Asteroid a){
        asteroid = a;
    }

    /**
     * Az entitas meghal, így lekerul az aszteroidarol.
     * Az Asteroid::RemoveEntity(Entity e) metodus eltavolitja
     * az atadott entittast magarol.
     */
    public void Die(){
        asteroid.RemoveEntity(this);
        display.MarkedToClear();
    }

    /**
     * Az entitas felrobban. A leszarmazottak valositjak meg a metodust.
     */
    public abstract void BlowUp();

    /**
     * Az entitás atlep az azsteroidajarol egy szomszedosra.
     * Ha az aszteroida szomszedai kozott megtalalhato a parameterul
     * atadott szomszed, jelen aszteroida eltavolitja magarol az entitast,
     * majd az uj aszteroida lesz az entitas asteroidja. Ekkor igazzal ter vissza.
     * Egyebkent hamissal.
     * @param w a szomszed, melyre at lep az entitas
     * @return a mozgas sikeressege
     */
    public boolean Move(Whereabout w) {
        Asteroid current = asteroid;
        if (asteroid.GetNeighbours().contains(w)) {
            if (w.AddEntity(this)) {
                current.RemoveEntity(this);
                display.Notify();
                return true;
            }
        }
        return false;
    }

    /**
     * Visszaadja az entitas inventory-jat.
     * @return - Az entitas inventory-ja
     */
    public Inventory GetInventory() {
        return null;
    }
    /**
     * beallitja az entitas display-et
     * */
    public void SetDisplay(EntityDisplay display){
        this.display = display;
    }
}