package game.userinterface;

import game.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Felelos a program inditasakor felugro ablak megjelenitesere
 */
public class MenuFrame extends JFrame {
    /**
     * a fopanel referenciaja
     */
    private JPanel mainpanel;
    /**
     * a jatek gomb referenciaja
     */
    private JButton playbutton;
    /**
     * az exit gomb referenciaja
     */
    private JButton exitbutton;
    /**
     * a jatek referenciaja
     */
    private Game game;

    /**
     * konstruktor, ami letrehozza a a menut, beallitja a szineket, betutipusokat,
     * hozzaadja a gombokat es a megfelelo attributumokat
     */
    public MenuFrame()
    {
        game = Game.GetInstanceOf();
        setSize(600, 400);
        setResizable(false);
        setTitle("Asteroid Game [agbkp Edition]");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel namelabel1 = new JLabel("ASTEROID GAME");
        namelabel1.setFont(new Font ("Verdana", Font.BOLD, 26));
        namelabel1.setForeground(new Color(249, 202,36));
        JLabel namelabel2 = new JLabel("abgkp EDITION");
        namelabel2.setFont(new Font ("Verdana", Font.BOLD, 26));
        namelabel2.setForeground(new Color(249, 202,36));
        playbutton = new JButton("Play");
        playbutton.setFocusable(false);
        playbutton.setBackground(new Color(72, 52, 212));
        playbutton.setForeground(new Color(106, 176, 76));
        exitbutton = new JButton("Exit");
        exitbutton.setFocusable(false);
        exitbutton.setBackground(new Color(72, 52, 212));
        exitbutton.setForeground(new Color(106, 176, 76));
        mainpanel = new JPanel(new GridBagLayout());
        mainpanel.setBackground(new Color(19, 15, 64));

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.gridx=0;
        c.gridy=0;
        c.weightx = 1;
        c.weighty = 0.15;
        c.anchor = GridBagConstraints.PAGE_END;
        mainpanel.add(namelabel1, c);
        c.gridy=1;
        c.anchor = GridBagConstraints.PAGE_START;
        mainpanel.add(namelabel2, c);
        c.anchor = GridBagConstraints.CENTER;
        c.fill=GridBagConstraints.VERTICAL;
        c.insets=new Insets(0,180,20,180);
        c.fill=GridBagConstraints.BOTH;
        c.gridy=2;
        c.weighty = 0.3;
        mainpanel.add(playbutton, c);
        c.insets=new Insets(0,180,40,180);
        c.gridy=3;
        mainpanel.add(exitbutton, c);
        this.add(mainpanel);

        /**
         * ActionListener felinicializalasa
         */
        class FrameActionListener implements ActionListener{
            public FrameActionListener(){}
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getActionCommand().equals("Play")) {
                    PlayPressed();
                }
                else
                    game.ExitProgram();
            }
        }
        FrameActionListener actionlistener = new FrameActionListener();
        playbutton.addActionListener(actionlistener);
        exitbutton.addActionListener(actionlistener);
    }

    /**
     * metodus, ami akkor hivodik, amikor a felhasznalo a play gombra kattint
     * Elojon egy felugroablak, ahol megkerdezi a jatekosok szamat
     */
    public void PlayPressed() {
        try {
            String tempstring = (String) JOptionPane.showInputDialog(this, "Number of Players:", "Asteroid Game [agbkp Edition]", JOptionPane.PLAIN_MESSAGE);
            if(tempstring != null)
            {
                int numberofplayers = Integer.parseInt(tempstring);
                if (numberofplayers < 2 )
                    JOptionPane.showMessageDialog(this, "More than 1 player is needed.", "Error", JOptionPane.WARNING_MESSAGE);
                else if (numberofplayers >5 )
                        JOptionPane.showMessageDialog(this, "Less than 6 player is needed.", "Error", JOptionPane.WARNING_MESSAGE);
                else
                        game.StartGame(numberofplayers);

            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Incorrect Input.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


}
