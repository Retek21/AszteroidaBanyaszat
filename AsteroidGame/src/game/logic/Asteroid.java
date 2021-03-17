package game.logic;
import java.util.ArrayList;

//Created by:Bendegúz Dengyel 2021.03.17
//Asteroid class
public class Asteroid implements Whereabout{
    //ATTRIBUTES:
    private int layers;//number of layers covering the asteroid
    private boolean sunnearness;//true or false depending the closure of the sun
    private boolean empty;//shows whether the core of the asteroid contains a material or not
    private ArrayList<Whereabout> neighbours;//the neighbours of the asteroid an entity can travel
    private ArrayList<Entity> entities;//the entities  currently staying on the asteroid
    private Material material;//the material containd in the core
    private Asteroidfield asteroidfield;//the asteroid field the current asteroid belongs to

    //CONSTRUCTOR:
    public Asteroid(int _layers,boolean _sunearness, Material _material, Asteroidfield _asteroidfield){
        System.out.println("\tAsteroid: Asteroid()");
        layers=_layers;
        sunnearness=_sunearness;
        material=_material;
        if(material==null) empty=true;
        else empty=false;
        asteroidfield=_asteroidfield;
    }

    //GETTERES, SETTERS:
    public int GetLayer() {
        System.out.println("\tAsteroid: getLayers()");
        System.out.println("\tAsteroid: getLayers() return:"+layers);
        return layers;
    }
    public void SetLayer(int layers) {
        System.out.println("\tAsteroid: setLayers("+layers+")");
        this.layers = layers;
    }
    public boolean GetSunnearness() {
        System.out.println("\tAsteroid: getSunnearness()");
        System.out.println("\tAsteroid: getSunnearness() return"+sunnearness);
        return sunnearness;
    }
    public void SetSunnearness(boolean sunnearness) {
        System.out.println("\tAsteroid: setSunnearness("+sunnearness+")");
        this.sunnearness = sunnearness;
    }
    public boolean GetEmpty() {
        System.out.println("\tAsteroid: getEmpty()");
        System.out.println("\tAsteroid: getEmpty() return:"+empty);
        return empty;
    }
    public void SetEmpty(boolean empty) {
        System.out.println("\tAsteroid: setEmpty("+empty+")");
        this.empty = empty;
    }
    public ArrayList<Whereabout> GetNeighbours() {
        System.out.println("\tAsteroid: getNeighbours()");
        System.out.println("\tAsteroid: getNeighbours() return:neigbours");
        return neighbours;
    }
    public ArrayList<Entity> GetEntities() {
        System.out.println("\tAsteroid: getEntities()");
        System.out.println("\tAsteroid: getEntities() return:entities");
        return entities;
    }
    public Material GetMaterial() {
        System.out.println("\tAsteroid: getMaterial()");
        System.out.println("\tAsteroid: getMaterial() return:material");
        if(!empty)return material;
        else return null;
    }
    public Asteroidfield GetAsteroidfield() {
        System.out.println("\tAsteroid: getAsteroidfield()");
        System.out.println("\tAsteroid: getAsteroidfield() return:asteroidfield");
        return asteroidfield;
    }

    //METHODS, FUNCTIONS:

    //adds a material to an asteroid
    public boolean AddMaterial(Material material){
        System.out.println("\tAsteroid: AddMaterial(material)");
        //if the core is empty the method succeeds, and sets the "empty" flag to false
        if(empty){
            this.material=material;
            empty=false;
            System.out.println("\tAsteroid: AddMaterial(material) return:true");
            return true;
        }
        //else it fails
        else {
            System.out.println("\tAsteroid: AddMaterial(material) return:false");
            return false;
        }
    }

    //removes the material from the core, and sets the "empty" flag to false
    public Material RemoveMaterial(){
        System.out.println("\tAsteroid: RemoveMaterial()");
        System.out.println("\tAsteroid: RemoveMaterial() return:material");
        Material tmp=material;
        material=null;
        empty=true;
        return tmp;
    }

    //adds an entity to the "entities"
    public boolean AddEntity(Entity entity){
        System.out.println("\tAsteroid: AddEntity(entity)");
        entities.add(entity);
        System.out.println("\tAsteroid: AddEntity(entity) return:true");
        return true;
    }

    //removes the given entity from "entities"
    public void RemoveEntity(Entity entity){
        System.out.println("\tAsteroid: RemoveEntity(entity)");
        entities.remove(entity);
    }

    //lowers the layer by 1 if the layers>0
    public void ThinLayer(){
        System.out.println("\tAsteroid: ThinLayer()");
        if(layer>0)layer-=1;
        //if the layers=0, calls the CheckInteraction() method
        if(layers==0)CheckInteraction();
    }

    //adds a Whereabout to the neighbours
    public void AddNeighbour(Whereabout neighbour){
        System.out.println("\tAsteroid: AddNeighbour(neighbour)");
        neighbours.add(neighbour);
    }

    // //removes the neighbour given in the header from the "neighbours"
    public void RemoveNeighbour(Whereabout neighbour){
        System.out.println("\tAsteroid: RemoveNeighbour(neighbour)");
        neighbours.remove(neighbour);
    }

    //sets the "sunnearness" flag to true
    public void OnFire(){
        System.out.println("\tAsteroid: OnFire()");
        sunnearness=true;
        //if the core is not empty and the layers>0 kills all entities
        if(!empty && layers>0){
            for(int i=0;i< entities.size();i++)
                entities[i].Die();
        }
    }

    //calls the material's Interact() method if the asteroid
    //is close to the sun and the layers=0
    public void CheckInteraction(){
        System.out.println("\tAsteroid: OnFire()");
        if(sunnearness&&layers==0)material.Interact(this);
    }

    //calls the RemoveNeighbour() method
    public void NearbyExplosion(Whereabout explodedWhereabout){
        System.out.println("\tAsteroid: NearbyExplosion(explodedWhereabout)");
        RemoveNeighbour(explodedWhereabout);
        //if there are no more neighbours the asteroid destroys itself
        if(neighbours.size()==0)Explode();
    }

    //the asteroid destroy itself
    public void Explode(){
        System.out.println("\tAsteroid: Explode()");
        //kills all entities
        for(int i=0;i<entities.size();i++)entities[i].BlowUp();
        //notifies all neighbours
        for(int i=0;i<neighbours.size();i++)NearbyExplosion(this);
        //notifies the asteroid field
        asteroidfield.RemoveAsteroid(this);
    }

    //return the neighbour from the "neighbours" with the given index
    public Whereabout GetNeighbour(int i){
        System.out.println("\tAsteroid: GetNeighbour(i)");
        System.out.println("\tAsteroid: GetNeighbour(i) return:neigbours[i]");
        return neighbours[i];
    }

    public int GetNumberOfNeighbours(){
        System.out.println("\tAsteroid: GetNumberOfNeighbours()");
        System.out.println("\tAsteroid: GetNumberOfNeighbours() return:"+neighbours.size());
        return neighbours.size();
    }
}