package game.userinterface;

import game.controller.Controller;
/**
* @author Szeredi Peter
* Inputmanager osztaly
* kezeli a kontexusnak megfelelo matodusok hivodjanak meg, kezeli azt hogy melyik gombok hasznalhatoak
* */
public class InputManager {
    /**
    privat static peldanya az inputmanagernek
    * */
    private static InputManager instance;
    /**
    * gombaktivitast allito bool
    * */
    private boolean buttonactivity;
    /**
    * displaymanager peldany
    * */
    private DisplayManager manager;
    /**
    * controller peldany
    * */
    private Controller controller;

    /**
    * kulonbozo buttonok peldanyai
    * */
    private GameButton dophasebutton;
    private GameButton movebutton;
    private GameButton drillbutton;
    private GameButton minebutton;
    private GameButton placebutton;
    private GameButton craftbutton;

    /**
    * az adott state
    * */
    private State state;

    /**
    * konstruktor, beallitja a controller es manager peldanyokat
    * */
    private InputManager()
    {
        manager = DisplayManager.GetInstance();
        controller = Controller.GetInstanceOf();
        buttonactivity = true;
    }
    /**
    * statikus konstruktor a singleton osztalyhoz
    * */
    public static InputManager GetInstanceOf(){
        if(instance == null)
            instance = new InputManager();
        return instance;
    }
    /**
    * beallitja a kulonbozo gombokat
     * @param dophase - dophase button
     * @param move - move button
     * @param drill - drill button
     * @param mine - mine button
     * @param place - place button
     * @param craft - craft button
    * */
    public void SetComponents(GameButton dophase,GameButton move,GameButton drill,GameButton mine,GameButton place,GameButton craft){
        dophasebutton = dophase;
        movebutton = move;
        drillbutton = drill;
        minebutton = mine;
        placebutton = place;
        craftbutton = craft;
    }
    /**
    * @param - kapcsolo bool
     * beallitja a kapcsolo alapjan a buttonokat, ha true beallitja a state-et is
    * */
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
    /**
    * az allapot alapjan allitja be a gombok aktivitasat
     * vegen beallitja a state-et is
     * @param s - az adott state
    * */
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
    /**
     * ha egy adott gamefieldre clickeltunk
     * */
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
    /**
     * kapott string alapjan kivalasztja az adott gombot es elinditja az alapjan a Displaymanager megfelelo kirajzolasat
     * @param buttoncmd - az string ami jeloli az adott gombot
     * @param craft - craft combobox altal atadott ertek stringje amit craftolni fog a telepes
     * @param place - place combobox altal visszadott ertek stringje amit placeelni akar a telepes
     * */
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
