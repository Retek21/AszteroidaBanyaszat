package game.logic;

import game.controller.Controller;

/**
 * Coal nyersanyag, dolgok(robot, teleport, bazis) epitesehez szukseges.
 * @author torok
 */
public class Coal extends Material{

    /**
     * A nyersanyag konstruktora ami beallitja a nevet.
     */
    public Coal(){
        name = "Coal";
    }

    /**
     *Meghívja a kontroller CoalDisintegrate metódusát.
     */
    @Override
    public void Disintegrate() {
        Controller.GetInstanceOf().CoalDisintegrate(this);
    }
}
