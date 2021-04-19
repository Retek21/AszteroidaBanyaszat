package game.controller;

import game.logic.*;
import java.io.*;
import java.util.*;

/**
 * Controller osztaly, mely a parancsertelmezesert,valamint azok feldolgozasaert felel
 * es a felhasznaloval valo kommunikacioert.
 */
public class Controller {

///////////////////////////ATTRIBUTES////////////////////////////////////////

    /**
     * Az aszteroidakat tarolo hashmap.
     */
    private LinkedHashMap<String, Asteroid> asteroids = new LinkedHashMap<String, Asteroid>();

    /**
     * A telepeseket tarolo hashmap.
     */
    private LinkedHashMap<String, Settler> settlers = new LinkedHashMap<String, Settler>();

    /**
     * A robotokat tarolo hashmap.
     */
    private LinkedHashMap<String, Robot> robots = new LinkedHashMap<String, Robot>();

    /**
     * Az ufokat tarolo hashmap.
     */
    private LinkedHashMap<String, Ufo> ufos = new LinkedHashMap<String, Ufo>();

    /**
     * A teleportokat tarolo hashmap.
     */
    private LinkedHashMap<String, Teleport> teleports = new LinkedHashMap<String, Teleport>();

    /**
     * A vasakat tarolo hashmap.
     */
    private LinkedHashMap<String, Iron> iron = new LinkedHashMap<String, Iron>();

    /**
     * A szeneket tarolo hashmap.
     */
    private LinkedHashMap<String, Coal> coal = new LinkedHashMap<String, Coal>();

    /**
     * A vasakat tarolo hashmap.
     */
    private LinkedHashMap<String, Ice> ice = new LinkedHashMap<String, Ice>();

    /**
     * Az uraniumot tarolo hashmap.
     */
    private LinkedHashMap<String, Uranium> uran = new LinkedHashMap<String, Uranium>();

    /**
     * Az aszteroidaov, ami az aszteroidakat tartalmazza.
     */
    private Asteroidfield asteroidfield;

    /**
     * A jatekban szereplo nap.
     */
    private Sun sun ;

    /**
     * A jatek veget jelolo bool valtozo. Alaperteke hamis.
     */
    private boolean end = false;

    /**
     * A gyozelmet jelolo bool valtozo. Alaperteke hamis.
     */
    private boolean victory = false;

    /**
     * Az inicializalo fazist jelolo bool valtozo.
     */
    private boolean initializing;

    /**
     * A leallasi felteteleke figyelo bool valtozo. Ha igaz,
     * figyeli az egyes objektumok allapotat, es a jatek veget/gyozelmet allithatja be.
     */
    private boolean checkconditions;

    /**
     * Manualis tesztelest beallito valtozo.
     */
    private boolean manual;

    /**
     * A kimeneti stringeket tarolo tomb.
     */
    private ArrayList<String> output = new ArrayList<String>();

    /**
     * A kontroller peldanya, singleton osztaly miatt.
     */
    private static Controller instance;

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
        System.out.println(out);
        output.add(out);
    }

    /**
     * Egy output text filet general.
     */
    private void GenerateOutputFile()
    {
        try
        {
            PrintWriter textout = new PrintWriter("Test_Output.txt", "UTF-8");
            for(int i = 0; i < output.size(); i++)
                textout.println(output.get(i));
            textout.close();
        }
        catch(IOException e)
        {
            System.out.println("I/O Exception");
        }
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////INITIALIZING PHASE////////////////////////////////////////////////////////

////////////////////////READ INPUT//////////////////////

    /**
     * A program palyaepito fazisanak levezenyleset kezelo metodus.
     * Egy ciklusban bekeri a felhasznalotol a palyaepito parancsokat es a parancsnak megfeleloen meghivja a megfelelo
     * metodust.
     */
    public void StartInitPhase()
    {
        String out = "[BUILDING GAME]";
        WriteOut(out);

        initializing = true;
        Scanner scanner = new Scanner(System.in);
        sun = new Sun();
        asteroidfield = new Asteroidfield();
        sun.AddAsteroidfield(asteroidfield);
        while(initializing)
        {
            String[] cmd = scanner.nextLine().split(" ");
            switch (cmd[0]) {
                case "set_sunnearness":
                    SetSunnearness(cmd);
                    break;
                case "set_core":
                    SetCore(cmd);
                    break;
                case "set_layers":
                    SetLayers(cmd);
                    break;
                case "set_neighbour":
                    SetNeighbour(cmd);
                    break;
                case "make":
                    Make(cmd);
                    break;
                case "add_entity":
                    AddEntity(cmd);
                    break;
                case "add_inventory":
                    AddInventory(cmd);
                    break;
                case "set_pair":
                    SetPair(cmd);
                    break;
                case "set_interact_count":
                    SetInteractionCount(cmd);
                    break;
                case "done":
                    Done();
                    break;
                default:
                    break;
            }
        }

        out = "[BUILDING ENDED]";
        WriteOut(out);
    }

    /**
     * A program palyaepito fazisanak levezenyleset kezelo metodus.
     * Egy ciklusban beolvassa a parameterkent kapott fajlbol a palyaepito parancsokat es a parancsnak megfeleloen meghivja a megfelelo
     * metodust.
     * @param buildinput Annak a fajlnak az elerese, amelybol be kell olvasni a parancsokat.
     */
    public void StartInitPhaseFromFile(String buildinput)
    {

        String out = "[BUILDING GAME]";
        WriteOut(out);

        sun = new Sun();
        asteroidfield = new Asteroidfield();
        sun.AddAsteroidfield(asteroidfield);

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(buildinput)));
            String in;
            while ((in = br.readLine()) != null) {
                String[] cmd = in.split(" ");
                switch (cmd[0]) {
                    case "set_sunnearness":
                        SetSunnearness(cmd);
                        break;
                    case "set_core":
                        SetCore(cmd);
                        break;
                    case "set_layers":
                        SetLayers(cmd);
                        break;
                    case "set_neighbour":
                        SetNeighbour(cmd);
                        break;
                    case "make":
                        Make(cmd);
                        break;
                    case "add_entity":
                        AddEntity(cmd);
                        break;
                    case "add_inventory":
                        AddInventory(cmd);
                        break;
                    case "set_pair":
                        SetPair(cmd);
                        break;
                    case "set_interact_count":
                        SetInteractionCount(cmd);
                        break;
                    case "done":
                        Done();
                        break;
                    default:
                        break;
                }
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("I/O Exception");
        }

        out = "[BUILDING ENDED]";
        WriteOut(out);
    }

