package game.controller;

import game.logic.*;

import java.io.*;
import java.util.*;


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
    private ArrayList<String> output = new ArrayList<String>();

    /**
     *
     */
    private static Controller instance;

    /**
     *
     * @return
     */
    public static Controller GetInstanceOf()
    {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    /**
     *
     */
    private Controller() {}

    public String SearchForAsteroid(Asteroid a) {
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

    public String SearchForTeleport(Teleport t) {
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

    public String SearchForSettler(Settler s) {
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

    public String SearchForRobot(Robot r) {
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

    public String SearchForUfo(Ufo u) {
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

    public String SearchForCoal(Material m) {
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

    public String SearchForIce(Material m) {
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

    public String SearchForIron(Material m) {
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

    public String SearchForUranium(Material m) {
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
     *
     */
    public void StartInitPhase()
    {
        String out = "[BUILDING GAME]";
        System.out.println(out);
        output.add(out);

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
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param param
     */
    public void SetSunnearness(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
            switch (param[2])
            {
                case "true":
                    if(asteroids.containsKey(param[1])) {
                        asteroids.get(param[1]).SetSunnearness(true);
                        out = "Asteroid: " + param[1] + " sunnearness set to true.";
                    }
                    break;
                case "false":
                    if(asteroids.containsKey(param[1])) {
                        asteroids.get(param[1]).SetSunnearness(false);
                        out = "Asteroid: " + param[1] + " sunnearness set to false.";
                    }
                    break;
                default:
                    break;
            }

        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param param
     */
    public void SetCore(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
        {
            if(iron.containsKey(param[2])) {
                asteroids.get(param[1]).AddMaterial(iron.get(param[2]));
                out = "Iron: " + param[2] + " added to Asteroid: " + param[1] + ".";
            }
            else if(ice.containsKey(param[2])) {
                asteroids.get(param[1]).AddMaterial(ice.get(param[2]));
                out = "Ice: " + param[2] + " added to Asteroid: " + param[1] + ".";
            }
            else if(coal.containsKey(param[2])) {
                asteroids.get(param[1]).AddMaterial(coal.get(param[2]));
                out = "Coal: " + param[2] + " added to Asteroid: " + param[1] + ".";
            }
            else if(uran.containsKey(param[2])) {
                asteroids.get(param[1]).AddMaterial(uran.get(param[2]));
                out = "Uranium: " + param[2] + " added to Asteroid: " + param[1] + ".";
            }
        }

        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param param
     */
    public void SetLayers(String[] param)
    {
        String out = "Invalid parameters.";

        if(param.length == 3)
        {
            if(asteroids.containsKey(param[1])) {
                asteroids.get(param[1]).SetLayer(Integer.parseInt(param[2]));
                out = "Asteroid: " + param[1] + " layers set to " + param[2] + ".";
            }
        }

        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param param
     */
    public void SetNeighbour(String[] param)
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
                    asteroids.get(param[1]).AddNeighbour(teleports.get(param[2]));
                    teleports.get(param[2]).Deploy(asteroids.get(param[1]));
                    out = "Asteroid: " + param[1] + " and Teleport: " + param[2] + " became neighbours.";
                }
            }
            else if(teleports.containsKey(param[1]) && asteroids.containsKey(param[2]))
            {
                teleports.get(param[1]).Deploy(asteroids.get(param[2]));
                asteroids.get(param[2]).AddNeighbour(asteroids.get(param[1]));
                out = "Teleport: " + param[1] + " and Asteroid: " + param[2] + " became neighbours.";
            }
        }

        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param param
     */
    public void Make(String[] param)
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
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param param
     */
    public void AddInventory(String[] param)
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
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param param
     */
    public void SetPair(String[] param)
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
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param param
     */
    public void SetInteractionCount(String[] param)
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

        System.out.println(out);
        output.add(out);
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
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param buildinput
     */
    public void StartInitPhaseFromFile(String buildinput)
    {
        String out = "[BUILDING GAME]";
        System.out.println(out);
        output.add(out);

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

        out = "[BUILDING ENDED]";
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param man
     * @param conditions
     */
    public void StartGamePhase(boolean man, boolean conditions)
    {
        String out = "[STARTING GAME]";
        System.out.println(out);
        output.add(out);

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
                out = "[ROUND OF SETTLER: " + pair.getKey() + "]";
                output.add(out);
                System.out.println(out);
                cmd = scanner.nextLine().split(" ");
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
                        Place(cmd[1], (String)pair.getKey());
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
                    CheckVictory((Settler)pair.getValue());
                }
            }

            if(end) break;
            it = robots.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                out = "[ROUND OF ROBOT: " + pair.getKey() + "]";
                output.add(out);
                System.out.println(out);
                if(manual) {
                    cmd = scanner.nextLine().split(" ");
                    switch (cmd[0]) {
                        case "drill":
                            Drill((String)pair.getKey());
                            break;
                        case "move":
                            Move((String)pair.getKey(), cmd[1]);
                            break;
                        case "step":
                            Step();
                            break;
                        case "endgame":
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
                out = "[ROUND OF UFO: " + pair.getKey() + "]";
                output.add(out);
                System.out.println(out);
                if(manual) {
                    cmd = scanner.nextLine().split(" ");
                    switch (cmd[0]) {
                        case "mine":
                            Mine((String)pair.getKey());
                            break;
                        case "move":
                            Move((String)pair.getKey(), cmd[1]);
                            break;
                        case "step":
                            Step();
                            break;
                        case "endgame":
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
                out = "[ROUND OF TELEPORT: " + pair.getKey() + "]";
                output.add(out);
                System.out.println(out);
                if(((Teleport)pair.getValue()).GetCraziness())
                {
                    if(manual ) {
                        cmd = scanner.nextLine().split(" ");
                        switch (cmd[0]) {
                            case "move":
                                Move((String)pair.getKey(), cmd[1]);
                                break;
                            case "step":
                                Step();
                                break;
                            case "endgame":
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
            }

            if(manual)
            {
                if(end) break;
                out = "[ROUND OF SUN]";
                output.add(out);
                System.out.println(out);
                cmd = scanner.nextLine().split(" ");
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

                if(end) break;
                out = "[ROUND OF ASTEROIDFIELD]";
                output.add(out);
                System.out.println(out);
                cmd = scanner.nextLine().split(" ");
                switch (cmd[0])
                {
                    case "step":
                        Step();
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

        out = "[GAME OVER]";
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param buildinput
     * @param man
     * @param conditions
     */
    public void StartGamePhaseFromFile(String buildinput, boolean man, boolean conditions)
    {
        try {
            manual = man;
            checkconditions = conditions;
            BufferedReader br = new BufferedReader(new FileReader(new File(buildinput)));
            ArrayList<String> in = new ArrayList<String>();
            String temp;
            while((temp = br.readLine()) != null)
                in.add(temp);
            br.close();

            Iterator cmdit = in.iterator();

            String[] cmd;
            while (!end) {
                if (end) break;
                Iterator it = settlers.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    cmd = ((String)cmdit.next()).split(" ");
                    switch (cmd[0]) {
                        case "drill":
                            Drill((String) pair.getKey());
                            break;
                        case "mine":
                            Mine((String) pair.getKey());
                            break;
                        case "craft":
                            Craft((String) pair.getKey(), cmd);
                            break;
                        case "move":
                            Move((String) pair.getKey(), cmd[1]);
                            break;
                        case "place":
                            Move(cmd[1], (String) pair.getKey());
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
                    if (checkconditions) {
                        CheckMaterials();
                        CheckVictory((Settler) pair.getValue());
                    }
                }

                if (end) break;
                it = robots.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (manual) {
                        cmd = ((String)cmdit.next()).split(" ");
                        switch (cmd[0]) {
                            case "drill":
                                Drill((String) pair.getKey());
                                break;
                            case "move":
                                Move((String) pair.getKey(), cmd[1]);
                                break;
                            case "step":
                                Step();
                                break;
                            case "endgame":
                                Endgame(false);
                                break;
                            case "dophase":
                                RobotDoPhase((String) pair.getKey());
                                break;
                            default:
                                break;
                        }
                    } else {
                        RobotDoPhase((String) pair.getKey());
                    }
                    if (checkconditions) CheckMaterials();
                }

                if (end) break;
                it = ufos.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (manual) {
                        cmd = ((String)cmdit.next()).split(" ");
                        switch (cmd[0]) {
                            case "mine":
                                Mine((String) pair.getKey());
                                break;
                            case "move":
                                Move((String) pair.getKey(), cmd[1]);
                                break;
                            case "step":
                                Step();
                                break;
                            case "endgame":
                                Endgame(false);
                                break;
                            case "dophase":
                                UfoDoPhase((String) pair.getKey());
                                break;
                            default:
                                break;
                        }
                    } else {
                        UfoDoPhase((String) pair.getKey());
                    }
                    if (checkconditions) CheckMaterials();
                }

                if (end) break;
                it = teleports.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (manual) {
                        cmd = ((String)cmdit.next()).split(" ");
                        switch (cmd[0]) {
                            case "move":
                                Move((String) pair.getKey(), cmd[1]);
                                break;
                            case "step":
                                Step();
                                break;
                            case "endgame":
                                Endgame(false);
                                break;
                            case "dophase":
                                TeleportDoPhase((String) pair.getKey());
                                break;
                            default:
                                break;
                        }
                    } else {
                        TeleportDoPhase((String) pair.getKey());
                    }
                }

                if (manual) {
                    if (end) break;
                    cmd = ((String)cmdit.next()).split(" ");
                    switch (cmd[0]) {
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

                    if (end) break;
                    cmd = ((String)cmdit.next()).split(" ");
                    switch (cmd[0]) {
                        case "step":
                            Step();
                            break;
                        case "rearrange":
                            Rearrange();
                            break;
                        default:
                            break;
                    }
                } else {
                    if (end) break;
                    SunStorm();
                    Rearrange();
                }
                if (checkconditions) CheckMaterials();
            }
        }
        catch(IOException e)
        {
            System.out.println("I/O Exception");
        }
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
            if((a.GetLayer() > 0 || a.IsEmpty()) && a.GetNumberOfNeighbours() > 0)
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
        String out = output.get(output.size()-1) +  "Robot: robot" + Integer.toString(n) + ".";
        System.out.println(out);
        output.remove(output.size()-1);
        output.add(out);
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
        String out = output.get(output.size()-1) +  "Teleport: teleport" + Integer.toString(n) + " and Teleport: teleport" + Integer.toString(n+1) + ".";
        System.out.println(out);
        output.remove(output.size()-1);
        output.add(out);
    }

    /**
     *
     * @param s
     */
    public void SettlerDie(Settler s)
    {
        String id = SearchForSettler(s);
        if (id != null) {
            settlers.remove(id);

            String out = "Settler: " + id + " died.";
            System.out.println(out);
            output.add(out);
        }
    }

    /**
     *
     * @param r
     */
    public void RobotDie(Robot r)
    {
        String id = SearchForRobot(r);
        if (id != null) {
            robots.remove(id);

            String out = "Robot: " + id + " died.";
            System.out.println(out);
            output.add(out);
        }
    }

    /**
     *
     * @param u
     */
    public void UfoDie(Ufo u)
    {
        String id = SearchForUfo(u);
        if (id != null) {
            ufos.remove(id);

            String out = "Ufo: " + id + " died.";
            System.out.println(out);
            output.add(out);
        }
    }

    /**
     *
     * @param a
     */
    public void AsteroidExplode(Asteroid a)
    {
        String id = SearchForAsteroid(a);
        if (id != null) 
        {
            teleports.remove(id);

            String out = "Asteroid: " + id + " exploded.";
            System.out.println(out);
            output.add(out);
        }
    }

    /**
     *
     * @param t
     */
    public void TeleportExplode(Teleport t)
    {
        String id = SearchForTeleport(t);
        if (id != null) 
        {
            teleports.remove(id);

            String out = "Teleport: " + id + " died.";
            System.out.println(out);
            output.add(out);
        }
    }

    /**
     *
     * @param id
     */
    public void Drill(String id)
    {
        String out = "Invalid parametes.";

        if(settlers.containsKey(id))
        {
            boolean success = settlers.get(id).Drill();
            if (!success)
                out = "Settler: " + id + " failed to drill.";
            else {
                Asteroid a = settlers.get(id).GetAsteroid();
                String asteroid_id = SearchForAsteroid(a);
                out = "Settler: " + id + " drilled Asteroid: " + asteroid_id + ".";
            }
        }
        else if(robots.containsKey(id))
        {
            boolean success = robots.get(id).Drill();
            if (!success)
                out = "Robot: " + id + " failed to drill.";
            else {
                Asteroid a = robots.get(id).GetAsteroid();
                String asteroid_id = SearchForAsteroid(a);
                out = "Robot: " + id + " drilled Asteroid: " + asteroid_id + ".";
            }
        }
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param id
     */
    public void Mine(String id)
    {
        String out = "Invalid parameters.";

        if(settlers.containsKey(id))
        {
            boolean success = settlers.get(id).Mine();
            if (!success)
                out = "Settler: " + id + " failed to mine.";
            else {
                Asteroid a = settlers.get(id).GetAsteroid();
                String asteroid_id = SearchForAsteroid(a);

                Material m = a.GetMaterial();
                String material_id = SearchForCoal(m);
                if (material_id != null) {
                    out = "Settler: " + id + " mined Asteroid: " + asteroid_id + " with Coal: " + material_id + ".";
                    System.out.println(out);
                    output.add(out);
                    return;
                }
                material_id = SearchForIce(m);
                if (material_id != null) {
                    out = "Settler: " + id + " mined Asteroid: " + asteroid_id + " with Ice: " + material_id + ".";
                    System.out.println(out);
                    output.add(out);
                    return;
                }
                material_id = SearchForIron(m);
                if (material_id != null) {
                    out = "Settler: " + id + " mined Asteroid: " + asteroid_id + " with Iron: " + material_id + ".";
                    System.out.println(out);
                    output.add(out);
                    return;
                }
                material_id = SearchForUranium(m);
                if (material_id != null) {
                    out = "Settler: " + id + " mined Asteroid: " + asteroid_id + " with Uranium: " + material_id + ".";
                    System.out.println(out);
                    output.add(out);
                    return;
                }
            }
        }
        else if(ufos.containsKey(id))
        {
            boolean success = ufos.get(id).Mine();
            if (!success)
                out = "Ufo: " + id + " failed to mine.";
            else {
                Asteroid a = ufos.get(id).GetAsteroid();
                String asteroid_id = SearchForAsteroid(a);

                Material m = a.GetMaterial();
                String material_id = SearchForCoal(m);
                if (material_id != null) {
                    out = "Ufo: " + id + " mined Asteroid: " + asteroid_id + " with Coal: " + material_id + ".";
                    System.out.println(out);
                    output.add(out);
                    return;
                }
                material_id = SearchForIce(m);
                if (material_id != null) {
                    out = "Ufo: " + id + " mined Asteroid: " + asteroid_id + " with Ice: " + material_id + ".";
                    System.out.println(out);
                    output.add(out);
                    return;
                }
                material_id = SearchForIron(m);
                if (material_id != null) {
                    out = "Ufo: " + id + " mined Asteroid: " + asteroid_id + " with Iron: " + material_id + ".";
                    System.out.println(out);
                    output.add(out);
                    return;
                }
                material_id = SearchForUranium(m);
                if (material_id != null) {
                    out = "Ufo: " + id + " mined Asteroid: " + asteroid_id + " with Uranium: " + material_id + ".";
                    System.out.println(out);
                    output.add(out);
                    return;
                }
            }
        }
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param id
     * @param param
     */
    public void Craft(String id, String[] param)
    {
        String out = "Invalid parameters.";
        if(param[1] == "robot")
        {
            out = "Settler: " + id + " crafted ";
            if(!settlers.get(id).CraftRobot())
                out = "Settler: " + id + " failed to craft.";
        }
        if(param[1] == "teleport")
        {
            out = "Settler: " + id + " crafted ";
            if(!settlers.get(id).CraftTeleport())
                out = "Settler: " + id + " failed to craft.";
        }
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param id
     * @param settlerid
     */
    public void Place(String id, String settlerid)
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

        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param id
     * @param where
     */
    public void Move(String id, String where)
    {
        String out = "Invalid parameters.";

        Whereabout w;
        if(asteroids.containsKey(where))
        {
            w = asteroids.get(where);
            if(settlers.containsKey(id)) {
                Asteroid current = settlers.get(id).GetAsteroid();
                String previous_asteroidid = SearchForAsteroid(current);
                boolean success = settlers.get(id).Move(w);
                if (!success)
                    out = "Settler: " + id + " failed to move.";
                else
                    out = "Settler: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + where + ".";
            }
            else if(robots.containsKey(id)) {
                Asteroid current = robots.get(id).GetAsteroid();
                String previous_asteroidid = SearchForAsteroid(current);
                boolean success = robots.get(id).Move(w);
                if (!success)
                    out = "Robot: " + id + " failed to move.";
                else
                    out = "Robot: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + where + ".";
            }
            else if(teleports.containsKey(id)) {
                Asteroid current = teleports.get(id).GetAsteroid();
                String previous_asteroidid = SearchForAsteroid(current);
                boolean success = teleports.get(id).Move(w);
                if (!success)
                    out = "Teleport: " + id + " failed to move.";
                else
                    out = "Teleport: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + where + ".";
            }
            else if(ufos.containsKey(id)) {
                Asteroid current = ufos.get(id).GetAsteroid();
                String previous_asteroidid = SearchForAsteroid(current);
                boolean success = ufos.get(id).Move(w);
                if (!success)
                    out = "Ufo: " + id + " failed to move.";
                else
                    out = "Ufo: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + where + ".";
            }
        }
        else if(teleports.containsKey(where))
        {
            Asteroid a = teleports.get(where).GetPair().GetAsteroid();
            String to = SearchForAsteroid(a);

            w = teleports.get(where);
            if(settlers.containsKey(id)) {
                Asteroid current = settlers.get(id).GetAsteroid();
                String previous_asteroidid = SearchForAsteroid(current);
                boolean success = settlers.get(id).Move(w);
                if (!success)
                    out = "Settler: " + id + " failed to move.";
                else
                    out = "Settler: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + to + ".";
            }
            else if(robots.containsKey(id)) {
                Asteroid current = robots.get(id).GetAsteroid();
                String previous_asteroidid = SearchForAsteroid(current);
                boolean success = robots.get(id).Move(w);
                if (!success)
                    out = "Robot: " + id + " failed to move.";
                else
                    out = "Robot: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + to + ".";
            }
            else if(teleports.containsKey(id)) {
                Asteroid current = teleports.get(id).GetAsteroid();
                String previous_asteroidid = SearchForAsteroid(current);
                boolean success = teleports.get(id).Move(w);
                if (!success)
                    out = "Teleport: " + id + " failed to move.";
                else
                    out = "Teleport: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + to + ".";
            }
            else if(ufos.containsKey(id)) {
                Asteroid current = ufos.get(id).GetAsteroid();
                String previous_asteroidid = SearchForAsteroid(current);
                boolean success = ufos.get(id).Move(w);
                if (!success)
                    out = "Ufo: " + id + " failed to move.";
                else
                    out = "Ufo: " + id + " moved from Asteroid: " + previous_asteroidid + " to Asteroid: " + to + ".";
            }
        }
        System.out.println(out);
        output.add(out);
    }

    /**
     *
     * @param asteroidid
     */
    public void SunStorm(String asteroidid)
    {
        if(asteroids.containsKey(asteroidid))
        {
            String out = "Sunstorm hits:\tAsteroid: " + asteroidid;
            Asteroid asteroid = asteroids.get(asteroidid);
            if(asteroid.GetNeighbours().size() > 0)
                out = out + ",";
            System.out.println(out);
            output.add(out);
            ArrayList<Whereabout> neighbours = asteroid.GetNeighbours();
            for(int i = 0; i < neighbours.size(); i++)
            {
                out = "\t\t\t";
                if(SearchForAsteroid((Asteroid)neighbours.get(i)) != null)
                    out = out + "Asteroid: " + SearchForAsteroid((Asteroid)neighbours.get(i));
                else if(SearchForTeleport((Teleport)neighbours.get(i)) != null)
                    out = out + "Asteroid: " + SearchForTeleport((Teleport)neighbours.get(i));
                if(i+1 < neighbours.size())
                    out = out + ",";
                System.out.println(out);
                output.add(out);
                neighbours.get(i).OnFire();
            }
            sun.Sunstorm(asteroid);
        }
    }

    /**
     *
     */
    public void SunStorm()
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
        ArrayList<Whereabout> neighbours = asteroid.GetNeighbours();
        if(asteroid != null)
        {
            String out = "Sunstorm hits:\tAsteroid: ";

            if(asteroid.GetNeighbours().size() > 0)
                out = out + SearchForAsteroid(asteroid) + ",";
            else
                out = out + SearchForAsteroid(asteroid);
            System.out.println(out);
            output.add(out);
            asteroid.OnFire();
            for (int i = 0; i < neighbours.size(); i++) {
                out = "\t\t";
                if(SearchForAsteroid((Asteroid)neighbours.get(i)) != null)
                    out = out + "Asteroid: " + SearchForAsteroid((Asteroid)neighbours.get(i));
                else if(SearchForTeleport((Teleport)neighbours.get(i)) != null)
                    out = out + "Asteroid: " + SearchForTeleport((Teleport)neighbours.get(i));
                if(i+1 < neighbours.size())
                    out = out + ",";
                System.out.println(out);
                output.add(out);
                neighbours.get(i).OnFire();
            }
        }
    }

    /**
     *
     */
    public void Rearrange()
    {
        String out = "Sunnearness:";
        Scanner scanner = new Scanner(System.in);
        Iterator it = asteroids.entrySet().iterator();
        if(!it.hasNext())
        {
            System.out.println(out);
            output.add(out);
        }
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            out = out + "Asteroid: " + pair.getValue() + " ";
            System.out.print(out);
            String in = scanner.nextLine();
            if (in == "true")
            {
                ((Asteroid) pair.getValue()).SetSunnearness(true);
                out = out + "(true)";
                if(it.hasNext())
                    out = out + ",";
            }
            else if(in == "false")
            {
                ((Asteroid)pair.getValue()).SetSunnearness(false);
                out = out + "(false)";
                if(it.hasNext())
                    out = out + ",";
            }
            output.add(out);
            out = "\t\t";
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

        String out;
        Iterator it = asteroids.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Asteroid a = (Asteroid) pair.getValue();


            out = "Asteroid: " + pair.getKey();
            System.out.println(out);
            output.add(out);


            out = "\tSunnearness: " + String.valueOf(a.GetSunnearness());
            System.out.println(out);
            output.add(out);


            out = "\tLayers: " + Integer.toString(a.GetLayer());
            System.out.println(out);
            output.add(out);


            out = "\tCore: ";
            if(SearchForCoal(a.GetMaterial()) != null) out = out + "Coal: " + SearchForCoal(a.GetMaterial());
            else if(SearchForIron(a.GetMaterial()) != null) out = out + "Iron: " + SearchForCoal(a.GetMaterial());
            else if(SearchForIce(a.GetMaterial()) != null) out = out + "Ice: " + SearchForCoal(a.GetMaterial());
            else if(SearchForUranium(a.GetMaterial()) != null) out = out + "Uranium: " + SearchForCoal(a.GetMaterial());
            else out = out + "null";
            System.out.println(out);
            output.add(out);


            ArrayList<Whereabout> neighbours = a.GetNeighbours();
            out = "\tNeighbours:\t";
            if(neighbours.size() < 1)
            {
                out = out + "null";
                System.out.println(out);
                output.add(out);
            }
            for (int i = 0; i < neighbours.size(); i++) {
                out = "\t\t";
                if(SearchForAsteroid((Asteroid)neighbours.get(i)) != null)
                    out = out + "Asteroid: " + SearchForAsteroid((Asteroid)neighbours.get(i));
                else if(SearchForTeleport((Teleport)neighbours.get(i)) != null)
                    out = out + "Asteroid: " + SearchForTeleport((Teleport)neighbours.get(i));
                if(i+1 < neighbours.size())
                    out = out + ",";
                System.out.println(out);
                output.add(out);
            }


            ArrayList<Entity> entities = a.GetEntities();
            out = "\tNeighbours:";
            if(entities.size() < 1)
            {
                out = out + "null";
                System.out.println(out);
                output.add(out);
            }
            for (int i = 0; i < neighbours.size(); i++) {
                out = "\t\t";
                if(SearchForSettler((Settler)entities.get(i)) != null)
                    out = out + "Settler: " + SearchForAsteroid((Asteroid)neighbours.get(i));
                else if(SearchForRobot((Robot)entities.get(i)) != null)
                    out = out + "Robot: " + SearchForTeleport((Teleport)neighbours.get(i));
                else if(SearchForUfo((Ufo) entities.get(i)) != null)
                    out = out + "Ufo: " + SearchForTeleport((Teleport)neighbours.get(i));
                if(i+1 < entities.size())
                    out = out + ",";
                System.out.println(out);
                output.add(out);
            }
        }

        it = teleports.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Teleport t = (Teleport)pair.getValue();
            out = "Teleport: " + pair.getKey();
            System.out.println(out);
            output.add(out);

            if(SearchForTeleport(t.GetPair()) != null)
                out = "\tPair: " + SearchForTeleport(t.GetPair());
            else
                out = "\tPair: null";
            System.out.println(out);
            output.add(out);

            if(t.GetPairReadyness())
                out = "\tState: active";
            else
                out = "\tState: inactive";
            System.out.println(out);
            output.add(out);

            if(t.GetAsteroid() != null)
                out = "\tPlace: Asteroid: " + SearchForAsteroid(t.GetAsteroid());
            else
                out = "\tPlace: null";
            System.out.println(out);
            output.add(out);
        }

        it = settlers.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Settler s = (Settler)pair.getValue();
            out = "Settler: " + pair.getKey();
            System.out.println(out);
            output.add(out);

            if(s.GetAsteroid() != null)
                out = "\tAsteroid: " + SearchForAsteroid(s.GetAsteroid());
            else
                out = "\tnull";
            System.out.println(out);
            output.add(out);

            out = "\tInventory:";
            System.out.println(out);
            output.add(out);

            out = "\t\tMaterials:";
            System.out.println(out);
            output.add(out);

            ArrayList<Teleport> ta = s.GetInventory().GetTeleports();
            ArrayList<Material> ma = s.GetInventory().GetMaterials();

            if(ma.size() == 0)
            {
                out = "\t\t\tnull" ;
                System.out.println(out);
                output.add(out);
            }
            for(int i = 0; i < ma.size(); i++)
            {
                if(SearchForCoal(ma.get(i)) != null) out = "\t\t\tCoal: " + SearchForCoal(ma.get(i));
                else if(SearchForIce(ma.get(i)) != null) out = "\t\t\tIce: " + SearchForIce(ma.get(i));
                else if(SearchForIron(ma.get(i)) != null) out = "\t\t\tIron: " + SearchForIron(ma.get(i));
                else if(SearchForUranium(ma.get(i)) != null) out = "\t\t\tUranium: " + SearchForUranium(ma.get(i));

                System.out.println(out);
                output.add(out);
            }

            out = "\t\tTeleports:";
            System.out.println(out);
            output.add(out);

            if(ta.size() == 0)
            {
                out = "\t\t\tnull" ;
                System.out.println(out);
                output.add(out);
            }
            for(int i = 0; i < ta.size(); i++)
            {
                if(SearchForTeleport(ta.get(i)) != null) out = "\t\t\tTeleport: " + SearchForTeleport(ta.get(i));

                System.out.println(out);
                output.add(out);
            }
        }

        it = robots.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Robot r = (Robot)pair.getValue();
            out = "Robot: " + pair.getKey();
            System.out.println(out);
            output.add(out);

            if(r.GetAsteroid() != null)
                out = "\tAsteroid: " + SearchForAsteroid(r.GetAsteroid());
            else
                out = "\tnull";
            System.out.println(out);
            output.add(out);
        }

        it = robots.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Robot r = (Robot)pair.getValue();
            out = "Robot: " + pair.getKey();
            System.out.println(out);
            output.add(out);

            if(r.GetAsteroid() != null)
                out = "\tAsteroid: " + SearchForAsteroid(r.GetAsteroid());
            else
                out = "\tnull";
            System.out.println(out);
            output.add(out);
        }

        it = ufos.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Ufo u = (Ufo)pair.getValue();
            out = "Ufo: " + pair.getKey();
            System.out.println(out);
            output.add(out);

            if(u.GetAsteroid() != null)
                out = "\tAsteroid: " + SearchForAsteroid(u.GetAsteroid());
            else
                out = "\tnull";
            System.out.println(out);
            output.add(out);
        }

        it = uran.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Uranium u = (Uranium)pair.getValue();
            out = "Uranium: " + pair.getKey();
            System.out.println(out);
            output.add(out);

            out = "\tInteractCount: " + Integer.toString(u.GetInteractCount());
            System.out.println(out);
            output.add(out);

            boolean found = false;
            Iterator it2 = settlers.entrySet().iterator();
            while(!it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (((Settler) pair2.getValue()).GetInventory().GetMaterials().contains(u)) {
                    found = true;
                    out = "\tSettler: " + pair2.getKey();
                }
            }
            it2 = asteroids.entrySet().iterator();
            while(!it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (SearchForUranium(((Asteroid)pair2.getValue()).GetMaterial()) == pair.getKey())
                {
                    found = true;
                    out = "\tAsteroid: " + pair2.getKey();
                }
            }
            if(!found)
                out = "\tnull";
            System.out.println(out);
            output.add(out);
        }

        it = ice.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Ice i = (Ice)pair.getValue();
            out = "Ice: " + pair.getKey();
            System.out.println(out);
            output.add(out);

            out = "\tInteractCount: " + Integer.toString(i.GetInteractCount());
            System.out.println(out);
            output.add(out);

            boolean found = false;
            Iterator it2 = settlers.entrySet().iterator();
            while(!it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (((Settler) pair2.getValue()).GetInventory().GetMaterials().contains(i)) {
                    found = true;
                    out = "\tSettler: " + pair2.getKey();
                }
            }
            it2 = asteroids.entrySet().iterator();
            while(!it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (SearchForUranium(((Asteroid)pair2.getValue()).GetMaterial()) == pair.getKey())
                {
                    found = true;
                    out = "\tAsteroid: " + pair2.getKey();
                }
            }
            if(!found)
                out = "\tnull";
            System.out.println(out);
            output.add(out);
        }

        it = iron.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Iron i = (Iron)pair.getValue();
            out = "Iron: " + pair.getKey();
            System.out.println(out);
            output.add(out);

            out = "\tInteractCount: " + Integer.toString(i.GetInteractCount());
            System.out.println(out);
            output.add(out);

            boolean found = false;
            Iterator it2 = settlers.entrySet().iterator();
            while(!it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (((Settler) pair2.getValue()).GetInventory().GetMaterials().contains(i)) {
                    found = true;
                    out = "\tSettler: " + pair2.getKey();
                }
            }
            it2 = asteroids.entrySet().iterator();
            while(!it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (SearchForUranium(((Asteroid)pair2.getValue()).GetMaterial()) == pair.getKey())
                {
                    found = true;
                    out = "\tAsteroid: " + pair2.getKey();
                }
            }
            if(!found)
                out = "\tnull";
            System.out.println(out);
            output.add(out);
        }

        it = coal.entrySet().iterator();
        while(!it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Coal c = (Coal)pair.getValue();
            out = "Coal: " + pair.getKey();
            System.out.println(out);
            output.add(out);

            out = "\tInteractCount: " + Integer.toString(c.GetInteractCount());
            System.out.println(out);
            output.add(out);

            boolean found = false;
            Iterator it2 = settlers.entrySet().iterator();
            while(!it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (((Settler) pair2.getValue()).GetInventory().GetMaterials().contains(c)) {
                    found = true;
                    out = "\tSettler: " + pair2.getKey();
                }
            }
            it2 = asteroids.entrySet().iterator();
            while(!it2.hasNext() && !found) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                if (SearchForUranium(((Asteroid)pair2.getValue()).GetMaterial()) == pair.getKey())
                {
                    found = true;
                    out = "\tAsteroid: " + pair2.getKey();
                }
            }
            if(!found)
                out = "\tnull";
            System.out.println(out);
            output.add(out);
        }

        try
        {
            PrintWriter textout = new PrintWriter("Output.txt", "UTF-8");
            for(int i = 0; i < output.size(); i++)
                textout.println(output.get(i));
            textout.close();
        }
        catch(IOException e)
        {
            System.out.println("I/O Exception");
        }
    }

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
    public void CoalDisintegrate(Coal c)
    {
        Iterator it = coal.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == c) {
                coal.remove(pair.getKey());

                String out = "Coal: " + pair.getKey() + " disintegrated.";
                System.out.println(out);
                output.add(out);
            }
        }
    }

    /**
     *
     * @param i
     */
    public void IceDisintegrate(Ice i)
    {
        Iterator it = ice.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() ==i) {
                ice.remove(pair.getKey());

                String out = "Ice: " + pair.getKey() + " disintegrated.";
                System.out.println(out);
                output.add(out);
            }
        }
    }

    /**
     *
     * @param i
     */
    public void IronDisintegrate(Iron i)
    {
        Iterator it = iron.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == i) {
                iron.remove(pair.getKey());

                String out = "Iron: " + pair.getKey() + " disintegrated.";
                System.out.println(out);
                output.add(out);
            }
        }
    }

    /**
     *
     * @param u
     */
    public void UraniumDisintegrate(Uranium u)
    {
        Iterator it = uran.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == u) {
                uran.remove(pair.getKey());

                String out = "Uranium: " + pair.getKey() + " disintegrated.";
                System.out.println(out);
                output.add(out);
            }
        }
    }


    public void Step() {
        String out = "Phase has been stepped.";
        System.out.println(out);
        output.add(out);
    }
}
