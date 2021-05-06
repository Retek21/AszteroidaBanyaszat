package game.userinterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    public GamePanel(){
        super();
        setBackground(new Color(50,56,65));
        setBorder(BorderFactory.createLineBorder(Color.black));

    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        ArrayList<AsteroidDisplay> asteroidDisplays = DisplayManager.GetInstance().GetAsteroidDisplays();
        ArrayList<UfoDisplay> ufoDisplays = DisplayManager.GetInstance().GetUfoDisplays();
        ArrayList<SettlerDisplay> settlerDisplays = DisplayManager.GetInstance().GetSettlerDisplays();
        ArrayList<TeleportDisplay> teleportDisplays = DisplayManager.GetInstance().GetTeleportDisplays();
        ArrayList<RobotDisplay> robotDisplays = DisplayManager.GetInstance().GetRobotDisplays();

        DisplayManager.GetInstance().GetSunDisplay().Paint(g);
        for (AsteroidDisplay ad: asteroidDisplays)
            ad.Paint(g);
        for(TeleportDisplay td:teleportDisplays)
            td.Paint(g);
        for (SettlerDisplay sd:settlerDisplays)
            sd.Paint(g);
        for(RobotDisplay rd:robotDisplays)
            rd.Paint(g);
        for(UfoDisplay ud:ufoDisplays)
            ud.Paint(g);
    }

    public void DrawDisplays(){
        repaint();
    }
}
