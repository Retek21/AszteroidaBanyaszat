package game.userinterface;


import java.util.ArrayList;
import game.logic.*;

public class Manager {

    public enum State{
        SettlerRound,
        WaitForMove,
        AIRound
    }
    private State state;
    private ArrayList<SettlerDisplay> settlerdisplays;
    private ArrayList<RobotDisplay> robotdisplays;
    private ArrayList<UfoDisplay> ufodisplays;
    private ArrayList<AsteroidDisplay> asteroiddisplays;
    private ArrayList<TeleportDisplay> teleportdisplays;
    private Actor actor;

    public void CreateAsteroidfieldDisplay(Asteroid[] a){}
    public void CreateTeleportDisplay(Teleport t){}
    public void CreateSettlerDisplay(Settler s){}
    public void CreateRobotDisplay(Robot r){}
    public void CreateUfoDisplay(Ufo u){}
    public void SetNeighbourhood(Asteroid a1, Asteroid a2){}
    public void DrawDisplays(){}
    public void WriteToNaplo(String[] text){}
    public void SetState(){}
    public void StartAct(String action){}
}
