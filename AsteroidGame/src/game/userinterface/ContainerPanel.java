package game.userinterface;

import javax.swing.*;
import java.awt.*;
/**
* @author Szeredi Peter
* containerpanel ami tartalmazza a tobbi panelt
* */
public class ContainerPanel extends JPanel {
    /**
    * beallitja a hatterszinet es a borderenek a colorjat
    * */
    public ContainerPanel(){
        super();
        setBackground(new Color(25,42,86));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
