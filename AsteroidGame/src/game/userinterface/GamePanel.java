package game.userinterface;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Manager manager;
    public void MouseClicked(){}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i =0; i < displaynumber; i++){
            displays[i].update();
        }
    }

}
