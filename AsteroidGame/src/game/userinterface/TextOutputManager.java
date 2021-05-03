package game.userinterface;

import javax.swing.*;
import java.util.ArrayList;

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

    public void SetComponents(NaploPanel naplo, InfoPanel info, JLabel head)
    {
        naplopanel = naplo;
        infopanel = info;
        headlabel = head;
    }

    public void WriteToNaplo(ArrayList<String> text)
    {
        naplopanel.WriteOut(text);
    }

    public void WriteToInfo(ArrayList<String> info)
    {
        infopanel.WriteInfo(info);
    }

    public void WriteToHead(String title)
    {
        headlabel.setText(title);
    }
}
