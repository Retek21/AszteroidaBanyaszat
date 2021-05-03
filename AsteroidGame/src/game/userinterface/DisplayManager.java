package game.userinterface;

import game.controller.Controller;
import game.logic.*;

import java.util.ArrayList;

public class DisplayManager {

    ArrayList<UfoDisplay> ufoDisplays=new ArrayList<UfoDisplay>();
    ArrayList<RobotDisplay> robotDisplays=new ArrayList<RobotDisplay>();
    ArrayList<SettlerDisplay> settlerDisplays=new ArrayList<SettlerDisplay>();
    ArrayList<TeleportDisplay> teleportDisplays=new ArrayList<TeleportDisplay>();
    ArrayList<AsteroidDisplay> asteroidDisplays=new ArrayList<AsteroidDisplay>();
    ArrayList<ConnectionDisplay> connectionDisplays=new ArrayList<ConnectionDisplay>();
    GamePanel gamePanel=new GamePanel();

    private  static DisplayManager instance=null;
    private DisplayManager(){}
    public static DisplayManager GetInstance(){
        if(instance==null)
            instance=new DisplayManager();
        return instance;
    }

    //CREATE
    public void CreateAsteroidfieldDisplay(Asteroid[] af){
        for(int i=0;i<af.length;i++){
            AsteroidDisplay ad=new AsteroidDisplay(af[i]);
            asteroidDisplays.add(ad);
        }
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
    public  void CreateConnectionDisplay(Display d1,Display d2){
        ConnectionDisplay cd=new ConnectionDisplay(d1,d2);
        connectionDisplays.add(cd);
    }

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
    public void RemoveConnectionDisplay(ConnectionDisplay cd){
        connectionDisplays.remove(cd);
    }

    public void DrawDisplays(){
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
    }

    public void SetNeighbourhood(Whereabout w1, Whereabout w2){
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
    }

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
                ad.GetSubject();
                Controller.GetInstanceOf().SettlerMove(ad);
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

    public boolean Intersect(Display d1,Display d2){
        //Area(Shape) ,ha krinya implementálta
    }

}
