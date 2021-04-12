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

    }


    //GETTERS, SETTERS
    public Inventory GetInventory(){

        return inventory;
    }

    public Factory GetFactory(){

        return factory;
    }

    public void SetInventory(Inventory i){

        inventory = i;
    }

    public Settler(Factory f ){

        factory = f;
    }


    //METHODS
    /*
    A telepes elkészít egy teleportkapu-párt.
    Ha van hely az inventory-jában meghívódik a Factory CreateTeleport metódusa, ami
    visszaad egy teleportpárt, ha sikeres a craftolás. Ebben az esetben a teleportok
    bekerülnek az inventory-ba.
    Ha nincs hely az inventory-ban több teleportnak, vagy a CreateTeleport null-lal tér vissza
    (sikeretelen craftolás) nem történik semmi.
     */
    public void CraftTeleport(){

        if(inventory.IsTeleportSlotEmpty() == true){
            ArrayList<Teleport> teleports;
            try {


                teleports = factory.CreateTeleport(inventory);
                inventory.AddTeleport(teleports.get(0));
                inventory.AddTeleport(teleports.get(1));


            } catch (Exception e) { }
        }
    }

    /*
    A telepes elkészít egy robotot.
    Meghívódik a Factory CreateRobot metódusa, ami
    visszaad egy robotot, ha sikeres a craftolás. Ebben az esetben a robot
    lekerül arra az aszteroidára, amelyen a telepes is tartózkodik.
    Ha a CreateRobot null-lal tér vissza
    (sikeretelen craftolás) nem történik semmi.
     */
    public void CraftRobot() {
        Robot robot;
        robot = factory.CreateRobot(inventory);

        if (robot != null) {
            robot.Deploy(asteroid);
        }
    }

    /*
    A telepes bányászik asz aszteroidán. Ha az inventory-ja tele van, vagy üres aszteroidát
    próbál bányászni, a cselekvés siekrtelen, nem történik semmi. Egyéb esetben a nyersanyag
    kikerül az aszteroidából és addolódik az inventory-ba.
     */
    public void Mine(){
        if(!inventory.IsMaterialSlotFull()){
            Material m = asteroid.RemoveMaterial();
            if (m != null)
                inventory.AddMaterial(m);
        }
    }

    /*
    A telepes lehelyez egy teleportot az aszteroidája szomszédságába.
    Meghívódik a teleport Deploy() metódusa.
     */
    public void PlaceTeleport(Teleport t){
        t.Deploy(asteroid);
        inventory.RemoveTeleport(t);
    }

    /*
    A telepes lehelyez egy nyersanyagot az aszteroida magjába.
    Meghívódik a nyersanyag Deploy() metódusa.
     */
    public void PlaceMaterial(Material m){
        boolean success =  m.Deploy(asteroid);
        if(success)
            inventory.RemoveMaterial(m);
    }


    /*
    A telepes meghal. Törlődik az inventory-ja és meghívódik az ősosztály Die() metódusa.
     */
    @Override
    public void Die(){
        inventory.Clear();
        super.Die();
    }

    /*
    A telepes felrobban aszteroida robbanás következtében, ennek következtében meghal.
     */
    @Override
    public void BlowUp(){
        Die();
    }

    /*
    A metódus azt valósítja meg, hogy a telepes mit csinál a fázisában.
    Mivel erős kapcsolatban van a controllerrel, a tesztesetekben nem használjuk.
     */
    @Override
    public void DoPhase(){

    }

}
