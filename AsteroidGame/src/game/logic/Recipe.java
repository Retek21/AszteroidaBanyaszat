package game.logic;

import java.util.ArrayList;

public class Recipe {

    /*
    Visszaadja azon nyersanyagok tömbjét, amelyek szükségesek egy teleportpár elkészítéséhez.
     */
    public static ArrayList<Material> GetTeleportRecipe() {
        Skeleton.WriteName("Recipe: GetTeleportRecipe()");
        Skeleton.tab++;

        Iron iron1 = new Iron();
        Iron iron2 = new Iron();
        Ice ice = new Ice();
        Uranium uran = new Uranium();

        ArrayList<Material> mold = new ArrayList<Material>();
        mold.add(iron1);
        mold.add(iron2);
        mold.add(ice);
        mold.add(uran);

        Skeleton.tab--;
        Skeleton.WriteName("Recipe: GetTeleportRecipe() return: mold");
        return mold;
    }

    /*
    Visszaadja azon nyersanyagok tömbjét, amelyek szükségesek egy robot elkészítéséhez.
     */
    public static ArrayList<Material> GetRobotRecipe() {
        Skeleton.WriteName("Recipe: GetRobotRecipe()");
        Skeleton.tab++;

        Iron iron = new Iron();
        Coal coal = new Coal();
        Uranium uran = new Uranium();

        ArrayList<Material> mold = new ArrayList<Material>();
        mold.add(iron);
        mold.add(coal);
        mold.add(uran);

        Skeleton.tab--;
        Skeleton.WriteName("Recipe: GetRobotRecipe() return: mold");

        return mold;
    }
}
