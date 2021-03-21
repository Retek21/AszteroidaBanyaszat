package game.logic;

import java.util.ArrayList;

public class Factory {

    public Factory() {
        Skeleton.WriteName("Factory: Factory()");
    }

    public ArrayList<Teleport> CreateTeleport(Inventory i) {
        Skeleton.WriteName("Factory: CreateTeleport(inventory)");
        Skeleton.tab++;

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

            Skeleton.tab--;
            Skeleton.WriteName("Factory: CreateTeleport(inventory) return: teleports");
            return teleports;
        }
        else {
            Skeleton.tab--;
            Skeleton.WriteName("Factory: CreateTeleport(inventory) return: null");

            return null;
        }
    }

    public Robot CreateRobot(Inventory i) {
        Skeleton.WriteName("Factory: CreateRobot(inventory)");
        Skeleton.tab++;

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

            Skeleton.tab--;
            Skeleton.WriteName("Factory: CreateTeleport(inventory) return: null");

            return new Robot();
        }
        else {
            Skeleton.tab--;
            Skeleton.WriteName("Factory: CreateTeleport(inventory) return: null");

            return null;
        }
    }
}
