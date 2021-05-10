package game.userinterface;

import game.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Felelossege a jatekablak megjelenitese
 */
public class GameFrame extends JFrame {

    /**
     * referencia az InputManagerre
     */
    InputManager inputmanager;

    /**
     * referencia a TextOutputManagerre
     */
    TextOutputManager textoutputmanager;

    /**
     * A naplopanel referenciaja
     */
    private NaploPanel naplopanel;
    /**
     * Az infopanel referenciaja
     */
    private InfoPanel infopanel;

    /**
     * A DisplayManager referenciaja
     */
    private DisplayManager gamepanel;

    /**
     * A DoPhaseButton referenciaja
     */
    private GameButton dophasebutton;
    /**
     * A MoveButton referenciaja
     */
    private GameButton movebutton;
    /**
     * A DrillButton referenciaja
     */
    private GameButton drillbutton;

    /**
     * A MineButton referenciaja
     */
    private GameButton minebutton;

    /**
     * A PlaceButton referenciaja
     */
    private GameButton placebutton;

    /**
     * A CraftButton referenciaja
     */
    private GameButton craftbutton;

    /**
     * A Craftable combobox referenciaja
     */
    private JComboBox<String> craftable;
    /**
     * A Placeable combobox referenciaja
     */
    private JComboBox<String> placeable;

    /**
     * A PhaseLabel referenciaja
     */
    private JLabel phaselabel;

    /**
     * A Game referenciaja
     */
    private Game game;

    /**
     * konstruktor, ami letrehozza a jatekablakot, es az osszes panelt
     * a megfelelo adatokkal
     */
    public GameFrame(){
        game = Game.GetInstanceOf();
        setSize(1280, 800);
        setResizable(false);
        setTitle("Asteroid Game [agbkp Edition]");
        setLocationRelativeTo(null);

        //komponensek felinicializalasa

        Dimension rightside_size = new Dimension(240, 2* 381);
        naplopanel = new NaploPanel(new Dimension(rightside_size.width, rightside_size.height / 2));
        JScrollPane scrollnaplo = new JScrollPane(naplopanel);
        naplopanel.SetParentPane(scrollnaplo);
        scrollnaplo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        infopanel = new InfoPanel(new Dimension(rightside_size.width, rightside_size.height / 2));
        gamepanel = DisplayManager.GetInstance();

        ContainerPanel headpanel = new ContainerPanel();
        phaselabel = new JLabel("Default Value");
        phaselabel.setFont(new Font ("Verdana", Font.BOLD, 26));
        phaselabel.setForeground(new Color(140, 122, 230));
        dophasebutton = new GameButton("DoPhase");
        movebutton = new GameButton("Move");
        drillbutton = new GameButton("Drill");
        minebutton = new GameButton("Mine");
        placebutton = new GameButton("Place");
        craftbutton = new GameButton("Craft");
        ContainerPanel buttonpanel = new ContainerPanel();
        craftable = new JComboBox<String>(new String[]{"Robot", "Teleport"});
        craftable.setFocusable(false);
        craftable.setBackground(new Color(64,115,158));
        craftable.setForeground(new Color(76,209,55));
        placeable = new JComboBox<String>(new String[]{"Coal", "Uranium", "Iron", "Ice", "Teleport"});
        placeable.setFocusable(false);
        placeable.setBackground(new Color(64,115,158));
        placeable.setForeground(new Color(76,209,55));

        //managerek elkeszitese, bekotese

        inputmanager = InputManager.GetInstanceOf();
        inputmanager.SetComponents(dophasebutton,movebutton,drillbutton,minebutton,placebutton,craftbutton);

        textoutputmanager = TextOutputManager.GetInstanceOf();
        textoutputmanager.SetComponents(naplopanel, infopanel, phaselabel);

        //komponensek elrendezese

        //A fopanel szetosztasa 2 reszre

        this.setLayout(new GridBagLayout());
        JPanel leftside = new JPanel();
        JPanel rightside = new JPanel();
        rightside.setPreferredSize(rightside_size);
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


        rightside.setLayout(new GridLayout(2,1));
        rightside.add(scrollnaplo);

        rightside.add(infopanel);


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


        /**
         * Az ActionListener felinicializalasa, a gombokat es a comboboxokat hasznalva
         */
        class ButtonActionListener implements ActionListener {
            public ButtonActionListener() {
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                inputmanager.ButtonPressed(e.getActionCommand(), (String)craftable.getSelectedItem(), (String)placeable.getSelectedItem());
            }
        }

        ButtonActionListener buttonactionlistener = new ButtonActionListener();


        dophasebutton.addActionListener(buttonactionlistener);
        movebutton.addActionListener(buttonactionlistener);
        drillbutton.addActionListener(buttonactionlistener);
        minebutton.addActionListener(buttonactionlistener);
        placebutton.addActionListener(buttonactionlistener);
        craftbutton.addActionListener(buttonactionlistener);

        /**
         * A MouseClickListener felinicializalasa
         * MousePressed esemenyre tovabbadja az InputManagernek a klikkeles koordinatait
         */
        class MouseClickListener implements MouseListener {
            public MouseClickListener() {}
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }
            @Override
            public void mouseExited(MouseEvent e){}
            @Override
            public void mousePressed(MouseEvent e){ inputmanager.GameFieldClicked(e.getX(), e.getY()); }
            @Override
            public void mouseReleased(MouseEvent e){}
            @Override
            public void mouseEntered(MouseEvent e){}
        }

        MouseListener[] listeners = gamepanel.getMouseListeners();
        if (listeners.length != 0) gamepanel.removeMouseListener(listeners[0]);
        MouseClickListener mouselistener = new MouseClickListener();
        gamepanel.addMouseListener(mouselistener);


        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                game.BackToMenu();
            }
        });
    }
}
