package game.main;

import game.logic.*;
import game.controller.*;

public class Main {
    public static void main(String[] args) {
        Game g = Game.GetInstanceOf();
        g.InitGame();
    }
}