////////////////////////COMMANDS//////////////////////

    /**
     * Beallitja a kapott parameterre az aszteroidanak a napkozelseget.
     * Ezutan kiirja vegbement valtozasokat.
     * @param param: a kapott karakterlanc egy tombben, ami tartalmazza a parancsot,
     *             az aszteroida azonositojat, es a beallitando erteket
     */
    private void SetSunnearness(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
            switch (param[2])
            {
                case "true":
                    if(asteroids.containsKey(param[1])) {
                        asteroids.get(param[1]).SetSunnearnessInit(true);
                        out = "Asteroid: " + param[1] + " sunnearness set to true.";
                    }
                    break;
                case "false":
                    if(asteroids.containsKey(param[1])) {
                        asteroids.get(param[1]).SetSunnearnessInit(false);
                        out = "Asteroid: " + param[1] + " sunnearness set to false.";
                    }
                    break;
                default:
                    break;
            }

        WriteOut(out);
    }

    /**
     * Beallitja az a kapott aszteroida magjat a kapott parameteru objektumra.
     * Ezutan kiirja vegbement valtozasokat.
     * @param param: a kapott karakterlanc, ami tartalmazza a parancsot,
     *            az aszteroida azonositojat, es a beallitando nyersanyagot
     */
    private void SetCore(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
        {
            if(iron.containsKey(param[2])) {
                asteroids.get(param[1]).SetCore(iron.get(param[2]));
                out = "Iron: " + param[2] + " added to Asteroid: " + param[1] + ".";
            }
            else if(ice.containsKey(param[2])) {
                asteroids.get(param[1]).SetCore(ice.get(param[2]));
                out = "Ice: " + param[2] + " added to Asteroid: " + param[1] + ".";
            }
            else if(coal.containsKey(param[2])) {
                asteroids.get(param[1]).SetCore(coal.get(param[2]));
                out = "Coal: " + param[2] + " added to Asteroid: " + param[1] + ".";
            }
            else if(uran.containsKey(param[2])) {
                asteroids.get(param[1]).SetCore(uran.get(param[2]));
                out = "Uranium: " + param[2] + " added to Asteroid: " + param[1] + ".";
            }
        }

        WriteOut(out);
    }

    /**
     * Beallitja az a kapott aszteroida retegeinek szamat a kapott ertekre.
     * Ezutan kiirja vegbement valtozasokat.
     * @param param: a karakterlanc egy tombben, ami tartalmazza az parancsot, a
     *             aszteroida azonositojat, es a beallitando erteket.
     */
    private void SetLayers(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
        {
            if(asteroids.containsKey(param[1])) {
                asteroids.get(param[1]).SetLayer(Integer.parseInt(param[2]));
                out = "Asteroid: " + param[1] + " layers set to " + param[2] + ".";
            }
        }

        WriteOut(out);
    }

    /**
     *  Beallitja az a kapott aszteroida retegeinek szamat a kapott ertekre.
     *  Ezutan kiirja vegbement valtozasokat.
     * @param param: a karakterlanc egy tombben, ami tartalmazza a parancsot, es
     *            két whereabout azonositojat.
     */
    private void SetNeighbour(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
        {
            if(asteroids.containsKey(param[1]))
            {
                if(asteroids.containsKey(param[2]))
                {
                    asteroids.get(param[1]).AddNeighbour(asteroids.get(param[2]));
                    asteroids.get(param[2]).AddNeighbour(asteroids.get(param[1]));
                    out = "Asteroid: " + param[1] + " and Asteroid: " + param[2] + " became neighbours.";
                }
                else if(teleports.containsKey(param[2]))
                {
                    teleports.get(param[2]).Deploy(asteroids.get(param[1]));
                    out = "Asteroid: " + param[1] + " and Teleport: " + param[2] + " became neighbours.";
                }
            }
            else if(teleports.containsKey(param[1]) && asteroids.containsKey(param[2]))
            {
                teleports.get(param[1]).Deploy(asteroids.get(param[2]));
                out = "Teleport: " + param[1] + " and Asteroid: " + param[2] + " became neighbours.";
            }
        }

        WriteOut(out);
    }

    /**
     * Elkesziti a a parameterkent kapott objektumot, es hozzarendeli az azonositojat.
     * Ezutan kiirja vegbement valtozasokat.
     * @param param: a karakterlanc egy tombben, ami tartalmazza a parancsot, a
     *             letrehozando objektum tipusat, es azonositojat
     */
    private void Make(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3){
            switch(param[1])
            {
                case "settler":
                    Settler s = new Settler();
                    settlers.put(param[2], s);
                    out = "New Settler: " + param[2] + " has been created.";
                    break;
                case "robot":
                    Robot r = new Robot();
                    robots.put(param[2], r);
                    out = "New Robot: " + param[2] + " has been created.";
                    break;
                case "ufo":
                    Ufo u = new Ufo();
                    ufos.put(param[2], u);
                    out = "New Ufo: " + param[2] + " has been created.";
                    break;
                case "asteroid":
                    Asteroid a = new Asteroid();
                    asteroidfield.AddAsteroid(a);
                    asteroids.put(param[2], a);
                    out = "New Asteroid: " + param[2] + " has been created.";
                    break;
                case "teleport":
                    Teleport t= new Teleport();
                    teleports.put(param[2], t);
                    out = "New Teleport: " + param[2] + " has been created.";
                    break;
                case "coal":
                    Coal c = new Coal();
                    coal.put(param[2], c);
                    out = "New Coal: " + param[2] + " has been created.";
                    break;
                case "ice":
                    Ice i = new Ice();
                    ice.put(param[2], i);
                    out = "New Ice: " + param[2] + " has been created.";
                    break;
                case "iron":
                    Iron ir = new Iron();
                    iron.put(param[2], ir);
                    out = "New Iron: " + param[2] + " has been created.";
                    break;
                case "uranium":
                    Uranium ur = new Uranium();
                    uran.put(param[2], ur);
                    out = "New Uranium: " + param[2] + " has been created.";
                    break;
                case "default":
                    break;
            }
        }
        WriteOut(out);
    }

    /**
     * Hozzadja a parameterkent kapott objektumot a telepes inventory-jához.
     *  Ezutan kiirja vegbement valtozasokat.
     * @param param: a karakterlanc egy tombben, ami tartalmazza a parancsot,
     *             a telepes azonositojat, es a hozzaadando objektumot
     */
    private void AddInventory(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
        {
            if(settlers.containsKey(param[1]))
            {
                if(iron.containsKey(param[2])) {
                    settlers.get(param[1]).GetInventory().AddMaterial(iron.get(param[2]));
                    out = "Iron: " + param[2] + " added to Settler: " + param[1] + "'s inventory.";
                }
                else if(ice.containsKey(param[2])) {
                    settlers.get(param[1]).GetInventory().AddMaterial(ice.get(param[2]));
                    out = "Ice: " + param[2] + " added to Settler: " + param[1] + "'s inventory.";
                }
                else if(coal.containsKey(param[2])) {
                    settlers.get(param[1]).GetInventory().AddMaterial(coal.get(param[2]));
                    out = "Coal: " + param[2] + " added to Settler: " + param[1] + "'s inventory.";
                }
                else if(uran.containsKey(param[2])) {
                    settlers.get(param[1]).GetInventory().AddMaterial(uran.get(param[2]));
                    out = "Uranium: " + param[2] + " added to Settler: " + param[1] + "'s inventory.";
                }
                else if(teleports.containsKey(param[2])) {
                    settlers.get(param[1]).GetInventory().AddTeleport(teleports.get(param[2]));
                    out = "Teleport: " + param[2] + " added to Settler: " + param[1] + "'s inventory.";
                }
            }
        }
        WriteOut(out);
    }

    /**
     * Osszeparosit ket teleportot, amit parameterkent kapott.
     *  Ezutan kiirja vegbement valtozasokat.
     * @param param: a karakterlanc egy tombben, ami tartalmazza a parancsot,
     *             es a ket teleport azonositojat
     */
    private void SetPair(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
        {
            if(teleports.containsKey(param[1]) && teleports.containsKey(param[2]))
            {
                teleports.get(param[1]).SetPair(teleports.get(param[2]));
                teleports.get(param[2]).SetPair(teleports.get(param[1]));
                out = "Teleport: " + param[1] + " and Teleport: " + param[2] + " became a pair.";
            }
        }
        WriteOut(out);
    }

    /**
     *
     * @param param
     */
    private void SetInteractionCount(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
        {
            if(iron.containsKey(param[1])) {
                iron.get(param[1]).SetInteractCount(Integer.parseInt(param[2]));
                out = "Iron: " + param[1] + "'s interactCount is set to " + param[2] + ".";
            }
            else if(ice.containsKey(param[1])) {
                ice.get(param[1]).SetInteractCount(Integer.parseInt(param[2]));
                out = "Ice: " + param[1] + "'s interactCount is set to " + param[2] + ".";
            }
            else if(coal.containsKey(param[1])) {
                coal.get(param[1]).SetInteractCount(Integer.parseInt(param[2]));
                out = "Coal: " + param[1] + "'s interactCount is set to " + param[2] + ".";
            }
            else if(uran.containsKey(param[1])) {
                uran.get(param[1]).SetInteractCount(Integer.parseInt(param[2]));
                out = "Uranium: " + param[1] + "'s interactCount is set to " + param[2] + ".";
            }
        }

        WriteOut(out);
    }

    /**
     *
     */
    private void Done() {
        initializing = false;
    }

    /**
     *
     * @param param
     */
    private void AddEntity(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
        {
            if(asteroids.containsKey(param[1]))
            {
                if(settlers.containsKey(param[2])) {
                    asteroids.get(param[1]).AddEntity(settlers.get(param[2]));
                    out = "Settler: " + param[2] + " added to Asteroid: " + param[1] + ".";
                }
                else if(ufos.containsKey(param[2])) {
                    asteroids.get(param[1]).AddEntity(ufos.get(param[2]));
                    out = "Ufo: " + param[2] + " added to Asteroid: " + param[1] + ".";
                }
                else if(robots.containsKey(param[2])) {
                    asteroids.get(param[1]).AddEntity(robots.get(param[2]));
                    out = "Robot: " + param[2] + " added to Asteroid: " + param[1] + ".";
                }
            }
        }
        WriteOut(out);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////GAME PHASE////////////////////////////////////////////////////////////////

////////////////////////READ INPUT//////////////////////
    /**
     * A program jatekfazisat kezelo metodus.
     * Vegigiteral a jatek szereploinek kulonbozo csoportjain, a csoportokon belul pedig az egyes szereplokon.
     * A szereplok koreiben parancsot olvas be a felhasznalotol, majd meghivja a megfelelo metodust.
     * Egyes szereplocsoportok eseten csak akkor olvas be, ha manual flag jelzett be van allitva.
     * @param man - A boolean valtozo, amely megmondja, hogy a jatek a telepeseken kivul, a tobbi
     *              szereplot automatikusan leptesse, vagy felhasznaloi inputra varjon.
     * @param conditions - A boolean valtozo, amely megmondja, hogy a program figyelje-e
     *                     az esetleges leallasi felteleket (gyozelem, vereseg) vagy ignoralja oket.
     */
    public void StartGamePhase(boolean man, boolean conditions)
    {
        String out = "[STARTING GAME]";
        WriteOut(out);

        manual = man;
        checkconditions = conditions;
        Scanner scanner = new Scanner(System.in);
        String[] cmd;
        while(!end)
        {
            if(end) break;

//SETTLER
            Iterator it = settlers.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                out = "[ROUND OF SETTLER: " + pair.getKey() + "]";
                WriteOut(out);
                cmd = scanner.nextLine().split(" ");
                SettlerRound((String)pair.getKey(), cmd);
                if (end) break;
            }

            if(end) break;

//ROBOT
            it = robots.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                out = "[ROUND OF ROBOT: " + pair.getKey() + "]";
                WriteOut(out);
                if(manual) {
                    cmd = scanner.nextLine().split(" ");
                    RobotRound((String)pair.getKey(), cmd);
                }
                else
                {
                    cmd = null;
                    RobotRound((String)pair.getKey(), cmd);
                }
                if (end) break;
            }

            if(end) break;

//UFO
            it = ufos.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                out = "[ROUND OF UFO: " + pair.getKey() + "]";
                WriteOut(out);
                if(manual) {
                    cmd = scanner.nextLine().split(" ");
                    UfoRound((String)pair.getKey(), cmd);
                }
                else
                {
                    cmd = null;
                    UfoRound((String)pair.getKey(), cmd);
                }
                if (end) break;
            }

            if(end) break;

