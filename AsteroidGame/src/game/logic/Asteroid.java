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
    private ArrayList<Whereabout> neighbours;//the neighbours of the asteroid where the entities can travel
    private ArrayList<Entity> entities;//the entities  currently staying on the asteroid
    private Material material;//the material in the core of the asteroid
    private Asteroidfield asteroidfield;//the asteroid field the current asteroid belongs to

    //CONSTRUCTOR:
    public Asteroid() {
        sunnearness=false;//in case adding uranium or ice
        empty=true;
        neighbours = new ArrayList<Whereabout>();
        entities = new ArrayList<Entity>();
    }

    public Asteroid(int _layers,boolean _sunearness, Material _material, Asteroidfield _asteroidfield){
        neighbours = new ArrayList<Whereabout>();
        entities = new ArrayList<Entity>();
        layers=_layers;
        sunnearness=_sunearness;
        material=_material;
        if(material==null) empty=true;
        else empty=false;
        asteroidfield=_asteroidfield;
    }

    //GETTERES, SETTERS:
    public int GetLayer() {
        return layers;
    }
    public void SetLayer(int layers) {
        this.layers = layers;
    }
    public boolean GetSunnearness() {
        return sunnearness;
    }

    //sets the "sunnearness" and calls the CheckInteraction() method
    public void SetSunnearness(boolean sunnearness) {
        this.sunnearness = sunnearness;
        if(sunnearness) CheckInteraction();
    }
    public boolean GetEmpty() {
        return empty;
    }
    public void SetEmpty(boolean empty) {
        this.empty = empty;
    }
    public ArrayList<Whereabout> GetNeighbours() {
        return neighbours;
    }
    public ArrayList<Entity> GetEntities() {
        return entities;
    }
    public Material GetMaterial() {
        if(!empty)return material;
        else return null;
    }
    public Asteroidfield GetAsteroidfield() {
        return asteroidfield;
    }
    public void SetAsteroidfield(Asteroidfield _asteroidfield){
        asteroidfield=_asteroidfield;
    }

    //METHODS, FUNCTIONS:

    //adds a material to an asteroid
    public boolean AddMaterial(Material material){
        if(empty && layers==0){
            this.material=material;
            empty=false;

            CheckInteraction();

            return true;
        }

        else {
            return false;
        }
    }

    /*
    Eltávolítja a nyersanyagot az aszteoridából. Ha az aszteroida üreges volt vagy van még rajta réteg,
    null-lal tér vissza. Egyéb esetben átállítja az empty flag-et true-ra, és visszatér a nyersanyaggal.
     */
    public Material RemoveMaterial(){
        if(layers>0 || material == null) {
            return null;
        }

        Material tmp=material;
        material=null;
        empty=true;

        return tmp;
    }

    //adds an entity to the "entities"
    public boolean AddEntity(Entity entity){
        entities.add(entity);
        entity.SetAsteroid(this);

        return true;
    }

    //removes the given entity from "entities"
    public void RemoveEntity(Entity entity){
        entities.remove(entity);
    }

    //lowers the layer by 1 if the layers>0
    public void ThinLayer(){
        if(layers>0)layers-=1;
        if(layers==0)CheckInteraction();
    }

    //adds a Whereabout to the neighbours
    public void AddNeighbour(Whereabout neighbour){
        neighbours.add(neighbour);
    }

    // //removes the neighbour given in the header from the "neighbours"
    public void RemoveNeighbour(Whereabout neighbour){
        neighbours.remove(neighbour);
    }

    /*
    Az aszteroida "kigyullad". A rajta található entitások meghalnak, ha nem teljesülnek az elbújás feltételei.
     */
    public void OnFire(){
        if(!empty || layers>0){
            for(int i=0;i< entities.size();i++)
                entities.get(i).Die();
        }
    }

    //calls the material's Interact() method if the asteroid
    //is close to the sun and the has no layers.
    public void CheckInteraction(){
        if(sunnearness&&layers==0){
            try{
                material.Interact(this);
            } catch(NullPointerException e){}
        }
    }

    //calls the RemoveNeighbour() method
    public void NearbyExplosion(Asteroid explodedAsteroid){
        RemoveNeighbour(explodedAsteroid);
        if(neighbours.size()==0)Explode();
    }

    //the asteroid destroy itself
    public void Explode(){

        Entity[] tempentities = new Entity[entities.size()];
        for(int i=0;i<entities.size();i++) tempentities[i] = entities.get(i);
        for(int i=0; i< tempentities.length; i++) entities.get(0).BlowUp();


        Whereabout[] tempwhereabout = new Whereabout[neighbours.size()];
        for(int i=0;i<neighbours.size();i++) tempwhereabout[i] = neighbours.get(i);
        for(int i=0;i<tempwhereabout.length;i++) tempwhereabout[i].NearbyExplosion(this);


        try {
            asteroidfield.RemoveAsteroid(this);
        } catch (NullPointerException e) { }
    }

    //return the neighbour from the "neighbours" with the given index
    public Whereabout GetNeighbour(int i){
        return neighbours.get(i);
    }

    public int GetNumberOfNeighbours(){
        return neighbours.size();
    }
}