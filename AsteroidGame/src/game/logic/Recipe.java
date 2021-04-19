package game.logic;

import java.util.ArrayList;

/**
 * Az osztalytol a craftolhato objektumok receptjei (Material-okat tartalmazo listak)
 * kerhetok le statikus metodusokon keresztul.
 */
public class Recipe {

    /**
     * Visszater egy teleportpar craftolasahoz szukseges nyersanyagok tombjevel.
     * Letrehozza a szukséges Material objektumokat (1 vas, 1 szen, 1 uran), belerakja oket egy tombbe, és visszater vele.
     * @return - Egy teleportpar craftolasahoz szukseges nyersanyagokat tartalmazo tomb.
     */
    public static ArrayList<Material> GetTeleportRecipe() {
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

    /**
     * Visszater egy robot craftolasahoz szukseges nyersanyagok tombjevel.
     * Letrehozza a szukséges Material objektumokat (2 vas, 1 jeg, 1 uran), belerakja oket egy tombbe, és visszater vele.
     * @return - Egy robot craftolasahoz szukseges nyersanyagokat tartalmazo tomb.
     */
    public static ArrayList<Material> GetRobotRecipe() {

        Iron iron = new Iron();
        Coal coal = new Coal();
        Uranium uran = new Uranium();

        ArrayList<Material> mold = new ArrayList<Material>();
        mold.add(iron);
        mold.add(coal);
        mold.add(uran);

        return mold;
    }

    /**
     * Visszater a gyozelemhez osszegyujteni szukséges Material-ok tombjével.
     * Letrehozza a szukséges Material objektumokat (3 vas, 3 szen, 3 jeg, 3 uran), belerakja oket egy tombbe, es visszater vele.
     * @return - A bazis megepitesehez (gyozelemhez) szukseges nyersanyagokat tartalmazo tomb.
     */
    public static ArrayList<Material> GetWinRecipe() {
        ArrayList<Material> mold = new ArrayList<Material>();
        for(int i = 0; i < 3; i++)
            mold.add(new Iron());
        for(int i = 0; i < 3; i++)
            mold.add(new Coal());
        for(int i = 0; i < 3; i++)
            mold.add(new Ice());
        for(int i = 0; i < 3; i++)
            mold.add(new Uranium());

        return mold;
    }
}
