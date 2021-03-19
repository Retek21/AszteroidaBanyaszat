package game.logic;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Material> materials;
    private ArrayList<Teleport> teleports;

    public Inventory() {
        System.out.println("Inventory()\n");

        materials = new ArrayList<Material>;
        teleports = new ArrayList<Teleport>;
    }

    public void AddMaterial(Material m) {
        System.out.println("Inventory: AddMaterial(material)\n");

        materials.add(m);
    }
    public void RemoveMaterial(Material m) {
        System.out.println("Inventory: RemoveMaterial(material)\n");

        materials.remove(m);
    }
    public void AddTeleport(Teleport t) {
        System.out.println("Inventory: AddTeleport(teleport)\n");

        teleports.add(t);
    }
    public void RemoveTeleport(Teleport t) {
        System.out.println("Inventory: RemoveTeleport(teleport)\n");

        teleports.remove(t);
    }
    public ArrayList<Material> GetMaterials() {
        System.out.println("Inventory: GetMaterials()\n");

        return materials;
    }
    public boolean IsMaterialSlotFull() {
        System.out.println("Inventory: IsMaterialSlotFull()\n");

        return materials.size() == 10;
    }
    public boolean IsTeleportSlotEmpty() {
        System.out.println("Inventory: IsTeleportSlotEmpty()\n");

        return teleports.size() == 0;
    }
    public void Clear() {
        System.out.println("Inventory: Clear()\n");

        for(Material m : materials)
            m.Disintegrate();
        if((teleports.size() != 0)) teleports.get(0).ExplodeWithPair();
    }
}
