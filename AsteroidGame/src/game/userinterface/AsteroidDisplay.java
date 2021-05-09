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
    private boolean[] AllocatedTeleportSectors= new boolean[9];
    private boolean[][] AllocatedAsteroidSectors = new boolean[5][5];

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

    public Asteroid GetSubject() {
        return subject;
    }

    public boolean[] GetAllocatedTeleportSectors() {
        return AllocatedTeleportSectors;
    }

    public boolean[][] GetAllocatedAsteroidSectors() {
        return AllocatedAsteroidSectors;
    }

    public AsteroidDisplay(Asteroid subject, int x, int y) {
        this.subject = subject;
        subject.SetDisplay(this);
        GetShape().setBounds(x, y , 150, 150);
        SetFillColor(new Color(51, 25, 0));
        SetOutlineColor(new Color(51, 25, 0));
        SectorInit();
        TeleportSectorInit();
    }

    private void SectorInit(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                AllocatedAsteroidSectors[i][j]=false;
            }
        }
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

        d.GetShape().x = GetShape().x + (x * (GetShape().width -30) ) / 4;
        d.GetShape().y = (GetShape().y + (y * (GetShape().height - 30)) / 4) + 30;
        d.GetShape().height = 20;
        d.GetShape().width = 20;
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
                    x = GetShape().x + GetShape().width - 30;
                    y = GetShape().y + (i-4) * GetShape().height / 5;
                }
                t.GetShape().setBounds(x,y,30,30);
                t.SetSectorpoint(i);
                break;
            }
        }
    }
    @Override
    public void Paint(Graphics g2d) {
        g2d.setColor(GetFillColor());
        g2d.fillOval(GetShape().x, GetShape().y + 30, GetShape().width -30, GetShape().height- 30);
        if (IsSelected()) {
            g2d.setColor(new Color(255, 20, 20));
        }else if(IsRoundoutline()){
            g2d.setColor(new Color(250, 230, 20));
        }else if(IsNeighbour()){
            g2d.setColor(new Color(20,200,0));
        }
        g2d.drawOval(GetShape().x, GetShape().y + 30, GetShape().width - 30, GetShape().height -30);
    }
    @Override
    public void Clear() {
        DisplayManager.GetInstance().RemoveAsteroidDisplay(this);
        super.Clear();
    }

    @Override
    public void Notify() {
        if(subject.GetSunnearness()) {
            SetFillColor(new Color(110, 73, 13));
        }else{
            SetFillColor(new Color(51, 25, 0));
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
