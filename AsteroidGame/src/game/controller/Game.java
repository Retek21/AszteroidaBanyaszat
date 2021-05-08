package game.controller;

import game.userinterface.GameFrame;
import game.userinterface.MenuFrame;

public class Game {
    GameFrame gameframe = new GameFrame(this);
    MenuFrame menuframe = new MenuFrame(this);

    /**
     * A kontroller osztalyra mutato referencia, ennek segitsegevel inicializal, futtatja a jatekot
     */
    private Controller controller=Controller.GetInstanceOf();

    /**
     * Elinditja a programot, ez meg nem a jatek inditasa
     * A felhasznalotol beolvas egy parancsot, majd ezt felbontja részeire
     */
    /*
    public void StartProgram()
    {
        controller = Controller.GetInstanceOf();
        Scanner scanner = new Scanner(System.in);
        System.out.println("[ASTEROIDGAME - abgkp EDITION]");
        String input = scanner.nextLine();
        String[] cmd = input.split(" ");
        switch(cmd[0])
        {
            case "start":
                Start(cmd);
                break;
            case "exit":
                break;
            default:
                break;
        }
    }*/
    public void StartProgram(){
        menuframe.setVisible(true);
    }

    /**
     * A megfeleo parancsparameterekkel meghivja a jatek ket
     * fazisat lebonyolito metodust. Eloszor a StartInitPhase-t, majd
     * a StartGamePhase-t.
     * @param - Parancsparameterek, amelyek kellenek a jatek megfelelo inditasahoz
     */
    /*public void Start(String[] param)
    {
        int b = -1,c = -1;
        boolean m = false, l = false;

        for(int i = 1; i< param.length; i++)    //a startot nem kell beolvasni megint
        {
            switch(param[i])
            {
                case "-m":
                    m = true;
                    break;
                case "-l":
                    l = true;
                    break;
                case "-b":
                    b = ++i;
                    break;
                case "-c":
                    c = ++i;
                default:
                    break;
            }
        }

        if(b == -1)    controller.StartInitPhase();
        else           controller.StartInitPhaseFromFile(param[b]);

        if(c == -1)    controller.StartGamePhase(m,l);
        else           controller.StartGamePhaseFromFile(param[c], m, l);
    }*/


    public void StartGame(int numberofplayers){
        menuframe.setVisible(false);
        System.out.println(numberofplayers + " darab jatekossal inditanank");
        gameframe.setVisible(true);
        controller.Init(numberofplayers);
    }

    public void ExitProgram(){

    }
}
