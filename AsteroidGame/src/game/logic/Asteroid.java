package game.logic;
import java.util.ArrayList;
import java.util.Scanner;

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
    public Asteroid() {
        Skeleton.WriteName("Asteroid: Asteroid()");
        Skeleton.tab++;
        sunnearness=false;//in case adding uranium or ice
        empty=true;
        neighbours = new ArrayList<Whereabout>();
        entities = new ArrayList<Entity>();
        Skeleton.tab--;
    }

    public Asteroid(int _layers,boolean _sunearness, Material _material, Asteroidfield _asteroidfield){
        Skeleton.WriteName("Asteroid: Asteroid()");
        Skeleton.tab++;
        neighbours = new ArrayList<Whereabout>();
        entities = new ArrayList<Entity>();
        layers=_layers;
        sunnearness=_sunearness;
        material=_material;
        if(material==null) empty=true;
        else empty=false;
        asteroidfield=_asteroidfield;
        Skeleton.tab--;
    }

    //GETTERES, SETTERS:
    public int GetLayer() {
        Skeleton.WriteName("Asteroid: getLayers()");
        Skeleton.WriteName("Asteroid: getLayers() return:"+layers);
        return layers;
    }
    public void SetLayer(int layers) {
        Skeleton.WriteName("Asteroid: setLayers("+layers+")");
        this.layers = layers;
    }
    public boolean GetSunnearness() {
        Skeleton.WriteName("Asteroid: getSunnearness()");
        Skeleton.WriteName("Asteroid: getSunnearness() return"+sunnearness);
        return sunnearness;
    }

    //sets the "sunnearness" and calls the CheckInteraction() method
    public void SetSunnearness(boolean sunnearness) {
        Skeleton.WriteName("Asteroid: setSunnearness("+sunnearness+")");
        Skeleton.tab++;
        this.sunnearness = sunnearness;
        if(sunnearness) CheckInteraction();
        Skeleton.tab--;
    }
    public boolean GetEmpty() {
        Skeleton.WriteName("Asteroid: getEmpty()");
        Skeleton.WriteName("Asteroid: getEmpty() return:"+empty);
        return empty;
    }
    public void SetEmpty(boolean empty) {
        Skeleton.WriteName("Asteroid: setEmpty("+empty+")");
        this.empty = empty;
    }
    public ArrayList<Whereabout> GetNeighbours() {
        Skeleton.WriteName("Asteroid: getNeighbours()");
        Skeleton.WriteName("Asteroid: getNeighbours() return:neigbours");
        return neighbours;
    }
    public ArrayList<Entity> GetEntities() {
        Skeleton.WriteName("Asteroid: getEntities()");
        Skeleton.WriteName("Asteroid: getEntities() return:entities");
        return entities;
    }
    public Material GetMaterial() {
        Skeleton.WriteName("Asteroid: getMaterial()");
        Skeleton.WriteName("Asteroid: getMaterial() return:material");
        if(!empty)return material;
        else return null;
    }
    public Asteroidfield GetAsteroidfield() {
        Skeleton.WriteName("Asteroid: getAsteroidfield()");
        Skeleton.WriteName("Asteroid: getAsteroidfield() return:asteroidfield");
        return asteroidfield;
    }
    public void SetAsteroidfield(Asteroidfield _asteroidfield){
        Skeleton.WriteName("Asteroid: SetAsteroidfield(asteroidfield)");
        asteroidfield=_asteroidfield;
    }

    //METHODS, FUNCTIONS:

    //adds a material to an asteroid
    public boolean AddMaterial(Material material){
        Skeleton.WriteName("Asteroid: AddMaterial(material)");
        //if the core is empty the method succeeds, and sets the "empty" flag to false
        if(empty && layers==0){
            this.material=material;
            empty=false;
            Skeleton.tab++;
            //checks if the sunnearness is true before return
            CheckInteraction();
            Skeleton.tab--;
            Skeleton.WriteName("Asteroid: AddMaterial(material) return:true");
            return true;
        }
        //else it fails
        else {
            Skeleton.WriteName("Asteroid: AddMaterial(material) return:false");
            return false;
        }
    }

    //removes the material from the core, and sets the "empty" flag to false
    public Material RemoveMaterial(){
        Skeleton.WriteName("Asteroid: RemoveMaterial()");

        if(layers>0 || material == null) {
            Skeleton.WriteName("Asteroid: RemoveMaterial() return: null");
            return null;
        }

        Material tmp=material;
        material=null;
        empty=true;

        Skeleton.WriteName("Asteroid: RemoveMaterial() return: material");

        return tmp;
    }

    //adds an entity to the "entities"
    public boolean AddEntity(Entity entity){
        Skeleton.WriteName("Asteroid: AddEntity(entity)");
        Skeleton.tab++;
        entities.add(entity);
        entity.SetAsteroid(this);
        Skeleton.tab--;
        Skeleton.WriteName("Asteroid: AddEntity(entity) return:true");
        return true;
    }

    //removes the given entity from "entities"
    public void RemoveEntity(Entity entity){
        Skeleton.WriteName("Asteroid: RemoveEntity(entity)");
        entities.remove(entity);
    }

    //lowers the layer by 1 if the layers>0
    public void ThinLayer(){
        Skeleton.WriteName("Asteroid: ThinLayer()");
        Skeleton.tab++;
        if(layers>0)layers-=1;
        //if the layers=0, calls the CheckInteraction() method
        if(layers==0)CheckInteraction();
        Skeleton.tab--;
    }

    //adds a Whereabout to the neighbours
    public void AddNeighbour(Whereabout neighbour){
        Skeleton.WriteName("Asteroid: AddNeighbour(neighbour)");
        neighbours.add(neighbour);
    }

    // //removes the neighbour given in the header from the "neighbours"
    public void RemoveNeighbour(Whereabout neighbour){
        Skeleton.WriteName("Asteroid: RemoveNeighbour(neighbour)");
        neighbours.remove(neighbour);
    }

    //sets the "sunnearness" flag to true
    public void OnFire(){
        Skeleton.WriteName("Asteroid: OnFire()");
        Skeleton.tab++;
        //if the core is not empty and the layers>0 kills all entities
        if(!empty || layers>0){
            for(int i=0;i< entities.size();i++)
                entities.get(i).Die();
        }
        Skeleton.tab--;
    }

    //calls the material's Interact() method if the asteroid
    //is close to the sun and the layers=0
    public void CheckInteraction(){
        Skeleton.WriteName("Asteroid: CheckInteraction()");
        Skeleton.tab++;
        if(sunnearness&&layers==0){
            try{
                material.Interact(this);
            } catch(NullPointerException e){}
        }
        Skeleton.tab--;
    }

    //calls the RemoveNeighbour() method
    public void NearbyExplosion(Asteroid explodedAsteroid){
        Skeleton.WriteName("Asteroid: NearbyExplosion(explodedAsteroid)");
        Skeleton.tab++;
        RemoveNeighbour(explodedAsteroid);
        //if there are no more neighbours the asteroid destroys itself
        if(neighbours.size()==0)Explode();
        Skeleton.tab--;
    }

    //the asteroid destroy itself
    public void Explode(){
        Skeleton.WriteName("Asteroid: Explode()");
        Skeleton.tab++;
        //kills all entities
        //mivel mindig kikerul a tömbből valtozik a tomb merete, mindig betolodnak 0ra
        Entity[] tempentities = new Entity[entities.size()];
        for(int i=0;i<entities.size();i++) tempentities[i] = entities.get(i);
        for(int i=0; i< tempentities.length; i++) entities.get(0).BlowUp();
        //notifies all neighbours
        //át kell alakítani, mert mindig kivesz
        Whereabout[] tempwhereabout = new Whereabout[neighbours.size()];
        for(int i=0;i<neighbours.size();i++) tempwhereabout[i] = neighbours.get(i);
        for(int i=0;i<tempwhereabout.length;i++) tempwhereabout[i].NearbyExplosion(this);

        //notifies the asteroid field
        try {
            asteroidfield.RemoveAsteroid(this);
        } catch (NullPointerException e) { }
        Skeleton.tab--;
    }

    //return the neighbour from the "neighbours" with the given index
    public Whereabout GetNeighbour(int i){
        Skeleton.WriteName("Asteroid: GetNeighbour(i)");
        Skeleton.WriteName("Asteroid: GetNeighbour(i) return:neighbours[i]");
        return neighbours.get(i);
    }

    public int GetNumberOfNeighbours(){
        Skeleton.WriteName("Asteroid: GetNumberOfNeighbours()");
        Skeleton.WriteName("Asteroid: GetNumberOfNeighbours() return:"+neighbours.size());
        return neighbours.size();
    }
}