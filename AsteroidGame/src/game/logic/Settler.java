package game.logic;
import java.util.ArrayList;
/**
 * Settler osztaly, az Entity leszarmazotta,
 * feladata az aszteroidak kozti mozgas, azok megfurasa, banyaszata
 * kulonbozo objektumok keszitese, azok elhelyezese.
 * @author Turiak Anita 2021.03.19.
 * @author Dengyel Bendeguz 2021.04.13.
 */
public class Settler extends Entity{
    /**
     *A settler konstruktora beallitja a parameterul kapott controllert
     * az osnek, es letrehozza a factoryt, valamint az inventoryt.
     */
    public Settler(Controller c){
        super(c);
        factory=new Factory(c);
        inventory=new Inventory();
    }

    /**
     * A Factory segiti a telepest az elkesztiheto dolgok elkesziteseben.
     */
    private Factory factory;
    /**
     * Az inventoryban tarolja az egyes kibanyaszott
     * nyersanyagokat, teleportokat.
     */
    private Inventory inventory;

    /**
     * Visszater a telepes inventoryjaval.
     * @return az inventory referenciaja
     */
    public Inventory GetInventory(){return inventory;}

    /**
     * Visszater a telepes factoryjaval.
     * @return a factory referenciaja
     */
    public Factory GetFactory(){return factory;}

    /**
     * A parameterul kapott inventoryt beallitja a telepes inventoryjanak.
     * @param i a beallitani kivant inventory
     */
    public void SetInventory(Inventory i){inventory = i;}

    /**
     * A telepes banyaszik az aszteroidan. Ha az inventory-ja tele van, vagy ures aszteroidat
     * probal banyaszni, a cselekves siekrtelen, nem tortenik semmi. Egyeb esetben a nyersanyag
     * kikerul az aszteroidabol és addolodik az inventory-ba. Ekkor visszateresi erteke igaz,
     * egyebkent hamis.
     * @return a metodus sikeressege
     */
    public boolean Mine(){
        if(!inventory.IsMaterialSlotFull()){
            Material m = asteroid.RemoveMaterial();
            if (m != null){
                inventory.AddMaterial(m);
                return true;
            }else
                return false;

        }else
            return false;
    }

    /**
     * A telepes megfur egy aszteroidat.
     * Visszateresi erteke a furas sikeressegenek erteke.
     * @return a metodus sikeressege
     */
    public boolean Drill(){
        return asteroid.ThinLayer();
    }

    /**
     * A telepes lehelyezi a parameterul kapott t teleportot az aszteroidaja szomszedsagaba.
     * Meghivodik a teleport Deploy() metódusa, ha az megtalalhato a az inventory
     * teleportjai kozott, majd eltavolitja a teleportot az invventorybol.
     * Sikeres lehelyezes eseten igazzal ter vissza, egyebkent hamissal.
     * @param t a lehelyezni kivant teleport
     * @return a lehelyezes sikeressege
     */
    public boolean PlaceTeleport(Teleport t){
        if(!inventory.GetTeleports().contains(t))
            return false;
        else{
            if(t.Deploy(asteroid)){
                inventory.RemoveTeleport(t);
                return true;
            }else
                return false;
        }
    }

    /**
     * A telepes lehelyez egy nyersanyagot az aszteroida magjaba, ha a
     * parameterul atadott m nyersanyag megtalalhato az inventoryban.
     * Ha az asteroid Asteroid::AddMaterial(Material m) meetodusa
     * sikeresen az asteroidhoz adja a nyersanyagot, akkor a nyersanyag
     * kikerul a telepes inventoryjabol az Inventory::RemoveMaterial(Material m)
     * metodussal. Ekkor a visszateres igaz, egyebkent hamis.
     * @param m a lehelyezni kivant nyersanyag
     * @return a lehelyezes sikeressege
     */
    public boolean PlaceMaterial(Material m){
        if(!inventory.GetMaterials().contains(m))
            return false;
        else{
            if(asteroid.AddMaterial(m)){
                inventory.RemoveMaterial(m);
                return true;
            }else
                return false;
        }
    }

    /**
     * A telepes meghal. Torlodik az inventory-ja és meghivodik az
     * ososztaly Die() metodusa, majd az inventory tartalma is megsemmisul az
     * Inventory::Clear() metodussal. Vegul a controller
     * Controller::SettlerDie(Settler s) metodussal a fazisokbol is kikerul.
     */
    @Override
    public void Die(){
        super.Die();
        inventory.Clear();
        c.SettlerDie(this);
    }

    /**
     * A telepes felrobban aszteroida robbanas kovetkezteben, ennek kovetkezteben
     * meghal, meghivja a Die() metodusat.
     */
    @Override
    public void BlowUp(){
        Die();
    }

    /**
     * A telepes elkeszit egy teleportkapu-part.
     * Ha van hely az inventoryjaban meghivodik a Factory::CreateTeleport(Inventory i)
     * metodusa, ami visszaad egy teleportpart, ha sikeres a craftolas. Ebben az esetben a teleportok
     * bekerulnek az inventoryba. A metodus igazzal ter vissza.
     * Ha nincs hely az inventoryban tobb teleportnak, vagy a CreateTeleport null-lal ter vissza
     * (sikeretelen craftoles) nem tertenik semmi. Ekkor a metodus hamissal ter vissza.
      * @return a craftolas sikeressege
     */
    public boolean CraftTeleport(){
        if(inventory.IsTeleportSlotEmpty()){
            ArrayList<Teleport> teleports = factory.CreateTeleport(inventory);
            if(teleports!=null){
                inventory.AddTeleport(teleports.get(0));
                inventory.AddTeleport(teleports.get(1));
                return true;
            }else
                return false;
        }else
            return false;
    }

    /**
     * A telepes elkeszit egy robotot.
     * Meghivodik a Factory::CreateRobot(Inventory i) metodusa, ami
     * visszaad egy robotot, ha sikeres a craftolas. Ebben az esetben a robot
     * lekerul arra az aszteroidara, amelyen a telepes is tartazkodik. Ekkor
     * a metodus igazzal ter vissza.
     * Ha a CreateRobot null-lal ter vissza (sikeretelen craftolás) nem
     * történik semmi. Ekkor  a metodus hamissal ter vissza.
     * @return a craftolas sikeressege
     */
    public boolean CraftRobot() {
        Robot robot = factory.CreateRobot(inventory);
        if (robot != null) {
            asteroid.AddEntity(robot);
            return true;
        }else
            return false;
    }
}
