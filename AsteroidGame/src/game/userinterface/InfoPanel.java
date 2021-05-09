package game.userinterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoPanel extends JPanel {
    public InfoPanel()
    {
        this.setLayout(new GridLayout(26,1));
        setBackground(new Color(25,42,86));
        setBorder(BorderFactory.createLineBorder(Color.black));

    }
    public void WriteInfo(ArrayList<String> info)
    {
        this.removeAll();
        for(int i = 0; i< info.size(); i++)
        {
            JLabel templabel = new JLabel(info.get(i).replaceAll("\t", "        "));
            templabel.setForeground(new Color(140, 122, 230));
            this.add(templabel);
        }
        revalidate();
        repaint();
    }
}
