package game.userinterface;

import game.controller.Controller;
import game.logic.*;
import game.logic.Robot;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DisplayManager {

    private ArrayList<UfoDisplay> ufoDisplays=new ArrayList<UfoDisplay>();
    private ArrayList<RobotDisplay> robotDisplays=new ArrayList<RobotDisplay>();
    private ArrayList<SettlerDisplay> settlerDisplays=new ArrayList<SettlerDisplay>();
    private ArrayList<TeleportDisplay> teleportDisplays=new ArrayList<TeleportDisplay>();
    private ArrayList<AsteroidDisplay> asteroidDisplays=new ArrayList<AsteroidDisplay>();
    //private ArrayList<ConnectionDisplay> connectionDisplays=new ArrayList<ConnectionDisplay>();
    private SunDisplay sunDisplay=new SunDisplay();
    private GamePanel gamePanel=new GamePanel();
    boolean[][] AllocatedAsteroidSectors=new boolean[10][10];

    private  static DisplayManager instance=null;
    private DisplayManager(){}
    public static DisplayManager GetInstance(){
        if(instance==null)
            instance=new DisplayManager();
        return instance;
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
        AllocatedAsteroidSectors[5][5]=true;//SUN
    }

    private Point CoordinateServer(){
        Random random=new Random();
        boolean freeSector=false;
        int x=-1;
        int y=-1;

        while(!freeSector){
            x = random.nextInt(10);
            y = random.nextInt(10);
            if(!AllocatedAsteroidSectors[x][y]){
                AllocatedAsteroidSectors[x][y]=true;
                freeSector=true;
            }
        }

        x = (x * gamePanel.getWidth()) / 10;
        y = (y * gamePanel.getHeight()) / 10;

        return new Point(x,y);
    }

    //CREATE
    public void CreateAsteroidfieldDisplay(Asteroid[] af){
        SectorInit();

        for(int i=0;i<af.length;i++){
            Point coord = CoordinateServer();
            AsteroidDisplay ad=new AsteroidDisplay(af[i], coord.x,coord.y);
            asteroidDisplays.add(ad);
        }

        Point coordOfSun = CoordinateServer();
        sunDisplay = new SunDisplay(coordOfSun.x,coordOfSun.y);
    }
    public void CreateTeleportDisplay(Teleport t){
        TeleportDisplay td=new TeleportDisplay(t);
        teleportDisplays.add(td);
    }
    public void CreateSettlerDisplay(Settler s){
        SettlerDisplay sd=new SettlerDisplay(sd);
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
    /*public  void CreateConnectionDisplay(Display d1, Display d2){
        ConnectionDisplay cd=new ConnectionDisplay(d1,d2);
        connectionDisplays.add(cd);
    }*/

    //REMOVE
    public void RemoveSettlerDisplay(SettlerDisplay sd){
        settlerDisplays.remove(sd);
    }
    public void RemoveRobotDisplay(RobotDisplay rd) {
        robotDisplays.remove(rd);
    }
    public void RemoveUfoDisplay(UfoDisplay ud){
        ufoDisplays.remove(ud);
    }
    public void RemoveTeleportDisplay(TeleportDisplay td){
        teleportDisplays.remove(td);
    }
    public void RemoveAsteroidDisplay(AsteroidDisplay ad){
        asteroidDisplays.remove(ad);
    }

    /*public void RemoveConnectionDisplay(ConnectionDisplay cd){
        connectionDisplays.remove(cd);
    }*/

    /*private void DrawSun(){
        Graphics2D g2d=(Graphics2D) ;
        g2d.setColor(Color.YELLOW);

        g2d.fillOval(getShape().x, getShape().y, getShape().width, getShape().height);
        g2d.setColor();
        g2d.drawOval(getShape().x, getShape().y, getShape().width, getShape().height);
    }*/

    public void UpdateGamePanel(){
        gamePanel.DrawDisplays();
    }

    /*public void DrawDisplays(){
        //DrawSun();
        for (AsteroidDisplay ad:asteroidDisplays)
            ad.Update();
        for(TeleportDisplay td:teleportDisplays)
            td.Update();
        for (SettlerDisplay sd:settlerDisplays)
            sd.Update();
        for(RobotDisplay rd:robotDisplays)
            rd.Update();
        for(UfoDisplay ud:ufoDisplays)
            ud.Update();
    }*/

    /*public void SetNeighbourhood(Whereabout w1, Whereabout w2){
        Display d1;
        Display d2;
        for(AsteroidDisplay tmp:asteroidDisplays){
            if(tmp.GetSubject()==w1)
                d1==tmp;
            if(tmp.GetSubject()==w2)
                d2==tmp;
        }
        for(TeleportDisplay tmp:teleportDisplays){
            if(tmp.GetSubject()==w1)
                d1==tmp;
            if(tmp.GetSubject()==w2)
                d2==tmp;
        }
        if(d1!=null && d2!=null){
            CreateConnectionDisplay(d1,d2);
        }
    }*/

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
                        sd.SelectOutline();
                        sd.GetSubject();
                        Controller.GetInstanceOf().InfoAboutSettler();

                        break;
                    }
                }
                for(RobotDisplay rd:robotDisplays){
                    pointInEntity=rd.PointInArea(x,y);
                    if(pointInEntity){
                        rd.SelectOutline();
                        rd.GetSubject();
                        Controller.GetInstanceOf().InfoAboutRobot();
                        break;
                    }
                }
                for(UfoDisplay ud:ufoDisplays){
                    pointInEntity=ud.PointInArea(x,y);
                    if(pointInEntity){
                        ud.SelectOutline();
                        ud.GetSubject();
                        Controller.GetInstanceOf().InfoAboutUfo();
                        break;
                    }
                }
                if(!pointInEntity){
                    ad.SelectOutline();
                    ad.GetSubject();
                    Controller.GetInstanceOf().InfoAboutAsteroid();
                    break;
                }
            }
        }

        for(TeleportDisplay td:teleportDisplays)
        {
            boolean pointInWherebout=td.PointInArea(x,y);
            if(pointInWherebout){
                td.SelectOutline();
                td.GetSubject();
                Controller.GetInstanceOf().InfoAboutTeleport();
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
                td.GetSubject();
                Controller.GetInstanceOf().SettlerMove(td);
                break;
            }
        }
    }

    /*public boolean Intersect(Display d1,Display d2){
        //Area(Shape) ,ha krinya implementálta
    }*/

}
