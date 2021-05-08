package game.userinterface;

public class WhereaboutDisplay extends Display{
    private boolean isNeighbour;

    public void SetisNeigbhour(boolean neigbhour) {
        isNeighbour = neigbhour;
        DisplayManager.GetInstance().DrawDisplays();
    }
    public boolean IsNeighbour() {
        return isNeighbour;
    }
}
