package game.logic;

import java.util.ArrayList;

public class Factory {

    public Factory() {

    }

    /*
    A metódus elkészíti a teleportkapupárokat.
    Elkéri a Recipe osztálytól a szükséges Material-ok listáját, az Inventory-tól pedig
    a rendelkezésre álló Material-okat. Összeveti őket, ha megvan az összes nyersanyag,
    eltávolítja azokat az inventory-ból, létrehozza a teleportokat, és visszatér a tömbjükkel.
    Ha nincsenek meg a szükséges nyersanyagok, a metódus null-lal visszatér.
     */
    public ArrayList<Teleport> CreateTeleport(Inventory i) {
        ArrayList<Material> mold = Recipe.GetTeleportRecipe();
        ArrayList<Material> materials = i.GetMaterials();

        boolean got_all = false;
        ArrayList<Material> used = new ArrayList<Material>();

        for(int j = 0; j < materials.size() && !got_all; j++) {
            for(int k = 0; k < mold.size(); k++) {
                Material mold_sample = mold.get(k);
                boolean match = mold_sample.CompareMaterial(materials.get(j));
                if (match) {
                    used.add(materials.get(j));
                    mold.remove(mold_sample);
                    if (mold.size() == 0) got_all = true;
                    break;
                }
            }
        }
        if (got_all) {
            for(Material m : used) {
                i.RemoveMaterial(m);
                m.Disintegrate();
            }
            Teleport t1 = new Teleport();
            Teleport t2 = new Teleport();
            t1.SetPair(t2);
            t2.SetPair(t1);
            ArrayList<Teleport> teleports = new ArrayList<Teleport>();
            teleports.add(t1);
            teleports.add(t2);

            return teleports;
        }
        else {
            return null;
        }
    }

    /*
    A metódus elkészíti a robotokat.
    Elkéri a Recipe osztálytól a szükséges Material-ok listáját, az Inventory-tól pedig
    a rendelkezésre álló Material-okat. Összeveti őket, ha megvan az összes nyersanyag,
    eltávolítja azokat az inventory-ból, létrehozza a robotot, és visszatér a tömbjükkel.
    Ha nincsenek meg a szükséges nyersanyagok, a metódus null-lal visszatér.
     */
    public Robot CreateRobot(Inventory i) {
        ArrayList<Material> mold = Recipe.GetRobotRecipe();
        ArrayList<Material> materials = i.GetMaterials();

        boolean got_all = false;
        ArrayList<Material> used = new ArrayList<Material>();

        for(int j = 0; j < materials.size() && !got_all; j++) {
            for(int k = 0; k < mold.size(); k++) {
                Material mold_sample = mold.get(k);
                boolean match = mold_sample.CompareMaterial(materials.get(j));
                if (match) {
                    used.add(materials.get(j));
                    mold.remove(mold_sample);
                    if (mold.size() == 0) got_all = true;
                    break;
                }
            }
        }
        if (got_all) {
            for(Material m : used) {
                i.RemoveMaterial(m);
                m.Disintegrate();
            }

            Robot robot = new Robot();

            return robot;
        }
        else {
            return null;
        }
    }
}
