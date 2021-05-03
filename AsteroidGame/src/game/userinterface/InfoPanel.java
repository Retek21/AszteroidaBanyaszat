package game.userinterface;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    public InfoPanel()
    {
        this.setLayout(new GridLayout(0,1));
        setBackground(new Color(25,42,86));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    public void WriteInfo(String[] info)
    {
        this.removeAll();
        for(int i = 0; i< info.length; i++)
        {
            JLabel templabel = new JLabel(info[i].replaceAll("\t", "        "));
            templabel.setForeground(new Color(140, 122, 230));
            this.add(templabel);
        }
    }
}
