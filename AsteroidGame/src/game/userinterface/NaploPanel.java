package game.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

/**
 * a naplo irasaert felelos
 */
public class NaploPanel extends JPanel {
    /**
     * a szovegsorok listaja
     */
    private ArrayList<JLabel> lines;
    private JScrollPane parentPane;
    private Dimension size;

    /**
     * konstruktor, beallitja a meretet es a szint, letrehoz egy uj listat a szovegnek
     * @param _size
     */
    public NaploPanel(Dimension _size)
    {
        size = _size;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(25,42,86));
        setBorder(BorderFactory.createLineBorder(Color.black));
        lines = new ArrayList<JLabel>();
    }

    /**
     * adatok kiirasa a panelre
     * @param text: a kiirando szoveg
     */
    public void WriteOut(ArrayList<String> text)
    {
        for(int i = 0; i < text.size(); i++)
        {
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

    /**
     * a panelen valo gorgetest segito metodus
     */
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
