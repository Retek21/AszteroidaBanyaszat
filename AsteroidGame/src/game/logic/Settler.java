package game.logic;

import java.util.ArrayList;

//created by: Turiák Anita 2021.03.19.
//Settler class, parent: Entity
public class Settler extends Entity{

    //ATTRIBUTES
    private Inventory inventory;            //inventory of the settler
    private Factory factory;                //factory, which helps the settler craft

    //default constructor
    public Settler(){
        System.out.print("\tSettler()\n \t");
    }


    //GETTERS, SETTERS
    public Inventory GetInventory(){
          System.out.println("\tSettler: GetInventory()");
        return inventory;
    }

    public Factory GetFactory(){
        System.out.printf("\tSettler: GetFactory()");
        return factory;
    }

    public void SetInventory(Inventory i){
             System.out.printf("\tSettler: SetInventory(inventory i)");
        inventory = i;
    }

    public Settler(Factory f ){
            System.out.println("\tSettler: Settler(Factory f)");
        factory = f;
    }


    //METHODS
    //Crafting functions
    public void CraftTeleport(){                    //teleport crafting
        //if there is enough storage in inventory, it crafts teleports
        System.out.println("\tSettler: CraftTeleport()");
        if(inventory.IsTeleportSlotEmpty() == true){
            ArrayList<Teleport> teleports = new ArrayList<>();
            try {
                teleports = factory.CreateTeleport(inventory);
                inventory.AddTeleport(teleports.get(0));
                inventory.AddTeleport(teleports.get(1));
            } catch (Exception e) { /*buzi vagy szacsikam bloo blooo bent fognak maradni blooo*/}
        }
    }

    //robot crafting
    public void CraftRobot() {
        System.out.printf("\tSettler: CraftRobot()");
        Robot robot;
        robot = factory.CreateRobot(inventory);
        //place robot after it is crafted
        PlaceRobot(robot);
    }

    //Mine the current asteroid
    public void Mine(){
        //if there is enough storage in the inventory, the settler mines
        System.out.printf("\tSettler: Mine()");
        if(!inventory.IsMaterialSlotFull()){
            Material m = asteroid.RemoveMaterial();
            inventory.AddMaterial(m);
        }
    }

    //Places teleport nearby asteroid
    public void PlaceTeleport(Teleport t){
        System.out.printf("\tSettler: PlaceTeleport(Teleport t");
            t.Deploy(asteroid);
            inventory.RemoveTeleport(t);
    }

    //places material into asteroid
    public void PlaceMaterial(Material m){
        System.out.printf("\tSettler: PlaceMaterial(Material m");
            m.Deploy(asteroid);
            inventory.RemoveMaterial(m);
    }

    //places robot on asteroid
    public void PlaceRobot(Robot r){
        System.out.printf("\tSettler: PlaceRobot(Robot r)");
        r.Deploy(asteroid);
    }

    //Settler dies
    @Override
    public void Die(){
        System.out.println("\tSettler: Die()");
        super.Die();
        inventory.Clear();
    }

    //Settler blows up after asteroid explodes
    @Override
    public void BlowUp(){
        System.out.printf("\tSettler: BlowUp()");
        Die();
    }

    //Settler does phase, meaning drill, mine, craft or move
    @Override
    public void DoPhase(){
        System.out.println("\tSettler: DoPhase()");
    }

}
