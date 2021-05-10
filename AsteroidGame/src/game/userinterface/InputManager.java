package game.userinterface;

import game.controller.Controller;

public class InputManager {

    private static InputManager instance;

    private boolean buttonactivity;

    private DisplayManager manager;

    private Controller controller;

    private GameButton dophasebutton;
    private GameButton movebutton;
    private GameButton drillbutton;
    private GameButton minebutton;
    private GameButton placebutton;
    private GameButton craftbutton;

    private State state;


    private InputManager()
    {
        manager = DisplayManager.GetInstance();
        controller = Controller.GetInstanceOf();
        buttonactivity = true;
    }

    public static InputManager GetInstanceOf(){
        if(instance == null)
            instance = new InputManager();
        return instance;
    }

    public void SetComponents(GameButton dophase,GameButton move,GameButton drill,GameButton mine,GameButton place,GameButton craft){
        dophasebutton = dophase;
        movebutton = move;
        drillbutton = drill;
        minebutton = mine;
        placebutton = place;
        craftbutton = craft;
    }

    public void TurnOnOffButtons(boolean value) {
        buttonactivity = value;
        if (buttonactivity)
            SetState(state);
        else {
            dophasebutton.setEnabled(false);
            movebutton.setEnabled(false);
            drillbutton.setEnabled(false);
            minebutton.setEnabled(false);
            placebutton.setEnabled(false);
            craftbutton.setEnabled(false);
        }
    }

    public void SetState(State s)
    {
        switch(s){
            case WAITFORMOVE:
                if (buttonactivity) {
                    dophasebutton.setEnabled(false);
                    movebutton.setEnabled(false);
                    drillbutton.setEnabled(false);
                    minebutton.setEnabled(false);
                    placebutton.setEnabled(false);
                    craftbutton.setEnabled(false);
                }
                break;
            case SETTLERROUND:
                    if (buttonactivity) {
                        dophasebutton.setEnabled(false);
                        movebutton.setEnabled(true);
                        drillbutton.setEnabled(true);
                        minebutton.setEnabled(true);
                        placebutton.setEnabled(true);
                        craftbutton.setEnabled(true);
                    }
                break;
            case AIROUND:
                if (buttonactivity) {
                    dophasebutton.setEnabled(true);
                    movebutton.setEnabled(false);
                    drillbutton.setEnabled(false);
                    minebutton.setEnabled(false);
                    placebutton.setEnabled(false);
                    craftbutton.setEnabled(false);
                }
                break;
            default:
                break;
        }
        state = s;
    }

    public void GameFieldClicked(int x, int y)
    {
        if(state==State.WAITFORMOVE){
            manager.ClickOnMoveTarget(x, y);
            System.out.println("WaitforMoveban kattintva x:" + x + " y:" + y);
            manager.RefreshSelectedDisplay();
        }
        else{
            manager.ClickOnGamePanel(x , y);
            System.out.println("NemWaitforMoveban kattintva x:" + x + " y:" + y);
        }
    }

    public void ButtonPressed(String buttoncmd, String craft, String place)
    {
        switch (buttoncmd){
            case "DoPhase":
                controller.DoPhase();
                System.out.println("DoPhase pressed");
                //DisplayManager.GetInstance().Test();
                //DisplayManager.GetInstance().repaint();
                manager.RefreshSelectedDisplay();
                break;
            case "Move":
                System.out.println("Move pressed");
                DisplayManager.GetInstance().SetNeigbhourHood();
                SetState(State.WAITFORMOVE);
                break;
            case "Drill":
                controller.SettlerDrill();
                System.out.println("Drill pressed");
                manager.RefreshSelectedDisplay();
                break;
            case "Mine":
                controller.SettlerMine();
                System.out.println("Mine pressed");
                manager.RefreshSelectedDisplay();
                break;
            case "Place":
                controller.SettlerPlace(place);
                System.out.println("Place pressed");
                manager.RefreshSelectedDisplay();
                break;
            case "Craft":
                controller.SettlerCraft(craft);
                System.out.println("Craft pressed");
                manager.RefreshSelectedDisplay();
                break;
        }
    }
}
