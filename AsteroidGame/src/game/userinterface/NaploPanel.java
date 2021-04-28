package game.userinterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NaploPanel extends JPanel {
    ArrayList<JLabel> lines = new ArrayList<JLabel>();
    GridLayout gr;

    public NaploPanel()
    {
        gr = new GridLayout();
        this.setLayout(gr);
    }

    public void WriteOut(String[] text)
    {
        gr.setRows(text.length);
        for(int i = lines.size(); i < text.length; i++)
        {
            //A tabulatort nem ismeri a label ezert at kell alakitani spacera
            JLabel templabel = new JLabel(text[i].replaceAll("\t", "        "));
            templabel.setForeground(new Color(140, 122, 230));
            lines.add(templabel);
            this.add(lines.get(i));
        }
    }
}
