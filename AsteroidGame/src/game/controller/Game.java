package game.controller;

import game.userinterface.DisplayManager;
import game.userinterface.EndGameFrame;
import game.userinterface.GameFrame;
import game.userinterface.MenuFrame;

public class Game {
    GameFrame gameframe ;
    MenuFrame menuframe;
    EndGameFrame endgameframe;

    private static Game instance;

    /**
     * A kontroller osztalyra mutato referencia, ennek segitsegevel inicializal, futtatja a jatekot
     */
    private Controller controller = Controller.GetInstanceOf();


    private Game() { }

    public void InitGame()
    {
        gameframe = new GameFrame();
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
        menuframe.setVisible(false);
        System.out.println(numberofplayers + " darab jatekossal inditanank");
        gameframe.setVisible(true);
        controller.Init(numberofplayers);
    }

    public void ExitGame(boolean victory)
    {
        gameframe.setVisible(false);
        endgameframe.SetVictory(victory);
        endgameframe.setVisible(true);
    }

    public void ExitProgram()
    {
        endgameframe.dispose();
        gameframe.dispose();
        menuframe.dispose();
    }
}