//TELEPORT
            it = teleports.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if(((Teleport)pair.getValue()).GetCraziness())
                {
                    out = "[ROUND OF TELEPORT: " + pair.getKey() + "]";
                    WriteOut(out);
                    if(manual ) {
                        cmd = scanner.nextLine().split(" ");
                        TeleportRound((String)pair.getKey(), cmd);
                    }
                    else
                    {
                        cmd = null;
                        TeleportRound((String)pair.getKey(), cmd);
                    }
                    if (end) break;
                }
            }

            if(end) break;

//SUN
            out = "[ROUND OF SUN]";
            WriteOut(out);
            cmd = null;
            if(manual) {
                cmd = scanner.nextLine().split(" ");

            }
            SunRound(cmd);

            if(end) break;

//ASTEROIDFIELD
            out = "[ROUND OF ASTEROIDFIELD]";
            WriteOut(out);
            cmd = null;
            if(manual) {
                cmd = scanner.nextLine().split(" ");

            }
            AsteroidfieldRound(cmd, false);
        }

        out = "[GAME OVER]";
        WriteOut(out);

        GenerateOutputFile();
    }

    /**
     * A program jatekfazisat kezelo metodus.
     * Vegigiteral a jatek szereploinek kulonbozo csoportjain, a csoportokon belul pedig az egyes szereplokon.
     * A szereplok koreiben parancsot olvas be a parameterkent kapott fajlbol, majd meghivja a megfelelo metodust.
     * Egyes szereplocsoportok eseten csak akkor olvas be, ha manual flag jelzett be van allitva.
     * @param buildinput - Annak a fajlnak az elerese, amelybol be kell olvasni a parancsokat.
     * @param man - A boolean valtozo, amely megmondja, hogy a jatek a telepeseken kivul, a tobbi
     *              szereplot automatikusan leptesse, vagy felhasznaloi inputra varjon.
     * @param conditions - A boolean valtozo, amely megmondja, hogy a program figyelje-e
     *                     az esetleges leallasi felteleket (gyozelem, vereseg) vagy ignoralja oket.
     */
    public void StartGamePhaseFromFile(String buildinput, boolean man, boolean conditions)
    {
        try {
            String out = "[STARTING GAME]";
            WriteOut(out);
            manual = man;
            checkconditions = conditions;
            BufferedReader br = new BufferedReader(new FileReader(new File(buildinput)));
            ArrayList<String> in = new ArrayList<String>();
            int incnt = 0;
            String temp;
            while((temp = br.readLine()) != null)
                in.add(temp);
            br.close();


            String[] cmd;
            while (!end) {
                if (end) break;

//SETTLER
                Iterator it = settlers.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    out = "[ROUND OF SETTLER: " + pair.getKey() + "]";
                    WriteOut(out);
                    cmd = (in.get(incnt)).split(" ");
                    incnt++;
                    SettlerRound((String)pair.getKey(), cmd);
                    if (end) break;
                }

                if (end) break;
//ROBOT
                it = robots.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    out = "[ROUND OF ROBOT: " + pair.getKey() + "]";
                    WriteOut(out);
                    if (manual) {
                        cmd = (in.get(incnt)).split(" ");
                        incnt++;
                        RobotRound((String)pair.getKey(), cmd);
                    }
                    else {
                        cmd = null;
                        RobotRound((String)pair.getKey(), cmd);
                    }
                    if (end) break;
                }

                if (end) break;

//UFO
                it = ufos.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    out = "[ROUND OF UFO: " + pair.getKey() + "]";
                    WriteOut(out);
                    if (manual) {
                        cmd = (in.get(incnt)).split(" ");
                        incnt++;
                        UfoRound((String)pair.getKey(), cmd);
                    }
                    else {
                        cmd = null;
                        UfoRound((String)pair.getKey(), cmd);
                    }
                    if (end) break;
                }

                if (end) break;

