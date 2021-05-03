package game.controller;

import game.userinterface.State;

public class Actor {
    private String id;
    private State state;
    private String title;

    public Actor(String _id, State _state, String _title) {
        id = _id;
        state = _state;
        title = _title;
    }

    public String GetID() {
        return id;
    }

    public State GetState() {
        return state;
    }

    public String GetTitle() {
        return title;
    }
}
