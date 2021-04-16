package game.controller;

import game.logic.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;

public class Controller {
    /**
     *
     */
    private HashMap<String, Asteroid> asteroids = new HashMap<String, Asteroid>();

    /**
     *
     */
    private HashMap<String, Settler> settlers = new HashMap<String, Settler>();

    /**
     *
     */
    private HashMap<String, Robot> robots = new HashMap<String, Robot>();

    /**
     *
     */
    private HashMap<String, Ufo> ufos = new HashMap<String, Ufo>();

    /**
     *
     */
    private HashMap<String, Teleport> teleports = new HashMap<String, Teleport>();

    /**
     *
     */
    private HashMap<String, Iron> iron = new HashMap<String, Iron>();

    /**
     *
     */
    private HashMap<String, Coal> coal = new HashMap<String, Coal>();

    /**
     *
     */
    private HashMap<String, Ice> ice = new HashMap<String, Ice>();

    /**
     *
     */
    private HashMap<String, Uranium> uran = new HashMap<String, Uranium>();

    /**
     *
     */
    private Asteroidfield asteroidfield;

    /**
     *
     */
    private Sun sun ;

    /**
     *
     */
    private boolean end = false;

    /**
     *
     */
    private boolean victory = false;

    /**
     *
     */
    private boolean initializing;

    /**
     *
     */
    private boolean checkconditions;

    /**
     *
     */
    private boolean manual;

