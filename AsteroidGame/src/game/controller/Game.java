package game.controller;

import game.userinterface.*;

/**
 * A jatek osztalya, felelos az inditasert, befejezesert
 */
public class Game {
    /**
     * a jatek kezdoablaka
     */
    GameFrame gameframe ;
    /**
     * a menu kezdoablaka
     */
    MenuFrame menuframe;

    private static Game instance;

    /**
     * A kontroller osztalyra mutato referencia, ennek segitsegevel inicializal, futtatja a jatekot
     */
    private Controller controller = Controller.GetInstanceOf();
    /**
     * referencia a DisplayManager-re
     */
    private DisplayManager dm = DisplayManager.GetInstance();
    /**
     * referencia a TextOutputManager-re
     */
    private TextOutputManager tm = TextOutputManager.GetInstanceOf();
    /**
     * referencia az InputManager-re
     */
    private InputManager im = InputManager.GetInstanceOf();

    /**
     * default konstruktor
     */
    private Game() { }

    /**
     * game inicializalasa, egy uj menuframe letrehozasa,
     * es a program inditasa
     */
    public void InitGame()
    {
        menuframe = new MenuFrame();
        StartProgram();
    }

    /**
     * visszaadja az instance-t
     * @return: az objektum
     */
    public static Game GetInstanceOf(){
        if(instance==null)
            instance=new Game();
        return instance;
    }

    /**
     * a program inditasa
     */
    public void StartProgram()
    {
        menuframe.setVisible(true);
    }

    /**
     * A jatek inditasa a kepott felhasznalokkal parameterezve
     * @param numberofplayers
     */
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

    /**
     * A jatek vege, letrehoz egy uj endframe-t a megfelelo parameterekkel
     * @param victory
     * @param reason
     */
    public void ExitGame(boolean victory, String reason)
    {
        EndGameFrame endframe = new EndGameFrame(gameframe, victory, reason);
    }

    /**
     * visszalepes a menube, uj program inditasa
     */
    public void BackToMenu() {
        gameframe.dispose();
        StartProgram();
    }

    /**
     * kilepes a programbol
     */
    public void ExitProgram()
    {
        System.exit(0);
    }
}
