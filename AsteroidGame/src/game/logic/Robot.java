package game.logic;

//created by: Turiák Anita 2021.03.19.
//Robot class, parent: Entity, interface: PLaceable
public class Robot extends Entity implements Placeable{

    //default constructor
    public Robot(){
        System.out.printf("\tRobot()");
    }

    //METHODS

    //robot does phase - drills or moves
    @Override
    public void DoPhase(){
        System.out.printf("\tRobot: DoPhase()");
        if(asteroid.GetLayer() == 0){
            int n = asteroid.GetNumberOfNeighbours();
            int Random = (int)(Math.random()*n);
            Move(Random);
        }
        else
            Drill();
    }

    //Deploy robot on asteroid
    @Override
    public void Deploy(Asteroid a) {
        System.out.printf("\tRobot: Deploy(Asteroid: a)");
        a.AddEntity(this);
    }


    //robot blows up because of an explosion
    @Override
    public void BlowUp(){
        System.out.printf("\tRobot: BlowUp()");
        if(asteroid.GetNumberOfNeighbours() == 0)
            Die();
        else{
            int n = asteroid.GetNumberOfNeighbours();
            int Random = (int)(Math.random()*n);
            Move(Random);
        }
    }
}
