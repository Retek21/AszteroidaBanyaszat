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
    /*
    A telepes elkészít egy teleportkapu-párt.
    Ha van hely az inventory-jában meghívódik a Factory CreateTeleport metódusa, ami
    visszaad egy teleportpárt, ha sikeres a craftolás. Ebben az esetben a teleportok
    bekerülnek az inventory-ba.
    Ha nincs hely az inventory-ban több teleportnak, vagy a CreateTeleport null-lal tér vissza
    (sikeretelen craftolás) nem történik semmi.
     */
    public void CraftTeleport(){                    //teleport crafting
        //if there is enough storage in inventory, it crafts teleports
        Skeleton.WriteName("Settler: CraftTeleport()");
        Skeleton.tab++;

        if(inventory.IsTeleportSlotEmpty() == true){
            ArrayList<Teleport> teleports;
            try {


                teleports = factory.CreateTeleport(inventory);
                inventory.AddTeleport(teleports.get(0));
                inventory.AddTeleport(teleports.get(1));


            } catch (Exception e) { }
        }
        Skeleton.tab--;
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
        Skeleton.WriteName("Settler: CraftRobot()");
        Skeleton.tab++;

        Robot robot;
        robot = factory.CreateRobot(inventory);

        if (robot != null) {
            robot.Deploy(asteroid);
        }

        Skeleton.tab--;
    }

    /*
    A telepes bányászik asz aszteroidán. Ha az inventory-ja tele van, vagy üres aszteroidát
    próbál bányászni, a cselekvés siekrtelen, nem történik semmi. Egyéb esetben a nyersanyag
    kikerül az aszteroidából és addolódik az inventory-ba.
     */
    public void Mine(){
        //if there is enough storage in the inventory, the settler mines
        Skeleton.WriteName("Settler: Mine()");
        Skeleton.tab++;
        if(!inventory.IsMaterialSlotFull()){
            Material m = asteroid.RemoveMaterial();
            if (m != null)
                inventory.AddMaterial(m);
        }
        Skeleton.tab--;
    }

    /*
    A telepes lehelyez egy teleportot az aszteroidája szomszédságába.
    Meghívódik a teleport Deploy() metódusa.
     */
    public void PlaceTeleport(Teleport t){
        Skeleton.WriteName("Settler: PlaceTeleport(Teleport t)");
        Skeleton.tab++;

        t.Deploy(asteroid);
        inventory.RemoveTeleport(t);

        Skeleton.tab--;
    }

    /*
    A telepes lehelyez egy nyersanyagot az aszteroida magjába.
    Meghívódik a nyersanyag Deploy() metódusa.
     */
    public void PlaceMaterial(Material m){
        Skeleton.WriteName("Settler: PlaceMaterial(Material m)");
        Skeleton.tab++;

        boolean success =  m.Deploy(asteroid);
        if(success)
            inventory.RemoveMaterial(m);

        Skeleton.tab--;
    }


    /*
    A telepes meghal. Törlődik az inventory-ja és meghívódik az ősosztály Die() metódusa.
     */
    @Override
    public void Die(){
        Skeleton.WriteName("Settler: Die()");
        Skeleton.tab++;
        inventory.Clear();
        super.Die();
        Skeleton.tab--;
    }

    /*
    A telepes felrobban aszteroida robbanás következtében, ennek következtében meghal.
     */
    @Override
    public void BlowUp(){
        Skeleton.WriteName("Settler: BlowUp()");
        Skeleton.tab++;

        Die();

        Skeleton.tab--;
    }

    /*
    A metódus azt valósítja meg, hogy a telepes mit csinál a fázisában.
    Mivel erős kapcsolatban van a controllerrel, a tesztesetekben nem használjuk.
     */
    @Override
    public void DoPhase(){
        Skeleton.WriteName("Settler: DoPhase()");
    }

}
