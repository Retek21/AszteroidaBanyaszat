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
                    Settler s = new Settler(this);
                    settlers.put(param[2], s);
                    break;
                case "robot":
                    Robot r = new Robot(this);
                    robots.put(param[2], r);
                    break;
                case "ufo":
                    Ufo u = new Ufo(this);
                    ufos.put(param[2], u);
                    break;
                case "asteroid":
                    Asteroid a = new Asteroid(this);
                    asteroids.put(param[2], a);
                    break;
                case "teleport":
                    Teleport t= new Teleport(this);
                    teleports.put(param[2], t);
                    break;
                case "coal":
                    Coal c = new Coal(this);
                    coal.put(param[2], c);
                    break;
                case "ice":
                    Ice i = new Ice(this);
                    ice.put(param[2], i);
                    break;
                case "iron":
                    Iron ir = new Iron(this);
                    iron.put(param[2], ir);
                    break;
                case "uranium":
                    Uranium ur = new Uranium(this);
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
                iron.get(param[1]).SetInteractCount(Integer.parseInt(param[2]));
            else if(ice.containsKey(param[1]))
                ice.get(param[1]).SetInteractCount(Integer.parseInt(param[2]));
            else if(coal.containsKey(param[1]))
                coal.get(param[1]).SetInteractCount(Integer.parseInt(param[2]));
            else if(uran.containsKey(param[1]))
                uran.get(param[1]).SetInteractCount(Integer.parseInt(param[2]));
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
            if(end) break;
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
                    case "craft":
                        Craft((String)pair.getKey(), cmd);
                        break;
                    case "move":
                        Move((String)pair.getKey(), cmd[1]);
                        break;
                    case "place":
                        Move(cmd[1], (String)pair.getKey());
                        break;
                    case "end":
                        Endgame(false);
                        break;
                    case "step":
                        break;
                    default:
                        break;
                }
                if(checkconditions)
                {
                    CheckMaterials();
                    CheckVictory((String)pair.getKey());
                }
            }

            if(end) break;
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
                        case "end":
                            Endgame(false);
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
                if(checkconditions) CheckMaterials();
            }

            if(end) break;
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
                        case "end":
                            Endgame(false);
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
                if(checkconditions) CheckMaterials();
            }

            if(end) break;
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
                        case "end":
                            Endgame(false);
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
                if(end) break;
                cmd = scanner.next().split(" ");
                switch (cmd[0])
                {
                    case "step":
                        break;
                    case "sunstorm":
                        SunStorm(cmd[1]);
                        break;
                    case "end":
                        Endgame(false);
                        break;
                    default:
                        break;
                }

                if(end) break;
                cmd = scanner.next().split(" ");
                switch (cmd[0])
                {
                    case "step":
                        break;
                    case "rearrange":
                        Rearrange();
                        break;
                    default:
                        break;
                }
            }
            else
            {
                if(end) break;
                SunStorm();
                Rearrange();
            }
            if(checkconditions) CheckMaterials();
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
            Ufo u = ufos.get(id);
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
    public void TeleportDoPhase(String id)
    {
        if(teleports.containsKey(id))
        {
            Teleport t = teleports.get(id);
            Asteroid a = t.GetAsteroid();
            if(a.GetNumberOfNeighbours() > 0)
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
     * @param r
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
    }

    /**
     *
     * @param t1
     * @param t2
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
    }

    /**
     *
     * @param s
     */
    public void SettlerDie(Settler s)
    {
        Iterator it = settlers.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == s)
                settlers.remove(pair.getKey());
        }
    }

    /**
     *
     * @param r
     */
    public void RobotDie(Robot r)
    {
        Iterator it = robots.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == r)
                robots.remove(pair.getKey());
        }
    }

    /**
     *
     * @param u
     */
    public void UfoDie(Ufo u)
    {
        Iterator it = ufos.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == u)
                ufos.remove(pair.getKey());
        }
    }

    /**
     *
     * @param a
     */
    public void AsteroidExplode(Asteroid a)
    {
        Iterator it = asteroids.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == a)
                asteroids.remove(pair.getKey());
        }
    }

    /**
     *
     * @param t
     */
    public void TeleportExplode(Teleport t)
    {
        Iterator it = teleports.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == t)
                teleports.remove(pair.getKey());
        }
    }

    /**
     *
     * @param id
     */
    public void Drill(String id)
    {
        if(settlers.containsKey(id))
        {
            settlers.get(id).GetAsteroid().ThinLayer();
        }
        else if(robots.containsKey(id))
        {
            robots.get(id).GetAsteroid().ThinLayer();
        }
    }

    /**
     *
     * @param id
     */
    public void Mine(String id)
    {
        if(settlers.containsKey(id))
        {
            settlers.get(id).Mine();
        }
        else if(ufos.containsKey(id))
        {
            ufos.get(id).Mine();
        }
    }

    /**
     *
     * @param id
     * @param param
     */
    public void Craft(String id, String[] param)
    {
        if(param[1] == "robot")
            settlers.get(id).CraftRobot();
        if(param[1] == "teleport")
            settlers.get(id).CraftTeleport();
    }

    /**
     *
     * @param id
     * @param settlerid
     */
    public void Place(String id, String settlerid)
    {
        if(coal.containsKey(id)) settlers.get(settlerid).PlaceMaterial(coal.get(id));
        else if(iron.containsKey(id)) settlers.get(settlerid).PlaceMaterial(iron.get(id));
        else if(ice.containsKey(id)) settlers.get(settlerid).PlaceMaterial(ice.get(id));
        else if(uran.containsKey(id)) settlers.get(settlerid).PlaceMaterial(uran.get(id));
        else if(teleports.containsKey(id)) settlers.get(settlerid).PlaceTeleport(teleports.get(id));
    }

    /**
     *
     * @param id
     * @param where
     */
    public void Move(String id, String where)
    {
        Whereabout w;
        if(asteroids.containsKey(where)) w = asteroids.get(where);
        else if(teleports.containsKey(where)) w = teleports.get(where);

        if(settlers.containsKey(id)) settlers.get(id).Move(w);
        else if(robots.containsKey(id)) robots.get(id).Move(w);
        else if(teleports.containsKey(id)) teleports.get(id).Move(w);
        else if(ufos.containsKey(id)) ufos.get(id).Move(w);
    }

    /**
     *
     * @param asteroidid
     */
    public void SunStorm(String asteroidid)
    {
        if(asteroids.containsKey(asteroidid))
            sun.Sunstorm(asteroids.get(asteroidid));
    }

    /**
     *
     */
    public void SunStorm()
    {
        sun.Sunstorm();
    }

    /**
     *
     */
    public void Rearrange()
    {
        Scanner scanner = new Scanner(System.in);
        Iterator it = asteroids.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            String in = scanner.next();
            if (in == "true")   ((Asteroid)pair.getValue()).SetSunnearness(true);
            else if(in == "false")  ((Asteroid)pair.getValue()).SetSunnearness(false);
        }
    }

    /**
     *
     * @param v
     */
    public void Endgame(boolean v)
    {
        end = true;
        victory = v;
    }

    /**
     *
     */
    public void CheckVictory()
    {

    }

    /**
     *
     */
    public void CheckMaterials()
    {}

    /**
     *
     * @param c
     */
    public void CoalDisinegrate(Coal c)
    {
        Iterator it = coal.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == c)
                coal.remove(pair.getKey());
        }
    }

    /**
     *
     * @param i
     */
    public void IceDisinegrate(Ice i)
    {
        Iterator it = ice.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() ==i)
                ice.remove(pair.getKey());
        }
    }

    /**
     *
     * @param i
     */
    public void IronDisinegrate(Iron i)
    {
        Iterator it = iron.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == i)
                iron.remove(pair.getKey());
        }
    }

    /**
     *
     * @param u
     */
    public void UraniumDisinegrate(Uranium u)
    {
        Iterator it = uran.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == u)
                uran.remove(pair.getKey());
        }
    }
}
