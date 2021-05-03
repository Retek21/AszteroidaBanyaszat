package game.userinterface;

import javax.swing.*;
import java.awt.*;

public class OptionsComboBox extends JComboBox {
    public OptionsComboBox(){
        super();
        setFocusable(false);
        setBackground(new Color(64,115,158));
        setForeground(new Color(76,209,55));
    }
    public OptionsComboBox(String[] s){
        super(s);
        setFocusable(false);
        setBackground(new Color(64,115,158));
        setForeground(new Color(76,209,55));
    }
}
