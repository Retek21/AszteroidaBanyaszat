package game.controller;

import game.logic.*;

import java.util.*;
import game.userinterface.*;

/**
 * Controller osztaly, mely a parancsertelmezesert,valamint azok feldolgozasaert felel
 * es a felhasznaloval valo kommunikacioert.
 */
public class Controller {

///////////////////////////ATTRIBUTES////////////////////////////////////////

    /**
     * Az aszteroidakat tarolo hashmap.
     */
    private LinkedHashMap<String, Asteroid> asteroids;

    /**
     * A telepeseket tarolo hashmap.
     */
    private LinkedHashMap<String, Settler> settlers;

    /**
     * A robotokat tarolo hashmap.
     */
    private LinkedHashMap<String, Robot> robots;

    /**
     * Az ufokat tarolo hashmap.
     */
    private LinkedHashMap<String, Ufo> ufos;

    /**
     * A teleportokat tarolo hashmap.
     */
    private LinkedHashMap<String, Teleport> teleports;

    /**
     * A vasakat tarolo hashmap.
     */
    private LinkedHashMap<String, Iron> iron;

    /**
     * A szeneket tarolo hashmap.
     */
    private LinkedHashMap<String, Coal> coal;

    /**
     * A jegeket tarolo hashmap.
     */
    private LinkedHashMap<String, Ice> ice;

    /**
     * Az uraniumot tarolo hashmap.
     */
    private LinkedHashMap<String, Uranium> uran;

    /**
     * Az aszteroidaov, ami az aszteroidakat tartalmazza.
     */
    private Asteroidfield asteroidfield;

    /**
     * A jatekban szereplo nap.
     */
    private Sun sun;

    /**
     * A kimeneti stringeket tarolo tomb.
     */
    private ArrayList<String> output = new ArrayList<String>();

    /**
     * A kontroller peldanya, singleton osztaly miatt.
     */
    private static Controller instance;


    /**
     * A DisplayManager peldanya
     */
    private DisplayManager dm;
    /**
     * A TextOutputManager peldanya
     */
    private TextOutputManager tm;

    /**
     * Az aktualis aktor
     */
    private String actor;
    /**
     * Az aktorok listaja
     */
    private ArrayList<Actor> actors;

    /**
     * A bool ertek, ami azt mutatja, hogy
     * az adott actor torlendo-e actors listabol.
     */
    private ArrayList<Actor> previousactors;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////CONSTRUCTORS//////////////////////////////////////////////////////////////

