package game.userinterface;

import game.logic.Entity;
import game.logic.Settler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SettlerDisplay extends Display{
    private Settler subject;
    private boolean moved;

    public SettlerDisplay(Settler subject, int x, int y) {
        this.subject = subject;
        subject.AddDisplay(this);
        getShape().setBounds(x,y,50,50);
    }

    @Override
    public void RoundOutline(Graphics2D g2d){

    }

    @Override
    public void Update(Graphics2D g2d){
        ArrayList<Entity> subjectEntities = subject.GetAsteroid().GetEntities();
        Random random = new Random();
        int x = random.nextInt(1200);
        int y = random.nextInt(900);
        for (Entity et: subjectEntities) {

        }
    }
    public void SelectOutline(){}
    @Override
    public void Clear(){
            DisplayManager.GetInstance().RemoveSettlerDisplay(this);
    }
}
