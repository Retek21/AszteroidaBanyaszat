package game.logic;

import java.util.ArrayList;

/**
 * Minden Settler objektum rendelkezik egy inventory-val. Az osztaly felelossége,
 * hogy az adott telepesnel levo objektumokat tarolja, rendszerezze.
 */
public class Inventory {
    /**
     * A tarolt nyersanyagok
     */
    private ArrayList<Material> materials;

    /**
     * A tarolt teleportok
     */
    private ArrayList<Teleport> teleports;

    /**
     * Letrehoz egy Inventory objektumot.
     */
    public Inventory() {
        materials = new ArrayList<Material>();
        teleports = new ArrayList<Teleport>();
    }

    /**
     * Felveszi a megadott nyersanyagot a materials kollekcioba.
     * @param m - Az inventory-ba felvevendo nyersanyag
     */
    public void AddMaterial(Material m) {
        materials.add(m);
    }

    /**
     * Eltavolitja a megadott nyersanyagot a materials kollekciobol.
     * @param m
     */
    public void RemoveMaterial(Material m) {
        materials.remove(m);
    }

    /**
     * Felveszi a megadott teleportot a teleports kollekcioba.
     * @param t - Az inventory-ba felvevendo teleport
     */
    public void AddTeleport(Teleport t) {
        t.SetInventory(this);
        teleports.add(t);
    }

    /**
     * Eltavolitja a megadott teleportot a teleports kollekciobol.
     * @param t - Az eltavolitando teleport.
     */
    public void RemoveTeleport(Teleport t) {
        teleports.remove(t);
    }

    /**
     * Visszaadja Visszater a materials kollekcioval.
     * @return - Az inventory-ban tarolt nyersanyagok tombje
     */
    public ArrayList<Material> GetMaterials() {
        return materials;
    }

    /**
     * Visszaadja Visszater a teleports kollekcioval.
     * @return - Az inventory-ban tarolt teleportok tombje
     */
    public ArrayList<Teleport> GetTeleports() {
        return teleports;
    }

    /**
     * Visszateresi ertekeben megadja, hogy “tele” van-e az inventoryban a nyersanyagoknak szant hely.
     * Ha a materials kollekcio 10 vagy annal tobb elemet tartalmaz, akkor igazzal, ha kevesebb, mint 10-et, akkor hamissal ter vissza.
     * @return - Az inventory-ban nyersanyagoknak szant hely tele van-e
     */
    public boolean IsMaterialSlotFull() {
        return materials.size() >= 10;
    }

    /**
     * Visszateresi ertekeben megadja van-e szabad hely meg egy teleportparnak az inventory-ban.
     * Ha teleports kollekcio 1 vagy annal kevesebb elemet tartalmaz, akkor igazzal, egyeb esetben hamissal ter vissza.
     * @return - Van-e szabad hely meg teleportparnak az inventory-ban.
     */
    public boolean IsTeleportSlotEmpty() {
        return teleports.size() <= 1;
    }

    /**
     * Az inventory ervenyteleniti a tartalmat.
     * A materials tomb elemeinek meghivódik a Disintegrate() metodusa, a teleports tomb elemeinek pedig az ExplodeWithPair() metodusa.
     */
    public void Clear() {

        for(int i = 0; i < materials.size(); i++)
            materials.get(i).Disintegrate();

        for(int i = 0; i < teleports.size(); i++)
        {
            int tempsize = teleports.size();
            teleports.get(i).ExplodeWithPair();
            if(teleports.size() == tempsize-1)
                i--;
            else if(teleports.size() == tempsize-2 && teleports.size() > 1)
                i-=2;
        }
    }
}
