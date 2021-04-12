package game.logic;

//created by: Turiák Anita 2021.03.19.
//Robot class, parent: Entity, interface: PLaceable
public class Robot extends Entity implements Placeable{

    //default constructor
    public Robot(){
       Skeleton.WriteName("Robot()");
    }

    //METHODS

    /*
    A robot a fázisában vagy mozog vagy fúr. Ha van még réteg az aszteroidáján fúr,
    egyébként átlép egy másik aszteroidára.
     */
    @Override
    public void DoPhase(){
        Skeleton.WriteName("Robot: DoPhase()");
        Skeleton.tab++;

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

        Skeleton.tab--;
    }

    /*
    A robot lekerül az aszteroidára. A művelet sikerességétől függően true-val vagy false-szal tér vissza.
     */
    @Override
    public boolean Deploy(Asteroid a) {
        Skeleton.WriteName("Robot: Deploy(Asteroid: a)");
        Skeleton.tab++;

        if(a.AddEntity(this)) {

            Skeleton.tab--;
            Skeleton.WriteName("Robot: Deploy(Asteroid: a) return: true");

            return true;
        }
        Skeleton.tab--;
        Skeleton.WriteName("Robot: Deploy(Asteroid: a) return: false");

        return false;
    }


    /*
    A robot felrobban az aszteroidája robbanása következtében. Ha a felrobban aszteroidának
    voltak szomszédjai, átlép valamelyikre, egyéb esetben meghal.
     */
    @Override
    public void BlowUp(){
        Skeleton.WriteName("Robot: BlowUp()");
        Skeleton.tab++;
        int n = asteroid.GetNumberOfNeighbours();
        if(n == 0)
            Die();
        else{
            int Random = (int)(Math.random()*n);
            Move(Random);
        }
        Skeleton.tab--;
    }
}
