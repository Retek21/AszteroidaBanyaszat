package game.logic;

//Osztály a telepsnek, őse az Entity
public class Settler extends Entity{
    private Inventory inventory;            //A telepes inventory-ja
    private Factory factory;                // A factory, ami segítségével tud craft-olni

    public Settler(){}

    public Settler(Factory f ){
        factory = f;
    }

    public void SetInventory(Inventory i){
        inventory = i;
    }

    public void CraftTeleport(){                    //Teleport előállításához szükséges függvény
        if(inventory.IsTeleportSlotEmpty == true){
            Teleport[] teleports = new Teleport[1];
            teleports = factory.CreateTeleport(inventory);
            inventory.AddTeleport(teleports[0]);
            inventory.AddTeleport(teleports[1]);
        }
    }

    public void CraftRobot() {                      //Robot előállításához szükséges függvény
        Robot robot;
        robot = factory.CreateRobot(inventory);
        Place(robot);                               //előállítása után azonnal le is helyeződik
    }

    public void Mine(){                             //ha van hely az inventory-ban, kibányássza
        if(inventory.IsMaterialSlotFull == false){  //az aszteroidában levő nyersanyagot
            Material m = asteroid.removeMaterial();
            inventory.AddMaterial(m);
        }
    }

    public void Place(Placeable p){                 //Lehelyez egy placeable objektumot az aszteroidára
        if(asteroid.getLayer() == 0) {
            p.Deploy(asteroid);
            inventory.removeMaterial(p);
        }
    }

    @Override
    public void Die(){                              //a telepes meghal
        inventory.Clear();
        asteroid.RemoveEntity(this);
    }

    @Override
    public void BlowUp(){                           //aszteroida robbanásakor hívódik
        Die();
    }

    @Override
    public void DoPhase(){}                         //egy fázisban a cselekvés

}