//TELEPORT
                it = teleports.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if(((Teleport)pair.getValue()).GetCraziness()) {
                        out = "[ROUND OF TELEPORT: " + pair.getKey() + "]";
                        WriteOut(out);
                        if (manual) {
                            cmd = (in.get(incnt)).split(" ");
                            incnt++;
                            TeleportRound((String) pair.getKey(), cmd);
                        } else {
                            cmd = null;
                            TeleportRound((String) pair.getKey(), cmd);
                        }
                        if (end) break;
                    }
                }

                if (end) break;

//SUN
                out = "[ROUND OF SUN]";
                WriteOut(out);
                cmd = null;
                if (manual) {
                    cmd = (in.get(incnt)).split(" ");
                    incnt++;
                }
                SunRound(cmd);

                if (end) break;

//ASTEROIDFIELD
                out = "[ROUND OF ASTEROIDFIELD]";
                WriteOut(out);
                cmd = null;
                if (manual) {
                    cmd = (in.get(incnt)).split(" ");
                    boolean iterating = true;
                    while(iterating && in.size()-1 > incnt)
                    {
                        if(in.get(incnt+1).equals("true") || in.get(incnt+1).equals("false"))
                        {
                            String[] tempstring = new String[cmd.length+1];
                            for(int j = 0; j < cmd.length; j++)
                                tempstring[j] = cmd[j];
                            tempstring[cmd.length] = in.get(incnt+1);
                            cmd = tempstring;
                            incnt++;
                        }
                        else
                            iterating = false;
                    }
                    incnt++;
                }
                AsteroidfieldRound(cmd, true);
            }
            out = "[GAME OVER]";
            WriteOut(out);
            GenerateOutputFile();
        }
        catch(IOException e)
        {
            System.out.println("I/O Exception");
        }
    }

