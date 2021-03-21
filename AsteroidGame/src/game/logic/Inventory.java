package game.logic;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Material> materials;
    private ArrayList<Teleport> teleports;

    public Inventory() {
        Skeleton.WriteName("Inventory: Inventory()");
        Skeleton.tab++;

        materials = new ArrayList<Material>();
        teleports = new ArrayList<Teleport>();

        Skeleton.tab--;
    }

    public void AddMaterial(Material m) {
        Skeleton.WriteName("Inventory: AddMaterial(material)");
        Skeleton.tab++;

        materials.add(m);

        Skeleton.tab--;
    }
    public void RemoveMaterial(Material m) {
        Skeleton.WriteName("Inventory: RemoveMaterial(material)");
        Skeleton.tab++;

        materials.remove(m);

        Skeleton.tab--;
    }
    public void AddTeleport(Teleport t) {
        Skeleton.WriteName("Inventory: AddTeleport(teleport)");
        Skeleton.tab++;
        t.SetInventory(this);
        teleports.add(t);

        Skeleton.tab--;
    }
    public void RemoveTeleport(Teleport t) {
        Skeleton.WriteName("Inventory: RemoveTeleport(teleport)");
        Skeleton.tab++;

        teleports.remove(t);

        Skeleton.tab--;
    }
    public ArrayList<Material> GetMaterials() {
        Skeleton.WriteName("Inventory: GetMaterials()");
        Skeleton.tab++;

        Skeleton.WriteName("Inventory: GetMaterials() return: materials");
        Skeleton.tab--;

        return materials;
    }
    public boolean IsMaterialSlotFull() {
        Skeleton.WriteName("Inventory: IsMaterialSlotFull()");
        Skeleton.tab++;

        boolean rt = materials.size() == 10;

        Skeleton.WriteName("Inventory: IsMaterialSlotFull() return: " + rt);
        Skeleton.tab--;

        return rt;
    }
    public boolean IsTeleportSlotEmpty() {
        Skeleton.WriteName("Inventory: IsTeleportSlotEmpty()");
        Skeleton.tab++;

        boolean rt = teleports.size() == 0;

        Skeleton.WriteName("Inventory: IsTeleportSlotEmpty() return: " + rt);
        Skeleton.tab--;

        return  rt;
    }
    public void Clear() {
        Skeleton.WriteName("Inventory: Clear()");
        Skeleton.tab++;

        for(Material m : materials)
            m.Disintegrate();
        if((teleports.size() != 0)) teleports.get(0).ExplodeWithPair();
        Skeleton.tab--;
    }
}
