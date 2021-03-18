package game.logic;


//A játékosok által létrehozott robot osztálya, őse az entity, és
//implementálja a placeable interfészt, azaz lehelyezhető egy aszteroidára
public class Robot extends Entity implements Placeable{

    public Robot(){}

    @Override
    public void DoPhase(){                          //egy fázisban a cselekvése
        if(asteroid.GetLayers == 0){
            int n = asteroid.GetNumberOfNeigbours();
            int Random = (int)(Math.random()*n);
            Move(Random);                           //lehet lépés
        }
        else
            Drill();                                //lehet fúrás
    }

    @Override
    public void Deploy(Asteroid a) {                // a robot lehelyezése
        asteroid.AddEntity(this);
    }

    @Override
    public void Die() {
        asteroid.RemoveEntity(this);
    }

    @Override
    public void BlowUp(){                           //az aszteroida felrobbanása esetén hívódik meg
        if(asteroid.GetNumberOfNeighbours == 0)
            Die();                                  //ha nincs szomszédos aszteroida, meghal a robot
        else{
            int n = asteroid.GetNumberOfNeighbours();
            int Random = (int)(Math.random()*n);
            Move(Random);                           //ha van, akkor oda lép
        }
    }
}
