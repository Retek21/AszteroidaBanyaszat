package game.userinterface;
import game.controller.Controller;
import game.logic.*;
import game.logic.Robot;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Osztály, mely az egyes Displayeket kezeli, a kirajzolásukat meghívja, létrehozza,
 * illetve eltávolítja azokat.
 * Az egyes kattintások koordinátaáit is házzárendeli az adott objektumra, amjd ennek
 * egeflelően információt ad az adott objektumról.
 * Singleton osztály.
 * @author Dengyel Bendegúz
 * @author Szabó Gergő
 * @author Török Kristóf
 */
public class DisplayManager extends JPanel {
    /**
     * A Singelton megvalósítást szolgálja, egy DisplayManager tagváltozó,
     * mindenki által elérhető.
     */
    private static DisplayManager instance = null;

    /**
     * Az UfoDisplayeket tároló lista.
     */
    private ArrayList<UfoDisplay> ufoDisplays;

    /**
     * A RobotDisplayeket tároló lista.
     */
    private ArrayList<RobotDisplay> robotDisplays;

    /**
     * A SettlerDisplayeket tároló lista.
     */
    private ArrayList<SettlerDisplay> settlerDisplays;

    /**
     * A TeleportDisplayeket tároló lista.
     */
    private ArrayList<TeleportDisplay> teleportDisplays;

    /**
     * Az AsteroidDisplayeket tároló lista.
     */
    private ArrayList<AsteroidDisplay> asteroidDisplays;

    /**
     * A törlendő entitások listája.
     */
    private ArrayList<Display> clearpuffer;

    /**
     * A SunDisplay referenciája
     */
    private SunDisplay sunDisplay;

    /**
     * A villogás időtartama
     */
    private int blinkingtime;

    /**
     * Az aszteroida mezőt felépítő háló sorai.
     */
    private int rows;

    /**
     * Az aszteroida mezőt felépítő háló oszlopai.
     */
    private int columns;

    /**
     * Az aszteroida mezőt felépítő aszteroidák száma.
     */
    private int numberOfAsteroids;

    /**
     * Az aszteroida mezőt felépítő háló szektorai (2D tömb), értéke igaz,
     * ha található a szektorban aszteroida, hamis ha nem.
     */
    private boolean[][] AllocatedAsteroidSectors;

    /**
     * Privát konstruktor, a singleton osztályhoz szűkséges láthatósággal.
     * A az ős JPanel konstruktorát hívja, valamint az inicializáló metódust.
     */
    private DisplayManager() {
        super();
        Init();

    }

