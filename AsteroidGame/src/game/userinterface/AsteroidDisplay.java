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

    public AsteroidDisplay(Asteroid subject, int x, int y) {
        this.subject = subject;
        subject.SetMyDisplay(this);
        GetShape().setBounds(x, y , 150, 150);
        SectorInit();
        TeleportSectorInit();
    }

    public boolean[][] getAllocatedAsteroidSectors() {
        return AllocatedAsteroidSectors;
    }

    boolean[][] AllocatedAsteroidSectors = new boolean[5][5];
    private void SectorInit(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
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
                AllocatedAsteroidSectors[x][y] = true;
                freeSector = true;
                d.SetSectorCoordinates(new Point(x, y));
            }
        }

        d.GetShape().x = GetShape().x + (x * (GetShape().width -30) ) / 4;
        d.GetShape().y = (GetShape().y + (y * (GetShape().height - 30)) / 4) + 30;
    }

    boolean[] AllocatedTeleportSectors= new boolean[9];
    ArrayList<TeleportDisplay> tds=new ArrayList<TeleportDisplay>();

    void TeleportSectorInit(){
        for(int i=0;i<9;i++)
            AllocatedTeleportSectors[i]=false;
    }
    void TeleportSectorAllocation(TeleportDisplay t){
        for(int i=0;i<9;i++){
            if(!AllocatedTeleportSectors[i]){
                AllocatedTeleportSectors[i]=true;
                int x;
                int y;
                if(i<5){
                    x = GetShape().x +  (i *GetShape().width) / 5;
                    y = GetShape().y; //+ GetShape().height;
                }
                else{
                    x = GetShape().x + GetShape().width - 30;
                    y = GetShape().y + (i-4) * GetShape().height / 5;
                }
                t.GetShape().setBounds(x,y,30,30);
                break;
            }
        }
    }
    @Override
    public void Paint(Graphics g2d) {
        if (subject.GetSunnearness()) {
            g2d.setColor(new Color(163, 45, 16));
        } else {
            g2d.setColor(new Color(150, 150, 150));
        }
        g2d.fillOval(GetShape().x, GetShape().y + 30, GetShape().width -30, GetShape().height- 30);
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
        g2d.drawOval(GetShape().x, GetShape().y + 30, GetShape().width - 30, GetShape().height -30);
        SetSelected(false);
        SetRoundoutline(false);
        SetisNeigbhour(false);
    }
    @Override
    public void Clear() {
        DisplayManager.GetInstance().RemoveAsteroidDisplay(this);
    }

}
