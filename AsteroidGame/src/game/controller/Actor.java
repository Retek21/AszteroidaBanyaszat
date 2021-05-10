package game.controller;

import game.userinterface.State;

/**
 * A jatekban eppen soron kovetkezo aktor
 */
public class Actor {
    /**
     * azonosito
     */
    private String id;
    /**
     * a statusz
     */
    private State state;
    /**
     * a cim
     */
    private String title;

    /**
     * konstrukro, beallitja a kapott parametereket
     * @param _id: azonosito
     * @param _state: statusz
     * @param _title: cim
     */
    public Actor(String _id, State _state, String _title) {
        id = _id;
        state = _state;
        title = _title;
    }

    /**
     * az azonosito visszaadasa
     * @return: azonosito
     */
    public String GetID() {
        return id;
    }

    /**
     * a statusz visszaadasa
     * @return: statusz
     */
    public State GetState() {
        return state;
    }

    /**
     * a cim visszaadasa
     * @return: cim
     */
    public String GetTitle() {
        return title;
    }
}
