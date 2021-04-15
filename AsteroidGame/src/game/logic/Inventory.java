package game.logic;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Material> materials;
    private ArrayList<Teleport> teleports;

    public Inventory() {
        materials = new ArrayList<Material>();
        teleports = new ArrayList<Teleport>();
    }

    /*
    Material felvétele az inventory-ba.
     */
    public void AddMaterial(Material m) {
        materials.add(m);
    }

    /*
    Material eltávolítása az inventory-ba.
     */
    public void RemoveMaterial(Material m) {
        materials.remove(m);
    }

    /*
    Teleport felvétele az inventory-ba.
     */
    public void AddTeleport(Teleport t) {
        t.SetInventory(this);
        teleports.add(t);
    }

    /*
    Teleport eltávolítása az inventory-ból.
     */
    public void RemoveTeleport(Teleport t) {
        teleports.remove(t);
    }

    /*
    Visszaadja az inventory-ban tárolt Material-ok tömbjét.
     */
    public ArrayList<Material> GetMaterials() {
        return materials;
    }

    /*
    Visszaadja, hogy tele van-e az inventory-ban a nyersanyagoknak szánt hely.
     */
    public boolean IsMaterialSlotFull() {
        boolean rt = materials.size() == 10;

        return rt;
    }

    /*
    Visszaadja, hogy van-e hely még teleportnak az inventory-ban.
     */
    public boolean IsTeleportSlotEmpty() {

        boolean rt = teleports.size() == 0;

        return  rt;
    }

    /*
    Törlődik az inventory tartalma.
     */
    public void Clear() {
        for(Material m : materials)
            m.Disintegrate();
        if((teleports.size() != 0)) teleports.get(0).ExplodeWithPair();
    }
}