///////////////////////////ROUND HANDLING////////////////////////////////////////

    private void SettlerRound(String settlerid, String[] cmd) {

        switch (cmd[0])
        {
            case "drill":
                SettlerDrill(settlerid);
                break;
            case "mine":
                SettlerMine(settlerid);
                break;
            case "craft":
                Craft(settlerid, cmd);
                break;
            case "move":
                SettlerMove(settlerid, cmd[1]);
                break;
            case "place":
                Place(cmd[1], settlerid);
                break;
            case "endgame":
                Endgame(false);
                break;
            case "step":
                Step();
                break;
            default:
                break;
        }
        if(checkconditions)
        {
            CheckMaterials();
            CheckVictory(settlers.get(settlerid));
        }
    }

    private void RobotRound(String robotid, String[] cmd) {
        if (manual) {
            switch (cmd[0]) {
                case "drill":
                    RobotDrill(robotid);
                    break;
                case "move":
                    RobotMove(robotid, cmd[1]);
                    break;
                case "step":
                    Step();
                    break;
                case "endgame":
                    Endgame(false);
                    break;
                case "dophase":
                    RobotDoPhase(robotid);
                    break;
                default:
                    break;
            }
        }
        else
            RobotDoPhase(robotid);
        if(checkconditions)
            CheckMaterials();
    }

    private void UfoRound(String ufoid, String[] cmd) {
        if (manual) {
            switch (cmd[0]) {
                case "mine":
                    UfoMine(ufoid);
                    break;
                case "move":
                    UfoMove(ufoid, cmd[1]);
                    break;
                case "step":
                    Step();
                    break;
                case "endgame":
                    Endgame(false);
                    break;
                case "dophase":
                    UfoDoPhase(ufoid);
                    break;
                default:
                    break;
            }
        }
        else
            UfoDoPhase(ufoid);
        if(checkconditions)
            CheckMaterials();
    }

    private void TeleportRound(String teleportid, String[] cmd) {
        if (manual) {
            switch (cmd[0]) {
                case "move":
                    TeleportMove(teleportid, cmd[1]);
                    break;
                case "step":
                    Step();
                    break;
                case "endgame":
                    Endgame(false);
                    break;
                case "dophase":
                    TeleportDoPhase(teleportid);
                    break;
                default:
                    break;
            }
        }
        else
            TeleportDoPhase(teleportid);
    }

    private void SunRound(String[] cmd) {
        if (manual) {
            switch (cmd[0])
            {
                case "step":
                    Step();
                    break;
                case "sunstorm":
                    SunStorm(cmd[1]);
                    break;
                case "endgame":
                    Endgame(false);
                    break;
                default:
                    break;
            }
        }
        else
            SunStorm();

    }

    private void AsteroidfieldRound(String[] cmd, boolean fromfile) {
           if (manual) {
               switch (cmd[0]) {
                   case "step":
                       Step();
                       break;
                   case "rearrange":
                       if(!fromfile)
                           RearrangeManually();
                       else
                           RearrangeFromFile(cmd);
                       break;
                   case "endgame":
                       Endgame(false);
                       break;
                   default:
                       break;
               }
           } else
               Rearrange();

        if(checkconditions) CheckMaterials();
    }

////////////////////////COMMANDS////////////////////////////////////

////////////////DOPHASE///////////////
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
                Random rand = new Random();
                Whereabout neighbour = a.GetNeighbour(rand.nextInt(a.GetNumberOfNeighbours()));
                String where_id = SearchForWhereabout(neighbour);
                if (where_id != null)
                    TeleportMove(id, where_id);
            }
        }
    }

