package game.userinterface;
import game.controller.Controller;
import game.logic.*;
import game.logic.Robot;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Osztaly, mely az egyes Displayeket kezeli, a kirajzolasukat meghivja, letrehozza,
 * illetve eltavolitja azokat.
 * Az egyes kattintasok koordinataait is hazzarendeli az adott objektumra, amjd ennek
 * egefleloen informaciot ad az adott objektumrol.
 * Singleton osztaly.
 * @author Dengyel Bendeguz
 * @author Szabo Gergo
 * @author Torok Kristof
 */
public class DisplayManager extends JPanel {
    /**
     * A Singelton megvalositast szolgalja, egy DisplayManager tagvaltozo,
     * mindenki altal elerheto.
     */
    private static DisplayManager instance = null;

    /**
     * Az UfoDisplayeket tarolo lista.
     */
    private ArrayList<UfoDisplay> ufoDisplays;

    /**
     * A RobotDisplayeket tarolo lista.
     */
    private ArrayList<RobotDisplay> robotDisplays;

    /**
     * A SettlerDisplayeket tarolo lista.
     */
    private ArrayList<SettlerDisplay> settlerDisplays;

    /**
     * A TeleportDisplayeket tarolo lista.
     */
    private ArrayList<TeleportDisplay> teleportDisplays;

    /**
     * Az AsteroidDisplayeket tarolo lista.
     */
    private ArrayList<AsteroidDisplay> asteroidDisplays;

    /**
     * A torlendo entitasok listaja.
     */
    private ArrayList<Display> clearpuffer;

    /**
     * A SunDisplay referenciaja
     */
    private SunDisplay sunDisplay;

    /**
     * A villogas idotartama
     */
    private int blinkingtime;

    /**
     * Az aszteroida mezot felepito halo sorai.
     */
    private int rows;

    /**
     * Az aszteroida mezot felepito halo oszlopai.
     */
    private int columns;

    /**
     * Az aszteroida mezot felepito aszteroidak szama.
     */
    private int numberOfAsteroids;

    /**
     * Az aszteroida mezot felepito halo szektorai (2D tomb), erteke igaz,
     * ha talalhato a szektorban aszteroida, hamis ha nem.
     */
    private boolean[][] AllocatedAsteroidSectors;

    /**
     * Privat konstruktor, a singleton osztalyhoz szukseges lathatosaggal.
     * A az os JPanel konstruktorat hivja, valamint az inicializalo metodust.
     */
    private DisplayManager() {
        super();
        Init();

    }