    /**
     * Inicializálja a változákat, és a játéktér dimenzióit beállítja.
     */
    public void Init() {
        /**
         * A játéktár dimenziói.
         */
        setBackground(new Color(50, 56, 65));
        setBorder(BorderFactory.createLineBorder(Color.black));

        /**
         * Tagváltozók inicializálása.
         */
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

        /**
         * Előre megépített játéktér.
         */
        AllocatedAsteroidSectors = new boolean[][]{
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

    /**
     * Az osztály egyetlen objektuma ezen keresztül érhető el bárki számára.
     * @return instanc: az osztály egyetlen példánya
     */
    public static DisplayManager GetInstance(){
        if(instance==null)
            instance=new DisplayManager();
        return instance;
    }

    /**
     * Visszatér a szektortömbbel.
     * @return AllocatedAsteroidSectors: a szektor tömb
     */
   public boolean[][] GetAllocatedAsteroidSectors() {
        return AllocatedAsteroidSectors;
    }

    /**
     * Viszatér a sorok számával.
     * @return rows: sorok száma
     */
    public int GetRows() {return rows;}

    /**
     * Viszatér az oszlopok számával.
     * @return columns: oszlopok száma
     */
    public int GetColumns() {return columns;}

    /**
     * Visszatér a villogá idejével.
     * @return blinkingtime: a villogás ideje
     */
    public int GetBlinkingTime() {return blinkingtime;}



    //CREATE

    /**
     * Létrehozza az AszteroidDiplayeket, valamint a SunDisplayt
     * a aparaméterül kapott Asteroid tömb alapján.
     * @param af: az aszterooida tömb
     */
    public void CreateAsteroidfieldDisplay(Asteroid[] af) {
        /**
         * végigiterál az af[] hosszán, majd a sorokon és oszlopokon
         * és ha igaz értékű szektorok[sor][oszlop] meőt talál, akkor azt hamisra
         * állítja (=foglalt), majd kiszmítja a szektor koordinátáit,
         * majd létrehoz egy AsteroidDisplayt az adott koordinátákkal, és az
         * aszteroida tömb adott elemével
         */
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

        /**
         * végül fix helyre létrehozza a SunDiplay-t
         */
        sunDisplay = new SunDisplay(getWidth()/2 -75, getHeight()/2 - 75);
    }

    /**
     * TeleportDiplayt készít a paraméterül kapott objektumhoz,
     * majd az azt tároló listába rakja az új Displayt.
     * @param t: az analízismodell beli objektum
     */
    public void CreateTeleportDisplay(Teleport t) {
        TeleportDisplay td = new TeleportDisplay(t);
        teleportDisplays.add(td);
    }

    /**
     * SettlerDiplayt készít a paraméterül kapott objektumhoz,
     * majd az azt tároló listába rakja az új Displayt.
     * @param s: az analízismodell beli objektum
     */
    public void CreateSettlerDisplay(Settler s) {
        SettlerDisplay sd = new SettlerDisplay(s);
        settlerDisplays.add(sd);
    }

    /**
     * RobotDiplayt készít a paraméterül kapott objektumhoz,
     * majd az azt tároló listába rakja az új Displayt.
     * @param r: az analízismodell beli objektum
     */
    public void CreateRobotDisplay(Robot r) {
        RobotDisplay rd = new RobotDisplay(r);
        robotDisplays.add(rd);
    }

    /**
     * UfoDiplayt készít a paraméterül kapott objektumhoz,
     * majd az azt tároló listába rakja az új Displayt.
     * @param u: az analízismodell beli objektum
     */
    public void CreateUfoDisplay(Ufo u) {
        UfoDisplay ud = new UfoDisplay(u);
        ufoDisplays.add(ud);
    }



    //REMOVE

    /**
     * Eltávolítja a paraméterül kapott Displayt annak
     * listájáből.
     * @param sd: a paraméterül kapott display
     */
    public void RemoveSettlerDisplay(SettlerDisplay sd) {
        settlerDisplays.remove(sd);
    }

    /**
     * Eltávolítja a paraméterül kapott Displayt annak
     * listájáből.
     * @param rd: a paraméterül kapott display
     */
    public void RemoveRobotDisplay(RobotDisplay rd) {
        robotDisplays.remove(rd);
    }

    /**
     * Eltávolítja a paraméterül kapott Displayt annak
     * listájáből.
     * @param ud: a paraméterül kapott display
     */
    public void RemoveUfoDisplay(UfoDisplay ud) {
        ufoDisplays.remove(ud);
    }

    /**
     * Eltávolítja a paraméterül kapott Displayt annak
     * listájáből.
     * @param td: a paraméterül kapott display
     */
    public void RemoveTeleportDisplay(TeleportDisplay td) {
        teleportDisplays.remove(td);
    }

    /**
     * Eltávolítja a paraméterül kapott Displayt annak
     * listájáből.
     * @param ad: a paraméterül kapott display
     */
    public void RemoveAsteroidDisplay(AsteroidDisplay ad) {
        asteroidDisplays.remove(ad);
    }

    /**
     * A GamePanelen való kattintás hatására kiírja a katintott objektum paramétereit.
     * Paraméerül a kattintás koordinátáit kapja meg.
     * @param x: a kattintás x koordinátája
     * @param y: a kattintás y koordinátája
     */
    public void ClickOnGamePanel(int x, int y) {
        ArrayList<Display> allDisplays = getDisplayArrayList();
        /**
         * először az összes AsteroidDisplayen végigiterál
         */
        for (AsteroidDisplay ad : asteroidDisplays) {
            /**
             * meviszgálja, hogy az adott pont benn van-e az adott aszteroidán
             */
            boolean pointInWhereabout = ad.PointInArea(x, y);
            /**
             * ha benn van
             */
            if (pointInWhereabout) {
                boolean pointInEntity = false;
                boolean pointInTeleport = false;
                /**
                 * megvizsgál minden SettlreDisplayt
                 */
                for (SettlerDisplay sd : settlerDisplays) {
                    pointInEntity = sd.PointInArea(x, y);
                    if (pointInEntity) {
                        /**
                         * ha a kattintás rá esik, kijelöli azt, majd kiírja annak információit
                         */
                        sd.SetSelected(true);
                        /**
                         * A többi display selected boolját hamisra állítja, hogy csak az legyen kiemelve
                         * akire kattintottak
                         */
                        allDisplays.remove(sd);
                        for (Display d : allDisplays) {
                            d.SetSelected(false);
                        }
                        Settler s = (Settler) sd.GetSubject();
                        /**
                         * végül kiírja az kattintott objektum adatait
                         */
                        Controller.GetInstanceOf().InfoAboutSettler(s);
                        break;
                    }
                }

                /**
                 *RobotDisplayre ugyan ez az elv
                 */
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

                /**
                 *UfoDisplayre ugyan ez az elv
                 */
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

                /**
                 *TeleportDisplayre ugyan ez az elv
                 */
                if (!pointInEntity) {
                    for (TeleportDisplay td : teleportDisplays) {
                        pointInTeleport = td.PointInArea(x, y);
                        if (pointInTeleport) {
                            td.SetSelected(true);
                            allDisplays.remove(td);
                            /**
                             * még a párját is kijelöli
                             */
                            allDisplays.remove(td.GetSubject().GetPair().GetDisplay());
                            for (Display d : allDisplays) {
                                d.SetSelected(false);
                            }
                            Controller.GetInstanceOf().InfoAboutTeleport(td.GetSubject());
                            break;
                        }
                    }
                }

                /**
                 * ha sem entitydisplay, sem teleportdisplay nem volt érintve, akkor az
                 * aszteroida infóit írja ki
                 */
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

    /**
     * A paraméterül kapott Displayt kiemeli körvonallal
     * @param d: a kiemelendő Display
     */
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

    /**
     * Valós időben ad információt a kattintott Displayről
     * (tehát pl. egy aszteroida adatai fúrás után rögtön változnak az InfoPanelen).
     */
    public void RefreshSelectedDisplay(){
        /**
         * Végigiterál az egyes Display tömbökön, és ha valamelyik ki van választva
         * (legutóbb erre kattintottak), arról mutat információt
         */
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

    /**
     * Beállítja, hogy mely aszteroidák Displaye-i szomszédosak.
     * Ezután pl utazáskor a szomszédok ki lesznek emelve.
     */
    public void SetNeigbhourHood(){
        for(SettlerDisplay d: settlerDisplays){
            if(d.IsRoundoutline()){
                AsteroidDisplay ad = (AsteroidDisplay) d.GetSubject().GetAsteroid().GetDisplay();
                ad.NotifyNeighbourhood();
            }
        }
    }

    /**
     * Visszatér az összes tárolt display összefésült listájával.
     * @return allDisplay
     */
    private ArrayList<Display> getDisplayArrayList() {
        ArrayList<Display> allDisplays = new ArrayList<>();
        allDisplays.addAll(asteroidDisplays);
        allDisplays.addAll(settlerDisplays);
        allDisplays.addAll(ufoDisplays);
        allDisplays.addAll(teleportDisplays);
        allDisplays.addAll(robotDisplays);
        return allDisplays;
    }

    /**
     * A GamePanelen való kattintás hatására az adott AsteroidDisplay szomszédjára
     * mozgatja a játékost.
     * Paraméerül a kattintás koordinátáit kapja meg.
     * @param x: a kattintás x koordinátája
     * @param y: a kattintás y koordinátája
     */
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