////////////////DRILL//////////////////////

    /**
     * A megadott telepest furatja a metodus.
     * @param id - A telepest azonosito id
     */
    private void SettlerDrill(String id)
    {
        String out = "Invalid parametes.";

        if(settlers.containsKey(id))
        {
            Asteroid a = settlers.get(id).GetAsteroid();
            String asteroid_id = SearchForAsteroid(a);
            boolean success = settlers.get(id).Drill();
            if (!success)
                out = "Settler: " + id + " failed to drill.";
            else {
                if (asteroid_id != null)
                    out = "Settler: " + id + " drilled Asteroid: " + asteroid_id + ".";
            }
        }
        WriteOut(out);
    }

    /**
     * A robotot furatja a metodus
     * @param id
     */
    private void RobotDrill(String id) {
        String out = "Invalid parametes.";

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
     *
     * @param id
     */
    private void SettlerMine(String id)
    {
        String out = "Invalid parameters.";

        if(settlers.containsKey(id))
        {
            Asteroid a = settlers.get(id).GetAsteroid();
            String asteroid_id = SearchForAsteroid(a);

            Material m = a.GetMaterial();
            String material_id = SearchForMaterial(m);
            boolean success = settlers.get(id).Mine();
            if (!success)
                out = "Settler: " + id + " failed to mine.";
            else if (asteroid_id != null && material_id != null)
                out = "Settler: " + id + " mined Asteroid: " + asteroid_id + " with " + m.GetName() + ": " + material_id + ".";
        }
        WriteOut(out);
    }

    private void UfoMine(String id) {
        String out = "Invalid parameters.";

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
     *
     * @param id
     * @param param
     */
    private void Craft(String id, String[] param)
    {
        String out = "Invalid parameters.";
        if(param[1].equals("robot"))
        {
            boolean success = settlers.get(id).CraftRobot();

            if(!success)
                out = "Settler: " + id + " failed to craft.";
            else {
                out = "Settler: " + id + " crafted ";
                out = out + output.get(output.size() - 1);
                output.remove(output.size()-1);
            }
        }
        if(param[1].equals("teleport"))
        {
            boolean success = settlers.get(id).CraftTeleport();

            if(!success)
                out = "Settler: " + id + " failed to craft.";
            else {
                out = "Settler: " + id + " crafted ";
                out = out + output.get(output.size() - 1);
                output.remove(output.size()-1);
            }
        }
        WriteOut(out);
    }

//////////////////////PLACE///////////////////////////////

    /**
     *
     * @param id
     * @param settlerid
     */
    private void Place(String id, String settlerid)
    {
        String out = "Invalid parameters.";

        Asteroid a = settlers.get(settlerid).GetAsteroid();
        String asteroid_id = SearchForAsteroid(a);

        boolean success = false;

        if(coal.containsKey(id)) {
            success = settlers.get(settlerid).PlaceMaterial(coal.get(id));
            if (success) {
                out = "Settler: " + settlerid + " placed Coal: " + id + " at Asteroid: " + asteroid_id + ".";
            }
        }
        else if(iron.containsKey(id)) {
            success = settlers.get(settlerid).PlaceMaterial(iron.get(id));
            if (success) {
                out = "Settler: " + settlerid + " placed Iron: " + id + " at Asteroid: " + asteroid_id + ".";
            }
        }
        else if(ice.containsKey(id)) {
            success = settlers.get(settlerid).PlaceMaterial(ice.get(id));
            if (success) {
                out = "Settler: " + settlerid + " placed Ice: " + id + " at Asteroid: " + asteroid_id + ".";
            }
        }
        else if(uran.containsKey(id)) {
            success = settlers.get(settlerid).PlaceMaterial(uran.get(id));
            if (success) {
                out = "Settler: " + settlerid + " placed Uranium: " + id + " at Asteroid: " + asteroid_id + ".";
            }
        }
        else if(teleports.containsKey(id)) {
            success = settlers.get(settlerid).PlaceTeleport(teleports.get(id));
            if (success) {
                out = "Settler: " + settlerid + " placed Teleport: " + id + " at Asteroid: " + asteroid_id + ".";
            }
        }

        if (!success)
            out = "Settler: " + settlerid + " failed to place.";

        WriteOut(out);
    }

///////////////////MOVE//////////////////////////

    /**
     *
     * @param id
     * @param where
     */
    private void SettlerMove(String id, String where) {
        String out = "Invalid parameters.";

        Whereabout w;
        Asteroid current = settlers.get(id).GetAsteroid();
        String previous_asteroidid = SearchForAsteroid(current);

        if (settlers.containsKey(id)) {
            if (asteroids.containsKey(where)) {
                w = asteroids.get(where);

                boolean success = settlers.get(id).Move(w);
                if (!success)
                    out = "Settler: " + id + " failed to move.";
                else
                    out = "Settler: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + where + ".";

            } else if (teleports.containsKey(where)) {
                Teleport pair = teleports.get(where).GetPair();
                String to = "null";
                if(pair != null) {
                    Asteroid a = pair.GetAsteroid();
                    to = SearchForAsteroid(a);
                }

                w = teleports.get(where);
                boolean success = settlers.get(id).Move(w);
                if (!success)
                    out = "Settler: " + id + " failed to move.";
                else
                    out = "Settler: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + to + ".";
            }
        }
        WriteOut(out);
    }

    private void RobotMove(String id, String where) {
        String out = "Invalid parameters.";

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

    private void UfoMove(String id, String where) {
        String out = "Invalid parameters.";

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

    private void TeleportMove(String id, String where) {
        String out = "Invalid parameters.";

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
     *
     * @param asteroidid
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
     *
     */
    private void SunStorm()
    {
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

    }

////////////////REARRANGE////////////////

    /**
     *
     */
    private void RearrangeManually()
    {
        String out = "Sunnearness:";
        Scanner scanner = new Scanner(System.in);
        Iterator it = asteroids.entrySet().iterator();
        WriteOut(out);
        Asteroid[] tempasteroids = new Asteroid[asteroids.size()];
        boolean[] nearness = new boolean[asteroids.size()];
        int cnt = 0;
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            tempasteroids[cnt] = (Asteroid) pair.getValue();
            out = "\t\tAsteroid: " + pair.getKey() + " ";
            System.out.print(out);
            String in = scanner.next();
            if (in.equals("true")) {
                nearness[cnt++] = true;
                out = out + "(true)";
                if (it.hasNext()) {
                    out = out + ",";
                }
            }
            else if (in.equals("false")) {
                nearness[cnt++] = false;
                out = out + "(false)";
                if (it.hasNext()) {
                    out = out + ",";
                }
            }
            output.add(out);
        }

        for(int i = 0; i < tempasteroids.length; i++)
        {
            tempasteroids[i].SetSunnearness(nearness[i]);
        }
    }

    private void Rearrange()
    {
        String out = "Sunnearness:";
        Iterator it = asteroids.entrySet().iterator();
        WriteOut(out);
        Asteroid[] tempasteroids = new Asteroid[asteroids.size()];
        boolean[] nearness = new boolean[asteroids.size()];
        int cnt = 0;
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            tempasteroids[cnt] = (Asteroid) pair.getValue();
            out = "\t\tAsteroid: " + pair.getKey() + " ";
            System.out.print(out);
            int b = new Random().nextInt(2);
            if (b == 1)
            {
                nearness[cnt++] = true;
                out = out + "(true)";
                System.out.print("(true)");
                if (it.hasNext()) {
                    out = out + ",";
                    System.out.print(",");
                }
            }
            else
            {
                nearness[cnt++] = false;
                out = out + "(false)";
                System.out.print("(false)");
                if (it.hasNext()) {
                    out = out + ",";
                    System.out.print(",");
                }
            }
            System.out.print("\n");
            output.add(out);
        }
        for(int i = 0; i < tempasteroids.length; i++)
        {
            tempasteroids[i].SetSunnearness(nearness[i]);
        }
    }

    private void RearrangeFromFile(String[] param)
    {
        boolean[] nearness = new boolean[asteroids.size()];
        Asteroid[] tempasteroids = new Asteroid[asteroids.size()];
        int cnt = 0;
        String out = "Sunnearness:";
        Iterator it = asteroids.entrySet().iterator();
        WriteOut(out);
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            tempasteroids[cnt] = (Asteroid) pair.getValue();
            out = "\t\tAsteroid: " + pair.getKey() + " ";
            System.out.print(out);
            String in = param[cnt+1];
            if (in.equals("true")) {
                nearness[cnt++] = true;
                out = out + "(true)";
                System.out.print("(true)");
                if (it.hasNext()) {
                    out = out + ",";
                    System.out.print(",");
                }
            } else if (in.equals("false")) {
                nearness[cnt++] = false;
                out = out + "(false)";
                System.out.print("(false)");
                if (it.hasNext()) {
                    out = out + ",";
                    System.out.print(",");
                }
            }
            System.out.print("\n");
            output.add(out);
        }

        for(int i = 0; i < tempasteroids.length; i++)
        {
            tempasteroids[i].SetSunnearness(nearness[i]);
        }
    }

///////////////STEP//////////////////////////

    /**
     * Kiirja a konzolra, hogy a lepes ki lett hagyva.
     * What are you doing step-command?
     */
    private void Step() {
        String out = "Phase has been stepped.";
        WriteOut(out);
    }

///////////////ENDGAME//////////////////////

    /**
     *
     * @param v
     */
    private void Endgame(boolean v)
    {
        end = true;
        victory = v;

        String out;
        Iterator it = asteroids.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Asteroid a = (Asteroid) pair.getValue();


            out = "Asteroid: " + pair.getKey();
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
        }

        it = teleports.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Teleport t = (Teleport)pair.getValue();
            out = "Teleport: " + pair.getKey();
            WriteOut(out);

            if(SearchForTeleport(t.GetPair()) != null)
                out = "\tPair: " + SearchForTeleport(t.GetPair());
            else
                out = "\tPair: null";
            WriteOut(out);

            if(t.GetPair() != null && t.GetPair().GetPairReadiness())
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
        }

        it = settlers.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Settler s = (Settler)pair.getValue();
            out = "Settler: " + pair.getKey();
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
                if(SearchForCoal(ma.get(i)) != null) out = "\t\t\tCoal: " + SearchForCoal(ma.get(i));
                else if(SearchForIce(ma.get(i)) != null) out = "\t\t\tIce: " + SearchForIce(ma.get(i));
                else if(SearchForIron(ma.get(i)) != null) out = "\t\t\tIron: " + SearchForIron(ma.get(i));
                else if(SearchForUranium(ma.get(i)) != null) out = "\t\t\tUranium: " + SearchForUranium(ma.get(i));

                WriteOut(out);
            }

            out = "\t\tTeleports:";
            WriteOut(out);

            if(ta.size() == 0)
            {
                out = "\t\t\tnull" ;
                WriteOut(out);
            }
            for(int i = 0; i < ta.size(); i++)
            {
                if(SearchForTeleport(ta.get(i)) != null) out = "\t\t\tTeleport: " + SearchForTeleport(ta.get(i));

                WriteOut(out);
            }
        }

        it = robots.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Robot r = (Robot)pair.getValue();
            out = "Robot: " + pair.getKey();
            WriteOut(out);

            if(r.GetAsteroid() != null)
                out = "\tAsteroid: " + SearchForAsteroid(r.GetAsteroid());
            else
                out = "\tnull";
            WriteOut(out);
        }

        it = ufos.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Ufo u = (Ufo)pair.getValue();
            out = "Ufo: " + pair.getKey();
            WriteOut(out);

            if(u.GetAsteroid() != null)
                out = "\tAsteroid: " + SearchForAsteroid(u.GetAsteroid());
            else
                out = "\tnull";
            WriteOut(out);
        }

        it = uran.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Uranium u = (Uranium)pair.getValue();
            out = "Uranium: " + pair.getKey();
            WriteOut(out);

            out = "\tInteractCount: " + Integer.toString(u.GetInteractCount());
            WriteOut(out);

            boolean found = false;
            Iterator it2 = settlers.entrySet().iterator();
            while(it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (((Settler) pair2.getValue()).GetInventory().GetMaterials().contains(u)) {
                    found = true;
                    out = "\tSettler: " + pair2.getKey();
                }
            }
            it2 = asteroids.entrySet().iterator();
            while(it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (SearchForMaterial(((Asteroid)pair2.getValue()).GetMaterial()) == pair.getKey())
                {
                    found = true;
                    out = "\tAsteroid: " + pair2.getKey();
                }
            }
            if(!found)
                out = "\tnull";
            WriteOut(out);
        }

        it = ice.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Ice i = (Ice)pair.getValue();
            out = "Ice: " + pair.getKey();
            WriteOut(out);

            out = "\tInteractCount: " + Integer.toString(i.GetInteractCount());
            WriteOut(out);

            boolean found = false;
            Iterator it2 = settlers.entrySet().iterator();
            while(it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (((Settler) pair2.getValue()).GetInventory().GetMaterials().contains(i)) {
                    found = true;
                    out = "\tSettler: " + pair2.getKey();
                }
            }
            it2 = asteroids.entrySet().iterator();
            while(it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (SearchForMaterial(((Asteroid)pair2.getValue()).GetMaterial()) == pair.getKey())
                {
                    found = true;
                    out = "\tAsteroid: " + pair2.getKey();
                }
            }
            if(!found)
                out = "\tnull";
            WriteOut(out);
        }

        it = iron.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Iron i = (Iron)pair.getValue();
            out = "Iron: " + pair.getKey();
            WriteOut(out);

            out = "\tInteractCount: " + Integer.toString(i.GetInteractCount());
            WriteOut(out);

            boolean found = false;
            Iterator it2 = settlers.entrySet().iterator();
            while(it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (((Settler) pair2.getValue()).GetInventory().GetMaterials().contains(i)) {
                    found = true;
                    out = "\tSettler: " + pair2.getKey();
                }
            }
            it2 = asteroids.entrySet().iterator();
            while(it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (SearchForMaterial(((Asteroid)pair2.getValue()).GetMaterial()) == pair.getKey())
                {
                    found = true;
                    out = "\tAsteroid: " + pair2.getKey();
                }
            }
            if(!found)
                out = "\tnull";
            WriteOut(out);
        }

        it = coal.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Coal c = (Coal)pair.getValue();
            out = "Coal: " + pair.getKey();
            WriteOut(out);

            out = "\tInteractCount: " + Integer.toString(c.GetInteractCount());
            System.out.println(out);
            output.add(out);

            boolean found = false;
            Iterator it2 = settlers.entrySet().iterator();
            while(it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (((Settler) pair2.getValue()).GetInventory().GetMaterials().contains(c)) {
                    found = true;
                    out = "\tSettler: " + pair2.getKey();
                }
            }
            it2 = asteroids.entrySet().iterator();
            while(it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (SearchForMaterial(((Asteroid)pair2.getValue()).GetMaterial()) == pair.getKey())
                {
                    found = true;
                    out = "\tAsteroid: " + pair2.getKey();
                }
            }
            if(!found)
                out = "\tnull";
            WriteOut(out);
        }
    }

