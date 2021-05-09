package game.userinterface;


public class BlinkerThread extends Thread{

    @Override
    public void run() {
        DisplayManager dm = DisplayManager.GetInstance();
        try {
            for (int i = 0; i < dm.GetBlinkingTime(); i++) {
                dm.BlinkWhereabouts();
                Thread.sleep(500);
            }
            dm.ClearClearPuffer();
        } catch (InterruptedException e) { System.out.println("InterruptedException has occured."); }
    }
}