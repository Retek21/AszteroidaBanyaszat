package game.userinterface;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {
    private DisplayManager manager;
    public GameButton(){
        super();
        setFocusable(false);
    }
    public GameButton(String s){
        super(s);
        setFocusable(false);
        setBackground(new Color(25, 45, 62));
        setForeground(new Color(48,245,196));
    }
}
