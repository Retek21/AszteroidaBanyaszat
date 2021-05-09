package game.userinterface;

import game.controller.Controller;
import game.logic.*;
import game.logic.Robot;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayManager extends JPanel {
    private static DisplayManager instance = null;
    private ArrayList<UfoDisplay> ufoDisplays;
    private ArrayList<RobotDisplay> robotDisplays;
    private ArrayList<SettlerDisplay> settlerDisplays;
    private ArrayList<TeleportDisplay> teleportDisplays;
    private ArrayList<AsteroidDisplay> asteroidDisplays;
    private ArrayList<Display> clearpuffer;
    private SunDisplay sunDisplay;
    private int blinkingtime;
    private int rows;
    private int columns;
    private int numberOfAsteroids;
    private boolean[][] AllocatedAsteroidSectors;

    private DisplayManager() {
        super();
        Init();

    }

    public void Init() {
        setBackground(new Color(50, 56, 65));
        setBorder(BorderFactory.createLineBorder(Color.black));
        // setPreferredSize(new Dimension(1022, 623));

        //initialize variables
        blinkingtime = 8;
        ufoDisplays = new ArrayList<UfoDisplay>();
        robotDisplays = new ArrayList<RobotDisplay>();
        settlerDisplays = new ArrayList<SettlerDisplay>();
        teleportDisplays = new ArrayList<TeleportDisplay>();
        asteroidDisplays = new ArrayList<AsteroidDisplay>();
        clearpuffer = new ArrayList<Display>();

        rows = 8;
        columns = 6;
        numberOfAsteroids = 36;
        AllocatedAsteroidSectors = new boolean[][]{
                //real
                {true, false, false, true, true, true},
                {true, true, true, true, true, true},
                {true, true, false, false, true, true},
                {true, true, false, false, false, true},
                {true, true, false, false, true, true},
                {true, true, false, true, true, false},
                {true, false, true, true, true, true},
                {true, true, true, true, true, true}
        };
    }

    public static DisplayManager GetInstance(){
        if(instance==null)
            instance=new DisplayManager();
        return instance;
    }
  
   public boolean[][] GetAllocatedAsteroidSectors() {
        return AllocatedAsteroidSectors;
    }

    public int GetRows() {return rows;}
    public int GetColumns() {return columns;}
    public int GetBlinkingTime() {return blinkingtime;}

    public void Test() {

        Asteroid[] asteroids = new Asteroid[numberOfAsteroids];
        for (int i = 0; i < numberOfAsteroids; i++) {
            asteroids[i] = new Asteroid();
        }
        CreateAsteroidfieldDisplay(asteroids);

        Teleport[] teleports = new Teleport[9];
        for (int i = 0; i < 9; i++) {
            teleports[i] = new Teleport();
            System.out.println(teleports[i].Deploy(asteroids[0]));
            CreateTeleportDisplay(teleports[i]);
        }

        Settler[] settlers = new Settler[16];
        for (int i = 0; i < 16; i++) {
            settlers[i] = new Settler();
            settlers[i].SetAsteroid(asteroids[0]);
            CreateSettlerDisplay(settlers[i]);
        }
    }

    //CREATE
    public void CreateAsteroidfieldDisplay(Asteroid[] af) {
        System.out.println(getWidth() + " " + getHeight());
        for (int i = 0; i < af.length; i++) {
            boolean found = false;
            for (int k = 0; k < rows; k++) {
                for (int j = 0; j < columns; j++) {
                    if (AllocatedAsteroidSectors[k][j]) {
                        AllocatedAsteroidSectors[k][j] = false;
                        int x = k;
                        int y = j;
                        x = (x * getWidth()) / rows;
                        y = (y * getHeight()) / columns;
                        AsteroidDisplay ad = new AsteroidDisplay(af[i], x, y);
                        asteroidDisplays.add(ad);
                        found = true;
                        break;
                    }
                }
                if (found)
                    break;
            }
        }
        sunDisplay = new SunDisplay(getWidth()/2 -75, getHeight()/2 - 75);
    }

    public void CreateTeleportDisplay(Teleport t) {
        TeleportDisplay td = new TeleportDisplay(t);
        teleportDisplays.add(td);
    }

    public void CreateSettlerDisplay(Settler s) {
        SettlerDisplay sd = new SettlerDisplay(s);
        settlerDisplays.add(sd);
    }

    public void CreateRobotDisplay(Robot r) {
        RobotDisplay rd = new RobotDisplay(r);
        robotDisplays.add(rd);
    }

    public void CreateUfoDisplay(Ufo u) {
        UfoDisplay ud = new UfoDisplay(u);
        ufoDisplays.add(ud);
    }

    //REMOVE
    public void RemoveSettlerDisplay(SettlerDisplay sd) {
        settlerDisplays.remove(sd);
    }

    public void RemoveRobotDisplay(RobotDisplay rd) {
        robotDisplays.remove(rd);
    }

    public void RemoveUfoDisplay(UfoDisplay ud) {
        ufoDisplays.remove(ud);
    }

    public void RemoveTeleportDisplay(TeleportDisplay td) {
        teleportDisplays.remove(td);
    }

    public void RemoveAsteroidDisplay(AsteroidDisplay ad) {
        asteroidDisplays.remove(ad);
    }

    public void ClickOnGamePanel(int x, int y) {
        ArrayList<Display> allDisplays = getDisplayArrayList();
        for (AsteroidDisplay ad : asteroidDisplays) {
            boolean pointInWhereabout = ad.PointInArea(x, y);
            if (pointInWhereabout) {
                boolean pointInEntity = false;
                boolean pointInTeleport = false;
                for (SettlerDisplay sd : settlerDisplays) {
                    pointInEntity = sd.PointInArea(x, y);
                    if (pointInEntity) {
                        sd.SetSelected(true);
                        allDisplays.remove(sd);
                        for (Display d : allDisplays) {
                            d.SetSelected(false);
                        }
                        Settler s = (Settler) sd.GetSubject();
                        Controller.GetInstanceOf().InfoAboutSettler(s);
                        break;
                    }
                }
                if (!pointInEntity) {
                    for (RobotDisplay rd : robotDisplays) {
                        pointInEntity = rd.PointInArea(x, y);
                        if (pointInEntity) {
                            rd.SetSelected(true);
                            allDisplays.remove(rd);
                            for (Display d : allDisplays) {
                                d.SetSelected(false);
                            }
                            Controller.GetInstanceOf().InfoAboutRobot((Robot) rd.GetSubject());
                            break;
                        }
                    }
                }
                if (!pointInEntity) {
                    for (UfoDisplay ud : ufoDisplays) {
                        pointInEntity = ud.PointInArea(x, y);
                        if (pointInEntity) {
                            ud.SetSelected(true);
                            allDisplays.remove(ud);
                            for (Display d : allDisplays) {
                                d.SetSelected(false);
                            }
                            Controller.GetInstanceOf().InfoAboutUfo((Ufo) ud.GetSubject());
                            break;
                        }
                    }
                }
                if (!pointInEntity) {
                    for (TeleportDisplay td : teleportDisplays) {
                        pointInTeleport = td.PointInArea(x, y);
                        if (pointInTeleport) {
                            td.SetSelected(true);
                            allDisplays.remove(td);
                            allDisplays.remove(td.GetSubject().GetPair().GetDisplay());
                            for (Display d : allDisplays) {
                                d.SetSelected(false);
                            }
                            Controller.GetInstanceOf().InfoAboutTeleport(td.GetSubject());
                            break;
                        }
                    }
                }
                if (!pointInEntity && !pointInTeleport) {
                    ad.SetSelected(true);
                    allDisplays.remove(ad);
                    for (Display astd : allDisplays) {
                        astd.SetSelected(false);
                    }
                    Controller.GetInstanceOf().InfoAboutAsteroid(ad.GetSubject());
                    break;
                }
            }
        }
    }
    public void ManageRoundOutlines(Display d){
        ArrayList<Display> allDisplays = getDisplayArrayList();
        if(d != null) {
            allDisplays.remove(d);
            d.SetRoundoutline(true);
        }
        for(Display display: allDisplays){
            display.SetRoundoutline(false);
        }
    }

    public void RefreshSelectedDisplay(){
        for(TeleportDisplay d : teleportDisplays){
            if(d.IsSelected()) {
                Controller.GetInstanceOf().InfoAboutTeleport(d.GetSubject());
                return;
            }
        }
        for(SettlerDisplay d : settlerDisplays){
            if(d.IsSelected()) {
                Controller.GetInstanceOf().InfoAboutSettler((Settler) d.GetSubject());
                return;
            }
        }
        for(RobotDisplay d : robotDisplays){
            if(d.IsSelected()) {
                Controller.GetInstanceOf().InfoAboutRobot((Robot) d.GetSubject());
                return;
            }
        }
        for(UfoDisplay d : ufoDisplays){
            if(d.IsSelected()) {
                Controller.GetInstanceOf().InfoAboutUfo((Ufo) d.GetSubject());
                return;
            }
        }
        for(AsteroidDisplay d : asteroidDisplays){
            if(d.IsSelected()) {
                Controller.GetInstanceOf().InfoAboutAsteroid(d.GetSubject());
                return;
            }
        }
    }

    public void SetNeigbhourHood(){
        for(SettlerDisplay d: settlerDisplays){
            if(d.IsRoundoutline()){
                AsteroidDisplay ad = (AsteroidDisplay) d.GetSubject().GetAsteroid().GetDisplay();
                ad.NotifyNeighbourhood();
            }
        }
    }
    private ArrayList<Display> getDisplayArrayList() {
        ArrayList<Display> allDisplays = new ArrayList<>();
        allDisplays.addAll(asteroidDisplays);
        allDisplays.addAll(settlerDisplays);
        allDisplays.addAll(ufoDisplays);
        allDisplays.addAll(teleportDisplays);
        allDisplays.addAll(robotDisplays);
        return allDisplays;
    }

    public void ClickOnMoveTarget(int x, int y) {
        for (TeleportDisplay td : teleportDisplays) {
            boolean pointInWherebout = td.PointInArea(x, y);
            if (pointInWherebout) {
                Controller.GetInstanceOf().SettlerMove(td.GetSubject());
                for (AsteroidDisplay asteroidDisplayd : asteroidDisplays) {
                    asteroidDisplayd.SetisNeigbhour(false);
                }
                for (TeleportDisplay teleportDisplayd : teleportDisplays) {
                    teleportDisplayd.SetisNeigbhour(false);
                }
                return;
            }
        }

        for (AsteroidDisplay ad : asteroidDisplays) {
            boolean pointInWherebout = ad.PointInArea(x, y);
            if (pointInWherebout) {
                Whereabout a = ad.GetSubject();
                Controller.GetInstanceOf().SettlerMove(a);
                for (AsteroidDisplay asteroidDisplayd : asteroidDisplays) {
                    asteroidDisplayd.SetisNeigbhour(false);
                }
                for (TeleportDisplay teleportDisplayd : teleportDisplays) {
                    teleportDisplayd.SetisNeigbhour(false);
                }
                return;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sunDisplay != null) {
            sunDisplay.Paint(g);
            for (AsteroidDisplay ad : asteroidDisplays)
                ad.Paint(g);
            for (TeleportDisplay td : teleportDisplays)
                td.Paint(g);
            for (SettlerDisplay sd : settlerDisplays)
                sd.Paint(g);
            for (RobotDisplay rd : robotDisplays)
                rd.Paint(g);
            for (UfoDisplay ud : ufoDisplays)
                ud.Paint(g);
        }
    }

    public void DrawDisplays() {
        repaint();
    }

    public void BlinkWhereabouts() {
        ArrayList<WhereaboutDisplay> displays = new ArrayList<WhereaboutDisplay>();
        displays.addAll(asteroidDisplays);
        displays.addAll(teleportDisplays);
        for(WhereaboutDisplay wd : displays)
            wd.Blink();
    }

    public void AddToClearPuffer(Display d) {
        clearpuffer.add(d);
    }

    public void ClearClearPuffer() {
        for(Display d : clearpuffer)
            d.Clear();
    }


    private Display activedisplay;

    public void RoundStart(Display display) {
        activedisplay.SetRoundoutline(false);
        activedisplay = display;
        display.SetRoundoutline(true);
    }

}