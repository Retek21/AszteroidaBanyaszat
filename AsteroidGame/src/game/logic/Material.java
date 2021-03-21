package game.logic;
/*
Absztrakt Material class
 */
public abstract class Material implements Placeable{
    /*
    @String name - Material neve
     //coming soon//@ConditionManager cm - referencia a ConditionManager osztalyra
     */
   protected String name;


   /*
   Deploy ezen meg kellene gondolkodni
    */
    @Override
    public void Deploy(Asteroid a){
        Skeleton.WriteName("\t" + name + ": Deploy(a)");
        Skeleton.tab++;
        if(a.AddMaterial(this)) {
                ///coming soon
        }
        Skeleton.tab--;
    }
    /*
    absztrakt interact, mindegyik osztaly overriderolja
     */
    public void Interact(Asteroid a){
        Skeleton.WriteName("\t" + name + ": Interact(a)");
    }
    /*
    Egy masik anyaggal hasonlitja ossze magat
    @return Boolean - osszehasonlitas eredmenye
     */
    public Boolean CompareMaterial(Material m){
        if(m.name.compareTo(name) == 0) {
            Skeleton.WriteName("\t" + name + ": Comparematerial(" + m.name +") return: true");
            return true;
        }
        Skeleton.WriteName("\t" + name + ": Comparematerial(" + m.name +") return: false");
        return false;
    }
    /*
    Jelez a controllernek, hogy kikerult a jatekbol
     */
    public void Disintegrate(){
        Skeleton.WriteName("\t" + name + ": Disintegrate");
    }
}
