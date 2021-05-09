package game.userinterface;

import game.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame {

    InputManager inputmanager;

    TextOutputManager textoutputmanager;

    private NaploPanel naplopanel;
    private InfoPanel infopanel;
    private DisplayManager gamepanel;

    private GameButton dophasebutton;
    private GameButton movebutton;
    private GameButton drillbutton;
    private GameButton minebutton;
    private GameButton placebutton;
    private GameButton craftbutton;

    private OptionsComboBox craftable;
    private OptionsComboBox placeable;

    private JLabel phaselabel;

    private Game game;

    public GameFrame(Game g){
        game = g;
        setSize(1280, 800);
        setResizable(false);
        setTitle("Asteroid Game [agbkp Edition]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //komponensek felinicializalasa

        naplopanel = new NaploPanel();
        JScrollPane scrollnaplo = new JScrollPane(naplopanel);
        scrollnaplo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        infopanel = new InfoPanel();
        gamepanel = DisplayManager.GetInstance();
    /*    JScrollPane scrollablegamepanel = new JScrollPane(gamepanel);
        scrollablegamepanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollablegamepanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollablegamepanel.getVerticalScrollBar().addAdjustmentListener(new java.awt.event.AdjustmentListener(){
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent ae){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        scrollablegamepanel.repaint();
                    }
                });
            }
        });
        scrollablegamepanel.getHorizontalScrollBar().addAdjustmentListener(new java.awt.event.AdjustmentListener(){
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent ae){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        scrollablegamepanel.repaint();
                    }
                });
            }
        });*/

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
        craftable = new OptionsComboBox(new String[]{"Robot", "Teleport"});
        placeable = new OptionsComboBox(new String[]{"Coal", "Uranium", "Iron", "Ice", "Teleport"});

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
     //   leftside.add(scrollablegamepanel, c);

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

        //ActionListener felinicializalasa

        class ButtonActionListener implements ActionListener {
            public ButtonActionListener() {
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                inputmanager.ButtonPressed(e.getActionCommand(), (String)craftable.getSelectedItem(), (String)placeable.getSelectedItem());
            }
        }

        ButtonActionListener buttonactionlistener = new ButtonActionListener();

        //komponensek hozzaadasa
        dophasebutton.addActionListener(buttonactionlistener);
        movebutton.addActionListener(buttonactionlistener);
        drillbutton.addActionListener(buttonactionlistener);
        minebutton.addActionListener(buttonactionlistener);
        placebutton.addActionListener(buttonactionlistener);
        craftbutton.addActionListener(buttonactionlistener);

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

        MouseClickListener mouselistener = new MouseClickListener();
        gamepanel.addMouseListener(mouselistener);

    }
}
