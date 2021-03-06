package game.userinterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Az az egyes kivalaszott objektumok informacioit jeleniti meg.
 * A JPanel leszarmazotja.
 * @author Szeredi Peter
 */
public class InfoPanel extends JPanel {

    private Dimension size;

    /**
     * A konstruktor, inicializalja a panel parametereit.
     * @param _size: a panel merete
     */
    public InfoPanel(Dimension _size)
    {
        size = _size;
        this.setLayout(new GridLayout(26,1));
        setBackground(new Color(25,42,86));
        setBorder(BorderFactory.createLineBorder(Color.black));

    }

    /**
     * Kiirja a paramaterul kapott infot formazva.
     * @param info: a kiirando String lista
     */
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

    @Override
    public Dimension getPreferredSize() {
        return size;
    }
}
