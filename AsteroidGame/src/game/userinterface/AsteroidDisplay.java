package game.userinterface;

import game.logic.Asteroid;
import game.logic.Whereabout;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidDisplay extends WhereaboutDisplay{

    private Asteroid subject;

    private Color fillColor;
    private Color outlineColor;

    public Color GetFillColor() {
        return fillColor;
    }

    public void SetFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color GetOutlineColor() {
        return outlineColor;
    }

    public void SetOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public boolean[] GetAllocatedTeleportSectors() {
        return AllocatedTeleportSectors;
    }

    boolean[] AllocatedTeleportSectors= new boolean[9];
    boolean[][] AllocatedAsteroidSectors = new boolean[5][5];

    public AsteroidDisplay(Asteroid subject, int x, int y) {
        this.subject = subject;
        subject.SetDisplay(this);
        GetShape().setBounds(x, y , 100, 100);
        SetFillColor(new Color(51, 25, 0));
        SetOutlineColor(new Color(51, 25, 0));
        SectorInit();
        TeleportSectorInit();
    }

    public boolean[][] GetAllocatedAsteroidSectors() {
        return AllocatedAsteroidSectors;
    }

    private void SectorInit(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                AllocatedAsteroidSectors[i][j]=false;
            }
        }
    }

    public Asteroid GetSubject() {
        return subject;
    }

    public void EnititySectorAllocation(EntityDisplay d){
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
                d.SetSectorpoints(new Point(x,y));
            }
        }

        d.GetShape().x = GetShape().x + (x * (GetShape().width -15) ) / 4;
        d.GetShape().y = (GetShape().y + (y * (GetShape().height - 15)) / 4) + 15;
        d.GetShape().height = 15;
        d.GetShape().width = 15;
    }

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
                    y = GetShape().y;
                }
                else{
                    x = GetShape().x + GetShape().width - 15;
                    y = GetShape().y + (i-4) * GetShape().height / 5;
                }
                t.GetShape().setBounds(x,y,15,15);
                t.SetSectorpoint(i);
                break;
            }
        }
    }
    @Override
    public void Paint(Graphics g2d) {
        if (underSunStorm && blink % 2 == 0) SetFillColor(new Color(232, 65, 24));
        else if (exploding && blink % 2 == 0) SetFillColor(new Color(255, 0, 0));
        else if(subject.GetSunnearness())
            SetFillColor(new Color(153, 72, 25));
        else
            SetFillColor(new Color(51, 25, 0));

        g2d.setColor(GetFillColor());
        g2d.fillOval(GetShape().x, GetShape().y + 15, GetShape().width -15, GetShape().height- 15);
        if (IsSelected()) {
            g2d.setColor(new Color(255, 20, 20));
        }else if(IsRoundoutline()){
            g2d.setColor(new Color(250, 230, 20));
        }else if(IsNeighbour()){
            g2d.setColor(new Color(20,200,0));
        }
        g2d.drawOval(GetShape().x, GetShape().y + 15, GetShape().width - 15, GetShape().height -15);
    }
    @Override
    public void Clear() {
        DisplayManager.GetInstance().RemoveAsteroidDisplay(this);
        super.Clear();
    }

    @Override
    public void Notify() {

        if ((!underSunStorm || !exploding) && blink == 0) {
            underSunStorm = subject.GetOnFireness();
            exploding = subject.GetExplodingness();
            if (underSunStorm || exploding) {
                blink = DisplayManager.GetInstance().GetBlinkingTime();
            }
        }
        DisplayManager.GetInstance().repaint();
    }

    public void NotifyNeighbourhood() {
        ArrayList<Whereabout> neighbours = subject.GetNeighbours();
        for (Whereabout neighbour: neighbours
        ) {
            neighbour.GetDisplay().SetisNeigbhour(true);
        }
    }
}