    /**
     * Inicializalja a valtozakat, es a jatekter dimenzioit beallitja.
     */
    public void Init() {
        /**
         * A jatektar dimenzioi.
         */
        setBackground(new Color(50, 56, 65));
        setBorder(BorderFactory.createLineBorder(Color.black));

        /**
         * Tagvaltozok inicializalasa.
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
         * Elore megepitett jatekter.
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
     * Az osztaly egyetlen objektuma ezen keresztul erheto el barki szamara.
     * @return instanc: az osztaly egyetlen peldanya
     */
    public static DisplayManager GetInstance(){
        if(instance==null)
            instance=new DisplayManager();
        return instance;
    }

    /**
     * Visszater a szektortombbel.
     * @return AllocatedAsteroidSectors: a szektor tomb
     */
   public boolean[][] GetAllocatedAsteroidSectors() {
        return AllocatedAsteroidSectors;
    }

    /**
     * Viszater a sorok szamaval.
     * @return rows: sorok szama
     */
    public int GetRows() {return rows;}

    /**
     * Viszater az oszlopok szamaval.
     * @return columns: oszlopok szama
     */
    public int GetColumns() {return columns;}

    /**
     * Visszater a villoga idejevel.
     * @return blinkingtime: a villogas ideje
     */
    public int GetBlinkingTime() {return blinkingtime;}



    //CREATE

    /**
     * Letrehozza az AszteroidDiplayeket, valamint a SunDisplayt
     * a aparameterul kapott Asteroid tomb alapjan.
     * @param af: az aszterooida tomb
     */
    public void CreateAsteroidfieldDisplay(Asteroid[] af) {
        /**
         * vegigiteral az af[] hosszan, majd a sorokon es oszlopokon
         * es ha igaz erteku szektorok[sor][oszlop] meot talal, akkor azt hamisra
         * allitja (=foglalt), majd kiszmitja a szektor koordinatait,
         * majd letrehoz egy AsteroidDisplayt az adott koordinatakkal, es az
         * aszteroida tomb adott elemevel
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
         * vegul fix helyre letrehozza a SunDiplay-t
         */
        sunDisplay = new SunDisplay(getWidth()/2 -75, getHeight()/2 - 75);
    }

    /**
     * TeleportDiplayt keszit a parameterul kapott objektumhoz,
     * majd az azt tarolo listaba rakja az uj Displayt.
     * @param t: az analizismodell beli objektum
     */
    public void CreateTeleportDisplay(Teleport t) {
        TeleportDisplay td = new TeleportDisplay(t);
        teleportDisplays.add(td);
    }

    /**
     * SettlerDiplayt keszit a parameterul kapott objektumhoz,
     * majd az azt tarolo listaba rakja az uj Displayt.
     * @param s: az analizismodell beli objektum
     */
    public void CreateSettlerDisplay(Settler s) {
        SettlerDisplay sd = new SettlerDisplay(s);
        settlerDisplays.add(sd);
    }

    /**
     * RobotDiplayt keszit a parameterul kapott objektumhoz,
     * majd az azt tarolo listaba rakja az uj Displayt.
     * @param r: az analizismodell beli objektum
     */
    public void CreateRobotDisplay(Robot r) {
        RobotDisplay rd = new RobotDisplay(r);
        robotDisplays.add(rd);
    }

    /**
     * UfoDiplayt keszit a parameterul kapott objektumhoz,
     * majd az azt tarolo listaba rakja az uj Displayt.
     * @param u: az analizismodell beli objektum
     */
    public void CreateUfoDisplay(Ufo u) {
        UfoDisplay ud = new UfoDisplay(u);
        ufoDisplays.add(ud);
    }



    //REMOVE

    /**
     * Eltavolitja a parameterul kapott Displayt annak
     * listajabol.
     * @param sd: a parameterul kapott display
     */
    public void RemoveSettlerDisplay(SettlerDisplay sd) {
        settlerDisplays.remove(sd);
    }

    /**
     * Eltavolitja a parameterul kapott Displayt annak
     * listajabol.
     * @param rd: a parameterul kapott display
     */
    public void RemoveRobotDisplay(RobotDisplay rd) {
        robotDisplays.remove(rd);
    }

    /**
     * Eltavolitja a parameterul kapott Displayt annak
     * listajabol.
     * @param ud: a parameterul kapott display
     */
    public void RemoveUfoDisplay(UfoDisplay ud) {
        ufoDisplays.remove(ud);
    }

    /**
     * Eltavolitja a parameterul kapott Displayt annak
     * listajabol.
     * @param td: a parameterul kapott display
     */
    public void RemoveTeleportDisplay(TeleportDisplay td) {
        teleportDisplays.remove(td);
    }

    /**
     * Eltavolitja a parameterul kapott Displayt annak
     * listajabol.
     * @param ad: a parameterul kapott display
     */
    public void RemoveAsteroidDisplay(AsteroidDisplay ad) {
        asteroidDisplays.remove(ad);
    }

    /**
     * A GamePanelen valo kattintas hatasara kiirja a katintott objektum parametereit.
     * Parameerul a kattintas koordinatait kapja meg.
     * @param x: a kattintas x koordinataja
     * @param y: a kattintas y koordinataja
     */
    public void ClickOnGamePanel(int x, int y) {
        ArrayList<Display> allDisplays = getDisplayArrayList();
        /**
         * eloszor az osszes AsteroidDisplayen vegigiteral
         */
        for (AsteroidDisplay ad : asteroidDisplays) {
            /**
             * meviszgalja, hogy az adott pont benn van-e az adott aszteroidan
             */
            boolean pointInWhereabout = ad.PointInArea(x, y);
            /**
             * ha benn van
             */
            if (pointInWhereabout) {
                boolean pointInEntity = false;
                boolean pointInTeleport = false;
                /**
                 * megvizsgal minden SettlreDisplayt
                 */
                for (SettlerDisplay sd : settlerDisplays) {
                    pointInEntity = sd.PointInArea(x, y);
                    if (pointInEntity) {
                        /**
                         * ha a kattintas ra esik, kijeloli azt, majd kiirja annak informacioit
                         */
                        sd.SetSelected(true);
                        /**
                         * A tobbi display selected booljat hamisra allitja, hogy csak az legyen kiemelve
                         * akire kattintottak
                         */
                        allDisplays.remove(sd);
                        for (Display d : allDisplays) {
                            d.SetSelected(false);
                        }
                        Settler s = (Settler) sd.GetSubject();
                        /**
                         * vegul kiirja az kattintott objektum adatait
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
                             * meg a parjat is kijeloli
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
                 * ha sem entitydisplay, sem teleportdisplay nem volt erintve, akkor az
                 * aszteroida infoit irja ki
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
     * A parameterul kapott Displayt kiemeli korvonallal
     * @param d: a kiemelendo Display
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
     * Valos idoben ad informaciot a kattintott Displayrol
     * (tehat pl. egy aszteroida adatai furas utan rogton valtoznak az InfoPanelen).
     */
    public void RefreshSelectedDisplay(){
        /**
         * Vegigiteral az egyes Display tombokon, es ha valamelyik ki van valasztva
         * (legutobb erre kattintottak), arrol mutat informaciot
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
     * Beallitja, hogy mely aszteroidak Displaye-i szomszedosak.
     * Ezutan pl utazaskor a szomszedok ki lesznek emelve.
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
     * Visszater az osszes tarolt display osszefesult listajaval.
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
     * A GamePanelen valo kattintas hatasara az adott AsteroidDisplay szomszedjara
     * mozgatja a jatekost.
     * Parameerul a kattintas koordinatait kapja meg.
     * @param x: a kattintas x koordinataja
     * @param y: a kattintas y koordinataja
     */
    public void ClickOnMoveTarget(int x, int y) {
        /**
         * az egyes teleportokra ellenoriz, ha talal katttintott teleportot, akkor a
         * parjanak aszteroidaajara mozgatja  ajatekost (ha aktivak)
         */
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

        /**
         * megcsinalja ugyan ezt az aszteroidakkal
         */
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

    /**
     * Az osszes Displayre meghivja annak Paint() metodusat.
     * @param g: a metodushoz szukseges Graphics objktum (osbol implemetalt metodus)
     */
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

    /**
     * Mas osztalyok altal hivhato rajzolas metodus.
     */
    public void DrawDisplays() {
        repaint();
    }

    /**
     * Villogtatja a BlinkerThread altal kivalasztott whereaboutdisplayeket.
     */
    public void BlinkWhereabouts() {
        ArrayList<WhereaboutDisplay> displays = new ArrayList<WhereaboutDisplay>();
        displays.addAll(asteroidDisplays);
        displays.addAll(teleportDisplays);
        for(WhereaboutDisplay wd : displays)
            wd.Blink();
    }

    /**
     * A torlendo displayekhez adja a parameterul kapott displayt.
     * @param d: a parameterul kapott display
     */
    public void AddToClearPuffer(Display d) {
        clearpuffer.add(d);
    }

    /**
     * Torli a torlendo Displayek pufferet.
     */
    public void ClearClearPuffer() {
        for(Display d : clearpuffer)
            d.Clear();
    }


    /**
     * Nem tudom ez mi.
     */
    private Display activedisplay;

    /**
     * Kijeloli a soron levo Displayt, akinek eppen a kore van.
     * @param display: a soron levo display
     */
    public void RoundStart(Display display) {
        activedisplay.SetRoundoutline(false);
        activedisplay = display;
        display.SetRoundoutline(true);
    }
}