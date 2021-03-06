package game.logic;

import game.controller.Controller;
import game.userinterface.AsteroidDisplay;
import game.userinterface.Display;
import game.userinterface.WhereaboutDisplay;

import java.util.ArrayList;

/**
 * Aszteroida osztaly, megvalositja a Whereabout interfeszt.
 * Feladata a rajta torteno interakciok karbantartasa.
 * A jatekosok az aszteroidakon jatszak a jatekot.
 */
public class Asteroid implements Whereabout{

    /**
     * az aszteroidan talalhato retegek szama, amit
     * furassal lehet csokkenteni
     */
    private int layers;

    /**
     * jelzi, hogy az aszteroida eppen napkozelben van-e
     */
    private boolean sunnearness;

    /**
     * jelzi, hogy az aszteroida ureges-e
     */
    private boolean empty;
    /**
     * aszteroidan naphivar van
     * */
    private boolean onfire;
    /**
     * felrobbanast jelzo bool
     * */
    private boolean exploding;
    /**
     * aszteroida displaye
     * */
    private WhereaboutDisplay display;

    /**
     * az aszteroida szomszedai, ahova az entitasok lepni tudnak az aszteroidarol
     */
    private ArrayList<Whereabout> neighbours;

    /**
     * az aszteroidan allo entitasok tombje
     */
    private ArrayList<Entity> entities;

    /**
     * az aszteroida belsejeben talalhato nyersanyag
     */
    private Material material;

    /**
     * az aszteroidamezo, amiben az aszteroida benne van
     */
    private Asteroidfield asteroidfield;

    /**
     * display visszaadasa
     * */
    @Override
    public WhereaboutDisplay GetDisplay() {
        return display;
    }
    /**
     * display beallitasa
     */
    public void SetDisplay(WhereaboutDisplay display) {
        this.display = display;
    }


    /**
     * az aszteroida default konstruktora
     * beallitja az alapertelmezett attributumokat
     */
    public Asteroid() {
        sunnearness=false;
        onfire = false;
        empty=true;
        neighbours = new ArrayList<Whereabout>();
        entities = new ArrayList<Entity>();
    }

    /**
     * az aszteroida konstruktora
     * beallitja a parameterkent kapott adatokat
     * @param _layers: a retegek szama
     * @param _sunearness: a napkozelseg
     * @param _material: az aszteroida belsejeben talalhato nyersanyag
     * @param _asteroidfield: az aszteroidamezo, ahova az aszteroida tartozik
     */
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

    /**
     * visszaadja az aszteroida retegeinek a szamat
     * @return: retegek szama
     */
    public int GetLayer() {
        return layers;
    }

    /**
     * beallitja az aszteroida reteginek szamat a kapott parameterre
     * @param layers: retegek szama
     */
    public void SetLayer(int layers) {
        this.layers = layers;
    }

    /**
     * visszaadja az aszteroida napkozelseget
     * @return: napkozelseg
     */
    public boolean GetSunnearness() {
        return sunnearness;
    }

    /**
     * beallitja az aszteroida napkozelseget a kapott parameterre,
     * majd megnezi, leellenorzi, hogy tortenik e interakcio
     * @param sunnearness: napkozelseg
     */
    public void SetSunnearness(boolean sunnearness) {
        this.sunnearness = sunnearness;
        display.Notify();
        if(sunnearness) CheckInteraction();
    }
    /**
     * beallitja a kezdeti napkozeliseget
     * @param sunnearness - sunnearness jelzo bool
     * */
    public void SetSunnearnessInit(boolean sunnearness) {
        this.sunnearness = sunnearness;
    }

    /**
     * visszaadja, hogy az aszteroida ures-e
     * @return: uresseg
     */
    public boolean IsEmpty() {
        return empty;
    }

    /**
     * beallitja az aszteroida uresseget
     * @param empty: uresseg
     */
    public void SetEmpty(boolean empty) {
        this.empty = empty;
    }

    /**
     * visszaadja az aszteroida szomszedainak listajat
     * @return: szomszedok
     */
    public ArrayList<Whereabout> GetNeighbours() {
        return neighbours;
    }

    /**
     * visszaadja az aszteroidan tartozkodo entitasok listajat
     * @return entitasok
     */
    public ArrayList<Entity> GetEntities() {
        return entities;
    }

    /**
     * visszaadja az aszteroidaban talalhato nyersanyagot
     * @return nyersanyag
     */
    public Material GetMaterial() {
        if(!empty)return material;
        else return null;
    }

    /**
     * visszaadja az aszteroida aszteroidamezojet
     * @return aszteroidamezo
     */
    public Asteroidfield GetAsteroidfield() {
        return asteroidfield;
    }

    /**
     * beallitja az aszteroida aszteroidamezojet
     * @param _asteroidfield aszteroidamezo
     */
    public void SetAsteroidfield(Asteroidfield _asteroidfield){
        asteroidfield=_asteroidfield;
    }

    /**
     * a parameterkent kapott nyersanyagot hozzaadja az aszteroida magjahoz,
     * amennyiben az ures volt
     * @param material: a hozzaadando nyersanyag
     * @return: a hozzaadas sikeressege
     */
    public boolean AddMaterial(Material material){
        if(empty && layers == 0){

            this.material=material;
            empty=false;
            CheckInteraction();
            return true;
        }
        else return false;
    }

