package game.userinterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NaploPanel extends JPanel {
    private ArrayList<JLabel> lines = new ArrayList<JLabel>();
    int startnum;

    public NaploPanel()
    {
        this.setLayout(new GridLayout(0,1));
        setBackground(new Color(25,42,86));
        setBorder(BorderFactory.createLineBorder(Color.black));
        startnum = 0;
        for(int i = 0; i < 25; i++)
        {
            JLabel templabel = new JLabel(" ");
            templabel.setForeground(new Color(140, 122, 230));
            lines.add(templabel);
            this.add(lines.get(i));
        }
    }

    public void WriteOut(ArrayList<String> text)
    {
        for(int i = 0; i < text.size(); i++)
        {
            if(startnum > 24)
            {
                //A tabulatort nem ismeri a label ezert at kell alakitani spacera
                JLabel templabel = new JLabel(text.get(i).replaceAll("\t", "        "));
                templabel.setForeground(new Color(140, 122, 230));
                lines.add(templabel);
                this.add(lines.get(lines.size()-1));
            }
            else
            {
                lines.get(startnum).setText(text.get(i).replaceAll("\t", "        "));
                startnum++;
            }
        }
    }
}
