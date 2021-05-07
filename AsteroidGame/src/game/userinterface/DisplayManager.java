package game.userinterface;

import game.controller.Controller;
import game.logic.*;
import game.logic.Robot;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayManager extends JPanel{
    private  static DisplayManager instance=null;
    private ArrayList<UfoDisplay> ufoDisplays;
    private ArrayList<RobotDisplay> robotDisplays;
    private ArrayList<SettlerDisplay> settlerDisplays;
    private ArrayList<TeleportDisplay> teleportDisplays;
    private ArrayList<AsteroidDisplay> asteroidDisplays;
    private SunDisplay sunDisplay;
    private int rows;
    private int columns;
    private int numberOfAsteroids;
    boolean[][] AllocatedAsteroidSectors;

    private DisplayManager(){
        super();
        setBackground(new Color(50,56,65));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(1050,1050));

        //initialize variables
        ufoDisplays=new ArrayList<UfoDisplay>();
        robotDisplays=new ArrayList<RobotDisplay>();
        settlerDisplays=new ArrayList<SettlerDisplay>();
        teleportDisplays=new ArrayList<TeleportDisplay>();
        asteroidDisplays=new ArrayList<AsteroidDisplay>();
        rows = 7;
        columns = 7;
        numberOfAsteroids=36;
        AllocatedAsteroidSectors=new  boolean[][]{
                //real
                {true, false, false, true, true, true, true},
                {true, true, true, true, true, true, true},
                {true, false, false, false, true, true, true},
                {true, true, false, false, true, false, true},
                {true, true, true, false, false, true, true},
                {true, true, false, true, true, false, true},
                {true, false, true, true, true, true, true}

                //test
                /*{true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true},
                {false, true, false, false, false, true, false},
                {true, true, false, false, false, true, true},
                {false, true, false, false, false, true, false},
                {true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true}*/

                //fun
                /*{true, true, true, true, false, false, true},
                {false, false, false, true, false, false, true},
                {false, false, false, true, false, false, true},
                {true, true, true, true, true, true, true},
                {true, false, false, true, false, false, false},
                {true, false, false, true, false, false, false},
                {true, false, false, true, true, true, true}*/
        };
    }

    public static DisplayManager GetInstance(){
        if(instance==null)
            instance=new DisplayManager();
        return instance;
    }
    public void Test(){

        Asteroid[] asteroids = new Asteroid[numberOfAsteroids];
        for(int i = 0; i < numberOfAsteroids; i ++){
            asteroids[i] = new Asteroid();
            //asteroids[i].SetSunnearness(true);
        }
        CreateAsteroidfieldDisplay(asteroids);

        //for debug: print number of true sectors (sun excluded)
        System.out.println("w:"+getWidth()+" h:"+getHeight());
        int trues=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(AllocatedAsteroidSectors[i][j])
                    trues++;

        /*Asteroid[] asteroid = new Asteroid[1];
        asteroid[0] = new Asteroid();
        System.out.println(getWidth()+ " " +getHeight());
        CreateAsteroidfieldDisplay(asteroid);

        Teleport[] teleports = new Teleport[9];
        for(int i =0; i < 9; i++){
            teleports[i] = new Teleport();
            System.out.println(teleports[i].Deploy(asteroid[0]));
            CreateTeleportDisplay(teleports[i]);
        }

       Settler[] settlers = new Settler[16];
       for(int i = 0; i < 16; i++){
           settlers[i] = new Settler();
           settlers[i].SetAsteroid(asteroid[0]);
           CreateSettlerDisplay(settlers[i]);
       }*/
    }
    //GETTERS
    public ArrayList<UfoDisplay> GetUfoDisplays(){return ufoDisplays;}
    public ArrayList<RobotDisplay> GetRobotDisplays(){return robotDisplays;}
    public ArrayList<SettlerDisplay> GetSettlerDisplays(){return settlerDisplays;}
    public ArrayList<TeleportDisplay> GetTeleportDisplays(){return teleportDisplays;}
    public ArrayList<AsteroidDisplay> GetAsteroidDisplays(){return asteroidDisplays;}
    public SunDisplay GetSunDisplay(){return sunDisplay;}

    private void SectorInit(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                AllocatedAsteroidSectors[i][j]=false;
            }
        }
        System.out.println("trues: "+trues);
    }

    //CREATE
    public void CreateAsteroidfieldDisplay(Asteroid[] af){
        for(int i=0;i<af.length;i++){
            //Point coord = CoordinateServer();
            for(int k=0;k<rows;k++){
                for(int j=0;j<columns;j++){
                    if( AllocatedAsteroidSectors[k][j]){
                        int x = k;
                        int y = j;
                        x = (x * getWidth()) / rows;
                        y = (y * getHeight()) / columns;
                        AsteroidDisplay ad=new AsteroidDisplay(af[i], x, y);
                        asteroidDisplays.add(ad);
                    }
                }
            }
        }
        sunDisplay = new SunDisplay((3 * getWidth()) / rows,(3* getHeight()) / columns);
    }

    public void CreateTeleportDisplay(Teleport t){
        TeleportDisplay td=new TeleportDisplay(t);
        teleportDisplays.add(td);
    }
    public void CreateSettlerDisplay(Settler s){
        SettlerDisplay sd=new SettlerDisplay(s);
        settlerDisplays.add(sd);
    }
    public void CreateRobotDisplay(Robot r){
        RobotDisplay rd=new RobotDisplay(r);
        robotDisplays.add(rd);
    }
    public void CreateUfoDisplay(Ufo u){
        UfoDisplay ud =new UfoDisplay(u);
        ufoDisplays.add(ud);
    }

    //REMOVE
    public void RemoveSettlerDisplay(SettlerDisplay sd){settlerDisplays.remove(sd);}
    public void RemoveRobotDisplay(RobotDisplay rd) {robotDisplays.remove(rd);}
    public void RemoveUfoDisplay(UfoDisplay ud){ufoDisplays.remove(ud); }
    public void RemoveTeleportDisplay(TeleportDisplay td){teleportDisplays.remove(td);}
    public void RemoveAsteroidDisplay(AsteroidDisplay ad){asteroidDisplays.remove(ad);}

    public void ClickOnGamePanel(int x, int y){
        for(AsteroidDisplay ad:asteroidDisplays)
        {
            boolean pointInWhereabout=ad.PointInArea(x,y);
            if(pointInWhereabout)
            {
                boolean pointInEntity=false;
                for(SettlerDisplay sd:settlerDisplays){
                    pointInEntity=sd.PointInArea(x,y);
                    if(pointInEntity){
                        sd.SetSelected(true);
                        Settler s = sd.GetSubject();
                        Controller.GetInstanceOf().InfoAboutSettler(s);
                        break;
                    }
                }
                for(RobotDisplay rd:robotDisplays){
                    pointInEntity=rd.PointInArea(x,y);
                    if(pointInEntity){
                        rd.SetSelected(true);
                        Controller.GetInstanceOf().InfoAboutRobot(rd.GetSubject());
                        break;
                    }
                }
                for(UfoDisplay ud:ufoDisplays){
                    pointInEntity=ud.PointInArea(x,y);
                    if(pointInEntity){
                        ud.SetSelected(true);
                        Controller.GetInstanceOf().InfoAboutUfo(ud.GetSubject());
                        break;
                    }
                }
                if(!pointInEntity){
                    ad.SetSelected(true);
                    Controller.GetInstanceOf().InfoAboutAsteroid(ad.GetSubject());
                    break;
                }
            }
        }

        for(TeleportDisplay td:teleportDisplays)
        {
            boolean pointInWherebout=td.PointInArea(x,y);
            if(pointInWherebout){
                td.SetSelected(true);
                Controller.GetInstanceOf().InfoAboutTeleport(td.GetSubject());
                break;
            }
        }
    }

    public void ClickOnMoveTarget(int x, int y){
        for(AsteroidDisplay ad:asteroidDisplays){
            boolean pointInWherebout=ad.PointInArea(x,y);
            if(pointInWherebout){
                Whereabout a = ad.GetSubject();
                Controller.GetInstanceOf().SettlerMove(a);
                break;
            }
        }
        for(TeleportDisplay td:teleportDisplays){
            boolean pointInWherebout=td.PointInArea(x,y);
            if(pointInWherebout){
                Controller.GetInstanceOf().SettlerMove(td.GetSubject());
                break;
            }
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(sunDisplay != null) {
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

    public void DrawDisplays(){
        repaint();
    }
    /*public boolean Intersect(Display d1,Display d2){
        //Area(Shape) ,ha krinya implementálta
    }*/

    private Display activedisplay;

    public void RoundStart(Display display)
    {
        activedisplay.SetRoundoutline(false);
        activedisplay=display;
        display.SetRoundoutline(true);
    }

}