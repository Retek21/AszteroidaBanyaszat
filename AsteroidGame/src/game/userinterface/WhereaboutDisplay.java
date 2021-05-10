package game.userinterface;

/*
 * @author Kristof Torok
 * whereabout displaye*/
public class WhereaboutDisplay extends Display{
    /*
    * szomszedot jelzo bool
    * */
    private boolean isNeighbour;
    /*
    * sunstorm alatti allapotot jelzo bool
    * */
    protected boolean underSunStorm;
    /*
    * felrobbanast allito bool
    * */
    protected boolean exploding;
    /*
    * villogas szamlaloja
    * */
    protected int blink;
    /*
    * beallitja a szomszedsagot jelzo boolt
    * */
    public void SetisNeigbhour(boolean neigbhour) {
        isNeighbour = neigbhour;
        DisplayManager.GetInstance().DrawDisplays();
    }
    /*
    * visszaadja a szomszedsagot jelzo boolt
    * */
    public boolean IsNeighbour() {
        return isNeighbour;
    }
    /*
    * blinkelest beallito fuggveny
    * ha sunstorm van vagy felrobban akkor blinkeles szamlaloja csokken
    * egyebkent ha 0 atvaltozik a boolok erteke
    * blinkeles kozben mindig repaintet ir
    * */
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
