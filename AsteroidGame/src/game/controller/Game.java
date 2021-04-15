package game.controller;

import game.controller.Controller;

import java.util.Scanner;

public class Game {
    /**
     * A kontroller osztalyra mutato referencia, ennek segitsegevel inicializal, futtatja a jatekot
     */
    private Controller controller;

    /**
     * Elinditja a programot, ez meg nem a jatek inditasa
     * A felhasznalotol beolvas egy parancsot, majd ezt felbontja részeire
     */
    public void StartProgram()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[ASTEROIDGAME - abgkp EDITION]");
        String input = scanner.next();
        String[] cmd = input.split(" ");
        switch(cmd[0])
        {
            case "start":
                Start(cmd);
                break;
            case "exit":
                Exit();
                break;
            default:
                Exit();
                break;
        }
    }

    /**
     *
     * @param param
     */
    public void Start(String[] param)
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
    }

    /**
     *
     */
    public void Exit(){}
}
