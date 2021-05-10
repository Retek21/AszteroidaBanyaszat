package game.userinterface;

/*
 * @author Kristof Torok
*/
/**
 * Display, ami megjelenit egy whereabout-ot
 */
public class WhereaboutDisplay extends Display{
    /**
     * a szomszedossagot jelzo logikai ertek
     */
    private boolean isNeighbour;
    /**
     * a napvihart jelzo logikai ertek
     */
    protected boolean underSunStorm;
    /**
     * a robbanast jelzo logikai ertek
     */
    protected boolean exploding;
    /**
     * a villogast jelzo logikai ertek
     */
    protected int blink;

    /**
     * beallitja az isNeighbour erteket
     * @param neigbhour: a kapott logikai ertek
     */
    public void SetisNeigbhour(boolean neigbhour) {
        isNeighbour = neigbhour;
        DisplayManager.GetInstance().DrawDisplays();
    }

    /**
     * visszaadja az isNeighbour erteket
     * @return: a logikai ertek
     */
    public boolean IsNeighbour() {
        return isNeighbour;
    }

    /**
    * blinkelest beallito fuggveny
    * ha sunstorm van vagy felrobban akkor blinkeles szamlaloja csokken
    * egyebkent ha 0 atvaltozik a boolok erteke
    * blinkeles kozben mindig repaintet ir
    */
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
