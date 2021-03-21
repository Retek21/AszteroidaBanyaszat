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
        System.out.printf("\tRobot: DoPhase()");
        if(asteroid.GetLayer() == 0){
            Skeleton.tab++;
            int n = asteroid.GetNumberOfNeighbours();
            int Random = (int)(Math.random()*n);
            Move(Random);
            Skeleton.tab--;
        }
        else {
            Skeleton.tab++;
            Drill();
            Skeleton.tab--;
        }
    }

    //Deploy robot on asteroid
    @Override
    public void Deploy(Asteroid a) {
        Skeleton.WriteName("Robot: Deploy(Asteroid: a)");
        asteroid.AddEntity(this);
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
