package game.controller;

import game.userinterface.*;

public class Game {
    GameFrame gameframe ;
    MenuFrame menuframe;

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
        StartProgram();
    }

    public static Game GetInstanceOf(){
        if(instance==null)
            instance=new Game();
        return instance;
    }

    public void StartProgram()
    {
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
        EndGameFrame endframe = new EndGameFrame(gameframe, "Asteroid Game [agbkp Edition]", victory);
    }

    public void BackToMenu() {
        gameframe.dispose();
        StartProgram();
    }

    public void ExitProgram()
    {
        System.exit(0);
    }
}
