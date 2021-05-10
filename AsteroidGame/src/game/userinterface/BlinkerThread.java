package game.userinterface;

/*
* @author Szabo Gergo
* blinkerthread osztaly
* */
public class BlinkerThread extends Thread{

    /*
    *blinkelteti a whereabout  majd elkuldi a szalat aludni
    * vegen torli a clearpuffer tartalmat
    * */
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
