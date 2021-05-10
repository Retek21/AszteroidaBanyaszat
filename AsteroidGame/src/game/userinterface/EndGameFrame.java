package game.userinterface;

import game.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
* @author Szabo Gergo
*
* */
public class EndGameFrame extends JDialog {

    private Game game;

    private JLabel label;
    private JLabel sublabel;

    public EndGameFrame(JFrame j, boolean vic, String reason)
    {
        super(j, Dialog.ModalityType.DOCUMENT_MODAL);
        game = Game.GetInstanceOf();

        setSize(500, 300);
        setResizable(false);
        setTitle("Asteroid Game [agbkp Edition]");
        setLocationRelativeTo(null);

        if (vic)
            label = new JLabel("VICTORY! :)");
        else
            label = new JLabel("DEFEAT! :(");
        label.setFont(new Font("Verdana", Font.BOLD, 36));
        label.setForeground(new Color(249, 202,36));

        sublabel = new JLabel(reason);
        sublabel.setFont(new Font("Verdana", Font.BOLD, 14));
        sublabel.setForeground(new Color(249, 202,36));

        JButton okbutton = new JButton("OK");
        okbutton.setFocusable(false);
        okbutton.setBackground(new Color(72, 52, 212));
        okbutton.setForeground(new Color(106, 176, 76));

      /*  JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
        mainpanel.add(label);
        mainpanel.add(sublabel);
        mainpanel.add(okbutton);
        this.add(mainpanel);*/
      /*  this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(label);
        this.add(sublabel);
        this.add(okbutton);*/

        JPanel mainpanel = new JPanel(new GridBagLayout());
        mainpanel.setBackground(new Color(19, 15, 64));

        this.add(mainpanel);


        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 1;

        c.insets=new Insets(15,0,0 ,0);
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.CENTER;
        mainpanel.add(label, c);

        c.gridy=1;
        c.insets = new Insets(0, 0, 50, 0);
        mainpanel.add(sublabel, c);

        c.insets=new Insets(30,60,30 ,60);
        c.gridx=0;
        c.gridy=2;
        c.fill=GridBagConstraints.BOTH;
        mainpanel.add(okbutton, c);


        setBackground(new Color(19, 15, 64));


        class FrameActionListener implements ActionListener {
            public FrameActionListener(){}
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getActionCommand().equals("OK")) {
                    game.BackToMenu();
                }
            }
        }

        FrameActionListener actionlistener = new FrameActionListener();
        okbutton.addActionListener(actionlistener);


        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                game.BackToMenu();
            }
        });


        this.setVisible(true);
    }

    public void SetVictory(boolean vic)
    {
        if(vic)
            label.setText("VICTORY!  :)");
        else
            label.setText("YOU LOST! :(");
    }
}
