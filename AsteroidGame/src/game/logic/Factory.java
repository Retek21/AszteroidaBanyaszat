package game.logic;

import game.controller.Controller;
import java.util.ArrayList;

/**
 * Az osztaly kezeli a telepesek altal, nyersanyagokbol letrehozni  kivant objektumok
 * letrehozasat a Recipe osztalytol lekerheto receptek, és a telepestol kapott Inventory alapjan.
 */
public class Factory {

    /**
     * Eldonti, hogy a parameterkent kapott materials tomb tartalmazza-e
     * a parameterkent kapott mold tombben talalhato, megfelelo tipusu nyersanyagokbol megfelelo mennyiseget.
     * @param materials - Nyersanyagyujtemeny, amelyrol ki akarjuk deriteni, hogy tartalmaz-e bizonyos tipusu nyersanyagokbol megfelelo mennyiseget.
     * @param mold  - Nyersanyaggyujtemeny, amely mintakent szolgal a tartalmazaskerdes megvalaszolasahoz.
     * @return - materials tomb tartalmazza-e a megfelelo nyersanyagokat.
     */
    public static boolean HasEnoughMaterial(ArrayList<Material> materials, ArrayList<Material> mold) {
        boolean got_all = false;

        for(int j = 0; j < materials.size() && !got_all; j++) {
            for (int k = 0; k < mold.size(); k++) {
                Material mold_sample = mold.get(k);
                boolean match = mold_sample.CompareMaterial(materials.get(j));
                if (match) {
                    mold.remove(mold_sample);
                    if (mold.size() == 0) got_all = true;
                    break;
                }
            }
        }
        return got_all;
    }

    /**
     * A metodus elkesziti a teleportkapuparokat.
     * Elkeri a Recipe osztalytol a szukseges Material-ok listajat, az Inventory-tol pedig
     * a rendelkezésre allo Material-okat. Osszeveti a ket listat, ha megvan az osszes nyersanyag,
     * eltavolítja azokat az inventory-bol, letrehozza a teleportokat, és visszater a tombjukkel.
     * Ha nincsenek meg a szukseges nyersanyagok, a metodus null-lal visszater.
     * @param i - Az Inventory objektum, ahonnan a rendelkezesre allo nyersanyagok tomje elerheto.
     * @return  - Ha meg vannak a szukseges nyersanyagok, a letrehozott teleportokat tartalmazo tomb,
     *            egyeb esetben null.
     */
    public static ArrayList<Teleport> CreateTeleport(Inventory i) {
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
            Teleport t1 = new Teleport(i);
            Teleport t2 = new Teleport(i);
            t1.SetPair(t2);
            t2.SetPair(t1);
            ArrayList<Teleport> teleports = new ArrayList<Teleport>();
            teleports.add(t1);
            teleports.add(t2);

            Controller.GetInstanceOf().AddTeleport(t1, t2);

            return teleports;
        }
        else {
            return null;
        }
    }


    /**
     * A metodus elkesziti a robotot.
     * Elkeri a Recipe osztalytol a szukseges Material-ok listajat, az Inventory-tol pedig
     * a rendelkezésre allo Material-okat. Osszeveti a ket listat, ha megvan az osszes nyersanyag,
     * eltavolítja azokat az inventory-bol, letrehozza a robotot, és visszater vele.
     * Ha nincsenek meg a szukseges nyersanyagok, a metodus null-lal visszater.
     * @param i - Az Inventory objektum, ahonnan a rendelkezesre allo nyersanyagok tombje elerheto.
     * @return  - Ha meg vannak a szukseges nyersanyagok, a letrehoztt Robot objektum,
     *            egyeb esetben null.
     */
    public static Robot CreateRobot(Inventory i) {
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
            Controller.GetInstanceOf().AddRobot(robot);
            return robot;
        }
        else {
            return null;
        }
    }
}
