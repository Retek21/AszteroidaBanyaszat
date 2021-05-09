package game.userinterface;

import game.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EndGameFrame extends JFrame {

    private Game game;

    private JLabel label;

    public EndGameFrame()
    {
        game = Game.GetInstanceOf();

        setSize(300, 200);
        setResizable(false);
        setTitle("Asteroid Game [agbkp Edition]");
        setLocationRelativeTo(null);

        label = new JLabel("Default");
        label.setFont(new Font("Verdana", Font.BOLD, 26));
        label.setForeground(new Color(249, 202,36));

        JButton okbutton = new JButton("ok");
        okbutton.setFocusable(false);
        okbutton.setBackground(new Color(72, 52, 212));
        okbutton.setForeground(new Color(106, 176, 76));

        JPanel mainpanel = new JPanel(new GridBagLayout());
        mainpanel.setBackground(new Color(19, 15, 64));

        this.add(mainpanel);


        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        c.insets=new Insets(30,50,0 ,30);
        c.gridx=0;
        c.gridy=0;
        mainpanel.add(label, c);

        c.insets=new Insets(30,60,30 ,60);
        c.gridx=0;
        c.gridy=1;
        mainpanel.add(okbutton, c);


        setBackground(new Color(19, 15, 64));


        class FrameActionListener implements ActionListener {
            public FrameActionListener(){}
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getActionCommand().equals("ok")) {
                    game.StartProgram();
                }
            }
        }

        FrameActionListener actionlistener = new FrameActionListener();
        okbutton.addActionListener(actionlistener);


        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                game.StartProgram();
            }
        });
    }

    public void SetVictory(boolean vic)
    {
        if(vic)
            label.setText("VICTORY!  :)");
        else
            label.setText("YOU LOST! :(");
    }
}