    /**
     *
     */
    public void StartInitPhase()
    {
        initializing = true;
        Scanner scanner = new Scanner(System.in);
        sun = new Sun();
        asteroidfield = new Asteroidfield();
        sun.AddAsteroidfield(asteroidfield);
        while(initializing)
        {
            String[] cmd = scanner.next().split(" ");
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
    }

    /**
     *
     * @param param
     */
    public void SetSunnearness(String[] param)
    {
        if(param.length == 3)
            switch (param[2])
            {
                case "true":
                    if(asteroids.containsKey(param[1]))
                        asteroids.get(param[1]).SetSunnearness(true);
                    break;
                case "false":
                    if(asteroids.containsKey(param[1]))
                        asteroids.get(param[1]).SetSunnearness(false);
                    break;
                default:
                    break;
            }
    }

    /**
     *
     * @param param
     */
    public void SetCore(String[] param)
    {
        if(param.length == 3)
        {
            if(iron.containsKey(param[2]))
                asteroids.get(param[1]).AddMaterial(iron.get(param[2]));
            else if(ice.containsKey(param[2]))
                asteroids.get(param[1]).AddMaterial(ice.get(param[2]));
            else if(coal.containsKey(param[2]))
                asteroids.get(param[1]).AddMaterial(coal.get(param[2]));
            else if(uran.containsKey(param[2]))
                asteroids.get(param[1]).AddMaterial(uran.get(param[2]));
        }
    }

    /**
     *
     * @param param
     */
    public void SetLayers(String[] param)
    {
        if(param.length == 3)
        {
            if(asteroids.containsKey(param[1]))
                asteroids.get(param[1]).SetLayer(Integer.parseInt(param[2]));
        }
    }

    /**
     *
     * @param param
     */
    public void SetNeighbour(String[] param)
    {
        if(param.length == 3)
        {
            if(asteroids.containsKey(param[1]))
            {
                if(asteroids.containsKey(param[2]))
                {
                    asteroids.get(param[1]).AddNeighbour(asteroids.get(param[2]));
                    asteroids.get(param[2]).AddNeighbour(asteroids.get(param[1]));
                }
                else if(teleports.containsKey(param[2]))
                {
                    asteroids.get(param[1]).AddNeighbour(teleports.get(param[2]));
                    teleports.get(param[2]).Deploy(asteroids.get(param[1]));
                }
            }
            else if(teleports.containsKey(param[1]) && asteroids.containsKey(param[2]))
            {
                    teleports.get(param[1]).Deploy(asteroids.get(param[2]));
                    asteroids.get(param[2]).AddNeighbour(asteroids.get(param[1]));
            }
        }
    }

    /**
     *
     * @param param
     */
    public void Make(String[] param)
    {
        if(param.length == 3){
            switch(param[1])
            {
                case "settler":
                    Settler s = new Settler();
                    settlers.put(param[2], s);
                    break;
                case "robot":
                    Robot r = new Robot();
                    robots.put(param[2], r);
                    break;
                case "ufo":
                    Ufo u = new Ufo();
                    ufos.put(param[2], u);
                    break;
                case "asteroid":
                    Asteroid a = new Asteroid();
                    asteroids.put(param[2], a);
                    break;
                case "teleport":
                    Teleport t= new Teleport();
                    teleports.put(param[2], t);
                    break;
                case "coal":
                    Coal c = new Coal();
                    coal.put(param[2], c);
                    break;
                case "ice":
                    Ice i = new Ice();
                    ice.put(param[2], i);
                    break;
                case "iron":
                    Iron ir = new Iron();
                    iron.put(param[2], ir);
                    break;
                case "uranium":
                    Uranium ur = new Uranium();
                    uran.put(param[2], ur);
                    break;
                case "default":
                    break;
            }
        }
    }

    /**
     *
     * @param param
     */
    public void AddInventory(String[] param)
    {
        if(param.length == 3)
        {
            if(settlers.containsKey(param[1]))
            {
                if(iron.containsKey(param[2])) settlers.get(param[1]).GetInventory().AddMaterial(iron.get(param[2]));
                else if(ice.containsKey(param[2])) settlers.get(param[1]).GetInventory().AddMaterial(ice.get(param[2]));
                else if(coal.containsKey(param[2])) settlers.get(param[1]).GetInventory().AddMaterial(coal.get(param[2]));
                else if(uran.containsKey(param[2])) settlers.get(param[1]).GetInventory().AddMaterial(uran.get(param[2]));
                else if(teleports.containsKey(param[2])) settlers.get(param[1]).GetInventory().AddTeleport(teleports.get(param[2]));
            }
        }
    }

    /**
     *
     * @param param
     */
    public void SetPair(String[] param)
    {
        if(param.length == 3)
        {
            if(teleports.containsKey(param[1]) && teleports.containsKey(param[2]))
            {
                teleports.get(param[1]).SetPair(teleports.get(param[2]));
                teleports.get(param[2]).SetPair(teleports.get(param[1]));
            }
        }
    }

    /**
     *
     * @param param
     */
    public void SetInteractionCount(String[] param)
    {
        if(param.length == 3)
        {
            if(iron.containsKey(param[1]))
                iron.get(param[1]).SetInteractionCount(Integer.parseInt(param[2]));
            else if(ice.containsKey(param[1]))
                ice.get(param[1]).SetInteractionCount(Integer.parseInt(param[2]));
            else if(coal.containsKey(param[1]))
                coal.get(param[1]).SetInteractionCount(Integer.parseInt(param[2]));
            else if(uran.containsKey(param[1]))
                uran.get(param[1]).SetInteractionCount(Integer.parseInt(param[2]));
        }
    }

    /**
     *
     */
    public void Done() {
        initializing = false;
    }

    /**
     *
     * @param param
     */
    public void AddEntity(String[] param)
    {
        if(param.length == 3)
        {
            if(asteroids.containsKey(param[1]))
            {
                if(settlers.containsKey(param[2])) asteroids.get(param[1]).AddEntity(settlers.get(param[2]));
                else if(ufos.containsKey(param[2])) asteroids.get(param[1]).AddEntity(ufos.get(param[2]));
                else if(robots.containsKey(param[2])) asteroids.get(param[1]).AddEntity(robots.get(param[2]));
            }
        }
    }

    /**
     *
     * @param buildinput
     */
    public void StartInitPhaseFromFile(String buildinput)
    {
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
                br.close();
            }
        }
        catch(IOException e)
        {
            System.out.println("I/O Exception");
        }
    }

    /**
     *
     * @param man
     * @param conditions
     */
    public void StartGamePhase(boolean man, boolean conditions)
    {
        manual = man;
        checkconditions = conditions;
        Scanner scanner = new Scanner(System.in);
        String[] cmd;
        while(!end)
        {
            Iterator it = settlers.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                cmd = scanner.next().split(" ");
                switch (cmd[0])
                {
                    case "drill":
                        Drill((String)pair.getKey());
                        break;
                    case "mine":
                        Mine((String)pair.getKey());
                        break;
                    case "move":
                        Move((String)pair.getKey(), cmd[1]);
                        break;
                    case "place":
                        Move(cmd[1], (String)pair.getKey());
                        break;
                    case "step":
                        break;
                    default:
                        break;
                }
            }

            it = robots.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if(manual) {
                    cmd = scanner.next().split(" ");
                    switch (cmd[0]) {
                        case "drill":
                            Drill((String)pair.getKey());
                            break;
                        case "move":
                            Move((String)pair.getKey(), cmd[1]);
                            break;
                        case "step":
                            break;
                        case "dophase":
                            RobotDoPhase((String)pair.getKey());
                            break;
                        default:
                            break;
                    }
                }
                else
                {
                    RobotDoPhase((String)pair.getKey());
                }
            }

            it = ufos.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if(manual) {
                    cmd = scanner.next().split(" ");
                    switch (cmd[0]) {
                        case "mine":
                            Mine((String)pair.getKey());
                            break;
                        case "move":
                            Move((String)pair.getKey(), cmd[1]);
                            break;
                        case "step":
                            break;
                        case "dophase":
                            UfoDoPhase((String)pair.getKey());
                            break;
                        default:
                            break;
                    }
                }
                else
                {
                    UfoDoPhase((String)pair.getKey());
                }
            }

            it = teleports.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if(manual) {
                    cmd = scanner.next().split(" ");
                    switch (cmd[0]) {
                        case "move":
                            Move((String)pair.getKey(), cmd[1]);
                            break;
                        case "step":
                            break;
                        case "dophase":
                            TeleportDoPhase((String)pair.getKey());
                            break;
                        default:
                            break;
                    }
                }
                else
                {
                    TeleportDoPhase((String)pair.getKey());
                }
            }

            if(manual)
            {
                cmd = scanner.next().split(" ");
                switch (cmd[0])
                {
                    case "step":
                        break;
                    case "sunstorm":
                        SunStorm(cmd[1]);
                }
            }
            else
            {
                SunStorm();
            }
        }
    }

    /**
     *
     * @param buildinput
     * @param man
     * @param conditions
     */
    public void StartGamePhaseFromFile(String buildinput, boolean man, boolean conditions)
    {

    }

    /**
     *
     * @param id
     */
    public void RobotDoPhase(String id)
    {
        if(robots.containsKey(id))
        {
            Robot r = robots.get(id);
            Asteroid a = r.GetAsteroid();
            if(a.GetLayer() > 0)    Drill(id);
            else if(a.GetNumberOfNeighbours() > 0)
            {
                Random rand = new Random();
                Whereabout neighbour = a.GetNeighbour(rand.nextInt(a.GetNumberOfNeighbours()));
                Iterator it = asteroids.entrySet().iterator();
                while(it.hasNext())
                {
                    Map.Entry pair = (Map.Entry) it.next();
                    if(pair.getValue() == neighbour) Move(id, (String)pair.getKey());
                }
                it = teleports.entrySet().iterator();
                while(it.hasNext())
                {
                    Map.Entry pair = (Map.Entry) it.next();
                    if(pair.getValue() == neighbour) Move(id, (String)pair.getKey());
                }
            }
        }
    }

    /**
     *
     * @param id
     */
    public void UfoDoPhase(String id)
    {
        if(ufos.containsKey(id))
        {
            Ufo u = ufo.get(id);
            Asteroid a = u.GetAsteroid();
            if((a.GetLayer() > 0 || a.GetEmpty()) && a.GetNumberOfNeighbours() > 0)
            {
                Random rand = new Random();
                Whereabout neighbour = a.GetNeighbour(rand.nextInt(a.GetNumberOfNeighbours()));
                Iterator it = asteroids.entrySet().iterator();
                while(it.hasNext())
                {
                    Map.Entry pair = (Map.Entry) it.next();
                    if(pair.getValue() == neighbour) Move(id, (String)pair.getKey());
                }
                it = teleports.entrySet().iterator();
                while(it.hasNext())
                {
                    Map.Entry pair = (Map.Entry) it.next();
                    if(pair.getValue() == neighbour) Move(id, (String)pair.getKey());
                }
            }
            else Mine(id);
        }
    }

    /**
     *
     * @param id
     */
    public void TeleportDoPhase(String id){}

    /**
     *
     * @param r
     */
    public void AddRobot(Robot r){}

    /**
     *
     * @param t
     */
    public void AddTeleport(Teleport t){}

    /**
     *
     * @param s
     */
    public void SettlerDie(Settler s){}

    /**
     *
     * @param r
     */
    public void RobotDie(Robot r){}

    /**
     *
     * @param u
     */
    public void UfoDie(Ufo u){}

    /**
     *
     * @param a
     */
    public void AsteroidExplode(Asteroid a){}

    /**
     *
     * @param t
     */
    public void TeleportExplode(Teleport t){}

    /**
     *
     * @param id
     */
    public void Drill(String id){}

    /**
     *
     * @param id
     */
    public void Mine(String id){}

    /**
     *
     * @param id
     * @param param
     */
    public void Craft(String id, String param){}

    /**
     *
     * @param id
     * @param settlerid
     */
    public void Place(String id, String settlerid){}

    /**
     *
     * @param id
     * @param where
     */
    public void Move(String id, String where){}

    /**
     *
     * @param asteroidid
     */
    public void SunStorm(String asteroidid){}

    /**
     *
     */
    public void SunStorm(){}

    /**
     *
     */
    public void Rearrange(){}

    /**
     *
     * @param v
     */
    public void Endgame(boolean v){}

    /**
     * Megnezi, hogy a parameterul kapott telepes aszteroidajan tartozkodo telepesek birtokolnak-e eleg nyersanyagot
     * a jatek megnyeresehez. Ha nem, nem csinal semmit, ha igen, akkor lezarja a jatekot.
     * @param s - A telepes, akinek az aszteroidajan futtatjuk az ellenorzest.
     */
    public void CheckVictory(Settler s){
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
    public void CheckMaterials(){
        int countCoal = coal.size();
        int countIce = ice.size();
        int countIron = iron.size();
        int countUranium = uran.size();

        if (countCoal >= 3 && countIce >= 3 && countIron >= 3 && countUranium >= 3)
            return;
        else
            Endgame(false);
    }

    /**
     *
     * @param c
     */
    public void CoalDisintegrate(Coal c){}

    /**
     *
     * @param i
     */
    public void IceDisintegrate(Ice i){}

    /**
     *
     * @param i
     */
    public void IronDisintegrate(Iron i){}

    /**
     *
     * @param u
     */
    public void UraniumDisintegrate(Uranium u){}
}