    /**
     * be??ll??tja az aszteroida magj??t felt??telek n??lk??l(teszteset inicializalasahoz szukseges)
     * @param material a hozzaadando nyersanyag
     */
    public void SetCore(Material material){
        this.material=material;
        empty=false;
    }

    /**
     * Banyaszaskor a nyersanyag kikerul az aszteroida magjabol.
     * Ha ures az aszteroida, akkor nullal ter vissza,
     * kulonben visszaadja a kibanyaszott nyersanyagot.
     * @return: kibanyaszott nyersanyag
     */
    public Material RemoveMaterial(){
        if(layers>0 || material == null)
            return null;

        Material tmp=material;
        material=null;
        empty=true;

        return tmp;
    }

    /**
     * hozzaadja az aszteroidan tartozkodo entitasok tombjehez a parameterul
     * kapott entitast
     * @param entity :az ujonnan erkezo entitas
     * @return: a hozzaadas sikeressege
     */
    public boolean AddEntity(Entity entity){
        entities.add(entity);
        entity.SetAsteroid(this);
        return true;
    }

    /**
     * A parameterul kapott entitast eltavolitja az entities tombjebol.
     * @param entity: az eltavolitando entitas
     */
    public void RemoveEntity(Entity entity){
        entities.remove(entity);
    }

    /**
     * furas soran az entitas eggyel csokkenti az aszteroida retegeinek a szamat.
     * ha a telepes a furas soran eltavolitotta az utolso reteget, meghivodik az
     * interakcio ellenorzes fuggvenye
     * @return: a furas sikeressege
     */
    public boolean ThinLayer(){

        if(layers < 1)
            return false;
        else{
            layers--;
            if(layers==0)
                CheckInteraction();
            return true;
        }
    }

    /**
     * uj szomszed felvetele a nyilvantartsba.
     * @param neighbour: az uj szomszed
     * @return: a felvetel sikeressege
     */
    public boolean AddNeighbour(Whereabout neighbour){
        boolean added = neighbours.add(neighbour);
        return added;
    }

    /**
     * A parameterul kapott whereabout-ot eltavolitja a szomszedai kozul.
     * @param neighbour
     */
    public void RemoveNeighbour(Whereabout neighbour){
        neighbours.remove(neighbour);
    }

    /**
     * az aszteroidat napvihar eri, aminek a hatasara kigyullad,
     * es meghivja a rajta tartozkodo entitaskora a die() metodust
     */
    public void OnFire(){
        onfire = true;
        display.Notify();
        onfire = false;

        if(!empty || layers>0){
            for(int i=0;i< entities.size();i++)
            {
                int tempsize = entities.size();
                entities.get(i).Die();
                if(entities.size() == tempsize-1)
                    i--;
            }
        }
    }

    /**
     * Ha a feltetel teljesul, meghivja az aszteroida nyersanyaganak az Interact metodusat,
     * az aszteroidat adva parameterul.
     */
    public void CheckInteraction(){
        if(sunnearness&&layers==0){
            if (material != null)
                material.Interact(this);
        }
    }

    /**
     * Az aszteroida a parameterul kapott whereabout-ot eltavolitja a szomszedai kozul.
     * Ha az eltavolitas utan a tomb ures, akkor felrobbantja magat.
     * @param explodedAsteroid: a felrobbano aszteroida
     */
    public void NearbyExplosion(Asteroid explodedAsteroid){
        RemoveNeighbour(explodedAsteroid);
        if(neighbours.size()==0)
            Explode();
    }

    /**
     * Az aszteroida felrobbanasakor hivodik.
     * Hatasara az osszes rajta tartozkodo entitasra meghivja a BlowUp() metodust,
     * majd eltavolitja magat a szomszedainak a nyilvantartasabol,
     * es kiveszi magat az aszteroidamezobol, valamint a controller nyilvantartasabol.
     */
    public void Explode(){
        exploding = true;
        display.Notify();

        Controller.GetInstanceOf().AsteroidExplode(this);

        for(int i=0;i<entities.size();i++)
        {
            int tempsize = entities.size();
            entities.get(i).BlowUp();
            if(entities.size() == tempsize-1)
                i--;
        }

        for(int i=0;i<neighbours.size();i++)
        {
            int tempsize = neighbours.size();
            neighbours.get(i).NearbyExplosion(this);
            if(neighbours.size() == tempsize-1)
                i--;
        }
        if (material != null)
            material.Disintegrate();
        asteroidfield.RemoveAsteroid(this);

        display.MarkedToClear();
    }

    /**
     * Visszaadja azt az aszteroidat, amely egy az aszteroidara lepo
     * objektumnak celaszteroidaja lesz.
     * Aszteroida eseteben ez onmaga.
     * @return - Ralepo objektum celaszteroidaja.
     */
    public Asteroid GetLandingPad() {
        return this;
    }

    /**
     * visszaadja a parameterul kapott szamu aszteroidat
     * @param i: a kert whereabout sorszama
     * @return: a whereabout
     */
    public Whereabout GetNeighbour(int i){
        return neighbours.get(i);
    }

    /**
     * visszaadja a szomszedainak a szamat
     * @return
     */
    public int GetNumberOfNeighbours(){
        return neighbours.size();
    }

    /**
     * onfire bool visszaadasa
     * */
    public boolean GetOnFireness() { return onfire; }
    /**
     * exploding bool visszaadasa
     * */
    public boolean GetExplodingness() { return exploding; }

}