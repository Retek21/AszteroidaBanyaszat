package game.userinterface;

public class EntityDisplay extends Display{

    private boolean moved;

    /**
     * egy korre moved true lesz
     */
    public void SetMoved(boolean move){moved=move;}

    public boolean GetMoved(){return moved;}
}
