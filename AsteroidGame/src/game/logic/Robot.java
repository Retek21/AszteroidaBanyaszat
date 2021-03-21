package game.logic;

//created by: Turiák Anita 2021.03.19.
//Robot class, parent: Entity, interface: PLaceable
public class Robot extends Entity implements Placeable{

    //default constructor
    public Robot(){
       Skeleton.WriteName("Robot()");
    }

    //METHODS

    //robot does phase - drills or moves
    @Override
    public void DoPhase(){
        Skeleton.WriteName("Robot: DoPhase()");
        if(asteroid.GetLayer() == 0) {
            Skeleton.tab++;
            int n = asteroid.GetNumberOfNeighbours();
            if (n > 0) {
                int Random = (int) (Math.random() * n);
                Move(Random);
                Skeleton.tab--;
            }
       else{
                Skeleton.tab++;
                Drill();
                Skeleton.tab--;
            }
        }
    }

    //Deploy robot on asteroid
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


    //robot blows up because of an explosion
    @Override
    public void BlowUp(){
        Skeleton.WriteName("Robot: BlowUp()");
        Skeleton.tab++;
        if(asteroid.GetNumberOfNeighbours() == 0)
            Die();
        else{
            int n = asteroid.GetNumberOfNeighbours();
            int Random = (int)(Math.random()*n);
            Move(Random);
        }
        Skeleton.tab--;
    }
}
