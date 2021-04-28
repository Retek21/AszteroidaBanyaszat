package game.userinterface;

import game.controller.Game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private NaploPanel naplopanel;
    private InfoPanel infopanel;
    private GamePanel gamepanel;
    private HeadPanel headpanel;
    private GameButton dophasebutton;
    private GameButton movebutton;
    private GameButton drillbutton;
    private GameButton minebutton;
    private GameButton placebutton;
    private GameButton craftbutton;
    private JComboBox craftable;
    private JComboBox placeable;
    private JLabel phaselabel;
    private Game game;

    public GameFrame(Game g){
        game = g;
        setSize(1200, 900);
        setResizable(false);
        setTitle("Asteroid Game [agbkp Edition]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //komponensek felinicializalasa

        naplopanel = new NaploPanel();
        JScrollPane scrollnaplo = new JScrollPane(naplopanel);
        scrollnaplo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        infopanel = new InfoPanel();
        gamepanel = new GamePanel();
        headpanel = new HeadPanel();
        phaselabel = new JLabel("Default Value");
        phaselabel.setFont(new Font ("Verdana", Font.BOLD, 26));
        phaselabel.setForeground(new Color(140, 122, 230));
        dophasebutton = new GameButton("DoPhase");
        dophasebutton.setBackground(new Color(25, 45, 62));
        dophasebutton.setForeground(new Color(48,245,196));
        movebutton = new GameButton("Move");
        movebutton.setBackground(new Color(25, 45, 62));
        movebutton.setForeground(new Color(48,245,196));
        drillbutton = new GameButton("Drill");
        drillbutton.setBackground(new Color(25, 45, 62));
        drillbutton.setForeground(new Color(48,245,196));
        minebutton = new GameButton("Mine");
        minebutton.setBackground(new Color(25, 45, 62));
        minebutton.setForeground(new Color(48,245,196));
        placebutton = new GameButton("Place");
        placebutton.setBackground(new Color(25, 45, 62));
        placebutton.setForeground(new Color(48,245,196));
        craftbutton = new GameButton("Craft");
        craftbutton.setBackground(new Color(25, 45, 62));
        craftbutton.setForeground(new Color(48,245,196));
        JPanel buttonpanel = new JPanel();
        craftable = new JComboBox(new String[]{"Robot", "Teleport"});
        craftable.setFocusable(false);
        craftable.setBackground(new Color(64,115,158));
        craftable.setForeground(new Color(76,209,55));
        placeable = new JComboBox(new String[]{"Coal", "Uranium", "Iron", "Ice", "Teleport"});
        placeable.setFocusable(false);
        placeable.setBackground(new Color(64,115,158));
        placeable.setForeground(new Color(76,209,55));

        //Panelek színei

        headpanel.setBackground(new Color(25,42,86));
        gamepanel.setBackground(new Color(50,56,65));
        buttonpanel.setBackground(new Color(25,42,86));
        naplopanel.setBackground(new Color(25,42,86));
        infopanel.setBackground(new Color(25,42,86));


        headpanel.setBorder(BorderFactory.createLineBorder(Color.black));
        gamepanel.setBorder(BorderFactory.createLineBorder(Color.black));
        buttonpanel.setBorder(BorderFactory.createLineBorder(Color.black));
        naplopanel.setBorder(BorderFactory.createLineBorder(Color.black));
        infopanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //komponensek elrendezese

        //A fopanel szetosztasa 2 reszre

        this.setLayout(new GridBagLayout());
        JPanel leftside = new JPanel();
        JPanel rightside = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 300;
        this.add(leftside, c);
        c.weightx = 2;
        c.ipadx = 0;
        c.gridx = 1;
        this.add(rightside, c);

        //A baloldali panel szetosztasa

        leftside.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx=0;
        c.gridy=0;
        leftside.add(headpanel, c);

        c.gridy=1;
        c.gridheight = 3;
        c.ipady = 600;
        leftside.add(gamepanel, c);

        c.gridy=4;
        c.gridheight = 1;
        c.ipady=0;
        leftside.add(buttonpanel, c);

        //a jobboldali panel szetosztasa

        rightside.setLayout(new GridLayout(2,1));
        //c.fill = GridBagConstraints.BOTH;
       // c.gridx=0;
        //c.gridy=0;
        rightside.add(scrollnaplo);

        //c.gridy=1;
        rightside.add(infopanel);

        //a headpanel elrendezése

        headpanel.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridy = 0;
        c.gridx = 0;
        headpanel.add(phaselabel, c);

        c.gridx = 1;
        c.ipadx = 40;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        headpanel.add(dophasebutton, c);

        //a buttonpanel elrendezese

        buttonpanel.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 60;
        c.ipady = 7;
        c.gridy = 0;
        c.gridx = 0;
        buttonpanel.add(movebutton, c);

        c.gridx = 1;
        buttonpanel.add(drillbutton, c);

        c.gridx = 2;
        buttonpanel.add(minebutton, c);

        c.gridx = 3;
        buttonpanel.add(placebutton, c);

        c.gridx = 4;
        buttonpanel.add(craftbutton, c);

        c.ipadx = 48;
        c.gridy = 1;
        c.gridx = 3;
        buttonpanel.add(placeable, c);

        c.gridy = 1;
        c.gridx = 4;
        buttonpanel.add(craftable, c);

        naplopanel.WriteOut(new String[]{"TEST1"});
        naplopanel.WriteOut(new String[]{"TEST1","TEST2"});
        naplopanel.WriteOut(new String[]{"TEST1", "TEST2","TEST3", "\tTEST3", "\t\tTEST3"});
        naplopanel.WriteOut(new String[]{"TEST1", "TEST2","TEST3", "\tTEST3", "\t\tTEST3", "TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4","TEST4", "TEST4"});
    }
}
