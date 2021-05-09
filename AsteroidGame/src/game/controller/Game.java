package game.controller;

import game.userinterface.*;

public class Game {
    GameFrame gameframe ;
    MenuFrame menuframe;
    EndGameFrame endgameframe;

    private static Game instance;

    /**
     * A kontroller osztalyra mutato referencia, ennek segitsegevel inicializal, futtatja a jatekot
     */
    private Controller controller = Controller.GetInstanceOf();
    private DisplayManager dm = DisplayManager.GetInstance();
    private TextOutputManager tm = TextOutputManager.GetInstanceOf();
    private InputManager im = InputManager.GetInstanceOf();

    private Game() { }

    public void InitGame()
    {
        menuframe = new MenuFrame();
        endgameframe = new EndGameFrame();
        StartProgram();
    }

    public static Game GetInstanceOf(){
        if(instance==null)
            instance=new Game();
        return instance;
    }

    public void StartProgram()
    {
        endgameframe.setVisible(false);
        menuframe.setVisible(true);
    }

    public void StartGame(int numberofplayers)
    {
        controller.PreInit();
        dm.Init();

        menuframe.setVisible(false);
        System.out.println(numberofplayers + " darab jatekossal inditanank");
        gameframe = new GameFrame();
        gameframe.setVisible(true);
        controller.Init(numberofplayers);
    }

    public void ExitGame(boolean victory)
    {
        gameframe.setVisible(false);
        gameframe.dispose();
        endgameframe.SetVictory(victory);
        endgameframe.setVisible(true);
    }

    public void ExitProgram()
    {
        System.exit(0);
    }
}
