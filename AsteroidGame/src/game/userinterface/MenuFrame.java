package game.userinterface;

import game.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private JPanel mainpanel;
    private JButton playbutton;
    private JButton exitbutton;
    private Game game;

    public MenuFrame(Game g)
    {
        game = g;
        setSize(600, 400);
        setResizable(false);
        setTitle("Asteroid Game [agbkp Edition]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel namelabel1 = new JLabel("ASTEROID GAME");
        namelabel1.setFont(new Font ("Verdana", Font.BOLD, 26));
        JLabel namelabel2 = new JLabel("abgkp EDITION");
        namelabel2.setFont(new Font ("Verdana", Font.BOLD, 26));
        playbutton = new JButton("play");
        exitbutton = new JButton("exit");
        mainpanel = new JPanel(new GridBagLayout());

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

        class FrameActionListener implements ActionListener{
            public FrameActionListener(){}
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getActionCommand().equals("play")) {
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

    public void PlayPressed(){
        try {
            String tempstring = (String) JOptionPane.showInputDialog(this, "Number of Players:", "Asteroid Game [agbkp Edition]", JOptionPane.PLAIN_MESSAGE);
            if(tempstring != null)
            {
                int numberofplayers = Integer.parseInt(tempstring);
                if (numberofplayers < 2)
                    JOptionPane.showMessageDialog(this, "More than 1 player is needed.", "Error", JOptionPane.WARNING_MESSAGE);
                else
                    game.StartGame(numberofplayers);
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Incorrect Input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