///////////////CHECK CONDITIONS//////////////////////////

     /**
     * Megnezi, hogy a parameterul kapott telepes aszteroidajan tartozkodo telepesek birtokolnak-e eleg nyersanyagot
     * a jatek megnyeresehez. Ha nem, nem csinal semmit, ha igen, akkor lezarja a jatekot.
     * @param s - A telepes, akinek az aszteroidajan futtatjuk az ellenorzest.
     */
     private void CheckVictory(Settler s){
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
        if(win)
            Endgame(true);
    }

    /**
     * Megnezi, hogy szerepel-e meg eleg nyersanyag a jatekban, ahhoz, hogy a jatekosok nyerhessenek.
     * Ha igen, nem csinal semmit, ha nem, akkor lezarja a jatekot.
     */
    private void CheckMaterials(){
        int countCoal = coal.size();
        int countIce = ice.size();
        int countIron = iron.size();
        int countUranium = uran.size();

        if (countCoal >= 3 && countIce >= 3 && countIron >= 3 && countUranium >= 3)
            return;
        else
            Endgame(false);
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
            if(robots.containsKey("robot" + Integer.toString(n)))
                n++;
            else
                unique = true;
        }
        robots.put("robot" + Integer.toString(n), r);
        output.add("Robot: robot" + Integer.toString(n) + ".");
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
            if(teleports.containsKey("teleport" + Integer.toString(n)) || teleports.containsKey("teleport" + Integer.toString(n+1)))
                n+=2;
            else
                unique = true;
        }
        teleports.put("teleport" + Integer.toString(n), t1);
        teleports.put("teleport" + Integer.toString(n+1), t2);
        output.add("Teleport: teleport" + Integer.toString(n) + " and Teleport: teleport" + Integer.toString(n+1) + ".");
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
