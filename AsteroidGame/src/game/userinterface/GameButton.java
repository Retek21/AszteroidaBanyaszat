package game.userinterface;

import javax.swing.*;
import java.awt.*;
/*
* @author Szeredi Peter
* GameButtont megvalosito osztaly
* */
public class GameButton extends JButton {
    /*
    * gamebutton konstruktora
    * kijeloles eltavolitasa
    * */
    public GameButton(){
        super();
        setFocusable(false);
    }
    /*
    * custom konsruktor string paramaterkent atadva a nevet
    * kijelolest eltavolitja
    * beallitja a szineit
    * */
    public GameButton(String s){
        super(s);
        setFocusable(false);
        setBackground(new Color(25, 45, 62));
        setForeground(new Color(48,245,196));
    }
}
