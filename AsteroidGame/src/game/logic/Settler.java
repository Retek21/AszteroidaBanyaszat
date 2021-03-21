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

        Skeleton.WriteName("Settler: Settler()");
    }


    //GETTERS, SETTERS
    public Inventory GetInventory(){
          Skeleton.WriteName("Settler: GetInventory()");
        return inventory;
    }

    public Factory GetFactory(){
        Skeleton.WriteName("Settler: GetFactory()");
        return factory;
    }

    public void SetInventory(Inventory i){
        Skeleton.WriteName("Settler: SetInventory(inventory i)");
        inventory = i;
    }

    public Settler(Factory f ){
        Skeleton.WriteName("Settler: Settler(Factory f)");
        factory = f;
    }


    //METHODS
    //Crafting functions
    public void CraftTeleport(){                    //teleport crafting
        //if there is enough storage in inventory, it crafts teleports
        Skeleton.WriteName("Settler: CraftTeleport()");
        Skeleton.tab++;

        if(inventory.IsTeleportSlotEmpty() == true){
            ArrayList<Teleport> teleports;
            try {
                Skeleton.tab++;

                teleports = factory.CreateTeleport(inventory);
                teleports.get(0).SetInventory(inventory);
                teleports.get(1).SetInventory(inventory);
                inventory.AddTeleport(teleports.get(0));
                inventory.AddTeleport(teleports.get(1));

                Skeleton.tab--;
            } catch (Exception e) { }
        }
        Skeleton.tab--;
    }

    //robot crafting
    public void CraftRobot() {
        Skeleton.WriteName("Settler: CraftRobot()");

        Skeleton.tab++;
        Robot robot;
        robot = factory.CreateRobot(inventory);

        Skeleton.tab++;
        if (robot != null) {
            //place robot after it is crafted
            PlaceRobot(robot);
        }
        Skeleton.tab--;
        Skeleton.tab--;
    }

    //Mine the current asteroid
    public void Mine(){
        //if there is enough storage in the inventory, the settler mines
        Skeleton.WriteName("Settler: Mine()");
        if(!inventory.IsMaterialSlotFull()){
            Skeleton.tab++;
            Material m = asteroid.RemoveMaterial();
            if (m != null)
                inventory.AddMaterial(m);
        }
        Skeleton.tab--;
    }

    //Places teleport nearby asteroid
    public void PlaceTeleport(Teleport t){
        Skeleton.WriteName("Settler: PlaceTeleport(Teleport t)");
        Skeleton.tab++;
            t.Deploy(asteroid);
            inventory.RemoveTeleport(t);
            Skeleton.tab--;
    }

    //places material into asteroid
    public void PlaceMaterial(Material m){
        Skeleton.WriteName("Settler: PlaceMaterial(Material m)");
        Skeleton.tab++;
            m.Deploy(asteroid);             //Deploy visszatérés még kérdéses
            inventory.RemoveMaterial(m);
            Skeleton.tab--;
    }

    //places robot on asteroid
    public void PlaceRobot(Robot r){
        Skeleton.WriteName("Settler: PlaceRobot(Robot r)");
        r.Deploy(asteroid);
    }

    //Settler dies
    @Override
    public void Die(){
        Skeleton.WriteName("Settler: Die()");
        Skeleton.tab++;
        super.Die();
        inventory.Clear();
        Skeleton.tab--;
    }

    //Settler blows up after asteroid explodes
    @Override
    public void BlowUp(){
        Skeleton.WriteName("Settler: BlowUp()");
        Die();
    }

    //Settler does phase, meaning drill, mine, craft or move
    @Override
    public void DoPhase(){
        Skeleton.WriteName("Settler: DoPhase()");
    }

}
