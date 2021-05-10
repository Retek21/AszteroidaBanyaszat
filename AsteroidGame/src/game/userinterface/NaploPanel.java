package game.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

public class NaploPanel extends JPanel {
    private ArrayList<JLabel> lines;
    private JScrollPane parentPane;

    public NaploPanel()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(25,42,86));
        setBorder(BorderFactory.createLineBorder(Color.black));
        lines = new ArrayList<JLabel>();
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

    public void SetParentPane(JScrollPane jp) {
        parentPane = jp;
    }

    public void ScrollDownMode() {
        if(parentPane != null) {
            JScrollBar naploVerticalBar = parentPane.getVerticalScrollBar();
            AdjustmentListener downScroller = new AdjustmentListener() {
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    Adjustable adjustable = e.getAdjustable();
                    adjustable.setValue(adjustable.getMaximum());
                    naploVerticalBar.removeAdjustmentListener(this);
                }
            };
            naploVerticalBar.addAdjustmentListener(downScroller);
        }
    }
}
