package game.userinterface;

import javax.swing.*;
import java.util.ArrayList;

/**
 * adatok kiiratasaert felelos manager osztaly
 */
public class TextOutputManager {
    private static TextOutputManager instance;

    private NaploPanel naplopanel;

    private InfoPanel infopanel;

    private JLabel headlabel;

    private TextOutputManager() {}


    public static TextOutputManager GetInstanceOf(){
        if(instance == null)
            instance = new TextOutputManager();
        return instance;
    }

    /**
     * beallitja a megfelelo komponenseket
     * @param naplo: naploPanel
     * @param info: InfoPanel
     * @param head: HeadLabel
     */
    public void SetComponents(NaploPanel naplo, InfoPanel info, JLabel head)
    {
        naplopanel = naplo;
        infopanel = info;
        headlabel = head;
    }

    /**
     * Naploba iras metodusa
     * @param text: a kiirando szoveg
     */
    public void WriteToNaplo(ArrayList<String> text)
    {
        naplopanel.ScrollDownMode();
        naplopanel.WriteOut(text);
    }
    /**
     * InfoPanelra iras metodusa
     * @param info: a kiirando szoveg
     */
    public void WriteToInfo(ArrayList<String> info)
    {
        infopanel.WriteInfo(info);
    }

    /**
     * Kiirja a cimet
     * @param title: a kiirando szoveg
     */
    public void WriteToHead(String title)
    {
        headlabel.setText(title);
    }
}
