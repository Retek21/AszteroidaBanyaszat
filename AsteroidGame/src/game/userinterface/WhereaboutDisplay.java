package game.userinterface;

public class WhereaboutDisplay extends Display{
    private boolean isNeighbour;

    protected boolean underSunStorm;
    protected boolean exploding;
    protected int blink;

    public void SetisNeigbhour(boolean neigbhour) {
        isNeighbour = neigbhour;
        DisplayManager.GetInstance().DrawDisplays();
    }
    public boolean IsNeighbour() {
        return isNeighbour;
    }

    public void Blink() {
        if (underSunStorm || exploding) {
            blink--;
            if (blink == 0) {
                underSunStorm = false;
                exploding = false;
            }
            DisplayManager.GetInstance().repaint();
        }
    }
}
