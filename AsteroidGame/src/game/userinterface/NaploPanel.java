package game.userinterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NaploPanel extends JPanel {
    private ArrayList<JLabel> lines = new ArrayList<JLabel>();

    public NaploPanel()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(25,42,86));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void WriteOut(ArrayList<String> text)
    {
        for(int i = 0; i < text.size(); i++)
        {
            //A tabulatort nem ismeri a label ezert at kell alakitani spacera
            JLabel templabel = new JLabel(text.get(i).replaceAll("\t", "        "));
            templabel.setForeground(new Color(140, 122, 230));
            lines.add(templabel);
            this.add(lines.get(lines.size()-1));

        }
        revalidate();
        repaint();

    }
}
