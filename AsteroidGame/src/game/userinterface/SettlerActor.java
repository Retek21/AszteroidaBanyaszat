package game.userinterface;

public class SettlerActor extends Actor{
    @Override
    public void Act(String[] param){}
    @Override
    public Manager.State GetState(){return Manager.State.SettlerRound;}
}
