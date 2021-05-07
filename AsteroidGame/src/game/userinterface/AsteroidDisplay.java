package game.userinterface;

import game.logic.Asteroid;
import game.logic.Whereabout;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidDisplay extends Display{

    public Asteroid GetSubject() {
        return subject;
    }

    private Asteroid subject;

    public Rectangle[][] getMyfields() {
        return myfields;
    }

    private Rectangle[][] myfields = new Rectangle[4][4];

    public boolean[][] getAllocatedAsteroidSectors() {
        return AllocatedAsteroidSectors;
    }

    boolean[][] AllocatedAsteroidSectors = new boolean[4][4];
    private void SectorInit(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                AllocatedAsteroidSectors[i][j]=false;
            }
        }
    }

    public void CoordinateServer(Display d){
        Random random=new Random();
        boolean freeSector=false;
        int x=-1;
        int y=-1;

        while(!freeSector){
            x = random.nextInt(4);
            y = random.nextInt(4);
            if(!AllocatedAsteroidSectors[x][y]){
                AllocatedAsteroidSectors[x][y]=true;
                freeSector=true;
                d.SetSectorCoordinates(new Point(x,y));
            }
        }

        x = d.GetShape().x + (x * GetShape().width) / 4;
        y = d.GetShape().y + (y * GetShape().height) / 4;
    }
    private boolean isNeighbour;
    public void SetisNeighbour(boolean neighbour) {
        this.isNeighbour = neighbour;
    }

    public AsteroidDisplay(Asteroid subject, int x, int y) {
        this.subject = subject;
        subject.SetMyDisplay(this);
        GetShape().setBounds(x, y, 80, 80);
        SectorInit();
    }

    @Override
    public void Paint(Graphics g2d) {
        if (subject.GetSunnearness()) {
            g2d.setColor(new Color(163, 45, 16));
        } else {
            g2d.setColor(new Color(150, 150, 150));
        }
        g2d.fillOval(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        if (IsSelected()) {
            g2d.setColor(new Color(255, 20, 20));
            ArrayList<Whereabout> neighbours = subject.GetNeighbours();
            for (Whereabout neighbour: neighbours
                 ) {
                neighbour.GetDisplay().SetisNeigbhour(true);
            }
        }else if(IsRoundoutline()){
            g2d.setColor(new Color(250, 230, 20));
        }else if(isNeighbour){
            g2d.setColor(new Color(20,200,0));
        }
        g2d.drawOval(GetShape().x, GetShape().y, GetShape().width, GetShape().height);
        SetSelected(false);
        SetRoundoutline(false);
        isNeighbour = false;
    }
    @Override
    public void Clear() {
        DisplayManager.GetInstance().RemoveAsteroidDisplay(this);
    }

}
