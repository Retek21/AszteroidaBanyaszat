package game.logic;

//created by: Turiák Anita 2021.03.19.
//Robot class, parent: Entity, interface: PLaceable
public class Robot extends Entity {

    //default constructor
    public Robot(){

    }

    //METHODS

    /*
    A robot a fázisában vagy mozog vagy fúr. Ha van még réteg az aszteroidáján fúr,
    egyébként átlép egy másik aszteroidára.
     */
    @Override
    public void DoPhase(){

        if(asteroid.GetLayer() == 0) {
            int n = asteroid.GetNumberOfNeighbours();
            if (n > 0) {
                int Random = (int) (Math.random() * n);
                Move(Random);
            }
        }
        else{
            Drill();
        }
    }

    /*
    A robot felrobban az aszteroidája robbanása következtében. Ha a felrobban aszteroidának
    voltak szomszédjai, átlép valamelyikre, egyéb esetben meghal.
     */
    @Override
    public void BlowUp(){

        int n = asteroid.GetNumberOfNeighbours();
        if(n == 0)
            Die();
        else{
            int Random = (int)(Math.random()*n);
            Move(Random);
        }
    }
}
