package game.logic;

import java.util.ArrayList;

public class Recipe {
    public static ArrayList<Material> GetTeleportRecipe() {
        System.out.println("Recipe: GetTeleportRecipe()\n");

        Iron iron1 = new Iron();
        Iron iron2 = new Iron();
        Ice ice = new Ice();
        Uranium uran = new Uranium();

        ArrayList<Material> mold = new ArrayList<Material>();
        mold.add(iron1);
        mold.add(iron2);
        mold.add(ice);
        mold.add(uran);
        return mold;
    }

    public static ArrayList<Material> GetRobotRecipe() {
        System.out.println("Recipe: GetRobotRecipe()\n");

        Iron iron = new Iron();
        Coal coal = new Coal();
        Uranium uran = new Uranium();

        ArrayList<Material> mold = new ArrayList<Material>();
        mold.add(iron);
        mold.add(coal);
        mold.add(uran);
        return mold;
    }
}