    /**
     * A singleton osztaly lekerdezeseert felelos metodus.
     * @return visszater az osztaly peldanyaval.
     */
    public static Controller GetInstanceOf()
    {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    /**
     * Ures konstruktor.
     */
    private Controller() {}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////SEGED METHODS//////////////////////////////////////////////////////////////

    /**
     * Kiiratasert feleos metodus. A kimeneti tombbe irja a kapott parametert.
     * @param out a kirando string.
     */
    private void WriteOut(String out) {
        if (out != null)
            output.add(out);
    }

    /**
     * A kimeneti stringet kiirja a naploba, majd nullazza a stringet
     */
    private void WriteNaplo() {
        tm.WriteToNaplo(output);
        output.clear();
    }

    /**
     * A kimeneti stringet kiirja az infopanelra, majd nullazza a stringet
     */
    private void WriteInfo() {
        tm.WriteToInfo(output);
        output.clear();
    }

    /**
     * Kiirja a panel tetejere a megfelelo cimet
     * @param title
     */
    private void WriteTitle(String title) {
        tm.WriteToHead(title);
    }

    /**
     * Az aktorokat megkereso algoritmus
     * azonosito alapjan keres, majd visszaadja a megfelelo aktort
     * @param id: azonosito
     * @return: keresett aktor
     */
    private Actor FindActor(String id) {
        for(Actor ac : actors)
            if (ac.GetID().equals(id))
                return ac;
        return null;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////SEARCHFOR METHODS/////////////////////////////////////////////////////////

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * az asteroids
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param a a keresendo asteroid.
     * @return a parameter kulcsa.
     */
    private String SearchForAsteroid(Whereabout a) {
        Iterator it = asteroids.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == a) {
                return (String)pair.getKey();
            }
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * a teleports
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param t a keresendo wehreabout.
     * @return a parameter kulcsa.
     */
    private String SearchForTeleport(Whereabout t) {
        Iterator it = teleports.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == t) {
                return (String)pair.getKey();
            }
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * a settlers
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param s a keresendo telepes.
     * @return a parameter kulcsa.
     */
    private String SearchForSettler(Entity s) {
        Iterator it = settlers.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == s) {
                return (String)pair.getKey();
            }
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * a robots
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param r a keresendo robot.
     * @return a parameter kulcsa.
     */
    private String SearchForRobot(Entity r) {
        Iterator it = robots.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == r) {
                return (String)pair.getKey();
            }
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * az ufos
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param u a keresendo ufo.
     * @return a parameter kulcsa.
     */
    private String SearchForUfo(Entity u) {
        Iterator it = ufos.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == u) {
                return (String)pair.getKey();
            }
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * a coal
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param m a keresendo szen.
     * @return a parameter kulcsa.
     */
    private String SearchForCoal(Material m) {
        Iterator it = coal.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == m) {
                return (String)pair.getKey();
            }
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * az ice
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param m a keresendo jeg.
     * @return a parameter kulcsa.
     */
    private String SearchForIce(Material m) {
        Iterator it = ice.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == m) {
                return (String)pair.getKey();
            }
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * az iron
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param m a keresendo vas.
     * @return a parameter kulcsa.
     */
    private String SearchForIron(Material m) {
        Iterator it = iron.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == m) {
                return (String)pair.getKey();
            }
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * au uran
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param m a keresendo uranium.
     * @return a parameter kulcsa.
     */
    private String SearchForUranium(Material m) {
        Iterator it = uran.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == m) {
                return (String)pair.getKey();
            }
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot
     * az asteroids as a teleports
     * mapben, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param w a keresendo whereabout.
     * @return a parameter kulcsa.
     */
    private String SearchForWhereabout(Whereabout w) {
        Iterator it = asteroids.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == w)
                return (String)pair.getKey();
        }
        it = teleports.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == w)
                return (String)pair.getKey();
        }
        return null;
    }

    /**
     * Metodus, mely megekeresi a parameterul adott objektumot a
     * SearchForIron(m),SearchForIce(m),SearchForCoal(m),SearchForUranium(m)
     * metodusokkal, es ha megtalalja azt, a kulcsat visszaadja.
     * Egyebkent null-al ter vissza.
     * @param m a keresendo nyersanyag.
     * @return a parameter kulcsa.
     */
    private String SearchForMaterial(Material m) {
        String id;

        id = SearchForIron(m);
        if (id != null) return id;

        id = SearchForIce(m);
        if (id != null) return id;

        id = SearchForCoal(m);
        if (id != null) return id;

        id = SearchForUranium(m);
        if (id != null) return id;

        return null;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////INIT PHASE///////////////////////////////////////////////////////

    /**
     * letrehoz minden objektumhoz egy uj, ures hashmapet,
     * benne az azonositokkal es az objektummal
     */
    public void PreInit() {
        asteroids = new LinkedHashMap<String, Asteroid>();
        settlers = new LinkedHashMap<String, Settler>();
        robots = new LinkedHashMap<String, Robot>();
        ufos = new LinkedHashMap<String, Ufo>();
        teleports = new LinkedHashMap<String, Teleport>();
        iron = new LinkedHashMap<String, Iron>();
        coal = new LinkedHashMap<String, Coal>();
        ice = new LinkedHashMap<String, Ice>();
        uran = new LinkedHashMap<String, Uranium>();
        actors = new ArrayList<Actor>();
        previousactors = new ArrayList<Actor>();
    }

    /**
     * A jatek inicializalasa, entitasok, aszteroidak adatainak beallitasa a tetszoleges
     * kezdo pozicioba, kezdo ertekekkel
     * @param players
     */
    public void Init(int players) {
        dm = DisplayManager.GetInstance();
        tm = TextOutputManager.GetInstanceOf();
        Random r = new Random();

//SUN/ASTEROIDFIELD
        asteroidfield = new Asteroidfield();
        sun = new Sun();
        actors.add(new Actor("_sun", State.AIROUND, "Round of Sun"));
        actors.add(new Actor("_asteroidfield", State.AIROUND, "Round of Asteroidfield"));

//ASTEROIDS
        Asteroid[] _asteroids = new Asteroid[36];
        for(int i = 0; i < 36; i++) {
            String id = "a" + i;
            Asteroid a = CreateAsteroid(id);
            _asteroids[i]=a;
        }

        Object[] ids = asteroids.keySet().toArray();
        CreateNeighbourhoods(ids);
        dm.CreateAsteroidfieldDisplay(_asteroids);

//SETTLERS
       for(int i = 0; i < players; i++) {
            String id = "s" + i;
            Settler s = CreateSettler(id);

//Teszteleshez kezdokitek a settlereknek.
//Ha kivesszuk innen a kommentet, akkor minden telepesnek az inventory-jaban kezdeskor
//lesz egy teleportpar, illetve egy teleportpar es egy robot craftolasahoz
//szukseges nyersanyag.
        /*  Teleport t = CreateTeleport("t" + i);
            s.GetInventory().AddTeleport(t);
            Teleport tt = CreateTeleport("t" + i+60);
            s.GetInventory().AddTeleport(tt);
            t.SetPair(tt);
            tt.SetPair(t);

            s.GetInventory().AddMaterial(CreateIron("irkit" + i + "_" +id));
            s.GetInventory().AddMaterial(CreateIron("irkit1" + i + "_" +id));
            s.GetInventory().AddMaterial(CreateIron("irkit2" + i + "_" +id));
            s.GetInventory().AddMaterial(CreateUranium("urkit" + i + "_" +id));
            s.GetInventory().AddMaterial(CreateUranium("urkit1" + i + "_" +id));
            s.GetInventory().AddMaterial(CreateIce("ikit" + i + "_" +id));
            s.GetInventory().AddMaterial(CreateCoal("ckit" + i + "_" +id));*/

            String aid = (String)ids[r.nextInt(ids.length)];
            AddEntity(id, aid);

            actors.add(new Actor(id, State.SETTLERROUND, "Round of Settler: " + id));

            dm.CreateSettlerDisplay(s);
       }


//UFOS
        for(int i = 0; i < players; i++) {
            String id = "u" + i;
            Ufo u = CreateUfo(id);

            String aid = (String)ids[r.nextInt(ids.length)];
            AddEntity(id, aid);

            actors.add(new Actor(id, State.AIROUND, "Round of Ufo: " + id));

            dm.CreateUfoDisplay(u);
        }
//ROBOTS
        for(int i = 0; i < 3; i++) {
            String id = "r" + i;
            Robot ro = CreateRobot(id);

            String aid = (String)ids[r.nextInt(ids.length)];
            AddEntity(id, aid);

            actors.add(new Actor(id, State.AIROUND, "Round of Robot: " + id));

            dm.CreateRobotDisplay(ro);
        }

//MATERIALS
        for(int i = 0; i < 7; i++) {
            String id = "ur" + i;
            Uranium u = CreateUranium(id);
            String aid = (String)ids[r.nextInt(ids.length)];
            while (!SetCore(id, aid))
                aid = (String)ids[r.nextInt(ids.length)];
        }
        for(int i = 0; i < 7; i++) {
            String id = "c" + i;
            Coal c = CreateCoal(id);
            String aid = (String)ids[r.nextInt(ids.length)];
            while (!SetCore(id, aid))
                aid = (String)ids[r.nextInt(ids.length)];
        }
        for(int i = 0; i < 8; i++) {
            String id = "i" + i;
            Ice ic = CreateIce(id);
            String aid = (String)ids[r.nextInt(ids.length)];
            while (!SetCore(id, aid))
                aid = (String)ids[r.nextInt(ids.length)];
        }
        for(int i = 0; i < 8; i++) {
            String id = "ir" + i;
            Iron ir = CreateIron(id);
            String aid = (String)ids[r.nextInt(ids.length)];
            while (!SetCore(id, aid))
                aid = (String)ids[r.nextInt(ids.length)];
        }

//LAYERS, SUNNEARNESS
        for(int i = 0; i < ids.length; i++) {
            String aid = (String)ids[i];
            SetLayers(aid, r.nextInt(4) + 1);
        }
        FirstRound();
    }

    /**
     * Egy aszteroida letrehozasa a megfelelo azonositoval,
     * majd felvetele a nyilvantartasba
     * @param id
     * @return
     */
    private Asteroid CreateAsteroid(String id) {
        Asteroid a = new Asteroid();
        asteroidfield.AddAsteroid(a);
        asteroids.put(id, a);
        return a;
    }
    /**
     * Egy teleport letrehozasa a megfelelo azonositoval,
     * majd felvetele a nyilvantartasba
     * @param id
     * @return
     */
    private Teleport CreateTeleport(String id) {
        Teleport t = new Teleport();
        teleports.put(id, t);
        return t;
    }

    /**
     * Egy telepes letrehozasa a megfelelo azonositoval,
     * majd felvetele a nyilvantartasba
     * @param id
     * @return
     */
    private Settler CreateSettler(String id) {
        Settler s = new Settler();
        settlers.put(id, s);
        return s;
    }
    /**
     * Egy robot letrehozasa a megfelelo azonositoval,
     * majd felvetele a nyilvantartasba
     * @param id
     * @return
     */
    private Robot CreateRobot(String id) {
        Robot r = new Robot();
        robots.put(id, r);
        return r;
    }

    /**
     * Egy ufo letrehozasa a megfelelo azonositoval,
     * majd felvetele a nyilvantartasba
     * @param id
     * @return
     */
    private Ufo CreateUfo(String id) {
        Ufo u = new Ufo();
        ufos.put(id, u);
        return u;
    }

    /**
     * Egy vas letrehozasa a megfelelo azonositoval,
     * majd felvetele a nyilvantartasba
     * @param id
     * @return
     */
    private Iron CreateIron(String id) {
        Iron i = new Iron();
        iron.put(id, i);
        return i;
    }

    /**
     * Egy jeg letrehozasa a megfelelo azonositoval,
     * majd felvetele a nyilvantartasba
     * @param id
     * @return
     */
    private Ice CreateIce(String id) {
        Ice i = new Ice();
        ice.put(id, i);
        return i;
    }

    /**
     * Egy szen letrehozasa a megfelelo azonositoval,
     * majd felvetele a nyilvantartasba
     * @param id
     * @return
     */
    private Coal CreateCoal(String id) {
        Coal c = new Coal();
        coal.put(id, c);
        return c;
    }

    /**
     * Egy uranium letrehozasa a megfelelo azonositoval,
     * majd felvetele a nyilvantartasba
     * @param id
     * @return
     */
    private Uranium CreateUranium(String id) {
        Uranium u = new Uranium();
        uran.put(id, u);
        return u;
    }


    /**
     * Beallitja a kapott azonositoju aszteroidanak a napkozelseget
     * a kapott logikai ertekre
     * @param id: az aszteroida azonositoja
     * @param value: a kapott logikai ertek
     */
    private void SetSunnearness(String id, boolean value)
    {
        Asteroid a = asteroids.get(id);
        a.SetSunnearnessInit(value);
    }

    /**
     *  Bellaitja a kapott azonositoju aszteroidanak a magjat
     *  a kapott azonositoju nyersanyagra, es visszater a sikeresseggel
     * @param materialid: a nyersanyag azonositoja
     * @param asteroidid: az aszteroida azonositoja
     * @return: a sikeresseg
     */
    private boolean SetCore(String materialid, String asteroidid)
    {
        Asteroid a = asteroids.get(asteroidid);
        if (!a.IsEmpty()) return false;

        if(iron.containsKey(materialid)) {
            a.SetCore(iron.get(materialid));
            return true;
        }
        else if(ice.containsKey(materialid)) {
            a.SetCore(ice.get(materialid));
            return true;
        }
        else if(coal.containsKey(materialid)) {
            a.SetCore(coal.get(materialid));
            return true;
        }
        else if(uran.containsKey(materialid)) {
            a.SetCore(uran.get(materialid));
            return true;
        }
        return false;
    }

    /**
     * Beallitja a kapott azonositoju aszteroidanak a retegszamat a kapott ertkere
     * @param asteroidid: az aszteroida azonositoja
     * @param value: a kapott ertek
     */
    private void SetLayers(String asteroidid, int value)
    {
        if(asteroids.containsKey(asteroidid)) {
            asteroids.get(asteroidid).SetLayer(value);
        }

    }

    /**
     * Beallitja a kapott ket azonositoju aszteroidat szomszedosnak
     * @param id1: az elso aszteroida azonositoja
     * @param id2: a masodik aszteroida azonositoja
     */
    private void SetNeighbourhood(String id1, String id2)
    {
        if(asteroids.containsKey(id1))
        {
            if(asteroids.containsKey(id2))
            {
                Asteroid a1 = asteroids.get(id1);
                Asteroid a2 = asteroids.get(id2);

                a1.AddNeighbour(asteroids.get(id2));
            }

        }
    }

    /**
     * Beallitja a szomszedossagi viszonyokat
     * @param ids: a kapott objektumok azonositoi
     */
    private void CreateNeighbourhoods(Object[] ids) {
        int index = 0;
        boolean[][] sectors = dm.GetAllocatedAsteroidSectors();
        int rows = dm.GetRows();
        int columns = dm.GetColumns();
        String[][] asteroidgrid = new String[rows][columns];;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (sectors[i][j]) {
                    asteroidgrid[i][j] = (String) ids[index];
                    index++;
                }
                else
                    asteroidgrid[i][j] = null;
            }
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {

                if(asteroidgrid[i][j] == null) continue;

                if (i == 0) {
                    if (j == 0) {
                        if(sectors[i][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j+1]);
                        if(sectors[i+1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j]);
                        if(sectors[i+1][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j+1]);
                    }
                    else if (j == columns-1) {
                        if(sectors[i][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j-1]);
                        if(sectors[i+1][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j-1]);
                        if(sectors[i+1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j]);
                    }
                    else {
                        if(sectors[i][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j-1]);
                        if(sectors[i][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j+1]);
                        if(sectors[i+1][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j-1]);
                        if(sectors[i+1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j]);
                        if(sectors[i+1][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j+1]);
                    }
                }

                else if (i == rows-1) {
                    if (j == 0) {
                        if(sectors[i][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j+1]);
                        if(sectors[i-1][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j+1]);
                        if(sectors[i-1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j]);
                    }
                    else if (j == columns-1) {
                        if(sectors[i][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j-1]);
                        if(sectors[i-1][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j-1]);
                        if(sectors[i-1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j]);
                    }
                    else {
                        if(sectors[i][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j-1]);
                        if(sectors[i][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j+1]);
                        if(sectors[i-1][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j-1]);
                        if(sectors[i-1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j]);
                        if(sectors[i-1][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j+1]);
                    }
                }

                else {
                    if (j == 0) {
                        if(sectors[i-1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j]);
                        if(sectors[i-1][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j+1]);
                        if(sectors[i][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j+1]);
                        if(sectors[i+1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j]);
                        if(sectors[i+1][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j+1]);
                    }
                    else if (j == columns-1) {
                        if(sectors[i-1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j]);
                        if(sectors[i-1][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j-1]);
                        if(sectors[i][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j-1]);
                        if(sectors[i+1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j]);
                        if(sectors[i+1][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j-1]);
                    }
                    else {
                        if(sectors[i-1][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j-1]);
                        if(sectors[i-1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j]);
                        if(sectors[i-1][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i-1][j+1]);
                        if(sectors[i][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j-1]);
                        if(sectors[i][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i][j+1]);
                        if(sectors[i+1][j-1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j-1]);
                        if(sectors[i+1][j]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j]);
                        if(sectors[i+1][j+1]) SetNeighbourhood(asteroidgrid[i][j], asteroidgrid[i+1][j+1]);
                    }
                }
            }
        }
    }

    /**
     * Hozzaadja a kapott azonositoju entitast a
     * kapott azonositoju aszteroidahoz
     * @param entityid: az entitas azonositoi
     * @param asteroidid: az aszteroida azonositoi
     */
    private void AddEntity(String entityid, String asteroidid)
    {
        if(asteroids.containsKey(asteroidid))
        {
            Asteroid a = asteroids.get(asteroidid);
            if(settlers.containsKey(entityid)) {
                Settler s = settlers.get(entityid);
                a.AddEntity(s);
            }
            else if(ufos.containsKey(entityid)) {
                Ufo u = ufos.get(entityid);
                a.AddEntity(u);
            }
            else if(robots.containsKey(entityid)) {
                Robot r = robots.get(entityid);
                a.AddEntity(r);
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////GAME PHASE//////////////////////////////////////////////////////

    /**
     * Az elso kort elkezdo metodus, beallitja a cimet, a kezdo aktort,
     * es az asktualis statuszt
     */
    public void FirstRound(){
        actors.sort(new ActorComparator());
        previousactors.addAll(actors);
        Actor ac = actors.get(0);
        actor = ac.GetID();
        settlers.get(actor).GetDisplay().SetRoundoutline(true);
        String title = ac.GetTitle();
        WriteTitle(title);
        InputManager.GetInstanceOf().SetState(ac.GetState());
    }

    /**
     * A kovetkezo kort menedzseli, ide tartozik a naplo- es infopanelre valo iras,
     * a jatek elvesztesenek ellenorzese, a kovetkezo aktor beallitasa, es a statusz
     * frissitese
     */
    public void NextRound() {
        WriteNaplo();
        CheckVictory(actor);
        CheckDefeat();

        Actor ac = null;
        actors.sort(new ActorComparator());

        boolean found = false;
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).GetID().equals(actor)) {
                if (i != actors.size() - 1) {
                    ac = actors.get(i + 1);
                } else {
                    ac = actors.get(0);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            for (int i = 0; i < previousactors.size(); i++) {
                if (previousactors.get(i).GetID().equals(actor)) {
                    for(int j = i; j < previousactors.size(); j++)
                        if (actors.contains(previousactors.get(j))) {
                            ac = previousactors.get(j);
                            break;
                        }
                    break;
                }
            }
        }

        if (ac != null) {
            actor = ac.GetID();

            Display d = null;
            if (settlers.containsKey(actor))
                d = settlers.get(actor).GetDisplay();
            else if (robots.containsKey(actor))
                d = robots.get(actor).GetDisplay();
            else if (ufos.containsKey(actor))
                d = ufos.get(actor).GetDisplay();
            else if (teleports.containsKey(actor))
                d = teleports.get(actor).GetDisplay();
            dm.ManageRoundOutlines(d);

            String title = ac.GetTitle();
            WriteTitle(title);
            InputManager.GetInstanceOf().SetState(ac.GetState());

            previousactors.clear();
            previousactors.addAll(actors);
        }
    }

////////////////DOPHASE///////////////

    /**
     * Az aktualis aktorhoz tartozo objektum leptetese
     * Megkerese, hogy az aktor milyen objektum, majd tovabbhivja
     * annak a DoPhase() metodusat
     */
    public void DoPhase() {
        if (robots.containsKey(actor))
            RobotDoPhase(actor);
        else if (ufos.containsKey(actor))
            UfoDoPhase(actor);
        else if (teleports.containsKey(actor))
            TeleportDoPhase(actor);
        else if (actor.equals("_sun"))
            SunStorm();
        else if (actor.equals("_asteroidfield"))
            Rearrange();
        NextRound();
    }

    /**
     * A metodus egy robot koret kezeli le.
     * Ha van meg reteg a robot aszteroidajan, furatja azt, ha nincs,
     * akkor tovabblepteti az egyik szomszedos aszteroidara.
     * @param id - A robotot azonosito id
     */
    private void RobotDoPhase(String id)
    {
        if(robots.containsKey(id))
        {
            Robot r = robots.get(id);
            Asteroid a = r.GetAsteroid();
            if(a.GetLayer() > 0)
                RobotDrill(id);
            else if(a.GetNumberOfNeighbours() > 0)
            {
                Random rand = new Random();
                Whereabout neighbour = a.GetNeighbour(rand.nextInt(a.GetNumberOfNeighbours()));
                String where_id = SearchForWhereabout(neighbour);
                if (where_id != null)
                    RobotMove(id, where_id);
            }
        }
    }

    /**
     * A metodus egy ufo koret kezeli le.
     * Ha van meg reteg az aszteroidan vagy ures, tovabblepteti egy szomszedos aszteroidara,
     * egyeb esetben banyasztatja
     * @param id - Az ufot azonosito id
     */
    private void UfoDoPhase(String id)
    {
        if(ufos.containsKey(id))
        {
            Ufo u = ufos.get(id);
            Asteroid a = u.GetAsteroid();
            if((a.GetLayer() > 0 || a.IsEmpty()) && a.GetNumberOfNeighbours() > 0)
            {
                Random rand = new Random();
                Whereabout neighbour = a.GetNeighbour(rand.nextInt(a.GetNumberOfNeighbours()));
                String where_id = SearchForWhereabout(neighbour);
                if (where_id != null)
                    UfoMove(id, where_id);
            }
            else UfoMine(id);
        }
    }

    /**
     * A metodus egy teleport koret kezeli le,
     * A teleport veletlenszeruen atlep egy szomszedos aszteroidara.
     * @param id - A teleportot azonosito id
     */
    private void TeleportDoPhase(String id)
    {
        if(teleports.containsKey(id))
        {
            Teleport t = teleports.get(id);
            Asteroid a = t.GetAsteroid();
            if(a.GetNumberOfNeighbours() > 0)
            {
                String where_id = null;
                Random rand = new Random();
                while (where_id == null || where_id.equals(id)) {
                    Whereabout neighbour = a.GetNeighbour(rand.nextInt(a.GetNumberOfNeighbours()));
                    where_id = SearchForWhereabout(neighbour);
                }
                TeleportMove(id, where_id);
            }
        }
    }

////////////////DRILL//////////////////////

    /**
     * Akkor hivodik, amikor egy telepes megfur egy aszteroidat.
     * Ha ez sikeres volt, kiirja a naplopanelre a cselekvest, majd
     * tovabblepteti a jatekot
     */
    public void SettlerDrill()
    {
        String out = null;

        if(settlers.containsKey(actor))
        {
            Asteroid a = settlers.get(actor).GetAsteroid();
            String asteroid_id = SearchForAsteroid(a);
            boolean success = settlers.get(actor).Drill();
            if (!success)
                out = "Settler: " + actor + " failed to drill.";
            else {
                if (asteroid_id != null)
                    out = "Settler: " + actor + " drilled Asteroid: " + asteroid_id + ".";
            }
        }
        WriteOut(out);
        NextRound();
    }

    /**
     * A robotot furatja a metodus
     * A meghatarozott kimenetet megjeleniti.
     * @param id - A robotot azonosito id
     */
    private void RobotDrill(String id) {
        String out = null;

        if(robots.containsKey(id))
        {
            Asteroid a = robots.get(id).GetAsteroid();
            String asteroid_id = SearchForAsteroid(a);
            boolean success = robots.get(id).Drill();
            if (!success)
                out = "Robot: " + id + " failed to drill.";
            else {
                if (asteroid_id != null)
                    out = "Robot: " + id + " drilled Asteroid: " + asteroid_id + ".";
            }
        }
        WriteOut(out);
    }

////////////////MINE///////////////////////////////

    /**
     * Akkor hivodik, amikor egy telepes kibanyaszik egy nyersanyagot.
     * Ha ez sikeresen megtortent, kiirja a naplopanelre a cselekvest, majd
     * tovabblepteti a jatekot
     */
    public void SettlerMine()
    {
        String out = null;

        if(settlers.containsKey(actor))
        {
            Asteroid a = settlers.get(actor).GetAsteroid();
            String asteroid_id = SearchForAsteroid(a);

            Material m = a.GetMaterial();
            String material_id = SearchForMaterial(m);
            boolean success = settlers.get(actor).Mine();
            if (!success)
                out = "Settler: " + actor + " failed to mine.";
            else if (asteroid_id != null && material_id != null)
                out = "Settler: " + actor + " mined Asteroid: " + asteroid_id + " with " + m.GetName() + ": " + material_id + ".";
        }
        WriteOut(out);
        NextRound();
    }

    /**
     * A ufot banyaszatja a metodus.
     * A meghatarozott kimenetet megjeleniti.
     * @param id - Az ufot azonosito id
     */
    private void UfoMine(String id) {
        String out = null;

        if(ufos.containsKey(id))
        {
            Asteroid a = ufos.get(id).GetAsteroid();
            String asteroid_id = SearchForAsteroid(a);

            Material m = a.GetMaterial();
            String material_id = SearchForMaterial(m);
            boolean success = ufos.get(id).Mine();
            if (!success)
                out = "Ufo: " + id + " failed to mine.";
            else if (asteroid_id != null && material_id != null)
                out = "Ufo: " + id + " mined Asteroid: " + asteroid_id + " with " + m.GetName() + ": " + material_id + ".";
        }
        WriteOut(out);
    }

////////////////////CRAFT////////////////////////////

    /**
     * Akkor hivodik, amikor egy telepes craftolni szeretne egy objektumot.
     * A kapott string alapjan eldonti, hogy ez robot, vagy teleportkapu, majd
     * meghivja az ennek megfelelo craft metodust
     * Ezek utan tovabblepteti a jatekot.
     * @param thing: a craftolando objektum
     */
    public void SettlerCraft(String thing)
    {
        String out = null;
        if(settlers.containsKey(actor)) {
            if (thing.equals("Robot")) {
                boolean success = settlers.get(actor).CraftRobot();

                if (!success)
                    out = "Settler: " + actor + " failed to craft.";
                else {
                    out = "Settler: " + actor + " crafted ";
                    out = out + output.get(output.size() - 1);
                    output.remove(output.size() - 1);
                }
            }
            if (thing.equals("Teleport")) {
                boolean success = settlers.get(actor).CraftTeleport();

                if (!success)
                    out = "Settler: " + actor + " failed to craft.";
                else {
                    out = "Settler: " + actor + " crafted ";
                    out = out + output.get(output.size() - 1);
                    output.remove(output.size() - 1);
                }
            }
        }
        else
            out = "Settler: " + actor + " failed to craft.";
        WriteOut(out);
        NextRound();
    }

//////////////////////PLACE///////////////////////////////

    /**
     * Akkor hivodik, amikor a telepes le szeretne helyezni egy objektumot.
     * A kapott string alapjan eldonti, hogy mi ez az objektum, es tovabbhivja
     * az ennek megfelelo metodust
     * @param thing: a lehelyezendo objektum
     */
    public void SettlerPlace(String thing)
    {
        String out = null;

        if(settlers.containsKey(actor)) {
            Settler s = settlers.get(actor);
            boolean success = false;

            Asteroid a = settlers.get(actor).GetAsteroid();
            String asteroid_id = SearchForAsteroid(a);

            if (thing.equals("Teleport")) {
                ArrayList<Teleport> tps = s.GetInventory().GetTeleports();
                if (tps.isEmpty()) { }
                else {
                    boolean nopair = false;
                    Teleport t = null;
                    for (Teleport tt : tps) {
                        if (tt.GetPairReadiness()) {
                            nopair = true;
                            t = tt;
                        }
                    }
                    if (!nopair) {
                        t = tps.get(0);
                    }
                    String tid = SearchForTeleport(t);
                    success = settlers.get(actor).PlaceTeleport(teleports.get(tid));
                    if (success) {
                        out = "Settler: " + actor + " placed Teleport: " + tid + " at Asteroid: " + asteroid_id + ".";
                        dm.CreateTeleportDisplay(t);
                    }
                }
            }
            else {
                ArrayList<Material> mats = s.GetInventory().GetMaterials();
                Material m;
                switch (thing) {
                    case "Uranium":
                        m = new Uranium();
                        break;
                    case "Iron":
                        m = new Iron();
                        break;
                    case "Ice":
                        m = new Ice();
                        break;
                    case "Coal":
                        m = new Coal();
                        break;
                    default:
                        m = new Coal();
                        break;
                }
                for(Material mm : mats) {
                    if (mm.GetName().equals(m.GetName())) {
                        success = settlers.get(actor).PlaceMaterial(mm);
                        if (success) {
                            String mid = SearchForMaterial(mm);
                            out = "Settler: " + actor + " placed " + mm.GetName() + ": " + mid + " at Asteroid: " + asteroid_id + ".";
                            break;
                        }
                    }
                }
            }

            if (!success)
                out = "Settler: " + actor + " failed to place.";
        }
        WriteOut(out);
        NextRound();
    }

///////////////////MOVE//////////////////////////

    /**
     * Akkor hivodik, amikor egy telepes mozog.
     * Megkeresi a kapott parameter alapjan, hogy hova szeretne lepni, majd
     * ha ez sikerul, odaleptetni, es kiirja a naplopanelre
     * Ezutan tovabblepteti a jatekot.
     * @param wa: Az a whereabout, ahova a telepes lepni szeretne
     */
    public void SettlerMove(Whereabout wa)
    {
        String where = SearchForWhereabout(wa);
        String out = null;

        if (settlers.containsKey(actor)) {
            Whereabout w;
            Asteroid current = settlers.get(actor).GetAsteroid();
            String previous_asteroidid = SearchForAsteroid(current);

            if (asteroids.containsKey(where)) {
                w = asteroids.get(where);

                boolean success = settlers.get(actor).Move(w);
                if (!success)
                    out = "Settler: " + actor + " failed to move.";
                else
                    out = "Settler: " + actor + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + where + ".";

            } else if (teleports.containsKey(where)) {
                Teleport pair = teleports.get(where).GetPair();
                String to = "null";
                if(pair != null) {
                    Asteroid a = pair.GetAsteroid();
                    to = SearchForAsteroid(a);
                }

                w = teleports.get(where);
                boolean success = settlers.get(actor).Move(w);
                if (!success)
                    out = "Settler: " + actor + " failed to move.";
                else
                    out = "Settler: " + actor + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + to + ".";
            }
        }
        WriteOut(out);
        NextRound();
    }

    /**
     * A robotot lepteti a megadott aszteroidara/teleportra.
     * A meghatarozott kimenetet megjeleniti.
     * @param id - A robtot azonosito id
     * @param where - A mozgas celjat azonosito id
     */
    private void RobotMove(String id, String where) {
        String out = null;

        Whereabout w;
        Asteroid current = robots.get(id).GetAsteroid();
        String previous_asteroidid = SearchForAsteroid(current);

        if (robots.containsKey(id)) {
            if (asteroids.containsKey(where)) {
                w = asteroids.get(where);

                boolean success = robots.get(id).Move(w);
                if (!success)
                    out = "Robot: " + id + " failed to move.";
                else
                    out = "Robot: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + where + ".";

            } else if (teleports.containsKey(where)) {
                Teleport pair = teleports.get(where).GetPair();
                String to = "null";
                if(pair != null) {
                    Asteroid a = pair.GetAsteroid();
                    to = SearchForAsteroid(a);
                }

                w = teleports.get(where);
                boolean success = robots.get(id).Move(w);
                if (!success)
                    out = "Robot: " + id + " failed to move.";
                else
                    out = "Robot: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + to + ".";
            }
        }
        WriteOut(out);
    }

    /**
     * Az ufot lepteti a megadott aszteroidara/teleportra.
     * A meghatarozott kimenetet megjeleniti.
     * @param id - Az ufot azonosito id
     * @param where - A mozgas celjat azonosito id
     */
    private void UfoMove(String id, String where) {
        String out = null;

        Whereabout w;
        Asteroid current = ufos.get(id).GetAsteroid();
        String previous_asteroidid = SearchForAsteroid(current);

        if (ufos.containsKey(id)) {
            if (asteroids.containsKey(where)) {
                w = asteroids.get(where);

                boolean success = ufos.get(id).Move(w);
                if (!success)
                    out = "Ufo: " + id + " failed to move.";
                else
                    out = "Ufo: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + where + ".";

            } else if (teleports.containsKey(where)) {
                Teleport pair = teleports.get(where).GetPair();
                String to = "null";
                if(pair != null) {
                    Asteroid a = pair.GetAsteroid();
                    to = SearchForAsteroid(a);
                }

                w = teleports.get(where);
                boolean success = ufos.get(id).Move(w);
                if (!success)
                    out = "Ufo: " + id + " failed to move.";
                else
                    out = "Ufo: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + to + ".";
            }
        }
        WriteOut(out);
    }

    /**
     * A teleportot lepteti a megadott aszteroidara/teleportra.
     * A meghatarozott kimenetet megjeleniti.
     * @param id - Az teleportot azonosito id
     * @param where - A mozgas celjat azonosito id
     */
    private void TeleportMove(String id, String where) {
        String out = null;

        Whereabout w;
        Asteroid current = teleports.get(id).GetAsteroid();
        String previous_asteroidid = SearchForAsteroid(current);

        if (teleports.containsKey(id)) {
            if (asteroids.containsKey(where)) {
                w = asteroids.get(where);

                boolean success = teleports.get(id).Move(w);
                if (!success)
                    out = "Teleports: " + id + " failed to move.";
                else
                    out = "Teleport: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + where + ".";

            } else if (teleports.containsKey(where)) {
                Teleport pair = teleports.get(where).GetPair();
                String to = "null";
                if(pair != null) {
                    Asteroid a = pair.GetAsteroid();
                    to = SearchForAsteroid(a);
                }

                w = teleports.get(where);
                boolean success = teleports.get(id).Move(w);
                if (!success)
                    out = "Teleport: " + id + " failed to move.";
                else
                    out = "Teleport: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + to + ".";
            }
        }
        WriteOut(out);
    }

///////////////////SUNSTORM/////////////////////////////

    /**
     * A Sun osztaly Sunstorm metodusat hivja meg a parameterul kapott azonositoju aszteroidara.
     * A meghatarozott kimenetet megjeleniti
     * @param asteroidid - A napvihar celjakent szolgalo aszteroida azonositoja
     */
    private void SunStorm(String asteroidid)
    {
        if(asteroids.containsKey(asteroidid))
        {
            String out = "Sunstorm hits:";
            Asteroid asteroid = asteroids.get(asteroidid);
            WriteOut(out);
            out = "\t\tAsteroid: " + SearchForAsteroid(asteroid);
            ArrayList<Whereabout> neighbours = asteroid.GetNeighbours();
            if(neighbours.size() > 0)
                out = out + ",";
            WriteOut(out);
            for(int i = 0; i < neighbours.size(); i++)
            {
                out = "\t\t";
                if(SearchForAsteroid(neighbours.get(i)) != null)
                    out = out + "Asteroid: " + SearchForAsteroid(neighbours.get(i));
                else if(SearchForTeleport(neighbours.get(i)) != null)
                    out = out + "Teleport: " + SearchForTeleport(neighbours.get(i));
                if(i+1 < neighbours.size())
                    out = out + ",";
                WriteOut(out);
            }
            sun.Sunstorm(asteroid);
        }
    }

    /**
     * A metodus kivalaszt veletlenszeruen egy aszteroidat, majd
     * erre az aszteroidra hivja meg az korabbi SunStorm(String) metodust.
     */
    private void SunStorm()
    {
        int happening = new Random().nextInt(2);
        if (happening == 0) {
            WriteOut("The Sun rests.");
            return;
        }
        ArrayList<Asteroid> tempasteroids = new ArrayList<Asteroid>();
        Iterator it = asteroids.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            tempasteroids.add((Asteroid) pair.getValue());
        }
        int rand = new Random().nextInt(tempasteroids.size());
        Asteroid asteroid = tempasteroids.get(rand);
        SunStorm(SearchForAsteroid(asteroid));
        new BlinkerThread().start();
    }

////////////////REARRANGE////////////////

    /**
     * A metodus ujrarendezi az aszteroidak napkozeliseget veletlenszeruen.
     * A meghatarozott kimenetet megjeleniti.
     */
    private void Rearrange()
    {
        String out = "Sunnearness:";
        Iterator it = asteroids.entrySet().iterator();
        Asteroid[] tempasteroids = new Asteroid[asteroids.size()];
        boolean[] nearness = new boolean[asteroids.size()];
        int cnt = 0;
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            tempasteroids[cnt] = (Asteroid) pair.getValue();
            out = "\t\tAsteroid: " + pair.getKey() + " ";
            int b = new Random().nextInt(2);
            if (b == 1)
            {
                nearness[cnt++] = true;
                out = out + "(true)";
                if (it.hasNext())
                    out = out + ",";
            }
            else
            {
                nearness[cnt++] = false;
                out = out + "(false)";
                if (it.hasNext())
                    out = out + ",";
            }
        }
        for(int i = 0; i < tempasteroids.length; i++)
        {
            tempasteroids[i].SetSunnearness(nearness[i]);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////INFO COMMANDS/////////////////////////////////////////////////////////////////

    /**
     * Egy aszteroida adatainak kirratasa az infopanelre
     * @param a: az aszteroida, amirol kellenek az adatok
     */
    public void InfoAboutAsteroid(Asteroid a) {
        String out;
        String id = SearchForAsteroid(a);
        out = "Asteroid: " + id;
        WriteOut(out);
        out = "\tSunnearness: " + String.valueOf(a.GetSunnearness());
        WriteOut(out);
        out = "\tLayers: " + Integer.toString(a.GetLayer());
        WriteOut(out);


        out = "\tCore: ";
        if(SearchForCoal(a.GetMaterial()) != null) out = out + "Coal: " + SearchForCoal(a.GetMaterial());
        else if(SearchForIron(a.GetMaterial()) != null) out = out + "Iron: " + SearchForIron(a.GetMaterial());
        else if(SearchForIce(a.GetMaterial()) != null) out = out + "Ice: " + SearchForIce(a.GetMaterial());
        else if(SearchForUranium(a.GetMaterial()) != null) out = out + "Uranium: " + SearchForUranium(a.GetMaterial());
        else out = out + "null";
        WriteOut(out);


        ArrayList<Whereabout> neighbours = a.GetNeighbours();
        out = "\tNeighbours:";
        WriteOut(out);
        if(neighbours.isEmpty())
        {
            out = "\t\tnull";
            WriteOut(out);
        }
        for (int i = 0; i < neighbours.size(); i++) {
            out = "\t\t";
            if(SearchForAsteroid(neighbours.get(i)) != null)
                out = out + "Asteroid: " + SearchForAsteroid(neighbours.get(i));
            else if(SearchForTeleport(neighbours.get(i)) != null)
                out = out + "Teleport: " + SearchForTeleport(neighbours.get(i));
            if(i+1 < neighbours.size())
                out = out + ",";
            WriteOut(out);
        }


        ArrayList<Entity> entities = a.GetEntities();
        out = "\tEntities:";
        WriteOut(out);
        if(entities.size() < 1)
        {
            out = "\t\tnull";
            WriteOut(out);
        }
        for (int i = 0; i < entities.size(); i++) {
            out = "\t\t";
            if(SearchForSettler(entities.get(i)) != null)
                out = out + "Settler: " + SearchForSettler(entities.get(i));
            else if(SearchForRobot(entities.get(i)) != null)
                out = out + "Robot: " + SearchForRobot(entities.get(i));
            else if(SearchForUfo(entities.get(i)) != null)
                out = out + "Ufo: " + SearchForUfo(entities.get(i));
            if(i+1 < entities.size())
                out = out + ",";
            WriteOut(out);
        }
        WriteInfo();
    }

    /**
     * Egy teleprot adatainak kirratasa az infopanelre
     * @param t: a teleprot, amirol kellenek az adatok
     */
    public void InfoAboutTeleport(Teleport t) {
        String out;
        String id = SearchForTeleport(t);
        out = "Teleport: " + id;
        WriteOut(out);
        if(SearchForTeleport(t.GetPair()) != null)
            out = "\tPair: " + SearchForTeleport(t.GetPair());
        else
            out = "\tPair: null";
        WriteOut(out);

        if(t.GetPair() != null && t.GetPairReadiness())
            out = "\tState: active";
        else
            out = "\tState: inactive";
        WriteOut(out);

        if(t.GetAsteroid() != null)
            out = "\tPlace: Asteroid: " + SearchForAsteroid(t.GetAsteroid());

        else {
            boolean found = false;
            Iterator it2 = settlers.entrySet().iterator();
            while (it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (((Settler) pair2.getValue()).GetInventory().GetTeleports().contains(t)) {
                    found = true;
                    out = "\tSettler: " + pair2.getKey();
                }
            }

            if (!found)
                out = "\tPlace: null";
        }
        WriteOut(out);
        WriteInfo();
    }

    /**
     * Egy telepes adatainak kirratasa az infopanelre
     * @param s: a telepes, amirol kellenek az adatok
     */
    public void InfoAboutSettler(Settler s) {
        String out;
        String id = SearchForSettler(s);
        out = "Settler: " + id;
        WriteOut(out);
        if(s.GetAsteroid() != null)
            out = "\tAsteroid: " + SearchForAsteroid(s.GetAsteroid());
        else
            out = "\tnull";
        WriteOut(out);

        out = "\tInventory:";
        WriteOut(out);

        out = "\t\tMaterials:";
        WriteOut(out);

        ArrayList<Teleport> ta = s.GetInventory().GetTeleports();
        ArrayList<Material> ma = s.GetInventory().GetMaterials();

        if(ma.size() == 0)
        {
            out = "\t\t\tnull" ;
            WriteOut(out);
        }
        for(int i = 0; i < ma.size(); i++)
        {
            if(SearchForCoal(ma.get(i)) != null) {
                out = "\t\t\tCoal: " + SearchForCoal(ma.get(i));
                WriteOut(out);
            }
            else if(SearchForIce(ma.get(i)) != null) {
                out = "\t\t\tIce: " + SearchForIce(ma.get(i));
                WriteOut(out);
            }
            else if(SearchForIron(ma.get(i)) != null) {
                out = "\t\t\tIron: " + SearchForIron(ma.get(i));
                WriteOut(out);
            }
            else if(SearchForUranium(ma.get(i)) != null) {
                out = "\t\t\tUranium: " + SearchForUranium(ma.get(i));
                WriteOut(out);
            }

        }

        out = "\t\tTeleports:";
        WriteOut(out);

        if(ta.size() == 0)
        {
            out = "\t\t\tnull" ;
            WriteOut(out);
        }
        for(int i = 0; i < ta.size(); i++) {
            if (SearchForTeleport(ta.get(i)) != null) out = "\t\t\tTeleport: " + SearchForTeleport(ta.get(i));

            WriteOut(out);
        }
        WriteInfo();
    }

    /**
     * Egy robot adatainak kirratasa az infopanelre
     * @param r: a robot, amirol kellenek az adatok
     */
    public void InfoAboutRobot(Robot r) {
        String out;
        String id = SearchForRobot(r);
        out = "Robot: " + id;
        WriteOut(out);
        if(r.GetAsteroid() != null)
            out = "\tAsteroid: " + SearchForAsteroid(r.GetAsteroid());
        else
            out = "\tnull";
        WriteOut(out);
        WriteInfo();
    }

    /**
     * Egy ufo adatainak kirratasa az infopanelre
     * @param u: az ufo, amirol kellenek az adatok
     */
    public void InfoAboutUfo(Ufo u) {
        String out;
        String id = SearchForUfo(u);
        out = "Ufo: " + id;
        WriteOut(out);
        if(u.GetAsteroid() != null)
            out = "\tAsteroid: " + SearchForAsteroid(u.GetAsteroid());
        else
            out = "\tnull";
        WriteOut(out);
        WriteInfo();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////ENDGAME//////////////////////

    /**
     * A jatek befejezeset vegzo metodus.
     * Beallitja victory flaget a gyozelemnek megfeleloen.
     * Majd vegig iteral valamennyi mapen, es a bennuk talalhato elemeknek
     * az adatait kiirja a kimenetre a meghatarozott modon.
     * @param v - Gyozelemnek megfelelp boolean ertek.
     */
    private void Endgame(boolean v, String reason)
    {
        Game.GetInstanceOf().ExitGame(v, reason);
    }

///////////////CHECK CONDITIONS//////////////////////////

    /**
     * Azt ellenorzi, hogy a telepesek megnyertek- e a jatekot.
     * Ha teljesulnek a feltetelek, meghivja az engame() metodust, true parameterrel
     * @param settlerid: a telepes azonositoja, aki segithet megnyerni a jatekot
     */
     private void CheckVictory(String settlerid){
         if (!settlers.containsKey(settlerid))
             return;
         Settler s = settlers.get(settlerid);
        ArrayList<Material> materials = new ArrayList<Material>();

        Asteroid a = s.GetAsteroid();
        ArrayList<Entity> entities = a.GetEntities();
        for (Entity e : entities) {
            Inventory i = e.GetInventory();
            if (i != null)
                materials.addAll(i.GetMaterials());
        }

        ArrayList<Material> mold = Recipe.GetWinRecipe();
        boolean win = Factory.HasEnoughMaterial(materials, mold);
        if(win) {
            String reason = "The settlers built the base!";
            Endgame(true, reason);
        }
    }

    /**
     * Megnezi, hogy szerepel-e meg eleg nyersanyag a jatekban, ahhoz, hogy a jatekosok nyerhessenek.
     * Ha igen, nem csinal semmit, ha nem, akkor lezarja a jatekot.
     */
    private void CheckDefeat(){
        int countCoal = coal.size();
        int countIce = ice.size();
        int countIron = iron.size();
        int countUranium = uran.size();

        if (countCoal >= 3 && countIce >= 3 && countIron >= 3 && countUranium >= 3) {}
        else {
            String reason = "There is not enough material to build the base anymore.";
            Endgame(false, reason);
            return;
        }
        if(settlers.isEmpty()) {
            String reason = "All the settlers has been killed.";
            Endgame(false, reason);
        }
    }

///////////////////////////EVENTS THAT CAN OCCUR DURING THE GAME///////////////////////////

    /**
     * Metodus, mely a parameterul kapott robotnak general egy egyedi azonositot,
     * es felveszi azt a robots mapbe. A output kimeneti listaba beleirja a muvelet sikeresseget.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param r a robot referenciaja
     */
    public void AddRobot(Robot r)
    {
        boolean unique = false;
        int n = 1;
        while(!unique)
        {
            if(robots.containsKey("r" + Integer.toString(n)))
                n++;
            else
                unique = true;
        }
        String id = "r" + Integer.toString(n);
        robots.put(id, r);
        actors.add(new Actor(id, State.AIROUND, "Round of Robot: " + id));
        output.add("Robot: " + id + ".");

        dm.CreateRobotDisplay(r);
    }

    /**
     * Metodus, mely a parameterul kapott teleportoknak general egy-egy egyedi azonositot,
     * es felveszi azokat a teleports mapbe. A output kimeneti listaba beleirja a muvelet sikeresseget.
     * @param t1 egyik teleport referenciaja
     * @param t2 masik teleport referenciaja
     */
    public void AddTeleport(Teleport t1, Teleport t2)
    {
        boolean unique = false;
        int n = 1;
        while(!unique)
        {
            if(teleports.containsKey("t" + Integer.toString(n)) || teleports.containsKey("t" + Integer.toString(n+1)))
                n+=2;
            else
                unique = true;
        }
        String id1 = "t" + Integer.toString(n);
        String id2 = "t" + Integer.toString(n+1);
        teleports.put(id1, t1);
        teleports.put(id2, t2);
        output.add("Teleport: " + id1 + " and Teleport: " + id2 + ".");
    }

    /**
     * Akkor hivodik, amikor a teleport megkergul.
     * Kiirja a naplopanelre az allapotat, es hozzaadja az aktorokhoz
     * @param t: a megkergult teleport
     */
    public void TeleportGoesCrazy(Teleport t) {
        String id = SearchForTeleport(t);
        if (id != null) {
            actors.add(new Actor(id, State.AIROUND, "Round of Teleport: " + id));

            String out = "Teleport: " + id + " went TOTAL CRAZY!!";
            WriteOut(out);
        }
    }

    /**
     * Metodus, mely telepes halalakor eltavolitja azt a settlers mapbol.
     * Az outputba irja a telepes halalat.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param s telepes referenciaja
     */
    public void SettlerDie(Settler s)
    {
        String id = SearchForSettler(s);
        if (id != null) {
            settlers.remove(id);
            Actor ac = FindActor(id);
            actors.remove(ac);

            String out = "Settler: " + id + " died.";
            WriteOut(out);
        }
    }

    /**
     * Metodus, mely robot halalakor eltavolitja azt a robots mapbol.
     * Az outputba irja a robot halalat.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param r robot referenciaja
     */
    public void RobotDie(Robot r)
    {
        String id = SearchForRobot(r);
        if (id != null) {
            robots.remove(id);
            Actor ac = FindActor(id);
            actors.remove(ac);

            String out = "Robot: " + id + " died.";
            WriteOut(out);
        }
    }

    /**
     * Metodus, mely ufo halalakor eltavolitja azt a ufos mapbol.
     * Az outputba irja az ufo halalat.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param u ufo referenciaja
     */
    public void UfoDie(Ufo u)
    {
        String id = SearchForUfo(u);
        if (id != null) {
            ufos.remove(id);
            Actor ac = FindActor(id);
            actors.remove(ac);

            String out = "Ufo: " + id + " died.";
            WriteOut(out);
        }
    }

    /**
     * Metodus, mely az aszteroida felrobbanasakor eltavolitja azt az asteroids mapbol.
     * Az outputba irja az aszteroida felrobbanasat.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param a aszteroida referenciaja
     */
    public void AsteroidExplode(Asteroid a)
    {
        new BlinkerThread().start();
        String id = SearchForAsteroid(a);
        if (id != null)
        {
            asteroids.remove(id);

            String out = "Asteroid: " + id + " exploded.";
            WriteOut(out);
        }
    }

    /**
     * Metodus, mely a teleport felrobbanasakor eltavolitja azt az teleports mapbol.
     * Az outputba irja az teleport felrobbanasat.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param t teleport referenciaja
     */
    public void TeleportExplode(Teleport t)
    {
        String id = SearchForTeleport(t);
        if (id != null)
        {
            teleports.remove(id);

            Actor ac = FindActor(id);
            if (ac != null)
                actors.remove(ac);


            String out = "Teleport: " + id + " exploded.";
            WriteOut(out);
        }
    }

    /**
     * Metodus, mely a szen megsemmisulesekor eltavolitja azt az coal mapbol.
     * Az outputba irja az szen megsemmisuleset.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param c szen referenciaja
     */
    public void CoalDisintegrate(Coal c)
    {
        String id = SearchForCoal(c);
        if (id != null) {
            coal.remove(id);

            String out = "Coal: " + id + " disintegrated.";
            WriteOut(out);

        }
    }

    /**
     * Metodus, mely a jeg megsemmisulesekor eltavolitja azt az ice mapbol.
     * Az outputba irja az jeg megsemmisuleset.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param i jeg referenciaja
     */
    public void IceDisintegrate(Ice i)
    {
        String id = SearchForIce(i);
        if (id != null) {
            ice.remove(id);

            String out = "Ice: " + id + " disintegrated.";
            WriteOut(out);

        }
    }

    /**
     * Metodus, mely a vas megsemmisulesekor eltavolitja azt az iron mapbol.
     * Az outputba irja az vas megsemmisuleset.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param i szen referenciaja
     */
    public void IronDisintegrate(Iron i)
    {
        String id = SearchForIron(i);
        if (id != null) {
            iron.remove(id);

            String out = "Iron: " + id + " disintegrated.";
            WriteOut(out);

        }
    }

    /**
     * Metodus, mely az uranium megsemmisulesekor eltavolitja azt az uranium mapbol.
     * Az outputba irja az uranium megsemmisuleset.
     * A konzolon ertesiti a felhasznalot a muveletrol.
     * @param u szen referenciaja
     */
    public void UraniumDisintegrate(Uranium u)
    {
        String id = SearchForUranium(u);
        if (id != null) {
            uran.remove(id);

            String out = "Uranium: " + id + " disintegrated.";
            WriteOut(out);

        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
