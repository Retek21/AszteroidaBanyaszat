package game.userinterface;

import javax.swing.*;

public class GameButton extends JButton {
    private Manager manager;
    public GameButton(){ super(); setFocusable(false); }
    public GameButton(String s){ super(s); setFocusable(false); }
    public void ActionPerformed(){}
}
